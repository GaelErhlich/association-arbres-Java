package et3.java.projet.entities.association.exceptions;

import java.util.Calendar;

/**
 * Exception levée lorsqu'on lance le bilan trop tôt après le précédent
 */
public class BilanTropTotException extends Exception {

  public BilanTropTotException(Calendar c) {
    super(
      "Le bilan est annuel et ne peut se faire qu'a partir du " +
      c.get(Calendar.DAY_OF_MONTH) +
      "/" +
      c.get(Calendar.MONTH) +
      "/" +
      c.get(Calendar.YEAR) +
      " ."
    );
  }
}
