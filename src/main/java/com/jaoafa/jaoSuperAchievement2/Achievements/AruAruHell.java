package com.jaoafa.jaoSuperAchievement2.Achievements;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 13
 *
 * アルアル地獄
 * .tと発言する
 * チャットより.tと発言した場合
 * ※隠し要素
 *
 * @since 2020/04/05
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class AruAruHell implements Listener {
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnAruAruHell(PlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();
		if (!message.equalsIgnoreCase(".t")) {
			return;
		}

		if (!Achievementjao.getAchievement(player, new AchievementType(13))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}