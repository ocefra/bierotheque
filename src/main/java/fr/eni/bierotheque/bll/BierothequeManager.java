package fr.eni.bierotheque.bll;

import fr.eni.bierotheque.bo.Biere;
import fr.eni.bierotheque.bo.Brasserie;

import java.util.List;

public interface BierothequeManager {
  List<Biere> getAllBiere();

  void addOrUpdateBiere(Biere biere) throws BierothequeManagerException;

  void addOrUpdateBrasserie(Brasserie brasserie);
}
