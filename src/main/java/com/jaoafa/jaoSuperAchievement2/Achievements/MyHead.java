package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * No. 53
 *
 * ぼくのあたま！
 * 自分の頭を召喚する。
 * 「/head」とコマンドを入力した場合
 * ※隠し要素
 *
 * @since 2020/08/20
 * @category jao Achievement
 *
 */
public class MyHead implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onHeadCommand(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(!message.equalsIgnoreCase("/head")){
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(53))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
            return;
        }
    }
}
