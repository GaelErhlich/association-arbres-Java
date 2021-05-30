package et3.java.projet.operations;

/**
 * Classe représentant une opération dans des comptes, avec une Personne source/destinataire, un montant, et une raison
 */
public class Transaction {

  private Long personneId;
  private float montant;
  private String raison;

  /**
   * Constructeur de Transaction
   * @param personneId identifiant de la personne (physique ou morale) recevant ou donnant l'argent (peut être null)
   * @param montant la somme d'argent impliquée (€, float)
   * @param raison raison pour laquelle la transaction a lieu
   */
  public Transaction(Long personneId, float montant, String raison) {
    this.personneId = personneId;
    this.montant = montant;
    this.raison = raison;
  }

  /**
   * Getter pour la somme d'argent
   * @return de l'argent en €
   */
  public float getMontant() {
    return montant;
  }

  /**
   * Indique si la transaction est un gain ou une perte (montant - ou +)
   * @return true si sortie d'argent, false si entrée d'argent
   */
  public boolean estUneSortie() {
    return getMontant() < 0;
  }

  /**
   * Getter pour la raison de la transaction
   * @return brève description (String)
   */
  public String getRaison() {
    return raison;
  }

  /**
   * Getter de l'identifiant de la personne qui reçoit ou donne l'argent (peut être null)
   * @return un identifiant ou null
   */
  public Long getPersonneId() {
    return personneId;
  }

  /**
   * Mise des informations de la Transaction en String d'une ligne
   * @return une description sur 1 seule ligne
   */
  @Override
  public String toString() {
    return "Transaction ("+ (estUneSortie() ? "Sortie" : "Entrée")+") {" +
            (personneId == null ? "" : "Personne n°"+personneId+",") +
            " " + montant + "€" +
            ", Motif : '" + raison + '\'' +
            " }";
  }
}
