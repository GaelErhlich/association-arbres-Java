package et3.java.projet.operations;

import et3.java.projet.entities.persons.Personne;

public class Transaction {

  private Long personneId;
  private float montant;
  private String raison;

  public Transaction(Long personneId, float montant, String raison) {
    this.personneId = personneId;
    this.montant = montant;
    this.raison = raison;
  }

  public boolean estUneSortie() {
    return montant < 0;
  }

  @Override
  public String toString() {
    return "Transaction ("+ (estUneSortie() ? "Sortie" : "Entrée")+") {" +
            "Personne n°" + personneId +
            ", " + montant + "€" +
            ", Motif : '" + raison + '\'' +
            " }";
  }
}
