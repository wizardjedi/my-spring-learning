package com.a1systems;

import com.a1systems.client.Client;
import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.commons.util.windowing.WindowFuture;
import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.pdu.EnquireLink;
import com.cloudhopper.smpp.pdu.PduRequest;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.pdu.SubmitSmResp;
import com.cloudhopper.smpp.type.Address;
import com.cloudhopper.smpp.type.LoggingOptions;
import com.cloudhopper.smpp.type.RecoverablePduException;
import com.cloudhopper.smpp.type.SmppBindException;
import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppInvalidArgumentException;
import com.cloudhopper.smpp.type.SmppTimeoutException;
import com.cloudhopper.smpp.type.UnrecoverablePduException;
import com.cloudhopper.smpp.util.PduUtil;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	public static Logger log = LoggerFactory.getLogger(App.class);

	private static void log(WindowFuture<Integer, PduRequest, PduResponse> future) {
		SubmitSm req = (SubmitSm)future.getRequest();
		SubmitSmResp resp = (SubmitSmResp)future.getResponse();

		log.debug("Got response with MSG ID={} for APPID={}", resp.getMessageId(), req.getReferenceObject());
	}

	public static void main(String[] args) throws SmppInvalidArgumentException {
		ApplicationContext ctx = new GenericXmlApplicationContext("spring.xml");

		SmppSessionConfiguration sessionConfig = new SmppSessionConfiguration();

		sessionConfig.setType(SmppBindType.TRANSCEIVER);
		sessionConfig.setHost("127.0.0.1");
		sessionConfig.setPort(2775);
		sessionConfig.setSystemId("smppclient1");
		sessionConfig.setPassword("password");

		LoggingOptions loggingOptions = new LoggingOptions();

		//loggingOptions.setLogPdu(false);
		//loggingOptions.setLogBytes(false);

		sessionConfig.setLoggingOptions(loggingOptions);

		Client client = new Client(sessionConfig);

		client.setSessionHandler(new MySmppSessionHandler(client));

		ExecutorService pool = Executors.newFixedThreadPool(2);

		pool.submit(client);

		client.start();

		log.debug("Wait to bound");

		while (
			client.getSession() == null
			|| !client.getSession().isBound()
		) {

			if (client.getSession() != null) {
				log.debug("Session is {}", client.getSession().isBound());
			} else {
				log.debug("Null session");
			}

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		log.debug("Try to send");

		SubmitSm sm = new SubmitSm();

		sm.setSourceAddress(new Address((byte)5, (byte)0, "Test"));
		sm.setDestAddress(new Address((byte)1, (byte)1, "79111234567"));

		sm.setShortMessage(CharsetUtil.encode("Привет!", "UCS-2"));

		sm.setRegisteredDelivery((byte)1);

		sm.setDataCoding((byte)8);



		try {
			client.getSession().submit(sm, TimeUnit.SECONDS.toMillis(60));
		} catch (RecoverablePduException ex) {
			java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnrecoverablePduException ex) {
			java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SmppTimeoutException ex) {
			java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SmppChannelException ex) {
			java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InterruptedException ex) {
			java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException ex) {
			java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}

		client.stop();

		pool.shutdown();
	}
}
