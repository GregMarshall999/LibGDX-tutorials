package com.gdx.tutorials.FLGT.control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.Input.Keys.*;

public class PCControls implements InputProcessor {
    public static boolean left, right, up, down;
    public static boolean mouse1Down, mouse2Down, mouse3Down;
    public static Vector2 mouseLocation = new Vector2(0, 0);
    public static boolean dragging;

    @Override
    public boolean keyDown(int keycode) {
        return keyOperation(true, keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return keyOperation(false, keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return mouseOperation(true, screenX, screenY, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        dragging = false;
        return mouseOperation(false, screenX, screenY, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        dragging = true;

        mouseLocation.x = screenX;
        mouseLocation.y = screenY;

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;

        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    /**
     * Sets the active or inactive state to the associated action for the given key
     * @param setter true or false to set action activity
     * @param keycode key associated to the action
     * @return true if keycode is registered to an action
     */
    private static boolean keyOperation(boolean setter, int keycode) {
        boolean keyProcessed = false;

        switch (keycode) {
            case LEFT -> {
                left = setter;
                keyProcessed = true;
            }
            case RIGHT -> {
                right = setter;
                keyProcessed = true;
            }
            case UP -> {
                up = setter;
                keyProcessed = true;
            }
            case DOWN -> {
                down = setter;
                keyProcessed = true;
            }
            default -> {}
        }

        return keyProcessed;
    }

    /**
     * Sets Mouse buttons to active or inactive state for the given button
     * @param setter true or false to set button activity
     * @param screenX x position of the cursor
     * @param screenY y position of the cursor
     * @param button mouse button activated
     * @return true of mouse button is registered
     */
    private static boolean mouseOperation(boolean setter, float screenX, float screenY, int button) {
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;

        return switch (button) {
            case 0 -> {
                mouse1Down = setter;
                yield true;
            }
            case 1 -> {
                mouse2Down = setter;
                yield true;
            }
            case 2 -> {
                mouse3Down = setter;
                yield true;
            }
            default -> false;
        };
    }
}
