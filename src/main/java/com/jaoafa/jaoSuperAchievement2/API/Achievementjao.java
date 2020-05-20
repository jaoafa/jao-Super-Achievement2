package com.jaoafa.jaoSuperAchievement2.API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.jaoafa.jaoSuperAchievement2.Main;
import com.jaoafa.jaoSuperAchievement2.MySQLDBManager;
import com.jaoafa.jaoSuperAchievement2.Lib.AchievementType;

public class Achievementjao {
	static private FireworkEffect.Type[] types = {
			Type.BALL,
			Type.BALL_LARGE,
			Type.BURST,
			Type.CREEPER,
			Type.STAR,
	};
	static private Random rand = new Random();

	public static boolean getAchievement(Player player, AchievementType type) {
		if (isAlreadyGettedAchievement(player, type)) {
			return true; // すでに取得済みだけどエラーじゃないので
		}

		try {
			MySQLDBManager sqlmanager = Main.getMySQLDBManager();
			Connection conn = sqlmanager.getConnection();

			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO jaoSuperAchievement2 (player, uuid, achievementid) VALUES (?, ?, ?);");
			statement.setString(1, player.getName());
			statement.setString(2, player.getUniqueId().toString());
			statement.setInt(3, type.getID());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		int gettedPlayerCount = getGettedPlayerCount(type.getID());

		Bukkit.broadcastMessage(AchievementAPI.getPrefix() + player.getName() + "が「" + type.getTitle() + "」を取得しました！ ("
				+ gettedPlayerCount + "人目)");
		Main.getDiscord().sendMessage("597423199227084800",
				"**[jaoSuperAchievement2]** " + DiscordEscape(player.getName()) + "が「" + DiscordEscape(type.getTitle())
						+ "」を取得しました！ (" + gettedPlayerCount + "人目)");

		jaoSuperAchievementEvent jaoSuperAchievementEvent = new jaoSuperAchievementEvent(player, type);
		Bukkit.getServer().getPluginManager().callEvent(jaoSuperAchievementEvent);

		new BukkitRunnable() {
			public void run() {
				Firework firework = player.getLocation().getWorld().spawn(player.getLocation().add(0, 3, 0),
						Firework.class);
				FireworkMeta meta = firework.getFireworkMeta();
				Builder effect = FireworkEffect.builder();
				effect.with(types[rand.nextInt(types.length)]);
				effect.withColor(Color.RED, Color.AQUA, Color.YELLOW, Color.GREEN, Color.LIME, Color.ORANGE,
						Color.PURPLE);
				effect.withFade(Color.RED, Color.AQUA, Color.YELLOW, Color.GREEN, Color.LIME, Color.ORANGE,
						Color.PURPLE);
				effect.flicker(true);
				meta.setPower(1);
				meta.addEffect(effect.build());
				firework.setFireworkMeta(meta);
				firework.detonate();
			}
		}.runTaskLater(Main.getJavaPlugin(), 1);
		return true;
	}

	public static boolean getAchievement(OfflinePlayer offplayer, AchievementType type) {
		if (isAlreadyGettedAchievement(offplayer, type)) {
			return true; // すでに取得済みだけどエラーじゃないので
		}

		try {
			MySQLDBManager sqlmanager = Main.getMySQLDBManager();
			Connection conn = sqlmanager.getConnection();

			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO jaoSuperAchievement2 (player, uuid, achievementid) VALUES (?, ?, ?);");
			statement.setString(1, offplayer.getName());
			statement.setString(2, offplayer.getUniqueId().toString());
			statement.setInt(3, type.getID());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		int gettedPlayerCount = getGettedPlayerCount(type.getID());

		Bukkit.broadcastMessage(
				AchievementAPI.getPrefix() + offplayer.getName() + "が「" + type.getTitle() + "」を取得しました！ ("
						+ gettedPlayerCount + "人目)");
		Main.getDiscord().sendMessage("597423199227084800",
				"**[jaoSuperAchievement2]** " + DiscordEscape(offplayer.getName()) + "が「"
						+ DiscordEscape(type.getTitle()) + "」を取得しました！ (" + gettedPlayerCount + "人目)");

		jaoSuperAchievementEvent jaoSuperAchievementEvent = new jaoSuperAchievementEvent(offplayer, type);
		Bukkit.getServer().getPluginManager().callEvent(jaoSuperAchievementEvent);
		return true;
	}

	static Map<UUID, List<Integer>> GettedAchievementCache = new HashMap<>(); // uuid, AchievementID

	public static boolean isAlreadyGettedAchievement(OfflinePlayer player, AchievementType type) {
		if (GettedAchievementCache.containsKey(player.getUniqueId())) {
			List<Integer> gettedList = GettedAchievementCache.get(player.getUniqueId());
			if (gettedList.contains(type.getID())) {
				return true;
			}
		}
		try {
			MySQLDBManager sqlmanager = Main.getMySQLDBManager();
			Connection conn = sqlmanager.getConnection();

			PreparedStatement statement = conn.prepareStatement(
					"SELECT * FROM jaoSuperAchievement2 WHERE uuid = ? AND achievementid = ?");
			statement.setString(1, player.getUniqueId().toString());
			statement.setInt(2, type.getID());
			ResultSet res = statement.executeQuery();
			if (res.next()) {
				if (GettedAchievementCache.containsKey(player.getUniqueId())) {
					List<Integer> gettedList = GettedAchievementCache.get(player.getUniqueId());
					gettedList.add(type.getID());
					GettedAchievementCache.put(player.getUniqueId(), gettedList);
				} else {
					List<Integer> gettedList = new ArrayList<>();
					gettedList.add(type.getID());
					GettedAchievementCache.put(player.getUniqueId(), gettedList);
				}
				res.close();
				statement.close();
				return true;
			} else {
				res.close();
				statement.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	static int getGettedPlayerCount(int id) {
		try {
			MySQLDBManager sqlmanager = Main.getMySQLDBManager();
			Connection conn = sqlmanager.getConnection();

			PreparedStatement statement = conn
					.prepareStatement("SELECT COUNT(*) FROM jaoSuperAchievement2 WHERE achievementid = ?;");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if (res.next()) {
				int ret = res.getInt(1);
				res.close();
				statement.close();
				return ret;
			} else {
				res.close();
				statement.close();
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	static String DiscordEscape(String text) {
		return text == null ? "" : text.replace("_", "\\_").replace("*", "\\*").replace("~", "\\~");
	}
}
