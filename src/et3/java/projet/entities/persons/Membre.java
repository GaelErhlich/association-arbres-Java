package et3.java.projet.entities.persons;

import et3.java.projet.entities.Municipalite;
import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.persons.exceptions.MembreCotisationDejaPayeeException;
import et3.java.projet.entities.trees.exceptions.ArbreNotFoundException;

import java.util.Calendar;

public class Membre extends Personne {

  private String adresse;
  private long naissance;
  private short visitesAnneeCourante;
  private long dateDerniereCotisation;
  private short anneePremiereCotisation;
  private Long[] arbresSouhaites = new Long[5];

  public Membre(
    String nomComplet,
    long naissance,
    String adresse,
    long dateDerniereCotisation,
    short anneePremiereCotisation,
    short visitesAnneeCourante
  ) {
    super(nomComplet, adresse);
    this.naissance = naissance;
    this.visitesAnneeCourante = visitesAnneeCourante;
    this.dateDerniereCotisation = dateDerniereCotisation;
    this.anneePremiereCotisation = anneePremiereCotisation;
  }


  public boolean estAJourDeCotisation() {
    Calendar c = Calendar.getInstance();
    int anneeCourante = c.get(Calendar.YEAR);

    c.setTimeInMillis(dateDerniereCotisation);
    return c.get(Calendar.YEAR) >= anneeCourante;
  }

  public short getVisitesAnneeCourante() {
    return visitesAnneeCourante;
  }

  public void incrementeVisitesAnneeCourante() {
    this.visitesAnneeCourante++;
  }

  /**
   * /!\ Ne pas appeler directement (passer par l'association)
   * Définit la date de dernière cotisation du membre comme aujourd'hui.
   * @param association l'association dont le membre est membre
   * @throws MembreCotisationDejaPayeeException
   */
  public void validerCotisation(Association association) throws MembreCotisationDejaPayeeException {
    if(estAJourDeCotisation()) {
      throw new MembreCotisationDejaPayeeException(this, association);
    }

    dateDerniereCotisation = Calendar.getInstance().getTimeInMillis();
  }



  public String getArbresSouhaitesStr(Municipalite municipalite) {
    StringBuilder stringBuilder = new StringBuilder();

    for(int i=0; i<arbresSouhaites.length; i++) {

      if(arbresSouhaites[i] != null) { // On vérifie que l'arbre existe toujours. Si oui, on l'ajoute au stringBuilder.
        try {
          municipalite.getArbre( arbresSouhaites[i] );
          stringBuilder.append( arbresSouhaites[i].toString() + "\n" );
        }catch (ArbreNotFoundException e) { // Si non, on le met à null directement.
          arbresSouhaites[i] = null;
        }
      }

      if(arbresSouhaites[i] == null) { // Si pas de valeur (null), on indique un emplacement libre.
        stringBuilder.append("1 emplacement libre\n");
      }
    }

    return stringBuilder.toString();
  }


  public void ajouterSouhaitArbre(long id) {

    short i = 0;
    while(i<5 && arbresSouhaites[i] != null) { // On cherche la 1ère case libre du tableau.
      i++;
    }

    if(i == 5) { // Si on n'en a pas trouvé, on libère la dernière place.
      for(int k=0; k<4; k++) {
        arbresSouhaites[k] = arbresSouhaites[k+1];
      }
      i = 4; // On indique que la 1ère case libre est la dernière.
    }

    arbresSouhaites[i] = id;
  }



  @Override
  public String toString() {
    return "Membre#"+getId()+" { "
            + getNomComplet()
            +", " + adresse
            +", Visites (cette année) : " + visitesAnneeCourante
            +", Arrivée en " + anneePremiereCotisation
            +", Cotisation : " + (estAJourDeCotisation() ? "À jour" : "Pas à jour")
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
            +"\n  Cotisation à jour : " + (estAJourDeCotisation() ? "Oui" : "Non")
            +"\n  Année de la 1ère cotisation : " + anneePremiereCotisation
            +"\n}";
  }
}
