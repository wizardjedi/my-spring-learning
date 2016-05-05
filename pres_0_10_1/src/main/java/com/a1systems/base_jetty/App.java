package com.a1systems.base_jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.NetworkTrafficSelectChannelConnector;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server();

		NetworkTrafficSelectChannelConnector connector = new NetworkTrafficSelectChannelConnector(server);
		connector.setPort(9090);
		connector.setReuseAddress(true);

		server.setConnectors(new Connector[]{connector});

		server.start();
		server.join();
    }
}
