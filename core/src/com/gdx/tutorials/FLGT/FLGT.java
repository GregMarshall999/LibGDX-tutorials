package com.gdx.tutorials.FLGT;

import com.badlogic.gdx.Game;
import com.gdx.tutorials.FLGT.screen.ScreenUtil;

public class FLGT extends Game {
    @Override
    public void create() {
        ScreenUtil.initLoading(this);
    }
}
