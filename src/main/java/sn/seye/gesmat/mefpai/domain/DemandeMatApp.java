package sn.seye.gesmat.mefpai.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import sn.seye.gesmat.mefpai.domain.enumeration.Sexe;

/**
 * A DemandeMatApp.
 */
@Entity
@Table(name = "demande_mat_app")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DemandeMatApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom_complet_app", nullable = false)
    private String nomCompletApp;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "etat_demande")
    private String etatDemande;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexe", nullable = false)
    private Sexe sexe;

    @NotNull
    @Column(name = "nationalite", nullable = false)
    private String nationalite;

    @NotNull
    @Column(name = "numero_inscription", nullable = false)
    private Integer numeroInscription;

    @Column(name = "objet")
    private String objet;

    @Column(name = "matricule_app")
    private String matriculeApp;

    @NotNull
    @Column(name = "annee_demande", nullable = false)
    private LocalDate anneeDemande;

    @JsonIgnoreProperties(value = { "inscriptions", "releveNotes", "diplomes", "niveauEtude" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Apprenant apprenant;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DemandeMatApp id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCompletApp() {
        return this.nomCompletApp;
    }

    public DemandeMatApp nomCompletApp(String nomCompletApp) {
        this.setNomCompletApp(nomCompletApp);
        return this;
    }

    public void setNomCompletApp(String nomCompletApp) {
        this.nomCompletApp = nomCompletApp;
    }

    public String getEmail() {
        return this.email;
    }

    public DemandeMatApp email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public DemandeMatApp telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public DemandeMatApp dateNaissance(LocalDate dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public DemandeMatApp adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtatDemande() {
        return this.etatDemande;
    }

    public DemandeMatApp etatDemande(String etatDemande) {
        this.setEtatDemande(etatDemande);
        return this;
    }

    public void setEtatDemande(String etatDemande) {
        this.etatDemande = etatDemande;
    }

    public Sexe getSexe() {
        return this.sexe;
    }

    public DemandeMatApp sexe(Sexe sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getNationalite() {
        return this.nationalite;
    }

    public DemandeMatApp nationalite(String nationalite) {
        this.setNationalite(nationalite);
        return this;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Integer getNumeroInscription() {
        return this.numeroInscription;
    }

    public DemandeMatApp numeroInscription(Integer numeroInscription) {
        this.setNumeroInscription(numeroInscription);
        return this;
    }

    public void setNumeroInscription(Integer numeroInscription) {
        this.numeroInscription = numeroInscription;
    }

    public String getObjet() {
        return this.objet;
    }

    public DemandeMatApp objet(String objet) {
        this.setObjet(objet);
        return this;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMatriculeApp() {
        return this.matriculeApp;
    }

    public DemandeMatApp matriculeApp(String matriculeApp) {
        this.setMatriculeApp(matriculeApp);
        return this;
    }

    public void setMatriculeApp(String matriculeApp) {
        this.matriculeApp = matriculeApp;
    }

    public LocalDate getAnneeDemande() {
        return this.anneeDemande;
    }

    public DemandeMatApp anneeDemande(LocalDate anneeDemande) {
        this.setAnneeDemande(anneeDemande);
        return this;
    }

    public void setAnneeDemande(LocalDate anneeDemande) {
        this.anneeDemande = anneeDemande;
    }

    public Apprenant getApprenant() {
        return this.apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public DemandeMatApp apprenant(Apprenant apprenant) {
        this.setApprenant(apprenant);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeMatApp)) {
            return false;
        }
        return id != null && id.equals(((DemandeMatApp) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DemandeMatApp{" +
            "id=" + getId() +
            ", nomCompletApp='" + getNomCompletApp() + "'" +
            ", email='" + getEmail() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", etatDemande='" + getEtatDemande() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", nationalite='" + getNationalite() + "'" +
            ", numeroInscription=" + getNumeroInscription() +
            ", objet='" + getObjet() + "'" +
            ", matriculeApp='" + getMatriculeApp() + "'" +
            ", anneeDemande='" + getAnneeDemande() + "'" +
            "}";
    }
}
