package et3.java.projet.entities.association.exceptions;

import java.util.Calendar;

public class BilanTropTotException extends Exception {

  public BilanTropTotException(Calendar c) {
    super(
      "Le bilan est annuel et ne peut se faire qu'apres le " +
      c.get(Calendar.DAY_OF_MONTH) +
      "/" +
      c.get(Calendar.MONTH) +
      "/" +
      c.get(Calendar.YEAR) +
      " ."
    );
  }
}
