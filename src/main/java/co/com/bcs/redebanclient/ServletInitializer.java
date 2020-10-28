package co.com.bcs.redebanclient;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	private static final Class<RedebanClientApplication> SOURCE =RedebanClientApplication.class;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServletInitializer.SOURCE).profiles("weblogic");
	}

	

}
