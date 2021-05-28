package et3.java.projet.entities.trees;

import java.util.ArrayList;
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
    String genre,
    String espece,
    String nomCommun,
    String adresse,
    float[] coordonnees,
    int circonference,
    float hauteur,
    boolean estAdulte
  ) {
    this.genre = genre;
    this.espece = espece;
    this.nomCommun = nomCommun;
    this.adresse = adresse;
    this.coordonnees = coordonnees;
    this.circonference = circonference;
    this.hauteur = hauteur;
    this.estAdulte = estAdulte;
  }

  public void ajouterVisite(Visite visite) {
    lVisites.add(visite);
  }

  public boolean estRemarquable() {
    return this.estRemarquable;
  }

  public long getDerniereVisite() {
    return lVisites.get(lVisites.size() - 1).getDate().getTime();
  }

  public long getId() {
    return this.id;
  }
}
