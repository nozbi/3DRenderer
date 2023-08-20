import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class MenuPanel extends JPanel
{
    private final int WIDTH = 500;
    private final int HEIGHT = 1000;
    private final double DEFAULT_OBSERVER_DISTANCE = 1000;
    private final Vector DEFAULT_MOVE_VECTOR = new Vector(0, 0, 0);
    private final Vector DEFAULT_ROTATE_VECTOR = new Vector(0, 0, 0);
    private final Vector DEFAULT_SCALE_VECTOR = new Vector(1, 1, 1);

    private DisplayPanel displayPanel;
    private MatrixPanel matrixPanel;

    public MenuPanel(DisplayPanel displayPanelParameter)
    {
        super();

        this.displayPanel = displayPanelParameter;

        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setLayout(new GridLayout(0, 1));

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 0));
        this.add(buttonsPanel);

        JButton loadShapeButton = new JButton("Load Shape");
        loadShapeButton.addActionListener(actionEvent -> {this.onLoadShapeButtonClicked();});
        buttonsPanel.add(loadShapeButton);

        JButton resetShapeButton = new JButton("Reset Shape");
        resetShapeButton.addActionListener(actionEvent -> {this.onResetShapeButtonClicked();});
        buttonsPanel.add(resetShapeButton);

        JPanel observerDistancePanel = new JPanel(new GridLayout(1, 0));
        this.add(observerDistancePanel);

        JLabel observerDistanceLabel = new JLabel("Distance:");
        observerDistancePanel.add(observerDistanceLabel);

        ValueInputField observerDistanceValueInputField = new ValueInputField(this.DEFAULT_OBSERVER_DISTANCE);
        observerDistanceValueInputField.addActionListener(actionEvent -> {this.onObserverDistanceValueInputFieldChanged(observerDistanceValueInputField);;});
        observerDistancePanel.add(observerDistanceValueInputField);

        VectorInputPanel moveShapeVectorInputPanel = new VectorInputPanel(this.DEFAULT_MOVE_VECTOR);
        this.add(moveShapeVectorInputPanel);

        JButton moveShapeButton = new JButton("Move Shape");
        moveShapeButton.addActionListener(actionEvent -> {this.onMoveShapeButtonClicked(moveShapeVectorInputPanel);});
        this.add(moveShapeButton);

        VectorInputPanel rotateShapeVectorInputPanel = new VectorInputPanel(this.DEFAULT_ROTATE_VECTOR);
        this.add(rotateShapeVectorInputPanel);

        JButton rotateShapeButton = new JButton("Rotate Shape");
        rotateShapeButton.addActionListener(actionEvent -> {this.onRotateShapeButtonClicked(rotateShapeVectorInputPanel);});
        this.add(rotateShapeButton);

        VectorInputPanel scaleShapeVectorInputPanel = new VectorInputPanel(this.DEFAULT_SCALE_VECTOR);
        this.add(scaleShapeVectorInputPanel);

        JButton scaleShapeButton = new JButton("Scale Shape");
        scaleShapeButton.addActionListener(actionEvent -> {this.onScaleShapeButtonClicked(scaleShapeVectorInputPanel);});
        this.add(scaleShapeButton);

        this.matrixPanel = new MatrixPanel();
        this.add(this.matrixPanel);

        this.displayPanel.changeObserverDistance(this.DEFAULT_OBSERVER_DISTANCE);
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
        this.updateMatrixPanel();
    }

    private void onMoveShapeButtonClicked(VectorInputPanel moveVectorInputPanelParameter)
    {
        this.displayPanel.moveShape(moveVectorInputPanelParameter.getVector());
        this.updateMatrixPanel();
    }

    private void onRotateShapeButtonClicked(VectorInputPanel rotateVectorInputPanelParameter)
    {
        this.displayPanel.rotateShape(rotateVectorInputPanelParameter.getVector());
        this.updateMatrixPanel();
    }

    private void onScaleShapeButtonClicked(VectorInputPanel scaleVectorInputPanelParameter)
    {
        this.displayPanel.scaleShape(scaleVectorInputPanelParameter.getVector());
        this.updateMatrixPanel();
    }

    private void onResetShapeButtonClicked()
    {
        this.displayPanel.resetShape();
        this.updateMatrixPanel();
    }

    private void updateMatrixPanel()
    {
        this.matrixPanel.setMatrix(this.displayPanel.getMatrix());
    }

    private void onObserverDistanceValueInputFieldChanged(ValueInputField observerDistanceValueInputFieldParameter)
    {
        this.displayPanel.changeObserverDistance(observerDistanceValueInputFieldParameter.getValue());
    }
}
