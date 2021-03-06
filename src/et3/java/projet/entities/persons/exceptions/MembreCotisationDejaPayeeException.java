package et3.java.projet.entities.persons.exceptions;

import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.persons.Membre;

/**
 * Exception levée lorsqu'on essaye de faire payer sa cotisation 2 fois à un membre
 */
public class MembreCotisationDejaPayeeException extends Exception {

        public Membre membre;
        public Association association;

        public MembreCotisationDejaPayeeException(Membre membre, Association association) {
            super("Le membre "+membre.getNomComplet()+"#"+membre.getId()+" est déjà à jour de cotisation dans l'association.");
            this.membre = membre;
            this.association = association;
        }


}
