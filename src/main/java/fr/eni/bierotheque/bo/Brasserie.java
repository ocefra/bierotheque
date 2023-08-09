package fr.eni.bierotheque.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Une brasserie aura les attributs suivants :
 * un nom
 * une ville
 * une adresse
 */
@Entity
@Data
@NoArgsConstructor
public class Brasserie {
  @Id
  @GeneratedValue
  private Integer id;

  private String nom;
  private String ville;
  private String adresse;

  public Brasserie(String nom, String ville, String adresse) {
    this.nom = nom;
    this.ville = ville;
    this.adresse = adresse;
  }
}
