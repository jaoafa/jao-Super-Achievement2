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

import java.util.UUID;

public class FirstMinicry implements AchievementInterface, Listener {
    String OldMessage = null;
    UUID OldPlayerUUID = null;

    @Override
    public Achievement getAchievement() {
        return Achievement.FIRSTMIMICRY;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Component component = event.message();
        String message = PlainComponentSerializer.plain().serialize(component);

        if (!message.equals(OldMessage)) {
            OldMessage = message;
            OldPlayerUUID = player.getUniqueId();
            return;
        }

        if (OldPlayerUUID.equals(player.getUniqueId())) {
            OldMessage = message;
            OldPlayerUUID = player.getUniqueId();
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());

        OldMessage = message;
        OldPlayerUUID = player.getUniqueId();
    }
}
