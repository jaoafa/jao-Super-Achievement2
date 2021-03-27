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
 * No. 80
 *
 * CapsLockついてますよ
 * 「JAO」か「AFA」を発言する
 * 「JAO」か「AFA」と発言した場合
 *
 * @category jao Achievement
 * @since 2021/03/27
 */
public class CapsLock implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeak(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();

        if (!message.equals("JAO") && !message.equals("AFA")) {
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(80))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}