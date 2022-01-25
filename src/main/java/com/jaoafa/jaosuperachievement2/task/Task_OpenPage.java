package com.jaoafa.jaosuperachievement2.task;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.MySQLDBManager;
import com.jaoafa.jaosuperachievement2.api.AchievementAPI;
import com.jaoafa.jaosuperachievement2.event.Event_JSA;
import com.jaoafa.jaosuperachievement2.lib.PlayerPageData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.StringWriter;
import java.sql.*;
import java.util.*;

public class Task_OpenPage extends BukkitRunnable {
    final Player player;
    final OfflinePlayer offplayer;
    final int page;
    final Map<Integer, Integer> gettedPlayerCountCache = new LinkedHashMap<>();
    long gettedPlayerCountCache_unixtime = -1;

    public Task_OpenPage(Player player, OfflinePlayer offplayer, int page) {
        this.player = player;
        this.offplayer = offplayer;
        this.page = page;
    }

    @Override
    public void run() {
        player.sendMessage(Component.text().append(
            AchievementAPI.getPrefix(),
            Component.text("情報を取得しています…しばらくお待ちください！")
        ));

        try {
            Inventory inv = Bukkit.getServer().createInventory(player, 4 * 9,
                Component.text(offplayer.getName() + "のjaoSuperAchievement"));

            int Got = getGettedAchievementCount(offplayer.getUniqueId());
            int notGot = getNotGettedAchievementCount(offplayer.getUniqueId());

            setItemSkull(inv, 4, offplayer, AchievementAPI.getjaoSuperAchievement(),
                Component.text("解除済み実績数: " + Got + "個", NamedTextColor.GREEN),
                Component.text("未解除実績数: " + notGot + "個", NamedTextColor.RED)
            );

            // インベントリスロット
            // 前へ: 0 / jSA解除済みor未解除数: 4 / 次へ: 8
            // 実績配置は9～ (1ページに27個配置可能)
            int i = 9;
            MySQLDBManager sqlmanager = Main.getMySQLDBManager();
            Connection conn = sqlmanager.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM jaoSuperAchievement2_Type LIMIT ?, ?");
            statement.setInt(1, (page - 1) * 27);
            statement.setInt(2, 27);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String title = res.getString("title");
                String description = res.getString("description");
                boolean hidden = res.getBoolean("hidden");
                Timestamp date = res.getTimestamp("created_at");
                String date_str = Main.sdfFormat(date);
                int gotPlayerCount = getGettedPlayerCount(id);

                boolean got = isGot(offplayer, id);
                Material material;
                String msg;
                String hiddenmsg = "";
                String unlockDate;
                int damage = id % 16;
                if (got) {
                    String gotTime = getGotTime(offplayer, id);
                    material = getWOOL(damage); // 取得済みなら羊毛
                    msg = ChatColor.GREEN + "実績取得済み";
                    unlockDate = "(解除日時: " + gotTime + " | 解除者数: " + gotPlayerCount + "人)";
                } else {
                    material = getSTAINED_GLASS(damage); // 未取得なら色付きガラス
                    msg = ChatColor.RED + "実績未取得";
                    unlockDate = "(解除者数: " + gotPlayerCount + "人)";

                    if (hidden) { // 隠し要素なら説明の文字列を全部アスタリスクにする
                        description = replaceAsterisk(description);
                    }
                }

                if (hidden)
                    hiddenmsg = "※隠し要素";

                setItem(inv, i, material, Component.text("[" + id + "] " + title),
                    Component.text(description, NamedTextColor.GRAY),
                    Component.empty(),
                    Component.text().append(
                        Component.text(msg),
                        Component.text(hiddenmsg, NamedTextColor.GOLD, TextDecoration.UNDERLINED)
                    ).build(),
                    Component.text(unlockDate, NamedTextColor.BLUE),
                    Component.text().append(
                        Component.text("実装日時: " + date_str, NamedTextColor.LIGHT_PURPLE)
                    ).build()
                );
                //if(i >= 35) break;
                i++;
            }

            res.close();
            statement.close();

            int all = getAchievementCount();
            if (page <= 1) {
                setItem(inv, 0, Material.BARRIER, Component.text("閉じる"));
            } else {
                setItemSkull(inv, 0, "MHF_ArrowLeft", Component.text("前へ"));
            }
            if (all >= (page * 27) + 1) {
                // 次へを追加
                setItemSkull(inv, 8, "MHF_ArrowRight", Component.text("次へ"));
            } else {
                setItem(inv, 8, Material.BARRIER, Component.text("閉じる"));
            }

            PlayerPageData ppd = new PlayerPageData(offplayer, page);
            Event_JSA.jSA.put(player.getName(), ppd);
            new Task_OpenInventory(player, inv).runTask(Main.getJavaPlugin());

        } catch (SQLException e) {
            player.sendMessage(Component.text().append(
                AchievementAPI.getPrefix(),
                Component.text("データベースサーバに接続できなかったか、操作に失敗しました。開発部にお問い合わせください。", NamedTextColor.YELLOW)
            ));
            e.printStackTrace();
        }
    }

    Material getWOOL(int damage) {
        List<Material> materials = Arrays.asList(
            Material.WHITE_WOOL,
            Material.ORANGE_WOOL,
            Material.MAGENTA_WOOL,
            Material.LIGHT_BLUE_WOOL,
            Material.YELLOW_WOOL,
            Material.LIME_WOOL,
            Material.PINK_WOOL,
            Material.GRAY_WOOL,
            Material.LIGHT_GRAY_WOOL,
            Material.CYAN_WOOL,
            Material.PURPLE_WOOL,
            Material.BLUE_WOOL,
            Material.BROWN_WOOL,
            Material.GREEN_WOOL,
            Material.RED_WOOL,
            Material.BLACK_WOOL);
        return materials.get(damage);
    }

    Material getSTAINED_GLASS(int damage) {
        List<Material> materials = Arrays.asList(
            Material.WHITE_STAINED_GLASS,
            Material.ORANGE_STAINED_GLASS,
            Material.MAGENTA_STAINED_GLASS,
            Material.LIGHT_BLUE_STAINED_GLASS,
            Material.YELLOW_STAINED_GLASS,
            Material.LIME_STAINED_GLASS,
            Material.PINK_STAINED_GLASS,
            Material.GRAY_STAINED_GLASS,
            Material.LIGHT_GRAY_STAINED_GLASS,
            Material.CYAN_STAINED_GLASS,
            Material.PURPLE_STAINED_GLASS,
            Material.BLUE_STAINED_GLASS,
            Material.BROWN_STAINED_GLASS,
            Material.GREEN_STAINED_GLASS,
            Material.RED_STAINED_GLASS,
            Material.BLACK_STAINED_GLASS);
        return materials.get(damage);
    }

    String replaceAsterisk(String value) {
        int l = value.length();
        return new StringWriter() {
            {
                for (int i = 0; i < l; i++) {
                    write("*");
                }
            }
        }.toString();
    }

    void setItem(Inventory inv, int index, Material material, Component title, Component... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.displayName(title);
        if (lore != null)
            itemmeta.lore(Arrays.asList(lore));
        item.setItemMeta(itemmeta);
        inv.setItem(index, item);
    }

    void setItemSkull(Inventory inv, int index, String name, Component title) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull_meta = (SkullMeta) item.getItemMeta();
        //noinspection deprecation
        skull_meta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
        skull_meta.displayName(title);
        item.setItemMeta(skull_meta);
        inv.setItem(index, item);
    }

    void setItemSkull(Inventory inv, int index, OfflinePlayer offplayer, Component title, Component... lore) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull_meta = (SkullMeta) item.getItemMeta();
        skull_meta.setOwningPlayer(offplayer);
        skull_meta.displayName(title);
        if (lore != null)
            skull_meta.lore(Arrays.asList(lore));
        item.setItemMeta(skull_meta);
        inv.setItem(index, item);
    }

    boolean isGot(OfflinePlayer offplayer, int id) {
        try {
            MySQLDBManager sqlmanager = Main.getMySQLDBManager();
            Connection conn = sqlmanager.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                "SELECT * FROM jaoSuperAchievement2 WHERE uuid = ? AND achievementid = ?;");
            statement.setString(1, offplayer.getUniqueId().toString());
            statement.setInt(2, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                res.close();
                statement.close();
                return true;
            } else {
                res.close();
                statement.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    String getGotTime(OfflinePlayer offplayer, int id) {
        try {
            MySQLDBManager sqlmanager = Main.getMySQLDBManager();
            Connection conn = sqlmanager.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                "SELECT * FROM jaoSuperAchievement2 WHERE uuid = ? AND achievementid = ?;");
            statement.setString(1, offplayer.getUniqueId().toString());
            statement.setInt(2, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                String time = Main.sdfFormat(res.getTimestamp("created_at"));
                res.close();
                statement.close();
                return time;
            } else {
                res.close();
                statement.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    int getGettedPlayerCount(int id) {
        if (gettedPlayerCountCache_unixtime != -1
            && gettedPlayerCountCache_unixtime > (System.currentTimeMillis() / 1000) - 3600
            && gettedPlayerCountCache.containsKey(id)) {
            // キャッシュから
            return gettedPlayerCountCache.get(id);
        }
        gettedPlayerCountCache.remove(id);
        // DBから
        try {
            MySQLDBManager sqlmanager = Main.getMySQLDBManager();
            Connection conn = sqlmanager.getConnection();

            try (PreparedStatement statement = conn
                .prepareStatement("SELECT * FROM jaoSuperAchievement2;")) {
                try (ResultSet res = statement.executeQuery()) {
                    while (res.next()) {
                        int achievementID = res.getInt("achievementid");
                        if (gettedPlayerCountCache.containsKey(achievementID)) {
                            gettedPlayerCountCache.put(achievementID,
                                gettedPlayerCountCache.get(achievementID) + 1);
                        } else {
                            gettedPlayerCountCache.put(achievementID, 1);
                        }
                    }
                }
            }
            gettedPlayerCountCache_unixtime = System.currentTimeMillis() / 1000;
            if (!gettedPlayerCountCache.containsKey(id)) {
                return 0;
            }
            return gettedPlayerCountCache.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 取得済み実績数を返却
     *
     * @param uuid UUID
     * @return 取得済み実績数
     * @throws SQLException SQLException
     */
    int getGettedAchievementCount(UUID uuid) throws SQLException {
        MySQLDBManager sqlmanager = Main.getMySQLDBManager();
        Connection conn = sqlmanager.getConnection();

        PreparedStatement statement = conn
            .prepareStatement("SELECT COUNT(*) FROM jaoSuperAchievement2 WHERE uuid = ?;");
        statement.setString(1, uuid.toString());
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            int ret = res.getInt(1);
            res.close();
            statement.close();
            return ret;
        } else {
            res.close();
            statement.close();
            return 0;
        }
    }

    /**
     * 未取得済み実績数を返却
     *
     * @param uuid UUID
     * @return 未取得済み実績数
     * @throws SQLException SQLException
     */
    int getNotGettedAchievementCount(UUID uuid) throws SQLException {
        int all = getAchievementCount();
        int getted = getGettedAchievementCount(uuid);
        return all - getted;
    }

    /**
     * すべての実績数を返却
     *
     * @return すべての実績数
     * @throws SQLException SQLException
     */
    int getAchievementCount() throws SQLException {
        MySQLDBManager sqlmanager = Main.getMySQLDBManager();
        Connection conn = sqlmanager.getConnection();

        PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM jaoSuperAchievement2_Type");
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            int ret = res.getInt(1);
            res.close();
            statement.close();
            return ret;
        } else {
            res.close();
            statement.close();
            return -1;
        }
    }
}
