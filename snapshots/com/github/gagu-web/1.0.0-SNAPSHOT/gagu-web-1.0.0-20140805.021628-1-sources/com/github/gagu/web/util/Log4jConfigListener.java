/*
 * Copyright 2014-2024 hanyosh
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

package com.github.gagu.web.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Bootstrap listener for custom log4j initialization in a web environment.
 * Delegates to {@link Log4jWebConfigurer} (see its javadoc for configuration details).
 *
 * <b>WARNING: Assumes an expanded WAR file</b>, both for loading the configuration
 * file and for writing the log files. If you want to keep your WAR unexpanded or
 * don't need application-specific log files within the WAR directory, don't use
 * log4j setup within the application (thus, don't use Log4jConfigListener or
 * Log4jConfigServlet). Instead, use a global, VM-wide log4j setup (for example,
 * in JBoss) or JDK 1.4's {@code java.util.logging} (which is global too).
 *
 * <p>This listener should be registered before ContextLoaderListener in {@code web.xml}
 * when using custom log4j initialization.
 *
 * Author: yong.han
 * Date: 14-7-24
 * Time: 下午3:53
 *
 * @author hanyosh
 */
public class Log4jConfigListener implements ServletContextListener {
	static final Log logger = LogFactory.getLog(Log4jConfigListener.class);
	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("Initial log4j configuration from web.xml with param \"log4jConfigLocation\" ");
		Log4jWebConfigurer.initLogging(event.getServletContext());
		logger.info("Log4j initialization complete.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		Log4jWebConfigurer.shutdownLogging(event.getServletContext());
	}

}
