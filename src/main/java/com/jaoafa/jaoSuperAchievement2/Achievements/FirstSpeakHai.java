package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import com.jaoafa.jaoSuperAchievement2.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * No. 77
 *
 * 逆jao語
 * 誤字を誤字する。
 * "hai"と発言する
 *
 * @category jao Achievement
 * @since 2021/03/27
 */
public class FirstSpeakHai implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnSpeak(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (!message.equalsIgnoreCase("hai")) {
            return;
        }

        new BukkitRunnable() {
            public void run() {
                // どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
                if (!Achievementjao.getAchievement(player, new AchievementType(77))) {
                    player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
                }
            }
        }.runTaskAsynchronously(Main.getJavaPlugin());
    }
}
