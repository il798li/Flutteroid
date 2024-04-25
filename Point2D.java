/*
 *  Project 3 - Flutteroid Farm and Binary Search Trees
 *
 * Note: DO NOT MODIFY!
 */

/**
 * This class represents an immutable point in a 2D space. 
 * 
 * 
 *
 * @version 1.0 2024-03-06
 * Initial Version
 */
public final class Point2D {

    private int x;
    private int y;

    /**
     * Default constructor for a 2D point. Makes points that are at (0, 0).
     */
    public Point2D() {
        x = 0;
        y = 0;
    }

    /**
     * Constructor that allows assigning the x and y values for the 2D point.
     * 
     * @param x The point x coordinate value.
     * @param y The point y coordinate value.
     */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Allows access to the point x coordinate value.
     * 
     * @return The point x coordinate value.
     */
    public final int getX() {
        return x;
    }

    /**
     * Allows access to the point y coordinate value.
     * 
     * @return The point y coordinate value.
     */
    public final int getY() {
        return y;
    }

    /**
     * Converts a point object into a string representation in this format:
     * (x, y)
     * 
     * @return A string reference representing a point object.
     */
    @Override
    public final String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Tests two point objects for equality.
     * 
     * @param obj The right operand object ("this" is the left operand).
     * 
     * @return True if the two points are equal, false otherwise.
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Point2D other = (Point2D) obj;
        
        if (this.x != other.x) {
            return false;
        }
        
        if (this.y != other.y) {
            return false;
        }
        
        return true;
    }
    
}
