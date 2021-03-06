package et3.java.projet.application;

import et3.java.projet.entities.Municipalite;
import et3.java.projet.entities.association.Association;
import et3.java.projet.entities.association.exceptions.BilanTropTotException;
import et3.java.projet.entities.persons.Membre;
import et3.java.projet.entities.persons.Personne;
import et3.java.projet.entities.persons.exceptions.DonateurDejaAjouteException;
import et3.java.projet.entities.persons.exceptions.DonateurNotFoundException;
import et3.java.projet.entities.persons.exceptions.MembreCotisationDejaPayeeException;
import et3.java.projet.entities.persons.exceptions.MembreNotFoundException;
import et3.java.projet.entities.trees.Arbre;
import et3.java.projet.entities.trees.Visite;
import et3.java.projet.entities.trees.exceptions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


/**
 * Classe gérant les interactions avec le programme
 */
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
                    """

                            Tapez :
                            > arbres : Ajouter et gérer les arbres
                            > membres : Ajouter et gérer les adhérents
                            > visites : Gérer les visites sur des arbres
                            > comptable : Gérer les finances de l'association
                            > quitter : Fermer l'application"""
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


                case "comptable":
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
        String commande;
        boolean doitEteindre = false;

        while(!doitEteindre) {

            System.out.println(
                    """

                            Tapez :
                            > information OU info : Consulter les information sur un arbre
                            > liste : Consulter la liste des arbres de la ville
                            > remarquables : Afficher la liste des arbres remarquables (en partant du moins récemment visité)
                            > remarquer : Définit un arbre comme remarquable (d'après notification de la municipalité)
                            > ajouter : Ajouter un arbre à base de données (d'après notification de la municipalité)
                            > supprimer : Retirer un arbre de la base de données (d'après notification de la municipalité)
                            > retour : Retourner au menu principal"""
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
                    System.out.println("\nRentrez l'identifiant de l'arbre nouvellement déclaré comme remarquable par la municipalité :");
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
                        }catch (ArbreNotFoundException ignored) { } // Si rien n'est trouvé, c'est normal et on continue.

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
                    System.out.println("\nRentrez l'ID de l'arbre à supprimer :");
                    commande = scanner.nextLine();
                    try {
                        long id = Long.parseLong(commande);
                        Arbre arbre = municipalite.getArbre(id);
                        System.out.println("Arbre supprimé :\n" +arbre.toString());
                        municipalite.removeArbre(arbre);
                        System.out.println("Suppression réussie.");

                    }catch (NumberFormatException e) {
                        System.err.println("L'identifiant spécifié "+commande+" est invalide. " +
                                "Seuls des nombres peuvent être des identifiants.");
                    }
                    catch (ArbreNotFoundException e) {
                        System.err.println("Aucun arbre n'a été trouvé avec l'identifiant "+e.id+".");
                    }
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
        String commande;
        boolean doitEteindre = false;

        while(!doitEteindre) {

            System.out.println(
                    """

                            Tapez :
                            > information OU info OU chercher : Consulter les informations sur un membre
                            > arbre : Ajouter un arbre à la liste de souhaits d'un membre
                            > liste : Consulter la liste des membres
                            > cotisé : Marquer un membre comme à jour de cotisation
                            > ajouter : Déclarer l'arrivée d'un nouveau membre
                            > exclure : Retirer un membre de l'association
                            > retour : Retourner au menu principal"""
            );
            commande = scanner.nextLine();

            switch (commande.toLowerCase()) {


                case "information":
                case "info":
                case "chercher":
                    try {
                        System.out.println("Indiquez le nom complet ou le début du nom complet du membre recherché :");
                        Membre[] membres = association.chercherMembre( scanner.nextLine() );
                        if(membres.length == 0) {
                            System.err.println("Aucun membre correspondant n'a pu être trouvé, avec ce début de nom.");
                            break;
                        }
                        else if(membres.length == 1) {
                            System.out.println("Une personne trouvée :\n"
                                    +membres[0].toLongString());
                        }
                        else {
                            System.out.println("Plusieurs résultats obtenus :");
                            for(Membre membre : membres) {
                                System.out.println(membre.toString());
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Vous n'avez pas donné une valeur du bon format. Un nombre était attendu.");
                    }
                    break;



                case "arbre":
                    try {

                        System.out.println("Indiquez l'identifiant du membre voulant ajouter un arbre à sa liste de souhaits :");
                        commande = scanner.nextLine();
                        long idMembre = Long.parseLong(commande);
                        Membre membre = association.getMembre(idMembre);

                        System.out.println("Indiquez l'identifiant de l'arbre à ajouter :");
                        commande = scanner.nextLine();
                        long idArbre = Long.parseLong(commande);
                        Arbre arbre = municipalite.getArbre(idArbre);

                        membre.ajouterSouhaitArbre( arbre.getId() );
                        System.out.println("Arbre ajouté à la liste avec succès.\nListe actuelle :\n"
                                +membre.getArbresSouhaitesStr(municipalite) );


                    }catch (NumberFormatException e) {
                        System.err.println("L'identifiant spécifié "+commande+" est invalide. " +
                                "Seuls des nombres peuvent être des identifiants.");
                    }catch (MembreNotFoundException e) {
                        System.err.println("Aucun membre n'a été trouvé avec l'identifiant "+e.id+".");
                    }catch (ArbreNotFoundException e) {
                        System.err.println("Aucun arbre n'a été trouvé avec l'identifiant "+e.id+".");
                    }
                    break;





                case "liste":
                    System.out.println("\nChargement de la liste des membres...");
                    System.out.println("\nListe des membres :\n"
                            +association.getMembresStr());
                    break;




                case "cotisé":
                    try {

                        System.out.println("Rappel : Le montant de la cotisation est "+association.getPrixCotisation()+"€.");
                        System.out.println("Indiquez l'identifiant du membre ayant nouvellement payé sa cotisation :");
                        commande = scanner.nextLine();
                        long id = Long.parseLong(commande);
                        Membre membre = association.getMembre(id);

                        association.validerCotisation(membre);
                        System.out.println(membre.getNomEtId()+" est maintenant à jour de cotisation. ("+association.getPrixCotisation()+"€)");

                    }
                    catch (NumberFormatException e) {
                        System.err.println("L'identifiant spécifié "+commande+" est invalide. " +
                                "Seuls des nombres peuvent être des identifiants.");
                    }
                    catch (MembreNotFoundException e) {
                        System.err.println("Aucun membre n'a été trouvé avec l'identifiant "+e.id+".");
                    }
                    catch (MembreCotisationDejaPayeeException e) {
                        System.err.println("Le membre "+e.membre.getNomEtId()+" est déjà à jour de cotisation.");
                    }
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


                        Membre membre = new Membre(nom, c.getTimeInMillis(), adresse, 0L, anneeCourante, (short)0 );
                        association.ajouterMembre(membre);
                        System.out.println("Membre ajouté avec succès.\n"
                                +membre.toLongString());

                    }
                    catch (NumberFormatException e) {
                        System.err.println("Vous n'avez pas donné une valeur du bon format. Un nombre était attendu.");
                    }


                    break;



                case "exclure":
                    try {

                        System.out.println("Indiquez l'identifiant du membre que vous souhaitez retirer :");
                        commande = scanner.nextLine();
                        long id = Long.parseLong(commande);
                        Membre membre = association.getMembre(id);

                        System.out.println("Supression du membre :\n" + membre.toLongString() );
                        association.retirerMembre(membre);
                        System.out.println("Membre supprimé avec succès.");

                    }
                    catch (NumberFormatException e) {
                        System.err.println("Vous n'avez pas donné une valeur du bon format. Un nombre était attendu.");
                    }catch (MembreNotFoundException e) {
                        System.err.println("Aucun membre n'a été trouvé avec l'identifiant "+e.id+".");
                    }
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
        String commande;
        boolean doitEteindre = false;

        while(!doitEteindre) {

            System.out.println(
                    """

                            Tapez :
                            > information OU info : Consulter les informations relatives à une visite
                            > programmer : Définir une visite
                            > rapport : Ajouter un compte-rendu à une visite
                            > acquitter OU défrayer : Indiquer qu'un membre a été défrayé
                            > retour : Retourner au menu principal"""
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
                    catch (VisiteDejaProgrammeeException e) {
                        System.err.println("Cette visite ne peut pas être programmée pour cet arbre, car une visite est déjà prévue.");
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
                    }

                    catch (NumberFormatException e) {
                        System.err.println("Un identifiant ne peut contenir que des chiffres.");
                    }
                    catch (VisiteNotFoundException e) {
                        System.err.println("L'identifiant de visite "+e.id+" n'a pas pu être trouvé dans l'association.");
                    }
                    break;




                case "défrayer":
                case "acquitter":
                    System.out.println("Rappel : Le montant remboursé en défraiement est "+association.getPrixCotisation()+"€.");
                    try {

                        System.out.println("Indiquez l'identifiant de la visite dont le visiteur doit être défrayé :");
                        commande = scanner.nextLine();
                        long id = Long.parseLong(commande);
                        System.out.println("Visite valide.");

                        association.defrayerVisite(id);
                        System.out.println("Le défraiement a été enregistré avec succès.");

                    }
                    catch (NumberFormatException e) {
                        System.err.println("Un identifiant ne peut contenir que des chiffres.");
                    }
                    catch (VisiteNotFoundException e) {
                        System.err.println("L'identifiant de visite "+e.id+" n'a pas pu être trouvé dans l'association.");
                    }
                    catch (VisiteDejaDefrayeeException e) {
                        System.err.println("La visite suivante a déjà été défrayée :\n"
                                +e.visite.toString() );
                    }
                    catch (MembreNotFoundException e) {
                        System.err.println("Il semblerait que le membre d'identifiant "+e.id+" ne soit plus dans l'association."
                                +"\nAucun remboursement n'aura donc lieu, car impossible de connaître son nombre de visites cette année.");
                    }
                    catch (MaxDefraiementsException e) {
                        System.err.println("Le membre "+e.membre.getNomEtId()+" a déjà atteint son nombre maximal de défraiements cette année.");
                    }
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
        String commande;
        boolean doitEteindre = false;

        while(!doitEteindre) {

            System.out.println(
                    """

                            Tapez :
                            > transaction OU tran : Déclarer une rentrée ou une sortie d'argent
                            > donateurs : Afficher la liste des donateurs enregistrés
                            > +donateur : Ajouter un donateur à la liste de l'association
                            > -donateur : Supprimer un donateur de la liste de l'association
                            > bilan : Afficher le bilan de l'exercice annuel, et possiblement le valider
                            > retour : Retourner au menu principal"""
            );
            commande = scanner.nextLine();

            switch (commande.toLowerCase()) {




                case "tran":
                case "transaction":
                    try {

                        System.out.println("Indiquez l'identifiant de la personne à qui est associée la transaction :"
                                +"\n"+municipalite.getId()+" pour la municipalité"
                                +"\n? pour aucun ou une personne sans identifiant"
                                +"\nAttention ! L'identifiant ne sera pas vérifié.");
                        commande = scanner.nextLine();
                        Long id = commande.equals("?") ? null : Long.parseLong(commande);

                        System.out.println("Indiquez le montant (en €) de la transaction :");
                        commande = scanner.nextLine();
                        float montant = Float.parseFloat(commande);

                        System.out.println("Indiquez le motif de la transaction :");
                        String raison = scanner.nextLine();


                        System.out.println("Transaction effectuée avec succès :\n"
                                + association.effectuerTransaction(id, montant, raison).toString() );
                        System.out.println("\nComptes actuels de l'association pour l'année :\n"+association.getTransactionsStr());

                    }
                    catch (NumberFormatException e) {
                        System.err.println("Le format de "+commande+" ne correspond pas au format de nombre attendu.");
                    }
                    break;





                case "donateurs":
                    System.out.println("Chargement de la liste des donateurs...\n");
                    System.out.println("Liste des donateurs :\n"+association.getDonateursStr());
                    break;







                case "+donateur":
                    try {
                        System.out.println("Indiquez le nom du nouveau donateur :");
                        String nom = scanner.nextLine();

                        System.out.println("Indiquez l'adresse du nouveau donateur :");
                        String adresse = scanner.nextLine();

                        Personne donateur = new Personne(nom, adresse);
                        association.ajouterDonateur(donateur);
                        System.out.println("Nouveau donateur ajouté avec succès :\n"
                                +donateur.toString());
                    }
                    // Dans la pratique, cette exception ne peut pas se présenter
                    // puisque l'on crée une nouvelle personne à chaque fois.
                    // Elle pourrait cependant servir avec une version améliorée de
                    // la commande qui accepterait des personnes déjà existantes.
                    catch (DonateurDejaAjouteException e) {
                        System.err.println("Ce donateur a déjà été ajouté à la liste.");
                    }
                    break;





                case "-donateur":
                    try {

                        System.out.println("Indiquez l'identifiant du donateur à supprimer de la liste :");
                        commande = scanner.nextLine();
                        long id = Long.parseLong(commande);

                        Personne donateur = association.retirerDonateur(id);
                        System.out.println("Donateur retiré avec succès :\n"
                                +donateur.toString());

                    }
                    catch (NumberFormatException e) {
                        System.err.println("Le format de "+commande+" ne correspond pas au format de nombre attendu.");
                    }
                    catch (DonateurNotFoundException e) {
                        System.err.println("Aucun donateur n'a été trouvé avec l'identifiant "+e.id+".");
                    }
                    break;





                case "bilan":
                    try {
						System.out.println(association.effectuerBilan(municipalite));
                        if(association.getDonateurs().length > 0){
                            System.out.println("Veuillez indiquer le montant de donation pour chaque donateur, si le donateur ne fait pas de don veuillez indiquer '0':");
                            for(Personne donateur: association.getDonateurs()){
                                System.out.print(donateur.getNomEtId()+ " :");
                                Float montant = Float.parseFloat(scanner.nextLine());
                                if(montant.intValue() != 0){
                                    association.effectuerTransaction(donateur.getId(),montant, "Don");
                                }
                            }
                            System.out.println("L'ensemble des dons ont été enregistré.");
                        }
                    }
                    catch (BilanTropTotException e) {
                        System.err.println(e.getMessage());
                    }

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
    }












    /**
     * Initialise les variables de l'application, et lance l'application
     * @param arbres Liste des arbres existant d'avance
     */
    public void initialize(ArrayList<Arbre> arbres) {

        // Déclaration de la municipalité
        municipalite = new Municipalite("Région Île-de-France", "2 Rue Simone Veil, 93400 Saint-Ouen");

        // Ajout des arbres à la municipalité
        for(Arbre arbre : arbres) {
            municipalite.addArbre(arbre);
        }

        // Association
        association = new Association();

        // try {
		// 	association.ajouterDonateur(new Personne("Lucas Dupont", "3 rue du boulevard"));
        //     association.ajouterDonateur(new Personne("Mathieu Dupont", "2 rue du boulevard"));
		// } catch (DonateurDejaAjouteException e) {
        //     System.out.println(e.getMessage());
		// }
        // Date now = new Date();
        // association.ajouterMembre(new Membre("Alex Durand", now.getTime(),"5 rue du boulevard", now.getTime(),(short) 2021, (short)0));
        // association.ajouterMembre(new Membre("Nicolas Dupont", now.getTime(),"8 rue du boulevard", now.getTime(),(short) 2021, (short)0));

        // Starting the application
        startMainLoop();
    }

}
