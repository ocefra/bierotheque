package fr.eni.bierotheque.bll;

import fr.eni.bierotheque.bo.Biere;
import fr.eni.bierotheque.bo.Brasserie;
import fr.eni.bierotheque.dal.BiereDAO;
import fr.eni.bierotheque.dal.BrasserieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BierothequeManagerImpl implements BierothequeManager {
  @Autowired
  BiereDAO biereDAO;

  @Autowired
  BrasserieDAO brasserieDAO;

  @Override
  public List<Biere> getAllBiere() {
//    return biereDAO.getAllByNoteDesc();
    return ((List<Biere>) biereDAO.findAll()).stream()
        .sorted(Comparator.reverseOrder())
//        .sorted((biere1, biere2) -> biere2.compareTo(biere1))
        .toList();
  }

  @Override
  public void addOrUpdateBiere(Biere biere) throws BierothequeManagerException {
    if (biere.getAlcool() < 5.) {
      throw new BierothequeManagerException("Pas de jus, svp !");
    }
    if (biere.getBrasserie() != null) {
      addOrUpdateBrasserie(biere.getBrasserie());
    }
    biereDAO.save(biere);
  }

  @Override
  public void addOrUpdateBrasserie(Brasserie brasserie) {
    brasserieDAO.save(brasserie);
  }
}
