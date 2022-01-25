package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class MyHead implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.MYHEAD;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }
        String message = event.getMessage();

        if (!message.equals("/head")) {
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
