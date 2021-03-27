package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Calendar;

public class AreYouOkeyAtThisTime implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.AREYOUOKEYATTHISTIME;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Calendar cal = Calendar.getInstance();
        Calendar cal_00 = Calendar.getInstance(); // 00:00
        cal_00.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        Calendar cal_03 = Calendar.getInstance(); // 03:00
        cal_03.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 3, 0, 0);
        if (cal.after(cal_00) && cal.before(cal_03)) {
            Achievementjao.getAchievementAsync(player, getAchievement());
        }
    }
}
