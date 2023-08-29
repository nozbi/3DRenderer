public class DistanceCalculator 
{
    public static double calculate(Vector startVectorParameter, Vector endVectorParameter)
    {
        double x1 = startVectorParameter.getX();
        double y1 = startVectorParameter.getY();
        double z1 = startVectorParameter.getZ();
        double x2 = endVectorParameter.getX();
        double y2 = endVectorParameter.getY();
        double z2 = endVectorParameter.getZ();
        double distance = Math.pow((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2) * 1.0), 0.5);
        return distance;
    }
}
