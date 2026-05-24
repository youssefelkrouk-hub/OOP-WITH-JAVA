import java.util.Date;

public class Main {

    // ================================================================
    //  Utilitaire : séparateur visuel pour les sections d'affichage
    // ================================================================
    static void titre(String texte) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  " + texte);
        System.out.println("=".repeat(60));
    }

    static void soustitre(String texte) {
        System.out.println("\n--- " + texte + " ---");
    }

    // ================================================================
    //  Affichage complet de l'état de la bibliothèque
    // ================================================================
    static void afficherEtat(Bibliotheque bib, String moment) {
        titre("ÉTAT DE LA BIBLIOTHÈQUE — " + moment);

        // ---- Livres ------------------------------------------------
        soustitre("LIVRES (" + bib.getlistLiv().size() + ")");
        if (bib.getlistLiv().isEmpty()) {
            System.out.println("  (aucun livre)");
        } else {
            System.out.printf("  %-6s %-22s %-20s %-8s %-8s%n",
                    "Code", "Titre", "Auteur", "Total", "Dispo");
            System.out.println("  " + "-".repeat(68));
            for (Livre l : bib.getlistLiv()) {
                System.out.printf("  %-6d %-22s %-20s %-8d %-8d%n",
                        l.cdLiv,
                        l.titre,
                        l.auteur.toString(),
                        l.nmbExp,
                        l.nmbExpD);
            }
        }

        // ---- Adhérents ---------------------------------------------
        soustitre("ADHÉRENTS (" + bib.getlistAdh().size() + ")");
        if (bib.getlistAdh().isEmpty()) {
            System.out.println("  (aucun adhérent)");
        } else {
            System.out.printf("  %-6s %-15s %-15s %-12s%n",
                    "Code", "Nom", "Prénom", "Date adhésion");
            System.out.println("  " + "-".repeat(52));
            for (Adherent a : bib.getlistAdh()) {
                System.out.printf("  %-6d %-15s %-15s %-12s%n",
                        a.cdAdh,
                        a.getnom(),
                        a.getprenom(),
                        a.dateAdh);
            }
        }

        // ---- Emprunts ----------------------------------------------
        soustitre("EMPRUNTS (" + bib.getlistEmp().size() + ")");
        if (bib.getlistEmp().isEmpty()) {
            System.out.println("  (aucun emprunt)");
        } else {
            System.out.printf("  %-6s %-22s %-15s %-12s %-12s%n",
                    "Code", "Livre", "Adhérent", "Date emp.", "État");
            System.out.println("  " + "-".repeat(72));
            for (Emprunt e : bib.getlistEmp()) {
                System.out.printf("  %-6d %-22s %-15s %-12s %-12s%n",
                        e.cdEmp,
                        e.livre.titre,
                        e.adh.getnom() + " " + e.adh.getprenom(),
                        e.dateEmp,
                        e.etatEmprunt());
            }
        }
    }

    // ================================================================
    //  MAIN
    // ================================================================
    public static void main(String[] args) {

        /* ---------------------------------------------------------- *
         *  1. Instanciation de la bibliothèque                        *
         * ---------------------------------------------------------- */
        titre("INITIALISATION DE LA BIBLIOTHÈQUE");
        Bibliotheque bib = new Bibliotheque();
        System.out.println("  Bibliothèque créée avec succès.");

        /* ---------------------------------------------------------- *
         *  2. Création des auteurs (avec gestion CodeAutException)    *
         * ---------------------------------------------------------- */
        titre("CRÉATION DES AUTEURS");

        Auteur auteur1 = null, auteur2 = null, auteur3 = null;

        // Cas normal : code valide (4 chiffres)
        try {
            auteur1 = new Auteur("Hugo", "Victor", 1234);
            System.out.println("  [OK] Auteur créé    : " + auteur1);
        } catch (CodeAutException e) {
            System.out.println("  [ERREUR] " + e.getMessage());
        }

        try {
            auteur2 = new Auteur("Camus", "Albert", 5678);
            System.out.println("  [OK] Auteur créé    : " + auteur2);
        } catch (CodeAutException e) {
            System.out.println("  [ERREUR] " + e.getMessage());
        }

        try {
            auteur3 = new Auteur("Zola", "Emile", 9999);
            System.out.println("  [OK] Auteur créé    : " + auteur3);
        } catch (CodeAutException e) {
            System.out.println("  [ERREUR] " + e.getMessage());
        }

        // Cas d'erreur volontaire : code invalide (< 4 chiffres)
        soustitre("Test CodeAutException — code invalide (42)");
        try {
            Auteur auteurInvalide = new Auteur("Inconnu", "Auteur", 42);
            System.out.println("  [OK] Auteur créé : " + auteurInvalide); // ne s'exécute pas
        } catch (CodeAutException e) {
            System.out.println("  [EXCEPTION CodeAutException] " + e.getMessage());
        }

        // Cas d'erreur volontaire : code invalide (> 4 chiffres)
        soustitre("Test CodeAutException — code invalide (12345)");
        try {
            Auteur auteurInvalide2 = new Auteur("Dupont", "Jean", 12345);
            System.out.println("  [OK] Auteur créé : " + auteurInvalide2); // ne s'exécute pas
        } catch (CodeAutException e) {
            System.out.println("  [EXCEPTION CodeAutException] " + e.getMessage());
        }

        /* ---------------------------------------------------------- *
         *  3. Création des livres et ajout à la bibliothèque          *
         * ---------------------------------------------------------- */
        titre("CRÉATION ET AJOUT DES LIVRES");

        // Livres normaux (plusieurs exemplaires disponibles)
        if (auteur1 != null) {
            Livre l1 = new Livre(101, "Les Misérables",      auteur1, 4, 4);
            Livre l2 = new Livre(102, "Notre-Dame de Paris", auteur1, 3, 3);
            bib.ajouterLivre(l1);
            System.out.println("  [OK] Livre ajouté   : " + l1);
            bib.ajouterLivre(l2);
            System.out.println("  [OK] Livre ajouté   : " + l2);
        }

        if (auteur2 != null) {
            Livre l3 = new Livre(103, "L'Étranger", auteur2, 5, 5);
            bib.ajouterLivre(l3);
            System.out.println("  [OK] Livre ajouté   : " + l3);
        }

        if (auteur3 != null) {
            Livre l4 = new Livre(104, "Germinal",   auteur3, 2, 2);
            // Livre avec 0 exemplaire disponible → indisponible dès le départ
            Livre l5 = new Livre(105, "Nana",       auteur3, 3, 0);
            bib.ajouterLivre(l4);
            System.out.println("  [OK] Livre ajouté   : " + l4);
            bib.ajouterLivre(l5);
            System.out.println("  [OK] Livre ajouté   : " + l5 + "  (0 exemplaire dispo → indisponible)");
        }

        // Test : ajouter un livre déjà présent → incrémente nmbExp
        soustitre("Test ajout d'un livre déjà existant (code 101)");
        if (auteur1 != null) {
            Livre l1bis = new Livre(101, "Les Misérables", auteur1, 4, 4);
            bib.ajouterLivre(l1bis);
            System.out.println("  [INFO] nmbExp après réajout : "
                    + bib.rechercherLivre(101).nmbExp);
        }

        /* ---------------------------------------------------------- *
         *  4. Création des adhérents et ajout à la bibliothèque       *
         * ---------------------------------------------------------- */
        titre("CRÉATION ET AJOUT DES ADHÉRENTS");

        Adherent adh1 = new Adherent("Benali",    "Yassine");
        Adherent adh2 = new Adherent("Elhassani", "Sara");
        Adherent adh3 = new Adherent("Moukrim",   "Karim");
        Adherent adh4 = new Adherent("Tazi",      "Nadia", new Date());

        bib.ajouterAdherent(adh1);
        System.out.println("  [OK] Adhérent ajouté : " + adh1);
        bib.ajouterAdherent(adh2);
        System.out.println("  [OK] Adhérent ajouté : " + adh2);
        bib.ajouterAdherent(adh3);
        System.out.println("  [OK] Adhérent ajouté : " + adh3);
        bib.ajouterAdherent(adh4);
        System.out.println("  [OK] Adhérent ajouté : " + adh4);

        // Test : ajouter un adhérent déjà présent
        soustitre("Test ajout d'un adhérent déjà présent");
        bib.ajouterAdherent(adh1); // doit afficher "Adhérent déjà présent."

        /* ---------------------------------------------------------- *
         *  État initial avant les emprunts                            *
         * ---------------------------------------------------------- */
        afficherEtat(bib, "AVANT LES EMPRUNTS");

        /* ---------------------------------------------------------- *
         *  5. Emprunts (avec gestion AdherentInexistantException)     *
         * ---------------------------------------------------------- */
        titre("RÉALISATION DES EMPRUNTS");

        // Emprunts normaux
        soustitre("Emprunt valide : adhérent " + adh1.cdAdh + " → livre 101");
        bib.ajouterEmprunt(adh1.cdAdh, 101);
        System.out.println("  [OK] Emprunt enregistré.");

        soustitre("Emprunt valide : adhérent " + adh2.cdAdh + " → livre 103");
        bib.ajouterEmprunt(adh2.cdAdh, 103);
        System.out.println("  [OK] Emprunt enregistré.");

        soustitre("Emprunt valide : adhérent " + adh3.cdAdh + " → livre 104");
        bib.ajouterEmprunt(adh3.cdAdh, 104);
        System.out.println("  [OK] Emprunt enregistré.");

        // Test : livre indisponible (nmbExpD == 0)
        soustitre("Test emprunt — livre indisponible (code 105)");
        bib.ajouterEmprunt(adh4.cdAdh, 105);
        // Bibliotheque affiche "Livre non disponible." en interne

        // Test : livre inexistant
        soustitre("Test emprunt — livre inexistant (code 999)");
        bib.ajouterEmprunt(adh1.cdAdh, 999);
        // Bibliotheque affiche "Livre introuvable." en interne

        // Test : adhérent inexistant (code 999)
        soustitre("Test emprunt — adhérent inexistant (code 999)");
        bib.ajouterEmprunt(999, 102);
        // Bibliotheque catch AdherentInexistantException et affiche le message

        /* ---------------------------------------------------------- *
         *  État après les emprunts                                    *
         * ---------------------------------------------------------- */
        afficherEtat(bib, "APRÈS LES EMPRUNTS");

        /* ---------------------------------------------------------- *
         *  6. Retours de livres (avec gestion RetourImpossibleException)
         * ---------------------------------------------------------- */
        titre("RETOURS DE LIVRES");

        // Récupérer le code du premier emprunt créé
        if (!bib.getlistEmp().isEmpty()) {
            int cdEmp1 = bib.getlistEmp().get(0).cdEmp;
            soustitre("Retour de l'emprunt n°" + cdEmp1
                    + " (" + bib.getlistEmp().get(0).livre.titre + ")");
            bib.retourLivre(cdEmp1);
            System.out.println("  [OK] Retour enregistré. État : "
                    + bib.getlistEmp().get(0).etatEmprunt());
        }

        if (bib.getlistEmp().size() >= 2) {
            int cdEmp2 = bib.getlistEmp().get(1).cdEmp;
            soustitre("Retour de l'emprunt n°" + cdEmp2
                    + " (" + bib.getlistEmp().get(1).livre.titre + ")");
            bib.retourLivre(cdEmp2);
            System.out.println("  [OK] Retour enregistré. État : "
                    + bib.getlistEmp().get(1).etatEmprunt());
        }

        // Test : RetourImpossibleException — on force un retour sur un livre
        // dont tous les exemplaires sont déjà disponibles
        soustitre("Test RetourImpossibleException — tous exemplaires déjà retournés");
        if (auteur2 != null) {
            Livre livreTest = new Livre(200, "La Peste", auteur2, 2, 2); // nmbExpD == nmbExp
            Emprunt empTest = new Emprunt(livreTest, adh1, new Date());
            System.out.print("  Tentative de retour d'un livre déjà complet... ");
            empTest.retourEmprunt(); // retourLivre() va lever RetourImpossibleException
            // Emprunt.retourEmprunt() catch l'exception et affiche le message
        }

        // Test : retour d'un emprunt inexistant (code fantaisiste)
        soustitre("Test retour d'un emprunt inexistant (code 9999)");
        bib.retourLivre(9999);
        System.out.println("  [INFO] Aucun emprunt trouvé avec ce code, rien ne s'est passé.");

        /* ---------------------------------------------------------- *
         *  7. État final                                              *
         * ---------------------------------------------------------- */
        afficherEtat(bib, "ÉTAT FINAL APRÈS LES RETOURS");

        /* ---------------------------------------------------------- *
         *  8. Résumé des exceptions rencontrées                       *
         * ---------------------------------------------------------- */
        titre("RÉSUMÉ DES EXCEPTIONS TESTÉES");
        System.out.println("  [CodeAutException]            → code auteur hors [1000-9999]");
        System.out.println("  [AdherentInexistantException] → adhérent introuvable à l'emprunt");
        System.out.println("  [RetourImpossibleException]   → retour quand nmbExpD >= nmbExp");
        System.out.println("\n  Toutes les exceptions ont été interceptées sans crash. ✓");
    }
}
