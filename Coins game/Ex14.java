/**
 * @author Mai Aloni (ID: 309651107)
 * @version 02-01-2019
 *
 */

public class Ex14
{

    /*****************Q1*****************/

    //to calculate Tamar's choices
    private static int max(int num1, int num2)
    {
        return (num1 > num2) ? num1 : num2;
    }

    /**
     * Gets coin's array as a parameter and prints the choices of Amir & Tamar. In the end of this game,
     * it also prints the totals.
     *
     * @param arr The coin's array
     */

    // Time complexity - O(n)
    // Space complexity - O(1)
    public static void win(int[] arr)
    {
        int sumEven = 0;
        int sumOdd = 0;

        //Amir can take sum of coins that are located in the even indexes or in the odd indexs. Amir gets this choice since he is the first one to play
        for (int i = 0; i < arr.length; i++)
        {
            if (i % 2 == 0)
            {
                sumEven += arr[i];
            }
            else
                {
                sumOdd += arr[i];
                }
        }

        boolean amirOdd = (sumOdd > sumEven);
        boolean amirEven = !amirOdd;
        int sumAmir = 0;
        int sumTamar = 0;

        //we want that Amir will get the higher sum between his choices, so he win.
        if (amirEven)
        {
            sumAmir = sumEven;
        }
        else
            {
            sumAmir = sumOdd;
            }

        //playing the game
        boolean amirTurn = true;
        int firstIndex = 0;
        int lastIndex = arr.length - 1;
        int amirCoin = 0;
        int tamarCoin = 0;

        for (int i = 0; i < arr.length; i++)
        {
            if (amirTurn && amirOdd)
            {
                if (firstIndex % 2 == 0)
                //if isEven(FirstIndex)
                {
                    amirCoin = arr[lastIndex];
                    lastIndex = lastIndex - 1;
                }
                else
                    {
                    amirCoin = arr[firstIndex];
                    firstIndex = firstIndex + 1;
                    }
                System.out.println("Amir took: " + amirCoin);
            }
            else if (amirTurn)
            {
                if (firstIndex % 2 == 0)
                {
                    amirCoin = arr[firstIndex];
                    firstIndex = firstIndex + 1;
                }
                else
                    {
                    amirCoin = arr[lastIndex];
                    lastIndex = lastIndex - 1;
                    }
                System.out.println("Amir took: " + amirCoin);
            }
            else
                {


                boolean tamarChoseFirstIndex = (arr[firstIndex] > arr[lastIndex]);
                tamarCoin = max(arr[firstIndex], arr[lastIndex]);
                if (tamarChoseFirstIndex) {
                    firstIndex = firstIndex + 1;
                } else {
                    lastIndex = lastIndex - 1;
                }
                sumTamar += tamarCoin;
                System.out.println("Tamar took: " + tamarCoin);
                }
            amirTurn = !amirTurn;
        }
        System.out.println("Amir's total: " + sumAmir);
        System.out.println("Tamar's total: " + sumTamar);

    }

/*****************Q2*****************/


        /*
        a)  The method gets an array (a) and a number (num), returns True if the cells sequence sum is equal to num
            and prints the index number from which the sum began to the index where the sum was completed. Otherwise it
            returns and prints "no"
        b)  Time complexity - O(n^3)
            Space complexity - O(1)*/
    //  c)

    /**
     * gets an array (a) and a number (num), returns True if the cells sequence sum is equal to num
     * and prints the index number from which the sum began to the index where the sum was completed.
     * Otherwise it returns and prints "no".
     *
     * @param a the array
     * @return true - if the cells sequence sum is equal to num
     * False - if the cells sequence sum is not equal to num
     */

    public static boolean what(int[] a, int num)
    {
        int leftIndex = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++)
        {
            sum += a[i];
            //Add elements to sum, until we reach to the required num. If the sum is bigger then num, we need to remove
            //elements from sum
            while (sum > num)
            {
                sum -= a[leftIndex];
                leftIndex++;
            }
            if (sum == num)
            {
                System.out.print("between " + leftIndex + " and " + i);
                return true;
            }
        }
        //If we reach here, the cells sequence sum is not equal to num
        System.out.print("No");
        return false;
    }


    /*d) Time complexity - O(n).
         There are at most 2 operations performed on every element:
         1. the number is added to the result
         2. the number is subtracted from result
         So the upper bound on number of operations is 2n which is O(n).
         Space complexity - O(1).
         The algorithm requires single and permanent memory cells: leftIndex, i and sum.
         Therefore the complexity of its place is O(1).
    */


/*****************Q3*****************/

    /**
     * Gets a two-dimensional array filled with integers, and a integer number, and returns the length of the slope
     * from the longest from order num in the array
     *
     * @param mat two-dimensional array
     * @param num order num
     * @return the longest slope from order num
     */

    public static int longestSlope(int[][] mat, int num)
    {
        int res = longestSlope(mat, num, 0, 0);
        if (res > 0) res++;
        return res;
    }

    private static int longestSlope(int[][] mat, int num, int i, int j)
    {
        //in case we got to the last row
        if (i == mat.length)
            return 0;
        if (j == mat[0].length)
            return longestSlope(mat, num, i + 1, 0);

        return max(longestSlopeCell(mat, num, i, j, 0), longestSlope(mat, num, i, j + 1));
    }

    private static int longestSlopeCell(int[][] mat, int num, int i, int j, int k)
    {
        int maxUp = k;
        int maxRight = k;
        int maxDown = k;
        int maxLeft = k;

        //calculates the possible moves on the slope
        if ((i > 0) && ((mat[i][j] - mat[i - 1][j]) == num))
        {
            maxUp = longestSlopeCell(mat, num, i - 1, j, k + 1);
        }
        if ((j < (mat[0].length - 1)) && ((mat[i][j] - mat[i][j + 1]) == num))
        {
            maxRight = longestSlopeCell(mat, num, i, j + 1, k + 1);
        }
        if ((i < (mat.length - 1)) && ((mat[i][j] - mat[i + 1][j]) == num))
        {

            maxDown = longestSlopeCell(mat, num, i + 1, j, k + 1);
        }
        if ((j > 0) && ((mat[i][j] - mat[i][j - 1]) == num))
        {
            maxLeft = longestSlopeCell(mat, num, i, j - 1, k + 1);
        }

        return max(maxUp, maxRight, maxLeft, maxDown);
    }

    //calculates the longest slop
    private static int max(int num1, int num2, int num3, int num4)
    {
        return max(max(num1, num2), max(num3, num4));
    }


    /*****************Q4*****************/

    /**
     * Gets array size n and max and returns the number of options to fill the array with numbers
     * from 1 to max to be arranged in a non-descending order
     *
     * @param n   size of array
     * @param max maximum number in the array
     * @return number of options to fill the array with numbers from 1 to max
     */


    public static int howManySorted(int n, int max)
    {
        return howManySorted(n, max,1);
    }

    private static int howManySorted(int n,int max, int i)
    {
        //all numbers are arranged in ascending order
        if(n == 0)
        {
            return 1;
        }
        //numbers in range: 1 to maximum have ended
        if(i > max)
        {
            return 0;
        }
        return howManySorted(n, max, i + 1) + howManySorted(n - 1, max, i);
    }

}

