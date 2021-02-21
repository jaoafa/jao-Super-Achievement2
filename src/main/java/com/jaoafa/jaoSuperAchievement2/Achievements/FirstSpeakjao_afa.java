package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * No. 3
 * <p>
 * はじめてのjao afa
 * 「jao afa」と発言する
 * 「jao」と発言したあとに「afa」と発言した場合
 *
 * @category jao Achievement
 * @since 2020/04/05
 */
public class FirstSpeakjao_afa implements Listener {
    Map<UUID, String> OldMessage = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeakjao_afa(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (!message.equals("afa"))
            OldMessage.put(player.getUniqueId(), message);
        if (!message.equals("afa")) {
            return;
        }
        if (!OldMessage.containsKey(player.getUniqueId())) {
            return;
        }
        if (!OldMessage.get(player.getUniqueId()).equals("jao")) {
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(3))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}
