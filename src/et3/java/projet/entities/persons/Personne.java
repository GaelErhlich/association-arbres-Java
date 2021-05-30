package et3.java.projet.entities.persons;

public class Personne {

  private static long idAcc = 0;
  private long id;
  private String nomComplet;

  public Personne(String nomComplet) {
    this.nomComplet = nomComplet;
    this.id = idAcc;
    this.idAcc += 1;
  }

  public String getNomComplet() {
    return this.nomComplet;
  }

  public long getId() {
    return this.id;
  }
}
