package com.gdx.tutorials.FLGT.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Utils {
    private static String[] fNames;
    private static String[] lNames;
    private static Pixmap pixmap;

    /**
     *  Converts HSV values to RGBA
     * @param hue The hue input value
     * @param saturation The saturation of the colour
     * @param value the value of the colour
     * @param alpha the alpha to output with RGB
     * @return The RGBA value
     */
    public static Color hsvToRgba(float hue, float saturation, float value, float alpha) {

        int h = (int)(hue * 6);
        float f = hue * 6 - h;
        float p = value * (1 - saturation);
        float q = value * (1 - f * saturation);
        float t = value * (1 - (1 - f) * saturation);

        return switch (h) {
            case 0 -> new Color(value, t, p, alpha);
            case 1 -> new Color(q, value, p, alpha);
            case 2 -> new Color(p, value, t, alpha);
            case 3 -> new Color(p, q, value, alpha);
            case 4 -> new Color(t, p, value, alpha);
            case 5 -> new Color(value, p, q, alpha);
            default ->
                    throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + hue + ", " + saturation + ", " + value);
        };
    }

    /** converts RGB 0-1 to hex string e.g. FFFFFF
     * @param r red value 0-1
     * @param g green value 0-1
     * @param b blue value 0-1
     * @return RGB in HEX
     */
    public static String rgbToString(float r, float g, float b) {
        String rs = Integer.toHexString((int)(r * 256));
        String gs = Integer.toHexString((int)(g * 256));
        String bs = Integer.toHexString((int)(b * 256));
        return rs + gs + bs;
    }

    /**
     * Generates a random name using 2 text files in the assets folder
     * @return random name
     */
    public static String generateRandomName(){
        String name = "";
        if(fNames == null){
            FileHandle fnfile = Gdx.files.internal("fname.txt");
            fNames = fnfile.readString().split("\n");

            FileHandle lnfile = Gdx.files.internal("lname.txt");
            lNames = lnfile.readString().split("\n");
        }
        int fni = (int)(Math.random() * fNames.length);
        name = fNames[fni].trim();

        int lni = (int)(Math.random() * lNames.length);
        name += "_"+lNames[lni].trim();

        return name;
    }

    /**
     * Quick access to console logging
     * @param o
     */
    public static void log(Object o){
        System.out.println(o);
    }

    public static Texture makeTexture(int width, int height, String hex){
        if(hex.length() == 6 ){
            hex+="FF";
        }
        return makeTexture(width,height,Color.valueOf(hex));
    }

    public static TextureRegion makeTextureRegion(int width, int height, String hex){
        if(hex.length() == 6 ){
            hex+="FF";
        }
        return makeTextureRegion(width,height,Color.valueOf(hex));
    }

    public static TextureRegion makeTextureRegion(int width, int height, Color col){
        TextureRegion tex = new TextureRegion(makeTexture(width,height,col));
        return tex;
    }

    public static Texture makeTexture(int width, int height, Color col){
        Texture tex;
        tex = new Texture(makePixMap(width,height,col));
        disposePixmap();
        return tex;
    }

    private static Pixmap makePixMap(int width, int height, Color fill){
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(fill);
        pixmap.fill();
        return pixmap;
    }

    private static void disposePixmap(){
        pixmap.dispose();
    }
}
