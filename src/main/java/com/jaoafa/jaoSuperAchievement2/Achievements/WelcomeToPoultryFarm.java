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
 * No. 30
 * <p>
 * 養鶏場へようこそ
 * X9Zと時間を共にする
 * X9Zがいる時にログインする
 *
 * @category jao Achievement
 * @since 2020/04/17
 */
public class WelcomeToPoultryFarm implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnFirstX9Z(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            public void run() {
                UUID X9Z_uuid = UUID.fromString("7008531a-539b-4dfc-8b81-7b267d18dd0a");
                if (player.getUniqueId().equals(X9Z_uuid)) {
                    for (Player play : Bukkit.getServer().getOnlinePlayers()) {
                        if (!Achievementjao.getAchievement(play, new AchievementType(30))) {
                            play.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                            return;
                        }
                    }
                    return;
                }

                Player X9Z = Bukkit.getPlayer(X9Z_uuid);
                if (X9Z == null)
                    return;
                if (!X9Z.isOnline())
                    return;

                // どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
                if (!Achievementjao.getAchievement(player, new AchievementType(30))) {
                    player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                }
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
