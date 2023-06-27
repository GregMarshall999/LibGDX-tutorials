package com.gdx.tutorials.FLGT.control;

import com.badlogic.gdx.InputProcessor;

import static com.badlogic.gdx.Input.Keys.*;

public class KeyBoardController implements InputProcessor {
    public boolean left, right, up, down;

    @Override
    public boolean keyDown(int keycode) {
        boolean keyProcessed = false;

        switch (keycode) {
            case LEFT -> {
                left = true;
                keyProcessed = true;
            }
            case RIGHT -> {
                right = true;
                keyProcessed = true;
            }
            case UP -> {
                up = true;
                keyProcessed = true;
            }
            case DOWN -> {
                down = true;
                keyProcessed = true;
            }
            default -> {}
        }

        return keyProcessed;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean keyProcessed = false;

        switch (keycode) {
            case LEFT -> {
                left = false;
                keyProcessed = true;
            }
            case RIGHT -> {
                right = false;
                keyProcessed = true;
            }
            case UP -> {
                up = false;
                keyProcessed = true;
            }
            case DOWN -> {
                down = false;
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
