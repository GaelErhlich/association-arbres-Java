package et3.java.projet.entities.association;

import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.persons.Personne;
import et3.java.projet.entities.persons.exceptions.MembreNotFoundException;
import et3.java.projet.entities.trees.Visite;
import et3.java.projet.entities.trees.exceptions.VisiteNotFoundException;
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
    visites = new ArrayList<Visite>();
  }

  public void ajouterMembre(Membre membre) {
    membres.add(membre);
  }

  public Membre[] chercherMembre(String recherche) {
      Object[] membresObj = membres
      .stream()
      .filter(membre -> membre.getNomComplet().toLowerCase().startsWith(recherche.toLowerCase()))
      .toArray();

      Membre[] membresArr = new Membre[ membresObj.length ];
      for(int i=0; i<membresObj.length; i++) {
        membresArr[i] = (Membre) membresObj[i];
      }

      return membresArr;
  }

  public Membre getMembre(long id) throws MembreNotFoundException {
    Object[] membresId = membres.stream().filter(membre -> membre.getId() == id).toArray();
    if(membresId.length > 0) {
      return (Membre) membresId[0];
    }else{
      throw new MembreNotFoundException(id);
    }
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

  public Visite getVisite(long id) throws VisiteNotFoundException {
     Object[] visitesWithId = this.visites.stream()
      .filter(visite -> visite.getId() == id)
      .toArray();

     if(visitesWithId.length > 0) {
       return (Visite) visitesWithId[0];
     }else{
       throw new VisiteNotFoundException(id);
     }
  }

  /**
   * /!\ Ne pas appeler directement
   * Ajoute une visite à la liste contenant toutes les visites
   * @param visite
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
}
