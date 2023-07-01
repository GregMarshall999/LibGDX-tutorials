package com.gdx.tutorials.FLGT.display;

import com.gdx.tutorials.FLGT.FLGT;
import com.gdx.tutorials.FLGT.display.screen.*;

public abstract class DisplayUtility {
    private static PreferenceScreen preferenceScreen;
    private static MenuScreen menuScreen;
    private static MainScreen mainScreen;
    private static EndScreen endScreen;

    /**
     * Changes the screen for the given context
     * @param context Running application context
     * @param screen screen id to switch to
     */
    public static void changeScreen(FLGT context, FLGTScreen screen) {
        switch (screen) {
            case MENU -> {
                if(menuScreen == null)
                    menuScreen = new MenuScreen(context);
                context.setScreen(menuScreen);
            }
            case PREFERENCE -> {
                if(preferenceScreen == null)
                    preferenceScreen = new PreferenceScreen(context);
                context.setScreen(preferenceScreen);
            }
            case APPLICATION -> {
                if(mainScreen == null)
                    mainScreen = new MainScreen(context);
                context.setScreen(mainScreen);
            }
            case ENDGAME -> {
                if(endScreen == null)
                    endScreen = new EndScreen(context);
                context.setScreen(endScreen);
            }
        }
    }

    /**
     * Prepares startup screen for the given application context
     * @param context Application context
     */
    public static void initStartup(FLGT context) {
        context.setScreen(new StartupScreen(context));
    }
}
