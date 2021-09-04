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

public class FirstX4Z implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.FIRSTX4Z;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }

        new BukkitRunnable() {
            public void run() {
                UUID X4Zuuid = UUID.fromString("5799296a-d1ec-4252-93bd-440bb9caa65c");
                if (player.getUniqueId().equals(X4Zuuid)) {
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        Achievementjao.getAchievementAsync(p, getAchievement());
                    }
                    return;
                }

                Player X4Z = Bukkit.getPlayer(X4Zuuid);
                if (X4Z == null)
                    return;
                if (!X4Z.isOnline())
                    return;

                Achievementjao.getAchievementAsync(player, getAchievement());
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
