package nc.univ.edt.service;

import nc.univ.edt.dao.EleveRepository;
import nc.univ.edt.model.Eleve;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class EleveService {
    @Autowired
    EntityManager entityManager;

    private void cleanEleveTable(ApplicationContext applicationContext) {
        EleveRepository eleveRepository = (EleveRepository) applicationContext.getBean("eleveRepository");
        eleveRepository.deleteAll();
    }

    public List<Eleve> getAll(ApplicationContext applicationContext) {
        EleveRepository eleveRepository = (EleveRepository) applicationContext.getBean("eleveRepository");
        return (List<Eleve>) eleveRepository.findAll();
    }
}
