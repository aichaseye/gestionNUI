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
import sn.seye.gesmat.mefpai.domain.DemandeMatApp;
import sn.seye.gesmat.mefpai.domain.enumeration.Sexe;
import sn.seye.gesmat.mefpai.repository.DemandeMatAppRepository;

/**
 * Integration tests for the {@link DemandeMatAppResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DemandeMatAppResourceIT {

    private static final String DEFAULT_NOM_COMPLET_APP = "AAAAAAAAAA";
    private static final String UPDATED_NOM_COMPLET_APP = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_ETAT_DEMANDE = "AAAAAAAAAA";
    private static final String UPDATED_ETAT_DEMANDE = "BBBBBBBBBB";

    private static final Sexe DEFAULT_SEXE = Sexe.Masclin;
    private static final Sexe UPDATED_SEXE = Sexe.Feminin;

    private static final String DEFAULT_NATIONALITE = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_INSCRIPTION = 1;
    private static final Integer UPDATED_NUMERO_INSCRIPTION = 2;

    private static final String DEFAULT_OBJET = "AAAAAAAAAA";
    private static final String UPDATED_OBJET = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULE_APP = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE_APP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ANNEE_DEMANDE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANNEE_DEMANDE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/demande-mat-apps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DemandeMatAppRepository demandeMatAppRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandeMatAppMockMvc;

    private DemandeMatApp demandeMatApp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandeMatApp createEntity(EntityManager em) {
        DemandeMatApp demandeMatApp = new DemandeMatApp()
            .nomCompletApp(DEFAULT_NOM_COMPLET_APP)
            .email(DEFAULT_EMAIL)
            .telephone(DEFAULT_TELEPHONE)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .adresse(DEFAULT_ADRESSE)
            .etatDemande(DEFAULT_ETAT_DEMANDE)
            .sexe(DEFAULT_SEXE)
            .nationalite(DEFAULT_NATIONALITE)
            .numeroInscription(DEFAULT_NUMERO_INSCRIPTION)
            .objet(DEFAULT_OBJET)
            .matriculeApp(DEFAULT_MATRICULE_APP)
            .anneeDemande(DEFAULT_ANNEE_DEMANDE);
        return demandeMatApp;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandeMatApp createUpdatedEntity(EntityManager em) {
        DemandeMatApp demandeMatApp = new DemandeMatApp()
            .nomCompletApp(UPDATED_NOM_COMPLET_APP)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .adresse(UPDATED_ADRESSE)
            .etatDemande(UPDATED_ETAT_DEMANDE)
            .sexe(UPDATED_SEXE)
            .nationalite(UPDATED_NATIONALITE)
            .numeroInscription(UPDATED_NUMERO_INSCRIPTION)
            .objet(UPDATED_OBJET)
            .matriculeApp(UPDATED_MATRICULE_APP)
            .anneeDemande(UPDATED_ANNEE_DEMANDE);
        return demandeMatApp;
    }

    @BeforeEach
    public void initTest() {
        demandeMatApp = createEntity(em);
    }

    @Test
    @Transactional
    void createDemandeMatApp() throws Exception {
        int databaseSizeBeforeCreate = demandeMatAppRepository.findAll().size();
        // Create the DemandeMatApp
        restDemandeMatAppMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatApp)))
            .andExpect(status().isCreated());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeCreate + 1);
        DemandeMatApp testDemandeMatApp = demandeMatAppList.get(demandeMatAppList.size() - 1);
        assertThat(testDemandeMatApp.getNomCompletApp()).isEqualTo(DEFAULT_NOM_COMPLET_APP);
        assertThat(testDemandeMatApp.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDemandeMatApp.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testDemandeMatApp.getDateNaissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testDemandeMatApp.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testDemandeMatApp.getEtatDemande()).isEqualTo(DEFAULT_ETAT_DEMANDE);
        assertThat(testDemandeMatApp.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testDemandeMatApp.getNationalite()).isEqualTo(DEFAULT_NATIONALITE);
        assertThat(testDemandeMatApp.getNumeroInscription()).isEqualTo(DEFAULT_NUMERO_INSCRIPTION);
        assertThat(testDemandeMatApp.getObjet()).isEqualTo(DEFAULT_OBJET);
        assertThat(testDemandeMatApp.getMatriculeApp()).isEqualTo(DEFAULT_MATRICULE_APP);
        assertThat(testDemandeMatApp.getAnneeDemande()).isEqualTo(DEFAULT_ANNEE_DEMANDE);
    }

    @Test
    @Transactional
    void createDemandeMatAppWithExistingId() throws Exception {
        // Create the DemandeMatApp with an existing ID
        demandeMatApp.setId(1L);

        int databaseSizeBeforeCreate = demandeMatAppRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeMatAppMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatApp)))
            .andExpect(status().isBadRequest());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNomCompletAppIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatAppRepository.findAll().size();
        // set the field null
        demandeMatApp.setNomCompletApp(null);

        // Create the DemandeMatApp, which fails.

        restDemandeMatAppMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatApp)))
            .andExpect(status().isBadRequest());

        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatAppRepository.findAll().size();
        // set the field null
        demandeMatApp.setEmail(null);

        // Create the DemandeMatApp, which fails.

        restDemandeMatAppMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatApp)))
            .andExpect(status().isBadRequest());

        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatAppRepository.findAll().size();
        // set the field null
        demandeMatApp.setDateNaissance(null);

        // Create the DemandeMatApp, which fails.

        restDemandeMatAppMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatApp)))
            .andExpect(status().isBadRequest());

        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSexeIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatAppRepository.findAll().size();
        // set the field null
        demandeMatApp.setSexe(null);

        // Create the DemandeMatApp, which fails.

        restDemandeMatAppMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatApp)))
            .andExpect(status().isBadRequest());

        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNationaliteIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatAppRepository.findAll().size();
        // set the field null
        demandeMatApp.setNationalite(null);

        // Create the DemandeMatApp, which fails.

        restDemandeMatAppMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatApp)))
            .andExpect(status().isBadRequest());

        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumeroInscriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatAppRepository.findAll().size();
        // set the field null
        demandeMatApp.setNumeroInscription(null);

        // Create the DemandeMatApp, which fails.

        restDemandeMatAppMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatApp)))
            .andExpect(status().isBadRequest());

        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAnneeDemandeIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeMatAppRepository.findAll().size();
        // set the field null
        demandeMatApp.setAnneeDemande(null);

        // Create the DemandeMatApp, which fails.

        restDemandeMatAppMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatApp)))
            .andExpect(status().isBadRequest());

        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDemandeMatApps() throws Exception {
        // Initialize the database
        demandeMatAppRepository.saveAndFlush(demandeMatApp);

        // Get all the demandeMatAppList
        restDemandeMatAppMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandeMatApp.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomCompletApp").value(hasItem(DEFAULT_NOM_COMPLET_APP)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].etatDemande").value(hasItem(DEFAULT_ETAT_DEMANDE)))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].nationalite").value(hasItem(DEFAULT_NATIONALITE)))
            .andExpect(jsonPath("$.[*].numeroInscription").value(hasItem(DEFAULT_NUMERO_INSCRIPTION)))
            .andExpect(jsonPath("$.[*].objet").value(hasItem(DEFAULT_OBJET)))
            .andExpect(jsonPath("$.[*].matriculeApp").value(hasItem(DEFAULT_MATRICULE_APP)))
            .andExpect(jsonPath("$.[*].anneeDemande").value(hasItem(DEFAULT_ANNEE_DEMANDE.toString())));
    }

    @Test
    @Transactional
    void getDemandeMatApp() throws Exception {
        // Initialize the database
        demandeMatAppRepository.saveAndFlush(demandeMatApp);

        // Get the demandeMatApp
        restDemandeMatAppMockMvc
            .perform(get(ENTITY_API_URL_ID, demandeMatApp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demandeMatApp.getId().intValue()))
            .andExpect(jsonPath("$.nomCompletApp").value(DEFAULT_NOM_COMPLET_APP))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.etatDemande").value(DEFAULT_ETAT_DEMANDE))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.nationalite").value(DEFAULT_NATIONALITE))
            .andExpect(jsonPath("$.numeroInscription").value(DEFAULT_NUMERO_INSCRIPTION))
            .andExpect(jsonPath("$.objet").value(DEFAULT_OBJET))
            .andExpect(jsonPath("$.matriculeApp").value(DEFAULT_MATRICULE_APP))
            .andExpect(jsonPath("$.anneeDemande").value(DEFAULT_ANNEE_DEMANDE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDemandeMatApp() throws Exception {
        // Get the demandeMatApp
        restDemandeMatAppMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDemandeMatApp() throws Exception {
        // Initialize the database
        demandeMatAppRepository.saveAndFlush(demandeMatApp);

        int databaseSizeBeforeUpdate = demandeMatAppRepository.findAll().size();

        // Update the demandeMatApp
        DemandeMatApp updatedDemandeMatApp = demandeMatAppRepository.findById(demandeMatApp.getId()).get();
        // Disconnect from session so that the updates on updatedDemandeMatApp are not directly saved in db
        em.detach(updatedDemandeMatApp);
        updatedDemandeMatApp
            .nomCompletApp(UPDATED_NOM_COMPLET_APP)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .adresse(UPDATED_ADRESSE)
            .etatDemande(UPDATED_ETAT_DEMANDE)
            .sexe(UPDATED_SEXE)
            .nationalite(UPDATED_NATIONALITE)
            .numeroInscription(UPDATED_NUMERO_INSCRIPTION)
            .objet(UPDATED_OBJET)
            .matriculeApp(UPDATED_MATRICULE_APP)
            .anneeDemande(UPDATED_ANNEE_DEMANDE);

        restDemandeMatAppMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDemandeMatApp.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDemandeMatApp))
            )
            .andExpect(status().isOk());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeUpdate);
        DemandeMatApp testDemandeMatApp = demandeMatAppList.get(demandeMatAppList.size() - 1);
        assertThat(testDemandeMatApp.getNomCompletApp()).isEqualTo(UPDATED_NOM_COMPLET_APP);
        assertThat(testDemandeMatApp.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDemandeMatApp.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testDemandeMatApp.getDateNaissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testDemandeMatApp.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testDemandeMatApp.getEtatDemande()).isEqualTo(UPDATED_ETAT_DEMANDE);
        assertThat(testDemandeMatApp.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testDemandeMatApp.getNationalite()).isEqualTo(UPDATED_NATIONALITE);
        assertThat(testDemandeMatApp.getNumeroInscription()).isEqualTo(UPDATED_NUMERO_INSCRIPTION);
        assertThat(testDemandeMatApp.getObjet()).isEqualTo(UPDATED_OBJET);
        assertThat(testDemandeMatApp.getMatriculeApp()).isEqualTo(UPDATED_MATRICULE_APP);
        assertThat(testDemandeMatApp.getAnneeDemande()).isEqualTo(UPDATED_ANNEE_DEMANDE);
    }

    @Test
    @Transactional
    void putNonExistingDemandeMatApp() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatAppRepository.findAll().size();
        demandeMatApp.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeMatAppMockMvc
            .perform(
                put(ENTITY_API_URL_ID, demandeMatApp.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demandeMatApp))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDemandeMatApp() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatAppRepository.findAll().size();
        demandeMatApp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeMatAppMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demandeMatApp))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDemandeMatApp() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatAppRepository.findAll().size();
        demandeMatApp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeMatAppMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeMatApp)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDemandeMatAppWithPatch() throws Exception {
        // Initialize the database
        demandeMatAppRepository.saveAndFlush(demandeMatApp);

        int databaseSizeBeforeUpdate = demandeMatAppRepository.findAll().size();

        // Update the demandeMatApp using partial update
        DemandeMatApp partialUpdatedDemandeMatApp = new DemandeMatApp();
        partialUpdatedDemandeMatApp.setId(demandeMatApp.getId());

        partialUpdatedDemandeMatApp
            .adresse(UPDATED_ADRESSE)
            .sexe(UPDATED_SEXE)
            .nationalite(UPDATED_NATIONALITE)
            .matriculeApp(UPDATED_MATRICULE_APP)
            .anneeDemande(UPDATED_ANNEE_DEMANDE);

        restDemandeMatAppMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemandeMatApp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDemandeMatApp))
            )
            .andExpect(status().isOk());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeUpdate);
        DemandeMatApp testDemandeMatApp = demandeMatAppList.get(demandeMatAppList.size() - 1);
        assertThat(testDemandeMatApp.getNomCompletApp()).isEqualTo(DEFAULT_NOM_COMPLET_APP);
        assertThat(testDemandeMatApp.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDemandeMatApp.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testDemandeMatApp.getDateNaissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testDemandeMatApp.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testDemandeMatApp.getEtatDemande()).isEqualTo(DEFAULT_ETAT_DEMANDE);
        assertThat(testDemandeMatApp.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testDemandeMatApp.getNationalite()).isEqualTo(UPDATED_NATIONALITE);
        assertThat(testDemandeMatApp.getNumeroInscription()).isEqualTo(DEFAULT_NUMERO_INSCRIPTION);
        assertThat(testDemandeMatApp.getObjet()).isEqualTo(DEFAULT_OBJET);
        assertThat(testDemandeMatApp.getMatriculeApp()).isEqualTo(UPDATED_MATRICULE_APP);
        assertThat(testDemandeMatApp.getAnneeDemande()).isEqualTo(UPDATED_ANNEE_DEMANDE);
    }

    @Test
    @Transactional
    void fullUpdateDemandeMatAppWithPatch() throws Exception {
        // Initialize the database
        demandeMatAppRepository.saveAndFlush(demandeMatApp);

        int databaseSizeBeforeUpdate = demandeMatAppRepository.findAll().size();

        // Update the demandeMatApp using partial update
        DemandeMatApp partialUpdatedDemandeMatApp = new DemandeMatApp();
        partialUpdatedDemandeMatApp.setId(demandeMatApp.getId());

        partialUpdatedDemandeMatApp
            .nomCompletApp(UPDATED_NOM_COMPLET_APP)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .adresse(UPDATED_ADRESSE)
            .etatDemande(UPDATED_ETAT_DEMANDE)
            .sexe(UPDATED_SEXE)
            .nationalite(UPDATED_NATIONALITE)
            .numeroInscription(UPDATED_NUMERO_INSCRIPTION)
            .objet(UPDATED_OBJET)
            .matriculeApp(UPDATED_MATRICULE_APP)
            .anneeDemande(UPDATED_ANNEE_DEMANDE);

        restDemandeMatAppMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemandeMatApp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDemandeMatApp))
            )
            .andExpect(status().isOk());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeUpdate);
        DemandeMatApp testDemandeMatApp = demandeMatAppList.get(demandeMatAppList.size() - 1);
        assertThat(testDemandeMatApp.getNomCompletApp()).isEqualTo(UPDATED_NOM_COMPLET_APP);
        assertThat(testDemandeMatApp.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDemandeMatApp.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testDemandeMatApp.getDateNaissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testDemandeMatApp.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testDemandeMatApp.getEtatDemande()).isEqualTo(UPDATED_ETAT_DEMANDE);
        assertThat(testDemandeMatApp.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testDemandeMatApp.getNationalite()).isEqualTo(UPDATED_NATIONALITE);
        assertThat(testDemandeMatApp.getNumeroInscription()).isEqualTo(UPDATED_NUMERO_INSCRIPTION);
        assertThat(testDemandeMatApp.getObjet()).isEqualTo(UPDATED_OBJET);
        assertThat(testDemandeMatApp.getMatriculeApp()).isEqualTo(UPDATED_MATRICULE_APP);
        assertThat(testDemandeMatApp.getAnneeDemande()).isEqualTo(UPDATED_ANNEE_DEMANDE);
    }

    @Test
    @Transactional
    void patchNonExistingDemandeMatApp() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatAppRepository.findAll().size();
        demandeMatApp.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeMatAppMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, demandeMatApp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(demandeMatApp))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDemandeMatApp() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatAppRepository.findAll().size();
        demandeMatApp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeMatAppMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(demandeMatApp))
            )
            .andExpect(status().isBadRequest());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDemandeMatApp() throws Exception {
        int databaseSizeBeforeUpdate = demandeMatAppRepository.findAll().size();
        demandeMatApp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandeMatAppMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(demandeMatApp))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DemandeMatApp in the database
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDemandeMatApp() throws Exception {
        // Initialize the database
        demandeMatAppRepository.saveAndFlush(demandeMatApp);

        int databaseSizeBeforeDelete = demandeMatAppRepository.findAll().size();

        // Delete the demandeMatApp
        restDemandeMatAppMockMvc
            .perform(delete(ENTITY_API_URL_ID, demandeMatApp.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DemandeMatApp> demandeMatAppList = demandeMatAppRepository.findAll();
        assertThat(demandeMatAppList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
