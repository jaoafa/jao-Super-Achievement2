package com.jaoafa.jaosuperachievement2.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class AchievementAPI {
	/**
	 * 「[jaoSuperAchievement] 」というプレフィックスを返します。
	 * @return プレフィックス
	 */
	public static Component getPrefix() {
	    return Component.text().append(
            Component.text("["),
            getjaoSuperAchievement(),
            Component.text("]"),
            Component.space()
        ).build();
	}

	/**
	 * 「jaoSuperAchievement」というプレフィックスを返します。
	 * @return プレフィックス
	 */
	public static Component getjaoSuperAchievement() {
	    return Component.text().append(
            Component.text("j", NamedTextColor.RED),
            Component.text("a", NamedTextColor.GOLD),
            Component.text("o", NamedTextColor.YELLOW),
            Component.text("S", NamedTextColor.GREEN),
            Component.text("u", NamedTextColor.AQUA),
            Component.text("p", NamedTextColor.BLUE),
            Component.text("e", NamedTextColor.DARK_BLUE),
            Component.text("r", NamedTextColor.RED),
            Component.text("A", NamedTextColor.GOLD),
            Component.text("c", NamedTextColor.YELLOW),
            Component.text("h", NamedTextColor.GREEN),
            Component.text("i", NamedTextColor.AQUA),
            Component.text("e", NamedTextColor.BLUE),
            Component.text("v", NamedTextColor.DARK_BLUE),
            Component.text("e", NamedTextColor.RED),
            Component.text("m", NamedTextColor.GOLD),
            Component.text("e", NamedTextColor.YELLOW),
            Component.text("n", NamedTextColor.GREEN),
            Component.text("t", NamedTextColor.AQUA),
            Component.text("2", NamedTextColor.BLUE)
        ).build();
	}
}
