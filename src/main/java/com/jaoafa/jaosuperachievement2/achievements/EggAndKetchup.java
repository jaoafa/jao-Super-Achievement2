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

public class EggAndKetchup implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.EGGANDKETCHUP;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            public void run() {
                UUID mine_book000_uuid = UUID.fromString("32ff7cdc-a1b4-450a-aa7e-6af75fe8c37c");
                Player mine_book000 = Bukkit.getPlayer(mine_book000_uuid);
                UUID X9Z_uuid = UUID.fromString("7008531a-539b-4dfc-8b81-7b267d18dd0a");
                Player X9Z = Bukkit.getPlayer(X9Z_uuid);

                if (mine_book000 == null) {
                    return;
                }
                if (!mine_book000.isOnline()) {
                    return;
                }
                if (X9Z == null) {
                    return;
                }
                if (!X9Z.isOnline()) {
                    return;
                }

                if (player.getUniqueId().equals(mine_book000_uuid) || player.getUniqueId().equals(X9Z_uuid)) {
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
