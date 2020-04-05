package com.jaoafa.jaoSuperAchievement2.Achievements;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.API.Achievementjao;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

/**
 * No. 20
 *
 * MASK
 * kohonayoshiのあたまを被る
 * kohonayoshiのあたまを被る
 *
 * @since 2020/04/05
 * @category jao Achievement
 *
 */
public class MASK {
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnMASK(PlayerMoveEvent event) { // インベントリ系のイベントだといまいちなのであえて移動イベント
		if (event.getFrom().distance(event.getTo()) == 0)
			return;
		Player player = (Player) event.getPlayer();

		ItemStack helmet = player.getInventory().getHelmet();
		if (helmet == null)
			return;
		if (helmet.getType() != Material.SKULL_ITEM) {
			return;
		}
		SkullMeta skull = (SkullMeta) helmet.getItemMeta();
		OfflinePlayer offplayer = skull.getOwningPlayer();

		if (offplayer == null) {
			return;
		}

		if (!offplayer.getUniqueId().toString().equalsIgnoreCase("26728d53-add7-46d1-97c3-0a25bc6607f5")) {
			return;
		}

		if (!Achievementjao.getAchievement(player, new AchievementType(20))) {
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
