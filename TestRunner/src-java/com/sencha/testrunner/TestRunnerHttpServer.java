package com.sencha.testrunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

public class TestRunnerHttpServer {

	private static String RESOURCES_DIR ="/resources";
	private static String SDK_DIR = "/Users/marcelofarias/git/SDK";
	
	private Server server;
	
	@SuppressWarnings("serial")
	public void start(Collection<ContextDescriptor> additionalContexts) {
		server = new Server(1903);

		try {
			final WebAppContext testRunnerContext = new WebAppContext();
			testRunnerContext.setContextPath("/");
			testRunnerContext.setBaseResource(Resource.newClassPathResource(RESOURCES_DIR));

			final WebAppContext sdkContext = new WebAppContext();
			sdkContext.setContextPath("/SDK");
			sdkContext.setBaseResource(Resource.newResource(SDK_DIR));
			
			List<Handler> handlers = new ArrayList<Handler>() {{
				add(testRunnerContext);
				add(sdkContext);
			}};
			
			for (ContextDescriptor contextDescriptor : additionalContexts) {
				WebAppContext webAppContext = new WebAppContext();
				webAppContext.setContextPath(contextDescriptor.getRelativeUrl());
				webAppContext.setBaseResource(Resource.newResource(contextDescriptor.getAbsolutePath()));
				handlers.add(webAppContext);
			}
			
			ContextHandlerCollection contexts = new ContextHandlerCollection();
			contexts.setHandlers(handlers.toArray(new Handler[handlers.size()]));
			server.setHandler(contexts);
			server.start();
		} catch (Exception e) {
			throw new TestRunnerException("Error starting test server", e);
		}
	}
	
	public void stop() {
		if (server != null) {
			try {
				server.stop();
			} catch (Exception e) {
				throw new TestRunnerException("Error stopping test server", e);
			}
		}
	}
	
	public static void main(String[] arguments) {
		TestRunnerHttpServer server = new TestRunnerHttpServer();
		server.start(Collections.singleton(new ContextDescriptor("/tmp", "/tmp")));
	}

}
