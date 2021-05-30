package et3.java.projet.operations;

/**
 * Classe représentant une opération dans des comptes, avec une Personne source/destinataire, un montant, et une raison
 */
public class Transaction {

  private Long personneId;
  private float montant;
  private String raison;

  public Transaction(Long personneId, float montant, String raison) {
    this.personneId = personneId;
    this.montant = montant;
    this.raison = raison;
  }

  public float getMontant() {
    return montant;
  }

  public boolean estUneSortie() {
    return getMontant() < 0;
  }

  public String getRaison() {
    return raison;
  }

  public Long getPersonneId() {
    return personneId;
  }

  @Override
  public String toString() {
    return "Transaction ("+ (estUneSortie() ? "Sortie" : "Entrée")+") {" +
            (personneId == null ? "" : "Personne n°"+personneId+",") +
            " " + montant + "€" +
            ", Motif : '" + raison + '\'' +
            " }";
  }
}
