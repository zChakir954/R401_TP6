package fr.info.Starfish.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

@Component
public class Fichier {
  
  private final PathMatchingResourcePatternResolver resolver;
  private String path;
  
  /*
   * Constructeur
   */    
  @Autowired
  public Fichier() { 
    path = "./Data/";
    resolver = new PathMatchingResourcePatternResolver();
  }
  
  /* 
   * lireFichier
   * - lecture donnees : appel methode privee
   * - sans utiliser un try-catch dans le lecteur
   */
  public String lireFichier(String file) {    
    String line="";
    try {
      line = lireContenuFichier(path+file);
    } catch(IOException ioe) {
      System.out.println.debug("Donnees Fichier - lireFichier : ["+ioe+"]");
    }
    return line;
  }
  /* 
   * ecrireContenu : écriture donnees (appel en methode privee)
   */
  public void ecrireFichier(String file, String s, boolean b) { 
    
    try {
      if(s!="") ecrireContenuFichier(path+file, s, b);
    } catch(IOException ioe) {
      System.out.println.debug("Donnees Fichier - ecrireContenu : ["+ioe+"]");
    }
  }
  
  /*
   * lireContenuFichier
   */
  public String lireContenuFichier(String fileName) throws IOException {
    String content="";
    Resource res = resolver.getResource("file:"+fileName);
    try (InputStream is = res.getInputStream()) {
      content = StreamUtils.copyToString(is, StandardCharsets.UTF_8);
      System.out.println.debug("Donnees Fichier - lireContenuFichier : [\n" + content + "\n]");
      
    } 
    catch (IOException ioe) {
      System.out.println.debug("Donnees Fichier - lireContenuFichier - erreur : ["+ioe+"]");
    }
    return content;
  }
  /*
   * ecrireContenuFichier
   */
  private void ecrireContenuFichier(String fileName, String client, Boolean b) throws IOException {
    try (
      FileOutputStream fos = 
        new FileOutputStream(fileName, b);
        BufferedOutputStream bos = new BufferedOutputStream(fos)
    ) {
        bos.write(client.getBytes());
        bos.flush();
        fos.getFD().sync();
        bos.close();
    }
  }

}
