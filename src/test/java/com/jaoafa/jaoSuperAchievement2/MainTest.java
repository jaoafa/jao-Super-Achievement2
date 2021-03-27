package com.jaoafa.jaoSuperAchievement2;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.bukkit.event.Listener;
import org.junit.Test;

import com.jaoafa.jaoSuperAchievement2.Lib.ClassFinder;

public class MainTest {
	@Test
	public void ListenerTest() {
		try {
			ClassFinder classFinder = new ClassFinder();
			for (Class<?> clazz : classFinder.findClasses("com.jaoafa.jaoSuperAchievement2.Achievements")) {
				if (!clazz.getName().startsWith("com.jaoafa.jaoSuperAchievement2.Achievements.")) {
					continue;
				}
				if (clazz.getEnclosingClass() != null) {
					continue;
				}
				if (clazz.getName().contains("$")) {
					continue;
				}

				Constructor<?> construct = (Constructor<?>) clazz.getConstructor();
				Object instance = construct.newInstance();

				if (instance instanceof Listener) {
					try {
						@SuppressWarnings("unused")
						Listener listener = (Listener) instance;
						System.out.println(clazz.getSimpleName() + " registable");
					} catch (ClassCastException e) {
						// commandexecutor not implemented
						System.out.println("! " + clazz.getSimpleName() + ": Listener not implemented [1]");
						fail();
                    }
				} else {
					System.out.println("! " + clazz.getSimpleName() + ": Listener not implemented [2]");
					fail();
                }
			}
		} catch (Exception e) { // ClassFinder.findClassesがそもそもException出すので仕方ないという判断で。
			e.printStackTrace();
			fail();
        }
	}
}
