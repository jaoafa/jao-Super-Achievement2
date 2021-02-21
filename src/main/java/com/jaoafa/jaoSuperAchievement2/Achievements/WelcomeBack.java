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
 * No. 52
 * <p>
 * おかえりなさいませ
 * 「rejao afa」と発言する
 * 「rejao」と発言したあとに「afa」と発言した場合・または、「rj」と発言した場合
 * ※隠し要素
 *
 * @category jao Achievement
 * @since 2020/08/20
 */
public class WelcomeBack implements Listener {
    Map<UUID, String> OldMessage = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeakrejao_afa(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (message.equalsIgnoreCase("rj")) {
            if (!Achievementjao.getAchievement(player, new AchievementType(52))) {
                player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
            }
            return;
        }
        if (!message.equalsIgnoreCase("afa"))
            OldMessage.put(player.getUniqueId(), message);
        if (!message.equalsIgnoreCase("afa")) {
            return;
        }
        if (!OldMessage.containsKey(player.getUniqueId())) {
            return;
        }
        if (!OldMessage.get(player.getUniqueId()).equalsIgnoreCase("rejao")) {
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(52))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
            return;
        }
    }
}
