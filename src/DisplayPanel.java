import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class DisplayPanel extends JPanel
{
    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;

    private Shape shape;

    public DisplayPanel()
    {
        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setBackground(Color.PINK);
    }
    
    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);

        if(this.shape != null)
        {
            this.shape.draw(g, 1000, this.WIDTH, this.HEIGHT);
        }
    }

    public void loadShape(Vertex3D[] vertices3DParameter, Edge[] edgesParameter)
    {
        this.shape = new Shape(vertices3DParameter, edgesParameter);
        this.repaint();
    }

    public void moveShape(double xParameter, double yParameter, double zParameter)
    {
        this.shape.move(xParameter, yParameter, zParameter);
        this.repaint();
    }

    public void rotateShape(double xParameter, double yParameter, double zParameter)
    {
        this.shape.rotate(xParameter, yParameter, zParameter);
        this.repaint();
    }

    public void scaleShape(double xParameter, double yParameter, double zParameter)
    {
        this.shape.scale(xParameter, yParameter, zParameter);
        this.repaint();
    }

    public void resetShape()
    {
        this.shape.reset();
        this.repaint();
    }
}
