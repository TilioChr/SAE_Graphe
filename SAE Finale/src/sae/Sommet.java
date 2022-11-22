package sae;

import java.util.List;

/**
 *
 * @author tilio
 */
public class Sommet {
    private final String nomSommet;
    private final String typeDeSommet;
    private List<Adjacent> listAdjacent;
    
    public Sommet (String nomSommet, String typeDeSommet, List<Adjacent> listAdjacent){
        this.nomSommet = nomSommet;
        this.typeDeSommet = typeDeSommet;
        this.listAdjacent = listAdjacent;
    }
    
    public String getTypeDeSommet() {
        return typeDeSommet;
    }

    public List<Adjacent> getListAdjacent() {
        return listAdjacent;
    }
    
    public String getNomSommet(){
        return nomSommet;
    }

    @Override
    public String toString() {
        return "Sommet{" + "nomSommet=" + nomSommet + ", typeDeSommet=" + typeDeSommet + ", listAdjacent=" + listAdjacent + '}';
    }

    
}
