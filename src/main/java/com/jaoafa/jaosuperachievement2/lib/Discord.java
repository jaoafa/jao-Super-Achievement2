package com.jaoafa.jaosuperachievement2.lib;

import com.jaoafa.jaosuperachievement2.Main;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public record Discord(String token) {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Discordにメッセージを送信します。
     *
     * @param channelId チャンネルID
     * @param content   メッセージテキスト
     */
    public void sendMessage(String channelId, String content) {
        try {
            JSONObject paramobj = new JSONObject();
            paramobj.put("content", content);

            String url = "https://discord.com/api/channels/" + channelId + "/messages";
            RequestBody body = RequestBody.create(paramobj.toString(), JSON);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bot " + token)
                .addHeader("User-Agent", "DiscordBot (https://jaoafa.com, v0.0.1)")
                .post(body)
                .build();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    return;
                }
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    Main.getMainLogger().info("[jaoSuperAchievement2] Discord Warning: " + responseBody.string());
                }
            }
        } catch (IOException e) {
            Main.getMainLogger().info("[jaoSuperAchievement2] Discord IOException: " + e.getMessage());
        }
    }
}
