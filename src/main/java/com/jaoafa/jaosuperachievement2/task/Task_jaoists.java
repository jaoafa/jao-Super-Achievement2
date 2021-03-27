package com.jaoafa.jaosuperachievement2.task;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Task_jaoists extends BukkitRunnable {
    Map<Achievement, Integer> achievements = new HashMap<>();

    {
        achievements.put(Achievement.NORMALJAOIST, 3600);
        achievements.put(Achievement.SINCERITYJAOIST, 86400);
        achievements.put(Achievement.SUPERJAOIST, 604800);
        achievements.put(Achievement.CHARISMAJAOIST, 2592000);
        achievements.put(Achievement.FOREVERJAOEST, 8640000);
    }

    @Override
    public void run() {
        for(Player player: Bukkit.getOnlinePlayers()){
            for(Map.Entry<Achievement, Integer> entry: achievements.entrySet()){
                Achievement achievement = entry.getKey();
                int onlineGameTick = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
                int borderGameTick = entry.getValue();

                if(onlineGameTick < borderGameTick){
                    return; // オンライン時間が、ボーダー時間内だったらリターン
                }

                Achievementjao.getAchievementAsync(player, achievement);
            }
        }
    }
}
