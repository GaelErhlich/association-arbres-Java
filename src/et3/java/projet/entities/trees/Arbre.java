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

  public boolean estRemarquable() {
    return this.estRemarquable;
  }

  public Visite getDerniereVisite() throws VisiteNotFoundException {
    if (lVisites.size() == 0) {
      throw new VisiteNotFoundException(0);
    } else {
      return lVisites.get(lVisites.size() - 1);
    }
  }

  public long getDerniereVisiteDate() {
    if (lVisites.size() > 0) {
      return lVisites.get(lVisites.size() - 1).getDate();
    }
    return 0;
  }

  public boolean isDerniereVisitePassee() {
    Calendar calendarDerniere = Calendar.getInstance();
    calendarDerniere.setTimeInMillis(getDerniereVisiteDate());
    Calendar calendarPresent = Calendar.getInstance();

    return calendarPresent.after(calendarDerniere);
  }

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

  public long getId() {
    return this.id;
  }

  public void rendreRemarquable(Municipalite municipalite)
    throws ArbreDejaRemarquableException {
    if (this.estRemarquable()) throw new ArbreDejaRemarquableException(this);

    municipalite.removeArbre(this);
    this.estRemarquable = true;
    municipalite.addArbre(this);
  }

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
          c.get(Calendar.MONTH) +
          "/" +
          c.get(Calendar.YEAR)
          : ""
      )
      .append(")");

    return str.toString();
  }

  public String toLongString() {
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(getDerniereVisiteDate());
    String derniereVisite =
      (
        estRemarquable
          ? ", Dernière visite :" +
          c.get(Calendar.DAY_OF_WEEK) +
          "/" +
          c.get(Calendar.MONTH) +
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
