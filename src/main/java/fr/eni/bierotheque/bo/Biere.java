package fr.eni.bierotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import fr.eni.bierotheque.bo.Brasserie;

@Entity
@Data
@NoArgsConstructor
public class Biere implements Comparable<Biere> {
  @Id
  @GeneratedValue
  private Integer id;

  private String nom;
  private Couleur couleur;
  private Double alcool;

  @Column(length = 1000)
  private String commentaire;

  private Double note;

  @ManyToOne
  private Brasserie brasserie;

  public Biere(String nom, Couleur couleur, Double alcool, String commentaire, Double note) {
    this.nom = nom;
    this.couleur = couleur;
    this.alcool = alcool;
    this.commentaire = commentaire;
    this.note = note;
  }

  @Override
  public int compareTo(Biere autre) {
    return this.note.compareTo(autre.getNote());
  }
}
