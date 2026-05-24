import java.io.*;
import java.nio.file.*;

public class MainGestionFichiers {

    public static void main(String[] args) {

        // ── Chemins (Interface Path ) ──────────────
        Path cheminLivres    = Paths.get("livres.txt");
        Path cheminAdherents = Paths.get("adherents.txt");
        Path repBackup       = Paths.get("backup");

        // ── Création du répertoire backup si inexistant ───────────
        try {
            if (!Files.exists(repBackup))
                Files.createDirectory(repBackup);
        } catch (IOException e) {
            System.out.println("Erreur création répertoire : " + e.getMessage());
            return;
        }


        // ── Écriture livres.txt ───────────────────────────────────
        try {
            FileWriter     fw  = new FileWriter(cheminLivres.toString());
            BufferedWriter bw  = new BufferedWriter(fw);

            bw.write("101|Les Miserables|Hugo|5|3");      bw.newLine();
            bw.write("102|L'Etranger|Camus|4|4");         bw.newLine();
 

            bw.close();//fermuture
            System.out.println("Fichier écrit : " + cheminLivres);

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        // ── Écriture adherents.txt ────────────────────────────────
        try {
            FileWriter     fw = new FileWriter(cheminAdherents.toString());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("1|Benali|Yassine");    bw.newLine();
            bw.write("2|Elhassani|Sara");    bw.newLine();

            bw.close();
            System.out.println("Fichier écrit : " + cheminAdherents);

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        // LECTURE — FileReader + BufferedReader 

        System.out.println("\n--- Contenu de livres.txt ---");
        try {
            FileReader     fr = new FileReader(cheminLivres.toString());
            BufferedReader br = new BufferedReader(fr);

            String ligne;
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("\n--- Contenu de adherents.txt ---");
        try {
            FileReader     fr = new FileReader(cheminAdherents.toString());
            BufferedReader br = new BufferedReader(fr);

            String ligne;
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
            }
            br.close();//fermuture//

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        // COPIE — Files.copy()

        try {
            Files.copy(cheminLivres,
                       repBackup.resolve("livres.txt"),
                       StandardCopyOption.REPLACE_EXISTING);
            System.out.println("\nCopié : " + cheminLivres + " → " + repBackup.resolve("livres.txt"));

            Files.copy(cheminAdherents,
                       repBackup.resolve("adherents.txt"),
                       StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copié : " + cheminAdherents + " → " + repBackup.resolve("adherents.txt"));

        } catch (IOException e) {
            System.out.println("Erreur copie : " + e.getMessage());
        }
    }
}
