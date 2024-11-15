package exo3;

import java.util.ArrayList;
import java.util.Scanner;

// Classe Étudiant
class Etudiant {
    String nom;
    String prenom;
    String filiere;
    ArrayList<Double> notes;

    // Constructeur
    public Etudiant(String nom, String prenom, String filiere) {
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.notes = new ArrayList<>();
    }

    // Méthode pour ajouter une note
    public void ajouterNote(double note) {
        notes.add(note);
    }

    // Méthode pour calculer la moyenne
    public double calculerMoyenne() {
        double somme = 0;
        for (double note : notes) {
            somme += note;
        }
        return somme / notes.size();
    }
}

public class GestionEtudiants {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Etudiant> etudiants = new ArrayList<>();

    // Méthode pour ajouter un étudiant
    public static void ajouterEtudiant() {
        System.out.print("Entrez le nom de l'étudiant: ");
        String nom = scanner.nextLine();
        System.out.print("Entrez le prénom de l'étudiant: ");
        String prenom = scanner.nextLine();
        System.out.print("Entrez la filière de l'étudiant (Génie Civil, Génie Électrique, Informatique): ");
        String filiere = scanner.nextLine();

        Etudiant etudiant = new Etudiant(nom, prenom, filiere);

        // Saisie des notes
        System.out.print("Combien de notes voulez-vous saisir pour cet étudiant? ");
        int nombreDeNotes = scanner.nextInt();
        for (int i = 0; i < nombreDeNotes; i++) {
            System.out.print("Entrez la note " + (i + 1) + ": ");
            double note = scanner.nextDouble();
            etudiant.ajouterNote(note);
        }
        scanner.nextLine(); // Pour consommer le retour à la ligne

        etudiants.add(etudiant);
        System.out.println("Étudiant ajouté avec succès!\n");
    }

    // Méthode pour calculer et afficher la moyenne des étudiants de 4ème année par filière
    public static void afficherMoyenneParFiliere(String filiere) {
        double sommeMoyennes = 0;
        int nombreEtudiants = 0;

        for (Etudiant etudiant : etudiants) {
            if (etudiant.filiere.equalsIgnoreCase(filiere)) {
                double moyenne = etudiant.calculerMoyenne();
                sommeMoyennes += moyenne;
                nombreEtudiants++;
                System.out.println("Moyenne de " + etudiant.prenom + " " + etudiant.nom + ": " + moyenne);
            }
        }

        if (nombreEtudiants > 0) {
            System.out.println("Moyenne générale pour la filière " + filiere + ": " + (sommeMoyennes / nombreEtudiants));
        } else {
            System.out.println("Aucun étudiant trouvé dans la filière " + filiere);
        }
        System.out.println();
    }

    // Méthode principale
    public static void main(String[] args) {
        int choix;
        do {
            System.out.println("===== Gestion des étudiants =====");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Afficher la moyenne des étudiants en Génie Civil");
            System.out.println("3. Afficher la moyenne des étudiants en Génie Électrique");
            System.out.println("4. Afficher la moyenne des étudiants en Informatique");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option: ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le retour à la ligne

            switch (choix) {
                case 1:
                    ajouterEtudiant();
                    break;
                case 2:
                    afficherMoyenneParFiliere("Génie Civil");
                    break;
                case 3:
                    afficherMoyenneParFiliere("Génie Électrique");
                    break;
                case 4:
                    afficherMoyenneParFiliere("Informatique");
                    break;
                case 5:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 5);
    }
}