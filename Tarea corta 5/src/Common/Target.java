package Common;

import java.io.Serializable;

public class Target implements Serializable, Constantes{

    public int[] coords;
    public int[] oldCoords;

    public Target(){
        coords = new int[2];
        coords[X] = 0;
        coords[Y] = 0;
        
        oldCoords = new int[2];
        oldCoords[X] = 0;
        oldCoords[Y] = 0;        
    }
    
    public void updateTarget(){
        oldCoords[X] = coords[X];
        oldCoords[Y] = coords[Y];
    }
    
}
