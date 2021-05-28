package et3.java.projet.application;

import java.io.File;
import java.util.Scanner;

import et3.java.projet.data.FileReader;

public class Main 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		boolean doitEteindre = false;


		while(!doitEteindre) {

			System.out.println(
				"Tapez :"
					+"\n> quitter : Fermer l'application"
					+"\n"
			);
			String commande = scanner.nextLine();

			switch (commande) {






				case "membre":
					System.out.println(
							"Tapez :"
							+"\n> "
							+"\n"
					);






				case "quitter":
					System.out.println("Extinction du programme...");
					doitEteindre = true;
					break;


				default:
					System.out.println("La commande '"+commande+"' n'est pas reconnue.");

			}

		}
		
		//TODO Project :)
	}


	private void chargeDatabase() {
		String path = "src\\et3\\java\\projet\\data\\data.csv";

		File tempFile = new File(path);

		if(tempFile.exists())
		{
			System.out.println("[Main] Reading the file " + path + " ...");

			//We start by reading the CSV file
			FileReader.getDataFromCSVFile(path);

			System.out.println("[Main] End of the file " + path + ".");
		}
		else
		{
			System.out.println("[Main] No file " + path);
		}
	}
}
