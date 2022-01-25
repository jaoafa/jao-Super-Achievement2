package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;

public class Botch implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.BOTCH;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }
        if (Bukkit.getServer().getOnlinePlayers().size() != 1) {
            return;
        }
        Achievementjao.getAchievementAsync(player, getAchievement());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if ((Bukkit.getServer().getOnlinePlayers().size() - 1) != 1) {
            return;
        }
        Optional<? extends Player> player = Bukkit.getServer().getOnlinePlayers().stream()
            .filter(p -> p.getUniqueId() != event.getPlayer().getUniqueId())
            .findFirst();

        if (player.isEmpty()) {
            return;
        }
        if (player.get().hasMetadata("NPC")) {
            return;
        }

        Achievementjao.getAchievementAsync(player.get(), getAchievement());
    }
}
