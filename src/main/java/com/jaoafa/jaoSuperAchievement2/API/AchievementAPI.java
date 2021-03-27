package com.jaoafa.jaoSuperAchievement2.API;

import org.bukkit.ChatColor;

public class AchievementAPI {
	/**
	 * 「[jaoSuperAchievement] 」というプレフィックスを返します。
	 * @return プレフィックス
	 */
	public static String getPrefix() {
		return "[" + getjaoSuperAchievement() + "] ";
	}

	/**
	 * 「jaoSuperAchievement」というプレフィックスを返します。
	 * @return プレフィックス
	 */
	public static String getjaoSuperAchievement() {
		return ChatColor.RED + "j" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "o" + ChatColor.GREEN + "S"
				+ ChatColor.AQUA + "u" + ChatColor.BLUE + "p" + ChatColor.DARK_BLUE + "e" + ChatColor.RED + "r"
				+ ChatColor.GOLD + "A" + ChatColor.YELLOW + "c" + ChatColor.GREEN + "h" + ChatColor.AQUA + "i"
				+ ChatColor.BLUE + "e" + ChatColor.DARK_BLUE + "v" + ChatColor.RED + "e" + ChatColor.GOLD + "m"
				+ ChatColor.YELLOW + "e" + ChatColor.GREEN + "n" + ChatColor.AQUA + "t" + ChatColor.BLUE + "2"
				+ ChatColor.RESET;
	}
}
