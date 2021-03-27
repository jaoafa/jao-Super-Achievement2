package com.jaoafa.jaoSuperAchievement2.Achievements;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Calendar;

/**
 * No. 46
 * <p>
 * 早起きは3jaoぐらい得
 * 06:00~09:00にサーバにログインする。
 * 06:00~09:00にサーバにログインする。
 * ※隠し要素
 *
 * @category jao Achievement
 * @since 2020/06/28
 */
public class EarlyBirdCatchesWorm implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnMorning(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Calendar cal = Calendar.getInstance();
        Calendar cal_06 = Calendar.getInstance(); // 06:00
        cal_06.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 6, 0, 0);
        Calendar cal_09 = Calendar.getInstance(); // 09:00
        cal_09.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 9, 0, 0);
        if (cal.after(cal_06) && cal.before(cal_09)) {
            // どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
            if (!Achievementjao.getAchievement(player, new AchievementType(46))) {
                player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
            }
        }
    }
}
