package et3.java.projet.entities.persons;

import java.util.Date;

public class Membre extends Personne {

  private static long idAcc = 0;
  private long id;
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
    this.id = idAcc;
    Membre.idAcc += 1;
  }

  public long getId() {
    return this.id;
  }
}
