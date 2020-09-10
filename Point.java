/**
 * This class represents a Point object in the first quadrant of the Cartesian coordinate system.
 * x and y coordinate values must be non-negative double.
 *
 * @author Mai Aloni (ID: 309651107)
 * @version 16-09-2018
 *
 */

public class Point
{
    private double _radius;
    private double _alpha;
    final int DEFAULT_VAL = 0;
    private final double RADIANS_TO_DEGRESS = (180/(Math.PI));
    private final double DEGREES_TO_RADIANS = (1/ RADIANS_TO_DEGRESS);
    private final double ROUND_ACCURATE = 10000;


    /**
     * Constructor for objects of class Point. Construct a new point with the specified x y coordinates.
     * If the x coordinate is negative it is set to zero. If the y coordinate is negative it is set to zero.
     * @param x The X coordinate of this Point
     * @param y the Y coordinate of this Point
     */

    public Point(double x, double y)
    {
        if (x < DEFAULT_VAL)
        {
            x = DEFAULT_VAL;
        }
        if (y < DEFAULT_VAL)
        {
            y = DEFAULT_VAL;
        }

        //Convert the values ​​from Cartesian to Polar
        setAlpha(x,y);
        setRadius(x,y);
    }


    /**
     * Constructor for objects of class Point. Copy constructor, construct a point using another point.
     * @param other The point from which to construct the new object
     */

    public Point(Point other)
    {
        if (other != null)
        {
            this._radius = other._radius;
            this._alpha = other._alpha;
        }
    }


    private void setRadius (double x, double y)
    {
        _radius = Math.sqrt(Math.pow(x,2) + Math.pow (y,2)) ;
    }


    private void setAlpha (double x, double y)
    {
        if (x == DEFAULT_VAL)
        {
            _alpha = 90;
        } else
            {
            _alpha = Math.atan(y / x) * RADIANS_TO_DEGRESS;
            }
    }


    /**
     * Returns the x coordinate of the point.
     * @return The x coordinate of the point
     */

    public double getX()
    {
        double x = Math.cos(_alpha* DEGREES_TO_RADIANS)*_radius;
        return Math.round(x*ROUND_ACCURATE)/ROUND_ACCURATE;
    }


    /**
     * Returns the y coordinate of the point.
     * @return The y coordinate of the point
     */

    public double getY ()
    {
        double y = Math.sin(_alpha* DEGREES_TO_RADIANS)*_radius;
        return Math.round(y*ROUND_ACCURATE)/ROUND_ACCURATE;
    }


    /**
     * Sets the x coordinate of the point.
     *  If the new x coordinate is negative the old x coordinate will remain unchanged.
     * @param num The new x coordinate
     */

    public void setX (double num)
    {
        if (num >= DEFAULT_VAL)
        {
            double y = getY();

            setAlpha(num, y);
            setRadius(num, y);
        }
    }


    /**
     * Sets the y coordinate of the point.
     *  If the new y coordinate is negative the old x coordinate will remain unchanged.
     * @param num The new y coordinate
     */

    public void setY (double num)
    {
        if (num >= DEFAULT_VAL)
        {
            double x = getX();

            setAlpha(x, num);
            setRadius(x, num);
        }
    }


    /**
     * Returns a string representation of Point in the format (x,y).
     * @return a string representation of this point
     */

    public String toString ()

    {
        return "(" + getX() + "," + getY() + ")";
    }


    /**
     * Check if this point equals other point.
     * @param other  The point to check equality with
     * @return true if this point equals other point
     */

    public boolean equals (Point other)
    {
        if (this._radius == other._radius && this._alpha == other._alpha)
        {
            return true;
        } else
            {
                return false;
            }
    }


    /**
     * Check if this point is above other point.
     * @param other The point to check if this point is above
     * @return true if this point is above other point
     */

    public boolean isAbove (Point other)
    {
        if (this.getY() > other.getY())
        {

            return true;
        } else
            {
            return false;
            }
    }


    /**
     * Check if this point is under other point.
     * @param other The point to check if this point is under
     * @return true if this point is under other point
     */

    public boolean isUnder (Point other)
    {
        return other.isAbove(this);
    }


    /**
     * Check if this point is left of a received point.
     * @param other The point to check if this is left of
     * @return true if this point is to the left of other point
     */

    public boolean isLeft (Point other)
    {
        if (this.getX() < other.getX())
        {
            return true;
        } else
            {
            return false;
            }
    }


    /**
     * Check if this point is right of a received point.
     * @param other The point to check if this is right of
     * @return true if this point is to the right of other point
     */

    public boolean isRight (Point other)
    {
        return other.isLeft(this);
    }


    /**
     * Calculate the distance between this point and other point.
     * @param other The point to calculate the distance from
     * @return The distance
     */

    public double distance (Point other)
    {
        return Math.round(Math.sqrt(Math.pow(getX() - other.getX(), 2) + Math.pow(getY() - other.getY(), 2))*ROUND_ACCURATE)/ROUND_ACCURATE;
    }


    /**
     Moves a point. If either coordinate becomes negative the point remains unchanged.
     * remain at the same place and not move.
     * @param dx  The difference to add to x
     * @param dy  The difference to add to y
     */

    public void move ( double dx, double dy)
    {
        double x = dx + getX();
        double y = dy + getY();

        //checks if the move does not move the point out of the first quadrant
        if ((x >= 0) && (y >= 0))
        {
            setX(x);
            setY(y);
        }

    }
}