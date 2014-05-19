package de.andrena.et14.spring.konferenz.springconfig;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.andrena.et14.spring.konferenz.IKonferenzService;
import de.andrena.et14.spring.konferenz.KonferenzListener;
import de.andrena.et14.spring.konferenz.KonferenzService;

@Configuration
public class KonferenzServiceSpringConfig {

	@Autowired
	private KonferenzDaoSpringConfig konferenzDaoSpringConfig;

	@Bean
	public IKonferenzService konferenzService() {
		KonferenzService konferenzService = new KonferenzService();
		konferenzService.setKonferenzDao(konferenzDaoSpringConfig.konferenzDao());
		ArrayList<KonferenzListener> listeners = new ArrayList<>();
		// TODO
		konferenzService.setListeners(listeners);
		return konferenzService;
	}
}
