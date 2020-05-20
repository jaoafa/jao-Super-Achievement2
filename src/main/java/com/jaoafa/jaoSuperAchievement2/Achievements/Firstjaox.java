package com.jaoafa.jaoSuperAchievement2.Achievements;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 44
 *
 * 感謝の極み
 * 「jaox」と発言する
 * jaoxと発言した場合
 * ※隠し要素
 *
 * @since 2020/05/21
 * @category jao Achievement
 *
 */
public class Firstjaox implements Listener {
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeak(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();

		if (!message.equalsIgnoreCase("jaox")) {
			return;
		}

		if (!Achievementjao.getAchievement(player, new AchievementType(44))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}