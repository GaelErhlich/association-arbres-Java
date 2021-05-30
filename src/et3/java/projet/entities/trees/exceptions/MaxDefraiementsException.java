package et3.java.projet.entities.trees.exceptions;

import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.persons.Membre;

public class MaxDefraiementsException extends Exception {

    public Membre membre;
    public Association association;


    public MaxDefraiementsException(Membre membre, Association association) {
        super("Le membre "+membre.getNomEtId()+" a atteint le nombre maximum de défraiements cette année.");
        this.membre = membre;
        this.association = association;
    }

}
