package com.jaoafa.jaosuperachievement2.command;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.api.AchievementAPI;
import com.jaoafa.jaosuperachievement2.task.Task_OpenPage;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Cmd_JSA implements CommandExecutor {
	JavaPlugin plugin;

	public Cmd_JSA(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Component.text().append(
                AchievementAPI.getPrefix(),
                Component.text("このコマンドはプレイヤーからのみ実行できます。")
            ));
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 1) {
            OfflinePlayer offplayer = Bukkit.getOfflinePlayer(args[0]);
            new Task_OpenPage(player, offplayer, 1).runTaskAsynchronously(Main.getJavaPlugin());
        } else {
            new Task_OpenPage(player, player, 1).runTaskAsynchronously(Main.getJavaPlugin());
        }
        return true;
    }
}