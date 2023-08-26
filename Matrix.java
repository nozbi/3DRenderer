public class Matrix 
{
    double[][] matrix;

    public Matrix()
    {
        this.matrix = new double[4][4];
        this.matrix[0][0] = 1;
        this.matrix[1][1] = 1;
        this.matrix[2][2] = 1;
        this.matrix[3][3] = 1;
    }

    public void move(Vector moveVectorParameter)
    {
        this.multiplyByOtherMatrix(this.createMoveMatrix(moveVectorParameter));
    }

    public void rotate(Vector rotateVectorParameter)
    {
        this.multiplyByOtherMatrix(this.createRotateXMatrix(Converter.convertDegreesToRadians(rotateVectorParameter.getX())));
        this.multiplyByOtherMatrix(this.createRotateYMatrix(Converter.convertDegreesToRadians(rotateVectorParameter.getY())));
        this.multiplyByOtherMatrix(this.createRotateZMatrix(Converter.convertDegreesToRadians(rotateVectorParameter.getZ())));
    }

    public void scale(Vector scaleVectorParameter)
    {
        this.multiplyByOtherMatrix(this.createScaleMatrix(scaleVectorParameter));
    }

    public double[][] getMatrix()
    {
        return this.matrix;
    }

    private Matrix createMoveMatrix(Vector moveVectorParameter)
    {
        Matrix moveMatrix = new Matrix();
        moveMatrix.getMatrix()[3][0] = moveVectorParameter.getX();
        moveMatrix.getMatrix()[3][1] = moveVectorParameter.getY();
        moveMatrix.getMatrix()[3][2] = moveVectorParameter.getZ();
        return moveMatrix;
    }

    private Matrix createScaleMatrix(Vector scaleVectorParameter)
    {
        Matrix scaleMatrix = new Matrix();
        scaleMatrix.getMatrix()[0][0] = scaleVectorParameter.getX();
        scaleMatrix.getMatrix()[1][1] = scaleVectorParameter.getY();
        scaleMatrix.getMatrix()[2][2] = scaleVectorParameter.getZ();
        return scaleMatrix;
    }

    private Matrix createRotateXMatrix(double angleParameter)
    {
        Matrix rotateXMatrix = new Matrix();
        rotateXMatrix.getMatrix()[1][1] = Math.cos(angleParameter);
        rotateXMatrix.getMatrix()[1][2] = Math.sin(angleParameter);
        rotateXMatrix.getMatrix()[2][1] = (-1) * Math.sin(angleParameter);
        rotateXMatrix.getMatrix()[2][2] = Math.cos(angleParameter);
        return rotateXMatrix;
    }

    private Matrix createRotateYMatrix(double angleParameter)
    {
        Matrix rotateYMatrix = new Matrix();
        rotateYMatrix.getMatrix()[0][0] = Math.cos(angleParameter);
        rotateYMatrix.getMatrix()[0][2] = (-1) * Math.sin(angleParameter);
        rotateYMatrix.getMatrix()[2][0] = Math.sin(angleParameter);
        rotateYMatrix.getMatrix()[2][2] = Math.cos(angleParameter);
        return rotateYMatrix;
    }

    private Matrix createRotateZMatrix(double angleParameter)
    {
        Matrix rotateZMatrix = new Matrix();
        rotateZMatrix.getMatrix()[0][0] = Math.cos(angleParameter);
        rotateZMatrix.getMatrix()[0][1] = Math.sin(angleParameter);
        rotateZMatrix.getMatrix()[1][0] = (-1) * Math.sin(angleParameter);
        rotateZMatrix.getMatrix()[1][1] = Math.cos(angleParameter);
        return rotateZMatrix;
    }

    private void multiplyByOtherMatrix(Matrix matrixParameter)
    {
        this.matrix = MatrixCalculator.multiplyMatrices(this.getMatrix(), matrixParameter.getMatrix());
    }

    public Vertex calculateNewVertex(Vertex vertexParameter)
    {
        double[][] oldVertexMatrix = new double[1][4];
        oldVertexMatrix[0][0] = vertexParameter.getX();
        oldVertexMatrix[0][1] = vertexParameter.getY();
        oldVertexMatrix[0][2] = vertexParameter.getZ();
        oldVertexMatrix[0][3] = 1;
        double[][] newVertexMatrix = MatrixCalculator.multiplyMatrices(oldVertexMatrix, this.matrix);
        return new Vertex(newVertexMatrix[0][0], newVertexMatrix[0][1], newVertexMatrix[0][2]);
    }
}
