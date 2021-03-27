package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

/**
 * No. 6
 * <p>
 * あのお方
 * jaotanの名を言う
 * 「jaotan」を含むメッセージを送信したとき
 *
 * @category jao Achievement
 * @since 2020/04/05
 */
@SuppressWarnings("deprecation")
public class Speakjaotan implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeakjaotan(PlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (!message.contains("jaotan")) {
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(6))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}