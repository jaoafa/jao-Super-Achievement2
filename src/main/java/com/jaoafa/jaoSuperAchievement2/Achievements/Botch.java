package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * No. 11
 * <p>
 * BOTCH
 * 1人になる
 * 誰もいない時にログインするor誰かがログアウトして1人になる
 * ※隠し要素
 *
 * @category jao Achievement
 * @since 2020/04/05
 */
public class Botch implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Bukkit.getServer().getOnlinePlayers().size() != 1) {
            return;
        }
        if (!Achievementjao.getAchievement(player, new AchievementType(11))) {
            player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
            return;
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        int i = Bukkit.getServer().getOnlinePlayers().size() - 1;
        Player botch_player = null;
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            Player left_player = event.getPlayer();
            if (!p.getUniqueId().equals(left_player.getUniqueId())) {
                botch_player = p;
            }
        }

        if (botch_player == null) {
            return;
        }

        if (i != 1) {
            return;
        }
        if (!Achievementjao.getAchievement(botch_player, new AchievementType(11))) {
            botch_player.sendMessage(AchievementAPI.getPrefix()
                    + "実績の解除中に問題が発生しました。もう一度お試しください。");
            return;
        }
    }
}