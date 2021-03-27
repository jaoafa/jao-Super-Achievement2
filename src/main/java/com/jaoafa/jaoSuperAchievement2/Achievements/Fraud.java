package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.Main;
import com.jaoafa.jaoSuperAchievement2.Task.Task_Fraud;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * No. 34
 * <p>
 * 詐欺
 * jaojaoもしくはj2と言って1分経つ
 * jaojaoもしくはj2と言って1分経った場合
 *
 * @category jao Achievement
 * @since 2020/04/24
 */
public class Fraud implements Listener {
    static Map<UUID, BukkitTask> JaoJaoTask = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeakjaojao(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (!message.equalsIgnoreCase("jaojao") && !message.equalsIgnoreCase("j2")) {
            return;
        }

        BukkitTask task = new Task_Fraud(player).runTaskLater(Main.getJavaPlugin(), 1200L); // 60s * 20tick
        JaoJaoTask.put(player.getUniqueId(), task);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeakjaojaoLeft(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (!JaoJaoTask.containsKey(player.getUniqueId())) {
            return;
        }

        if (!JaoJaoTask.get(player.getUniqueId()).isCancelled()) {
            JaoJaoTask.get(player.getUniqueId()).cancel();
        }
        JaoJaoTask.remove(player.getUniqueId());
    }
}