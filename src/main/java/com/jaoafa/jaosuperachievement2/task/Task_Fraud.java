package com.jaoafa.jaosuperachievement2.task;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Task_Fraud extends BukkitRunnable {
    final Player player;

    public Task_Fraud(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (!player.isOnline()) {
            return;
        }

        Achievementjao.getAchievementAsync(player, Achievement.FRAUD);
    }
}
