package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstServerLogin implements AchievementInterface, Listener {
    boolean first = false;

    @Override
    public Achievement getAchievement() {
        return Achievement.FIRSTSERVERLOGIN;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        if (first) {
            return;
        }

        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }
        first = true;
        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
