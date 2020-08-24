package com.jaoafa.jaoSuperAchievement2.Task;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Task_jaoists extends BukkitRunnable {

    @Override
    public void run() {
        for(Player player: Bukkit.getOnlinePlayers()){
            Normal(player);
            Sincerity(player);
            Super(player);
            Charisma(player);
            Forever(player);
        }
    }

    /**
     * No. 62
     *
     * ふつうのjaoist
     * サーバのログイン時間が1時間を超える
     * サーバのログイン時間が1時間を超える
     *
     * @since 2020/08/24
     * @category jao Achievement
     *
     */
    void Normal(Player player){
        int onlineGameTick = player.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
        int borderGameTick = 3600;

        if(onlineGameTick < borderGameTick){
            return; // オンライン時間が、ボーダー時間内だったらリターン
        }

        if(!Achievementjao.getAchievement(player, new AchievementType(62))){
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
    /**
     * No. 63
     *
     * まことのjaoist
     * サーバのログイン時間が1日を超える
     * サーバのログイン時間が1日を超える
     *
     * @since 2020/08/24
     * @category jao Achievement
     *
     */
    void Sincerity(Player player){
        int onlineGameTick = player.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
        int borderGameTick = 86400;

        if(onlineGameTick < borderGameTick){
            return; // オンライン時間が、ボーダー時間内だったらリターン
        }

        if(!Achievementjao.getAchievement(player, new AchievementType(63))){
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
    /**
     * No. 64
     *
     * スーパーjaoist
     * サーバのログイン時間が1週間を超える
     * サーバのログイン時間が1週間を超える
     *
     * @since 2020/08/24
     * @category jao Achievement
     *
     */
    void Super(Player player){
        int onlineGameTick = player.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
        int borderGameTick = 604800;

        if(onlineGameTick < borderGameTick){
            return; // オンライン時間が、ボーダー時間内だったらリターン
        }

        if(!Achievementjao.getAchievement(player, new AchievementType(64))){
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
    /**
     * No. 65
     *
     * カリスマjaoist
     * サーバのログイン時間が1ヶ月を超える
     * サーバのログイン時間が1ヶ月を超える
     *
     * @since 2020/08/24
     * @category jao Achievement
     *
     */
    void Charisma(Player player){
        int onlineGameTick = player.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
        int borderGameTick = 604800;

        if(onlineGameTick < borderGameTick){
            return; // オンライン時間が、ボーダー時間内だったらリターン
        }

        if(!Achievementjao.getAchievement(player, new AchievementType(65))){
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
    /**
     * No. 66
     *
     * えいえんのjaoist
     * サーバのログイン時間が100日を超える
     * サーバのログイン時間が100日を超える
     *
     * @since 2020/08/24
     * @category jao Achievement
     *
     */
    void Forever(Player player){
        int onlineGameTick = player.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
        int borderGameTick = 8640000;

        if(onlineGameTick < borderGameTick){
            return; // オンライン時間が、ボーダー時間内だったらリターン
        }

        if(!Achievementjao.getAchievement(player, new AchievementType(66))){
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}
