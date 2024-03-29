package sn.seye.gesmat.mefpai.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sn.seye.gesmat.mefpai.domain.Localite;
import sn.seye.gesmat.mefpai.repository.LocaliteRepository;
import sn.seye.gesmat.mefpai.service.LocaliteService;
import sn.seye.gesmat.mefpai.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link sn.seye.gesmat.mefpai.domain.Localite}.
 */
@RestController
@RequestMapping("/api")
public class LocaliteResource {

    private final Logger log = LoggerFactory.getLogger(LocaliteResource.class);

    private static final String ENTITY_NAME = "localite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LocaliteService localiteService;

    private final LocaliteRepository localiteRepository;

    public LocaliteResource(LocaliteService localiteService, LocaliteRepository localiteRepository) {
        this.localiteService = localiteService;
        this.localiteRepository = localiteRepository;
    }

    /**
     * {@code POST  /localites} : Create a new localite.
     *
     * @param localite the localite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new localite, or with status {@code 400 (Bad Request)} if the localite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/localites")
    public ResponseEntity<Localite> createLocalite(@Valid @RequestBody Localite localite) throws URISyntaxException {
        log.debug("REST request to save Localite : {}", localite);
        if (localite.getId() != null) {
            throw new BadRequestAlertException("A new localite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Localite result = localiteService.save(localite);
        return ResponseEntity
            .created(new URI("/api/localites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /localites/:id} : Updates an existing localite.
     *
     * @param id the id of the localite to save.
     * @param localite the localite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated localite,
     * or with status {@code 400 (Bad Request)} if the localite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the localite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/localites/{id}")
    public ResponseEntity<Localite> updateLocalite(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Localite localite
    ) throws URISyntaxException {
        log.debug("REST request to update Localite : {}, {}", id, localite);
        if (localite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, localite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!localiteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Localite result = localiteService.save(localite);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, localite.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /localites/:id} : Partial updates given fields of an existing localite, field will ignore if it is null
     *
     * @param id the id of the localite to save.
     * @param localite the localite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated localite,
     * or with status {@code 400 (Bad Request)} if the localite is not valid,
     * or with status {@code 404 (Not Found)} if the localite is not found,
     * or with status {@code 500 (Internal Server Error)} if the localite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/localites/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Localite> partialUpdateLocalite(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Localite localite
    ) throws URISyntaxException {
        log.debug("REST request to partial update Localite partially : {}, {}", id, localite);
        if (localite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, localite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!localiteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Localite> result = localiteService.partialUpdate(localite);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, localite.getId().toString())
        );
    }

    /**
     * {@code GET  /localites} : get all the localites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of localites in body.
     */
    @GetMapping("/localites")
    public ResponseEntity<List<Localite>> getAllLocalites(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Localites");
        Page<Localite> page = localiteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /localites/:id} : get the "id" localite.
     *
     * @param id the id of the localite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the localite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/localites/{id}")
    public ResponseEntity<Localite> getLocalite(@PathVariable Long id) {
        log.debug("REST request to get Localite : {}", id);
        Optional<Localite> localite = localiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(localite);
    }

    /**
     * {@code DELETE  /localites/:id} : delete the "id" localite.
     *
     * @param id the id of the localite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/localites/{id}")
    public ResponseEntity<Void> deleteLocalite(@PathVariable Long id) {
        log.debug("REST request to delete Localite : {}", id);
        localiteService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
