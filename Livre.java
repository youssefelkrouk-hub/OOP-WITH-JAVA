//on va crée une class exception qui va herité de la classes exception 
class RetourImpossibleException extends Exception {
    public RetourImpossibleException(String message) {
        super(message);// pour qu'il renvoie le message de la classe exception
        //par exemple ArithmiticException...
    }
}



public class Livre {
    int cdLiv;
    String titre;
    Auteur auteur;
    int nmbExp;
    int nmbExpD;

    Livre(int cd,String t,Auteur A,int exp,int expd) {
        this.cdLiv=cd;
        this.titre=t;
        this.auteur=A;
        this.nmbExp=exp;
        this.nmbExpD=expd;
    }

    public boolean livreDisponible() {
        if (this.nmbExpD>0 && this.nmbExp>0) {return true;}
        return false;
    }

   //lorsque le nombre d’exemplaires déjà retournés (nmbExpD) 
   // atteint le nombre total d’exemplaires (nmbExp)
   

    public void retourLivre() throws RetourImpossibleException {
    if (nmbExpD >= nmbExp) {
        //c'est ici qu'on va lever l'exception pour  la corriger ...
        throw new RetourImpossibleException("Impossible : tous les exemplaires sont déjà retournés.");
    }
    this.nmbExpD++;
    }


    @Override
    public String toString() {
        return this.cdLiv+" "+this.titre+" "+this.auteur+" "+this.nmbExp+" "+this.nmbExpD;
    }
}