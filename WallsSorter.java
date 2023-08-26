public class WallsSorter 
{
    public static Wall[] sort(Wall[] wallsParameter, Vertex[] verticesParameter)
    {
        int n = wallsParameter.length;  
        Wall temp = null;  
        for(int i=0; i < n; i++)
        {  
            for(int j=1; j < (n-i); j++)
            {  
                if(wallsParameter[j-1].getZOrder(verticesParameter) < wallsParameter[j].getZOrder(verticesParameter))
                {   
                    temp = wallsParameter[j-1];  
                    wallsParameter[j-1] = wallsParameter[j];  
                    wallsParameter[j] = temp;  
                }          
            } 
        } 

        return wallsParameter;
    }
}
