package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class WelcomeToPoultryFarm implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.WELCOMETOPOULTRYFARM;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnFirstX9Z(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }

        new BukkitRunnable() {
            public void run() {
                UUID X9Z_uuid = UUID.fromString("7008531a-539b-4dfc-8b81-7b267d18dd0a");
                if (player.getUniqueId().equals(X9Z_uuid)) {
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        Achievementjao.getAchievementAsync(p, getAchievement());
                    }
                    return;
                }

                Player X9Z = Bukkit.getPlayer(X9Z_uuid);
                if (X9Z == null)
                    return;
                if (!X9Z.isOnline())
                    return;

                Achievementjao.getAchievementAsync(player, getAchievement());
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
