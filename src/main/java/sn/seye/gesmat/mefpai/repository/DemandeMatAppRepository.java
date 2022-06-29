package sn.seye.gesmat.mefpai.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.seye.gesmat.mefpai.domain.DemandeMatApp;

/**
 * Spring Data SQL repository for the DemandeMatApp entity.
 */
@Repository
public interface DemandeMatAppRepository extends JpaRepository<DemandeMatApp, Long> {
    default Optional<DemandeMatApp> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<DemandeMatApp> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<DemandeMatApp> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct demandeMatApp from DemandeMatApp demandeMatApp left join fetch demandeMatApp.apprenant",
        countQuery = "select count(distinct demandeMatApp) from DemandeMatApp demandeMatApp"
    )
    Page<DemandeMatApp> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct demandeMatApp from DemandeMatApp demandeMatApp left join fetch demandeMatApp.apprenant")
    List<DemandeMatApp> findAllWithToOneRelationships();

    @Query("select demandeMatApp from DemandeMatApp demandeMatApp left join fetch demandeMatApp.apprenant where demandeMatApp.id =:id")
    Optional<DemandeMatApp> findOneWithToOneRelationships(@Param("id") Long id);
}
