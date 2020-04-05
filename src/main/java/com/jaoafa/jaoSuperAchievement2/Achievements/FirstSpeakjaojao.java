package com.jaoafa.jaoSuperAchievement2.Achievements;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 4
 *
 * はじめてのjaojao
 * 「jaojao」と発言してから退出する
 * 「jaojao」と発言して10秒以内にサーバから退出した場合
 *
 * @since 2020/04/05
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class FirstSpeakjaojao implements Listener {
	Map<UUID, Long> JaoJaoTime = new HashMap<UUID, Long>();

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjaojao(PlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();
		if (!message.equalsIgnoreCase("jaojao")) {
			return;
		}

		JaoJaoTime.put(player.getUniqueId(), System.currentTimeMillis() / 1000L);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjaojaoLeft(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		if (!JaoJaoTime.containsKey(player.getUniqueId())) {
			return;
		}

		if (((System.currentTimeMillis() / 1000L) - JaoJaoTime.get(player.getUniqueId())) > 10) {
			return;
		}

		if (!Achievementjao.getAchievement(player, new AchievementType(4))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}