package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * No. 9
 * <p>
 * もりのくまさん
 * くまのアタマをかぶる
 * mine_book000の頭をかぶる
 *
 * @category jao Achievement
 * @since 2020/04/05
 */
public class ForestBear implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnForestBear(InventoryCloseEvent event) { // インベントリを閉じたとき
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getPlayer();

        ItemStack helmet = player.getInventory().getHelmet();
        if (helmet == null)
            return;
        if (helmet.getType() != Material.SKULL_ITEM) {
            return;
        }

        SkullMeta skull = (SkullMeta) helmet.getItemMeta();
        OfflinePlayer offplayer = skull.getOwningPlayer();

        if (offplayer == null) {
            return;
        }

        if (!offplayer.getUniqueId().toString().equalsIgnoreCase("32ff7cdc-a1b4-450a-aa7e-6af75fe8c37c")) {
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(9))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}
