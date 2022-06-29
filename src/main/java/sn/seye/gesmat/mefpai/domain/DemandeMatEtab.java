package sn.seye.gesmat.mefpai.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import sn.seye.gesmat.mefpai.domain.enumeration.NomDep;
import sn.seye.gesmat.mefpai.domain.enumeration.StatutEtab;
import sn.seye.gesmat.mefpai.domain.enumeration.TypeEtab;
import sn.seye.gesmat.mefpai.domain.enumeration.TypeInspection;

/**
 * A DemandeMatEtab.
 */
@Entity
@Table(name = "demande_mat_etab")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DemandeMatEtab implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom_complet", nullable = false)
    private String nomComplet;

    @NotNull
    @Column(name = "responsabilite", nullable = false)
    private String responsabilite;

    @NotNull
    @Column(name = "nom_etab", nullable = false)
    private String nomEtab;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "departement", nullable = false)
    private NomDep departement;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_etab", nullable = false)
    private TypeEtab typeEtab;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutEtab statut;

    @NotNull
    @Column(name = "annee_demande", nullable = false)
    private LocalDate anneeDemande;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_insp", nullable = false)
    private TypeInspection typeInsp;

    @Column(name = "etat_demande")
    private String etatDemande;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "objet")
    private String objet;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "bons", "personnel", "classes", "diplomes", "demandeMatEtabs", "localite", "inspection", "filiereEtude", "serieEtude" },
        allowSetters = true
    )
    private Etablissement etablissement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DemandeMatEtab id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomComplet() {
        return this.nomComplet;
    }

    public DemandeMatEtab nomComplet(String nomComplet) {
        this.setNomComplet(nomComplet);
        return this;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getResponsabilite() {
        return this.responsabilite;
    }

    public DemandeMatEtab responsabilite(String responsabilite) {
        this.setResponsabilite(responsabilite);
        return this;
    }

    public void setResponsabilite(String responsabilite) {
        this.responsabilite = responsabilite;
    }

    public String getNomEtab() {
        return this.nomEtab;
    }

    public DemandeMatEtab nomEtab(String nomEtab) {
        this.setNomEtab(nomEtab);
        return this;
    }

    public void setNomEtab(String nomEtab) {
        this.nomEtab = nomEtab;
    }

    public NomDep getDepartement() {
        return this.departement;
    }

    public DemandeMatEtab departement(NomDep departement) {
        this.setDepartement(departement);
        return this;
    }

    public void setDepartement(NomDep departement) {
        this.departement = departement;
    }

    public TypeEtab getTypeEtab() {
        return this.typeEtab;
    }

    public DemandeMatEtab typeEtab(TypeEtab typeEtab) {
        this.setTypeEtab(typeEtab);
        return this;
    }

    public void setTypeEtab(TypeEtab typeEtab) {
        this.typeEtab = typeEtab;
    }

    public StatutEtab getStatut() {
        return this.statut;
    }

    public DemandeMatEtab statut(StatutEtab statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(StatutEtab statut) {
        this.statut = statut;
    }

    public LocalDate getAnneeDemande() {
        return this.anneeDemande;
    }

    public DemandeMatEtab anneeDemande(LocalDate anneeDemande) {
        this.setAnneeDemande(anneeDemande);
        return this;
    }

    public void setAnneeDemande(LocalDate anneeDemande) {
        this.anneeDemande = anneeDemande;
    }

    public TypeInspection getTypeInsp() {
        return this.typeInsp;
    }

    public DemandeMatEtab typeInsp(TypeInspection typeInsp) {
        this.setTypeInsp(typeInsp);
        return this;
    }

    public void setTypeInsp(TypeInspection typeInsp) {
        this.typeInsp = typeInsp;
    }

    public String getEtatDemande() {
        return this.etatDemande;
    }

    public DemandeMatEtab etatDemande(String etatDemande) {
        this.setEtatDemande(etatDemande);
        return this;
    }

    public void setEtatDemande(String etatDemande) {
        this.etatDemande = etatDemande;
    }

    public String getEmail() {
        return this.email;
    }

    public DemandeMatEtab email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjet() {
        return this.objet;
    }

    public DemandeMatEtab objet(String objet) {
        this.setObjet(objet);
        return this;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public DemandeMatEtab etablissement(Etablissement etablissement) {
        this.setEtablissement(etablissement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeMatEtab)) {
            return false;
        }
        return id != null && id.equals(((DemandeMatEtab) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DemandeMatEtab{" +
            "id=" + getId() +
            ", nomComplet='" + getNomComplet() + "'" +
            ", responsabilite='" + getResponsabilite() + "'" +
            ", nomEtab='" + getNomEtab() + "'" +
            ", departement='" + getDepartement() + "'" +
            ", typeEtab='" + getTypeEtab() + "'" +
            ", statut='" + getStatut() + "'" +
            ", anneeDemande='" + getAnneeDemande() + "'" +
            ", typeInsp='" + getTypeInsp() + "'" +
            ", etatDemande='" + getEtatDemande() + "'" +
            ", email='" + getEmail() + "'" +
            ", objet='" + getObjet() + "'" +
            "}";
    }
}
