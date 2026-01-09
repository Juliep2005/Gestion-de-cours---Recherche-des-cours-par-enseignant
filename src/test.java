
import java.sql.Connection;

public class test {
    public static void main(String[] args) {
        try {
            Connection c = DBConnection.getConnection();
            System.out.println("✅ Connexion réussie à la base !");
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
