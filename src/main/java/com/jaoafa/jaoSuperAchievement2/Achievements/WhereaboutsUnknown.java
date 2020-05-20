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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

import com.jaoafa.jaoSuperAchievement2.Main;
import com.jaoafa.jaoSuperAchievement2.Task.Task_WhereaboutsUnknown;

/**
 * No. 42
 *
 * 消息不明
 * サーバに参加してから5分間放置
 * サーバに参加してから5分間全く動かず何も呟かない
 * ※隠し要素
 *
 * @since 2020/05/21
 * @category jao Achievement
 *
 */
public class WhereaboutsUnknown implements Listener {
	static List<UUID> Actioned = new ArrayList<UUID>();
	static Map<UUID, BukkitTask> WhereaboutsUnknownTask = new HashMap<>();

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeak(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		if (!Actioned.contains(player.getUniqueId())) {
			Actioned.add(player.getUniqueId());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if (!Actioned.contains(player.getUniqueId())) {
			Actioned.add(player.getUniqueId());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onJoinTask(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (Actioned.contains(player.getUniqueId())) {
			Actioned.remove(player.getUniqueId());
		}
		if (WhereaboutsUnknownTask.containsKey(player.getUniqueId())
				&& !WhereaboutsUnknownTask.get(player.getUniqueId()).isCancelled()) {
			WhereaboutsUnknownTask.get(player.getUniqueId()).cancel();
			WhereaboutsUnknownTask.remove(player.getUniqueId());
		}

		BukkitTask task = new Task_WhereaboutsUnknown(player).runTaskLater(Main.getJavaPlugin(), 6000L); // 5分
		WhereaboutsUnknownTask.put(player.getUniqueId(), task);
	}

	public static List<UUID> getActioned() {
		return Actioned;
	}
}