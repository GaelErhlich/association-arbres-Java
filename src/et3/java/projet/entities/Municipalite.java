package et3.java.projet.entities;

import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.persons.Personne;
import et3.java.projet.entities.trees.Arbre;
import et3.java.projet.entities.trees.Visite;
import et3.java.projet.entities.trees.exceptions.ArbreNotFoundException;
import java.util.*;
import java.util.Map.Entry;

public class Municipalite extends Personne {

  private static long counter = 0;
  private TreeMap<Long, Arbre> arbresRemarquables;
  private Map<Long, Arbre> arbres;

  public Municipalite(String nom, String adresse) {
    super(nom, adresse);
    arbresRemarquables = new TreeMap<Long, Arbre>();
    arbres = new HashMap<Long, Arbre>();
  }

  /*
  public boolean estArbreDansListeRemarquable(long id) { // TODO en construction
    return Arrays.stream(getArbresRemarquables()).anyMatch(arbre -> arbre.getId() == id); // TODO Peut-être à supprimer en fait ?
  }*/

  public Arbre getArbre(long id) throws ArbreNotFoundException {
    Arbre arbre = this.arbres.get(id);
    if (arbre == null) {
      throw new ArbreNotFoundException(id);
    }
    return arbre;
  }

  public Arbre[] getArbresRemarquables() {
    Object[] objArray = this.arbresRemarquables.values().toArray();
    Arbre[] a = new Arbre[objArray.length];
    System.arraycopy(objArray, 0, a, 0, objArray.length);
    return a;
  }

  public Arbre[] getArbres() {
    Object[] objArray = this.arbres.values().toArray();
    Arbre[] a = new Arbre[objArray.length];
    int i = 0;
    for (Object o : objArray) {
      a[i++] = (Arbre) o;
    }
    return a;
  }

  public void addArbre(Arbre arbre) {
    boolean estRemarquable = arbre.estRemarquable();

    if (estRemarquable) {
      long derniereVisite = arbre.getDerniereVisite();

      if (derniereVisite == 0) {
        derniereVisite = Municipalite.counter;
        Municipalite.counter -= 1;
      }
      arbresRemarquables.put(derniereVisite, arbre);
    }

    arbres.put(arbre.getId(), arbre);
  }

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

  public String getArbresStr() {
    return this.arbres.values()
      .stream()
      .map(arbre -> arbre.toString())
      .reduce((curr, acc) -> acc + "\n" + curr)
      .orElse("");
  }

  public String getArbresRemarquablesStr() {
    return this.arbresRemarquables.values()
      .stream()
      .map(arbre -> arbre.toString())
      .reduce((curr, acc) -> acc + "\n" + curr)
      .orElse("");
  }

  public void progammerVisite(
    long idArbre,
    long dateVisite,
    Membre membre,
    Association association
  ) {
    Visite newVisite = new Visite(membre.getId(), dateVisite);
    Arbre aRem = (Arbre) this.arbresRemarquables.values()
      .stream()
      .filter(arbre -> arbre.getId() == idArbre)
      .toArray()[0];

    aRem.ajouterVisite(newVisite, association);
    this.removeArbre(aRem);
    this.addArbre(aRem);
  }

  public boolean estCoupé(long id) {
    return !arbres.containsKey(id);
  }
}
