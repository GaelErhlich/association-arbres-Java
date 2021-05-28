package et3.java.projet.entities.trees;

import et3.java.projet.entities.persons.Membre;
import java.util.Date;

public class Visite {

  private static long idAcc = 0;
  private long id;
  private Date date;
  private long visiteur;
  private String compteRendu;

  public Visite(long visiteur, Date date, String compteRendu) {
    this.id = idAcc;
    this.date = date;
    this.visiteur = visiteur;
    this.compteRendu = compteRendu;
    Visite.idAcc += 1;
  }

  public Date getDate() {
    return this.date;
  }

  public long getId() {
    return this.id;
  }
}
