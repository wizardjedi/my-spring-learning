package com.a1systems.client;

import com.cloudhopper.smpp.SmppClient;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.SmppSessionHandler;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client implements Runnable {
	public static final Logger log = LoggerFactory.getLogger(Client.class);

	protected SmppSessionConfiguration cfg;
	protected SmppSessionHandler sessionHandler;
	protected ClientState state;

	protected volatile SmppSession session;

	protected SmppClient smppClient;

	protected Timer timer;

	protected ElinkTask elinkTask;
	protected RebindTask rebindTask;

	protected long rebindPeriod = TimeUnit.SECONDS.toMillis(5);
	protected long elinkPeriod = TimeUnit.SECONDS.toMillis(5);

	public Client(SmppSessionConfiguration cfg) {
		this.cfg = cfg;

		this.elinkTask = new ElinkTask(this);
		this.rebindTask = new RebindTask(this);
	}

	@Override
	public void run() {
		log.debug("Creating client");

		this.state = ClientState.IDLE;

		while (this.state != ClientState.STOPPING) {
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException ex) {
				/* */
			}
		}

		this.smppClient.destroy();

		this.state = ClientState.STOPPED;
	}

	public void start() {
		log.debug("Starting client");

		this.timer = new Timer();

		this.smppClient = new DefaultSmppClient();

		this.bind();
	}

	private void runRebindTask() {
		rebindTask = new RebindTask(this);

		this.timer.scheduleAtFixedRate(rebindTask, 0, getRebindPeriod());
	}

	private void runElinkTask() {
		elinkTask = new ElinkTask(this);

		this.timer.scheduleAtFixedRate(elinkTask, getElinkPeriod(), getElinkPeriod());
	}


	public void bind() {
		if (
			this.state == ClientState.BOUND
			|| this.state == ClientState.IDLE
		) {
			log.debug("Binding state");

			if (
				this.session != null
				&& this.session.isBound()
			) {
				this.session.close();
				this.session.destroy();
			}

			this.state = ClientState.BINDING;

			this.elinkTask.cancel();
			runRebindTask();
		}
	}

	public void bound(SmppSession session) {
		if (this.state == ClientState.BINDING) {
			log.debug("Bound state");

			this.state = ClientState.BOUND;

			this.session = session;

			this.rebindTask.cancel();
			runElinkTask();
		}
	}

	public void stop() {
		log.debug("Stopping");

		this.state = ClientState.STOPPING;

		this.elinkTask.cancel();
		this.rebindTask.cancel();
		this.timer.cancel();
		this.timer.purge();

		this.timer = null;
	}

	// getters and setters
	public long getRebindPeriod() {
		return rebindPeriod;
	}

	public void setRebindPeriod(long rebindPeriod) {
		this.rebindPeriod = rebindPeriod;
	}

	public long getElinkPeriod() {
		return elinkPeriod;
	}

	public void setElinkPeriod(long elinkPeriod) {
		this.elinkPeriod = elinkPeriod;
	}

	public SmppClient getSmppClient() {
		return smppClient;
	}

	public void setSmppClient(SmppClient smppClient) {
		this.smppClient = smppClient;
	}

	public SmppSessionConfiguration getCfg() {
		return cfg;
	}

	public void setCfg(SmppSessionConfiguration cfg) {
		this.cfg = cfg;
	}

	public SmppSessionHandler getSessionHandler() {
		return sessionHandler;
	}

	public void setSessionHandler(SmppSessionHandler sessionHandler) {
		this.sessionHandler = sessionHandler;
	}

	public SmppSession getSession() {
		return session;
	}

	public void setSession(SmppSession session) {
		this.session = session;
	}


}
