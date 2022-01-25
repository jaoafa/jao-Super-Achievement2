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

public class BewareOfBears implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.BEWAREOFBEARS;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnBewareOfBears(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }

        new BukkitRunnable() {
            public void run() {
                UUID mine_book000_uuid = UUID.fromString("32ff7cdc-a1b4-450a-aa7e-6af75fe8c37c");
                if (player.getUniqueId().equals(mine_book000_uuid)) {
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        Achievementjao.getAchievementAsync(p, getAchievement());
                    }
                    return;
                }

                Player mine_book000 = Bukkit.getPlayer(mine_book000_uuid);
                if (mine_book000 == null)
                    return;
                if (!mine_book000.isOnline())
                    return;

                Achievementjao.getAchievementAsync(player, getAchievement());
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
