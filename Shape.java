import java.awt.Graphics;


public class Shape 
{
    private Vertex3D[] originalVertices;
    private Vertex3D[] vertices;
    private Edge[] edges;

    public Shape(Vertex3D[] vertices3DParameter, Edge[] edgesParameter)
    {
        this.originalVertices = vertices3DParameter;
        this.vertices = vertices3DParameter;
        this.edges = edgesParameter;
    }

    private Vertex2D convertVertexFrom3DTo2D(Vertex3D vertex3DParameter, int viewerDistanceParameter, int viewWidthParameter, int viewHeightParameter)
    {
        int d = viewerDistanceParameter;
        int x = (int)((viewWidthParameter / 2) + ((d * vertex3DParameter.getX()) / (vertex3DParameter.getZ() + d)));
        int y = (int)((viewHeightParameter / 2) + ((d * vertex3DParameter.getY()) / (vertex3DParameter.getZ() + d)));

        

        return new Vertex2D(x, y);
    }

    public void draw(Graphics graphicsParameter, int viewerDistanceParameter, int viewWidthParameter, int viewHeightParameter)
    {
        Vertex2D vertices2D[] = new Vertex2D[vertices.length];
        for(int i = 0; i < vertices2D.length; i++)
        {
            vertices2D[i] = this.convertVertexFrom3DTo2D(vertices[i], viewerDistanceParameter, viewWidthParameter, viewHeightParameter);
        }

        for(int i = 0; i < edges.length; i++)
        {
            Vertex2D start = vertices2D[edges[i].getStart()];
            Vertex2D end = vertices2D[edges[i].getEnd()];
            graphicsParameter.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
        }
    }

    public void move(double xParameter, double yParameter,double zParameter)
    {
        for(int i = 0; i < this.vertices.length; i++)
        {
            this.vertices[i] = new Vertex3D(this.vertices[i].getX() + xParameter, this.vertices[i].getY() + yParameter, this.vertices[i].getZ() + zParameter);
        }
    }

    public void rotate(double xParameter, double yParameter,double zParameter)
    {

    }

    public void scale(double xParameter, double yParameter,double zParameter)
    {

    }

    public void reset()
    {
        this.vertices = this.originalVertices;
    }
}
