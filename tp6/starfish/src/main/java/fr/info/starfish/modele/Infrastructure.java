package fr.info.starfish.modele; 

import lombok.Data;

@Data
public class Infrastructure {
    
    // --- Les attributs selon la spécification (Fiche 6 - §2.b) ---
    private String id;
    private String localisation; // ex: "France Paris" ou juste "Mexico"
    private String ville;        // Pour un usage futur (HashMap)
    private String contact;      // Adresse email (générée plus tard)
    private String latitude;     // Coordonnées GPS (générées plus tard)
    private String longitude;    // Coordonnées GPS (générées plus tard)
    private String type;         // "Data Center" ou "Cloud"
    private String portee;       // "National" ou "Local"
    private String nombre;       // ex: "dn=10" ou "cl=5"
    private String complement;   // Info complémentaire (peut être vide)

    /**
     * Méthode d'initialisation (équivalent d'un constructeur spécifique).
     * Selon la Fiche 6 (§3.a) : "À la création d'une instance, il n'y a pas à affecter 
     * les champs contact, latitude, longitude. On défini tous les autres champs 
     * (le cas échéant avec une valeur nulle)."
     */
    public void init(String id, String localisation, String ville, String type, 
                     String portee, String nombre, String complement) {
        this.id = id;
        this.localisation = localisation;
        this.ville = ville;
        this.type = type;
        this.portee = portee;
        this.nombre = nombre;
        this.complement = complement;
        
        // Ces champs sont volontairement laissés à null ou vides à la création.
        // C'est notre composant "Services" qui va travailler pour les remplir !
        this.contact = null;
        this.latitude = null;
        this.longitude = null;
    }
}