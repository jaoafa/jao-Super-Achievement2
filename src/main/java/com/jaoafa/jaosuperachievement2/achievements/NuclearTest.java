package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Arrays;
import java.util.Objects;

public class NuclearTest implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.NUCLEARTEST;
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnGetTNT(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }

        if(Arrays.stream(player.getInventory().getContents()).filter(Objects::nonNull).noneMatch(item -> item.getType() == Material.TNT)){
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
