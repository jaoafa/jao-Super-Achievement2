package com.jaoafa.jaosuperachievement2.task;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class Task_OpenInventory extends BukkitRunnable {
    final Player player;
    final Inventory inv;

    public Task_OpenInventory(Player player, Inventory inv) {
        this.player = player;
        this.inv = inv;
    }

    @Override
    public void run() {
        player.openInventory(inv);
    }
}
