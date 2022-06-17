package nc.univ.edt.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salle {
    /**
     * Id de l avatar
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int nbPlaces;

    @Column
    private String nom;

    @Column
    private String code;

    /**
     * La liste des cours
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cours> cours = new ArrayList<>();

    public Salle() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public Long getId() {
        return id;
    }

    public Salle(String nom, String code, int nbPlaces){
        this.nom = nom;
        this.code = code;
        this.nbPlaces = nbPlaces;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}
