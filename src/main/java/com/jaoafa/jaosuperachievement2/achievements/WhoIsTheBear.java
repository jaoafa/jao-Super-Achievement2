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

public class WhoIsTheBear implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.WHOISTHEBEAR;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getPlayer();

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

        if (!offplayer.getUniqueId().toString().equals("32ff7cdc-a1b4-450a-aa7e-6af75fe8c37c")) {
            return;
        }

        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate == null || chestplate.getType() != Material.LEATHER_CHESTPLATE) {
            return;
        }

        ItemStack leggings = player.getInventory().getLeggings();
        if (leggings == null || leggings.getType() != Material.LEATHER_LEGGINGS) {
            return;
        }

        ItemStack boots = player.getInventory().getBoots();
        if (boots == null || boots.getType() != Material.LEATHER_BOOTS) {
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
