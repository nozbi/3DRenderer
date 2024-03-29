public class ArrayCalculator 
{
    public static double calculateAverageValue(double[] arrayParameter)
    {
        double sum = 0;
        for(int i = 0; i < arrayParameter.length; i++)
        {
            sum += arrayParameter[i];
        }

        return sum / arrayParameter.length;
    }
}
