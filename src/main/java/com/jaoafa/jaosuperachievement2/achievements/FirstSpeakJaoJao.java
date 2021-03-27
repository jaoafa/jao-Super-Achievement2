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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FirstSpeakJaoJao implements AchievementInterface, Listener {
    Map<UUID, Long> JaoJaoTime = new HashMap<>();

    @Override
    public Achievement getAchievement() {
        return Achievement.FIRSTSPEAKJAOJAO;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Component component = event.message();
        String message = PlainComponentSerializer.plain().serialize(component);
        if (!message.equals("jaojao")) {
            return;
        }

        JaoJaoTime.put(player.getUniqueId(), System.currentTimeMillis() / 1000L);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (!JaoJaoTime.containsKey(player.getUniqueId())) {
            return;
        }

        if (((System.currentTimeMillis() / 1000L) - JaoJaoTime.get(player.getUniqueId())) > 10) {
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
