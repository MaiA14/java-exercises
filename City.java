/**
 * This class represents a city. City is represented by its name, center, central station, number of residents (must be positive),
 * and number of neighborhoods (must be positive).
 *
 * @author Mai Aloni (ID: 309651107)
 * @version 17-09-2018
 *
 */

public class City
{
    private String _cityName;
    private Point _cityCenter;
    private Point _centralStation;
    private long _numOfResidents;
    private int _noOfNeighborhoods;

    private final int MIN_VALUE = 0;
    private final int MIN_VALUE_RESIDENTS = 0;
    private final int MIN_VALUE_NEIGHBORHOODS = 1;


    /**
     * Construct a city. Construct a city with name, x and y coordinate values of city center,
     * x and y coordinate values of central station,
     * number of residents (non negative. If its negative it should be initialized to MIN_VALUE_RESIDENTS - 0),
     * number of neighborhoods (if its negative or 0 , it should be initialized to MIN_VALUE_NEIGHBORHOODS - 1).
     * @param cityName The name of the city
     * @param cityCenterX The x coordinate of the city's center
     * @param cityCenterY The y coordinate of the city's center
     * @param centralStationX The x coordinate of the city's central station
     * @param centralStationY The y coordinate of the city's central station
     * @param numOfResidents Number of residents
     * @param noOfNeighborhoods Number of neighborhoods
     */

    public City(String cityName, double cityCenterX, double cityCenterY, double centralStationX,
                double centralStationY, long numOfResidents, int noOfNeighborhoods)
    {
        if (numOfResidents < MIN_VALUE_RESIDENTS)
        {
            numOfResidents = MIN_VALUE_RESIDENTS;
        }

        if (noOfNeighborhoods < MIN_VALUE)
        {
            noOfNeighborhoods = MIN_VALUE_NEIGHBORHOODS;
        }

        _cityName = cityName;
        _cityCenter = new Point (cityCenterX, cityCenterY);
        _centralStation = new Point (centralStationX, centralStationY);
        _numOfResidents = numOfResidents;
        _noOfNeighborhoods = noOfNeighborhoods;
    }


    /**
     * Copy constructor for cities.
     * Construct a city with the same attributes as other city.
     * @param other The point object from which to construct the new city
     */

    public City(City other)
    {
        this._cityName = other._cityName;
        this._cityCenter = new Point(other._cityCenter);
        this._centralStation = new Point(other._centralStation);
        this._numOfResidents = other._numOfResidents;
        this._noOfNeighborhoods = other._noOfNeighborhoods;
    }


    /**
     * Returns the city's name.
     * @return The city's name
     */

    public String getCityName()
    {
        return this._cityName;
    }


    /**
     * Returns the center of the city as a Point object.
     * @return The city's center
     */

    public Point getCityCenter()
    {
        return new Point(this._cityCenter);
    }

    /**
     * Returns the central station of the city as a Point object.
     * @return The central station of the city
     */


    public Point getCentralStation()
    {
        return new Point(this._centralStation);
    }

    /**
     * Returns the number of residents of the city.
     * @return The number of residents of the city
     */


    public long getNumOfResidents()
    {
        return this._numOfResidents;
    }


    /**
     * Returns the number of neighborhoods of the city.
     * @return The number of neighborhoods of the city
     */

    public int getNoOfNeighborhoods()
    {
        return this._noOfNeighborhoods;
    }


    /**
     * Sets the name of the city.
     * @param cityName The new name of the city
     */

    public void setCityName(String cityName)
    {
        this._cityName = cityName;
    }


    /**
     * Sets the center point of the city.
     * @param cityCenter The new center point of the city
     */


    public void setCityCenter(Point cityCenter)
    {
        this._cityCenter = new Point(cityCenter);
    }


    /**
     * Sets the central station point of the city.
     * @param centralStation The new central station point of the city
     */

    public void setCentralStation(Point centralStation)
    {
        this._centralStation = new Point(centralStation);
    }


    /**
     * Sets the number of residents of the city. If it is negative set it to MIN_VALUE_RESIDENTS -  0, else to numOfResidents.
     * @param numOfResidents The new number of residents in the city
     */

    public void setNumOfResidents(long numOfResidents)
    {
        if (numOfResidents < MIN_VALUE_RESIDENTS)
        {
            this._numOfResidents = MIN_VALUE_RESIDENTS;
        }
        else
        {
            this._numOfResidents = numOfResidents;
        }
    }


    /**
     * Sets the number of neighborhoods of the city,. if it negative number set to 0, else set to noOfNeighborhoods
     * @param noOfNeighborhoods The new number of neighborhoods in the city
     */

    public void setNoOfNeighborhoods(int noOfNeighborhoods)
    {
        if (noOfNeighborhoods < MIN_VALUE)
        {
            this._noOfNeighborhoods = MIN_VALUE_NEIGHBORHOODS;
        }
        else
        {
            this._noOfNeighborhoods = noOfNeighborhoods;
        }
    }


    /**
     * Adds or subtracts residents to the city. If the result residents is negative,
     * it sets to zero and returns false. Otherwise it sets to the result and returns true.
     * @param residentsUpdate The new number of residents in the city
     * @return True if the new number of residents is non-negative
     */

    public boolean addResidents(long residentsUpdate)
    {
        long newNumOfResidents = residentsUpdate + this._numOfResidents;
        if (newNumOfResidents > MIN_VALUE_RESIDENTS)
        {
            setNumOfResidents(newNumOfResidents);
            return true;
        }
        else
        {
            setNumOfResidents(MIN_VALUE_RESIDENTS);
            return false;
        }
    }


    /**
     * Move the location of the central station of the city with the given deltas.
     * If the new location has a negative coordinate - the central station keeps its original location
     *
     * @param deltaX How much the x coordinate of the central station of the city is to be moved
     * @param deltaY  How much the y coordinate of the central station of the city is to be moved
     */

    public void moveCentralStation(double deltaX, double deltaY)
    {
        this._centralStation.move(deltaX, deltaY);
    }


    /**
     * Calculates the distance between the city center and the central station.
     * @return  The distance between the center of this city and its central station
     */

    public double distanceBetweenCenterAndStation()
    {
        return this._cityCenter.distance(this._centralStation);
    }

    /**
     * Returns a new city with a name, dX and dY away from this city, zero residents and one neighborhood.
     * @param newCityName The new city's name
     * @param dX The x difference from this city's location
     * @param dY The y difference from this city's location
     * @return  A new city with the given values
     */


    public City newCity(String newCityName, double dX, double dY)
    {
        Point _cityCenter = this._cityCenter;
        Point _centralStation = this._centralStation;
        _cityCenter.move(dX, dY);
        moveCentralStation(dX,dY);
        return (new City (newCityName, _cityCenter.getX(), _cityCenter.getY() ,_centralStation.getX()
                ,_centralStation.getY(),MIN_VALUE_RESIDENTS, MIN_VALUE_NEIGHBORHOODS));
    }


    /**
     * Returns a string representation of this city.
     *
     * Overrides toString in class java.lang.Object
     * @return  String representation of this city

     */

    public String toString()
    {
        return ("City Name: " + getCityName()
                + "\nCity Center: " + getCityCenter()
                + "\nCentral Station: " + getCentralStation()
                + "\nNumber Of Residents: " + getNumOfResidents()
                + "\nNumber Of Neighborhoods: " + getNoOfNeighborhoods());
    }
}
