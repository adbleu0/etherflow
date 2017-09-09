package com.adbleu.rpc.eth;

import org.apache.log4j.Logger;

import com.adbleu.jsonrpc.JsonCall;
import com.adbleu.rpc.personal.UnlockAccount;
import com.adbleu.util.HexUtil;
import com.adbleu.util.HttpClient;
import com.jayway.jsonpath.JsonPath;

public class SendEther {

	private static final Logger LOG = Logger.getLogger(SendEther.class);

	/**
	 * 
	 * @param fromAddress
	 * @param password
	 * @param durationSecs
	 * @param file
	 * @param gas
	 * @param gasValue
	 * @return
	 */
	public String execute(final String fromAddress, final String password, final int durationSecs,
			final String toAddress, final String gas, final String gasPrice, final String ether) {

		try {
			// unlock the acc for duration in seconds
			String response = new UnlockAccount().execute(fromAddress, password, durationSecs);

			boolean unlocked = JsonPath.read(response, "$.result");

			if (!unlocked) {
				return "Could not unlock account";
			}

			String wei = HexUtil.toWeiHex(ether);

			LOG.info(wei);

			response = HttpClient.execute(new JsonCall().setId(String.valueOf(System.nanoTime()))
					.setMethod("eth_sendTransaction").addObjectParam("from", fromAddress)
					.addObjectParam("to", toAddress).addObjectParam("gas", gas).addObjectParam("gasPrice", gasPrice)
					.addObjectParam("value", wei));

			LOG.info(response);

			String transactionReceipt = JsonPath.read(response, "$.result");

			return transactionReceipt;

		} catch (Exception e) {
			LOG.error("", e);
			return e.getMessage();
		}

	}

}
