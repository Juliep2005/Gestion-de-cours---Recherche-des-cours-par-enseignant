import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionCoursGUI extends JFrame {

    private CoursDAO dao;

    // Composants Swing
    private JTextField tfNom, tfEnseignant, tfVolume;
    private JTextField tfRecherche;
    private JTextField tfIdSupprimer;
    private JTextArea taAffichage;

    public GestionCoursGUI() {
        dao = new CoursDAO();

        setTitle("Gestion de Cours");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        add(panel);

        // Zone d'affichage
        taAffichage = new JTextArea();
        taAffichage.setEditable(false);
        panel.add(new JScrollPane(taAffichage), BorderLayout.CENTER);

        // Panel de saisie (Ajouter et Afficher)
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Ajouter un cours"));

        tfNom = new JTextField();
        tfEnseignant = new JTextField();
        tfVolume = new JTextField();

        panelForm.add(new JLabel("Nom du cours :"));
        panelForm.add(tfNom);
        panelForm.add(new JLabel("Enseignant :"));
        panelForm.add(tfEnseignant);
        panelForm.add(new JLabel("Volume horaire :"));
        panelForm.add(tfVolume);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnAfficher = new JButton("Afficher tous les cours");

        panelForm.add(btnAjouter);
        panelForm.add(btnAfficher);

        panel.add(panelForm, BorderLayout.NORTH);

        // Panel recherche
        JPanel panelRecherche = new JPanel(new FlowLayout());
        panelRecherche.setBorder(BorderFactory.createTitledBorder("Recherche par enseignant"));

        tfRecherche = new JTextField(15);
        JButton btnRecherche = new JButton("Rechercher");

        panelRecherche.add(new JLabel("Enseignant :"));
        panelRecherche.add(tfRecherche);
        panelRecherche.add(btnRecherche);

        panel.add(panelRecherche, BorderLayout.SOUTH);

        // Panel suppression
        JPanel panelSuppression = new JPanel(new FlowLayout());
        panelSuppression.setBorder(BorderFactory.createTitledBorder("Supprimer un cours"));

        tfIdSupprimer = new JTextField(5);
        JButton btnSupprimer = new JButton("Supprimer");

        panelSuppression.add(new JLabel("ID du cours :"));
        panelSuppression.add(tfIdSupprimer);
        panelSuppression.add(btnSupprimer);

        panel.add(panelSuppression, BorderLayout.EAST);

        // Actions des boutons
        btnAjouter.addActionListener(e -> ajouterCours());
        btnAfficher.addActionListener(e -> afficherCours());
        btnRecherche.addActionListener(e -> rechercherCours());
        btnSupprimer.addActionListener(e -> supprimerCours());

        setVisible(true);
    }

    // Méthodes pour les boutons
    private void ajouterCours() {
        String nom = tfNom.getText().trim();
        String enseignant = tfEnseignant.getText().trim();
        String volumeStr = tfVolume.getText().trim();

        if (nom.isEmpty() || enseignant.isEmpty() || volumeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int volume = Integer.parseInt(volumeStr);
            Cours c = new Cours(nom, enseignant, volume);
            dao.ajouterCours(c);
            JOptionPane.showMessageDialog(this, "Cours ajouté avec succès !");
            tfNom.setText("");
            tfEnseignant.setText("");
            tfVolume.setText("");
            afficherCours(); // mise à jour affichage
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Volume horaire doit être un nombre !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void afficherCours() {
        List<Cours> liste = dao.getAllCours();
        taAffichage.setText("");
        for (Cours c : liste) {
            taAffichage.append(c + "\n");
        }
    }

    private void rechercherCours() {
        String enseignant = tfRecherche.getText().trim();
        if (enseignant.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir le nom de l'enseignant !", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Cours> liste = dao.rechercherParEnseignant(enseignant);
        taAffichage.setText("");
        for (Cours c : liste) {
            taAffichage.append(c + "\n");
        }
    }

    private void supprimerCours() {
        String idStr = tfIdSupprimer.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir l'ID du cours !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            dao.supprimerCours(id);
            JOptionPane.showMessageDialog(this, "Action terminée !");
            tfIdSupprimer.setText("");
            afficherCours(); // mise à jour affichage
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'ID doit être un nombre !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main
    public static void main(String[] args) {
        new GestionCoursGUI();
    }
}
