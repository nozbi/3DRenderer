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

    public DisplayPanel()
    {
        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setBackground(Color.PINK);
    }
    
    @Override
    public void paint(Graphics graphicsParameter) 
    {
        super.paint(graphicsParameter);

        if(this.shape != null)
        {
            this.shape.draw(graphicsParameter, this.observerDistance, this.WIDTH, this.HEIGHT);
        }
    }

    public void loadShape(Vertex3D[] vertices3DParameter, Edge[] edgesParameter)
    {
        this.shape = new Shape(vertices3DParameter, edgesParameter);
        this.repaint();
    }

    public void moveShape(Vector moveVectorParameter)
    {
        this.shape.move(moveVectorParameter);
        this.repaint();
    }

    public void rotateShape(Vector rotateVectorParameter)
    {
        this.shape.rotate(rotateVectorParameter);
        this.repaint();
    }

    public void scaleShape(Vector scaleVectorParameter)
    {
        this.shape.scale(scaleVectorParameter);
        this.repaint();
    }

    public void resetShape()
    {
        this.shape.reset();
        this.repaint();
    }

    public void changeObserverDistance(double observerDistanceParameter)
    {
        this.observerDistance = observerDistanceParameter;
        this.repaint();
    }

    public Matrix getMatrix()
    {
        return this.shape.getMatrix();
    }
}
