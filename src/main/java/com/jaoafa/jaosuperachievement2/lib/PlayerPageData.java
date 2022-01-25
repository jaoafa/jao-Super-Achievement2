package com.jaoafa.jaosuperachievement2.lib;

import org.bukkit.OfflinePlayer;

public record PlayerPageData(OfflinePlayer offplayer, int page) {
    public OfflinePlayer getViewPlayer() {
        return offplayer;
    }

    public int getPage() {
        return page;
    }
}
