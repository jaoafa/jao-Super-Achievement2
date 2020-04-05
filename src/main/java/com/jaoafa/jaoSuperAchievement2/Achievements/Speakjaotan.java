package com.jaoafa.jaoSuperAchievement2.Achievements;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 6
 *
 * あのお方
 * jaotanの名を言う
 * 「jaotan」を含むメッセージを送信したとき
 *
 * @since 2020/04/05
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class Speakjaotan implements Listener {
	JavaPlugin plugin;

	public Speakjaotan(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjaotan(PlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();
		if (!message.contains("jaotan")) {
			return;
		}

		if (!Achievementjao.getAchievement(player, new AchievementType(6))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}