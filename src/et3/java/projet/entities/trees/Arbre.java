package et3.java.projet.entities.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

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
  private Date remarquableDate;
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
    Date remarquableDate
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

  public void ajouterVisite(Visite visite) {
    lVisites.add(visite);
  }

  public boolean estRemarquable() {
    return this.estRemarquable;
  }

  public long getDerniereVisite() {
    if (lVisites.size() > 0) {
      return lVisites.get(lVisites.size() - 1).getDate().getTime();
    }
    return -1;
  }

  public long getId() {
    return this.id;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    Calendar c = Calendar.getInstance();
    Date derniereVisite = this.estRemarquable
      ? new Date(this.getDerniereVisite())
      : null;

    if (this.estRemarquable) {
      c.setTime(derniereVisite);
    }

    str
      .append("Arbre#")
      .append(id)
      .append("(")
      .append(nomCommun)
      .append("/")
      .append(espece)
      .append("/")
      .append(genre)
      .append("|@")
      .append(adresse)
      .append(
        estRemarquable
          ? "|Dernière visite :" +
          c.get(Calendar.DAY_OF_WEEK) +
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
    return (
      "Arbre{" +
      "id=" +
      id +
      ", genre='" +
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
      ", estAdulte=" +
      estAdulte +
      ", estRemarquable=" +
      estRemarquable +
      ", remarquableDate=" +
      remarquableDate +
      ", lVisites=" +
      lVisites +
      '}'
    );
  }
}
