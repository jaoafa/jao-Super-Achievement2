package com.jaoafa.jaosuperachievement2.command;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.api.AchievementAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cmd_jaoSuperAchievement2 implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        JavaPlugin plugin = Main.getJavaPlugin();
        if (plugin == null) {
            sender.sendMessage(Component.text().append(
                AchievementAPI.getPrefix(),
                Component.text("JavaPluginの取得に失敗しました。", NamedTextColor.AQUA)
            ));
            return true;
        }
        PluginDescriptionFile desc = plugin.getDescription();
        String nowVersion = desc.getVersion();
        String latestVersion = getLatestVersion();
        if (latestVersion == null) {
            sender.sendMessage(Component.text().append(
                AchievementAPI.getPrefix(),
                Component.text("最新バージョンの取得に失敗しました。", NamedTextColor.AQUA)
            ));
            return true;
        }
        int compare = compareVersion(nowVersion, latestVersion);
        if (compare == 0) {
            sender.sendMessage(Component.text().append(
                AchievementAPI.getPrefix(),
                Component.text("現在のバージョンは最新です。", NamedTextColor.AQUA)
            ));
            return true;
        }
        if (compare == 1) {
            sender.sendMessage(Component.text().append(
                AchievementAPI.getPrefix(),
                Component.text("現在のバージョンよりも新しいバージョンがあります。", NamedTextColor.AQUA),
                Component.text("最新バージョン: ", NamedTextColor.AQUA),
                Component.text(latestVersion, NamedTextColor.GREEN)
            ));
            return true;
        }
        if (compare == -1) {
            sender.sendMessage(Component.text().append(
                AchievementAPI.getPrefix(),
                Component.text("現在のバージョンよりも最新バージョンが古いです。", NamedTextColor.AQUA),
                Component.text("最新バージョン: ", NamedTextColor.AQUA),
                Component.text(latestVersion, NamedTextColor.GREEN)
            ));
            return true;
        }
        sender.sendMessage(Component.text().append(
            AchievementAPI.getPrefix(),
            Component.text("バージョンチェックに失敗しました。", NamedTextColor.AQUA)
        ));
        return true;
    }

    /**
     * セマンティック・バージョニングに基づき、バージョンを比較します。
     *
     * @param version1 バージョンひとつめ
     * @param version2 バージョンふたつめ
     * @return version1 が version2 より新しい場合は 1、version1 が version2 より古い場合は -1、同じ/セマンティック・バージョニングに合致しなければ 0
     */
    private int compareVersion(String version1, String version2) {
        Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)");
        Matcher match1 = pattern.matcher(version1);
        if (!match1.matches()) {
            return 0;
        }
        Matcher match2 = pattern.matcher(version2);
        if (!match2.matches()) {
            return 0;
        }
        if (version1.equals(version2)) {
            return 0;
        }
        int[] v1 = new int[]{
            Integer.parseInt(match1.group(1)),
            Integer.parseInt(match1.group(2)),
            Integer.parseInt(match1.group(3))
        };
        int[] v2 = new int[]{
            Integer.parseInt(match2.group(1)),
            Integer.parseInt(match2.group(2)),
            Integer.parseInt(match2.group(3))
        };
        for (int i = 0; i < 3; i++) {
            if (v1[i] > v2[i]) {
                return -1;
            }
        }
        return 1;
    }

    private String getLatestVersion() {
        try {
            String url = "https://api.github.com/repos/jaoafa/jao-Super-Achievement2/releases/latest";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).get().build();
            Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                return null;
            }
            ResponseBody body = response.body();
            if (body == null) {
                return null;
            }
            JSONObject object = new JSONObject(body.string());
            Pattern pattern = Pattern.compile("v(\\d+\\.\\d+\\.\\d+)"); // e.g., v1.0.0
            return pattern
                .matcher(object.getString("tag_name"))
                .replaceAll("$1");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
