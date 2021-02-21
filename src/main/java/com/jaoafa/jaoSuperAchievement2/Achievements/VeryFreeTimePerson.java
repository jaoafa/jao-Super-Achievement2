package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
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

/**
 * No. 15
 * <p>
 * とてもお暇な方
 * もっと他にやることないの?
 * 石炭ブロックを石炭にしたあと石炭で石炭ブロックを作る
 * ※隠し要素
 *
 * @category jao Achievement
 * @since 2020/04/05
 */
public class VeryFreeTimePerson implements Listener {
    Set<UUID> Craft = new HashSet<>(); // 既に石炭ブロック→石炭してるか

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnCraft(CraftItemEvent event) {
        if (event.getWhoClicked().getType() != EntityType.PLAYER) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ItemStack is = event.getRecipe().getResult();

        if (is.getType() == Material.COAL) {
            // 石炭ブロック→石炭
            Craft.add(player.getUniqueId());
        } else if (is.getType() == Material.COAL_BLOCK) {
            // 石炭→石炭ブロック
            if (!Craft.contains(player.getUniqueId())) {
                return;
            }
            if (!Achievementjao.getAchievement(player, new AchievementType(15))) {
                player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                return;
            }
        }
    }
}