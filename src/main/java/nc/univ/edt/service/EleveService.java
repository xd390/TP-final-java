package nc.univ.edt.service;

import nc.univ.edt.dao.EleveRepository;
import nc.univ.edt.model.Eleve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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

    public Eleve save(Eleve eleve, ApplicationContext applicationContext){
        EleveRepository eleveRepository = (EleveRepository) applicationContext.getBean("eleveRepository");
        return eleveRepository.save(eleve);
    }

    public Eleve get(Long id, ApplicationContext applicationContext){
        EleveRepository eleveRepository = (EleveRepository) applicationContext.getBean("eleveRepository");
        Eleve eleve = eleveRepository.findById(id).orElseThrow(() -> new RuntimeException("Eleve not found for this id "+id));
        return eleve;
    }

    public Boolean delete(Long id, ApplicationContext applicationContext){
        EleveRepository eleveRepository = (EleveRepository) applicationContext.getBean("eleveRepository");
        try{eleveRepository.deleteById(id);}
        catch (Exception exception){
            return false;
        }
        return true;
    }

    public Boolean update(Eleve eleve, ApplicationContext applicationContext){
        EleveRepository eleveRepository = (EleveRepository) applicationContext.getBean("eleveRepository");
        Eleve eleveRepo = eleveRepository.findById(eleve.getId()).orElseThrow(() -> new RuntimeException("Eleve not found for this id "+eleve.getId()));
        eleveRepo.setAdresse(eleve.getAdresse());
        eleveRepo.setAge(eleve.getAge());
        eleveRepo.setCours(eleve.getCours());
        eleveRepo.setNiveau(eleve.getNiveau());
        eleveRepo.setNom(eleve.getNom());
        eleveRepo.setPrenom(eleve.getPrenom());
        eleveRepository.save(eleveRepo);
        return true;
    }
}
