package com.jaoafa.jaosuperachievement2.event;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.api.AchievementAPI;
import com.jaoafa.jaosuperachievement2.lib.PlayerPageData;
import com.jaoafa.jaosuperachievement2.task.Task_OpenPage;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Event_JSA implements Listener {
	JavaPlugin plugin;

	public Event_JSA(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public static Map<String, PlayerPageData> jSA = new HashMap<>();

	@EventHandler(ignoreCancelled = true)
	public void onPostClick(InventoryClickEvent event) {
		if (event.getWhoClicked().getType() != EntityType.PLAYER)
			return;
		if (event.getClickedInventory() == null)
			return;
		if (!PlainComponentSerializer.plain().serialize(event.getView().title()).endsWith("jaoSuperAchievement"))
			return;
		Player player = (Player) event.getWhoClicked();

		event.setCancelled(true);

		if (!jSA.containsKey(player.getName())) {
			player.sendMessage(AchievementAPI.getPrefix() + "現在開いているページを取得できませんでした。再度開きなおしてください。");
			return;
		}
		PlayerPageData ppd = jSA.get(player.getName());
		int page = ppd.getPage();
		OfflinePlayer offplayer = ppd.getViewPlayer();
		ItemStack is = event.getClickedInventory().getItem(event.getSlot());
		if (is == null || is.getType() == Material.AIR) {
			return;
		}

		if (is.getItemMeta().getDisplayName().equalsIgnoreCase("閉じる") || is.getType() == Material.BARRIER) {
			player.closeInventory();
			return;
		}

		if (event.getSlot() == 0) {
			// 前へ
			new Task_OpenPage(player, offplayer, page - 1).runTaskAsynchronously(Main.getJavaPlugin());
		} else if (event.getSlot() == 8) {
			// 次へ
			new Task_OpenPage(player, offplayer, page + 1).runTaskAsynchronously(Main.getJavaPlugin());
		}
	}
}