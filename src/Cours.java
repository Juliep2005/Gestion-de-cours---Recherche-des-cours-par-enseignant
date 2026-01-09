
public class Cours {
    private int id;
    private String nom_du_cours;
    private String enseignant;
    private int VolumeHoraire; // durée en heures

    // Constructeur sans id (pour insertion)
    public Cours(String nom_du_cours, String enseignant, int VolumeHoraire) {
        this.nom_du_cours = nom_du_cours;
        this.enseignant = enseignant;
        this.VolumeHoraire = VolumeHoraire;
    }

    // Constructeur avec id (pour récupération depuis la base)
    public Cours(int id, String nom_du_cours, String enseignant, int VolumeHoraire) {
        this.id = id;
        this.nom_du_cours = nom_du_cours;
        this.enseignant = enseignant;
        this.VolumeHoraire = VolumeHoraire;
    }

    public int getId() {
        return id;
    }

    public String getNom_du_cours() {
        return nom_du_cours;
    }

    public void setNom_du_cours(String nom_du_cours) {
        this.nom_du_cours = nom_du_cours;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

    public int getVolumeHoraire() {
        return VolumeHoraire;
    }

    public void setVolumeHoraire(int VolumeHoraire) {
        this.VolumeHoraire = VolumeHoraire;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", nom_du_cours='" + nom_du_cours + '\'' +
                ", enseignant='" + enseignant + '\'' +
                ", VolumeHoraire=" + VolumeHoraire +
                '}';
    }

}