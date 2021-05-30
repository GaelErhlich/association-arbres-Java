package et3.java.projet.entities.association;

import et3.java.projet.entities.association.exceptions.BilanTropTotException;
import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.persons.Personne;
import et3.java.projet.entities.persons.exceptions.MembreNotFoundException;
import et3.java.projet.entities.trees.Visite;
import et3.java.projet.entities.trees.exceptions.VisiteNotFoundException;
import et3.java.projet.operations.Transaction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Association {

  private String nom = "Association d'amoureux des arbres générique";
  private String rapportAnneePrec = "";
  private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
  private ArrayList<Personne> donateurs = new ArrayList<Personne>();
  private ArrayList<Membre> membres = new ArrayList<Membre>();
  private ArrayList<Visite> visites = new ArrayList<Visite>();
  private float argent = 0;
  private float prixCotisation = 20;
  private Date dernierBilan;

  public Association() {}

  public void ajouterMembre(Membre membre) {
    membres.add(membre);
  }

  public Membre[] chercherMembre(String recherche) {
    Object[] membresObj = membres
      .stream()
      .filter(
        membre ->
          membre
            .getNomComplet()
            .toLowerCase()
            .startsWith(recherche.toLowerCase())
      )
      .toArray();

    Membre[] membresArr = new Membre[membresObj.length];
    for (int i = 0; i < membresObj.length; i++) {
      membresArr[i] = (Membre) membresObj[i];
    }

    return membresArr;
  }

  public Membre getMembre(long id) throws MembreNotFoundException {
    Object[] membresId = membres
      .stream()
      .filter(membre -> membre.getId() == id)
      .toArray();
    if (membresId.length > 0) {
      return (Membre) membresId[0];
    } else {
      throw new MembreNotFoundException(id);
    }
  }

  public void retirerMembre(Membre membre) {
    membres.remove(membre);
  }

  public String getMembresStr() {
    StringBuilder liste = new StringBuilder();

    for (Membre membre : membres) {
      liste.append(membre.toString()).append("\n");
    }

    return liste.toString();
  }

  public Visite[] getVisites() {
    return (Visite[]) this.visites.toArray();
  }

  public Visite getVisite(long id) throws VisiteNotFoundException {
    Object[] visitesWithId =
      this.visites.stream().filter(visite -> visite.getId() == id).toArray();

    if (visitesWithId.length > 0) {
      return (Visite) visitesWithId[0];
    } else {
      throw new VisiteNotFoundException(id);
    }
  }

  /**
   * /!\ Ne pas appeler directement
   * Ajoute une visite à la liste contenant toutes les visites
   * @param visite La visite qu'on veut ajouter à la liste
   */
  public void addVisiteListeComplete(Visite visite) {
    visites.add(visite);
  }

  public void retirerMembre(long id) {
    Membre membre = (Membre) this.membres.stream()
      .filter(m -> m.getId() == id)
      .toArray()[0];
    membre = null;
  }

  public void effectuerTransaction(
    Personne partie,
    float montant,
    String raison
  ) {
    transactions.add(new Transaction(partie, montant, raison));
  }

  public void effectuerBilan() throws BilanTropTotException {
    if (dernierBilan != null) {
      Calendar c = Calendar.getInstance();
      Date now = new Date();
      Date dernierBilanClone = (Date) dernierBilan.clone();
      c.setTime(dernierBilanClone);
      c.add(Calendar.YEAR, 1);
      if (c.after(now)) {
        throw new BilanTropTotException(c);
      } else {
        dernierBilan = now;
      }
    }
  }
}
