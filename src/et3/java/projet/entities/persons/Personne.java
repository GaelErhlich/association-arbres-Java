package et3.java.projet.entities.persons;

/**
 * Représente une personne physique ou morale, avec un nom, une adresse, et un ID unique
 */
public class Personne {

  private static long idAcc = 0;
  private long id;
  private String nomComplet;
  private String adresse;

  public Personne(String nomComplet, String adresse) {
    this.nomComplet = nomComplet;
    this.adresse = adresse;
    this.id = idAcc;
    this.idAcc += 1;
  }

  public String getNomComplet() {
    return this.nomComplet;
  }

  public String getNomEtId() {
    return this.getNomComplet()+"#"+this.getId();
  }

  public long getId() {
    return this.id;
  }


  @Override
  public String toString() {
    return "Personne#"+getId()+ " {" +
            "Nom : " + nomComplet +
            ", Adresse : '" + adresse + '\'' +
            "}";
  }

}
