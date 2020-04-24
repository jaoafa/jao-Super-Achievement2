package com.jaoafa.jaoSuperAchievement2.Achievements;

import java.util.HashMap;
import java.util.Map;
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
 * No. 3
 *
 * はじめてのjao afa
 * 「jao afa」と発言する
 * 「jao」と発言したあとに「afa」と発言した場合
 *
 * @since 2020/04/05
 * @category jao Achievement
 *
 */
public class FirstSpeakjao_afa implements Listener {
	Map<UUID, String> OldMessage = new HashMap<UUID, String>();

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjao_afa(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();
		if (!message.equalsIgnoreCase("afa"))
			OldMessage.put(player.getUniqueId(), message);
		if (!message.equalsIgnoreCase("afa")) {
			return;
		}
		if (!OldMessage.containsKey(player.getUniqueId())) {
			return;
		}
		if (!OldMessage.get(player.getUniqueId()).equalsIgnoreCase("jao")) {
			return;
		}

		if (!Achievementjao.getAchievement(player, new AchievementType(3))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
