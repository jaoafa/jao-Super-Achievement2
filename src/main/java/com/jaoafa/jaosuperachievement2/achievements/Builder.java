package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Builder implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.BUILDER;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        int count = 0;
        for (Material material : Material.values()) {
            try {
                count += player.getStatistic(Statistic.USE_ITEM, material);
            } catch (Exception ignored) {
            }
        }

        count++; // イベント発生時には今回の分が入っていないので

        if (count < 256) {
            return; // 256以下
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
