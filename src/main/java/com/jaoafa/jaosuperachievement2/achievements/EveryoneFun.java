package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EveryoneFun implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.EVERYONEFUN;
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (Bukkit.getServer().getOnlinePlayers().size() < 10) {
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}

