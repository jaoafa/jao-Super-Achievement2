package com.jaoafa.jaoSuperAchievement2.Task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Achievements.WhereaboutsUnknown;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

public class Task_WhereaboutsUnknown extends BukkitRunnable {
	Player player;

	public Task_WhereaboutsUnknown(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		if (!player.isOnline()) {
			return;
		}
		if (WhereaboutsUnknown.getActioned().contains(player.getUniqueId())) {
			WhereaboutsUnknown.getActioned().remove(player.getUniqueId());
			return;
		}
		if (!Achievementjao.getAchievement(player, new AchievementType(42))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
	}
}
