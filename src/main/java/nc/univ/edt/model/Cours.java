package nc.univ.edt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Cours {
    /**
     * Id de l avatar
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date debut;

    @Column
    private Date fin;

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

    public Cours(Date debut, Date fin){

        this.debut = debut;
        this.fin = fin;
    }
}
