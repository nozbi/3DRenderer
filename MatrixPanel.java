import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MatrixPanel extends JPanel
{
    public MatrixPanel()
    {
        super();
    }

    public void setMatrix(Matrix matrixParameter)
    {
        double[][] matrix = matrixParameter.getMatrix();

        this.setLayout(new GridLayout(matrix.length, matrix[0].length));

        this.removeAll();
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix.length; j++)
            {
                this.add(new JLabel(Double.toString(matrix[i][j])));
            }
        }
        this.revalidate();
    }
}
