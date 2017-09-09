package com.adbleu.rpc.eth;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.adbleu.jsonrpc.JsonCall;
import com.adbleu.rpc.personal.ListAccounts;
import com.adbleu.util.HexUtil;
import com.adbleu.util.HttpClient;
import com.adbleu.util.JsonUtil;

/**
 * 
 * Returns the balance of the account of given address.
 * 
 * https://github.com/paritytech/parity/wiki/JSONRPC-eth-module#eth_getbalance
 * 
 * curl --data
 * '{"method":"eth_getBalance","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1"],"id":1,"jsonrpc":"2.0"}'
 * -H "Content-Type: application/json" -X POST localhost:8545
 *
 * { "id": 1, "jsonrpc": "2.0", "result": "0x0234c8a3397aab58" }
 */
public class GetBalance {

	/**
	 * 
	 * @param address
	 * @return
	 */
	public String execute(final String address) {
		try {
			return HttpClient.execute(new JsonCall().setId(String.valueOf(System.nanoTime()))
					.setMethod("eth_getBalance").addStringParam(address));
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String execute() {
		try {
			String result = new ListAccounts().execute();
			List<String> accounts = JsonUtil.parseArray(new ByteArrayInputStream(result.getBytes()), "$.result");
			StringBuilder sb = new StringBuilder();
			accounts.forEach(acc -> {
				String response = execute(acc);
				try {
					String balance = JsonUtil.parse(new ByteArrayInputStream(response.getBytes()), "$.result"); 
					sb.append(String.join(":", acc, balance));
					sb.append("\n");
					sb.append(String.join(":", acc, HexUtil.intFromHex(balance)));
					sb.append("\n");
					sb.append("\n");
				} catch (IOException e) {
					sb.append(e.getMessage());
				}
			});
			return sb.toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
