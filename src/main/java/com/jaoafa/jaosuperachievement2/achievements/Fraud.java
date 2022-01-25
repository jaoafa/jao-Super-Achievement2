package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import com.jaoafa.jaosuperachievement2.task.Task_Fraud;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Fraud implements AchievementInterface, Listener {
    static final Map<UUID, BukkitTask> JaoJaoTask = new HashMap<>();

    @Override
    public Achievement getAchievement() {
        return Achievement.FRAUD;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }
        Component component = event.message();
        String message = PlainTextComponentSerializer.plainText().serialize(component);

        if (!message.equals("jaojao") && !message.equals("j2")) {
            return;
        }

        BukkitTask task = new Task_Fraud(player).runTaskLaterAsynchronously(Main.getJavaPlugin(), 1200L); // 60s * 20tick
        JaoJaoTask.put(player.getUniqueId(), task);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }

        if (!JaoJaoTask.containsKey(player.getUniqueId())) {
            return;
        }

        if (!JaoJaoTask.get(player.getUniqueId()).isCancelled()) {
            JaoJaoTask.get(player.getUniqueId()).cancel();
        }
        JaoJaoTask.remove(player.getUniqueId());
    }
}