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

public class EarlyBirdCatchesWorm implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.EARLYBIRDCATCHESWORM;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }

        Calendar cal = Calendar.getInstance();
        Calendar cal_06 = Calendar.getInstance(); // 06:00
        cal_06.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 6, 0, 0);
        Calendar cal_09 = Calendar.getInstance(); // 09:00
        cal_09.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 9, 0, 0);
        if (cal.after(cal_06) && cal.before(cal_09)) {
            Achievementjao.getAchievementAsync(player, getAchievement());
        }
    }
}
