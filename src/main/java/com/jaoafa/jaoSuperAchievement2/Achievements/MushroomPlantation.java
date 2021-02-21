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
 * No. 29
 * <p>
 * きのこ栽培工場
 * Hirotaisou2012と時間を共にする
 * Hirotaisou2012がいる時にログインする
 *
 * @category jao Achievement
 * @since 2020/04/17
 */
public class MushroomPlantation implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnFirstHirotaisou2012(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            public void run() {
                UUID Hirotaisou2012_uuid = UUID.fromString("39cf878b-ef0b-44fc-a2c6-de3d540a4728");
                if (player.getUniqueId().equals(Hirotaisou2012_uuid)) {
                    for (Player play : Bukkit.getServer().getOnlinePlayers()) {
                        if (!Achievementjao.getAchievement(play, new AchievementType(29))) {
                            play.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                            return;
                        }
                    }
                    return;
                }

                Player Hirotaisou2012 = Bukkit.getPlayer(Hirotaisou2012_uuid);
                if (Hirotaisou2012 == null)
                    return;
                if (!Hirotaisou2012.isOnline())
                    return;

                // どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
                if (!Achievementjao.getAchievement(player, new AchievementType(29))) {
                    player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                    return;
                }
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
