package com.jaoafa.jaosuperachievement2;

import com.jaoafa.jaosuperachievement2.command.Cmd_JSA;
import com.jaoafa.jaosuperachievement2.command.Cmd_jaoSuperAchievement2;
import com.jaoafa.jaosuperachievement2.event.Event_JSA;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import com.jaoafa.jaosuperachievement2.lib.ClassFinder;
import com.jaoafa.jaosuperachievement2.lib.Discord;
import com.jaoafa.jaosuperachievement2.task.Task_jaoists;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    static FileConfiguration conf;
    static JavaPlugin JavaPlugin;
    static MySQLDBManager sqlmanager;
    static Discord discord = null;

    public static JavaPlugin getJavaPlugin() {
        if (JavaPlugin == null) {
            Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("jao-Super-Achievement2");
            return (JavaPlugin) plugin;
        }
        return JavaPlugin;
    }

    public static Logger getMainLogger() {
        return JavaPlugin.getLogger();
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

    /**
     * プラグインが起動したときに呼び出し
     *
     * @author mine_book000
     * @since 2020/04/05
     */
    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("jsa")).setExecutor(new Cmd_JSA(this));
        Objects.requireNonNull(getCommand("jaosuperachievement2")).setExecutor(new Cmd_jaoSuperAchievement2());
        getServer().getPluginManager().registerEvents(new Event_JSA(this), this);

        JavaPlugin = this;

        LoadjaoAchievements();
        Load_Config();
        Load_Task();
    }

    private void LoadjaoAchievements() {
        try {
            ClassFinder classFinder = new ClassFinder(this.getClassLoader());
            for (Class<?> clazz : classFinder.findClasses("com.jaoafa.jaosuperachievement2.achievements")) {
                if (!clazz.getName().startsWith("com.jaoafa.jaosuperachievement2.achievements.")) {
                    continue;
                }
                if (clazz.getEnclosingClass() != null) {
                    continue;
                }
                if (clazz.getName().contains("$")) {
                    continue;
                }

                Constructor<?> construct = clazz.getConstructor();
                Object instance = construct.newInstance();

                if (!(instance instanceof Listener)) {
                    return;
                }
                if (!(instance instanceof AchievementInterface)) {
                    return;
                }
                try {
                    Listener listener = (Listener) instance;
                    getServer().getPluginManager().registerEvents(listener, this);
                    getLogger().info(clazz.getSimpleName() + " registered");
                } catch (ClassCastException e) {
                    // commandexecutor not implemented
                    getLogger().warning(clazz.getSimpleName() + ": Listener not implemented [1]");
                }
            }
        } catch (Exception e) { // ClassFinder.findClassesがそもそもException出すので仕方ないという判断で。
            e.printStackTrace();
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

    private void Load_Task() {
        new Task_jaoists().runTaskTimerAsynchronously(this, 0L, 1200L);
    }
}
