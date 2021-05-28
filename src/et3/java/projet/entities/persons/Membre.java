package et3.java.projet.entities.persons;

import java.util.Date;

public class Membre extends Personne {

  private String adresse;
  private Date naissance;
  private short visitesAnneeCourante;
  private Date dateDerniereCotisation;
  private short anneePremiereCotisation;

  public Membre(
    String nomComplet,
    Date naissance,
    String adresse,
    Date dateDerniereCotisation,
    short anneePremiereCotisation,
    short visitesAnneeCourante
  ) {
    super(nomComplet);
    this.adresse = adresse;
    this.naissance = naissance;
    this.visitesAnneeCourante = visitesAnneeCourante;
    this.dateDerniereCotisation = dateDerniereCotisation;
    this.anneePremiereCotisation = anneePremiereCotisation;
  }

  @Override
  public String toString() {
    return "Membre{" +
            "adresse='" + adresse + '\'' +
            ", naissance=" + naissance +
            ", visitesAnneeCourante=" + visitesAnneeCourante +
            ", dateDerniereCotisation=" + dateDerniereCotisation +
            ", anneePremiereCotisation=" + anneePremiereCotisation +
            '}';
  }

  public String toLongString() {
    return "Membre "+getNomComplet()+" :"
            +"\n  Adresse : "+adresse
            +"\n  Date de naissance : "+naissance
            +"\n  Nombre de visites cette année : " + visitesAnneeCourante
            +"\n  Date de dernière cotisation : " + dateDerniereCotisation
            +"\n  Année de la 1ère cotisation : " + anneePremiereCotisation
            +"\n}";
  }
}
