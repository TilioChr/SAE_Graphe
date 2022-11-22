/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tilio, romane, louaï
 */
public class Initialisation {
    
    //constructeur vide
    public Initialisation(){
    }
    
    public static List<Sommet> makeInit(File road){
    File filePath = road;
    List<String> liste = new ArrayList<String>();
    List<Sommet> listeSommet = new ArrayList<>(); //creation list de sommet a retourner
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
      // ferme le fichier
      br.close();
      List<List<Adjacent>> listeListeAdj = new ArrayList<>(); //creation de la liste de liste d'adjacence
      List<String> listeNomSommet = new ArrayList<>(); //collecte les nom de chaque sommet pour les utiliser a la creation d'objet sommet
      List<String> listeTypeSommet = new ArrayList<>(); //collecte les nom de chaque sommet pour les utiliser a la creation d'objet sommet
      //Ici il faut decouper chaque case de la liste
      for(String s : liste){ //parcours chaque ligne de la liste
        String[] sepSommet = s.split(" : "); //separe le sommet de sa liste d'adjacence
        //System.out.println(sepSommet[0]); //affiche le sommet concerne
        String[] sepNom = sepSommet[0].split(",");
        listeNomSommet.add(sepNom[1]);
        listeTypeSommet.add(sepNom[0]);
        //System.out.println(sepSommet[1]); //affiche la liste d'adjacence
        String[] sepAdjacent = sepSommet[1].split("; "); //separe chaque adjacent dans la liste d'adjacence
        //System.out.println(sepAdjacent[0]); 
        List<Adjacent> listeAdj = new ArrayList<Adjacent>(); //creation de la liste d'ajdacence propre au sommet

        for (String a : sepAdjacent){
            String[] sepType = a.split(","); //separe le type de la distance si il s'agit d'une route ou le type du nom du sommet
            //System.out.println(sepType[0]); //Affiche type route
            String[] sepNomAdj = sepType[1].split(" :: "); //separe la distance du type de route
            //System.out.println(sepNomAdj[0]); //Affiche Distance route
            String[] sepNomAdj2 = sepType[2].split(" :: "); //separe le nom des adjacent
            String[] cleangui = sepNomAdj2[0].split("\""); //fais disparaitre les guillements
            //Ici il faut construire des adjacent
            Adjacent adj = new Adjacent(Integer.parseInt(sepNomAdj[0]), sepType[0], cleangui[0]);
            //System.out.println(adj.toString());
            listeAdj.add(adj);
        }
        
        listeListeAdj.add(listeAdj);
      }
      
      //Construire des sommet et une liste de sommet (note les sommets demande la liste de leurs adjacent pour etre construit)
      int iterateurCreerSommet = 0;
      for (String a : listeNomSommet) {
          //System.out.println("Nom du sommet : "+a);
          //System.out.println("Liste d'adjacence : "+listeListeAdj.get(iterateurCreerSommet));
          Sommet sommet = new Sommet(a, listeTypeSommet.get(iterateurCreerSommet), listeListeAdj.get(iterateurCreerSommet));
          //System.out.println(sommet.toString());
          listeSommet.add(sommet);
          iterateurCreerSommet = iterateurCreerSommet+1;
      }
      
      for(Sommet s : listeSommet){
          System.out.println(s);
      }
      
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
        return listeSommet;    }
}
