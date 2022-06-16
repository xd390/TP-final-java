package nc.univ.edt.service;

import nc.univ.edt.dao.EleveRepository;
import nc.univ.edt.dao.SalleRepository;
import nc.univ.edt.model.Eleve;
import nc.univ.edt.model.Salle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import java.util.List;

public class SalleService {
    @Autowired
    EntityManager entityManager;

    public List<Salle> getAll(ApplicationContext applicationContext) {
        SalleRepository salleRepository = (SalleRepository) applicationContext.getBean("salleRepository");
        return (List<Salle>) salleRepository.findAll();
    }
}
