package com.gdx.tutorials.FLGT.control;

import com.badlogic.gdx.InputProcessor;

public class PCControls implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        return ControllerUtility.keyOperation(true, keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return ControllerUtility.keyOperation(false, keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return ControllerUtility.mouseOperation(true, screenX, screenY, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        ControllerUtility.dragging = false;
        return ControllerUtility.mouseOperation(false, screenX, screenY, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        ControllerUtility.dragging = true;

        ControllerUtility.mouseLocation.x = screenX;
        ControllerUtility.mouseLocation.y = screenY;

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        ControllerUtility.mouseLocation.x = screenX;
        ControllerUtility.mouseLocation.y = screenY;

        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
