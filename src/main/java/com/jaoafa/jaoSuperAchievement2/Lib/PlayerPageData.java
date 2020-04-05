package com.jaoafa.jaoSuperAchievement2.Lib;

import org.bukkit.OfflinePlayer;

public class PlayerPageData {
	private OfflinePlayer offplayer;
	private int page;

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
