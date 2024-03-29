import javax.swing.JTextField;


public class ValueInputField extends JTextField
{
    private double acceptedValue;
    

    public ValueInputField(double defaultValueParameter)
    {
        super();

        this.acceptedValue = defaultValueParameter;
        this.setText(Double.toString(this.acceptedValue));
    }

    public double getValue()
    {
        double value = 0;
        try
        {
            value = Double.parseDouble(this.getText());
            this.acceptedValue = value;
        }
        catch(Exception exception)
        {
            value = this.acceptedValue;
        }
        this.setText(Double.toString(value));
        return value;
    }
}
