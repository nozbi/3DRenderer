import java.awt.Graphics;


public class Shape 
{
    private Vertex3D[] originalVertices;
    private Vertex3D[] vertices;
    private Edge[] edges;
    private Matrix matrix;

    public Shape(Vertex3D[] verticesParameter, Edge[] edgesParameter)
    {
        this.edges = edgesParameter;
        this.vertices = this.copyVertex3DArray(verticesParameter);
        this.originalVertices = this.copyVertex3DArray(verticesParameter);

        this.matrix = new Matrix();
    }

    private Vertex2D convertVertexFrom3DTo2D(Vertex3D vertex3DParameter, double viewerDistanceParameter, int viewWidthParameter, int viewHeightParameter)
    {
        double d = viewerDistanceParameter;
        int x = (int)((viewWidthParameter / 2) + ((d * vertex3DParameter.getX()) / (vertex3DParameter.getZ() + d)));
        int y = (int)((viewHeightParameter / 2) + ((d * vertex3DParameter.getY()) / (vertex3DParameter.getZ() + d)));
        return new Vertex2D(x, y);
    }

    public void draw(Graphics graphicsParameter, double viewerDistanceParameter, int viewWidthParameter, int viewHeightParameter)
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

    public void move(Vector moveVectorParameter)
    {
        this.matrix.move(moveVectorParameter);
        this.updateVertices();
    }

    public void rotate(Vector rotateVectorParameter)
    {
        this.matrix.rotate(rotateVectorParameter);
        this.updateVertices();
    }

    public void scale(Vector scaleVectorParameter)
    {
        this.matrix.scale(scaleVectorParameter);
        this.updateVertices();
    }

    public void reset()
    {
        this.vertices = 
        this.copyVertex3DArray(this.originalVertices);

        this.matrix = new Matrix();
    }

    private Vertex3D[] copyVertex3DArray(Vertex3D[] vertex3DArrayParameter)
    {
        Vertex3D[] vertices = new Vertex3D[vertex3DArrayParameter.length];
        for(int i = 0; i < vertex3DArrayParameter.length; i++)
        {
            vertices[i] = new Vertex3D(vertex3DArrayParameter[i].getX(), vertex3DArrayParameter[i].getY(), vertex3DArrayParameter[i].getZ());
        }
        return vertices;
    }

    public Matrix getMatrix()
    {
        return this.matrix;
    }

    private void updateVertices()
    {
        for(int i = 0; i < this.vertices.length; i++)
        {
            this.vertices[i] = this.matrix.calculateNewVertex(this.originalVertices[i]);
        }
    }
}
