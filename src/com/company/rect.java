package com.company;

import java.awt.*;

public class rect {
    private int height;
    private int width;
    private int offset;
    private Color color;

    public void setMaximumSize(int x, int y) {
        width = x;
        height = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int y) {
        height = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int x) {
        width = x;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int x) {
        offset = x;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color x) {
        color = x;
    }

}
