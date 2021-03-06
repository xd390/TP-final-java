package nc.univ.edt.service;

import nc.univ.edt.dao.SalleRepository;
import nc.univ.edt.model.Cours;
import nc.univ.edt.model.Salle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class SalleService {
    @Autowired
    EntityManager entityManager;

    public List<Salle> getAll(ApplicationContext applicationContext) {
        SalleRepository salleRepository = (SalleRepository) applicationContext.getBean("salleRepository");
        return (List<Salle>) salleRepository.findAll();
    }

    public Salle save(Salle salle, ApplicationContext applicationContext){
        SalleRepository salleRepository = (SalleRepository) applicationContext.getBean("salleRepository");
        return salleRepository.save(salle);
    }

    public boolean delete(Long id, ApplicationContext applicationContext){
        SalleRepository salleRepository = (SalleRepository) applicationContext.getBean("salleRepository");
        try{salleRepository.deleteById(id);}
        catch (Exception exception){
            return false;
        }
        return true;
    }

    public Salle get(Long id, ApplicationContext applicationContext){
        SalleRepository salleRepository = (SalleRepository) applicationContext.getBean("salleRepository");
        Salle salle = salleRepository.findById(id).orElseThrow(() -> new RuntimeException("Salle not found for this id "+id));
        return salle;
    }

    public boolean update(Salle salle, ApplicationContext applicationContext){
        SalleRepository salleRepository = (SalleRepository) applicationContext.getBean("salleRepository");
        Salle salleRepo = salleRepository.findById(salle.getId()).orElseThrow(() -> new RuntimeException("Salle not found for this id "+salle.getId()));
        salleRepo.setCours(salle.getCours());
        salleRepo.setCode(salle.getCode());
        salleRepo.setNom(salle.getNom());
        salleRepo.setNbPlaces(salle.getNbPlaces());
        salleRepository.save(salleRepo);
        return true;
    }
}
