package et3.java.projet.entities.trees;

import et3.java.projet.entities.persons.Membre;
import java.util.Date;

public class Visite {

  private static long idAcc = 0;
  private long id;
  private Date date;
  private long visiteur;
  private String compteRendu;
  private boolean estDefrayee;

  public Visite(long visiteur, Date date) {
    this.id = idAcc;
    this.date = date;
    this.visiteur = visiteur;
    Visite.idAcc += 1;
  }

  public Visite(long visiteur, Date date, String compteRendu) {
    this(visiteur, date);
    this.compteRendu = compteRendu;
  }

  public Date getDate() {
    return this.date;
  }

  public long getId() {
    return this.id;
  }

  public String getCompteRendu() {
    return compteRendu;
  }

  public void setCompteRendu(String compteRendu) {
    this.compteRendu = compteRendu;
  }


  @Override
  public String toString() {
    return "Visite#"+id+
            "\n  Date : " + date.toString() +
            "\n  Visiteur : " + visiteur +
            "\n  Compte-rendu : " + compteRendu +
            "\n  Défrayé : " + (estDefrayee ? "Oui" : "Non") +
            '\n';
  }
}
