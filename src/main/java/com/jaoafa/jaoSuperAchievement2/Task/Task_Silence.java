package com.jaoafa.jaoSuperAchievement2.Task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Achievements.Silence;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

public class Task_Silence extends BukkitRunnable {
	Player player;

	public Task_Silence(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		if (!player.isOnline()) {
			return;
		}
		if (Silence.getSpeaked().contains(player.getUniqueId())) {
			Silence.getSpeaked().remove(player.getUniqueId());
			return;
		}
		if (!Achievementjao.getAchievement(player, new AchievementType(41))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
	}
}
