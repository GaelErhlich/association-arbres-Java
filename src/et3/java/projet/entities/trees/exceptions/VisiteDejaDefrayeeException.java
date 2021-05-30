package et3.java.projet.entities.trees.exceptions;

import et3.java.projet.entities.trees.Visite;

public class VisiteDejaDefrayeeException extends Exception {

    public Visite visite;

    public VisiteDejaDefrayeeException(Visite visite) {
        super("La visite "+visite.getId()+" a déjà été défrayée.");
        this.visite = visite;
    }
}
