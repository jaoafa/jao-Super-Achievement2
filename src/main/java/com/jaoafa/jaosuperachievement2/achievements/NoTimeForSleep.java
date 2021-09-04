package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class NoTimeForSleep implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.NOTIMEFORSLEEP;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnBed(PlayerBedEnterEvent event) {
        if(event.getPlayer().hasMetadata("NPC")){
            return;
        }
        Achievementjao.getAchievementAsync(event.getPlayer(), getAchievement());
    }
}
