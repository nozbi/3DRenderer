public class Vector 
{
    private double x;
    private double y;
    private double z;

    public Vector(double xParameter, double yParameter, double zParameter)
    {
        this.x = xParameter;
        this.y = yParameter;
        this.z = zParameter;
    }

    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public double getZ()
    {
        return this.z;
    }
}
