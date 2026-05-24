
public class Personne {
    private String nom;
    private String prenom;

    Personne() {}

    Personne(String n,String p) {
        this.nom=n;
        this.prenom=p;
    }

    public String getnom() {
        return this.nom;
    }

    public String getprenom() {
        return this.prenom;
    }

    public void setnom(String n) {
        this.nom=n;
    }

    public void setprenom(String p) {
        this.prenom=p;
    }


    @Override
    //ici public est obligatoire : on ne doit pas restreindre le niveau de visibilité au-dessous de celui de la superclasse(ici Object)
    public String toString() {
        return this.nom+" "+this.prenom;
    }
    
    public boolean equals(Personne p1,Personne p2){
        return p1.equals(p2);

    }
}