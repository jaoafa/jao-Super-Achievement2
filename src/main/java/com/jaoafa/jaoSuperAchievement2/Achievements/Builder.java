package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * No. 71
 *
 * 建築家
 * 建築をする
 * ブロックを256個以上設置する
 *
 * @since 2020/10/10
 * @category jao Achievement
 *
 */
public class Builder implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();

        int count = 0;
        for(Material material : Material.values()){
            try{
                count += player.getStatistic(Statistic.USE_ITEM, material);
            }catch(Exception ignored){
            }
        }

        count++; // イベント発生時には今回の分が入っていないので

        if(count < 256){
            return; // 256以下
        }

        if(!Achievementjao.getAchievement(player, new AchievementType(71))){
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}
