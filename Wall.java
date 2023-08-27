import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Arrays;


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

    public double getZOrder(Vertex[] verticesParameter)
    {
        double[] verticesZOrders = new double[this.indexesOfVertices.length];
        for(int i = 0; i < verticesZOrders.length; i++)
        {
            verticesZOrders[i] = verticesParameter[this.indexesOfVertices[i]].getZ();
        }

        double wallZOrder = ArrayCalculator.calculateAverageValue(verticesZOrders);
        return wallZOrder;
    }

    public double getXYOrder(Vertex[] verticesParameter)
    {
        double[] xArray = new double[this.indexesOfVertices.length];
        for(int i = 0; i < this.indexesOfVertices.length; i++)
        {
            xArray[i] = verticesParameter[this.indexesOfVertices[i]].getX();
        }
        Arrays.sort(xArray);
        double minX = xArray[0];
        double maxX = xArray[xArray.length - 1];
        double midX = Math.abs((minX + maxX) / 2);

        double[] yArray = new double[this.indexesOfVertices.length];
        for(int i = 0; i < this.indexesOfVertices.length; i++)
        {
            yArray[i] = verticesParameter[this.indexesOfVertices[i]].getY();
        }
        Arrays.sort(yArray);
        double minY = yArray[0];
        double maxY = yArray[yArray.length - 1];
        double midY = Math.abs((minY + maxY) / 2);

        if(midX < midY)
        {
            return midX;
        }
        else
        {
            return midY;
        }
    }
}
