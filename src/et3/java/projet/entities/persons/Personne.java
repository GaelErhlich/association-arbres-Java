package et3.java.projet.entities.persons;

/**
 * Représente une personne physique ou morale, avec un nom, une adresse, et un ID unique
 */
public class Personne {

  private static long idAcc = 0;
  private long id;
  private String nomComplet;
  private String adresse;

  /**
   * Constructeur par défaut de Personne
   * @param nomComplet Nom complet de la personne
   * @param adresse Adresse postale de la personne (String)
   */
  public Personne(String nomComplet, String adresse) {
    this.nomComplet = nomComplet;
    this.adresse = adresse;
    this.id = idAcc;
    this.idAcc += 1;
  }

  /**
   * Getter de l'identifiant de la personne
   * @return identifiant long integer
   */
  public long getId() {
    return this.id;
  }

  /**
   * Getter du nom complete de la personne
   * @return une String
   */
  public String getNomComplet() {
    return this.nomComplet;
  }

  /**
   * Donne une String avec le nom complet et l'identifiant de la personne
   * @return la String
   */
  public String getNomEtId() {
    return this.getNomComplet()+"#"+this.getId();
  }

  /**
   * Getter de l'adresse de la personne
   * @return adresse sous forme de String
   */
  public String getAdresse() {
    return adresse;
  }

  /**
   * Met les informations de la personne en String sur une seule ligne
   * @return la description String sur une ligne
   */
  @Override
  public String toString() {
    return "Personne#"+getId()+ " {" +
            "Nom : " + nomComplet +
            ", Adresse : '" + adresse + '\'' +
            "}";
  }

}
