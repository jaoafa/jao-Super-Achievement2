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

public class MushroomPlantation implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.MUSHROOMPLANTATION;
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnFirstHirotaisou2012(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            public void run() {
                UUID Hirotaisou2012_uuid = UUID.fromString("39cf878b-ef0b-44fc-a2c6-de3d540a4728");
                if (player.getUniqueId().equals(Hirotaisou2012_uuid)) {
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        Achievementjao.getAchievementAsync(player, getAchievement());
                    }
                    return;
                }

                Player Hirotaisou2012 = Bukkit.getPlayer(Hirotaisou2012_uuid);
                if (Hirotaisou2012 == null)
                    return;
                if (!Hirotaisou2012.isOnline())
                    return;

                Achievementjao.getAchievementAsync(player, getAchievement());
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
