import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ShapeFileReader 
{
    private boolean fileCorrect;
    private Vertex[] vertices;
    private Wall[] walls;

    public ShapeFileReader(JPanel panelParameter)
    {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        File selectedFile = null;
        int result = fileChooser.showOpenDialog(panelParameter);
        selectedFile = fileChooser.getSelectedFile();
        if ((selectedFile != null) && (result == JFileChooser.APPROVE_OPTION))
        {
            try
            {
                ArrayList<String> verticesStrings = new ArrayList<String>();
                ArrayList<String> wallsStrings = new ArrayList<String>();
                BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile));
                String line;
                boolean verticesLoaded = false;
                while ((line = bufferedReader.readLine()) != null) 
                {
                    if(verticesLoaded == false)
                    {
                        if(line.isEmpty())
                        {
                            verticesLoaded = true;
                        }
                        else
                        {
                            verticesStrings.add(line);
                        }
                    }
                    else
                    {
                        if(line.isEmpty() == false)
                        {
                            wallsStrings.add(line);
                        }
                    }
                }
                bufferedReader.close();

                this.vertices = this.convertVerticesStringsToVertices(verticesStrings);
                this.walls = this.convertWallsStringsToWalls(wallsStrings);
                this.fileCorrect = true;
            }
            catch(Exception exception){}
        }
    }

    public Vertex[] getVertices()
    {
        return this.vertices;
    }

    public Wall[] getWalls()
    {
        return this.walls;
    }

    public boolean isFileGood()
    {
        return this.fileCorrect;
    }

    private Vertex[] convertVerticesStringsToVertices(ArrayList<String> verticesStringsParameter)
    {
        Vertex[] convertedVertices = new Vertex[verticesStringsParameter.size()];
        for (int i = 0; i < verticesStringsParameter.size(); i++) 
        {
            convertedVertices[i] = this.convertVertexStringToVertex(verticesStringsParameter.get(i));
        }
        return convertedVertices;
    }

    private Wall[] convertWallsStringsToWalls(ArrayList<String> wallsStringsParameter)
    {
        Wall[] convertedWalls = new Wall[wallsStringsParameter.size()];
        for (int i = 0; i < wallsStringsParameter.size(); i++) 
        {
            convertedWalls[i] = this.convertWallStringToWall(wallsStringsParameter.get(i));
        }
        return convertedWalls;
    }

    private Vertex convertVertexStringToVertex(String vertexStringParameter)
    {
        List<String> vertexStrings = Arrays.asList(vertexStringParameter.split(","));
        return new Vertex(Double.parseDouble(vertexStrings.get(0)), Double.parseDouble(vertexStrings.get(1)), Double.parseDouble(vertexStrings.get(2)));
    }

    private Wall convertWallStringToWall(String wallStringParameter)
    { 
        List<String> wallStrings = Arrays.asList(wallStringParameter.split(","));
        int[] wallVerticesIndexes = new int[wallStrings.size()];
        for(int i = 0; i < wallStrings.size(); i++)
        {
            wallVerticesIndexes[i] = Integer.parseInt(wallStrings.get(i));
        }
        return new Wall(wallVerticesIndexes);
    }
}
