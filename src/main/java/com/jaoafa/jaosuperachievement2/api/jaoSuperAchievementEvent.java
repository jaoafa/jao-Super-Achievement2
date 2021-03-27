package com.jaoafa.jaosuperachievement2.api;

import com.jaoafa.jaosuperachievement2.lib.Achievement;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class jaoSuperAchievementEvent extends Event {
	private final OfflinePlayer offplayer;
	private final Achievement achievement;
	private static final HandlerList handlers = new HandlerList();

	public jaoSuperAchievementEvent(OfflinePlayer player, Achievement achievement) {
		this.offplayer = player;
		this.achievement = achievement;
	}

	public OfflinePlayer getPlayer() {
		return offplayer;
	}

	public Achievement getAchievement() {
		return achievement;
	}

	@Override
	public @NotNull HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}