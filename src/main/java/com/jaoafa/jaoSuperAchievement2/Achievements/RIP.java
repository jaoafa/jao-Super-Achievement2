package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import com.jaoafa.jaoSuperAchievement2.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * No. 38
 * <p>
 * R.I.P.
 * 安らかに眠れ
 * いずれかの方法で死亡する
 *
 * @category jao Achievement
 * @since 2020/04/24
 */
public class RIP implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        new BukkitRunnable() {
            public void run() {
                if (!Achievementjao.getAchievement(player, new AchievementType(38))) {
                    player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                }
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
