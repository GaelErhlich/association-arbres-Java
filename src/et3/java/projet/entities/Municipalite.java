package et3.java.projet.entities;

import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.persons.Personne;
import et3.java.projet.entities.trees.Arbre;
import et3.java.projet.entities.trees.Visite;
import et3.java.projet.entities.trees.exceptions.ArbreNotFoundException;
import et3.java.projet.entities.trees.exceptions.VisiteDejaProgrammeeException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Classe représentant la collectivité locale qui gère les arbres
 */
public class Municipalite extends Personne {

  private static long counter = 0;
  private TreeMap<Long, Arbre> arbresRemarquables;
  private Map<Long, Arbre> arbres;

  /**
   * Constructeur par défaut
   * @param nom Nom de la municipalité
   * @param adresse Adresse du siège de la municipalité
   */
  public Municipalite(String nom, String adresse) {
    super(nom, adresse);
    arbresRemarquables = new TreeMap<Long, Arbre>();
    arbres = new HashMap<Long, Arbre>();
  }

  /**
   * Donne un arbre de la municipalité à partir de son identifiant
   * @param id identifiant
   * @return l'arbre recherché
   * @throws ArbreNotFoundException si l'arbre n'est pas la liste de la commune
   */
  public Arbre getArbre(long id) throws ArbreNotFoundException {
    Arbre arbre = this.arbres.get(id);
    if (arbre == null) {
      throw new ArbreNotFoundException(id);
    }
    return arbre;
  }

  /**
   * Donne un tableau contenant tous les arbres remarquables de la municipalité
   * @return tableau d'Arbres
   */
  public Arbre[] getArbresRemarquables() {
    Object[] objArray = this.arbresRemarquables.values().toArray();
    Arbre[] a = new Arbre[objArray.length];
    System.arraycopy(objArray, 0, a, 0, objArray.length);
    return a;
  }

  /**
   * Donne la liste des arbres de la municipalité
   * @return un tableau d'Arbres
   */
  public Arbre[] getArbres() {
    Object[] objArray = this.arbres.values().toArray();
    Arbre[] a = new Arbre[objArray.length];
    int i = 0;
    for (Object o : objArray) {
      a[i++] = (Arbre) o;
    }
    return a;
  }

  /**
   * Ajouter un arbre à la liste de la municipalité
   * @param arbre l'Arbre à ajouter
   */
  public void addArbre(Arbre arbre) {
    boolean estRemarquable = arbre.estRemarquable();

    if (estRemarquable) {
      long derniereVisite = arbre.getDerniereVisiteDate();

      if (derniereVisite == 0) {
        derniereVisite = Municipalite.counter;
        Municipalite.counter -= 1;
      }
      arbresRemarquables.put(derniereVisite, arbre);
    }

    arbres.put(arbre.getId(), arbre);
  }

  /**
   * Retirer un arbre de la liste de la municipalité (à la mort de celui-ci)
   * @param arbre l'Arbre à supprimer
   */
  public void removeArbre(Arbre arbre) {
    if (arbre.estRemarquable()) {
      Long e = (Long) arbresRemarquables
        .entrySet()
        .stream()
        .filter(a -> a.getValue().getId() == arbre.getId())
        .map(a -> a.getKey())
        .toArray()[0];
      arbresRemarquables.remove(e);
    }
    arbres.remove(arbre.getId());
  }

  /**
   * Construit la liste des arbres sous forme String à afficher
   * @return la liste organisée en lignes
   */
  public String getArbresStr() {
    return this.arbres.values()
      .stream()
      .map(arbre -> arbre.toString())
      .reduce((curr, acc) -> acc + "\n" + curr)
      .orElse("");
  }

  /**
   * Construit la liste des arbres remarquables sous forme String à afficher
   * @return la liste organisée en lignes
   */
  public String getArbresRemarquablesStr() {
    return this.arbresRemarquables.values()
      .stream()
      .map(arbre -> arbre.toString())
      .reduce((curr, acc) -> acc + "\n" + curr)
      .orElse("");
  }

  /**
   * Indique si un arbre est absent de la liste
   * @param arbre l'arbre à chercher
   * @return true si l'arbre est absent, false s'il est présent
   */
  public boolean estCoupe(Long arbre) {
    return !this.arbres.containsKey(arbre);
  }
}
