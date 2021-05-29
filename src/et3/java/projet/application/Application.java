package et3.java.projet.application;

import et3.java.projet.entities.Municipalite;
import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.persons.exceptions.MembreNotFoundException;
import et3.java.projet.entities.trees.Arbre;
import et3.java.projet.entities.trees.Visite;
import et3.java.projet.entities.trees.exceptions.ArbreDejaRemarquableException;
import et3.java.projet.entities.trees.exceptions.ArbreNotFoundException;
import et3.java.projet.entities.trees.exceptions.VisiteNotFoundException;

import java.util.ArrayList;
import java.util.Calendar;
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
                    System.out.println("\nRentrez l'ID de l'arbre dont vous souhaitez connaître les informations :");
                    commande = scanner.nextLine();
                    try {
                        long id = Long.parseLong(commande);
                        try {
                            Arbre arbre = municipalite.getArbre(id);
                            System.out.println(arbre.toLongString());
                        }
                        catch (ArbreNotFoundException e) {
                            System.err.println("Aucun arbre n'a été trouvé avec l'identifiant "+id+".");
                        }

                    }catch (NumberFormatException e) {
                        System.err.println("L'identifiant spécifié "+commande+" est invalide. " +
                                "Seuls des nombres peuvent être des identifiants.");
                    }
                    break;




                case "liste":
                    System.out.println("\nChargement de la liste...");
                    System.out.println("\nListe de tous les arbres connus par la municipalité:\n"
                            +municipalite.getArbresStr());
                    break;



                case "remarquables":
                    System.out.println("\nListe de tous les arbres remarquables :\n"
                            +municipalite.getArbresRemarquablesStr());
                    break;



                case "remarquer":
                    System.out.println("\nRentrez l'ID de l'arbre nouvellement déclaré comme remarquable par la municipalité :");
                    commande = scanner.nextLine();
                    try {
                        long id = Long.parseLong(commande);
                        Arbre arbre = municipalite.getArbre(id);
                        arbre.rendreRemarquable(municipalite);
                        System.out.println("L'arbre a été marqué comme remarquable avec succès.");
                        System.out.println(arbre.toLongString());


                    }catch (NumberFormatException e) {
                        System.err.println("L'identifiant spécifié "+commande+" est invalide. " +
                                "Seuls des nombres peuvent être des identifiants.");
                    }catch (ArbreNotFoundException e) {
                        System.err.println("Aucun arbre n'a été trouvé avec l'identifiant "+e.id+".");
                    }catch (ArbreDejaRemarquableException e) {
                        System.err.println("L'arbre "+e.arbre.getId()+" était déjà marqué comme remarquable.");
                    }
                    break;




                case "ajouter":
                    try {

                        System.out.println("Indiquez l'identifiant donné par la mairie pour l'arbre planté :");
                        commande = scanner.nextLine();
                        long id = Long.parseLong(commande);
                        try {
                            municipalite.getArbre(id); // Une erreur sera lancée seulement si l'arbre est déjà en mémoire.
                            System.err.println("Un arbre avec cet identifiant est déjà dans la base de données.");
                            break;
                        }catch (ArbreNotFoundException e) { } // Si rien n'est trouvé, c'est normal et on continue.

                        float[] coordonnees = new float[2];
                        System.out.println("Indiquez la LATITUDE de l'arbre (forme 12.3493621) :");
                        commande = scanner.nextLine();
                        coordonnees[0] = Float.parseFloat(commande);
                        System.out.println("Indiquez la LONGITUDE de l'arbre (forme 12.3493621) :");
                        commande = scanner.nextLine();
                        coordonnees[1] = Float.parseFloat(commande);

                        System.out.println("Indiquez la hauteur de l'arbre (mètres, format 15.476) :");
                        commande = scanner.nextLine();
                        float hauteur = Float.parseFloat(commande);

                        System.out.println("Indiquez la circonférence de l'arbre (centimètres, format 67 sans virgule) :");
                        commande = scanner.nextLine();
                        int circonference = Integer.parseInt(commande);

                        System.out.println("Indiquez si l'arbre est adulte (OUI ou NON) :");
                        commande = scanner.nextLine();
                        boolean estAdulte = (commande.toUpperCase().equals("OUI"));

                        System.out.println("Indiquez l'adresse où l'arbre se situe :");
                        String adresse = scanner.nextLine();

                        System.out.println("Indiquez le genre (en classification) de l'arbre (ex: Populus) :");
                        String genre = scanner.nextLine();

                        System.out.println("Indiquez l'espèce de l'arbre (ex: Nigra) :");
                        String espece = scanner.nextLine();

                        System.out.println("Indiquez le nom commun de l'arbre (ex: Peuplier) :");
                        String nomCommun = scanner.nextLine();

                        Arbre arbre = new Arbre(
                                id, genre, espece, nomCommun, adresse, coordonnees, circonference, hauteur, estAdulte
                        );
                        municipalite.addArbre(arbre);
                        System.out.println(arbre.toLongString());
                    }
                    catch (NumberFormatException e) {
                        System.err.println("Un nombre était attendu ici. Vous n'avez pas donné une valeur au bon format.");
                    }
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
                    System.out.println("\nChargement de la liste des membres...");
                    System.out.println("\nListe des membres :\n"
                            +association.getMembresStr());
                    // TODO : Afficher la liste des membres
                    break;



                case "ajouter":
                    try {
                        System.out.println("Entrez le nom complet du nouveau membre :");
                        String nom = scanner.nextLine();

                        System.out.println("Entrez l'adresse du nouveau membre :");
                        String adresse = scanner.nextLine();


                        System.out.println("Saisie de la date de naissance. Tapez Entrée pour commencer...");
                        scanner.nextLine();

                        System.out.println("Indiquez le jour de naissance (17 pour 17 février 1975) :");
                        commande = scanner.nextLine();
                        int naissanceJour = Integer.parseInt(commande);

                        System.out.println("Indiquez le mois de naissance (02 pour 17 février 1975) :");
                        commande = scanner.nextLine();
                        int naissanceMois = Integer.parseInt(commande);

                        System.out.println("Indiquez l'année de naissance (1975 pour 17 février 1975) :");
                        commande = scanner.nextLine();
                        int naissanceAnnee = Integer.parseInt(commande);

                        Calendar c = Calendar.getInstance();
                        short anneeCourante = (short) c.get(Calendar.YEAR);
                        c.set(naissanceAnnee, naissanceMois, naissanceJour);


                        Membre membre = new Membre(nom, c.getTimeInMillis(), adresse, 0l, anneeCourante, (short)0 );
                        association.ajouterMembre(membre);
                        System.out.println("Membre ajouté avec succès.\n"
                                +membre.toLongString());

                    }
                    catch (NumberFormatException e) {
                        System.err.println("Vous n'avez pas donné une valeur du bon format. Un nombre était attendu.");
                    }


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
                            + "\n> information OU info : Consulter les informations relatives à une visite"
                            + "\n> programmer : Définir une visite"
                            + "\n> rapport : Ajouter un compte-rendu à une visite"
                            + "\n> acquitter : Indiquer qu'un membre a été défrayé"
                            + "\n> retour : Retourner au menu principal"
            );
            commande = scanner.nextLine();

            switch (commande.toLowerCase()) {


                case "information":
                case "info":
                    System.out.println("Entrez l'identifiant de la visite à consulter :");
                    commande = scanner.nextLine();

                    try {
                        long id = Long.parseLong(commande);
                        Visite visite = association.getVisite(id);
                        System.out.println(visite.toString());
                        // TODO : Donner des informations sur visite
                    }

                    catch (NumberFormatException e) {
                        System.err.println("Un identifiant ne peut contenir que des chiffres.");
                    }
                    catch (VisiteNotFoundException e) {
                        System.err.println("L'identifiant de visite "+e.id+" n'a pas pu être trouvé dans l'association.");
                    }
                    break;





                case "programmer":
                    try {

                        System.out.println("Renseignez l'identifiant de l'arbre à visiter :");
                        commande = scanner.nextLine();
                        long id = Long.parseLong(commande);
                        Arbre arbre = municipalite.getArbre(id);
                        if(arbre.estRemarquable()) {
                            System.out.println("Arbre valide.");
                        }else {
                            System.err.println("L'arbre spécifié a été trouvé mais n'est pas un arbre remarquable.");
                            break;
                        }

                        System.out.println("Donnez maintenant l'identifiant du membre effectuant la visite :");
                        commande = scanner.nextLine();
                        long mId = Long.parseLong(commande);
                        Membre membre = association.getMembre(mId);
                        System.out.println("Membre valide.");


                        System.out.println("Quel jour du mois voulez-vous programmer la visite ?");
                        commande = scanner.nextLine();
                        int jour = Integer.parseInt(commande);

                        System.out.println("Indiquez le numéro du mois :");
                        commande = scanner.nextLine();
                        int mois = Integer.parseInt(commande);

                        System.out.println("Indiquez l'année de la visite :");
                        commande = scanner.nextLine();
                        int annee = Integer.parseInt(commande);

                        System.out.println("Indiquez l'heure de la visite (14 pour 14:30, 11 pour 11:59) :");
                        commande = scanner.nextLine();
                        int heures = Integer.parseInt(commande);

                        System.out.println("Complétez l'heure de la visite avec le nombre de minutes (30 pour 14:30, 0 pour 11:00) :");
                        commande = scanner.nextLine();
                        int minutes = Integer.parseInt(commande);


                        Calendar c = Calendar.getInstance();
                        c.set(annee, mois, jour, heures, minutes);

                        Visite visite = new Visite(membre.getId(), c.getTimeInMillis() );
                        arbre.ajouterVisite(visite, association);

                        System.out.println("Visite ajoutée :\n"
                                +visite.toString() );

                        // TODO : Programmer une visite

                    }
                    catch (ArbreNotFoundException e) {
                        System.err.println("Aucun arbre n'a été trouvé avec l'identifiant "+e.id+".");
                    }
                    catch (MembreNotFoundException e) {
                        System.err.println("Aucun membre n'a été trouvé avec l'identifiant "+e.id+".");
                    }
                    catch (NumberFormatException e) {
                        System.err.println("Vous n'avez pas donné une valeur du bon format. Un nombre était attendu.");
                    }
                    break;




                case "rapport":
                    System.out.println("Indiquez l'identifiant de la visite à laquelle le compte-rendu sera ajouté :");
                    commande = scanner.nextLine();
                    try {
                        long id = Long.parseLong(commande);
                        Visite visite = association.getVisite(id);
                        System.out.println("Entrez le compte-rendu complet :");
                        commande = scanner.nextLine();
                        visite.setCompteRendu(commande);
                        System.out.println("\nLe compte-rendu a bien été enregistré.\n"
                                + visite.toString());
                        // TODO : Ajouter un compte-rendu à une visite
                    }

                    catch (NumberFormatException e) {
                        System.err.println("Un identifiant ne peut contenir que des chiffres.");
                    }
                    catch (VisiteNotFoundException e) {
                        System.err.println("L'identifiant de visite "+e.id+" n'a pas pu être trouvé dans l'association.");
                    }
                    break;




                case "acquitter":
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
