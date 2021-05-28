package et3.java.projet.entities.association;

import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.persons.Personne;
import et3.java.projet.entities.trees.Visite;
import et3.java.projet.operations.Transaction;
import java.util.ArrayList;

public class Association {

  private String nom;
  private String rapportAnneePrec;
  private ArrayList<Transaction> transactions;
  private ArrayList<Personne> donateurs;
  private ArrayList<Membre> membres;
  private ArrayList<Visite> visites;
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

  
  public String getMembresStr() {
    StringBuilder liste = new StringBuilder();

    for(Membre membre : membres) {
      liste.append(membre.toString()).append("\n");
    }

    return liste.toString();
  }



  public Visite[] getVisites() {
    return (Visite[]) this.visites.toArray();
  }

  public Visite getVisite(long id) {
    return (Visite) this.visites.stream()
      .filter(visite -> visite.getId() == id)
      .toArray()[0];
  }

  public Visite[] searchVisite(String recherche) {
    return (Visite[]) this.visites.stream()
      .filter(visite -> visite.getNomComplet().startsWith(recherche))
      .toArray();
  }

  public void retirerMembre(Membre membre) {}
}
