package fr.eni.bierotheque.dal;

import fr.eni.bierotheque.bo.Biere;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BiereDAO extends CrudRepository<Biere, Integer> {

  @Query("SELECT b FROM Biere b ORDER BY b.note DESC")
  List<Biere> getAllByNoteDesc();
}
