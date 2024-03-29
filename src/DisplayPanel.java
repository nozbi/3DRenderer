import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class DisplayPanel extends JPanel
{
    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;

    private Shape shape;
    private double observerDistance;
    private ProjectionMode projectionMode;
    private ViewMode viewMode;

    public DisplayPanel()
    {
        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setBackground(Color.WHITE);
    }
    
    @Override
    public void paint(Graphics graphicsParameter) 
    {
        super.paint(graphicsParameter);

        if(this.shape != null)
        {
            this.shape.draw(graphicsParameter, this.WIDTH, this.HEIGHT, this.observerDistance, this.projectionMode, this.viewMode);
        }
    }

    public void loadShape(Shape shapeParameter)
    {
        this.shape = shapeParameter;
        this.repaint();
    }

    public void moveShape(Vector moveVectorParameter)
    {
        if(this.shape != null)
        {
            this.shape.move(moveVectorParameter);
            this.repaint();
        }
    }

    public void rotateShape(Vector rotateVectorParameter)
    {
        if(this.shape != null)
        {
            this.shape.rotate(rotateVectorParameter);
            this.repaint();
        }
    }

    public void scaleShape(Vector scaleVectorParameter)
    {
        if(this.shape != null)
        {
            this.shape.scale(scaleVectorParameter);
            this.repaint();
        }
    }

    public void resetShape()
    {
        if(this.shape != null)
        {
            this.shape.reset();
            this.repaint();
        }
    }

    public void changeObserverDistance(double observerDistanceParameter)
    {
        this.observerDistance = observerDistanceParameter;
        this.repaint();
    }

    public Matrix getMatrix()
    {
        if(this.shape != null)
        {
            return this.shape.getMatrix();
        }
        else
        {
            return new Matrix();
        }
    }

    public void changeProjectionMode(ProjectionMode projectionModeParameter)
    {
        this.projectionMode = projectionModeParameter;
         this.repaint();
    }

    public void changeViewMode(ViewMode viewModeParameter)
    {
        this.viewMode = viewModeParameter;
        this.repaint();
    }
}
