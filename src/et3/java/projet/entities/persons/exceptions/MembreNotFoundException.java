package et3.java.projet.entities.persons.exceptions;

/**
 * Exception levée lorsqu'une ID ne correspond à aucun membre
 */
public class MembreNotFoundException extends Exception {

    public long id;

    public MembreNotFoundException(long id) {
        super("Le membre avec l'identifiant "+id+" n'a pas pu être trouvé.");
        this.id = id;
    }

}
