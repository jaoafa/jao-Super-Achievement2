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

public class SpeakerOnlyAfa implements AchievementInterface, Listener {
    Set<UUID> AlreadyJao = new HashSet<>();

    @Override
    public Achievement getAchievement() {
        return Achievement.SPEAKONLYAFA;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }
        Component component = event.message();
        String message = PlainComponentSerializer.plain().serialize(component);

        if (message.equals("jao")) {
            AlreadyJao.add(player.getUniqueId());
            return;
        }

        if (!message.equals("afa")) {
            AlreadyJao.add(player.getUniqueId());
            return;
        }

        if(AlreadyJao.contains(player.getUniqueId())){
            return;
        }

        Achievementjao.getAchievementAsync(player, getAchievement());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }
        AlreadyJao.remove(player.getUniqueId());
    }
}
