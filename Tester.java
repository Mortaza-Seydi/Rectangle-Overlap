/**
 *  In The Name of ALLAH
 *
 *  Written by: Mortaza Seydi - Zanjan University - Winter 2018
 *
 */

public class Tester
{
    public static void main (String[] args)
    {
        Rectangle rec1 = new Rectangle("0,0", "8,8");
        if (!rec1.check())
        {
            System.out.println("This Points Does Not Make Rectangle");
            System.exit(0);
        }


        Rectangle rec2 = new Rectangle("1,1", "10,6");
        if(!rec2.check())
        {
            System.out.println("This Points Does Not Make Rectangle");
            System.exit(0);
        }


        if (rec1.hasIntersection(rec2))
            System.out.println("\nyes they have Intersection\n");
        else
            System.out.println("\nno they do not have Intersection\n");


        Rectangle newRec = rec1.getCommonRectangle(rec2);
        if (newRec != null)
            newRec.draw("*");
        else
            System.out.println("\nError\n");



        if (rec1.contains(rec2))
            System.out.println("\nyes they are contains\n");
        else
            System.out.println("\nyes they are not contains\n");
    }
}
