import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.plaf.FileChooserUI;

public class ShapeFileReader 
{
    public ShapeFileReader(JPanel panelParameter)
    {
        JFileChooser fileChooser = new JFileChooser();
        File selectedFile = null;
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(panelParameter);
        if (result == JFileChooser.APPROVE_OPTION) 
        {
            selectedFile = fileChooser.getSelectedFile();
        } 
        else 
        {
            
        }
    }

    public Vertex[] getVertices()
    {
        Vertex[] vertices = 
        {
           new Vertex(-100, 100, 100),
           new Vertex(100, 100, 100),
           new Vertex(100, -100, 100),
           new Vertex(-100, -100, 100),
           new Vertex(-100, 100, -100),
           new Vertex(100, 100, -100),
           new Vertex(100, -100, -100),
           new Vertex(-100, -100, -100)
        };

        return vertices;
    }

    public Wall[] getWalls()
    {
        Wall[] walls =
        {
            new Wall(new int[]{0, 1, 2, 3}),
            new Wall(new int[]{4, 5, 6, 7}),
            new Wall(new int[]{0, 3, 7, 4}),
            new Wall(new int[]{1, 2, 6, 5}),
            new Wall(new int[]{2, 3, 7, 6}),
            new Wall(new int[]{0, 4, 5, 1})
        };

        return walls;
    }

    public boolean isFileGood()
    {
        return true;
    }
}
