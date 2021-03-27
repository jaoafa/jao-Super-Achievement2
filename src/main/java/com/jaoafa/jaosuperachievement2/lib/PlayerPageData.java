package com.jaoafa.jaosuperachievement2.lib;

import org.bukkit.OfflinePlayer;

public class PlayerPageData {
	private final OfflinePlayer offplayer;
	private final int page;

	public PlayerPageData(OfflinePlayer offplayer, int page) {
		this.offplayer = offplayer;
		this.page = page;
	}

	public OfflinePlayer getViewPlayer() {
		return offplayer;
	}

	public int getPage() {
		return page;
	}
}
