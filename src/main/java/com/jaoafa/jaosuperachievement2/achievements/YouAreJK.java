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

public class YouAreJK implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.YOUAREJK;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }
        Component component = event.message();
        String message = PlainComponentSerializer.plain().serialize(component);

        if (!message.equalsIgnoreCase("ぴえん")) {
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }
}
