package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import com.jaoafa.jaoSuperAchievement2.Main;
import com.jaoafa.jaoSuperAchievement2.Task.Task_Hai;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

/**
 * No. 81
 *
 * すべてのはじまり
 * 誤字からスタート
 * ログインしてからの第一声が「hai」だった場合
 * ※隠し要素
 *
 * @category jao Achievement
 * @since 2021/03/27
 */
public class EverythingBegin implements Listener {
    static Set<UUID> isSpoken = new HashSet<>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        isSpoken.add(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeak(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if(!isSpoken.contains(player.getUniqueId())){
            return;
        }
        isSpoken.remove(player.getUniqueId());
        if (!message.equals("hai")){
            return;
        }
        if (!Achievementjao.getAchievement(player, new AchievementType(81))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}
