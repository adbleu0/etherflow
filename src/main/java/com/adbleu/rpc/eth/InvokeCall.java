package com.adbleu.rpc.eth;

import org.apache.log4j.Logger;

import com.adbleu.jsonrpc.JsonCall;
import com.adbleu.util.HttpClient;
import com.jayway.jsonpath.JsonPath;

/**
 * 
 * https://github.com/paritytech/parity/wiki/JSONRPC-eth-module#eth_call
 *
 * https://github.com/ethereum/wiki/wiki/Ethereum-Contract-ABI
 * 
 * curl --data
 * '{"method":"eth_call","params":[{"from":"0x407d73d8a49eeb85d32cf465507dd71d507100c1","to":"0xa94f5374fce5edbc8e2a8697c15331677e6ebf0b","value":"0x186a0"}],"id":1,"jsonrpc":"2.0"}'
 * -H "Content-Type: application/json" -X POST localhost:8545
 * 
 * { "id": 1, "jsonrpc": "2.0", "result": "0x" }
 */
public class InvokeCall {

	private static final Logger LOG = Logger.getLogger(InvokeCall.class);

	/**
	 * 
	 * @param fromAddress
	 * @param toAddress
	 * @param file
	 * @param gas
	 * @param gasValue
	 * @return
	 */
	public String execute(final String fromAddress, final String toAddress, final String encodedData) {

		try {

			String response = HttpClient.execute(new JsonCall().setId(String.valueOf(System.nanoTime()))
					.setMethod("eth_call").addObjectParam("from", fromAddress).addObjectParam("to", toAddress)
					.addObjectParam("data", encodedData));

			LOG.info(response);

			String result = JsonPath.read(response, "$.result");

			return result;

		} catch (Exception e) {
			LOG.error("", e);
			return e.getMessage();
		}

	}

}
