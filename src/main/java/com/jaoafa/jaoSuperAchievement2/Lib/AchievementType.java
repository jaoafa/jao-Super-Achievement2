package com.jaoafa.jaoSuperAchievement2.Lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jaoafa.jaoSuperAchievement2.Main;
import com.jaoafa.jaoSuperAchievement2.MySQLDBManager;

public class AchievementType {
	private int id;
	private String title;
	private String description;

	public AchievementType(int id) {
		try {
			MySQLDBManager sqlmanager = Main.getMySQLDBManager();
			Connection conn = sqlmanager.getConnection();

			PreparedStatement statement = conn.prepareStatement("SELECT * FROM jaoSuperAchievement2_Type WHERE id = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if (res.next()) {
				this.id = res.getInt("id");
				title = res.getString("title");
				description = res.getString("description");

				res.close();
				statement.close();
			} else {
				res.close();
				statement.close();
				throw new IllegalArgumentException("指定されたIDのjaoSuperAchievementは見つかりません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getID() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
}
