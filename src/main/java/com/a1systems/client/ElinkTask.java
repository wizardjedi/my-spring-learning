package com.a1systems.client;

import static com.a1systems.client.RebindTask.log;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.pdu.EnquireLink;
import com.cloudhopper.smpp.type.RecoverablePduException;
import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppTimeoutException;
import com.cloudhopper.smpp.type.UnrecoverablePduException;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.slf4j.LoggerFactory;

public class ElinkTask extends TimerTask {
	public static final org.slf4j.Logger log = LoggerFactory.getLogger(ElinkTask.class);

	protected Client client;

	public ElinkTask(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		if (client.state == ClientState.BOUND) {
			SmppSession session = client.getSession();

			log.debug("Send elink");

			try {
				session.enquireLink(new EnquireLink(), TimeUnit.SECONDS.toMillis(10));

				log.debug("Elink sent successfull");
			} catch (RecoverablePduException ex) {
				log.debug("{}", ex);
			} catch (UnrecoverablePduException ex) {
				log.debug("{}", ex);
			} catch (SmppTimeoutException ex) {
				client.bind();

				log.debug("{}", ex);
			} catch (SmppChannelException ex) {
				log.debug("{}", ex);
			} catch (InterruptedException ex) {
				log.debug("{}", ex);
			}
		}
	}

}
