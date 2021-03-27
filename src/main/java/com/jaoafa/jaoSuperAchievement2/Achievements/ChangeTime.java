package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * No. 16
 * <p>
 * 時間操作の神
 * Minecraftサーバ内の時間を変更する
 * "/time set <数値>"コマンドを用いて現在時刻とは違う時刻に変更する
 *
 * @category jao Achievement
 * @since 2020/04/05
 */
public class ChangeTime implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onChangeTimeCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();
        if (!player.hasPermission("minecraft.command.time")) {
            return;
        }
        if (!command.startsWith("/time set ")) {
            return;
        }
        if (!Achievementjao.getAchievement(player, new AchievementType(16))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
        }
    }
}
