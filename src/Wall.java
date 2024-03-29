import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;


public class Wall 
{
    private int[] indexesOfVertices;
    private Color color;

    public Wall(int[] indexesOfVerticesParameter)
    {
        this.indexesOfVertices = indexesOfVerticesParameter;
        this.color = RandomColorGenerator.generateRandomColor();
    }

    public void draw(Graphics graphicsParameter, Point2D[] points2DParameter, ViewMode viewModeParameter)
    {
        Polygon polygon = new Polygon();
        for(int i = 0; i < this.indexesOfVertices.length; i++)
        {
            polygon.addPoint(points2DParameter[this.indexesOfVertices[i]].getX(), points2DParameter[this.indexesOfVertices[i]].getY());
        }
        
        Graphics2D graphics2D = (Graphics2D)graphicsParameter;
        switch(viewModeParameter)
        {
            case EDGES:
                graphics2D.draw(polygon);
                break;
            case ONLY_VISIBLE_EDGES:
                graphics2D.setColor(Color.WHITE);   
                graphics2D.fillPolygon(polygon);
                graphics2D.setColor(Color.BLACK); 
                graphics2D.draw(polygon);
                break;
            case WALLS:
                graphics2D.fillPolygon(polygon);
                break;
            case MULTICOLOR_WALLS: 
                graphics2D.setColor(this.color);    
                graphics2D.fillPolygon(polygon);
                break;
        }
    }

    public double getDistance(Vertex[] verticesParameter, double observerDistanceParameter)
    {
        double sum = 0;
        for(int i = 0; i < this.indexesOfVertices.length; i++)
        {
            double x = verticesParameter[this.indexesOfVertices[i]].getX();
            double y = verticesParameter[this.indexesOfVertices[i]].getY();
            double z = verticesParameter[this.indexesOfVertices[i]].getZ();

            Vector startVector = new Vector(0, 0, -observerDistanceParameter);
            Vector endVector = new Vector(x, y, z);

            sum += DistanceCalculator.calculate(startVector, endVector);
        }

        return sum / this.indexesOfVertices.length;
    }
}
