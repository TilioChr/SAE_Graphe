package sae;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author tilio
 */

public class SAE {

    public static void main(String[] args) {
    String filePath = "fichier.csv";
    ArrayList liste = new ArrayList();
    try {
      // Importe le fichier dans la variable "br"
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      // Lis la premier ligne de "br"
      String line = br.readLine();
      // Ajoute toutes les lignes dans une liste séparées d'un 'lineseparator'
      while (line != null) {
        liste.add(line);
        liste.add(System.lineSeparator());
        line = br.readLine();
      }
      System.out.println(liste);
      System.out.println(liste.get(1));
      // ferme le fichier
      br.close();
      // Exception
      } catch (IOException e) {
      if (e instanceof FileNotFoundException) {
        System.out.println("Le fichier n'existe pas");
      } else {
        System.out.println("Erreur lors de la lecture du fichier");
        e.printStackTrace();
      }
      }
    
    
    
    }
}
