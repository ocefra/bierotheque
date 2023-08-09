package fr.eni.bierotheque;

import fr.eni.bierotheque.bll.BierothequeManager;
import fr.eni.bierotheque.bll.BierothequeManagerException;
import fr.eni.bierotheque.bo.Biere;
import fr.eni.bierotheque.bo.Brasserie;
import fr.eni.bierotheque.bo.Couleur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Biérothèque permettant de référencer nos dégustations de bières artisanales bretonnes.
 * Le logiciel doit permettre de saisir une bière et de lister celle ci dans l'ordre des notes attribuées (les meilleurs notes au dessus).
 * Techniquement :
 * Faire deux fonctions (addBiere, getAllBiere) et qui refuse les bières qui ont un degré d'alcool de <0.5
 */
@SpringBootApplication
public class BierothequeApplication implements CommandLineRunner {
  @Autowired
  BierothequeManager manager;

  public static void main(String[] args) {
    SpringApplication.run(BierothequeApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // =>======== Jeu de données ==============
    // =>=== brasseries =====
    Brasserie sainteColombe = new Brasserie("Sainte-Colombe", "Corps-Nuds", "22 boulevard François Mitterrand, ZI Les Grands Sillons");
    Brasserie arBreizher = new Brasserie("Ar Breizher", "Arzal", "28 Parc d'activités de l'Estuaire");
    Brasserie britt = new Brasserie("Brasserie de Bretagne (Britt)", "Concarneau", "7 Rue Victor Schoelcher");
    // =<=== fin brasseries =====

    // =>=== bières =====
    Biere sainteColombeBlanche = new Biere("Sainte-Colombe blanche", Couleur.BLANCHE, 5.5, "Peu amère et plutôt sucrée, la Sainte Colombe Blanche ravira les amateurs de bières blanches légères tout en restant une réelle bière de dégustation, complexe et aromatique.", 4.3);
    sainteColombeBlanche.setBrasserie(sainteColombe);
    Biere sainteColombeBlonde = new Biere("Sainte-Colombe blonde", Couleur.BLONDE, 5.5, "La brasserie Sainte Colombe présente une nouvelle fois une bière de grande qualité avec cette blonde qui, malgré ses 5,5°, ne se laisse pas démonter !", 4.6);
    sainteColombeBlonde.setBrasserie(sainteColombe);
    Biere sainteColombeBretonnePieNoir = new Biere("Sainte-Colombe bretonne pie noir", Couleur.BRUNE, 6., "Que dire de la Sainte Colombe Bretonne Pie Noir ? C’est une bière dense et intense avec un caractère bien trempé qui ne laissera pas indifférent. A essayer !", 4.8);
    sainteColombeBretonnePieNoir.setBrasserie(sainteColombe);

    Biere bleNoirBio = new Biere("Ar Breizher blé noir bio", Couleur.NOIRE, 5.5, "Laissez-vous séduire par sa belle robe noire, avec ses odeurs de grains torréfiés, ses arômes de café et de chocolat naturellement revêtue d'un léger voile de sarrasin, qui apparaît subtilement en bouche.", 5.);
    bleNoirBio.setBrasserie(arBreizher);

    Biere arMenDoree = new Biere("Ar-men dorée", Couleur.BLONDE, 7.7, "La Ar-Men Dorée Bio est une bière copieuse et puissante. Plutôt structurée sur le malt et les saveurs sucrées, l’amertume y est très discrète sur fonds d’arômes appétissants.", 4.9);
    arMenDoree.setBrasserie(britt);
    Biere gwinizDu = new Biere ("Dremmwel au blé noir Gwiniz du", Couleur.AMBREE, 5.4, "La Dremmwel au blé noir Gwiniz Du est une bière incontournable des crêperies bretonnes. Elle accompagnera parfaitement vos crêpes ou galettes ! Le petit plus : essayez-la avec une crêpe dessert, comme par exemple une crêpe blé noir au chocolat, un mariage original qui saura s’associer à la dominante maltée de cette bière.", 4.5);
    gwinizDu.setBrasserie(britt);

    Biere dremmwelSansAlcool = new Biere("Dremmwel sans alcool", Couleur.BLONDE, 0., "Bière bretonne biologique aux arômes maltés et fruités. Brassée à Concarneau.", 1.);
    // =<=== fin bières =====
    // =<======== Fin jeu de données ==============

    // =>======== Persistance des données ==============
    manager.addOrUpdateBiere(sainteColombeBlanche);
    manager.addOrUpdateBiere(sainteColombeBlonde);
    manager.addOrUpdateBiere(sainteColombeBretonnePieNoir);
    manager.addOrUpdateBiere(bleNoirBio);
    manager.addOrUpdateBiere(arMenDoree);
    manager.addOrUpdateBiere(gwinizDu);

    try {
      manager.addOrUpdateBiere(dremmwelSansAlcool);
    } catch (BierothequeManagerException e) {
      System.err.println("ERREUR " + e.getMessage());
    }
    // =<======== Fin persistance des données ==============

    // =>======== Liste des bières ============
//    manager.getAllBiere().forEach(System.out::println);
    System.out.println("Les bières que nous avons dégustées :");
    manager.getAllBiere().forEach(biere -> System.out.println(String.format("- note %.1f : %s", biere.getNote(), biere.toString())));
    // =<======== Fin liste des bières ============
  }
}
