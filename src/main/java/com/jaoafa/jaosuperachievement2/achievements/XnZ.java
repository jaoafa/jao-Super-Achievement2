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

public class XnZ implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.XNZ;
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }

        new BukkitRunnable() {
            public void run() {
                UUID X4Z_uuid = UUID.fromString("5799296a-d1ec-4252-93bd-440bb9caa65c");
                Player X4Z = Bukkit.getPlayer(X4Z_uuid);
                UUID X5Z_uuid = UUID.fromString("0bdc0219-f3c3-4d73-a4df-1d8bd088f419");
                Player X5Z = Bukkit.getPlayer(X5Z_uuid);
                UUID X9Z_uuid = UUID.fromString("7008531a-539b-4dfc-8b81-7b267d18dd0a");
                Player X9Z = Bukkit.getPlayer(X9Z_uuid);

                if (X4Z == null) {
                    return;
                }
                if (!X4Z.isOnline()) {
                    return;
                }
                if (X5Z == null) {
                    return;
                }
                if (!X5Z.isOnline()) {
                    return;
                }
                if (X9Z == null) {
                    return;
                }
                if (!X9Z.isOnline()) {
                    return;
                }

                if (player.getUniqueId().equals(X4Z_uuid) || player.getUniqueId().equals(X5Z_uuid) || player.getUniqueId().equals(X9Z_uuid)) {
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        Achievementjao.getAchievementAsync(p, getAchievement());
                    }
                    return;
                }

                Achievementjao.getAchievementAsync(player, getAchievement());
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
