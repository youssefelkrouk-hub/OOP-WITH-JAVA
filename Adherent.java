import java.util.Date; //c'est la biblio pour  recuperer la date 

public class Adherent extends Personne {
    static int nmbAdhstat; // c'est celui la qu'on va l'incrementé pas l'attribut pour éviter d'affecter a tout les adherent le meme code d'adherent 
    int cdAdh;
    Date dateAdh; 

    Adherent(String n,String p,Date D) {
        super(n,p);
        nmbAdhstat++;
        this.cdAdh=nmbAdhstat;
        this.dateAdh=D;
    }
    
    public Adherent(String n,String p) {
        this(n,p,new Date()); //Cette instruction doit passer en premier! Sinon on aura une erreur (constructeurs enchainés)
    }

    @Override
    //Ovveride pour indiquer au compilitaeur qu'on a entrain de redéfinir la méthode toString
    //de la superclasse Object

    public String toString() {
        return this.cdAdh+" "+super.toString()+" "+this.dateAdh; // super pour signifie qu'on utiliser toString de la classe Personne 
    }
    
}



