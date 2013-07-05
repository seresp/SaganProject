/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package integration.configuration;

import org.junit.After;
import org.junit.Test;
import org.springframework.bootstrap.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.index.configuration.CrawlerConfiguration;

import static junit.framework.Assert.assertNotNull;

public class CrawlerConfigurationTests {

	//TODO make this dynamic
	public static final int PORT = 9080;

	private ConfigurableApplicationContext context;

	@After
	public void clean() {
		if (context != null) {
			context.close();
		}
	}

	@Test
	public void testContextLoading() throws Exception {
		SpringApplication application = CrawlerConfiguration.build(CrawlerOfflineConfiguration.class);
		application.setDefaultCommandLineArgs("--server.port=" + PORT, "--spring.database.url=jdbc:hsqldb:mem:acceptancetestdb", "--search.index.delay=6000000");
		context = (ConfigurableApplicationContext) application.run();
		CrawlerConfiguration configuration = context.getBean(CrawlerConfiguration.class);
		assertNotNull(configuration);
		context.close();
	}

}
