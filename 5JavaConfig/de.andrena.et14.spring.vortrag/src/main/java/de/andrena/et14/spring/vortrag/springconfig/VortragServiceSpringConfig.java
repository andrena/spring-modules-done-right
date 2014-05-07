package de.andrena.et14.spring.vortrag.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.andrena.et14.spring.konferenz.KonferenzListener;
import de.andrena.et14.spring.konferenz.springconfig.KonferenzDaoSpringConfig;
import de.andrena.et14.spring.vortrag.BenachrichtigeReferentenBeiKonferenzAenderung;
import de.andrena.et14.spring.vortrag.IVortragService;
import de.andrena.et14.spring.vortrag.VortragService;

@Configuration
public class VortragServiceSpringConfig {

	@Autowired
	private VortragDaoSpringConfig vortragDaoSpringConfig;

	@Autowired
	private KonferenzDaoSpringConfig konferenzDaoSpringConfig;

	@Bean
	public IVortragService vortragService() {
		VortragService vortragService = new VortragService();
		vortragService.setVortragDao(vortragDaoSpringConfig.vortragDao());
		vortragService.setKonferenzDao(konferenzDaoSpringConfig.konferenzDao());
		return vortragService;
	}

	@Bean
	public KonferenzListener benachrichtigeReferentenBeiKonferenzAenderung() {
		return new BenachrichtigeReferentenBeiKonferenzAenderung();
	}
}
