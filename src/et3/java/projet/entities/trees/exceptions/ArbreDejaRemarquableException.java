package et3.java.projet.entities.trees.exceptions;

import et3.java.projet.entities.trees.Arbre;

/**
 * Exception levée lorsqu'on essaye de rendre remarquable un arbre qui l'est déjà
 */
public class ArbreDejaRemarquableException extends Exception {

    public Arbre arbre;

    public ArbreDejaRemarquableException(Arbre arbre) {
        super("L'arbre "+arbre.getId()+" est déjà remarquable.");
        this.arbre = arbre;
    }

}
