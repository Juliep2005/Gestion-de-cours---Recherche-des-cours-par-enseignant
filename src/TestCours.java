public class TestCours {
    public static void main(String[] args) {
        // 1️⃣ Créer un DAO
        CoursDAO dao = new CoursDAO();

        // 2️⃣ Créer un cours à ajouter
        Cours cours1 = new Cours("Mathématiques", "Mme Dupont", 40);

        // 3️⃣ Ajouter le cours dans la base
        dao.ajouterCours(cours1);
    }
}
