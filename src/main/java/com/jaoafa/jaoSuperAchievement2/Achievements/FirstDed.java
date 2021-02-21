package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * No. 25
 * <p>
 * ded
 * 「jaojao」を発言せずに退出する
 * 「jaojao」を発言せずに退出する
 *
 * @category jao Achievement
 * @since 2020/04/06
 */
public class FirstDed implements Listener {
    List<UUID> JaoJao = new ArrayList<UUID>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeakjaojao(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (!message.equalsIgnoreCase("jaojao")) {
            return;
        }

        if (!JaoJao.contains(player.getUniqueId())) {
            JaoJao.add(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnNotSpeakjaojaoLeft(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (JaoJao.contains(player.getUniqueId())) {
            JaoJao.remove(player.getUniqueId());
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(25))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
            return;
        }
    }
}