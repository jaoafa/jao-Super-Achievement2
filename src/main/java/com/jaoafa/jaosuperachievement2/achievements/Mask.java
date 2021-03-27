package com.jaoafa.jaosuperachievement2.Achievements;

import com.jaoafa.jaosuperachievement2.API.AchievementAPI;
import com.jaoafa.jaosuperachievement2.API.Achievementjao;
import com.jaoafa.jaosuperachievement2.Lib.Achievement;
import com.jaoafa.jaosuperachievement2.Lib.AchievementInterface;
import com.jaoafa.jaosuperachievement2.Lib.AchievementType;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Mask implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.MASK;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnMASK(InventoryCloseEvent event) { // インベントリを閉じたとき
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

        if (!offplayer.getUniqueId().toString().equalsIgnoreCase("26728d53-add7-46d1-97c3-0a25bc6607f5")) {
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
