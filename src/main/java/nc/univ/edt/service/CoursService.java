package nc.univ.edt.service;

import nc.univ.edt.dao.CoursRepository;
import nc.univ.edt.dao.SalleRepository;
import nc.univ.edt.model.Cours;
import nc.univ.edt.model.Salle;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CoursService {

    public List<Cours> getAll(ApplicationContext applicationContext) {
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("CoursRepository");
        return (List<Cours>) coursRepository.findAll();
    }
}
