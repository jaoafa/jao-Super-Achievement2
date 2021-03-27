package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * No. 5
 * <p>
 * お前はおはようございますをございますというのか
 * jaoを言わずafaと言ったら怒られる
 * jaoを言わずafaと言った場合
 * ※隠し要素
 *
 * @category jao Achievement
 * @since 2020/04/05
 */
@SuppressWarnings("deprecation")
public class SpeakOnlyafa implements Listener {
    Set<UUID> Alreadyjao = new HashSet<>(); // 既にjaoと発言しているか(ログイン時にリセット)

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeakafa(PlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (message.equalsIgnoreCase("jao")) {
            Alreadyjao.add(player.getUniqueId());
            return;
        }
        if (!message.equalsIgnoreCase("afa")) {
            return;
        }
        if (Alreadyjao.contains(player.getUniqueId())) {
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(5))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!Alreadyjao.contains(player.getUniqueId())) {
            return;
        }
        Alreadyjao.remove(player.getUniqueId());
    }
}