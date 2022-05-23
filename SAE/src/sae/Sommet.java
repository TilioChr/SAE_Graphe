package sae;

import java.util.ArrayList;

/**
 *
 * @author tilio
 */
public class Sommet {
    private ArrayList adjacent;
    private final String typeDeSommet;
    
    public Sommet (String typeDeSommet){
        this.typeDeSommet = typeDeSommet;
    }
    
    public String getTypeDeSommet() {
        return typeDeSommet;
    }
    
    public ArrayList getAdj(){
        return adjacent;
    }
}
