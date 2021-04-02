package com.jaoafa.jaosuperachievement2.lib;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class Discord {
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	String token;

	public Discord(String token) {
		this.token = token;
	}

	/**
	 * Discordにメッセージを送信します。
	 * @param channelId チャンネルID
	 * @param content メッセージテキスト
     */
	public void sendMessage(String channelId, String content) {
		try {
            JSONObject paramobj = new JSONObject();
            paramobj.put("content", content);

            String url = "https://discord.com/api/channels/" + channelId + "/messages";
            RequestBody body = RequestBody.create(JSON, paramobj.toString()); // NoSuchMethodError: okhttp3.RequestBody.create
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
                if (response.body() != null) {
                    System.out.println("[jaoSuperAchievement2] Discord Warning: " + response.body().string());
                }
            }
        } catch (IOException e) {
			System.out.println("[jaoSuperAchievement2] Discord IOException: " + e.getMessage());
        }
	}
}
