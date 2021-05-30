package et3.java.projet.entities.trees;

import et3.java.projet.entities.Municipalite;
import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.trees.exceptions.ArbreDejaRemarquableException;
import et3.java.projet.entities.trees.exceptions.VisiteDejaProgrammeeException;
import et3.java.projet.entities.trees.exceptions.VisiteNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe représentant un arbre de la base de données
 */
public class Arbre {

  private long id;
  private String genre;
  private String espece;
  private String nomCommun;
  private String adresse;
  private final float coordonnees[]; // 2 coordonnnées GPS
  private int circonference;
  private float hauteur;
  private boolean estAdulte;
  private boolean estRemarquable = false;
  private long remarquableDate;
  private ArrayList<Visite> lVisites;

  /**
   * Constructeur de Arbre
   * @param id identifiant municipal de l'arbre
   * @param genre Nom du genre de l'espèce de l'arbre
   * @param espece Nom de l'espèce de l'arbre
   * @param nomCommun Nom commun de l'espèce de l'arbre
   * @param adresse Adresse postale de l'arbre
   * @param coordonnees Coordonnées GPS de l'arbre (float[2])
   * @param circonference Circonférence de l'arbre en centimètres
   * @param hauteur Hauteur de l'arbre en mètres
   * @param estAdulte true si l'arbre est adulte, false s'il est jeune
   * @param estRemarquable true si l'arbre est considéré comme remarquable, false sinon
   * @param remarquableDate Date à laquelle le caractère remarquable a été reconnu (0 si inconnu, ms depuis 01/01/1970)
   */
  public Arbre(
    long id,
    String genre,
    String espece,
    String nomCommun,
    String adresse,
    float[] coordonnees,
    int circonference,
    float hauteur,
    boolean estAdulte,
    boolean estRemarquable,
    long remarquableDate
  ) {
    this(
      id,
      genre,
      espece,
      nomCommun,
      adresse,
      coordonnees,
      circonference,
      hauteur,
      estAdulte
    );
    this.estRemarquable = estRemarquable;
    this.remarquableDate = remarquableDate;
  }

  /**
   * Constructeur de Arbre
   * @param id identifiant municipal de l'arbre
   * @param genre Nom du genre de l'espèce de l'arbre
   * @param espece Nom de l'espèce de l'arbre
   * @param nomCommun Nom commun de l'espèce de l'arbre
   * @param adresse Adresse postale de l'arbre
   * @param coordonnees Coordonnées GPS de l'arbre (float[2])
   * @param circonference Circonférence de l'arbre en centimètres
   * @param hauteur Hauteur de l'arbre en mètres
   * @param estAdulte true si l'arbre est adulte, false s'il est jeune
   */
  public Arbre(
    long id,
    String genre,
    String espece,
    String nomCommun,
    String adresse,
    float[] coordonnees,
    int circonference,
    float hauteur,
    boolean estAdulte
  ) {
    this.id = id;
    this.genre = genre;
    this.espece = espece;
    this.nomCommun = nomCommun;
    this.adresse = adresse;
    this.coordonnees = coordonnees;
    this.circonference = circonference;
    this.hauteur = hauteur;
    this.estAdulte = estAdulte;
    lVisites = new ArrayList<Visite>();
  }

  /**
   * Getter de l'identifiant de l'arbre
   * @return l'identifiant, un long integer
   */
  public long getId() {
    return this.id;
  }

  /**
   * Getter pour savoir si l'arbre est remarquable
   * @return true si remarquable, false sinon
   */
  public boolean estRemarquable() {
    return this.estRemarquable;
  }

  /**
   * Getter de la dernière visite enregistrée sur l'arbre
   * @return la dernière Visite (passée ou future) sur l'arbre
   * @throws VisiteNotFoundException si il n'y a aucune visite
   */
  public Visite getDerniereVisite() throws VisiteNotFoundException {
    if (lVisites.size() == 0) {
      throw new VisiteNotFoundException(0);
    } else {
      return lVisites.get(lVisites.size() - 1);
    }
  }

  /**
   * Getter de la date de la dernière visite enregistrée sur cet arbre
   * @return 0 si aucune visite, vrai date sinon (millisecondes depuis 01/01/1970)
   */
  public long getDerniereVisiteDate() {
    if (lVisites.size() > 0) {
      return lVisites.get(lVisites.size() - 1).getDate();
    }
    return 0;
  }

  /**
   * Indique si la dernière visite enregistrée a déjà eu lieu
   * @return true si passée, false si encore une future visite
   */
  public boolean isDerniereVisitePassee() {
    Calendar calendarDerniere = Calendar.getInstance();
    calendarDerniere.setTimeInMillis(getDerniereVisiteDate());
    Calendar calendarPresent = Calendar.getInstance();

    return calendarPresent.after(calendarDerniere);
  }

  /**
   * Ajoute une visite à la liste des visites de l'arbre et à celle de l'association
   * @param visite la visite à ajouter à l'arbre
   * @param association l'association à mettre à jour aussi
   * @throws VisiteDejaProgrammeeException si une visite est déjà prévue sur cet arbre
   */
  public void ajouterVisite(Visite visite, Association association)
    throws VisiteDejaProgrammeeException {
    // On vérifie si l'arbre ne vas pas déjà être visité dans le futur.
    if (!isDerniereVisitePassee()) {
      try {
        Visite visiteOriginale = getDerniereVisite();
        throw new VisiteDejaProgrammeeException(this, visiteOriginale, visite);
      } catch (VisiteNotFoundException e) {
        e.printStackTrace();
      }
    }

    lVisites.add(visite);
    association.addVisiteListeComplete(visite);
  }

  /**
   * Rend un arbre remarquable et ajoute l'arbre à la liste des remarquables de la municipalité
   * @param municipalite Municipalité à mettre à jour
   * @throws ArbreDejaRemarquableException si l'arbre est déjà remarquable
   */
  public void rendreRemarquable(Municipalite municipalite)
    throws ArbreDejaRemarquableException {
    if (this.estRemarquable()) throw new ArbreDejaRemarquableException(this);

    municipalite.removeArbre(this);
    this.estRemarquable = true;
    municipalite.addArbre(this);
  }

  /**
   * Met les informations essentielles de l'arbre en 1 String d'une seule ligne
   * @return une String d'une ligne
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    Calendar c = Calendar.getInstance();
    Date derniereVisite = this.estRemarquable
      ? new Date(this.getDerniereVisiteDate())
      : null;

    if (this.estRemarquable) {
      c.setTime(derniereVisite);
    }

    str
      .append("Arbre#")
      .append(id)
      .append("(")
      .append(nomCommun)
      .append("|")
      .append(espece)
      .append("|")
      .append(genre)
      .append("|@")
      .append(adresse)
      .append(
        estRemarquable
          ? "|Dernière visite :" +
          c.get(Calendar.DAY_OF_MONTH) +
          "/" +
          (c.get(Calendar.MONTH) + 1) +
          "/" +
          c.get(Calendar.YEAR)
          : ""
      )
      .append(")");

    return str.toString();
  }

  /**
   * Met les informations de l'arbre dans une String développée
   * @return la String descriptive
   */
  public String toLongString() {
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(getDerniereVisiteDate());
    String derniereVisite =
      (
        estRemarquable
          ? ", Dernière visite :" +
          c.get(Calendar.DAY_OF_WEEK) +
          "/" +
          (c.get(Calendar.MONTH) + 1) +
          "/" +
          c.get(Calendar.YEAR)
          : ""
      );

    return (
      "Arbre#" +
      getId() +
      "{" +
      "genre='" +
      genre +
      '\'' +
      ", espece='" +
      espece +
      '\'' +
      ", nomCommun='" +
      nomCommun +
      '\'' +
      ", adresse='" +
      adresse +
      '\'' +
      ", coordonnees=" +
      Arrays.toString(coordonnees) +
      ", circonference=" +
      circonference +
      ", hauteur=" +
      hauteur +
      ", Maturité : " +
      (estAdulte ? "Adulte" : "Jeune") +
      ", " +
      (estRemarquable ? "Remarquable" : "Non-remarquable") +
      derniereVisite +
      ", Visites=" +
      lVisites.size() +
      '}'
    );
  }
}
