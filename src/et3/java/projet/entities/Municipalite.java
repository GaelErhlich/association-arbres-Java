package et3.java.projet.entities;

import et3.java.projet.entities.trees.Arbre;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Municipalite {

  private TreeMap<Long, Arbre> arbresRemarquables;
  private Map<Long, Arbre> arbres;

  public Municipalite() {
    arbresRemarquables = new TreeMap<Long, Arbre>();
    arbres = new HashMap<Long, Arbre>();
  }

  public Arbre getArbre(long id) {
    return this.arbres.get(id);
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
      arbresRemarquables.put(arbre.getDerniereVisite(), arbre);
    }

    arbres.put(arbre.getId(), arbre);
  }

  public void removeArbre(Arbre arbre) {
    if (arbre.estRemarquable()) {
      arbresRemarquables.remove(arbre.getDerniereVisite());
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
}
