package et3.java.projet.entities.trees.exceptions;

/**
 * Exception levée lorsqu'un ID ne correspond à aucun arbre
 */
public class ArbreNotFoundException extends Exception {

    public long id;

    public ArbreNotFoundException(long id) {
        super("L'arbre d'ID "+id+" n'a pas été trouvé.");
        this.id = id;
    }



}
