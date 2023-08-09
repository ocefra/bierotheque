package fr.eni.bierotheque.dal;

import fr.eni.bierotheque.bo.Brasserie;
import org.springframework.data.repository.CrudRepository;

public interface BrasserieDAO extends CrudRepository<Brasserie, Integer> {
}
