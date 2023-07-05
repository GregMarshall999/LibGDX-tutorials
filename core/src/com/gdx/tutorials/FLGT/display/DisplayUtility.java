package com.gdx.tutorials.FLGT.display;

import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.screen.*;

public abstract class DisplayUtility {
    private static StartupScreen startupScreen;
    private static PreferencesScreen preferencesScreen;
    private static MenuScreen menuScreen;
    private static MainScreen mainScreen;
    private static EndScreen endScreen;

    public static void changeScreen(FLGTScreen screen, FLGT context) {
        switch (screen) {
            case STARTUP -> {
                if (startupScreen == null) startupScreen = new StartupScreen(context);
                context.setScreen(startupScreen);
            }
            case MENU -> {
                if (menuScreen == null) menuScreen = new MenuScreen(context);
                context.setScreen(menuScreen);
            }
            case PREFERENCES -> {
                if (preferencesScreen == null) preferencesScreen = new PreferencesScreen(context);
                context.setScreen(preferencesScreen);
            }
            case APPLICATION -> {
                mainScreen = new MainScreen(context);
                context.setScreen(mainScreen);
            }
            case ENDGAME -> {
                if (endScreen == null) endScreen = new EndScreen(context);
                context.setScreen(endScreen);
            }
        }
    }
}
