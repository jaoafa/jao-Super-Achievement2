package com.jaoafa.jaoSuperAchievement2.Lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jaoafa.jaoSuperAchievement2.Main;
import com.jaoafa.jaoSuperAchievement2.MySQLDBManager;

public class AchievementType {
	private int id;
	private String name;
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
				name = res.getString("name");
				description = res.getString("description");
			} else {
				throw new IllegalArgumentException("指定されたIDのjaoSuperAchievementは見つかりません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
