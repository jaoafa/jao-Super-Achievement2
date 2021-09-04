package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import com.jaoafa.jaosuperachievement2.task.Task_WhereaboutsUnknown;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class WhereaboutsUnknown implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.WHEREABOUTSUNKNOWN;
    }

    static List<UUID> Actioned = new ArrayList<>();
    static Map<UUID, BukkitTask> WhereaboutsUnknownTask = new HashMap<>();

    public static List<UUID> getActioned() {
        return Actioned;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }

        if (!Actioned.contains(player.getUniqueId())) {
            Actioned.add(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }

        if (!Actioned.contains(player.getUniqueId())) {
            Actioned.add(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }

        Actioned.remove(player.getUniqueId());
        if (WhereaboutsUnknownTask.containsKey(player.getUniqueId())
                && !WhereaboutsUnknownTask.get(player.getUniqueId()).isCancelled()) {
            WhereaboutsUnknownTask.get(player.getUniqueId()).cancel();
            WhereaboutsUnknownTask.remove(player.getUniqueId());
        }

        BukkitTask task = new Task_WhereaboutsUnknown(player).runTaskLater(Main.getJavaPlugin(), 6000L); // 5分
        WhereaboutsUnknownTask.put(player.getUniqueId(), task);
    }
}