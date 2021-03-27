package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FirstDed implements AchievementInterface, Listener {
    List<UUID> JaoJao = new ArrayList<>();

    @Override
    public Achievement getAchievement() {
        return Achievement.FIRSTDED;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Component component = event.message();
        String message = PlainComponentSerializer.plain().serialize(component);

        if (!message.equals("jaojao")) {
            return;
        }

        if (!JaoJao.contains(player.getUniqueId())) {
            JaoJao.add(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (JaoJao.contains(player.getUniqueId())) {
            JaoJao.remove(player.getUniqueId());
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
