import java.awt.GridLayout;
import javax.swing.JPanel;


public class VectorInputPanel extends JPanel
{
    private ValueInputField xValueInputField;
    private ValueInputField yValueInputField;
    private ValueInputField zValueInputField;


    public VectorInputPanel(Vector defaultVectorParameter)
    {
        super();

        this.setLayout(new GridLayout(1, 0));

        this.xValueInputField = new ValueInputField(defaultVectorParameter.getX());
        this.add(this.xValueInputField);

        this.yValueInputField = new ValueInputField(defaultVectorParameter.getY());
        this.add(this.yValueInputField);

        this.zValueInputField = new ValueInputField(defaultVectorParameter.getZ());
        this.add(this.zValueInputField);

    }

    public Vector getVector()
    {
        return new Vector(this.xValueInputField.getValue(), this.yValueInputField.getValue(), this.zValueInputField.getValue());
    }
}
