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
 * No. 72
 *
 * 砂利の上に生えたきのこ
 * X4Z,Hirotaisou2012と時間をともにする
 * X4Z,Hirotaisou2012がいるときにログインする（している）
 *
 * @since 2020/10/10
 * @category jao Achievement
 *
 */
public class MushroomsGrowingOnGravel implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void On(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            public void run() {
                UUID X4Z_uuid = UUID.fromString("5799296a-d1ec-4252-93bd-440bb9caa65c");
                Player X4Z = Bukkit.getPlayer(X4Z_uuid);
                UUID Hirotaisou2012_uuid = UUID.fromString("39cf878b-ef0b-44fc-a2c6-de3d540a4728");
                Player Hirotaisou2012 = Bukkit.getPlayer(Hirotaisou2012_uuid);

                if (X4Z == null) {
                    return;
                }
                if (!X4Z.isOnline()) {
                    return;
                }
                if (Hirotaisou2012 == null) {
                    return;
                }
                if (!Hirotaisou2012.isOnline()) {
                    return;
                }

                if (player.getUniqueId().equals(X4Z_uuid) || player.getUniqueId().equals(Hirotaisou2012_uuid)) {
                    for (Player play : Bukkit.getServer().getOnlinePlayers()) {
                        if (!Achievementjao.getAchievement(play, new AchievementType(72))) {
                            play.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                            return;
                        }
                    }
                    return;
                }

                // どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
                if (!Achievementjao.getAchievement(player, new AchievementType(72))) {
                    player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                }
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
