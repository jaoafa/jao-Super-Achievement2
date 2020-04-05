package com.jaoafa.jaoSuperAchievement2.Achievements;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 8
 *
 * 砂利を感じに
 * 鯖管と時間を共にする
 * X4Zがいる時にログインする
 *
 * @since 2020/04/05
 * @category jao Achievement
 *
 */
public class FirstX4Z implements Listener {
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnFirstX4Z(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		UUID X4Zuuid = UUID.fromString("5799296a-d1ec-4252-93bd-440bb9caa65c");
		if (player.getUniqueId().equals(X4Zuuid)) {
			for (Player play : Bukkit.getServer().getOnlinePlayers()) {
				if (!Achievementjao.getAchievement(play, new AchievementType(8))) {
					play.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
					return;
				}
			}
			return;
		}

		Player X4Z = Bukkit.getPlayer(X4Zuuid);
		if (X4Z == null)
			return;
		if (!X4Z.isOnline())
			return;

		// どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
		if (!Achievementjao.getAchievement(player, new AchievementType(8))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}