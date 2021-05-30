package et3.java.projet.entities.trees.exceptions;

/**
 * Exception levée lorsqu'un ID ne correspond à aucune visite
 */
public class VisiteNotFoundException extends Exception {

    public long id;

    public VisiteNotFoundException(long id) {
        super("La visite d'identifiant "+id+" n'a pas pu être trouvée.");
        this.id = id;
    }

}
