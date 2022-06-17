package nc.univ.edt.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cours {
    /**
     * Id de l avatar
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date debut;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fin;

    /**
     * La liste des étudiants
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Eleve> etudiants = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Niveau niveau;

    @ManyToOne(fetch = FetchType.LAZY)
    private Salle salle;


    public Cours() {

    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Cours(Date debut, Date fin) throws ParseException {
        this.debut = debut;
        this.fin = fin;
    }

    public List<Eleve> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Eleve> etudiants) {
        this.etudiants = etudiants;
    }

    public void addEtudiant(Eleve eleve){
        this.etudiants.add(eleve);
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
