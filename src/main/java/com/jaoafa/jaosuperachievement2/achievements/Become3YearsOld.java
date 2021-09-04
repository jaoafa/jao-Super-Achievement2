package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Become3YearsOld implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.BECOME3YEARSOLD;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnBecome3YearsOld(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }

        ItemStack helmet = player.getInventory().getHelmet();
        if (helmet == null)
            return;
        if (helmet.getType() != Material.PLAYER_HEAD) {
            return;
        }
        SkullMeta skull = (SkullMeta) helmet.getItemMeta();
        OfflinePlayer offplayer = skull.getOwningPlayer();

        if (offplayer == null) {
            return;
        }

        if (!offplayer.getUniqueId().toString().equalsIgnoreCase("0ad34a33-3ca4-4c86-84f3-a4591920b06a")) {
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
