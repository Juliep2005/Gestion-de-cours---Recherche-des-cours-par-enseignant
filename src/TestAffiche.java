import java.util.List;

public class TestAffiche {
    public static void main(String[] args) {
        CoursDAO dao = new CoursDAO();
        List<Cours> tous = dao.getAllCours();

        System.out.println("Liste des cours :");
        for (Cours c : tous) {
            System.out.println(c);
        }
    }
}
