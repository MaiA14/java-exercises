public class Recursion
{
    //83a2 - 2018
    public static int prince(int[][] drm, int i, int j)
    {
        int res = prince(drm, i, j, -1, 1);
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }

    private static int prince(int[][] drm, int i, int j, int prev, int len) {
        if (i < 0 || i >= drm.length || j < 0 || j >= drm[0].length) return Integer.MAX_VALUE; //out of bound
        if (drm[i][j] == -2) return Integer.MAX_VALUE; //already visited
        if (drm[i][j] == -1) return len; //found the villain
        if (prev != -1 && (drm[i][j] - prev < -2 || drm[i][j] - prev > 1)) return Integer.MAX_VALUE; //can't jump

        int minRoute, temp;
        temp = drm[i][j];
        drm[i][j] = -2;
        minRoute = min(
                prince(drm, i + 1, j, temp, len + 1), //down
                prince(drm, i - 1, j, temp, len + 1), //up
                prince(drm, i, j + 1, temp, len + 1), //left
                prince(drm, i, j - 1, temp, len + 1));//right
        drm[i][j] = temp;
        return minRoute;
    }

    private static int min(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    public static int findSingle(int[] a) {
        int high = a.length - 1;
        int low = 0;
        int mid;
        while (low < (high - 1)) //we don't need to look for mid anymore
        {
            mid = (low + high) / 2;
            if ((mid % 2 == 0) && (a[mid] == a[mid + 1]) || ((mid % 2 == 1) && (a[mid] == a[mid - 1])))
                low = mid;
            else
                high = mid;
        }
        return a[high];
    }

    //82A - 2016
    public static int where(int[] vec) {
        if (vec.length == 1)
            return -1;
        return where(vec, 0, 1, vec.length - 1);
    }

    private static int where(int[] vec, int left, int p, int right) {
        if (p > right)
            return -1;
        if (sum(vec, left, p - 1) == sum(vec, p, right))
            return p;
        return where(vec, left, p + 1, right);
    }

    private static int sum(int[] vec, int lo, int hi) {
        if (lo > hi)
            return 0;
        return vec[lo] + sum(vec, lo + 1, hi);
    }

    public static boolean isBalanced(int[] vec, int k) {
        return isBalanced(vec, 0, vec.length - 1, k);
    }

    public static boolean isBalanced(int[] vec, int left, int right, int k) {
        if (left == right)
            return (k == 0);
        int pBalanced = where(vec, left, left + 1, right);
        if (pBalanced == -1)
            return (k == 0);
        return isBalanced(vec, left, pBalanced - 1, k - 1) && isBalanced(vec, pBalanced, right, k - 1);
    }

    //89 - 2015

    public static boolean splitEqualMult(int[] a) {
        return splitEqualMult(a, 0, 1, 1); //1 is the smallest result
    }

    private static boolean splitEqualMult(int[] a, int i, int sum1, int sum2) {
        if (i == a.length) //return true if we passed all elements and found a split, otherwise - false
            return sum1 == sum2;
        boolean split1 = splitEqualMult(a, i + 1, sum1 * a[i], sum2);
        boolean split2 = splitEqualMult(a, i + 1, sum1, sum2 * a[i]);
        return split1 || split2;
    }

    // O(logn), O(1)
    public static boolean findX(int[] a, int x) {
        if (a.length == 1)
            return false;
        int low, high, mid;
        low = 0;
        high = a.length - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (mid == a.length - 1)
                return false;
            if (a[mid] + a[mid + 1] == x)
                return true;
            else if (a[mid] + a[mid + 1] > x)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return false;
    }

    // 87 - 2017
    public static boolean isPythagorean(int[] arr) {
        for (int c = arr.length - 1; c > 1; c--) //we need to run on at least 2 indexes
        {
            int a = 0;
            int b = c - 1;
            while (b > a) {
                double sum = Math.pow(arr[a], 2) + Math.pow(arr[b], 2);
                if (sum == Math.pow(arr[c], 2))
                    return true;
                else if (sum < Math.pow(arr[c], 2))
                    a++; //sum should be bigger, so we need to increase a
                else
                    b--; //sum should be lower, so we need to decrease b
            }
        }
        return false;
    }

    //87 a6 - 2018
    public static int passingCars(int[] a) {
        int total = 0, zeros = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0)
                zeros++;
            else total += zeros;
        }
        return total;
    }

    public static int minDiff(int[] a)
    {
        return minDiff(a, 0, 0, 0);
    }

    private static int minDiff(int[] a, int i, int sum1, int sum2) {
        if (i == a.length) return Math.abs(sum1 - sum2);
        int group1 = minDiff(a, i + 1, sum1 + a[i], sum2);
        int group2 = minDiff(a, i + 1, sum1, sum2 + a[i]);
        return Math.min(group1, group2);
    }

    //87 b6 -2016
    private static int count(int sum, int total, int current)
    {
        if (total > sum || current > sum) /*if the sum until now bigger than thr requested one,
                                            or the current number we want to add is bigger than requested on, stop*/
            return 0;
        if (total == sum)
            return 1;
        current = current + 1;
        return count(sum, total + current, current) + count(sum, total, current);
    }

    public static int count(int sum)
    {
        return count(sum, 0, 0);
    }

    //o(n), o(1)
    public static int subStrC(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == 'c')
                count++;
        return count;
    }

    //O(n), O(1)
    public static int subStrMaxC(String s, char c, int k) {
        //count how many times c appears in the string
        int total = 0;
        int totalSub = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'c')
                total++;
        }

        if (total < 2) //if there is no string that met with the condition
            return 0;
        for (int i = k; i >= 0; i--) {
            if (i < total)
                totalSub = totalSub + (total - (i + 1));
        }
        return totalSub;
    }

    //83 b -2015
    // O(n), O(1)
    public static void replace(int[] a) {
        if (a.length != 0) {
            int max = 0;
            int temp = 0;
            for (int i = a.length - 1; i >= 0; i--) {
                if (a[i] > max) {
                    temp = a[i];
                    a[i] = max;
                    max = temp;
                } else {
                    a[i] = max;
                }
            }
        }

    }

    private static int lcs(String s, String t, int i, int j, int max) {
        if (i == s.length() || j == t.length()) //we passed all letters, so we need to return max
            return max;

        if (s.charAt(i) == t.charAt(j))
        {
            return lcs(s, t, i + 1, j + 1, max + 1);
        }
        return Math.max(lcs(s, t, i + 1, j, max), lcs(s, t, i, j + 1, max));
    }

    public static int lcs(String s, String t) {
        return lcs(s, t, 0, 0, 0);
    }

    //91 a -2015
    public static int makeSum(int[] lengths, int k, int num) {
        return makeSum(lengths, k, num, 0);
    }

    private static int makeSum(int[] lengths, int k, int num, int i) {
        if (num < 0 || i == lengths.length) return 0;
        if (k - lengths[i] == 0)
            return 1 + makeSum(lengths, k, num, i + 1);
        if (k - lengths[i] > 0)
            return makeSum(lengths, k - lengths[i], num - 1, i) + makeSum(lengths, k, num, i + 1);

        return makeSum(lengths, k, num, i + 1);
    }

    public static boolean what(int[] a, int num) {
        int leftIndex = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            //Add elements to sum, until we reach to the required num. If the sum is bigger then num, we need to remove
            //elements from sum
            while (sum > num) {
                sum -= a[leftIndex];
                leftIndex++;
            }
            if (sum == num) {
                return true;
            }
        }
        //If we reach here, the cells sequence sum is not equal to num
        return false;
    }

    //94 B -2015
    public static int longOrdNum(String s) {
        return longOrdNum(s, 0, 0, 1);
    }

    private static int longOrdNum(String s, int i, int max, int count) {
        if (count > max)
            max = count;
        if (i == s.length() - 1)
            return max;
        if (s.charAt(i) < '0' || s.charAt(i) > '9' || s.charAt(i) >= s.charAt(i + 1))
            return longOrdNum(s, i + 1, max, 1);
        return longOrdNum(s, i + 1, max, count + 1);
    }

    //O(n) O(1)
    public static void smallestSub(int[] a, int k) {
        int i = 0;
        int j = 0;
        int sum = a[0];
        int min = a.length + 1;
        while (i <= a.length && j <= a.length) {
            if (sum > k) {
                min = (min < (j - i + 1)) ? min : (j - i + 1);
                sum -= a[i];
                i++;
            } else {
                j++;
                if (j < a.length)
                    sum += a[j];
            }
        }
        System.out.print(min);
    }

    //85A - 2014
    public static void printPath(int[][] a) {
        printPath(a, 0, 0, a[0][0] - 1);
    }

    private static boolean printPath(int[][] a, int i, int j, int prev) {
        if (i < 0 || i == a.length || j < 0 || j == a[0].length || a[i][j] <= prev)
            return false;

        System.out.print("(" + i + "," + j + ")");

        boolean isHill =
                ((i == 0) || (a[i][j] > a[i - 1][j]))
                        && ((i == a.length - 1) || (a[i][j] > a[i + 1][j]))
                        && ((j == 0) || (a[i][j] > a[i][j - 1]))
                        && ((j == a[0].length - 1) || (a[i][j] > a[i][j + 1]));

        if (isHill) return true;

        return (
                (printPath(a, i - 1, j, a[i][j]))
                        || (printPath(a, i + 1, j, a[i][j]))
                        || (printPath(a, i, j - 1, a[i][j]))
                        || (printPath(a, i, j + 1, a[i][j]))
        );
    }

    //O(n)
    public static int findMax(int[] arr) {
        if (arr.length == 0)
            return 0;
        int max = arr[0];
        int pos = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                pos = i;
                max = arr[i];
                i++;
            }
        }
        return pos;
    }

    //O(logn)
    public static int findMax3(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int mid = (low + high) / 2;
        while ((mid < (arr.length - 1)) && (arr[mid + 1] > arr[mid])) {
            if (arr[low] > arr[mid]) {
                high = mid;
                mid = (low + high) / 2;
            } else if (arr[high] < arr[mid]) {
                low = mid;
                mid = (low + high) / 2;
            } else
                return arr.length - 1;
        }
        return mid;
    }

    public static int findMax2(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        if (arr[low] < arr[high])
            return high;
        while (low < high && arr[mid] < arr[mid + 1]) {
            mid = (low + high) / 2;
            if (arr[mid] > arr[low])
                low = mid + 1;
            else
                high = mid;
        }
        return mid;
    }


    //2018 - 82B
    public static boolean findSum(int[][] mat, int sum, int[][] path) {
        return findSum(mat, 0, 0, sum, path);
    }

    private static boolean findSum(int[][] mat, int i, int j, int sum, int[][] path) {
        if (j == mat[0].length)
            return findSum(mat, i + 1, 0, sum, path);
        if (i == mat.length)
            return false;

        int[][] pathCurrent = new int[mat.length][mat[0].length];

        if (findPathFromCell(mat, i, j, 0, sum, pathCurrent)) {
            copyMat(pathCurrent, path, 0, 0);
            return true;
        } else {
            return findSum(mat, i, j + 1, sum, path);
        }
    }

    private static boolean findPathFromCell(int[][] mat, int i, int j, int currentSum, int sum, int[][] pathCurrent) {
        if ((i < 0) || (i == mat.length) || (j < 0) || (j == mat[0].length) || (pathCurrent[i][j] == 1))
            return false;
        currentSum += mat[i][j];
        if (currentSum > sum)
            return false;
        pathCurrent[i][j] = 1;
        if (currentSum == sum)
            return true;
        return findPathFromCell(mat, i + 1, j, currentSum, sum, pathCurrent)
                || findPathFromCell(mat, i - 1, j, currentSum, sum, pathCurrent)
                || findPathFromCell(mat, i, j + 1, currentSum, sum, pathCurrent)
                || findPathFromCell(mat, i, j - 1, currentSum, sum, pathCurrent)
                ;
    }

    private static void copyMat(int[][] fromMat, int[][] toMat, int i, int j) {
        if (j == toMat[0].length)
            copyMat(fromMat, toMat, i + 1, 0);
        else if (i < toMat.length) {
            toMat[i][j] = fromMat[i][j];
            copyMat(fromMat, toMat, i, j + 1);
        }
    }

    public static int solutions(int num) {
        int[] arr = {0, 0, 0};
        return solutions(1, 1, 0, num, arr);
    }

    private static int solutions(int element, int pos, int sum, int num, int[] arr) {
        if (pos > 3)
            return 0;
        if ((sum + element) > num)
            return 0;
        if ((pos == 3) && ((sum + element) == num)) {
            arr[pos - 1] = element;
            System.out.println(arr[0] + " + " + arr[1] + " + " + arr[2]);
            return 1; //when position equals to sum, there is only one option: 1 + 1 + 1
        }
        arr[pos - 1] = element;
        //update the sum and move to the next position + increase the element by 1.
        return solutions(1, pos + 1, sum + element, num, arr) + solutions(element + 1, pos, sum, num, arr);

    }

    //2014 - 85B

    /****************Q1****************/
    public static int longestWorm(int[][] mat) {
        return longestWorm(mat, 0, 0, 1);
    }

    private static int longestWorm(int[][] mat, int i, int j, int max) {
        if (i == mat.length)
            return max;
        if (j == mat[0].length)
            return longestWorm(mat, i + 1, 0, max);
        max = Math.max(max, findWormFromCell(mat, i, j, mat[i][j], true));
        return longestWorm(mat, i, j + 1, max);
    }

    private static int findWormFromCell(int[][] mat, int i, int j, int prev, boolean start) {
        if (i < 0 || i > mat.length || j < 0 || j > mat.length)
            return 0;
        int right, left, up, down;
        if (prev == mat[i][j] - 1 || start) {
            right = findWormFromCell(mat, i, j + 1, prev, false);
            left = findWormFromCell(mat, i, j - 1, prev, false);
            up = findWormFromCell(mat, i - 1, j, prev, false);
            down = findWormFromCell(mat, i + 1, j, prev, false);
            return 1 + maxWorm(right, left, up, down);
        }
        return 0;
    }


    private static int maxWorm(int num1, int num2, int num3, int num4) {
        int max1 = (num1 > num2) ? num1 : num2;
        int max2 = (num3 > num4) ? num3 : num4;
        return max1 > max2 ? max1 : max2;
    }


    /****************Q2****************/
    //O(n) O(1)
    //check if the number of dividers is even

    //O(1) O(1)
    public static boolean what(int n) {
        int sqr = (int) (Math.sqrt(n));
        if (sqr * sqr == n)
            return false;
        else
            return true;
    }

    //2014 - 84B

    /****************Q1****************/
    public static void specialPrint(int n) {
        specialPrint(n, 0, 1, "");
    }

    private static void specialPrint(int n, int counter, int lastDigit, String s) {
        if (n == counter) //counter got to n. no need to fill more numbers
        {
            System.out.print(s);
            System.out.println();
        } else {
            if (lastDigit == 0) //if last digit is 0 we can add 1
                specialPrint(n, counter + 1, 1, s + " 1");
            else {                    //if last digit is 1, we can add 0 and 0
                specialPrint(n, counter + 1, 0, s + "0");
                specialPrint(n, counter + 1, 0, s + "1");
            }
        }

    }

    /****************Q2****************/
    //returns the longest sub array with odd sum
    //O(n^3)
    private static int getRangeLength(int start, int end) {
        return end - start + 1;
    }

    public static int what3(int[] a) {
        int counterOdd = 0;
        int firstOddIndex = -1;
        int lastOddIndex = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 != 0) //counts group of odd numbers
            {
                counterOdd++;
                if (firstOddIndex == -1)
                    firstOddIndex = i;
                lastOddIndex = i;
            }
        }
        if (counterOdd == 0) //there are no odd numbers in the array
            return 0;
        if (counterOdd % 2 == 1) //there is odd number of numbers in the array, so the sum is odd.
            return a.length;
        //there is even number of odd numbers, at least two
        int rangeDropFirstOddIndex = getRangeLength(firstOddIndex + 1, a.length - 1);
        int rangeDropLastOddIndex = getRangeLength(0, lastOddIndex - 1);
        return Math.max(rangeDropFirstOddIndex, rangeDropLastOddIndex);
    }


    //2013 - 85A4

    /****************Q2****************/
    //O(logn)
    public static int fixedPoint(int[] a) {
        int low = 0;
        int high = a.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (a[mid] == mid)
                return mid;
            else if (a[mid] > mid)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;

    }

    //2013 - 83A3

    /****************Q1****************/

    public static boolean balancePartition(int[] arr) {
        return balancedPartition(arr, 0, 0, 0, 0, 0);
    }

    private static boolean balancedPartition(int[] a, int i, int sum1, int sum2, int add1, int add2) {
        if (i == a.length)
            return sum1 == sum2 && add1 == add2;
        return balancedPartition(a, i + 1, sum1 + a[i], sum2, add1 + 1, add2) || balancedPartition(a, i + 1, sum1, sum2 + a[i], add1, add2 + 1);
    }


    /****************Q2****************/
    //o(n) O(1)
    public static int included(int x, int y) {
        boolean[] digInX = new boolean[10];
        while (x != 0) {
            int dig = x % 10;
            digInX[dig] = true;
            x /= 10;
        }
        boolean[] digInY = new boolean[10];
        while (y != 0) {
            int dig = y % 10;
            digInY[dig] = true;
            y /= 10;
        }
        int k = 0;
        for (int i = 0; i < 10; i++)
            if (digInX[i] && !digInY[i])
                k++;
        return k;
    }

    //2013 - 85A5

    /****************Q1****************/
    public static int minDiff2(int[] arr) {
        return minDifff(arr, 0, 0, 0);
    }

    private static int minDifff(int[] a, int i, int sum1, int sum2)
    {
        if (i == a.length)
            return Math.abs(sum1 - sum2);
        int group1 = minDiff(a, i + 1, sum1 + a[i], sum2);
        int group2 = minDifff(a, i + 1, sum1, sum2 + a[i]);
        return Math.min(group1, group2);
    }

    /****************Q2****************/

//    public static int minAbsSum(int[] a) {
//        if (a.length == 2)
//            return Math.abs(a[0]) + Math.abs(a[1]);
//        int delimiter = findDelimiter(a);
//        if (delimiter == 0 && a[0] > 0)//all numbers are positive
//            return (a[0] + a[1]);
//        else if (delimiter == 0 && a[0] < 0) //all numbers are non-positive
//            return (-a[a.length - 1] - a[a.length - 2]);
//        if (delimiter == a.length - 1) {
//            if (-a[delimiter - 2] < a[delimiter])
//                return (-a[delimiter - 2] - a[delimiter - 1]);
//            else
//                return -a[delimiter - 1] + a[delimiter];
//        }
//        if (delimiter == 1) {
//            if (-a[delimiter - 1] < a[delimiter + 1])
//                return (-a[delimiter - 1] + a[delimiter]);
//            else
//                return a[delimiter - 1] + a[delimiter + 1];
//        }
//        int x = -a[delimiter - 2] - a[delimiter - 1];
//        int y = -a[delimiter - 1] + a[delimiter];
//        int z = [delimiter]+a[delimiter + 1];
//        if (x <= y && x <= z)
//            return x;
//        else if (y <=)
//
//    }

    //2013 - 92b3

    /****************Q1****************/

    public static int longestFlat(int[] arr) {
        return longestFlat(arr, 1, arr[0], arr[0]);
    }

    private static int longestFlat(int[] arr, int i, int prev, int next) {
        if (i == arr.length)
            return 0;
        if (arr[i] > prev && arr[i] - prev != 1)
            return longestFlat(arr, i + 1, arr[i], arr[i]);
        if (arr[i] < next && next - arr[i] != 1)
            return longestFlat(arr, i + 1, arr[i], arr[i]);

        return 1 + longestFlat(arr, i + 1, prev, next);
    }

    /****************Q2****************/
    //O(n) O(1)
    public static void sortMod(int a[], int k) {
        int temp = 0;
        int start = 0;
        //loop from 0 to requested k
        for (int firstMod = 0; firstMod < k; firstMod++) {   //check mod on our array
            for (int i = start; i < a.length; i++) {
                //if we we found a number that his mod equals to k, save it. Swap.
                if (a[i] % k == firstMod) {
                    temp = a[start];
                    a[i] = temp;
                    start++;
                }
            }

        }
    }

    //2013 - 92A3

    /****************Q1****************/

    public static boolean match(int[] a, int[] pattern) {
        return match(a, pattern, 0, 0);
    }

    private static boolean match(int[] a, int[] pattern, int i, int j) {
        if (j == pattern.length)
            return true;
        if (i > a.length - 1 || j > pattern.length - 1)
            return false;

        if (pattern[j] == 0)
            if (a[i] > 0 && a[i] < 100)
                return match(a, pattern, i + 1, j + 1);
            else
                return match(a, pattern, i + 1, 0);

        if (pattern[j] == 1)
            if (a[i] > 0 && a[i] < 10)
                return match(a, pattern, i + 1, j + 1);
            else
                return match(a, pattern, i + 1, 0);

        if (pattern[j] == 2)
            if (a[i] > 9 && a[i] < 100)
                return match(a, pattern, i + 1, j + 1);
            else
                return match(a, pattern, i + 1, 0);

        return false;
    }

    //2012 - 81A1

    /****************Q1****************/

    public static boolean splitEqualSum(int[] a) {
        return splitEqualSum(a, 0, 0, 0);
    }

    private static boolean splitEqualSum(int[] a, int i, int sum1, int sum2) {
        if (i == a.length)
            return (sum1 == sum2);
        boolean group1 = splitEqualSum(a, i + 1, sum1 + a[i], sum2);
        boolean group2 = splitEqualSum(a, i + 1, sum1, sum2 + a[i]);
        return group1 || group2;
    }

    //2012 - 82A2

    /****************Q1****************/

    public static boolean isSumOf(int[] s, int n) {
        return isSumOf(s, n, 0, "");
    }

    private static boolean isSumOf(int[] s, int n, int i, String str) {
        if ((i == s.length) || (n < 0))
            return false;
        if (n == 0) {
            System.out.println(str);
            return true;
        }
        boolean s1 = isSumOf(s, n, i + 1, str);
        boolean s2 = isSumOf(s, n - s[i], i, str + s[i] + " ");
        return s1 || s2;


    }

    /****************Q2****************/

    // O(logn) O(1)
    public static int count(int[] a, int x) {
        int start, end, mid, left, right, temp;
        temp = 0;
        left = 0;
        right = 0;
        start = 0;
        end = a.length - 1;

        while (start <= end) {
            mid = (start + end) / 2;
            if (a[mid] < x)
                start = mid + 1;
            else if (a[mid] > x)
                end = mid - 1;
            else {
                temp = mid;
                break;
            }
        }

        start = 0;
        end = temp;
        while (start <= end) {
            mid = (start + end) / 2;
            if (a[mid] > x)
                end = mid - 1;
            else if (a[mid] < x)
                start = mid + 1;
            else if (mid > 0 && a[mid] == a[mid - 1])
                end = mid - 1;
            else {
                left = mid;
                break;
            }
        }

        start = temp;
        end = a.length - 1;

        while (start <= end) {
            mid = (start + end) / 2;
            if (a[mid] > x)
                end = mid - 1;
            else if (a[mid] < x)
                start = mid + 1;
            else if (mid < a.length - 1 && a[mid] == a[mid + 1])
                start = mid + 1;
            else {
                right = mid;
                break;
            }
        }

        if (right - left != 0)
            return right - left + 1;
        else
            return 0;
    }

    //2012 - 92B5

    /****************Q1****************/

    public static void mirror(int[] a) {
        mirror(a, 0, a.length - 1);
    }

    private static void mirror(int[] a, int left, int right) {
        if (left > right)
            printArr(a, 0);
        else {
            mirror(a, left + 1, right - 1);
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            mirror(a, left + 1, right - 1);
        }
    }

    private static void printArr(int[] arr, int ind) {
        System.out.print(arr[ind] + "\t");
        if (ind < arr.length - 1) printArr(arr, ind + 1);
        else System.out.println();
    }

    /****************Q2****************/

    //O(logn) O(1)
    public static int findBC(String s) {
        int i = 0;
        while (s.charAt(i) == 'a')
            i = (i + 1) * 2;
        return i;
    }

    //O(logn) O(1)
    public static int firstB(String s) {
        int low = 0;
        int mid = 0;
        int high = findBC(s);
        while (high > low + 1) {
            mid = (low + high) / 2;
            if ((s.charAt(mid) == 'b' || s.charAt(mid) == 'c'))
                high = mid;
            else
                low = mid;
        }
        if ((high == low + 1) && (s.charAt(low) == 'a') && (s.charAt(high) == 'b'))
            return high;
        return -1;
    }

    //2012 - 85A3

    /****************Q1****************/

    public static void crossSort(int[] arr) {
        int[] temp = new int[arr.length];
        int i = 0, j, x = 0;
        if (arr.length % 2 == 0)
            j = arr.length - 1;
        else
            j = arr.length - 2;
        while (i < arr.length || j >= 0) {
            if (i < arr.length && j >= 0) {
                if (arr[i] < arr[j]) {
                    temp[x] = arr[i];
                    x++;
                    i = i + 2;
                } else {
                    temp[x] = arr[j];
                    x++;
                    j = j - 2;
                }
            } else if (i < arr.length) {
                temp[x] = arr[i];
                x++;
                i = i + 2;
            } else {
                temp[x] = arr[j];
                x++;
                j = j - 2;
            }
        }

        for (i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
    }


    public static int crossSearch(int[] arr, int x) {
        int start = 0;
        int end, mid;
        if (arr.length % 2 == 0)
            end = arr.length - 2;
        else
            end = arr.length - 1;

        while (start <= end) {
            mid = (start + end) / 2;
            if (mid % 2 != 0)
                mid += 1;
            if (arr[mid] > x)
                end = mid - 2;
            else if (arr[mid] < x)
                start = mid + 2;
            else
                return mid;
        }

        start = 1;
        if (arr.length % 2 != 0)
            end = arr.length - 2;
        else
            end = arr.length - 1;

        while (start <= end) {
            mid = (start + end) / 2;
            if (mid % 2 == 0)
                mid += 1;
            if (arr[mid] < x)
                end = mid - 2;
            else if (arr[mid] > x)
                start = mid + 2;
            else
                return mid;
        }
        return -1;
    }


    /****************Q2****************/

    public static void printPathWeights(int[][] m) {
        printPathWeights(m, 0, 0, 0);
    }

    private static void printPathWeights(int[][] m, int i, int j, int sum) {
        if (i > m.length - 1 || i < 0 || j > m[0].length - 1 || j < 0 || m[i][j] == -1)
            return;

        if (i == m.length - 1 && j == m[0].length - 1) {
            System.out.println(sum + m[m.length - 1][m[0].length] - 1);
            return;
        }
        sum += m[i][j];
        int temp = m[i][j];
        m[i][j] = -1;
        printPathWeights(m, i + 1, j, sum + temp);
        printPathWeights(m, i - 1, j, sum + temp);
        printPathWeights(m, i, j + 1, sum + temp);
        printPathWeights(m, i, j - 1, sum + temp);
        m[i][j] = temp;
    }


    //2012 - 92B3

    /****************Q1****************/
    public static boolean isWay(int[] a) {
        return isWay(a, 0, new int[a.length]);
    }

    private static boolean isWay(int[] a, int i, int[] reference) {
        if (i == a.length - 1)
            return true;
        if (i < 0 || i > a.length - 1 || reference[i] == -1)
            return false;

        reference[i] = -1;
        boolean right = isWay(a, i + a[i], reference);
        boolean left = isWay(a, i - a[i], reference);

        return right || left;
    }

    /****************Q2****************/
    public static int alternating(String s) {
        int count = 1;
        char start = s.charAt(0);
        for (int i = 2; i < s.length(); i += 2) {
            if (s.charAt(i) == start) {
                count++;
            }
        }
        int f = s.length() / 2 - count;
        return Math.min(f, s.length() / 2 - f);


    }


    //2011 - 87A6

    /****************Q1****************/

    public static boolean isPath(int[][] mat) {
        return isPath(mat, 0, 0, 0, true);
    }

    private static boolean isPath(int[][] mat, int i, int j, int prev, boolean s) {
        if (i == mat.length - 1)
            return j == mat.length - 1;
        if (i < 0 || i >= mat.length || j >= mat.length || j < 0)
            return false;
        if (prev + 1 == mat[i][j] || s) {
            boolean right = isPath(mat, i, j + 1, mat[i][j], false),
                    left = isPath(mat, i, j - 1, mat[i][j], false),
                    up = isPath(mat, i + 1, j, mat[i][j], false),
                    down = isPath(mat, i - 1, j, mat[i][j], false);

            return right || left || up || down;
        }
        return false;
    }

    /****************Q2****************/
    //O(n) O(n)
    // count how many multiple of there are
    public static int countMult(int[] a, int x) {
        boolean[] b = new boolean[a.length];
        int mult, count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % x == 0) {
                mult = a[i] / x;
                b[mult - 1] = true;
            }
        }
        for (int i = 0; i < b.length; i++) {
            if (b[i])
                count++;
        }
        return count;
    }

    public static int maximalDrop(int[] a) {
        if (a.length < 2)
            return 0;
        int i = 1; // search index
        int max = 0; // max index
        int min = 0; //min index
        int maxFall = 0; //maximum Fall found
        while (i < a.length) { // keep searching until array ends
            if (a[i] > a[max]) { // if new max value found
                max = i;
                min = i; // replace both indexes
            } else if (a[i] < a[min]) // else if new min value found
                min = i; // replace it
            if (a[max] - a[min] > maxFall) // if new maximum fall found
                maxFall = a[max] - a[min]; // replace it
            i++;
        }
        return maxFall;
    }

    //2009 - B3

    /****************Q1****************/
    public static boolean samePattern(String s1, String s2) {
        if (s2.length() == 0)
            return s1.length() == 0;
        if (s1.length() == 0)
            return s2.length() == 0;

        if (s2.charAt(0) == '*')
            return samePattern(s1, s2.substring(1)) || samePattern(s1.substring(1), s2);

        if (s2.charAt(0) == s1.charAt(0))
            return samePattern(s1.substring(1), s2.substring(1));

        return false;
    }

    //2015 - 83A2

    /****************Q1****************/

    public static int shortestPath(int[][] mat) {
        return shortestPath(mat, 0, -1, 0, 0);
    }

    private static int shortestPath(int[][] mat, int i, int j, int prev, int length) {
        if (i == mat.length - 1 && j == mat[0].length - 1)
            return length;
        if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length)
            return Integer.MAX_VALUE;
        if (mat[i][j] <= prev)
            return Integer.MAX_VALUE;
        int minPath, temp, left, right, up, down;
        prev = mat[i][j];
        mat[i][j] = -1;
        up = shortestPath(mat, i + 1, j, prev, length + 1);
        down = shortestPath(mat, i - 1, j, prev, length + 1);
        right = shortestPath(mat, i, j + 1, prev, length + 1);
        left = shortestPath(mat, i, j - 1, prev, length + 1);
        mat[i][j] = prev;
        return minPath(up, down, right, left);
    }


    private static int minPath(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    /****************Q1****************/
 
    //2016 - 92B2

    /****************Q1****************/

    public static int minPath(char[][] minChess, int i, int j) {
        boolean[][] path = new boolean[minChess.length][minChess[0].length];
        int result = minPath2(minChess, i, j, path);
        if (result >= 100)
            return -1;
        else
            return result;
    }


    private static int minPath2(char[][] minChess, int i, int j, boolean[][] path) {
        if (i < 0 || i >= minChess.length || j < 0 || j >= minChess[0].length || path[i][j] == true)
            return 100;
        if (minChess[i][j] == 'K')
            return 0;
        path[i][j] = true;
        int res = min8Steps(
                minPath2(minChess, i + 2, j + 1, path),
                minPath2(minChess, i + 2, j - 1, path),
                minPath2(minChess, i - 2, j + 1, path),
                minPath2(minChess, i - 2, j - 1, path),
                minPath2(minChess, i + 1, j - 2, path),
                minPath2(minChess, i + 1, j + 2, path),
                minPath2(minChess, i - 1, j - 2, path),
                minPath2(minChess, i - 1, j + 2, path)
        );
        path[i][j] = false;
        return 1 + res;
    }

    private static int min8Steps(int num1, int num2, int num3, int num4, int num5, int num6, int num7, int num8) {
        return Math.min(Math.min(Math.min(num1, num2), Math.min(num3, num4)), Math.min(Math.min(num5, num6), Math.min(num7, num8)));
    }

    //2018 - 91B

    /****************Q1****************/

    public static int cheapestRoute(int[] stations) {
        return cheapestRoute(stations, 0, 0);
    }

    private static int cheapestRoute(int[] stations, int i, int sum) {
        if (i == stations.length)
            return sum;
        if (i == stations.length - 1 || i == stations.length - 2)
            return cheapestRoute(stations, i + 1, sum + stations[i]);
        int firstOp = cheapestRoute(stations, i + 1, sum + stations[i]);
        int secOp = cheapestRoute(stations, i + 2, sum + stations[i + 2]);
        return firstOp < secOp ? firstOp : secOp;
    }

    /****************Q2****************/

    public static void sortmod(int[] a, int k) {
        for (int first = 0; first <= k; first++) {
            int begin;
            int temp = 0;
            for (int i = first; i < a.length; i++) {
                if (a[i] % k == 0) {
                    temp = a[first];
                    a[first] = temp;
                    a[i] = temp;
                    first++;
                }
            }
        }
    }


    // 2017 - 85

    /****************Q1****************/

    public static int oneFiveSeven(int n) {
        return oneFiveSeven(n, 0, 0);
    }

    private static int oneFiveSeven(int n, int i, int count) {
        if (i == n)
            return count;
        if (i > n)
            return Integer.MAX_VALUE;
        int a = oneFiveSeven(n, i + 7, count + 1);
        int b = oneFiveSeven(n, i + 5, count + 1);
        int c = oneFiveSeven(n, i + 1, count + 1);
        int min = a;
        if (b < a)
            min = b;
        if (c < b && c < a)
            min = c;
        return min;
    }

    /****************Q2****************/

    public static void printClosest(int a[], int b[], int x) {
        int diff = Integer.MAX_VALUE;
        int resLeftA = 0;
        int resRightB = 0;
        int left = 0;
        int right = b.length - 1;
        while (left < right && right >= 0) {
            if (Math.abs(a[left] + b[right] - x) < diff) {
                resLeftA = left;
                resRightB = right;
                diff = Math.abs(a[left] + b[right] - x);
            }

            if (a[left] + b[right] > x)
                right--;
            else
                left++;

        }
        System.out.print(("The closest pair is [" + a[resLeftA] +
                ", " + b[resRightB] + "]"));
    }


    // 2017 - 90
    /****************Q1****************/
//    public static int knapSack(item[] items, int w)
//    {
//        return knapSack(items, 0,0);
//    }
//
//    private static int knapSack(item[] items, int w, int value, int i)
//    {
//        if(w == 0)
//            return value;
//        if (w < 0)
//            return value-items[i-1.getValue();
//
//        int k1 = knapSack(items, w-items[i].getWieght(), value, i +1);
//        int k2 = knapSack(items, w, value-items[i].getValue(), i + 1);
//        return Math.max(k1, k2);
//    }


    /****************Q2****************/
    public static int findSmallest(int[] arr) {
        int res = 1;
        for (int i = 0; i < arr.length && arr[i] <= res; i++)
            res = res + arr[i];
        return res;
    }

    // 2017 - 84

    /****************Q1****************/
    public static int ways(int k, int n) {
        if (n == 0 && k == 0)
            return 1;
        if ((n > k) || (k == 0))
            return 0;
        return ways(k - 1, n - 1) + ways(k - 1, n + 1);
    }

   //O(n) O(1)
    public static boolean sum2(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            if (arr[low] + arr[high] == num) {
                return true;
            } else if (arr[low] + arr[high] > num) {
                high--;
            } else {
                low++;
            }
        }
        return false;
    }

    public static boolean sum3(int[] arr, int num) {
        for (int i = 0; i < (arr.length - 1); i++) {
            if (sum2(arr, num - arr[i]))
                return true;
        }
        return false;
    }

    /****************Q2****************/

    public static boolean isPowerOf3(double num) {
        return isPowerOf3(num, 0, 1);
    }

    private static boolean isPowerOf3(double num, double sum, double power) {
        if (sum == num)
            return true;
        double self = Math.pow(3, power);
        if ((sum > num) || (self > num))
            return false;
        return isPowerOf3(num, sum + self, power + 1) || isPowerOf3(num, sum, power + 1);
    }

    public static int avgDiff(int[] a)
    {
        double sumLeft = 0;
        double sumRight = 0;
        int iMaxDiff = -1;
        double maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            sumRight += a[i];
        }
        for (int i = 0; i < a.length - 1; i++) {
            sumLeft += a[i];
            sumRight -= a[i];
            double avgLeft = sumLeft / (i + 1);
            double avgRight = sumRight / (a.length - 1 - i);
            double diff = avgLeft - avgRight;
            if (diff > maxDiff) {
                maxDiff = diff;
                iMaxDiff = i;
            }
        }
        return iMaxDiff;
    }

    public static int makeSum4(int[] lengths, int k, int num) {
        return makeSum4(lengths, k, num, 0, 0, 0);
    }

    private static int makeSum4(int[] lengths, int k, int num, int lengthsI, int sum, int totalSticks) {
        if (lengthsI == lengths.length)
            return 0;
        if (sum > k || totalSticks > num)
            return 0;
        if (sum == k)
            return 1;
        return makeSum4(lengths, k, num, lengthsI + 1, sum, totalSticks) +
                makeSum4(lengths, k, num, lengthsI, sum + lengths[lengthsI], totalSticks + 1);
    }

//    public static int makeSum4(int[] lengths, int k, int num)
//    {
//        return makeSum4(lengths, k, num, 0);
//    }
//
//    private static int makeSum4(int [] lengths, int k, int num, int lengthsI)
//    {
//        if (lengthsI == lengths.length)
//            return 0;
//        if (k < 0 || num < 0)
//            return 0;
//        if (k == 0)
//            return 1;
//        return makeSum4(lengths, k, num, lengthsI + 1) +
//                makeSum4(lengths, k - lengths[lengthsI], num - 1, lengthsI);
//    }


    public static int longestPalindrome (int [] arr)
    {
        return longestPalindrome (arr, 0, 0);
    }

    private static int longestPalindrome (int [] arr, int left, int right)
    {
        if (right > arr.length - 1)
            return 0;

        int self = palindromeLength(arr, left, right, 0);
        return max(self, longestPalindrome(arr, left, right + 1) ,longestPalindrome(arr, right, right + 1));
    }

    private static int palindromeLength (int [] arr, int left, int right, int i)
    {
        if ((left + i) > (left + right)/2) //half palindrome
        {
            int length = right - left + 1;
            return length;
        }
        if (arr[left + i] != arr[right - i])
            return 0;
        return palindromeLength(arr, left, right, i + 1);
    }

    private static int max (int num1, int num2, int num3)
    {
        int tmp = (num1 > num2) ? num1 : num2;
        return (tmp > num3) ? tmp : num3;
    }
}





