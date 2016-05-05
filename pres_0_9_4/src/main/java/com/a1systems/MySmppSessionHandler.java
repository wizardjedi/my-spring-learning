package com.a1systems;

import com.cloudhopper.smpp.PduAsyncResponse;
import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import com.cloudhopper.smpp.pdu.DeliverSm;
import com.cloudhopper.smpp.pdu.PduRequest;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.cloudhopper.smpp.pdu.SubmitSmResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySmppSessionHandler extends DefaultSmppSessionHandler {
	public static Logger log = LoggerFactory.getLogger(MySmppSessionHandler.class);

	@Override
	public PduResponse firePduRequestReceived(PduRequest pduRequest) {
		if (
			pduRequest.isRequest()
			&& pduRequest.getClass() == DeliverSm.class
		) {
			log.debug("Got DELIVER_SM");

			DeliverSm dlr = (DeliverSm)pduRequest;

			log.debug("Msg id={}", dlr.getOptionalParameter(SmppConstants.TAG_RECEIPTED_MSG_ID));
			log.debug("Status={}", dlr.getOptionalParameter(SmppConstants.TAG_MSG_STATE));

			return pduRequest.createResponse();
		}

		return super.firePduRequestReceived(pduRequest);
	}

	@Override
	public void fireExpectedPduResponseReceived(PduAsyncResponse pduAsyncResponse) {
		if (pduAsyncResponse.getResponse().getClass() == SubmitSmResp.class) {
			SubmitSmResp ssmr = (SubmitSmResp)pduAsyncResponse.getResponse();

			log.debug("Got response with MSG ID={} for seqnum={}", ssmr.getMessageId(), ssmr.getSequenceNumber());
		}
	}





}
