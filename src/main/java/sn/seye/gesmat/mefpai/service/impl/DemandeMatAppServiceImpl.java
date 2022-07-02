package sn.seye.gesmat.mefpai.service.impl;

import java.util.Calendar;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.seye.gesmat.mefpai.domain.DemandeMatApp;
import sn.seye.gesmat.mefpai.domain.enumeration.Sexe;
import sn.seye.gesmat.mefpai.repository.DemandeMatAppRepository;
import sn.seye.gesmat.mefpai.service.DemandeMatAppService;

/**
 * Service Implementation for managing {@link DemandeMatApp}.
 */
@Service
@Transactional
public class DemandeMatAppServiceImpl implements DemandeMatAppService {

    private final Logger log = LoggerFactory.getLogger(DemandeMatAppServiceImpl.class);

    private final DemandeMatAppRepository demandeMatAppRepository;

    public DemandeMatAppServiceImpl(DemandeMatAppRepository demandeMatAppRepository) {
        this.demandeMatAppRepository = demandeMatAppRepository;
    }

    @Override
    public DemandeMatApp save(DemandeMatApp demandeMatApp) {
        log.debug("Request to save DemandeMatApp : {}", demandeMatApp);

        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String date = String.valueOf(System.currentTimeMillis());
        String initiales = demandeMatApp.getNomCompletApp().substring(date.length() - 2).toUpperCase();
        String sexe = "M";
        if (demandeMatApp.getSexe().equals(Sexe.Feminin)) {
            sexe = "F";
        }
        String matricule = year.substring(year.length() - 2).concat(sexe).concat(date.substring(date.length() - 4)).concat(initiales);

        demandeMatApp.setMatriculeApp(matricule);

        return demandeMatAppRepository.save(demandeMatApp);
    }

    @Override
    public Optional<DemandeMatApp> partialUpdate(DemandeMatApp demandeMatApp) {
        log.debug("Request to partially update DemandeMatApp : {}", demandeMatApp);

        return demandeMatAppRepository
            .findById(demandeMatApp.getId())
            .map(existingDemandeMatApp -> {
                if (demandeMatApp.getNomCompletApp() != null) {
                    existingDemandeMatApp.setNomCompletApp(demandeMatApp.getNomCompletApp());
                }
                if (demandeMatApp.getEmail() != null) {
                    existingDemandeMatApp.setEmail(demandeMatApp.getEmail());
                }
                if (demandeMatApp.getTelephone() != null) {
                    existingDemandeMatApp.setTelephone(demandeMatApp.getTelephone());
                }
                if (demandeMatApp.getDateNaissance() != null) {
                    existingDemandeMatApp.setDateNaissance(demandeMatApp.getDateNaissance());
                }
                if (demandeMatApp.getAdresse() != null) {
                    existingDemandeMatApp.setAdresse(demandeMatApp.getAdresse());
                }
                if (demandeMatApp.getEtatDemande() != null) {
                    existingDemandeMatApp.setEtatDemande(demandeMatApp.getEtatDemande());
                }
                if (demandeMatApp.getSexe() != null) {
                    existingDemandeMatApp.setSexe(demandeMatApp.getSexe());
                }
                if (demandeMatApp.getNationalite() != null) {
                    existingDemandeMatApp.setNationalite(demandeMatApp.getNationalite());
                }
                if (demandeMatApp.getNumeroInscription() != null) {
                    existingDemandeMatApp.setNumeroInscription(demandeMatApp.getNumeroInscription());
                }
                if (demandeMatApp.getObjet() != null) {
                    existingDemandeMatApp.setObjet(demandeMatApp.getObjet());
                }
                if (demandeMatApp.getMatriculeApp() != null) {
                    existingDemandeMatApp.setMatriculeApp(demandeMatApp.getMatriculeApp());
                }
                if (demandeMatApp.getAnneeDemande() != null) {
                    existingDemandeMatApp.setAnneeDemande(demandeMatApp.getAnneeDemande());
                }

                return existingDemandeMatApp;
            })
            .map(demandeMatAppRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DemandeMatApp> findAll(Pageable pageable) {
        log.debug("Request to get all DemandeMatApps");
        return demandeMatAppRepository.findAll(pageable);
    }

    public Page<DemandeMatApp> findAllWithEagerRelationships(Pageable pageable) {
        return demandeMatAppRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DemandeMatApp> findOne(Long id) {
        log.debug("Request to get DemandeMatApp : {}", id);
        return demandeMatAppRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DemandeMatApp : {}", id);
        demandeMatAppRepository.deleteById(id);
    }
}
