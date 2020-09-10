/**
 * This class represents a country. Country contains a list of cities.
 *
 * @author Mai Aloni (ID: 309651107)
 * @version 19-09-2018
 *
 */

public class Country
{
    private String _countryName;
    private int _noOfCities;
    private City[] _cities;
    private final int MAX_NUM_CITIES = 1000;
    private final int MIN_NUM_OF_CITIES = 0;
    private final int MIN_NUM_OF_CITIES_DISTANCE = 2;

    /**
     * Constructs a country. Constructs a country with given name.
     * empty array with maximum number of cities and a variable for the number of the cities.
     * @param countryName The country name
     */

    public Country(String countryName)
    {
        _countryName = countryName;
        _cities = new City[MAX_NUM_CITIES];
        _noOfCities = 0;// SHAY: Correct
    }


    /**
     * Adds city. adds city by its name, city's center, central station, number of residents and number of neighborhoods.
     * it adds city in case there is place in the country.
     * @param cityName          The name of the city
     * @param cityCenterX       The x coordinate of the city's center
     * @param cityCenterY       The y coordinate of the city's center
     * @param centralStationX   The x coordinate of the city's central station
     * @param centralStationY   The y coordinate of the city's central station
     * @param numOfResidents    Number of residents
     * @param noOfNeighborhoods Number of neighborhoods
     * @return true if there is place in the cities array, false in case there is not.
     */


    public boolean addCity(String cityName, double cityCenterX, double cityCenterY, double centralStationX,
                           double centralStationY, long numOfResidents, int noOfNeighborhoods)
    {
        if (_noOfCities < MAX_NUM_CITIES)
        {
            _cities[_noOfCities] = new City(cityName, cityCenterX, cityCenterY, centralStationX, centralStationY,
                    numOfResidents, noOfNeighborhoods);
            _noOfCities++;
            return true;
        }
        else
            {
            return false;
            }// SHAY: Correct
    }


    /**
     * Returns the number of residents.
     * @return number of residents
     */

    public int getNumOfResidents()
    {
        int total = 0;
        for (int i = 0; i < _noOfCities; i++)
            total += _cities[i].getNumOfResidents();
        return total;// SHAY: Correct
    }

    /**
     * Checks what is the longest distance between to cities and returns the number of the longest distance.
     * @return the longest distance between two cities if the number of cities in the country is bigger or equal to
     * MIN_NUM_OF_CITIES_DISTANCE - 2, else returns 0.
     */

    public double longestDistance()
    {
        if (getNumOfCities() >= MIN_NUM_OF_CITIES_DISTANCE)
        {
            double Max = 0;
            for (int i = 0; i < _noOfCities - 1; i++)
            {
                for (int j = i + 1; j < _noOfCities; j++)
                {
                    double distanceBetweenTwoCities = (_cities[i].getCityCenter()).distance(_cities[j].getCityCenter());
                    if (distanceBetweenTwoCities > Max)
                    {
                        Max = distanceBetweenTwoCities;
                    }// SHAY: Correct
                }
            }
            return Max;
        }
        else
            {
            return 0;
            }
    }


    /**
     * Gets a name of city and returns a string with details of all cities that are located north of the given city.
     * If there isn't a city with the given name, the string it returns is "The is no city with that name...".
     * In case that there are no cities north of the given city, the string it returns is "There are no cities north of..."
     *
     * @param cityName The city's name
     * @return string - "There is no city with the name..." - if there is no city with this name.
     * "There are no cities north of..." -  if there no cities that are located north of the given city.
     * A string with details about all the cities that are located north of the given city.
     */

    public String citiesNorthOf(String cityName)
    {
        boolean isFound = false;
        String stringNorthOf = "";
        int foundCity = 0; //to save the index of the city that we found
        for (int i = 0; i < _noOfCities; i++)
        {
            if (_cities[i].getCityName().equals(cityName))
            {
                isFound = true;
                foundCity = i;
                break;
            }
        }

        if (isFound == false)
        {
            return "There is no city with the name " + cityName;
        }
        else
            {
            for (int i = 0; i < _noOfCities; i++)
            {
                boolean isNorth = false;
                isNorth = (_cities[i].getCityCenter().isAbove(_cities[foundCity].getCityCenter()));
                if (isNorth)
                {
                    stringNorthOf += _cities[i].toString() + "\n" + "\n";
                }
            }

            if (stringNorthOf == "")
            {
                return "There are no cities north of" + cityName;
            }
            else
                {
                return "The cities north of " + cityName +  " are: " + "\n" + "\n" +stringNorthOf;
                }
            }
    }


    /**
     * looks for the the southernmost city, in case there is at least 1 (MIN_NUM_OF_CITIES - 0) and returns it. else -
     * returns null.
     *
     * @return the southern most city if there is. else return null.
     */

    public City southernmostCity()
    {
        if (getNumOfCities() > MIN_NUM_OF_CITIES)
        {
            City southernMost = new City(_cities[0]);
            for (int i = 0; i < _noOfCities; i++)
            {
                if (_cities[i].getCityCenter().isUnder(southernMost.getCityCenter()) == true)
                {
                    southernMost = new City(_cities[i]);
                }
            }
            return southernMost;
        }
        else
            {
            return null;
            }
    }


    /**
     * Returns the country name.
     *
     * @return country name
     */

    public String getCountryName()
    {
        return _countryName;
    }


    /**
     * Returns the number of cities in the country.
     *
     * @return the number of cities
     */

    public int getNumOfCities()
    {
        return _noOfCities;
    }


    /**
     * Returns an array of cities the size of the number of cities in the country.
     * @return new array with the cities in country
     */

    public City[] getCities()
    {
        City[] newArray = new City[_noOfCities];
        for (int i = 0; i < _noOfCities; i++)
        {
            City c1 = new City(_cities[i]);
            newArray[i] = c1;
        }
        return newArray;
    }


    /**
     * Gets two cities and unite them to one city. In addition, the method puts the new city in the place of the
     * largest city and deletes the smallest one. (measured by the number of residents).
     * If the cities are identical in size, the second city will be deleted.
     *
     * @param city1 the name of the first city
     * @param city2 the name of the second city
     * @return unify city with the name: "city1-city2"
     * center: the point in the middle between the two cities' centers
     * central station: the western point of the two cities' central station
     * number of residents: sum of residents in city1 and city2
     * number of neighborhood: sum of neighborhoods in city1 and city2
     */

    public City unifyCities(String city1, String city2)
    {
        Point westernStation;
        int city1Index = 0;
        int city2Index = 0;
        String unifyCityName = "";
        for (int i = 0; i < _noOfCities; i++)
        {
            if (_cities[i].getCityName().equals(city1))
            {
                city1Index = i;
            }
            if (_cities[i].getCityName().equals(city2))
                city2Index = i;
        }

        unifyCityName = "" + city1 + "-" + city2 + "";
        long sumOfResidents = _cities[city1Index].getNumOfResidents() + _cities[city2Index].getNumOfResidents();
        int sumOfNeighborhoods = _cities[city1Index].getNoOfNeighborhoods() + _cities[city2Index].getNoOfNeighborhoods();
        Point cityCenter1 = _cities[city1Index].getCityCenter();
        Point cityCenter2 = _cities[city2Index].getCityCenter();
        Point middleCenter = this.middle(cityCenter1, cityCenter2);
        Point centralStation1 = _cities[city1Index].getCentralStation();
        Point centralStation2 = _cities[city2Index].getCentralStation();

        //checks if central station1 is western to central station2
        if (cityCenter1.isLeft(centralStation2))
        {
            westernStation = centralStation1;
        } else
            {
            westernStation = centralStation2;
            }
        long numOfResidents1 = _cities[city1Index].getNumOfResidents();
        long numOfResidents2 = _cities[city2Index].getNumOfResidents();

        City unifyCity = new City(unifyCityName, middleCenter.getX(), middleCenter.getY(), westernStation.getX(), westernStation.getY(), sumOfResidents, sumOfNeighborhoods);
        if (numOfResidents1 < numOfResidents2)
        {
            _cities[city2Index] = unifyCity; //stores the new city in the place of the bigger city (city2 in this case)
            _cities[city1Index] = _cities[_noOfCities - 1]; //stores the first city in the array in the place of the smallest city order to close holes in the array
        }
        else
        {
            _cities[city1Index] = unifyCity;
            _cities[city2Index] = _cities[_noOfCities - 1];
        }
        _cities[_noOfCities] = null;
        _noOfCities = _noOfCities - 1;
        return unifyCity;
    }


    /**
     * Returns a string representation of this country.
     * Overrides toString in class java.lang.Object
     * @return String representation of this country
     */

    public String toString()
    {
        if (getNumOfCities() > MIN_NUM_OF_CITIES)
        {

            String countryString = "Cities of " + getCountryName() + ":\n";
            for (int i = 0; i < _noOfCities; i++)
            {
                countryString = countryString + _cities[i].toString() + "\n";
            }
            return countryString;
        }
        else
            {
            return "There are no cities in this country.";
            }
    }



    private Point middle(Point point1, Point point2)

    {
        return new Point( (point1.getX() + point2.getX())/2,(point1.getY() + point2.getY())/2);
    }

}