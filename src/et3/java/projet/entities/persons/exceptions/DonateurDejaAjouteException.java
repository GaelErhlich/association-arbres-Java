package et3.java.projet.entities.persons.exceptions;

import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.persons.Personne;

public class DonateurDejaAjouteException extends Exception {

    public Personne donateur;
    public Association association;

    public DonateurDejaAjouteException(Personne donateur, Association association) {
        super("Le donateur "+donateur.getNomComplet()+"#"+donateur.getId()+" est déjà donateur de l'association.");
        this.donateur = donateur;
        this.association = association;
    }
}
