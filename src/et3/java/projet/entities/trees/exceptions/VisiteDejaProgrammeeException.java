package et3.java.projet.entities.trees.exceptions;

import et3.java.projet.entities.trees.Arbre;
import et3.java.projet.entities.trees.Visite;

public class VisiteDejaProgrammeeException extends Exception {

    public Arbre arbre;
    public Visite visiteOriginale;
    public Visite visiteDoublon;


    public VisiteDejaProgrammeeException(Arbre arbre, Visite visiteOriginale, Visite visiteDoublon) {
        super("La visite #"+visiteOriginale.getId()+" est déjà programmée à l'avenir sur l'arbre #"+arbre.getId()+".");
        this.arbre = arbre;
        this.visiteOriginale = visiteOriginale;
        this.visiteDoublon = visiteDoublon;
    }

}
