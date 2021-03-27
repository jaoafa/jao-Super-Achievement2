package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * No. 12
 * <p>
 * いちばんのり！
 * 鯖がリスタートしてから一番最初にログインする
 * 3時の再起動後、または鯖を再起動して一番最初にログインする
 *
 * @category jao Achievement
 * @since 2020/04/05
 */
public class FirstServerLogin implements Listener {
    boolean first = false;

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnFirstServerLogin(PlayerJoinEvent event) {
        if (first) {
            return;
        }

        Player player = event.getPlayer();
        first = true;
        // どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
        if (!Achievementjao.getAchievement(player, new AchievementType(12))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}