package sn.seye.gesmat.mefpai.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import sn.seye.gesmat.mefpai.IntegrationTest;
import sn.seye.gesmat.mefpai.domain.DemandeMatEtab;
import sn.seye.gesmat.mefpai.domain.enumeration.NomDep;
import sn.seye.gesmat.mefpai.domain.enumeration.StatutEtab;
import sn.seye.gesmat.mefpai.domain.enumeration.TypeEtab;
import sn.seye.gesmat.mefpai.domain.enumeration.TypeInspection;
import sn.seye.gesmat.mefpai.repository.DemandeMatEtabRepository;

/**
 * Integration tests for the {@link DemandeMatEtabResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DemandeMatEtabResourceIT {

    private static final String DEFAULT_NOM_COMPLET = "AAAAAAAAAA";
    private static final String UPDATED_NOM_COMPLET = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABILITE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABILITE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ETAB = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ETAB = "BBBBBBBBBB";

    private static final NomDep DEFAULT_DEPARTEMENT = NomDep.Dakar;
    private static final NomDep UPDATED_DEPARTEMENT = NomDep.Pikine;

    private static final TypeEtab DEFAULT_TYPE_ETAB = TypeEtab.LyceeTechnique;
    private static final TypeEtab UPDATED_TYPE_ETAB = TypeEtab.CFP;

    private static final StatutEtab DEFAULT_STATUT = StatutEtab.Prive;
    private static final StatutEtab UPDATED_STATUT = StatutEtab.Public;

    private static final LocalDate DEFAULT_ANNEE_DEMANDE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANNEE_DEMANDE = LocalDate.now(ZoneId.systemDefault());

    private static final TypeInspection DEFAULT_TYPE_INSP = TypeInspection.IA;
    private static final TypeInspection UPDATED_TYPE_INSP = TypeInspection.IEF;

    private static final String DEFAULT_ETAT_DEMANDE = "AAAAAAAAAA";
    private static final String UPDATED_ETAT_DEMANDE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_OBJET = "AAAAAAAAAA";
    private static final String UPDATED_OBJET = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/demande-mat-etabs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DemandeMatEtabRepository demandeMatEtabRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandeMatEtabMockMvc;

    private DemandeMatEtab demandeMatEtab;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandeMatEtab createEntity(EntityManager em) {
        DemandeMatEtab demandeMatEtab = new DemandeMatEtab()
            .nomComplet(DEFAULT_NOM_COMPLET)
            .responsabilite(DEFAULT_RESPONSABILITE)
            .nomEtab(DEFAULT_NOM_ETAB)
            .departement(DEFAULT_DEPARTEMENT)
            .typeEtab(DEFAULT_TYPE_ETAB)
            .statut(DEFAULT_STATUT)
            .anneeDemande(DEFAULT_ANNEE_DEMANDE)
            .typeInsp(DEFAULT_TYPE_INSP)
            .etatDemande(DEFAULT_ETAT_DEMANDE)
            .email(DEFAULT_EMAIL)
            .objet(DEFAULT_OBJET);
        return demandeMatEtab;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandeMatEtab createUpdatedEntity(EntityManager em) {
        DemandeMatEtab demandeMatEtab = new DemandeMatEtab()
            .nomComplet(UPDATED_NOM_COMPLET)
            .responsabilite(UPDATED_RESPONSABILITE)
            .nomEtab(UPDATED_NOM_ETAB)
            .departement(UPDATED_DEPARTEMENT)
            .typeEtab(UPDATED_TYPE_ETAB)
            .statut(UPDATED_STATUT)
            .anneeDemande(UPDATED_ANNEE_DEMANDE)
            .typeInsp(UPDATED_TYPE_INSP)
            .etatDemande(UPDATED_ETAT_DEMANDE)
            .email(UPDATED_EMAIL)
            .objet(UPDATED_OBJET);
        return demandeMatEtab;
    }

    @BeforeEach
    public void initTest() {
        demandeMatEtab = createEntity(em);
    }

    @Test
    @Transactional
    void createDemandeMatEtab() throws Exception {
        int databaseSizeBeforeCreate = demandeMatEtabRepository.findAll().size();
        // Create the DemandeMatEtab
        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isCreated());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeCreate + 1);
        DemandeMatEtab testDemandeMatEtab = demandeMatEtabList.get(demandeMatEtabList.size() - 1);
        assertThat(testDemandeMatEtab.getNomComplet()).isEqualTo(DEFAULT_NOM_COMPLET);
        assertThat(testDemandeMatEtab.getResponsabilite()).isEqualTo(DEFAULT_RESPONSABILITE);
        assertThat(testDemandeMatEtab.getNomEtab()).isEqualTo(DEFAULT_NOM_ETAB);
        assertThat(testDemandeMatEtab.getDepartement()).isEqualTo(DEFAULT_DEPARTEMENT);
        assertThat(testDemandeMatEtab.getTypeEtab()).isEqualTo(DEFAULT_TYPE_ETAB);
        assertThat(testDemandeMatEtab.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testDemandeMatEtab.getAnneeDemande()).isEqualTo(DEFAULT_ANNEE_DEMANDE);
        assertThat(testDemandeMatEtab.getTypeInsp()).isEqualTo(DEFAULT_TYPE_INSP);
        assertThat(testDemandeMatEtab.getEtatDemande()).isEqualTo(DEFAULT_ETAT_DEMANDE);
        assertThat(testDemandeMatEtab.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDemandeMatEtab.getObjet()).isEqualTo(DEFAULT_OBJET);
    }

    @Test
    @Transactional
    void createDemandeMatEtabWithExistingId() throws Exception {
        // Create the DemandeMatEtab with an existing ID
        demandeMatEtab.setId(1L);

        int databaseSizeBeforeCreate = demandeMatEtabRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNomCompletIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatEtabRepository.findAll().size();
        // set the field null
        demandeMatEtab.setNomComplet(null);

        // Create the DemandeMatEtab, which fails.

        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkResponsabiliteIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatEtabRepository.findAll().size();
        // set the field null
        demandeMatEtab.setResponsabilite(null);

        // Create the DemandeMatEtab, which fails.

        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNomEtabIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatEtabRepository.findAll().size();
        // set the field null
        demandeMatEtab.setNomEtab(null);

        // Create the DemandeMatEtab, which fails.

        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDepartementIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatEtabRepository.findAll().size();
        // set the field null
        demandeMatEtab.setDepartement(null);

        // Create the DemandeMatEtab, which fails.

        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeEtabIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatEtabRepository.findAll().size();
        // set the field null
        demandeMatEtab.setTypeEtab(null);

        // Create the DemandeMatEtab, which fails.

        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatutIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatEtabRepository.findAll().size();
        // set the field null
        demandeMatEtab.setStatut(null);

        // Create the DemandeMatEtab, which fails.

        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAnneeDemandeIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatEtabRepository.findAll().size();
        // set the field null
        demandeMatEtab.setAnneeDemande(null);

        // Create the DemandeMatEtab, which fails.

        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeInspIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatEtabRepository.findAll().size();
        // set the field null
        demandeMatEtab.setTypeInsp(null);

        // Create the DemandeMatEtab, which fails.

        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatEtabRepository.findAll().size();
        // set the field null
        demandeMatEtab.setEmail(null);

        // Create the DemandeMatEtab, which fails.

        restDemandeMatEtabMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDemandeMatEtabs() throws Exception {
        // Initialize the database
        demandeMatEtabRepository.saveAndFlush(demandeMatEtab);

        // Get all the demandeMatEtabList
        restDemandeMatEtabMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandeMatEtab.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomComplet").value(hasItem(DEFAULT_NOM_COMPLET)))
            .andExpect(jsonPath("$.[*].responsabilite").value(hasItem(DEFAULT_RESPONSABILITE)))
            .andExpect(jsonPath("$.[*].nomEtab").value(hasItem(DEFAULT_NOM_ETAB)))
            .andExpect(jsonPath("$.[*].departement").value(hasItem(DEFAULT_DEPARTEMENT.toString())))
            .andExpect(jsonPath("$.[*].typeEtab").value(hasItem(DEFAULT_TYPE_ETAB.toString())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].anneeDemande").value(hasItem(DEFAULT_ANNEE_DEMANDE.toString())))
            .andExpect(jsonPath("$.[*].typeInsp").value(hasItem(DEFAULT_TYPE_INSP.toString())))
            .andExpect(jsonPath("$.[*].etatDemande").value(hasItem(DEFAULT_ETAT_DEMANDE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].objet").value(hasItem(DEFAULT_OBJET)));
    }

    @Test
    @Transactional
    void getDemandeMatEtab() throws Exception {
        // Initialize the database
        demandeMatEtabRepository.saveAndFlush(demandeMatEtab);

        // Get the demandeMatEtab
        restDemandeMatEtabMockMvc
            .perform(get(ENTITY_API_URL_ID, demandeMatEtab.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demandeMatEtab.getId().intValue()))
            .andExpect(jsonPath("$.nomComplet").value(DEFAULT_NOM_COMPLET))
            .andExpect(jsonPath("$.responsabilite").value(DEFAULT_RESPONSABILITE))
            .andExpect(jsonPath("$.nomEtab").value(DEFAULT_NOM_ETAB))
            .andExpect(jsonPath("$.departement").value(DEFAULT_DEPARTEMENT.toString()))
            .andExpect(jsonPath("$.typeEtab").value(DEFAULT_TYPE_ETAB.toString()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()))
            .andExpect(jsonPath("$.anneeDemande").value(DEFAULT_ANNEE_DEMANDE.toString()))
            .andExpect(jsonPath("$.typeInsp").value(DEFAULT_TYPE_INSP.toString()))
            .andExpect(jsonPath("$.etatDemande").value(DEFAULT_ETAT_DEMANDE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.objet").value(DEFAULT_OBJET));
    }

    @Test
    @Transactional
    void getNonExistingDemandeMatEtab() throws Exception {
        // Get the demandeMatEtab
        restDemandeMatEtabMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDemandeMatEtab() throws Exception {
        // Initialize the database
        demandeMatEtabRepository.saveAndFlush(demandeMatEtab);

        int databaseSizeBeforeUpdate = demandeMatEtabRepository.findAll().size();

        // Update the demandeMatEtab
        DemandeMatEtab updatedDemandeMatEtab = demandeMatEtabRepository.findById(demandeMatEtab.getId()).get();
        // Disconnect from session so that the updates on updatedDemandeMatEtab are not directly saved in db
        em.detach(updatedDemandeMatEtab);
        updatedDemandeMatEtab
            .nomComplet(UPDATED_NOM_COMPLET)
            .responsabilite(UPDATED_RESPONSABILITE)
            .nomEtab(UPDATED_NOM_ETAB)
            .departement(UPDATED_DEPARTEMENT)
            .typeEtab(UPDATED_TYPE_ETAB)
            .statut(UPDATED_STATUT)
            .anneeDemande(UPDATED_ANNEE_DEMANDE)
            .typeInsp(UPDATED_TYPE_INSP)
            .etatDemande(UPDATED_ETAT_DEMANDE)
            .email(UPDATED_EMAIL)
            .objet(UPDATED_OBJET);

        restDemandeMatEtabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDemandeMatEtab.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDemandeMatEtab))
            )
            .andExpect(status().isOk());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeUpdate);
        DemandeMatEtab testDemandeMatEtab = demandeMatEtabList.get(demandeMatEtabList.size() - 1);
        assertThat(testDemandeMatEtab.getNomComplet()).isEqualTo(UPDATED_NOM_COMPLET);
        assertThat(testDemandeMatEtab.getResponsabilite()).isEqualTo(UPDATED_RESPONSABILITE);
        assertThat(testDemandeMatEtab.getNomEtab()).isEqualTo(UPDATED_NOM_ETAB);
        assertThat(testDemandeMatEtab.getDepartement()).isEqualTo(UPDATED_DEPARTEMENT);
        assertThat(testDemandeMatEtab.getTypeEtab()).isEqualTo(UPDATED_TYPE_ETAB);
        assertThat(testDemandeMatEtab.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testDemandeMatEtab.getAnneeDemande()).isEqualTo(UPDATED_ANNEE_DEMANDE);
        assertThat(testDemandeMatEtab.getTypeInsp()).isEqualTo(UPDATED_TYPE_INSP);
        assertThat(testDemandeMatEtab.getEtatDemande()).isEqualTo(UPDATED_ETAT_DEMANDE);
        assertThat(testDemandeMatEtab.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDemandeMatEtab.getObjet()).isEqualTo(UPDATED_OBJET);
    }

    @Test
    @Transactional
    void putNonExistingDemandeMatEtab() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatEtabRepository.findAll().size();
        demandeMatEtab.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeMatEtabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, demandeMatEtab.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDemandeMatEtab() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatEtabRepository.findAll().size();
        demandeMatEtab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeMatEtabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDemandeMatEtab() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatEtabRepository.findAll().size();
        demandeMatEtab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeMatEtabMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatEtab)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDemandeMatEtabWithPatch() throws Exception {
        // Initialize the database
        demandeMatEtabRepository.saveAndFlush(demandeMatEtab);

        int databaseSizeBeforeUpdate = demandeMatEtabRepository.findAll().size();

        // Update the demandeMatEtab using partial update
        DemandeMatEtab partialUpdatedDemandeMatEtab = new DemandeMatEtab();
        partialUpdatedDemandeMatEtab.setId(demandeMatEtab.getId());

        partialUpdatedDemandeMatEtab.etatDemande(UPDATED_ETAT_DEMANDE).email(UPDATED_EMAIL).objet(UPDATED_OBJET);

        restDemandeMatEtabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemandeMatEtab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDemandeMatEtab))
            )
            .andExpect(status().isOk());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeUpdate);
        DemandeMatEtab testDemandeMatEtab = demandeMatEtabList.get(demandeMatEtabList.size() - 1);
        assertThat(testDemandeMatEtab.getNomComplet()).isEqualTo(DEFAULT_NOM_COMPLET);
        assertThat(testDemandeMatEtab.getResponsabilite()).isEqualTo(DEFAULT_RESPONSABILITE);
        assertThat(testDemandeMatEtab.getNomEtab()).isEqualTo(DEFAULT_NOM_ETAB);
        assertThat(testDemandeMatEtab.getDepartement()).isEqualTo(DEFAULT_DEPARTEMENT);
        assertThat(testDemandeMatEtab.getTypeEtab()).isEqualTo(DEFAULT_TYPE_ETAB);
        assertThat(testDemandeMatEtab.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testDemandeMatEtab.getAnneeDemande()).isEqualTo(DEFAULT_ANNEE_DEMANDE);
        assertThat(testDemandeMatEtab.getTypeInsp()).isEqualTo(DEFAULT_TYPE_INSP);
        assertThat(testDemandeMatEtab.getEtatDemande()).isEqualTo(UPDATED_ETAT_DEMANDE);
        assertThat(testDemandeMatEtab.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDemandeMatEtab.getObjet()).isEqualTo(UPDATED_OBJET);
    }

    @Test
    @Transactional
    void fullUpdateDemandeMatEtabWithPatch() throws Exception {
        // Initialize the database
        demandeMatEtabRepository.saveAndFlush(demandeMatEtab);

        int databaseSizeBeforeUpdate = demandeMatEtabRepository.findAll().size();

        // Update the demandeMatEtab using partial update
        DemandeMatEtab partialUpdatedDemandeMatEtab = new DemandeMatEtab();
        partialUpdatedDemandeMatEtab.setId(demandeMatEtab.getId());

        partialUpdatedDemandeMatEtab
            .nomComplet(UPDATED_NOM_COMPLET)
            .responsabilite(UPDATED_RESPONSABILITE)
            .nomEtab(UPDATED_NOM_ETAB)
            .departement(UPDATED_DEPARTEMENT)
            .typeEtab(UPDATED_TYPE_ETAB)
            .statut(UPDATED_STATUT)
            .anneeDemande(UPDATED_ANNEE_DEMANDE)
            .typeInsp(UPDATED_TYPE_INSP)
            .etatDemande(UPDATED_ETAT_DEMANDE)
            .email(UPDATED_EMAIL)
            .objet(UPDATED_OBJET);

        restDemandeMatEtabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemandeMatEtab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDemandeMatEtab))
            )
            .andExpect(status().isOk());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeUpdate);
        DemandeMatEtab testDemandeMatEtab = demandeMatEtabList.get(demandeMatEtabList.size() - 1);
        assertThat(testDemandeMatEtab.getNomComplet()).isEqualTo(UPDATED_NOM_COMPLET);
        assertThat(testDemandeMatEtab.getResponsabilite()).isEqualTo(UPDATED_RESPONSABILITE);
        assertThat(testDemandeMatEtab.getNomEtab()).isEqualTo(UPDATED_NOM_ETAB);
        assertThat(testDemandeMatEtab.getDepartement()).isEqualTo(UPDATED_DEPARTEMENT);
        assertThat(testDemandeMatEtab.getTypeEtab()).isEqualTo(UPDATED_TYPE_ETAB);
        assertThat(testDemandeMatEtab.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testDemandeMatEtab.getAnneeDemande()).isEqualTo(UPDATED_ANNEE_DEMANDE);
        assertThat(testDemandeMatEtab.getTypeInsp()).isEqualTo(UPDATED_TYPE_INSP);
        assertThat(testDemandeMatEtab.getEtatDemande()).isEqualTo(UPDATED_ETAT_DEMANDE);
        assertThat(testDemandeMatEtab.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDemandeMatEtab.getObjet()).isEqualTo(UPDATED_OBJET);
    }

    @Test
    @Transactional
    void patchNonExistingDemandeMatEtab() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatEtabRepository.findAll().size();
        demandeMatEtab.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeMatEtabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, demandeMatEtab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDemandeMatEtab() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatEtabRepository.findAll().size();
        demandeMatEtab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeMatEtabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDemandeMatEtab() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatEtabRepository.findAll().size();
        demandeMatEtab.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeMatEtabMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(demandeMatEtab))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DemandeMatEtab in the database
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDemandeMatEtab() throws Exception {
        // Initialize the database
        demandeMatEtabRepository.saveAndFlush(demandeMatEtab);

        int databaseSizeBeforeDelete = demandeMatEtabRepository.findAll().size();

        // Delete the demandeMatEtab
        restDemandeMatEtabMockMvc
            .perform(delete(ENTITY_API_URL_ID, demandeMatEtab.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DemandeMatEtab> demandeMatEtabList = demandeMatEtabRepository.findAll();
        assertThat(demandeMatEtabList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
