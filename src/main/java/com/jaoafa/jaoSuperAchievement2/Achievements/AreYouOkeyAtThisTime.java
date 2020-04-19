package com.jaoafa.jaoSuperAchievement2.Achievements;

import java.util.Calendar;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 27
 *
 * こんな時間に大丈夫！？
 * 00:00 ～ 03:00にログインする
 * 00:00 ～ 03:00にログインする
 * ※隠し要素
 *
 * @since 2020/04/09
 * @category jao Achievement
 *
 */
public class AreYouOkeyAtThisTime implements Listener {
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnNightLogin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		Calendar cal = Calendar.getInstance();
		Calendar cal_00 = Calendar.getInstance(); // 00:00
		cal_00.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
		Calendar cal_03 = Calendar.getInstance(); // 03:00
		cal_03.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 3, 0, 0);
		if (cal.after(cal_00) && cal.before(cal_03)) {
			// どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
			if (!Achievementjao.getAchievement(player, new AchievementType(27))) {
				player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
				return;
			}
		}
	}
}
