package com.jaoafa.jaoSuperAchievement2.Achievements;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 24
 *
 * 寝てる暇などない
 * ベッドで寝る
 * 夜中にベッドで寝た場合
 *
 * @since 2020/04/06
 * @category jao Achievement
 *
 */
public class NoTimeForSleep implements Listener {
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnBedEnter(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		if (!Achievementjao.getAchievement(player, new AchievementType(24))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
