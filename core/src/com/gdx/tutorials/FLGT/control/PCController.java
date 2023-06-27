package com.gdx.tutorials.FLGT.control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.Input.Keys.*;

public class PCController implements InputProcessor {
    public boolean left, right, up, down;

    public boolean mouse1Down, mouse2Down, mouse3Down;
    public boolean dragging;
    public Vector2 mouseLocation = new Vector2();

    @Override
    public boolean keyDown(int keycode) {
        return keyOp(true, keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return keyOp(false, keycode);
    }

    private boolean keyOp(boolean setter, int keycode) {
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

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return mouseOp(true, screenX, screenY, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        dragging = false;
        return mouseOp(false, screenX, screenY, button);
    }

    private boolean mouseOp(boolean setter, float screenX, float screenY, int button) {
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

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
