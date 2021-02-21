package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * No. 45
 * <p>
 * 一般通過
 * ログインして10秒以内にサーバーを退出する
 * ログインして10秒以内にサーバーを退出する
 *
 * @category jao Achievement
 * @since 2020/05/21
 */
public class GeneralPassing implements Listener {
    Map<UUID, Long> LoginTime = new HashMap<UUID, Long>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LoginTime.put(player.getUniqueId(), System.currentTimeMillis() / 1000L);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (!LoginTime.containsKey(player.getUniqueId())) {
            return;
        }

        if (((System.currentTimeMillis() / 1000L) - LoginTime.get(player.getUniqueId())) > 10) {
            LoginTime.remove(player.getUniqueId());
            return;
        }

        if (!Achievementjao.getAchievement(player, new AchievementType(45))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
            return;
        }
        LoginTime.remove(player.getUniqueId());
    }
}
