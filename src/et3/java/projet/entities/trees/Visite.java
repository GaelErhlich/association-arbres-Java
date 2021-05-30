package et3.java.projet.entities.trees;

import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.trees.exceptions.VisiteDejaDefrayeeException;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe représentant une visite sur un arbre réel
 */
public class Visite {

  private static long idAcc = 0;
  private long id;
  private long date;
  private long visiteur;
  private String compteRendu = "";
  private boolean estDefrayee;

  /**
   * Constructeur de Visite sans compte-rendu
   * @param visiteur l'identifiant du visiteur
   * @param date la date (millisecondes depuis 01/01/1970)
   */
  public Visite(long visiteur, long date) {
    this.id = idAcc;
    this.date = date;
    this.visiteur = visiteur;
    Visite.idAcc += 1;
  }

  /**
   * Constructeur de Visite avec compte-rendu
   * @param visiteur l'identifiant du visiteur
   * @param date la date (millisecondes depuis 01/01/1970)
   * @param compteRendu Compte-rendu de la visite (String)
   */
  public Visite(long visiteur, long date, String compteRendu) {
    this(visiteur, date);
    this.compteRendu = compteRendu;
  }

  /**
   * Getter de l'identifiant de la visite
   * @return l'identifiant en long integer
   */
  public long getId() {
    return this.id;
  }

  /**
   * Getter de la date de la visite
   * @return date de la visite (millisecondes depuis 01/01/1970)
   */
  public long getDate() {
    return this.date;
  }

  /**
   * Getter de l'identifiant du visiteur de cette visite
   * @return l'identifiant en long integer
   */
  public long getVisiteurId() {
    return visiteur;
  }

  /**
   * Getter du compte-rendu de la visite
   * @return le compte-rendu (String)
   */
  public String getCompteRendu() {
    return compteRendu;
  }

  /**
   * Setter du compte-rendu de la visite
   * @param compteRendu un texte (String)
   */
  public void setCompteRendu(String compteRendu) {
    this.compteRendu = compteRendu;
  }


  /**
   * Getter pour savoir si le visiteur a été défrayé à l'heure actuelle
   * @return true si défrayé, false si pas (encore) défrayé
   */
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


  /**
   * Met les informations relatives à la visite en String
   * @return une String descriptive sur plusieurs lignes
   */
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
