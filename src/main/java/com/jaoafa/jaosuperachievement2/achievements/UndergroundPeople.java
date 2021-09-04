package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class UndergroundPeople implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.UNDERGROUNDPEOPLE;
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if(player.hasMetadata("NPC")){
            return;
        }

        if (player.getLocation().getY() > -64D) {
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}