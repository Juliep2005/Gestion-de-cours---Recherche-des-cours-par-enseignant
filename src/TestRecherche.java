import java.util.List;

public class TestRecherche {
    public static void main(String[] args) {
        CoursDAO dao = new CoursDAO();

        // Recherche tous les cours de Mme Dupont
        List<Cours> coursDupont = dao.rechercherParEnseignant("Mme Dupont");

        System.out.println("Cours de Mme Dupont :");
        for (Cours c : coursDupont) {
            System.out.println(c);
        }
    }
}
