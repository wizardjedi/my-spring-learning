package com.a1systems.base_jetty;

import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.NetworkTrafficSelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server();

		NetworkTrafficSelectChannelConnector connector = new NetworkTrafficSelectChannelConnector(server);
		connector.setPort(9090);
		connector.setReuseAddress(true);

		server.setConnectors(new Connector[]{connector});

		// setup web app
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");

		webAppContext
				.setResourceBase(
					Thread
						.currentThread()
						.getContextClassLoader()
						.getResource("webapp")
						.toExternalForm()
				);

		ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
		contextHandlerCollection.addHandler(webAppContext);

		HandlerCollection handler = new HandlerCollection();

		handler.addHandler(contextHandlerCollection);

		server.setHandler(handler);

		// Run jetty
		server.start();
		server.join();
    }
}
