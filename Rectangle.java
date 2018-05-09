/**
 *  In The Name of ALLAH
 *
 *  Written by: Mortaza Seydi - Zanjan University - Winter 2018
 *
 *  Assignment 2
 *  Exercise 4
 *
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Rectangle
{
    private int[] x = new int[2];
    private int[] y = new int[2];

    private int length, width;
    private String point1, point2;

    private ArrayList<int[]> innerCoor = new ArrayList<>();
    private ArrayList<int[]> outerCoor = new ArrayList<>();


    public Rectangle(String point1, String point2)
    {
        this.point1 = point1;
        this.point2 = point2;
    }

    public boolean check ()
    {
        String[] a = point1.split(",");
        String[] b = point2.split(",");

        x[0] = Integer.parseInt(a[0]);
        x[1] = Integer.parseInt(b[0]);

        y[0] = Integer.parseInt(a[1]);
        y[1] = Integer.parseInt(b[1]);

        if (x[0] == x[1] || y[0] == y[1])
            return false;

        else
        {
            length = Math.abs(x[1] - x[0]);
            width  = Math.abs(y[1] - y[0]);

            Arrays.sort(x);
            Arrays.sort(y);

            for (int i = 1; i < length; i++)
            {
                for (int j = 1; j < width; j++)
                {
                    int[] newCoor = new int[2];
                    newCoor[0] = x[0] + i;
                    newCoor[1] = y[0] + j;
                    innerCoor.add(newCoor);
                }
            }

            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j <= width; j++)
                {
                    int[] newCoor = new int[2];
                    newCoor[0] = x[0] + (i*length);
                    newCoor[1] = y[0] + j;
                    outerCoor.add(newCoor);
                }
            }

            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j <= length; j++)
                {
                    int[] newCoor = new int[2];
                    newCoor[0] = x[0] + j;
                    newCoor[1] = y[0] + (i*width);
                    outerCoor.add(newCoor);
                }
            }

            return true;
        }
    }

    public ArrayList<int[]> getInnerCoor()
    {
        return innerCoor;
    }

    public ArrayList<int[]> getOuterCoor()
    {
        return outerCoor;
    }

    public int s ()
    {
        return length*width;
    }

    public int p ()
    {
        return (length+width)*2;
    }

    public boolean hasIntersection (Rectangle rectangle)
    {
        for (int i = 0; i < innerCoor.size(); i++)
        {
            for (int j = 0; j < rectangle.getOuterCoor().size(); j++)
            {
                if (rectangle.getOuterCoor().get(j)[0] == innerCoor.get(i)[0] && rectangle.getOuterCoor().get(j)[1] == innerCoor.get(i)[1])
                   return true;
            }
        }

        for (int i = 0; i < rectangle.getInnerCoor().size(); i++)
        {
            for (int j = 0; j < innerCoor.size(); j++)
            {
                if (rectangle.getInnerCoor().get(i)[0] == innerCoor.get(j)[0] && rectangle.getInnerCoor().get(i)[1] == innerCoor.get(j)[1])
                    return true;
            }
        }


        return false;
    }

    public Rectangle getCommonRectangle (Rectangle rectangle)
    {
        if (hasIntersection(rectangle))
        {
            ArrayList<int[]> point = new ArrayList<>();

            for (int i = 0; i < innerCoor.size(); i++) {
                for (int j = 0; j < rectangle.getOuterCoor().size(); j++) {
                    if (rectangle.getOuterCoor().get(j)[0] == innerCoor.get(i)[0] && rectangle.getOuterCoor().get(j)[1] == innerCoor.get(i)[1]) {
                        int[] point1 = new int[2];
                        point1[0] = rectangle.getOuterCoor().get(j)[0];
                        point1[1] = rectangle.getOuterCoor().get(j)[1];
                        point.add(point1);
                    }
                }
            }

            for (int i = 0; i < rectangle.getInnerCoor().size(); i++) {
                for (int j = 0; j < outerCoor.size(); j++) {
                    if (rectangle.getInnerCoor().get(i)[0] == outerCoor.get(j)[0] && rectangle.getInnerCoor().get(i)[1] == outerCoor.get(j)[1]) {
                        int[] point2 = new int[2];
                        point2[0] = rectangle.getInnerCoor().get(i)[0];
                        point2[1] = rectangle.getInnerCoor().get(i)[1];
                        point.add(point2);
                    }

                }
            }

            int[] allX = new int[point.size()];
            int[] allY = new int[point.size()];

            for (int i = 0; i < point.size(); i++) {
                allX[i] = point.get(i)[0];
                allY[i] = point.get(i)[1];
            }

            Arrays.sort(allX);
            Arrays.sort(allY);

            String p1 = Integer.toString(allX[0]) + "," + Integer.toString(allY[0]);
            String p2 = Integer.toString(allX[point.size() - 1]) + "," + Integer.toString(allY[point.size() - 1]);

            Rectangle newRec = new Rectangle(p1, p2);
            if (newRec.check())
                return newRec;

            else
                return null;
        }

        else
            return null;

    }

    public boolean contains (Rectangle rectangle)
    {
        int counter = 0;

        for (int i = 0; i < innerCoor.size(); i++)
        {
            for (int j = 0; j < rectangle.getInnerCoor().size(); j++)
            {
                if (rectangle.getInnerCoor().get(j)[0] == innerCoor.get(i)[0] && rectangle.getInnerCoor().get(j)[1] == innerCoor.get(i)[1])
                    counter++;
            }
        }

        if (counter == innerCoor.size())
            return true;

        else if (counter == rectangle.getInnerCoor().size())
            return true;

        return false;

    }

    public void draw (String sharp)
    {

        for (int i=0; i< length +1; i++)
        {
            System.out.print(sharp);
            System.out.print(" ");
        }

        System.out.println();

        for (int j=0; j< width -1; j++)
        {
            System.out.print(sharp);
            System.out.print(" ");

            for (int z = 0; z< length -1; z++)
                System.out.print("  ");

            System.out.print(sharp);
            System.out.print(" ");
            System.out.println();
        }

        for (int i=0; i< length +1; i++)
        {
            System.out.print(sharp);
            System.out.print(" ");
        }
    }


}
