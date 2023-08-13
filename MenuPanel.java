import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuPanel extends JPanel
{
    private final int WIDTH = 500;
    private final int HEIGHT = 1000;

    private DisplayPanel displayPanel;

    public MenuPanel(DisplayPanel displayPanelParameter)
    {
        this.displayPanel = displayPanelParameter;

        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setLayout(new GridLayout(0, 1));

        JButton loadShapeButton = new JButton("Load Shape");
        this.add(loadShapeButton);

        JButton moveShapeButton = new JButton("Move Shape");
        this.add(moveShapeButton);

        JButton rotateShapeButton = new JButton("Rotate Shape");
        this.add(rotateShapeButton);

        JButton scaleShapeButton = new JButton("Scale Shape");
        this.add(scaleShapeButton);

        JButton resetShapeButton = new JButton("Reset Shape");
        this.add(resetShapeButton);
        
        loadShapeButton.addActionListener(actionEvent -> {this.onLoadShapeButtonClicked();});
        moveShapeButton.addActionListener(actionEvent -> {this.onMoveShapeButtonClicked();});
        rotateShapeButton.addActionListener(actionEvent -> {this.onRotateShapeButtonClicked();});
        scaleShapeButton.addActionListener(actionEvent -> {this.onScaleShapeButtonClicked();});
        resetShapeButton.addActionListener(actionEvent -> {this.onResetShapeButtonClicked();});
    }

    private void onLoadShapeButtonClicked()
    {
        //
        Vertex3D[] vertices3D = 
        {
           new Vertex3D(-100, 100, 100),
           new Vertex3D(100, 100, 100),
           new Vertex3D(100, -100, 100),
           new Vertex3D(-100, -100, 100),
           new Vertex3D(-100, 100, -100),
           new Vertex3D(100, 100, -100),
           new Vertex3D(100, -100, -100),
           new Vertex3D(-100, -100, -100)
        };

        Edge[] edges = 
        {
            new Edge(0, 1),
            new Edge(1, 2),
            new Edge(2, 3),
            new Edge(3, 0),
            new Edge(4, 5),
            new Edge(5, 6),
            new Edge(6, 7),
            new Edge(7, 4),
            new Edge(0, 4),
            new Edge(1, 5),
            new Edge(2, 6),
            new Edge(3, 7)
        };
        //

        this.displayPanel.loadShape(vertices3D, edges);
    }

    private void onMoveShapeButtonClicked()
    {
        this.displayPanel.moveShape(100, 0, 0);
    }

    private void onRotateShapeButtonClicked()
    {
        this.displayPanel.rotateShape(30, 0, 0);
    }

    private void onScaleShapeButtonClicked()
    {
        this.displayPanel.scaleShape(2, 0, 0);
    }

    private void onResetShapeButtonClicked()
    {
        this.displayPanel.resetShape();
    }
}
