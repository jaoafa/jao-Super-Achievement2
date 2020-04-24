package com.jaoafa.jaoSuperAchievement2.Achievements;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 14
 *
 * はじめてのものまね
 * 直前に送られたメッセージと同じメッセージを送る
 * 直前に送った別プレイヤーのメッセージと同じものを送信
 *
 * @since 2020/04/05
 * @category jao Achievement
 *
 */
public class FirstMimicry implements Listener {
	String OLDMessage = null;
	UUID OLDPlayerUUID = null;

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjao_afa(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();

		if (!message.equals(OLDMessage)) {
			OLDMessage = message;
			OLDPlayerUUID = player.getUniqueId();
			return; // メッセージが同じじゃなかったらはじく
		}
		if (OLDPlayerUUID.equals(player.getUniqueId())) {
			OLDMessage = message;
			OLDPlayerUUID = player.getUniqueId();
			return; // プレイヤーが同じだったらはじく
		}

		if (!Achievementjao.getAchievement(player, new AchievementType(14))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}

		OLDMessage = message;
		OLDPlayerUUID = player.getUniqueId();
	}
}