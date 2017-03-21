package com.charter;

/**
 * Created by david on 3/21/17.
 */
public class Path {
    private Point from;
    private Point to;

    public Path() {
    }

    public Path(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    public Point getFrom() {
        return from;
    }

    public void setFrom(Point from) {
        this.from = from;
    }

    public Point getTo() {
        return to;
    }

    public void setTo(Point to) {
        this.to = to;
    }
}
