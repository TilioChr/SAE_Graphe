package sae;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tilio
 */
public class Sommet {
    private List<Adjacent> listAdjacent;
    private final String typeDeSommet;
    
    public Sommet (String typeDeSommet){
        this.typeDeSommet = typeDeSommet;
    }
    
    public String getTypeDeSommet() {
        return typeDeSommet;
    }
    
    public List getAdj(){
        return listAdjacent;
    }
}
