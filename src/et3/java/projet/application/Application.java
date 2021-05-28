package et3.java.projet.application;

import et3.java.projet.entities.Municipalite;
import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.trees.Arbre;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    private Municipalite municipalite;
    private Association association;


    /**
     * Démarre la boucle principale de l'interface textuelle
     */
    public void startMainLoop()
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

                case "arbres":
                    startArbresLoop();
                    break;


                case "membres":
                    startMembresLoop();
                    break;


                case "visites":
                    startVisitesLoop();
                    break;


                case "comptabilité":
                    startComptabiliteLoop();
                    break;


                case "quitter":
                    System.out.println("Extinction du programme...");
                    doitEteindre = true;
                    break;

                default:
                    System.out.println("La commande '"+commande+"' n'est pas reconnue.");

            }
        }

    }



    /////////////////////////////////////////////////////////////
    //
    //					    Section ARBRES
    //
    /////////////////////////////////////////////////////////////


    /**
     * Démarre la boucle du sous-menu des arbres dans l'interface
     */
    private void startArbresLoop() {
        Scanner scanner = new Scanner(System.in);
        String commande = new String();
        boolean doitEteindre = false;

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
                    System.out.println("String vide ? "+ municipalite.getArbresStr());
                    break;



                case "remarquables":
                    System.out.println(municipalite.getArbresRemarquablesStr());
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
        return;
    }




    /////////////////////////////////////////////////////////////
    //
    //					  Section MEMBRES
    //
    /////////////////////////////////////////////////////////////


    /**
     * Démarre la boucle du sous-menu des membres dans l'interface
     */
    private void startMembresLoop() {
        Scanner scanner = new Scanner(System.in);
        String commande = new String();
        boolean doitEteindre = false;

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
        return;
    }






    /////////////////////////////////////////////////////////////
    //
    //					  Section VISITES
    //
    /////////////////////////////////////////////////////////////


    /**
     * Démarre la boucle du sous-menu des visites dans l'interface
     */
    private void startVisitesLoop() {
        Scanner scanner = new Scanner(System.in);
        String commande = new String();
        boolean doitEteindre = false;

        while(!doitEteindre) {

            System.out.println(
                    "\nTapez :"
                            + "\n> programmer : Définir une visite"
                            + "\n> rapport : Ajouter un compte-rendu à une visite"
                            + "\n> retour : Retourner au menu principal"
            );
            commande = scanner.nextLine();

            switch (commande.toLowerCase()) {




                case "programmer":
                    // TODO : Programmer une visite
                    break;




                case "rapport":
                    // TODO : Ajouter un compte-rendu à une visite
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
    }




    /////////////////////////////////////////////////////////////
    //
    //					Section COMPTABILITE
    //
    /////////////////////////////////////////////////////////////


    /**
     * Démarre la boucle du sous-menu de la comptabilité dans l'interface
     */
    private void startComptabiliteLoop() {

        Scanner scanner = new Scanner(System.in);
        String commande = new String();
        boolean doitEteindre = false;

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
        return;
    }












    /**
     * Initialise les variables de l'application, et lance l'application
     * @param arbres Liste des arbres existant d'avance
     */
    public void initialize(ArrayList<Arbre> arbres) {

        // Déclaration de la municipalité
        municipalite = new Municipalite();

        // Ajout des arbres à la municipalité
        for(Arbre arbre : arbres) {
            municipalite.addArbre(arbre);
        }

        // Association
        association = new Association();



        // Starting the application
        startMainLoop();
    }

}
