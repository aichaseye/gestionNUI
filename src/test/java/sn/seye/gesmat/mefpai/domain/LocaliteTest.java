package sn.seye.gesmat.mefpai.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import sn.seye.gesmat.mefpai.web.rest.TestUtil;

class LocaliteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Localite.class);
        Localite localite1 = new Localite();
        localite1.setId(1L);
        Localite localite2 = new Localite();
        localite2.setId(localite1.getId());
        assertThat(localite1).isEqualTo(localite2);
        localite2.setId(2L);
        assertThat(localite1).isNotEqualTo(localite2);
        localite1.setId(null);
        assertThat(localite1).isNotEqualTo(localite2);
    }
}
