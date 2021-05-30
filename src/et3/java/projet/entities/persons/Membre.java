package et3.java.projet.entities.persons;

import et3.java.projet.entities.Municipalite;
import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.persons.exceptions.MembreCotisationDejaPayeeException;
import et3.java.projet.entities.trees.exceptions.ArbreNotFoundException;
import java.util.Calendar;

/**
 * Personne représentant un membre d'une association d'amis des arbres
 */
public class Membre extends Personne {

  private long naissance;
  private short visitesDefrayeesAnnuel;
  private long dateDerniereCotisation;
  private short anneePremiereCotisation;
  private Long[] arbresSouhaites = new Long[5];

  /**
   * Constructeur par défaut de Membre
   * @param nomComplet Nom complet du membre
   * @param naissance Date de naissance du membre (millisecondes depuis 01/01/1970)
   * @param adresse Adresse postale du membre (String)
   * @param dateDerniereCotisation Date de la dernière cotisation (millisecondes depuis 01/01/1970)
   * @param anneePremiereCotisation Année de la première cotisation (format "1998", "2017", etc.)
   * @param visitesDefrayeesAnnuel Nombre de visites défrayées cette année pour ce membre
   */
  public Membre(
    String nomComplet,
    long naissance,
    String adresse,
    long dateDerniereCotisation,
    short anneePremiereCotisation,
    short visitesDefrayeesAnnuel
  ) {
    super(nomComplet, adresse);
    this.naissance = naissance;
    this.visitesDefrayeesAnnuel = visitesDefrayeesAnnuel;
    this.dateDerniereCotisation = dateDerniereCotisation;
    this.anneePremiereCotisation = anneePremiereCotisation;
  }

  /**
   * Indique si le membre est à jour de cotisations cette année
   * @return true si à jour, false si pas à jour
   */
  public boolean estAJourDeCotisation() {
    Calendar c = Calendar.getInstance();
    int anneeCourante = c.get(Calendar.YEAR);

    c.setTimeInMillis(dateDerniereCotisation);
    return c.get(Calendar.YEAR) >= anneeCourante;
  }

  /**
   * Getter pour le nombre de visites que le membre s'est fait défrayer cette année
   * @return le nombre de visites défrayées
   */
  public short getVisitesDefrayeesAnnuel() {
    return visitesDefrayeesAnnuel;
  }

  /**
   * Réinitialise à 0 le nombre de visites défrayées du membre
   */
  public void reinitialiserNbrVisites() {
    this.visitesDefrayeesAnnuel = 0;
  }

  /**
   * Incrémente le nombre de visites que le membre s'est fait défrayer cette année, pour mettre à jour
   */
  public void incrementeVisitesAnneeCourante() {
    this.visitesDefrayeesAnnuel++;
  }

  /**
   * /!\ Ne pas appeler directement (passer par l'association)
   * Définit la date de dernière cotisation du membre comme aujourd'hui.
   * @param association l'association dont le membre est membre
   * @throws MembreCotisationDejaPayeeException
   */
  public void validerCotisation(Association association)
    throws MembreCotisationDejaPayeeException {
    if (estAJourDeCotisation()) {
      throw new MembreCotisationDejaPayeeException(this, association);
    }

    dateDerniereCotisation = Calendar.getInstance().getTimeInMillis();
  }


  /**
   * Donne la liste des arbres que le membre veut voir choisis comme remarquables, sous forme de String à afficher
   * @param municipalite La municipalité de l'arbre
   * @return une String de la liste
   */
  public String getArbresSouhaitesStr(Municipalite municipalite) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < arbresSouhaites.length; i++) {
      if (arbresSouhaites[i] != null) { // On vérifie que l'arbre existe toujours. Si oui, on l'ajoute au stringBuilder.
        try {
          municipalite.getArbre(arbresSouhaites[i]);
          stringBuilder.append(arbresSouhaites[i].toString() + "\n");
        } catch (ArbreNotFoundException e) { // Si non, on le met à null directement.
          arbresSouhaites[i] = null;
        }
      }

      if (arbresSouhaites[i] == null) { // Si pas de valeur (null), on indique un emplacement libre.
        stringBuilder.append("1 emplacement libre\n");
      }
    }

    return stringBuilder.toString();
  }


  /**
   * Donne la liste des identifiants des arbres souhaités par le membre
   * @return un tableau de Long integers
   */
  public Long[] getArbresSouhaites() {
    return this.arbresSouhaites;
  }


  /**
   * Ajoute l'identifiant d'un arbre à la liste de souhaits d'un membre, supprimant le premier pour faire de la place si nécessaire
   * @param id identifiant de l'arbre
   */
  public void ajouterSouhaitArbre(long id) {
    short i = 0;
    while (i < 5 && arbresSouhaites[i] != null) { // On cherche la 1ère case libre du tableau.
      i++;
    }

    if (i == 5) { // Si on n'en a pas trouvé, on libère la dernière place.
      for (int k = 0; k < 4; k++) {
        arbresSouhaites[k] = arbresSouhaites[k + 1];
      }
      i = 4; // On indique que la 1ère case libre est la dernière.
    }

    arbresSouhaites[i] = id;
  }



  /**
   * Réinitialise la liste des arbres du membre
   */
  public void reinitialiserArbresSouhaites() {
    this.arbresSouhaites = new Long[5];
  }



  /**
   * Met les informations essentielles du membre en une String d'une seule ligne
   * @return la String d'une ligne
   */
  @Override
  public String toString() {
    return (
      "Membre#" +
      getId() +
      " { " +
      getNomComplet() +
      ", " +
      getAdresse() +
      ", Visites défrayées (cette année) : " +
      getVisitesDefrayeesAnnuel() +
      ", Arrivée en " +
      anneePremiereCotisation +
      ", Cotisation : " +
      (estAJourDeCotisation() ? "À jour" : "Pas à jour") +
      " }"
    );
  }



  /**
   * Met les informations sur le membre en une String développée de plusieurs lignes
   * @return la String descriptive
   */
  public String toLongString() {
    Calendar dateNaiss = Calendar.getInstance();
    dateNaiss.setTimeInMillis(naissance);

    String dateCotisation;
    if (dateDerniereCotisation == 0) {
      dateCotisation = "Jamais";
    } else {
      Calendar dateCotis = Calendar.getInstance();
      dateCotis.setTimeInMillis(dateDerniereCotisation);
      dateCotisation =
        dateCotis.get(Calendar.DAY_OF_MONTH) +
        "/" +
        dateCotis.get(Calendar.MONTH) +
        "/" +
        dateCotis.get(Calendar.YEAR);
    }

    return (
      "Membre " +
      getNomComplet() +
      "#" +
      getId() +
      " :" +
      "\n  Adresse : " +
      getAdresse() +
      "\n  Date de naissance : " +
      dateNaiss.get(Calendar.DAY_OF_MONTH) +
      "/" +
      dateNaiss.get(Calendar.MONTH) +
      "/" +
      dateNaiss.get(Calendar.YEAR) +
      "\n  Nombre de visites défrayées cette année : " +
      getVisitesDefrayeesAnnuel() +
      "\n  Date de dernière cotisation : " +
      dateCotisation +
      "\n  Cotisation à jour : " +
      (estAJourDeCotisation() ? "Oui" : "Non") +
      "\n  Année de la 1ère cotisation : " +
      anneePremiereCotisation +
      "\n}"
    );
  }
}
