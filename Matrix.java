/**
*This class represents a matrix. The matrix represents a two-dimensional image in black and white.
 *

 * @author Mai Aloni (ID: 309651107)
 * @version 26-09-2018
 *
 */

public class Matrix
{
    private int[][] _picture;

    /**
     * Constructs a Matrix from a two-dimensional array. The dimensions as well as the values of this Matrix
     * will be the same as the dimensions and values of the two dimensional array.
     *
     * @param array
     */


    public Matrix(int[][] array)
    {
        _picture = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++)
            {
                _picture[i][j] = array[i][j];
            }
        }
    }


    /**
     * Constructs a size1 by size2 Matrix of zeroes
     * @param size1
     * @param size2
     */

    public Matrix(int size1, int size2)
    {
        _picture = new int[size1][size2];
    }


    /**
     * Returns a string representation of this matrix.
     * Overrides toString in class java.lang.Object
     * @return String representation of this matrix
     */

    public String toString()
    {
        String string = "";
        String tab = "\t";
        String newLine = "\n";
        int i, j;
        for (i = 0; i < getRows(); i++)
        {
            for (j = 0; j < getColumns(); j++)
            {
                if (j != (getColumns() - 1))
                {
                    string += _picture[i][j] + tab; //tab will be added to all rows except the last one
                }
                else
                    {
                    string += _picture[i][j];
                    }
            }
            string += newLine;
        }
        return string;
    }


    private int getColumns()
    {
        return _picture[0].length;
    }


    private int getRows()
    {
        return _picture.length;
    }


    /**
     * Flips the picture vertical, so that the first row becomes last, the second becomes the second from the end...
     * @return  Flipped matrix vertical
     */

    public Matrix flipVertical()
    {
        //build a new matrix, to set on it the flip (to not work on the original array)
        Matrix flippedVerticalPic = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++)
        {
            for (int j = 0; j < getColumns(); j++)
            {
                flippedVerticalPic._picture[getRows() - 1 - i][j] = _picture[i][j];
            }
        }
        return flippedVerticalPic;
    }


    /**
     * Flips the picture horizontal, so that the first column becomes last, the second becomes the second from the end...
     * @return Flipped matrix horizontal
     */

    public Matrix flipHorizontal()
    {
        Matrix flippedHorizontalPic = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++)
        {
            for (int j = 0; j < getColumns(); j++)
            {
                flippedHorizontalPic._picture[i][getColumns() - 1 - j] = _picture[i][j];
            }
        }
        return flippedHorizontalPic;
    }


    /**
     * Rotates the image 90 degrees clockwise.
     * @return  rotated matrix by 90 degrees clockwise
     */

    public Matrix rotateClockwise()
    {
        //swap - rows to columns and columns to rows
        Matrix rotatedClockwisePic = new Matrix(getColumns(), getRows());
        for(int i = 0 ; i < rotatedClockwisePic.getRows(); i++)
        {
            for(int j = 0 ; j < rotatedClockwisePic.getColumns(); j++)
            {
                rotatedClockwisePic._picture[i][j] = _picture[getRows() - 1 - j][i];
            }
        }
        return rotatedClockwisePic;
    }


    /**
     * Rotates the image 90 degrees clockwise.
     * @return  rotated matrix by 90 degrees clockwise
     */

    public Matrix  rotateCounterClockwise()
    {
        Matrix rotatedCounterClockPic = new Matrix(getColumns(),getRows());
        for(int i = 0 ; i < rotatedCounterClockPic.getRows(); i++)
        {
            for(int j = 0 ; j < rotatedCounterClockPic.getColumns(); j++)
            {
                rotatedCounterClockPic._picture[i][j] = _picture[j][getColumns() - 1 - i];
            }
        }
        return rotatedCounterClockPic;
    }
}