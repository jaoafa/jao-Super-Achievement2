package com.jaoafa.jaosuperachievement2.task;

import com.jaoafa.jaosuperachievement2.achievements.Silence;
import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Task_Silence extends BukkitRunnable {
    final Player player;

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
        Achievementjao.getAchievementAsync(player, Achievement.SILENCE);
    }
}
