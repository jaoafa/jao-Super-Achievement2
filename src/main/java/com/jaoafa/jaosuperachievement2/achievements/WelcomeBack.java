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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class WelcomeBack implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.WELCOMEBACK;
    }

    Set<UUID> isSpeakedJao = new HashSet<>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Component component = event.message();
        String message = PlainComponentSerializer.plain().serialize(component);

        if (message.equals("rj")){
            // rjと言った場合
            Achievementjao.getAchievementAsync(player, getAchievement());
        }else if(message.equals("rejao")){
            // jaoをしゃべった場合追加
            isSpeakedJao.add(player.getUniqueId());
        }else if(message.equals("afa") && isSpeakedJao.contains(player.getUniqueId())){
            // 前回jaoをしゃべっていてafaとしゃべった場合
            Achievementjao.getAchievementAsync(player, getAchievement());
        }else{
            // それ以外の場合はjaoをしゃべっていないことにする
            isSpeakedJao.remove(player.getUniqueId());
        }
    }
}
