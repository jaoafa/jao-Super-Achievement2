package com.jaoafa.jaoSuperAchievement2;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement2.API.AchievementAPI;
import com.jaoafa.jaoSuperAchievement2.Command.Cmd_JSA;
import com.jaoafa.jaoSuperAchievement2.Event.Event_JSA;
import com.jaoafa.jaoSuperAchievement2.Lib.ClassFinder;
import com.jaoafa.jaoSuperAchievement2.Lib.Discord;

public class Main extends JavaPlugin {
	static FileConfiguration conf;
	static JavaPlugin JavaPlugin;
	static MySQLDBManager sqlmanager;
	static Discord discord = null;

	/**
	 * プラグインが起動したときに呼び出し
	 * @author mine_book000
	 * @since 2020/04/05
	 */
	@Override
	public void onEnable() {
		getCommand("jsa").setExecutor(new Cmd_JSA(this));
		getServer().getPluginManager().registerEvents(new Event_JSA(this), this);

		JavaPlugin = this;

		LoadjaoAchievements();
		Load_Config();
		//LoadTask();
	}

	private void LoadjaoAchievements() {
		try {
			ClassFinder classFinder = new ClassFinder(this.getClassLoader());
			for (Class<?> clazz : classFinder.findClasses("com.jaoafa.jaoSuperAchievement2.Achievements")) {
				if (!clazz.getName().startsWith("com.jaoafa.jaoSuperAchievement2.Achievements.")) {
					continue;
				}
				if (clazz.getEnclosingClass() != null) {
					continue;
				}
				if (clazz.getName().contains("$")) {
					continue;
				}

				Constructor<?> construct = (Constructor<?>) clazz.getConstructor();
				Object instance = construct.newInstance();

				if (instance instanceof Listener) {
					try {
						Listener listener = (Listener) instance;
						getServer().getPluginManager().registerEvents(listener, this);
						getLogger().info(clazz.getSimpleName() + " registered");
					} catch (ClassCastException e) {
						// commandexecutor not implemented
						getLogger().warning(clazz.getSimpleName() + ": Listener not implemented [1]");
						continue;
					}
				} else {
					getLogger().warning(clazz.getSimpleName() + ": Listener not implemented [2]");
					continue;
				}
			}
		} catch (Exception e) { // ClassFinder.findClassesがそもそもException出すので仕方ないという判断で。
			e.printStackTrace();
			return;
		}
	}

	private void Load_Config() {
		conf = getConfig();
		if (!conf.contains("sqlserver")) {
			getLogger().warning("sqlserverが定義されていません。プラグインを無効化します。");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		if (!conf.contains("sqlport")) {
			getLogger().warning("sqlportが定義されていません。プラグインを無効化します。");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		if (!conf.contains("sqldatabase")) {
			getLogger().warning("sqldatabaseが定義されていません。プラグインを無効化します。");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		if (!conf.contains("sqlusername")) {
			getLogger().warning("sqlusernameが定義されていません。プラグインを無効化します。");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		if (!conf.contains("sqlpassword")) {
			getLogger().warning("sqlpasswordが定義されていません。プラグインを無効化します。");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		try {
			sqlmanager = new MySQLDBManager(
					conf.getString("sqlserver"),
					conf.getString("sqlport"),
					conf.getString("sqldatabase"),
					conf.getString("sqlusername"),
					conf.getString("sqlpassword"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			getLogger().warning("データベースの接続に失敗しました。プラグインを無効化します。");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		if (!conf.contains("discordtoken")) {
			getLogger().warning("discordtokenが定義されていません。Discordとの連携機能を無効化します。");
		} else {
			discord = new Discord(conf.getString("discordtoken"));
		}
	}

	public static void SendMessage(CommandSender sender, Command cmd, String text) {
		sender.sendMessage(AchievementAPI.getPrefix() + ChatColor.YELLOW + text);
	}

	public static JavaPlugin getJavaPlugin() {
		return JavaPlugin;
	}

	public static Discord getDiscord() {
		return discord;
	}

	public static MySQLDBManager getMySQLDBManager() {
		return sqlmanager;
	}

	public static String sdfFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}
}
