
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO {
    public void ajouterCours(Cours cours) {
        String sql = "INSERT INTO cours (nom, enseignant, volume_horaire) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cours.getNom_du_cours());
            ps.setString(2, cours.getEnseignant());
            ps.setInt(3, cours.getVolumeHoraire());

            ps.executeUpdate();
            System.out.println(" Cours ajouté avec succès : " + cours.getNom_du_cours());
        } catch (SQLException e) {
            System.out.println(" Erreur lors de l'ajout du cours : ");
            e.printStackTrace();
        }
    }

    public List<Cours> getAllCours() {
        List<Cours> listeCours = new ArrayList<>();
        String sql = "SELECT * FROM cours";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String enseignant = rs.getString("enseignant");
                int volumeHoraire = rs.getInt("volume_horaire");

                Cours c = new Cours(id, nom, enseignant, volumeHoraire);
                listeCours.add(c);
            }
        } catch (SQLException e) {
            System.out.println(" Erreur lors de la récupération des cours : ");
            e.printStackTrace();
        }
        return listeCours;
    }

    public List<Cours> rechercherParEnseignant(String enseignant) {
        List<Cours> listeCours = new ArrayList<>();
        String sql = "SELECT * FROM cours WHERE enseignant = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, enseignant);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                int volume = rs.getInt("volume_horaire");

                Cours c = new Cours(id, nom, enseignant, volume);
                listeCours.add(c);
            }

        } catch (SQLException e) {
            System.out.println(" Erreur lors de la recherche par enseignant");
            e.printStackTrace();
        }

        return listeCours;
    }

    public void supprimerCours(int id) {
        String sql = "DELETE FROM cours WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int lignes = ps.executeUpdate();

            if (lignes > 0) {
                System.out.println(" Cours supprimé avec succès, id=" + id);
            } else {
                System.out.println(" Aucun cours trouvé avec id=" + id);
            }

        } catch (SQLException e) {
            System.out.println(" Erreur lors de la suppression du cours");
            e.printStackTrace();
        }
    }

}
