public class Vertex 
{
    private double x;
    private double y;
    private double z;

    public Vertex(double xParameter, double yParameter, double zParameter)
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

    public void move(double xParameter, double yParameter,double zParameter)
    {
        this.x += xParameter;
        this.y += yParameter;
        this.z += zParameter;
    }

    public void scale(double xParameter, double yParameter,double zParameter)
    {
        this.x *= xParameter;
        this.y *= yParameter;
        this.z *= zParameter;
    }

    public void rotate(Vector rotateVectorParameter)
    {
        this.rotateX(this.convertDegreesToRadians(rotateVectorParameter.getX()));
        this.rotateY(this.convertDegreesToRadians(rotateVectorParameter.getY()));
        this.rotateZ(this.convertDegreesToRadians(rotateVectorParameter.getZ()));
    }

    private void rotateX(double angleParameter)
    {
        double newX = this.x;
        double newY = (this.y * Math.cos(angleParameter)) - (this.z * Math.sin(angleParameter));
        double newZ = (this.y * Math.sin(angleParameter)) + (this.z * Math.cos(angleParameter));

        this.x = newX;
        this.y = newY;
        this.z = newZ;
    }

    private void rotateY(double angleParameter)
    {
        double newX = (this.z * Math.sin(angleParameter)) + (this.x * Math.cos(angleParameter));
        double newY = this.y;
        double newZ = (this.z * Math.cos(angleParameter)) - (this.x * Math.sin(angleParameter));

        this.x = newX;
        this.y = newY;
        this.z = newZ;
    }

    private void rotateZ(double angleParameter)
    {
        double newX = (this.x * Math.cos(angleParameter)) - (this.y * Math.sin(angleParameter));
        double newY = (this.x * Math.sin(angleParameter)) + (this.y * Math.cos(angleParameter));
        double newZ = this.z;

        this.x = newX;
        this.y = newY;
        this.z = newZ;
    }

    private double convertDegreesToRadians(double angleParameter)
    {
        return (angleParameter * Math.PI) / 180;
    }
}
