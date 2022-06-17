package nc.univ.edt.service;

import nc.univ.edt.dao.CoursRepository;
import nc.univ.edt.model.Cours;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursService {

    public List<Cours> getAll(ApplicationContext applicationContext) {
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("coursRepository");
        return (List<Cours>) coursRepository.findAll();
    }

    public Cours save(Cours cours, ApplicationContext applicationContext){
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("coursRepository");
        return coursRepository.save(cours);
    }

    public boolean delete(Long id, ApplicationContext applicationContext){
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("coursRepository");
        try{coursRepository.deleteById(id);}
        catch (Exception exception){
            return false;
        }
        return true;
    }

    public Optional<Cours> get(Long id, ApplicationContext applicationContext){
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("coursRepository");
        Optional<Cours> cours = coursRepository.findById(id);
        if(cours.isEmpty())
            throw new RuntimeException();
        return cours;
    }

    public boolean update(Cours cours, ApplicationContext applicationContext){
        CoursRepository coursRepository = (CoursRepository) applicationContext.getBean("coursRepository");
        Cours coursRepo = coursRepository.findById(cours.getId()).orElseThrow(() -> new RuntimeException("Cours not found for this id "+cours.getId()));
        coursRepo.setDebut(cours.getDebut());
        coursRepo.setEtudiants(cours.getEtudiants());
        coursRepo.setNiveau(cours.getNiveau());
        coursRepo.setSalle(cours.getSalle());
        coursRepo.setFin(cours.getFin());
        coursRepository.save(coursRepo);
        return true;
    }
}
