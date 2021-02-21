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
 * No. 73
 * <p>
 * XnZ
 * Admin,ModeratorのXnZと時間を共にする
 * X4Z,X5Z,X9Z全員がログインしている時にログインする
 *
 * @category jao Achievement
 * @since 2020/10/11
 */
public class XnZ implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void On(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            public void run() {
                UUID X4Z_uuid = UUID.fromString("5799296a-d1ec-4252-93bd-440bb9caa65c");
                Player X4Z = Bukkit.getPlayer(X4Z_uuid);
                UUID X5Z_uuid = UUID.fromString("0bdc0219-f3c3-4d73-a4df-1d8bd088f419");
                Player X5Z = Bukkit.getPlayer(X5Z_uuid);
                UUID X9Z_uuid = UUID.fromString("7008531a-539b-4dfc-8b81-7b267d18dd0a");
                Player X9Z = Bukkit.getPlayer(X9Z_uuid);

                if (X4Z == null) {
                    return;
                }
                if (!X4Z.isOnline()) {
                    return;
                }
                if (X5Z == null) {
                    return;
                }
                if (!X5Z.isOnline()) {
                    return;
                }
                if (X9Z == null) {
                    return;
                }
                if (!X9Z.isOnline()) {
                    return;
                }

                if (player.getUniqueId().equals(X4Z_uuid) || player.getUniqueId().equals(X5Z_uuid) || player.getUniqueId().equals(X9Z_uuid)) {
                    for (Player play : Bukkit.getServer().getOnlinePlayers()) {
                        if (!Achievementjao.getAchievement(play, new AchievementType(73))) {
                            play.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                            return;
                        }
                    }
                    return;
                }

                // どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
                if (!Achievementjao.getAchievement(player, new AchievementType(73))) {
                    player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                }
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
