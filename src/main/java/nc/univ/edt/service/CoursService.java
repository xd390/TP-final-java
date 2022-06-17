package nc.univ.edt.service;

import nc.univ.edt.dao.CoursRepository;
import nc.univ.edt.model.Cours;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;

public class CoursService {

    public List<Cours> getAll(ApplicationContext applicationContext) {
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("CoursRepository");
        return (List<Cours>) coursRepository.findAll();
    }

    public Cours save(Cours cours, ApplicationContext applicationContext){
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("CoursRepository");
        return coursRepository.save(cours);
    }

    public boolean delete(Long id, ApplicationContext applicationContext){
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("CoursRepository");
        try{coursRepository.deleteById(id);}
        catch (Exception exception){
            return false;
        }
        return true;
    }

    public Optional<Cours> get(Long id, ApplicationContext applicationContext){
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("CoursRepository");
        Optional<Cours> cours = coursRepository.findById(id);
        if(cours.isEmpty())
            throw new RuntimeException();
        return cours;
    }

    public boolean update(Long id, Cours cours, ApplicationContext applicationContext){
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("CoursRepository");
        Cours coursRepo = coursRepository.findById(id).orElseThrow(() -> new RuntimeException("Cours not found for this id "+id));
        coursRepo.setDebut(cours.getDebut());
        coursRepo.setEtudiants(cours.getEtudiants());
        coursRepo.setNiveau(cours.getNiveau());
        coursRepo.setSalle(cours.getSalle());
        coursRepo.setFin(cours.getFin());
        coursRepository.save(coursRepo);
        return true;
    }
}
