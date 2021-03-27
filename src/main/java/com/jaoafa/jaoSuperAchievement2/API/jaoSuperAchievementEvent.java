package com.jaoafa.jaoSuperAchievement2.API;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

public class jaoSuperAchievementEvent extends Event {
	private OfflinePlayer offplayer;
	private AchievementType type;
	private static final HandlerList handlers = new HandlerList();

	public jaoSuperAchievementEvent(OfflinePlayer player, AchievementType type) {
		this.offplayer = player;
		this.type = type;
	}

	public OfflinePlayer getPlayer() {
		return offplayer;
	}

	public AchievementType getType() {
		return type;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}