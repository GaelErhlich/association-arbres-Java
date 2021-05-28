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
				"\nTapez :"
						+"\n> arbres : Ajouter et gérer les arbres"
						+"\n> membres : Ajouter et gérer les adhérents"
						+"\n> visites : Gérer les visites sur des arbres"
						+"\n> comptabilité : Gérer les finances de l'association"
						+"\n> quitter : Fermer l'application"
			);
			String commande = scanner.nextLine();

			switch (commande.toLowerCase()) {





				/////////////////////////////////////////////////////////////
				//
				//					  Section ARBRES
				//
				/////////////////////////////////////////////////////////////



				case "arbres":

					while(!doitEteindre) {

						System.out.println(
								"\nTapez :"
										+ "\n> information OU info : Consulter les information sur un arbre"
										+ "\n> liste : Consulter la liste des arbres de la ville"
										+ "\n> remarquables : Afficher la liste des arbres remarquables (en partant du moins récemment visité)"
										+ "\n> remarquer : Définit un arbre comme remarquable (d'après notification de la municipalité)"
										+ "\n> ajouter : Ajouter un arbre à base de données (d'après notification de la municipalité)"
										+ "\n> supprimer : Retirer un arbre de la base de données (d'après notification de la municipalité)"
										+ "\n> retour : Retourner au menu principal"
						);
						commande = scanner.nextLine();

						switch (commande.toLowerCase()) {




							case "information":
							case "info":
								// TODO : Afficher les informations sur un arbre
								break;




							case "liste":
								// TODO : Afficher la liste des arbres
								break;



							case "remarquables":
								// TODO : Consulter la liste des arbres remarquables
								break;



							case "remarquer":
								// TODO : Définir un arbre comme remarquable
								break;




							case "ajouter":
								// TODO : Ajouter un arbre
								break;




							case "supprimer":
								// TODO : Retirer un arbre de la liste
								break;





							case "retour":
								doitEteindre = true;
								break;

							default:
								System.out.println("La commande '"+commande+"' n'est pas reconnue.");
						}
						System.out.println("\nTapez Entrée pour continuer.");
						scanner.nextLine();
					}
					doitEteindre = false;
					break;







				/////////////////////////////////////////////////////////////
				//
				//					  Section MEMBRES
				//
				/////////////////////////////////////////////////////////////

				case "membres":

					while(!doitEteindre) {

						System.out.println(
								"\nTapez :"
										+ "\n> information OU info : Consulter les informations sur un membre"
										+ "\n> arbre : Ajouter un arbre à la liste de souhaits d'un membre"
										+ "\n> liste : Consulter la liste des membres"
										+ "\n> ajouter : Déclarer l'arrivée d'un nouveau membre"
										+ "\n> exclure : Retirer un membre de l'association"
										+ "\n> retour : Retourner au menu principal"
						);
						commande = scanner.nextLine();

						switch (commande.toLowerCase()) {


							case "information":
							case "info":
								// TODO : Afficher des informations sur un membre
								break;



							case "arbre":
								// TODO : Ajouter un arbre aux souhaits d'un membre
								break;



							case "liste":
								// TODO : Afficher la liste des membres
								break;



							case "ajouter":
								// TODO : Ajouter un membre
								break;



							case "exclure":
								// TODO : Supprimer un membre
								break;



							case "retour":
								doitEteindre = true;
								break;

							default:
								System.out.println("La commande '"+commande+"' n'est pas reconnue.");
						}
						System.out.println("\nTapez Entrée pour continuer.");
						scanner.nextLine();
					}
					doitEteindre = false;
					break;





				/////////////////////////////////////////////////////////////
				//
				//					  Section VISITES
				//
				/////////////////////////////////////////////////////////////



				case "visites":

					while(!doitEteindre) {

						System.out.println(
								"\nTapez :"
										+ "\n> programmer : Définir une visite"
										+ "\n> retour : Retourner au menu principal"
						);
						commande = scanner.nextLine();

						switch (commande.toLowerCase()) {




							case "programmer":
								// TODO : Programmer une visite
								break;





							case "retour":
								doitEteindre = true;
								break;

							default:
								System.out.println("La commande '"+commande+"' n'est pas reconnue.");
						}
						System.out.println("\nTapez Entrée pour continuer.");
						scanner.nextLine();
					}
					doitEteindre = false;
					break;









				/////////////////////////////////////////////////////////////
				//
				//					Section COMPTABILITE
				//
				/////////////////////////////////////////////////////////////



				case "comptabilité":

					while(!doitEteindre) {

						System.out.println(
								"\nTapez :"
										+ "\n> transaction : Déclarer une rentrée ou une sortie d'argent"
										+ "\n> bilan : Afficher le bilan de l'exercice annuel, et possiblement le valider"
										+ "\n> retour : Retourner au menu principal"
						);
						commande = scanner.nextLine();

						switch (commande.toLowerCase()) {




							case "transaction":
								// TODO : Ajouter une ligne à la comptabilité de l'association
								break;




							case "bilan":
								// TODO : Afficher le bilan de l'exercice budgétaire de l'année et permet de boucler
								break;





							case "retour":
								doitEteindre = true;
								break;

							default:
								System.out.println("La commande '"+commande+"' n'est pas reconnue.");
						}
						System.out.println("\nTapez Entrée pour continuer.");
						scanner.nextLine();
					}
					doitEteindre = false;
					break;











				/////////////////////////////////////////////////////////////
				//
				//					  Fin de la boucle
				//
				/////////////////////////////////////////////////////////////


				case "quitter":
					System.out.println("Extinction du programme...");
					doitEteindre = true;
					break;

				default:
					System.out.println("La commande '"+commande+"' n'est pas reconnue.");

			}
		}

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
