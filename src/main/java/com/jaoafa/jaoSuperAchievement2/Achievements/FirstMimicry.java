package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

/**
 * No. 14
 * <p>
 * はじめてのものまね
 * 直前に送られたメッセージと同じメッセージを送る
 * 直前に送った別プレイヤーのメッセージと同じものを送信
 *
 * @category jao Achievement
 * @since 2020/04/05
 */
public class FirstMimicry implements Listener {
    String OLDMessage = null;
    UUID OLDPlayerUUID = null;

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeakjao_afa(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();

        if (!message.equals(OLDMessage)) {
            OLDMessage = message;
            OLDPlayerUUID = player.getUniqueId();
            return; // メッセージが同じじゃなかったらはじく
        }
        if (OLDPlayerUUID.equals(player.getUniqueId())) {
            OLDMessage = message;
            OLDPlayerUUID = player.getUniqueId();
            return; // プレイヤーが同じだったらはじく
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(14))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
            return;
        }

        OLDMessage = message;
        OLDPlayerUUID = player.getUniqueId();
    }
}