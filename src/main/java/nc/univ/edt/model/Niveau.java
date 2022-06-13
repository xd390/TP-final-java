package nc.univ.edt.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Niveau {
    /**
     * Id de l avatar
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String code;

    @Column
    private String libelle;

    /**
     * La liste des étudiants
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Eleve> etudiants = new ArrayList<>();

    /**
     * La liste des cours
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cours> cours = new ArrayList<>();

    public Niveau(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public Niveau() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Eleve> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Eleve> etudiants) {
        this.etudiants = etudiants;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}
