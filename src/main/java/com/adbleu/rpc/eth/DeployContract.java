package com.adbleu.rpc.eth;

import org.apache.log4j.Logger;

import com.adbleu.jsonrpc.JsonCall;
import com.adbleu.rpc.personal.UnlockAccount;
import com.adbleu.util.HttpClient;
import com.adbleu.util.SystemUtil;
import com.jayway.jsonpath.JsonPath;

/**
 * 
 * https://github.com/paritytech/parity/wiki/JSONRPC-eth-module#eth_sendtransaction
 * Creates a new contract if the data field contains code.
 * 
 */
public class DeployContract {

	private static final Logger LOG = Logger.getLogger(DeployContract.class);

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
	public String execute(final String fromAddress, final String password, int durationSecs, String gas,
			String gasPrice, final String filePath, long sleepSecs) {

		try {
			// unlock the acc for duration in seconds
			String response = new UnlockAccount().execute(fromAddress, password, durationSecs);

			boolean unlocked = JsonPath.read(response, "$.result");

			if (!unlocked) {
				return "Could not unlock account";
			}

			String file = "0x" + SystemUtil.getFile(filePath);

			LOG.info(file);

			response = HttpClient.execute(new JsonCall().setId(String.valueOf(System.nanoTime()))
					.setMethod("parity_postTransaction").addObjectParam("from", fromAddress).addObjectParam("gas", gas)
					.addObjectParam("gasPrice", gasPrice).addObjectParam("data", file));

			String transactionReceipt = JsonPath.read(response, "$.result");

			Thread.sleep(sleepSecs * 1000);

			response = HttpClient.execute(new JsonCall().setId(String.valueOf(System.nanoTime()))
					.setMethod("eth_getTransactionReceipt").addStringParam(transactionReceipt));

			return JsonPath.read(response, "$.result['contractAddress']");

		} catch (Exception e) {
			LOG.error("", e);
			return e.getMessage();
		}

	}

}
