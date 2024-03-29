import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class MenuPanel extends JPanel
{
    private final int WIDTH = 333;
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

        JPanel settingsPanel = new JPanel(new GridLayout(0, 1));
        this.add(settingsPanel);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 0));
        settingsPanel.add(buttonsPanel);

        JButton loadShapeButton = new JButton("Load Shape");
        loadShapeButton.addActionListener(actionEvent -> {this.onLoadShapeButtonClicked();});
        buttonsPanel.add(loadShapeButton);

        JButton resetShapeButton = new JButton("Reset Shape");
        resetShapeButton.addActionListener(actionEvent -> {this.onResetShapeButtonClicked();});
        buttonsPanel.add(resetShapeButton);

        JPanel observerDistancePanel = new JPanel(new GridLayout(1, 0));
        settingsPanel.add(observerDistancePanel);

        JLabel observerDistanceLabel = new JLabel("Distance:");
        observerDistancePanel.add(observerDistanceLabel);

        ValueInputField observerDistanceValueInputField = new ValueInputField(this.DEFAULT_OBSERVER_DISTANCE);
        observerDistanceValueInputField.addActionListener(actionEvent -> {this.onObserverDistanceValueInputFieldChanged(observerDistanceValueInputField);});
        observerDistancePanel.add(observerDistanceValueInputField);

        JPanel projectionModePanel = new JPanel(new GridLayout(1, 0));
        settingsPanel.add(projectionModePanel);

        JLabel projectionModeLabel = new JLabel("Projection:");
        projectionModePanel.add(projectionModeLabel);

        JComboBox<ProjectionMode> projectionModeComboBox = new JComboBox<ProjectionMode>(ProjectionMode.values());
        projectionModeComboBox.addActionListener(actionEvent -> {this.onProjectionModeComboBoxChanged(projectionModeComboBox);});
        projectionModePanel.add(projectionModeComboBox);

        JPanel viewModePanel = new JPanel(new GridLayout(1, 0));
        settingsPanel.add(viewModePanel);

        JLabel viewModeLabel = new JLabel("View:");
        viewModePanel.add(viewModeLabel);

        JComboBox<ViewMode> viewModeComboBox = new JComboBox<ViewMode>(ViewMode.values());
        viewModeComboBox.addActionListener(actionEvent -> {this.onViewModeComboBoxChanged(viewModeComboBox);});
        viewModePanel.add(viewModeComboBox);

        JPanel transformPanel = new JPanel(new GridLayout(0, 1));
        this.add(transformPanel);

        VectorInputPanel moveShapeVectorInputPanel = new VectorInputPanel(this.DEFAULT_MOVE_VECTOR);
        transformPanel.add(moveShapeVectorInputPanel);

        JButton moveShapeButton = new JButton("Move Shape");
        moveShapeButton.addActionListener(actionEvent -> {this.onMoveShapeButtonClicked(moveShapeVectorInputPanel);});
        transformPanel.add(moveShapeButton);

        VectorInputPanel rotateShapeVectorInputPanel = new VectorInputPanel(this.DEFAULT_ROTATE_VECTOR);
        transformPanel.add(rotateShapeVectorInputPanel);

        JButton rotateShapeButton = new JButton("Rotate Shape");
        rotateShapeButton.addActionListener(actionEvent -> {this.onRotateShapeButtonClicked(rotateShapeVectorInputPanel);});
        transformPanel.add(rotateShapeButton);

        VectorInputPanel scaleShapeVectorInputPanel = new VectorInputPanel(this.DEFAULT_SCALE_VECTOR);
        transformPanel.add(scaleShapeVectorInputPanel);

        JButton scaleShapeButton = new JButton("Scale Shape");
        scaleShapeButton.addActionListener(actionEvent -> {this.onScaleShapeButtonClicked(scaleShapeVectorInputPanel);});
        transformPanel.add(scaleShapeButton);

        this.matrixPanel = new MatrixPanel();
        this.add(this.matrixPanel);

        this.displayPanel.changeObserverDistance(this.DEFAULT_OBSERVER_DISTANCE);
        this.onObserverDistanceValueInputFieldChanged(observerDistanceValueInputField);
        this.onProjectionModeComboBoxChanged(projectionModeComboBox);
        this.onViewModeComboBoxChanged(viewModeComboBox);
        this.updateMatrixPanel();
    }

    private void onLoadShapeButtonClicked()
    {
        ShapeFileReader shapeFileReader = new ShapeFileReader(this);
        if(shapeFileReader.isFileGood())
        {
            Vertex[] vertices = shapeFileReader.getVertices();
            Wall[] walls =  shapeFileReader.getWalls();
            this.displayPanel.loadShape(new Shape(vertices, walls));
            this.updateMatrixPanel();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "INCORRECT FILE FORMAT");
        }
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

    private void onProjectionModeComboBoxChanged(JComboBox<ProjectionMode> projectionModeComboBoxParameter)
    {
        ProjectionMode projectionMode = (ProjectionMode)projectionModeComboBoxParameter.getSelectedItem();
        this.displayPanel.changeProjectionMode(projectionMode);
    }

    private void onViewModeComboBoxChanged(JComboBox<ViewMode> viewModeComboBoxParameter)
    {
        ViewMode viewMode = (ViewMode)viewModeComboBoxParameter.getSelectedItem();
        this.displayPanel.changeViewMode(viewMode);
    }
}    
