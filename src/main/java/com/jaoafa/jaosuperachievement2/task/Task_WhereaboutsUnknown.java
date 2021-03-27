package com.jaoafa.jaosuperachievement2.task;

import com.jaoafa.jaosuperachievement2.achievements.WhereaboutsUnknown;
import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
        Achievementjao.getAchievementAsync(player, Achievement.WHEREABOUTSUNKNOWN);
	}
}
