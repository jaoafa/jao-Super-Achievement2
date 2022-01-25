package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import com.jaoafa.jaosuperachievement2.task.Task_Silence;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class Silence implements AchievementInterface, Listener {
    static final List<UUID> Speaked = new ArrayList<>();
    static final Map<UUID, BukkitTask> SilenceTask = new HashMap<>();

    public static List<UUID> getSpeaked() {
        return Speaked;
    }

    @Override
    public Achievement getAchievement() {
        return Achievement.SILENCE;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }

        Speaked.remove(player.getUniqueId());
        if (SilenceTask.containsKey(player.getUniqueId()) && !SilenceTask.get(player.getUniqueId()).isCancelled()) {
            SilenceTask.get(player.getUniqueId()).cancel();
            SilenceTask.remove(player.getUniqueId());
        }

        BukkitTask task = new Task_Silence(player).runTaskLater(Main.getJavaPlugin(), 1200L); // 1分
        SilenceTask.put(player.getUniqueId(), task);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }

        if (!Speaked.contains(player.getUniqueId())) {
            Speaked.add(player.getUniqueId());
        }
    }
}