public class TestSuppression {
    public static void main(String[] args) {
        CoursDAO dao = new CoursDAO();

        // Suppression du cours avec id 1
        dao.supprimerCours(1);
    }
}
