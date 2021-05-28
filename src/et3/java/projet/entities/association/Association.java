package et3.java.projet.entities.association;

import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.persons.Personne;
import et3.java.projet.operations.Transaction;
import java.util.ArrayList;

public class Association {

  private String nom;
  private String rapportAnneePrec;
  private ArrayList<Transaction> transactions;
  private ArrayList<Personne> donateurs;
  private ArrayList<Membre> membres;
  private float argent;
  private float prixCotisation;

  public Association() {
    transactions = new ArrayList<Transaction>();
    donateurs = new ArrayList<Personne>();
    membres = new ArrayList<Membre>();
  }

  public void ajouterMembre(Membre membre) {
    membres.add(membre);
  }

  public Membre[] chercherMembre(String recherche) {
    return (Membre[]) membres
      .stream()
      .filter(membre -> membre.getNomComplet().startsWith(recherche))
      .toArray();
  }

  public void retirerMembre(Membre membre) {}
}
