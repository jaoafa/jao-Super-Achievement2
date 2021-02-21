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
 * No. 68
 * <p>
 * スパマーじゃん
 * 同じことを二回連続で発言する
 * 同じことを二連続で発言した場合
 *
 * @category jao Achievement
 * @since 2020/08/24
 */
public class Spammer implements Listener {
    Map<UUID, String> map = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeak(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();

        if (map.containsKey(player.getUniqueId())) {
            String oldMessage = map.get(player.getUniqueId());
            if (oldMessage.equals(message)) {
                if (!Achievementjao.getAchievement(player, new AchievementType(68))) {
                    player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                }
            }
        }

        map.put(player.getUniqueId(), message);
    }
}
