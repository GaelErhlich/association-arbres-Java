package et3.java.projet.entities.trees.exceptions;

import et3.java.projet.entities.trees.Visite;

/**
 * Exception levée lorsqu'on essaye de défrayer 2 fois la même visite
 */
public class VisiteDejaDefrayeeException extends Exception {

    public Visite visite;

    public VisiteDejaDefrayeeException(Visite visite) {
        super("La visite "+visite.getId()+" a déjà été défrayée.");
        this.visite = visite;
    }
}
