package com.jaoafa.jaosuperachievement2.api;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.MySQLDBManager;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Achievementjao {
    static private final FireworkEffect.Type[] types = {
        Type.BALL,
        Type.BALL_LARGE,
        Type.BURST,
        Type.CREEPER,
        Type.STAR,
    };
    static private final Random rand = new Random();
    static Map<UUID, List<Integer>> GettedAchievementCache = new HashMap<>(); // uuid, AchievementID

    /**
     * 実績を非同期に付与します。
     *
     * @param player 実績を取得するプレイヤー
     * @param achievement 取得する実績
     */
    public static void getAchievementAsync(Player player, Achievement achievement) {
        if (!Bukkit.getPluginManager().isPluginEnabled("jao-Super-Achievement2")) {
            return;
        }
        new BukkitRunnable() {
            public void run() {
                Achievementjao.getAchievement(player, achievement);
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }

    /**
     * 実績を非同期に付与します。
     *
     * @param player 実績を取得するプレイヤー
     * @param achievement 取得する実績
     */
    public static void getAchievementAsync(OfflinePlayer player, Achievement achievement) {
        if (!Bukkit.getPluginManager().isPluginEnabled("jao-Super-Achievement2")) {
            return;
        }
        new BukkitRunnable() {
            public void run() {
                Achievementjao.getAchievement(player, achievement);
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }

    /**
     * 実績を付与します。<br>
     * このメソッドでは、チャット・Discordメッセージ・花火を出します。
     *
     * @param player 実績を取得するプレイヤー
     * @param achievement 取得する実績
     * @see #getAchievementAsync(Player, Achievement)
     * @deprecated 同期的に動作してしまうため、 getAchievementAsync(Player, Achievement) を使用して下さい。
     */
    @Deprecated
    public static void getAchievement(Player player, Achievement achievement) {
        getAchievement(player, achievement, true);
    }

    /**
     * 実績を付与します。
     *
     * @param player 実績を取得するプレイヤー
     * @param achievement 取得する実績
     * @param isGrantAnnounce チャット・Discordメッセージを出すかどうか
     * @see #getAchievementAsync(Player, Achievement)
     * @deprecated 同期的に動作してしまうため、 getAchievementAsync(Player, Achievement) を使用して下さい。
     */
    @Deprecated
    public static void getAchievement(Player player, Achievement achievement, boolean isGrantAnnounce) {
        if (!Bukkit.getPluginManager().isPluginEnabled("jao-Super-Achievement2")) {
            return;
        }
        if (isAlreadyGotAchievement(player, achievement)) {
            return;
        }

        try {
            MySQLDBManager sqlmanager = Main.getMySQLDBManager();
            Connection conn = sqlmanager.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO jaoSuperAchievement2 (player, uuid, achievementid) VALUES (?, ?, ?);");
            statement.setString(1, player.getName());
            statement.setString(2, player.getUniqueId().toString());
            statement.setInt(3, achievement.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
            return;
        }

        if(!isGrantAnnounce){
            return;
        }

        int gettedPlayerCount = getGotPlayerCount(achievement.getId());

        Bukkit.getServer().sendMessage(Component.text().append(
            AchievementAPI.getPrefix(),
            Component.text(player.getName()),
            Component.space(),
            Component.text("が「"),
            Component.text(achievement.getTitle()),
            Component.text(String.format("」を取得しました！ (%d人目)", gettedPlayerCount))
        ));
        Main.getDiscord().sendMessage("597423199227084800",
            "**[jaoSuperAchievement2]** " + DiscordEscape(player.getName()) + "が「" + DiscordEscape(achievement.getTitle())
                + "」を取得しました！ (" + gettedPlayerCount + "人目)");

        new BukkitRunnable() {
            public void run() {
                Firework firework = player.getLocation().getWorld().spawn(player.getLocation().add(0, 3, 0),
                    Firework.class);
                FireworkMeta meta = firework.getFireworkMeta();
                Builder effect = FireworkEffect.builder();
                effect.with(types[rand.nextInt(types.length)]);
                effect.withColor(Color.RED, Color.AQUA, Color.YELLOW, Color.GREEN, Color.LIME, Color.ORANGE,
                    Color.PURPLE);
                effect.withFade(Color.RED, Color.AQUA, Color.YELLOW, Color.GREEN, Color.LIME, Color.ORANGE,
                    Color.PURPLE);
                effect.flicker(true);
                meta.setPower(1);
                meta.addEffect(effect.build());
                firework.setFireworkMeta(meta);
                firework.detonate();
            }
        }.runTaskLater(Main.getJavaPlugin(), 1);
    }

    /**
     * 実績を付与します。<br>
     * このメソッドでは、チャット・Discordメッセージを出します。
     *
     * @param offplayer 実績を取得するプレイヤー
     * @param achievement 取得する実績
     * @see #getAchievementAsync(OfflinePlayer, Achievement)
     * @deprecated 同期的に動作してしまうため、 getAchievementAsync(OfflinePlayer, Achievement) を使用して下さい。
     */
    @Deprecated
    public static void getAchievement(OfflinePlayer offplayer, Achievement achievement){
        getAchievement(offplayer, achievement, true);
    }

    /**
     * 実績を付与します。同期的に動作してしまうため、 getAchievementAsync(OfflinePlayer, Achievement) を使用して下さい。
     *
     * @param offplayer 実績を取得するプレイヤー
     * @param achievement 取得する実績
     * @param isGrantAnnounce チャット・Discordメッセージを出すかどうか
     * @see #getAchievementAsync(OfflinePlayer, Achievement)
     */
    @Deprecated
    public static void getAchievement(OfflinePlayer offplayer, Achievement achievement, boolean isGrantAnnounce) {
        if (!Bukkit.getPluginManager().isPluginEnabled("jao-Super-Achievement2")) {
            return;
        }
        if (isAlreadyGotAchievement(offplayer, achievement)) {
            return;
        }

        try {
            MySQLDBManager sqlmanager = Main.getMySQLDBManager();
            Connection conn = sqlmanager.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO jaoSuperAchievement2 (player, uuid, achievementid) VALUES (?, ?, ?);");
            statement.setString(1, offplayer.getName());
            statement.setString(2, offplayer.getUniqueId().toString());
            statement.setInt(3, achievement.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        if(!isGrantAnnounce){
            return;
        }

        int gettedPlayerCount = getGotPlayerCount(achievement.getId());

        Bukkit.getServer().sendMessage(Component.text().append(
            AchievementAPI.getPrefix(),
            Component.text(offplayer.getName() != null ? offplayer.getName() : offplayer.getUniqueId().toString()),
            Component.space(),
            Component.text("が「"),
            Component.text(achievement.getTitle()),
            Component.text(String.format("」を取得しました！ (%d人目)", gettedPlayerCount))
        ));
        Main.getDiscord().sendMessage("597423199227084800",
            "**[jaoSuperAchievement2]** " + DiscordEscape(offplayer.getName()) + "が「"
                + DiscordEscape(achievement.getTitle()) + "」を取得しました！ (" + gettedPlayerCount + "人目)");
    }

    /**
     * 既に実績を取得しているかどうかを返します。
     *
     * @param player 取得しているかどうかを確認するプレイヤー
     * @param achievement 実績
     * @return 実績を取得しているか
     * @see #isAlreadyGotAchievement(OfflinePlayer, Achievement)
     * @deprecated メソッド名の変更
     */
    @Deprecated
    public static boolean isAlreadyGettedAchievement(OfflinePlayer player, Achievement achievement) {
        return isAlreadyGotAchievement(player, achievement);
    }

    /**
     * 既に実績を取得しているかどうかを返します。
     *
     * @param player 取得しているかどうかを確認するプレイヤー
     * @param achievement 実績
     * @return 実績を取得しているか
     */
    public static boolean isAlreadyGotAchievement(OfflinePlayer player, Achievement achievement) {
        if (GettedAchievementCache.containsKey(player.getUniqueId())) {
            List<Integer> gotList = GettedAchievementCache.get(player.getUniqueId());
            if (gotList.contains(achievement.getId())) {
                return true;
            }
        }
        try {
            MySQLDBManager sqlmanager = Main.getMySQLDBManager();
            Connection conn = sqlmanager.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                "SELECT * FROM jaoSuperAchievement2 WHERE uuid = ? AND achievementid = ?");
            statement.setString(1, player.getUniqueId().toString());
            statement.setInt(2, achievement.getId());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                if (GettedAchievementCache.containsKey(player.getUniqueId())) {
                    List<Integer> gettedList = GettedAchievementCache.get(player.getUniqueId());
                    gettedList.add(achievement.getId());
                    GettedAchievementCache.put(player.getUniqueId(), gettedList);
                } else {
                    List<Integer> gettedList = new ArrayList<>();
                    gettedList.add(achievement.getId());
                    GettedAchievementCache.put(player.getUniqueId(), gettedList);
                }
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

    /**
     * 実績取得済みのプレイヤー数を返します。
     *
     * @param id 実績のId
     * @return その実績を取得済みのプレイヤー数
     * @deprecated メソッド名および引数の変更
     * @see #getGotPlayerCount(Achievement)
     */
    @Deprecated
    static int getGettedPlayerCount(int id) {
        return getGotPlayerCount(Achievement.fromId(id));
    }

    /**
     * 実績取得済みのプレイヤー数を返します。
     *
     * @param achievement 実績
     * @return その実績を取得済みのプレイヤー数
     */
    static int getGotPlayerCount(Achievement achievement) {
        if(achievement == null){
            return 0;
        }
        try {
            MySQLDBManager sqlmanager = Main.getMySQLDBManager();
            Connection conn = sqlmanager.getConnection();

            PreparedStatement statement = conn
                .prepareStatement("SELECT COUNT(*) FROM jaoSuperAchievement2 WHERE achievementid = ?;");
            statement.setInt(1, achievement.getId());
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
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    static String DiscordEscape(String text) {
        return text == null ? "" : text.replace("_", "\\_").replace("*", "\\*").replace("~", "\\~");
    }
}
