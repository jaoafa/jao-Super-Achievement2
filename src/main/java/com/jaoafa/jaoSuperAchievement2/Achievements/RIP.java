package com.jaoafa.jaoSuperAchievement2.Achievements;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.jaoafa.jaoSuperAchievement2.Main;
import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 38
 *
 * R.I.P.
 * 安らかに眠れ
 * いずれかの方法で死亡する
 *
 * @since 2020/04/24
 * @category jao Achievement
 *
 */
public class RIP implements Listener {
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();

		new BukkitRunnable() {
			public void run() {
				if (!Achievementjao.getAchievement(player, new AchievementType(38))) {
					player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
					return;
				}
			}
		}.runTaskAsynchronously(Main.getJavaPlugin());
	}
}
