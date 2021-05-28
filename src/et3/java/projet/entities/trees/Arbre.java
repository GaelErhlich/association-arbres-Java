package et3.java.projet.entities.trees;

import java.util.ArrayList;
import java.util.Date;

public class Arbre {

  private String genre;
  private String espece;
  private String nomCommun;
  private String adresse;
  private float coordonnees[]; // 2 coordonnnées GPS
  private int circonference;
  private float hauteur;
  private boolean estAdulte;
  private boolean estRemarquable;
  private Date remarquableDate;
  private ArrayList<Visite> lVisites;

  public Arbre(
    String genre,
    String espece,
    String nomCommun,
    String adresse,
    float latitude,
    float longitude,
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
      latitude,
      longitude,
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
    float latitude,
    float longitude,
    int circonference,
    float hauteur,
    boolean estAdulte
  ) {
    this.genre = genre;
    this.espece = espece;
    this.nomCommun = nomCommun;
    this.adresse = adresse;
    this.coordonnees[0] = latitude;
    this.coordonnees[1] = longitude;
    this.circonference = circonference;
    this.hauteur = hauteur;
    this.estAdulte = estAdulte;
  }

  public void ajouterVisite(Visite visite) {
    lVisites.add(visite);
  }
}
