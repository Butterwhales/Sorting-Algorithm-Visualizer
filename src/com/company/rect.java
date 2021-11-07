package com.company;

import java.awt.*;

public class rect {
    private int height;
    private int width;
    private int offset;
    private Color color;

    /**
     * @param x Width to set the rectangle to
     * @param y Height to set the rectangle to
     */
    public void setMaximumSize(int x, int y) {
        width = x;
        height = y;
    }

    /**
     * @return Return height of rectangle
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param y Height to set the rectangle to
     */
    public void setHeight(int y) {
        height = y;
    }

    /**
     * @return Return width of rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param x Width to set rectangle to
     */
    public void setWidth(int x) {
        width = x;
    }

    /**
     * @return Return offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param x Offset to set rectangle to
     */
    public void setOffset(int x) {
        offset = x;
    }

    /**
     * @return Color of rectangle
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param x Color to set rectangle to
     */
    public void setColor(Color x) {
        color = x;
    }

}
