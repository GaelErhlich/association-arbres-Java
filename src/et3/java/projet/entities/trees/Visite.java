package et3.java.projet.entities.trees;

import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.trees.exceptions.VisiteDejaDefrayeeException;

import java.util.Calendar;
import java.util.Date;

public class Visite {

  private static long idAcc = 0;
  private long id;
  private long date;
  private long visiteur;
  private String compteRendu = "";
  private boolean estDefrayee;

  public Visite(long visiteur, long date) {
    this.id = idAcc;
    this.date = date;
    this.visiteur = visiteur;
    Visite.idAcc += 1;
  }

  public Visite(long visiteur, long date, String compteRendu) {
    this(visiteur, date);
    this.compteRendu = compteRendu;
  }

  public long getId() {
    return this.id;
  }

  public long getDate() {
    return this.date;
  }

  public long getVisiteurId() {
    return visiteur;
  }

  public String getCompteRendu() {
    return compteRendu;
  }

  public void setCompteRendu(String compteRendu) {
    this.compteRendu = compteRendu;
  }


  public boolean estDefrayee() {
    return estDefrayee;
  }


  /**
   * Change l'état de la visite en "défrayée".
   * Attention : N'ajoute pas la transaction à l'association.
   */
  public void rendreDefraye() throws VisiteDejaDefrayeeException {
    if(estDefrayee()) {
      throw new VisiteDejaDefrayeeException(this);
    }
    estDefrayee = true;
  }


  @Override
  public String toString() {
    Calendar dateC = Calendar.getInstance();
    dateC.setTimeInMillis(this.getDate());

    return "Visite#"+id+
            "\n  Date : " + dateC.get(Calendar.DAY_OF_MONTH)+"/"+dateC.get(Calendar.MONTH)+"/"+dateC.get(Calendar.YEAR)+
                    " - "+dateC.get(Calendar.HOUR)+"h"+dateC.get(Calendar.MINUTE)+
            "\n  Identifiant du visiteur : " + visiteur +
            "\n  Compte-rendu : " + ( compteRendu.length()==0 ? "Non-remis" : compteRendu) +
            "\n  Défrayé : " + (estDefrayee ? "Oui" : "Non") +
            '\n';
  }
}
