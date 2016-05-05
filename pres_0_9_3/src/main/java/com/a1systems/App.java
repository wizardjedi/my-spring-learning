package com.a1systems;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.type.Address;
import com.cloudhopper.smpp.type.LoggingOptions;
import com.cloudhopper.smpp.type.RecoverablePduException;
import com.cloudhopper.smpp.type.SmppBindException;
import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppInvalidArgumentException;
import com.cloudhopper.smpp.type.SmppTimeoutException;
import com.cloudhopper.smpp.type.UnrecoverablePduException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	public static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		DefaultSmppClient client = new DefaultSmppClient();

		SmppSessionConfiguration sessionConfig = new SmppSessionConfiguration();

		sessionConfig.setType(SmppBindType.TRANSCEIVER);
		sessionConfig.setHost("127.0.0.1");
		sessionConfig.setPort(2775);
		sessionConfig.setSystemId("smppclient1");
		sessionConfig.setPassword("password");

		LoggingOptions loggingOptions = new LoggingOptions();

		loggingOptions.setLogPdu(false);
		loggingOptions.setLogBytes(false);

		sessionConfig.setLoggingOptions(loggingOptions);

		try {
			SmppSession session = client.bind(sessionConfig, new MySmppSessionHandler());

			SubmitSm sm = createSubmitSm("Test", "79111234567", "Привет землянин!", "UCS-2");

			log.debug("Try to send message");

			session.submit(sm, TimeUnit.SECONDS.toMillis(60));

			log.debug("Message sent");

			log.debug("Wait 10 seconds");

			TimeUnit.SECONDS.sleep(10);

			log.debug("Destroy session");

			session.close();
			session.destroy();

			log.debug("Destroy client");

			client.destroy();

			log.debug("Bye!");
		} catch (SmppTimeoutException ex) {
			log.error("{}", ex);
		} catch (SmppChannelException ex) {
			log.error("{}", ex);
		} catch (SmppBindException ex) {
			log.error("{}", ex);
		} catch (UnrecoverablePduException ex) {
			log.error("{}", ex);
		} catch (InterruptedException ex) {
			log.error("{}", ex);
		} catch (RecoverablePduException ex) {
			log.error("{}", ex);
		}
	}

	public static SubmitSm createSubmitSm(String src, String dst, String text, String charset) throws SmppInvalidArgumentException {
		SubmitSm sm = new SubmitSm();

		// For alpha numeric will use
		// TON=5
		// NPI=0
		sm.setSourceAddress(new Address((byte)5, (byte)0, src));

		// For national numbers will use
		// TON=1
		// NPI=1
		sm.setDestAddress(new Address((byte)1, (byte)1, dst));

		// Set datacoding to UCS-2
		sm.setDataCoding((byte)8);

		// Encode text
		sm.setShortMessage(CharsetUtil.encode(text, charset));

		//We would like to get delivery receipt
		sm.setRegisteredDelivery((byte)1);

		return sm;
	}
}
