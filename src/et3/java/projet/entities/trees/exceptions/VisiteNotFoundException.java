package et3.java.projet.entities.trees.exceptions;

public class VisiteNotFoundException extends Exception {

    public long id;

    public VisiteNotFoundException(long id) {
        super("La visite d'identifiant "+id+" n'a pas pu être trouvée.");
        this.id = id;
    }

}
