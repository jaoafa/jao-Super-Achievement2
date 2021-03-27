package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import com.jaoafa.jaoSuperAchievement2.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * No. 50
 * <p>
 * たまごとケチャップの合わせ技
 * X9Z,mine_book000と時間をともにする
 * X9Z,mine_book000の両方がいるときにログインする
 * ※隠し要素
 *
 * @category jao Achievement
 * @since 2020/06/28
 */
public class EggAndKetchup implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void On(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            public void run() {
                UUID mine_book000_uuid = UUID.fromString("32ff7cdc-a1b4-450a-aa7e-6af75fe8c37c");
                Player mine_book000 = Bukkit.getPlayer(mine_book000_uuid);
                UUID X9Z_uuid = UUID.fromString("7008531a-539b-4dfc-8b81-7b267d18dd0a");
                Player X9Z = Bukkit.getPlayer(X9Z_uuid);

                if (mine_book000 == null) {
                    return;
                }
                if (!mine_book000.isOnline()) {
                    return;
                }
                if (X9Z == null) {
                    return;
                }
                if (!X9Z.isOnline()) {
                    return;
                }

                if (player.getUniqueId().equals(mine_book000_uuid) || player.getUniqueId().equals(X9Z_uuid)) {
                    for (Player play : Bukkit.getServer().getOnlinePlayers()) {
                        if (!Achievementjao.getAchievement(play, new AchievementType(50))) {
                            play.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                            return;
                        }
                    }
                    return;
                }

                // どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
                if (!Achievementjao.getAchievement(player, new AchievementType(50))) {
                    player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                }
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
