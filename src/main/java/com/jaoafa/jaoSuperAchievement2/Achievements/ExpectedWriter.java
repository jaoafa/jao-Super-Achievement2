package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * No. 78
 *
 * 期待の作家
 * 本を書いて署名する
 * 本(本と羽ペン)を書いて署名する
 *
 * @category jao Achievement
 * @since 2021/03/27
 */
public class ExpectedWriter implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnEditBook(PlayerEditBookEvent event) {
        Player player = event.getPlayer();
        if(!event.isSigning()){
            return;
        }
        // どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
        if (!Achievementjao.getAchievement(player, new AchievementType(78))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}