package nc.univ.edt.model;

import javax.persistence.*;

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

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public Long getId() {
        return id;
    }

    public Salle(String nom, String code, int nbPlaces){
        this.nom = nom;
        this.code = code;
        this.nbPlaces = nbPlaces;
    }
}
