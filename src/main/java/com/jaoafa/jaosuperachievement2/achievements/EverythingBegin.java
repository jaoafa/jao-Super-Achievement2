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
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EverythingBegin implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.EVERYTHINGBEGIN;
    }

    static Set<UUID> isSpoken = new HashSet<>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        isSpoken.add(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Component component = event.message();
        String message = PlainComponentSerializer.plain().serialize(component);

        if (!isSpoken.contains(player.getUniqueId())) {
            return;
        }
        isSpoken.remove(player.getUniqueId());
        if (!message.equals("hai")) {
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
