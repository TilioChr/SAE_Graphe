package sae;

/**
 *
 * @author tilio
 */
public class Adjacent {
    private final int distance;
    private final String typeDeRoute;
    
    public Adjacent (int distance, String typeDeRoute){
        this.distance = distance;
        this.typeDeRoute = typeDeRoute;
    }
    
    public int getDistance (){
        return distance;
    }
    
    public String getTypeDeRoute (){
        return typeDeRoute;
    }    
}
