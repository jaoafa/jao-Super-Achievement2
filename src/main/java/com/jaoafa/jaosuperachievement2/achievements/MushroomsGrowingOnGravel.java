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

public class MushroomsGrowingOnGravel implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.MUSHROOMSGROWINGONGRAVEL;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }

        new BukkitRunnable() {
            public void run() {
                UUID X4Z_uuid = UUID.fromString("5799296a-d1ec-4252-93bd-440bb9caa65c");
                Player X4Z = Bukkit.getPlayer(X4Z_uuid);
                UUID Hirotaisou2012_uuid = UUID.fromString("39cf878b-ef0b-44fc-a2c6-de3d540a4728");
                Player Hirotaisou2012 = Bukkit.getPlayer(Hirotaisou2012_uuid);

                if (X4Z == null) {
                    return;
                }
                if (!X4Z.isOnline()) {
                    return;
                }
                if (Hirotaisou2012 == null) {
                    return;
                }
                if (!Hirotaisou2012.isOnline()) {
                    return;
                }

                if (player.getUniqueId().equals(X4Z_uuid) || player.getUniqueId().equals(Hirotaisou2012_uuid)) {
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
