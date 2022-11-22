/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author tilio
 */
public class ModeleListe extends AbstractListModel{
    List<String> listModele = new ArrayList<>();
    
    @Override
    public int getSize() {
        return listModele.size();
    }

    @Override
    public String getElementAt(int index) {
        return listModele.get(index);
    }
    
    public void initialize(List<String> valeur){
        listModele = valeur;
    }
    
    public void ajouter(String sommet){
        listModele.add(sommet);
        this.fireIntervalAdded(this, 0, listModele.size());
    }
    
    public void vider(){
        listModele.clear();
    }
    
}
