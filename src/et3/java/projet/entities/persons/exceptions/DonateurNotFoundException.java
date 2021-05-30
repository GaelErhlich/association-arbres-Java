package et3.java.projet.entities.persons.exceptions;

/**
 * Exception levée lorsqu'une ID ne correspond à aucun donateur
 */
public class DonateurNotFoundException extends Exception {

    public long id;

    public DonateurNotFoundException(long id) {
        super("Le donateur avec l'identifiant "+id+" n'a pas pu être trouvé.");
        this.id = id;
    }


}
