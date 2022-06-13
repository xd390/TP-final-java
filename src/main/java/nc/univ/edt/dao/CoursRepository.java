package nc.univ.edt.dao;

import nc.univ.edt.model.Cours;
import org.springframework.data.repository.CrudRepository;

public interface CoursRepository extends CrudRepository <Cours, Long> {
}
