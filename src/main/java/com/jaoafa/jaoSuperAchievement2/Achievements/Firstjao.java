package com.jaoafa.jaoSuperAchievement2.Achievements;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 1
 *
 * はじめてのjao
 * jao鯖に初めて入る
 * 鯖に初めてログインする
 *
 * @since 2020/04/05
 * @category jao Achievement
 *
 */
public class Firstjao implements Listener {
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnFirstjao(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		// どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
		if (!Achievementjao.getAchievement(player, new AchievementType(1))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}