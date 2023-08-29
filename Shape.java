import java.awt.Graphics;


public class Shape 
{
    private Vertex[] originalVertices;
    private Vertex[] vertices;
    private Wall[] walls;
    private Matrix matrix;

    public Shape(Vertex[] verticesParameter, Wall[] wallsParameter)
    {
        this.walls = wallsParameter;
        this.vertices = this.copyVertex3DArray(verticesParameter);
        this.originalVertices = this.copyVertex3DArray(verticesParameter);

        this.matrix = new Matrix();
    }

    private Point2D convertVertexFrom3DTo2DPerspective(Vertex vertex3DParameter, int viewWidthParameter, int viewHeightParameter, double observerDistanceParameter)
    {
        double d = observerDistanceParameter;
        int x = (int)((viewWidthParameter / 2) + ((d * vertex3DParameter.getX()) / (vertex3DParameter.getZ() + d)));
        int y = (int)((viewHeightParameter / 2) + ((d * vertex3DParameter.getY()) / (vertex3DParameter.getZ() + d)));
        return new Point2D(x, y);
    }

    private Point2D convertVertexFrom3DTo2DOrthographic(Vertex vertex3DParameter, int viewWidthParameter, int viewHeightParameter)
    {
        int x = (int)((viewWidthParameter / 2) + vertex3DParameter.getX());
        int y = (int)((viewHeightParameter / 2) + vertex3DParameter.getY());
        return new Point2D(x, y);
    }

    public void draw(Graphics graphicsParameter, int viewWidthParameter, int viewHeightParameter, double observerDistanceParameter, ProjectionMode projectionModeParameter, ViewMode viewModeParameter)
    {
        Point2D points2D[] = new Point2D[vertices.length];

        switch(projectionModeParameter)
        {
            case PERSPECTIVE:
                for(int i = 0; i < points2D.length; i++)
                {
                    points2D[i] = this.convertVertexFrom3DTo2DPerspective(vertices[i], viewWidthParameter, viewHeightParameter, observerDistanceParameter);
                }
                break;
            case ORTHOGRAPHIC:
                for(int i = 0; i < points2D.length; i++)
                {
                    points2D[i] = this.convertVertexFrom3DTo2DOrthographic(vertices[i], viewWidthParameter, viewHeightParameter);
                }
                break;
        }

        this.walls = WallsSorter.sort(this.walls, this.vertices, observerDistanceParameter);
        for(int i = 0; i < this.walls.length; i++)
        {
            this.walls[i].draw(graphicsParameter, points2D, viewModeParameter);
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

    private Vertex[] copyVertex3DArray(Vertex[] vertex3DArrayParameter)
    {
        Vertex[] vertices = new Vertex[vertex3DArrayParameter.length];
        for(int i = 0; i < vertex3DArrayParameter.length; i++)
        {
            vertices[i] = new Vertex(vertex3DArrayParameter[i].getX(), vertex3DArrayParameter[i].getY(), vertex3DArrayParameter[i].getZ());
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
