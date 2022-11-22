package sae;

/**
 *
 * @author tilio
 */
public class Adjacent {
    private final int distance;
    private final String typeDeRoute;
    private final String nomSommetAdj;
    
    public Adjacent (int distance, String typeDeRoute, String nomSommetAdj){
        this.distance = distance;
        this.typeDeRoute = typeDeRoute;
        this.nomSommetAdj = nomSommetAdj;
    }
    
    public int getDistance (){
        return distance;
    }
    
    public String getTypeDeRoute (){
        return typeDeRoute;
    }    
    
    public String getSommetAdj (){
        return nomSommetAdj;
    }   

    @Override
    public String toString() {
        return "Adjacent{" + "distance=" + distance + ", typeDeRoute=" + typeDeRoute + ", nomSommetAdj=" + nomSommetAdj + '}';
    }
    
    
}
