package fr.info.Starfish.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.ArrayList;

import fr.info.Starfish.data.Fichier;

@Component
public class HashTable {
   
  //private HashMap<> hMap;
  private final String            fileName;
  private Fichier                 fichier;
   
  @Autowired
    public HashTable() {
      this.fichier  = new Fichier();
      fileName      = "nom_du_fichier_de_donnees.txt";
    }
  
  public String 
    lireFichier() { 
      return fichier.lireFichier(fileName);
    }
  
  public void 
    ecrireFichier(String contenu, boolean mode) {
      fichier.ecrireFichier(fileName, contenu, mode);
    }
  /*
  public void 
    parseDonnees(){ } 
    */
   
  /*
   * Récupérer les clés : avec keySet
   * villes : ensemble de clés
   */
  /*
  private void 
    afficherHMap() { }
   */
   
  /*
  public void 
    ajout() { }
    */
  
  /*
   * + toute autre méthodes requises
   */
    
}

