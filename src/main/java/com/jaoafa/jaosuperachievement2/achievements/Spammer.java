package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Spammer implements AchievementInterface, Listener {
    final Map<UUID, String> map = new HashMap<>();

    @Override
    public Achievement getAchievement() {
        return Achievement.SPAMMER;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NPC")) {
            return;
        }
        Component component = event.message();
        String message = PlainTextComponentSerializer.plainText().serialize(component);

        if (map.containsKey(player.getUniqueId())) {
            String oldMessage = map.get(player.getUniqueId());
            if (oldMessage.equals(message)) {
                Achievementjao.getAchievementAsync(player, getAchievement());
            }
        }

        map.put(player.getUniqueId(), message);
    }
}
