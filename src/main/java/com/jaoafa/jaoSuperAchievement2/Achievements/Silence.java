package com.jaoafa.jaoSuperAchievement2.Achievements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

import com.jaoafa.jaoSuperAchievement2.Main;
import com.jaoafa.jaoSuperAchievement2.Task.Task_Silence;

/**
 * No. 41
 *
 * 無言の極み
 * サーバに参加してから1分間何も喋らない
 * サーバに参加してから1分間 何もチャットを送信しない
 * ※隠し要素
 *
 * @since 2020/05/21
 * @category jao Achievement
 *
 */
public class Silence implements Listener {
	static List<UUID> Speaked = new ArrayList<UUID>();
	static Map<UUID, BukkitTask> SilenceTask = new HashMap<>();

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeak(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		if (!Speaked.contains(player.getUniqueId())) {
			Speaked.add(player.getUniqueId());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onJoinTask(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (Speaked.contains(player.getUniqueId())) {
			Speaked.remove(player.getUniqueId());
		}
		if (SilenceTask.containsKey(player.getUniqueId()) && !SilenceTask.get(player.getUniqueId()).isCancelled()) {
			SilenceTask.get(player.getUniqueId()).cancel();
			SilenceTask.remove(player.getUniqueId());
		}

		BukkitTask task = new Task_Silence(player).runTaskLater(Main.getJavaPlugin(), 1200L); // 1分
		SilenceTask.put(player.getUniqueId(), task);
	}

	public static List<UUID> getSpeaked() {
		return Speaked;
	}
}