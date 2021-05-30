package et3.java.projet.operations;

import et3.java.projet.entities.persons.Personne;

public class Transaction {

  private Personne personne;
  private float montant;
  private String raison;

  public Transaction(Personne partie, float montant, String raison) {
    this.personne = partie;
    this.montant = montant;
    this.raison = raison;
  }
}
