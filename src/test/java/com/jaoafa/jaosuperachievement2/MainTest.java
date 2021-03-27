package com.jaoafa.jaosuperachievement2;

import com.jaoafa.jaosuperachievement2.lib.Achievement;
import com.jaoafa.jaosuperachievement2.lib.AchievementInterface;
import com.jaoafa.jaosuperachievement2.lib.ClassFinder;
import org.bukkit.event.Listener;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.fail;

public class MainTest {
	@Test
	public void ListenerTest() {
	    Set<Achievement> achievements = new HashSet<>();
		try {
			ClassFinder classFinder = new ClassFinder();
			for (Class<?> clazz : classFinder.findClasses("com.jaoafa.jaosuperachievement2.achievement")) {
				if (!clazz.getName().startsWith("com.jaoafa.jaosuperachievement2.achievement.")) {
					continue;
				}
				if (clazz.getEnclosingClass() != null) {
					continue;
				}
				if (clazz.getName().contains("$")) {
					continue;
				}

				Constructor<?> construct = clazz.getConstructor();
				Object instance = construct.newInstance();

                if (!(instance instanceof AchievementInterface)) {
                    System.out.println("! " + clazz.getSimpleName() + ": AchievementInterfaceを実装していないようです。");
                    fail();
                }

                AchievementInterface achievement = (AchievementInterface) instance;

                if(achievements.contains(achievement.getAchievement())){
                    System.out.println(clazz.getSimpleName() + " 既にこの実績は登録されています。対象実績設定を誤っていませんか？");
                    fail();
                    return;
                }
                achievements.add(achievement.getAchievement());

				if (instance instanceof Listener) {
					try {
						@SuppressWarnings("unused")
						Listener listener = (Listener) instance;
						System.out.println(clazz.getSimpleName() + " 登録可能");
					} catch (ClassCastException e) {
						// commandexecutor not implemented
						System.out.println("! " + clazz.getSimpleName() + ": Listenerを実装していないようです。");
						fail();
                    }
				} else {
					System.out.println("! " + clazz.getSimpleName() + ": Listenerを実装していないようです。");
					fail();
                }
			}
		} catch (Exception e) { // ClassFinder.findClassesがそもそもException出すので仕方ないという判断で。
			e.printStackTrace();
			fail();
        }
	}
}
