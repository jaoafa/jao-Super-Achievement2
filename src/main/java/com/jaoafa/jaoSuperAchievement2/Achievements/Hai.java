package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import com.jaoafa.jaoSuperAchievement2.Main;
import com.jaoafa.jaoSuperAchievement2.Task.Task_Hai;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * No. 75
 *
 * はい。
 * ログインしてから1分以内に「jao」とだけ言い、「afa」を言わない。
 * ログインしてから1分以内にjaoと言いafaを言わなかった場合。
 *
 * @since 2020/10/11
 * @category jao Achievement
 *
 */
public class Hai implements Listener {
    static Map<UUID, Long> LoginTime = new HashMap<>();
    static Map<UUID, BukkitTask> HaiTask = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        LoginTime.put(player.getUniqueId(), System.currentTimeMillis() / 1000);
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        LoginTime.remove(player.getUniqueId());
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeak(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (message.equalsIgnoreCase("jao") && LoginTime.containsKey(player.getUniqueId())) {
            long unixtime = LoginTime.get(player.getUniqueId());
            if(unixtime + 60 < System.currentTimeMillis() / 1000){
                return;
            }
            if(HaiTask.containsKey(player.getUniqueId()) && !HaiTask.get(player.getUniqueId()).isCancelled()){
                HaiTask.get(player.getUniqueId()).cancel();
            }
            BukkitTask task = new Task_Hai(player).runTaskLaterAsynchronously(Main.getJavaPlugin(), 1200L);
            HaiTask.put(player.getUniqueId(), task);
        }else if (message.equalsIgnoreCase("afa") && HaiTask.containsKey(player.getUniqueId()) && !HaiTask.get(player.getUniqueId()).isCancelled()) {
            HaiTask.get(player.getUniqueId()).cancel();
        }
    }
}
