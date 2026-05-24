// Classe d'exception

class CodeAutException extends Exception {
    public CodeAutException(String message) {
        super(message);// retourner le message de la classe Exception ,qu'elle contient déja !
    }
}

public class Auteur extends Personne {
    private int cdAut;

    public Auteur(String nom, String prenom, int code) throws CodeAutException {
        super(nom, prenom);
        // Vérification naïve : un nombre à 4 chiffres est compris entre 1000 et 9999
        if (code < 1000 || code > 9999) {
            throw new CodeAutException("Le code de l'auteur est incorrect : il doit comporter exactement 4 chiffres.");
        }
        //sinon en continue de faire l'initialisation//
        this.cdAut = code;
    }

    public int getCode() {
        return cdAut;
    }

    public void setCode(int code) throws CodeAutException {
        // La même vérification doit être faite lors d'une modification du code
        if (code < 1000 || code > 9999) {
            throw new CodeAutException("Le code de l'auteur est incorrect : il doit comporter exactement 4 chiffres.");
        }
        this.cdAut= code;
    }

    @Override 
    public String toString() {
        return this.cdAut + " " + super.toString();
    }
}

