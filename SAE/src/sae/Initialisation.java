/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tilio
 */
public class Initialisation {
    
    public Initialisation(){
    }
    
    public static List<Sommet> makeInit(){
    String filePath = "fichier.csv";
    List<String> liste = new ArrayList<String>();
    try {
      // Importe le fichier dans la variable "br"
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      // Lis la premier ligne de "br"
      String line = br.readLine();
      // Ajoute toutes les lignes dans une liste séparées d'un 'lineseparator'
      while (line != null) {
        liste.add(line);
        line = br.readLine();
      }
      System.out.println(liste);
      System.out.println(liste.get(1));
      // ferme le fichier
      br.close();
      
      //Ici il faut construire des adjacent
      
      //Ici il faut construire des sommet et une liste de sommet (note les sommets demande la liste de leurs adjacent pour etre construit)
      
      // Exception
      } catch (IOException e) {
      if (e instanceof FileNotFoundException) {
        System.out.println("Le fichier n'existe pas");
      } else {
        System.out.println("Erreur lors de la lecture du fichier");
        e.printStackTrace();
      }
      }
        //ici il faut retourner une liste de sommet que la vue recupere
        return null;
    }
}
