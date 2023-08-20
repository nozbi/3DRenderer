public class MatrixCalculator 
{
    public static double[][] multiplyMatrices(double[][] matrix1Parameter, double[][] matrix2Parameter) 
    {
        int rows1 = matrix1Parameter.length;
        int cols1 = matrix1Parameter[0].length;
        int cols2 = matrix2Parameter[0].length;
        double[][] result = new double[rows1][cols2];
        for (int i = 0; i < rows1; i++) 
        {
            for (int j = 0; j < cols2; j++) 
            {
                double sum = 0.0;
                for (int k = 0; k < cols1; k++) 
                {
                    sum += matrix1Parameter[i][k] * matrix2Parameter[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }
}
