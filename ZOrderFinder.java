import java.util.Arrays;

public class ZOrderFinder 
{
    public static double getClosestZOrder(double[] zOrdersParameter)
    {
        Arrays.sort(zOrdersParameter);
        return zOrdersParameter[zOrdersParameter.length - 1];
    }
}
