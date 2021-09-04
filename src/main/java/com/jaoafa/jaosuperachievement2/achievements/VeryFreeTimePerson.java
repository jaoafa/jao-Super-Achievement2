package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VeryFreeTimePerson implements AchievementInterface, Listener {
    Set<UUID> Crafted = new HashSet<>(); // 既に石炭ブロック→石炭してるか

    @Override
    public Achievement getAchievement() {
        return Achievement.VERYFREETIMEPERSON;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnCraft(CraftItemEvent event) {
        if (event.getWhoClicked().getType() != EntityType.PLAYER) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if(player.hasMetadata("NPC")){
            return;
        }
        ItemStack is = event.getRecipe().getResult();

        if (is.getType() == Material.COAL) {
            // 石炭ブロック→石炭
            Crafted.add(player.getUniqueId());
        } else if (is.getType() == Material.COAL_BLOCK) {
            // 石炭→石炭ブロック
            if (!Crafted.contains(player.getUniqueId())) {
                return;
            }
            Achievementjao.getAchievementAsync(player, getAchievement());
        }
    }
}
