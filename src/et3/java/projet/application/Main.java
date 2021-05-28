package et3.java.projet.application;

import et3.java.projet.data.FileReader;
import et3.java.projet.entities.trees.Arbre;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    ArrayList<Arbre> arbres = chargeDatabase(args[0]);
    System.out.println(arbres.size());

    Application app = new Application();
    app.initialize(arbres);
  }

  private static ArrayList<Arbre> chargeDatabase(String path) {
    ArrayList<Arbre> arbres = new ArrayList<Arbre>();

    File tempFile = new File(path);

    if (tempFile.exists()) {
      System.out.println("[Main] Reading the file " + path + " ...");

      //We start by reading the CSV file
      arbres = FileReader.getDataFromCSVFile(path);

      System.out.println("[Main] End of the file " + path + ".");
    } else {
      System.out.println("[Main] No file " + path);
    }

    return arbres;
  }
}
