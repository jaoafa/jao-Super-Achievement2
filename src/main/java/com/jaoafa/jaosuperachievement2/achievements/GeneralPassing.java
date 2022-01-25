package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GeneralPassing implements AchievementInterface, Listener {
    final Map<UUID, Long> LoginTime = new HashMap<>();

    @Override
    public Achievement getAchievement() {
        return Achievement.GENERALPASSING;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }
        LoginTime.put(player.getUniqueId(), System.currentTimeMillis() / 1000L);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }

        if (!LoginTime.containsKey(player.getUniqueId())) {
            return;
        }

        if (((System.currentTimeMillis() / 1000L) - LoginTime.get(player.getUniqueId())) > 10) {
            LoginTime.remove(player.getUniqueId());
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
        LoginTime.remove(player.getUniqueId());
    }
}
