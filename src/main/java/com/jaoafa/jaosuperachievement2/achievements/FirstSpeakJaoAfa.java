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

public class FirstSpeakJaoAfa implements AchievementInterface, Listener {
    Set<UUID> isSpeakedJao = new HashSet<>();

    @Override
    public Achievement getAchievement() {
        return Achievement.FIRSTSPEAKJAOAFA;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        if(player.hasMetadata("NPC")){
            return;
        }
        Component component = event.message();
        String message = PlainComponentSerializer.plain().serialize(component);

        if(message.equals("jao")){
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
