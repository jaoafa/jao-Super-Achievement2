package com.jaoafa.jaoSuperAchievement2.Achievements;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 43
 *
 * そうだ、地底人になろう
 * 奈落に落ちて死ぬ
 * 奈落に落ちて死んだ時に発動 -> Y-64以下での死亡時
 *
 * @since 2020/05/21
 * @category jao Achievement
 *
 */
public class UndergroundPeople implements Listener {
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();

		if (player.getLocation().getY() > -64) {
			return;
		}

		if (!Achievementjao.getAchievement(player, new AchievementType(42))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}