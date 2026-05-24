import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar; // utilisée ici pour  calculer plusTroisDay 
import java.util.List;

// pour enlever une exception c'est  le code de l'adherent n'existe pas ! 
class AdherentInexistantException extends Exception { //exception contrôlée (checked).
    public AdherentInexistantException(String message) {
        super(message);
    }
}


public class Bibliotheque {
    // j'ai utiliser les collections  pour manipulation des méthodes 
    List<Livre> listLiv=new ArrayList<Livre>();
    List<Adherent> listAdh=new ArrayList<Adherent>();
    List<Emprunt> listEmp=new ArrayList<Emprunt>();

    public Bibliotheque() {} // default constructor 

    public List<Adherent> getlistAdh() {return this.listAdh;}

    public List<Livre> getlistLiv() {return this.listLiv;}

    public List<Emprunt> getlistEmp() {return this.listEmp;}
    // on va crée une methode qui cherche un Adherent par son code 
    // si le code est inexistant une exception est levée si le codelivre est incorrect.

    public Adherent rechercherAdherent(int codeA) throws AdherentInexistantException {
        for (int i=0;i<listAdh.size();i++) {
            if (listAdh.get(i).cdAdh==codeA) {
                return listAdh.get(i);
            }
        }
        throw new AdherentInexistantException("Adhérent avec le code " + codeA + " introuvable.");

        
    }

    public Livre rechercherLivre(int codeL) {
        for (int i=0;i<listLiv.size();i++) {
            if (listLiv.get(i).cdLiv==codeL) {
                return listLiv.get(i);
            }
        }
        return null;
    }

    public void ajouterLivre(Livre L) {
        if (this.rechercherLivre(L.cdLiv)==null) {
            this.listLiv.add(L);
        }
        else {L.nmbExp++;}
    }

// ici on va lever l'exception par le bloc try/catch

    public void ajouterAdherent(Adherent A) {
    try {
        rechercherAdherent(A.cdAdh);
        // Si on arrive ici, l'adhérent existe déjà → ne pas l'ajouter
        System.out.println("Adhérent déjà présent.");
    } catch (AdherentInexistantException e) {
        // L'adhérent n'existe pas → on peut l'ajouter
        this.listAdh.add(A);
    }
    }
// de meme ici , handling exception trough  try/catch block 
    public void ajouterEmprunt(int codeA, int codeL) {
    try {
        Adherent A = rechercherAdherent(codeA);
        Livre L = rechercherLivre(codeL);
        if (L == null) {
            System.out.println("Livre introuvable.");
            return;
        }
        if (!L.livreDisponible()) {
            System.out.println("Livre non disponible.");
            return;
        }
        Emprunt E = new Emprunt(L, A, new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(E.dateEmp);
        cal.add(Calendar.DAY_OF_MONTH, 3);
        E.dateRP = cal.getTime();
        L.nmbExpD--;
        this.listEmp.add(E);
    } catch (AdherentInexistantException e) {
        System.out.println(e.getMessage());
    }
    }


    public void retourLivre(int cdEmp) {
        for (int i=0;i<listEmp.size();i++) {
            if (listEmp.get(i).cdEmp==cdEmp) {
                listEmp.get(i).dateRE=new Date();
                listEmp.get(i).retourEmprunt();
                break;
            }
        }
    }
}