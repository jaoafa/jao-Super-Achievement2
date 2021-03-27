package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

/**
 * No. 37
 * <p>
 * わんぱく少年
 * 両手に雪玉をもつ
 * オフハンドとメインハンドに雪玉を持つ
 *
 * @category jao Achievement
 * @since 2020/04/24
 */
public class NaughtyBoy implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnNaughtyBoy(InventoryCloseEvent event) { // インベントリを閉じたとき
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getPlayer();

        ItemStack main = player.getInventory().getItemInMainHand();
        ItemStack off = player.getInventory().getItemInOffHand();
        if (main.getType() != Material.SNOW_BALL || off.getType() != Material.SNOW_BALL) {
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(37))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}
