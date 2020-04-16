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
 * No. 28
 *
 * 熊出没注意
 * mine_book000と時間を共にする
 * mine_book000がいる時にログインする
 *
 * @since 2020/04/17
 * @category jao Achievement
 *
 */
public class BewareOfBears implements Listener {
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnFirstX4Z(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		UUID mine_book000_uuid = UUID.fromString("32ff7cdc-a1b4-450a-aa7e-6af75fe8c37c");
		if (player.getUniqueId().equals(mine_book000_uuid)) {
			for (Player play : Bukkit.getServer().getOnlinePlayers()) {
				if (!Achievementjao.getAchievement(play, new AchievementType(28))) {
					play.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
					return;
				}
			}
			return;
		}

		Player mine_book000 = Bukkit.getPlayer(mine_book000_uuid);
		if (mine_book000 == null)
			return;
		if (!mine_book000.isOnline())
			return;

		// どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
		if (!Achievementjao.getAchievement(player, new AchievementType(28))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}