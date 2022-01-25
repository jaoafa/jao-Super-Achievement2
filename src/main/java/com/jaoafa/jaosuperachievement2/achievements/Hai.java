package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import com.jaoafa.jaosuperachievement2.task.Task_Hai;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Hai implements AchievementInterface, Listener {
    static final Map<UUID, Long> LoginTime = new HashMap<>();
    static final Map<UUID, BukkitTask> HaiTask = new HashMap<>();

    @Override
    public Achievement getAchievement() {
        return Achievement.HAI;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }

        LoginTime.put(player.getUniqueId(), System.currentTimeMillis() / 1000);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }

        LoginTime.remove(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }
        Component component = event.message();
        String message = PlainTextComponentSerializer.plainText().serialize(component);

        if (message.equals("jao") && LoginTime.containsKey(player.getUniqueId())) {
            long unixtime = LoginTime.get(player.getUniqueId());
            if (unixtime + 60 < System.currentTimeMillis() / 1000) {
                return;
            }
            if (HaiTask.containsKey(player.getUniqueId()) && !HaiTask.get(player.getUniqueId()).isCancelled()) {
                HaiTask.get(player.getUniqueId()).cancel();
            }
            BukkitTask task = new Task_Hai(player).runTaskLaterAsynchronously(Main.getJavaPlugin(), 1200L);
            HaiTask.put(player.getUniqueId(), task);
        } else if (message.equals("afa") && HaiTask.containsKey(player.getUniqueId()) && !HaiTask.get(player.getUniqueId()).isCancelled()) {
            HaiTask.get(player.getUniqueId()).cancel();
        }
    }
}
