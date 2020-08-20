package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * No. 51
 *
 * 大草原不可避ww
 * チャット欄に草を生やす
 * 「草」と発言した場合
 *
 * @since 2020/08/20
 * @category jao Achievement
 *
 */
public class Grass implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeak(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();

        if (!message.equalsIgnoreCase("草")) {
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(51))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
            return;
        }
    }
}
