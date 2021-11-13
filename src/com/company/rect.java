package com.company;

import java.awt.*;

public class rect {
    private int height;
    private int width;
    private int offset;
    private Color color;

    /**
     * Sets rectangle maximum size
     *
     * @param x Width to set the rectangle to
     * @param y Height to set the rectangle to
     */
    public void setMaximumSize(int x, int y) {
        width = x;
        height = y;
    }

    /**
     * Gets rectangle height
     *
     * @return Return height of rectangle
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets rectangle height
     *
     * @param y Height to set the rectangle to
     */
    public void setHeight(int y) {
        height = y;
    }

    /**
     * Get rectangle width
     *
     * @return Return width of rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set rectangle width
     *
     * @param x Width to set rectangle to
     */
    public void setWidth(int x) {
        width = x;
    }

    /**
     * Get rectangle offset
     *
     * @return Return offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Set Rectangle Offset
     *
     * @param x Offset to set rectangle to
     */
    public void setOffset(int x) {
        offset = x;
    }

    /**
     * Get rectangle color
     *
     * @return Color of rectangle
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set rectangle color
     *
     * @param x Color to set rectangle to
     */
    public void setColor(Color x) {
        color = x;
    }
}
