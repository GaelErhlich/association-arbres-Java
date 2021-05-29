package et3.java.projet.entities.persons;

import java.util.Calendar;
import java.util.Date;

public class Membre extends Personne {

  private static long idAcc = 0;
  private long id;
  private String adresse;
  private long naissance;
  private short visitesAnneeCourante;
  private long dateDerniereCotisation;
  private short anneePremiereCotisation;

  public Membre(
    String nomComplet,
    long naissance,
    String adresse,
    long dateDerniereCotisation,
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

  @Override
  public String toString() {
    return "Membre#"+getId()+" { "
            + getNomComplet()
            +", " + adresse
            +", Visites (cette année) : " + visitesAnneeCourante
            +", Arrivée : " + anneePremiereCotisation
            +" }";
  }



  public String toLongString() {

    Calendar dateNaiss = Calendar.getInstance();
    dateNaiss.setTimeInMillis(naissance);

    String dateCotisation;
    if(dateDerniereCotisation == 0) {
      dateCotisation = "Jamais";
    }
    else {
      Calendar dateCotis = Calendar.getInstance();
      dateCotis.setTimeInMillis(dateDerniereCotisation);
      dateCotisation = dateCotis.get(Calendar.DAY_OF_MONTH)+"/"+dateCotis.get(Calendar.MONTH)+"/"+dateCotis.get(Calendar.YEAR);
    }

    return "Membre "+getNomComplet()+"#"+getId()+" :"
            +"\n  Adresse : "+adresse
            +"\n  Date de naissance : "+dateNaiss.get(Calendar.DAY_OF_MONTH)+"/"+dateNaiss.get(Calendar.MONTH)+"/"+dateNaiss.get(Calendar.YEAR)
            +"\n  Nombre de visites cette année : " + visitesAnneeCourante
            +"\n  Date de dernière cotisation : " + dateCotisation
            +"\n  Année de la 1ère cotisation : " + anneePremiereCotisation
            +"\n}";
  }
}
