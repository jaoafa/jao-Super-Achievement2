package com.jaoafa.jaosuperachievement2.achievements;

import com.jaoafa.jaosuperachievement2.Main;
import com.jaoafa.jaosuperachievement2.MySQLDBManager;
import com.jaoafa.jaosuperachievement2.api.Achievementjao;
import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ContinuationLogins implements AchievementInterface, Listener {
    @Override
    public Achievement getAchievement() {
        return Achievement.CONTINUATIONLOGINS;
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        // この時点でSQLにログイン記録が入っているはず。login_successチェックはしない。
        Player player = event.getPlayer();
        int days = getContinuationDays(player);
        System.out.println("[ContinuationLogins] ContinuationLoginDays: " + days);
        if (days >= 7) {
            // 7日間以上ログイン
            Achievementjao.getAchievementAsync(player, getAchievement());
        }
    }

    /**
     * 継続ログイン日数を取得する。
     *
     * @param player プレイヤー
     * @return 継続ログイン日数
     */
    private int getContinuationDays(Player player) {
        MySQLDBManager sqlManager = Main.getMySQLDBManager();
        try {
            Connection conn = sqlManager.getConnection();
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0); // 今日の0時
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM login WHERE uuid = ? AND ? <= date AND date <= ? LIMIT 1");
            for (int i = 1; i < 10; i++) {
                // i = 継続ログイン日数。最大10日。
                cal.add(Calendar.DAY_OF_MONTH, -1); // 1日前
                String day0 = formatDate(cal.getTime()); // 00:00:00
                String day24 = formatDate(new Date(cal.getTimeInMillis() + (60 * 60 * 24 - 1) * 1000)); // 23:59:59

                statement.setString(1, player.getUniqueId().toString());
                statement.setString(2, day0);
                statement.setString(3, day24);
                ResultSet res = statement.executeQuery();

                if (!res.next()) {
                    res.close();
                    statement.close();
                    return i;
                }
                res.close();
            }
            statement.close();
            return 10;
        } catch (SQLException e) {
            return 1;
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
