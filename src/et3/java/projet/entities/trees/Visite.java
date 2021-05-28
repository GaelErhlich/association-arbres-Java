package et3.java.projet.entities.trees;

import et3.java.projet.entities.persons.Membre;
import java.util.Date;

public class Visite {

  private Date date;
  private Membre visiteur;
  private String compteRendu;

  public Visite(Membre visiteur, Date date, String compteRendu) {
    this.date = date;
    this.visiteur = visiteur;
    this.compteRendu = compteRendu;
  }
}
