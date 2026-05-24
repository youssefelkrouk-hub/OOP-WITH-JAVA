
import java.util.Date;

public class Emprunt {
    static int nmbEmp;
    int cdEmp;
    Livre livre;
    Adherent adh;
    Date dateEmp;
    Date dateRP;
    Date dateRE;

    Emprunt(Livre L,Adherent A,Date D) {
        this.livre=L;
        this.adh=A;
        nmbEmp++;
        this.cdEmp=nmbEmp;
        this.dateEmp=D;
    }

    public String etatEmprunt() {

        Date aujourdhui = new Date();

        if (this.dateRP!=null && this.dateEmp!=null) {
            if (this.dateRE==null && aujourdhui.before(this.dateRP)) {
                return "En cours";
            }
            if (this.dateRE==null && aujourdhui.after(this.dateRP)) {
                return "Non rendu";
            }
            if (this.dateRE!=null) {
                return "Rendu";
            }
         }
        return "Etat inconnu";
    }
// ici on va essayer de gérer l'exception mentionnée dans la classe Livre(RetourImpossibleException) 
// en utilisant le block Try/Catch !!
    public void retourEmprunt() {
    try {
        this.livre.retourLivre();
    }
    catch (RetourImpossibleException e) {
        System.out.println("Erreur lors du retour : " + e.getMessage());
        // On peut aussi logguer ou ne rien faire
    }
    }
}