package com.jaoafa.jaoSuperAchievement2.Achievements;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 25
 *
 * ded
 * 「jaojao」を発言せずに退出する
 * 「jaojao」を発言せずに退出する
 *
 * @since 2020/04/06
 * @category jao Achievement
 *
 */
public class FirstDed implements Listener {
	List<UUID> JaoJao = new ArrayList<UUID>();

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjaojao(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();
		if (!message.equalsIgnoreCase("jaojao")) {
			return;
		}

		if (!JaoJao.contains(player.getUniqueId())) {
			JaoJao.add(player.getUniqueId());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnNotSpeakjaojaoLeft(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		if (JaoJao.contains(player.getUniqueId())) {
			JaoJao.remove(player.getUniqueId());
			return;
		}

		if (!Achievementjao.getAchievement(player, new AchievementType(25))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}