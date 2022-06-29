package sn.seye.gesmat.mefpai.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import sn.seye.gesmat.mefpai.domain.enumeration.Etat;
import sn.seye.gesmat.mefpai.domain.enumeration.NomSemestre;

/**
 * A ReleveNote.
 */
@Entity
@Table(name = "releve_note")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReleveNote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "annee", nullable = false)
    private LocalDate annee;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;

    @NotNull
    @Column(name = "apreciation", nullable = false)
    private String apreciation;

    @Column(name = "moyenne")
    private Float moyenne;

    @Column(name = "moyenne_generale")
    private Float moyenneGenerale;

    @Column(name = "rang")
    private String rang;

    @Column(name = "note_conduite")
    private Integer noteConduite;

    @Enumerated(EnumType.STRING)
    @Column(name = "nom_semestre")
    private NomSemestre nomSemestre;

    @Column(name = "matricule_rel")
    private String matriculeRel;

    @ManyToOne
    @JsonIgnoreProperties(value = { "inscriptions", "releveNotes", "diplomes", "niveauEtude" }, allowSetters = true)
    private Apprenant apprenant;

    @ManyToOne
    @JsonIgnoreProperties(value = { "etablissements", "releveNotes" }, allowSetters = true)
    private FiliereEtude filiereEtude;

    @ManyToOne
    @JsonIgnoreProperties(value = { "etablissements", "releveNotes" }, allowSetters = true)
    private SerieEtude serieEtude;

    @ManyToMany(mappedBy = "releveNotes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "classes", "releveNotes" }, allowSetters = true)
    private Set<Programme> programmes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReleveNote id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAnnee() {
        return this.annee;
    }

    public ReleveNote annee(LocalDate annee) {
        this.setAnnee(annee);
        return this;
    }

    public void setAnnee(LocalDate annee) {
        this.annee = annee;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public ReleveNote etat(Etat etat) {
        this.setEtat(etat);
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getApreciation() {
        return this.apreciation;
    }

    public ReleveNote apreciation(String apreciation) {
        this.setApreciation(apreciation);
        return this;
    }

    public void setApreciation(String apreciation) {
        this.apreciation = apreciation;
    }

    public Float getMoyenne() {
        return this.moyenne;
    }

    public ReleveNote moyenne(Float moyenne) {
        this.setMoyenne(moyenne);
        return this;
    }

    public void setMoyenne(Float moyenne) {
        this.moyenne = moyenne;
    }

    public Float getMoyenneGenerale() {
        return this.moyenneGenerale;
    }

    public ReleveNote moyenneGenerale(Float moyenneGenerale) {
        this.setMoyenneGenerale(moyenneGenerale);
        return this;
    }

    public void setMoyenneGenerale(Float moyenneGenerale) {
        this.moyenneGenerale = moyenneGenerale;
    }

    public String getRang() {
        return this.rang;
    }

    public ReleveNote rang(String rang) {
        this.setRang(rang);
        return this;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public Integer getNoteConduite() {
        return this.noteConduite;
    }

    public ReleveNote noteConduite(Integer noteConduite) {
        this.setNoteConduite(noteConduite);
        return this;
    }

    public void setNoteConduite(Integer noteConduite) {
        this.noteConduite = noteConduite;
    }

    public NomSemestre getNomSemestre() {
        return this.nomSemestre;
    }

    public ReleveNote nomSemestre(NomSemestre nomSemestre) {
        this.setNomSemestre(nomSemestre);
        return this;
    }

    public void setNomSemestre(NomSemestre nomSemestre) {
        this.nomSemestre = nomSemestre;
    }

    public String getMatriculeRel() {
        return this.matriculeRel;
    }

    public ReleveNote matriculeRel(String matriculeRel) {
        this.setMatriculeRel(matriculeRel);
        return this;
    }

    public void setMatriculeRel(String matriculeRel) {
        this.matriculeRel = matriculeRel;
    }

    public Apprenant getApprenant() {
        return this.apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public ReleveNote apprenant(Apprenant apprenant) {
        this.setApprenant(apprenant);
        return this;
    }

    public FiliereEtude getFiliereEtude() {
        return this.filiereEtude;
    }

    public void setFiliereEtude(FiliereEtude filiereEtude) {
        this.filiereEtude = filiereEtude;
    }

    public ReleveNote filiereEtude(FiliereEtude filiereEtude) {
        this.setFiliereEtude(filiereEtude);
        return this;
    }

    public SerieEtude getSerieEtude() {
        return this.serieEtude;
    }

    public void setSerieEtude(SerieEtude serieEtude) {
        this.serieEtude = serieEtude;
    }

    public ReleveNote serieEtude(SerieEtude serieEtude) {
        this.setSerieEtude(serieEtude);
        return this;
    }

    public Set<Programme> getProgrammes() {
        return this.programmes;
    }

    public void setProgrammes(Set<Programme> programmes) {
        if (this.programmes != null) {
            this.programmes.forEach(i -> i.removeReleveNote(this));
        }
        if (programmes != null) {
            programmes.forEach(i -> i.addReleveNote(this));
        }
        this.programmes = programmes;
    }

    public ReleveNote programmes(Set<Programme> programmes) {
        this.setProgrammes(programmes);
        return this;
    }

    public ReleveNote addProgramme(Programme programme) {
        this.programmes.add(programme);
        programme.getReleveNotes().add(this);
        return this;
    }

    public ReleveNote removeProgramme(Programme programme) {
        this.programmes.remove(programme);
        programme.getReleveNotes().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReleveNote)) {
            return false;
        }
        return id != null && id.equals(((ReleveNote) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReleveNote{" +
            "id=" + getId() +
            ", annee='" + getAnnee() + "'" +
            ", etat='" + getEtat() + "'" +
            ", apreciation='" + getApreciation() + "'" +
            ", moyenne=" + getMoyenne() +
            ", moyenneGenerale=" + getMoyenneGenerale() +
            ", rang='" + getRang() + "'" +
            ", noteConduite=" + getNoteConduite() +
            ", nomSemestre='" + getNomSemestre() + "'" +
            ", matriculeRel='" + getMatriculeRel() + "'" +
            "}";
    }
}
