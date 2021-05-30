package et3.java.projet.entities.association;

import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.persons.Personne;
import et3.java.projet.entities.persons.exceptions.DonateurDejaAjouteException;
import et3.java.projet.entities.persons.exceptions.DonateurNotFoundException;
import et3.java.projet.entities.persons.exceptions.MembreCotisationDejaPayeeException;
import et3.java.projet.entities.persons.exceptions.MembreNotFoundException;
import et3.java.projet.entities.trees.Visite;
import et3.java.projet.entities.trees.exceptions.MaxDefraiementsException;
import et3.java.projet.entities.trees.exceptions.VisiteDejaDefrayeeException;
import et3.java.projet.entities.trees.exceptions.VisiteNotFoundException;
import et3.java.projet.operations.Transaction;
import java.util.ArrayList;


/**
 * Classe représentant une association d'amoureux des arbres (unique à l'heure actuelle)
 */
public class Association {

  private String nom = "Association d'amoureux des arbres générique";
  private String rapportAnneePrec = "";
  private ArrayList<Transaction> transactions = new ArrayList<>();
  private ArrayList<Personne> donateurs = new ArrayList<>();
  private ArrayList<Membre> membres = new ArrayList<>();
  private ArrayList<Visite> visites = new ArrayList<>();
  private float argent = 0;
  private float prixCotisation = 20.00f;
  private float prixDefraiement = 9.50f;
  private short maxVisitesDefrayees = 2;

  public Association() {

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

  public void retirerMembre(Membre membre) {
    membres.remove(membre);
  }


  public float getPrixCotisation() {
    return prixCotisation;
  }


  public void validerCotisation(Membre membre) throws MembreCotisationDejaPayeeException {
    membre.validerCotisation(this);
    this.effectuerTransaction(membre.getId(), this.getPrixCotisation(), "Paiement de cotisation");
  }


  public float getPrixDefraiement() {
    return prixDefraiement;
  }

  public short getMaxVisitesDefrayees() {
    return maxVisitesDefrayees;
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


  public void defrayerVisite(long id) throws VisiteNotFoundException, VisiteDejaDefrayeeException, MembreNotFoundException, MaxDefraiementsException {

    Visite visite = getVisite(id);
    visite.rendreDefraye();

    Membre membre = getMembre( visite.getVisiteurId() );
    if( membre.getVisitesDefrayeesAnnuel() >= getMaxVisitesDefrayees() ) {
      throw new MaxDefraiementsException(membre, this);
    }

    membre.incrementeVisitesAnneeCourante();
    this.effectuerTransaction(visite.getVisiteurId(), getPrixDefraiement(), "Défraiement pour la visite "+visite.getId());

  }



  /**
   * /!\ Ne pas appeler directement
   * Ajoute une visite à la liste contenant toutes les visites
   * @param visite La visite qu'on veut ajouter à la liste
   */
  public void addVisiteListeComplete(Visite visite) {
    visites.add(visite);
  }




  public Transaction effectuerTransaction(Long id, float montant, String raison) {
    Transaction transaction = new Transaction(id, montant, raison);
    transactions.add( transaction );
    return transaction;
  }


  /**
   * Construit la liste des transactions de l'association cette année, finie par le solde actuel de l'année.
   * @return les comptes de l'association pour l'année
   */
  public String getTransactionsStr() {
    StringBuilder stringBuilder = new StringBuilder();
    float solde = 0;

    for(Transaction transaction : transactions) {
      solde += transaction.getMontant();
      stringBuilder.append(transaction.toString()).append("\n");
    }
    stringBuilder.append("Solde annuel : ").append(solde).append("€\n");

    return stringBuilder.toString();
  }



  public Personne getDonateur(long id) throws DonateurNotFoundException {
    Object[] donateursObj = donateurs.stream().filter(personne -> personne.getId() == id).toArray();

    if(donateursObj.length == 0) {
      throw new DonateurNotFoundException(id);
    } else {
      return (Personne) donateursObj[0];
    }
  }


  public void ajouterDonateur(Personne personne) throws DonateurDejaAjouteException {
    try {
      Personne donateur = getDonateur(personne.getId());
      throw new DonateurDejaAjouteException(donateur, this); // Si on a pu trouver le donateur, alors on ne doit pas l'ajouter à nouveau.
    }
    catch (DonateurNotFoundException e) { // Si on ne l'a pas trouvé dans la liste, c'est que tout est normal.
      donateurs.add(personne);
    }
  }

  public Personne retirerDonateur(long id) throws DonateurNotFoundException {
    Personne donateur = getDonateur(id);
    donateurs.remove(donateur);
    return donateur;
  }


  public Personne[] getDonateurs() {
    Object[] donateursObj = donateurs.toArray();
    Personne[] donateursArr = new Personne[ donateursObj.length ];
    for(int i=0; i<donateursObj.length; i++) {
      donateursArr[i] = (Personne) donateursObj[i];
    }

    return donateursArr;
  }


  public String getDonateursStr() {
    StringBuilder stringBuilder = new StringBuilder();
    Personne[] donateurs = getDonateurs();

    for(Personne donateur : donateurs) {
      stringBuilder.append(donateur.toString()).append("\n");
    }

    return stringBuilder.toString();
  }


}
