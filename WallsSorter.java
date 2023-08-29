public class WallsSorter 
{
    public static Wall[] sort(Wall[] wallsParameter, Vertex[] verticesParameter, double observerDistanceParameter)
    {
        int n = wallsParameter.length;  
        Wall temp = null;  
        for(int i=0; i < n; i++)
        {  
            for(int j=1; j < (n-i); j++)
            {  
                if(wallsParameter[j-1].getDistance(verticesParameter, observerDistanceParameter) < wallsParameter[j].getDistance(verticesParameter, observerDistanceParameter))
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
