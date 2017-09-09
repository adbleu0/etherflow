package com.adbleu.rpc.personal;

import com.adbleu.jsonrpc.JsonCall;
import com.adbleu.util.HttpClient;

/**
 * 
 * Lists all stored accounts.
 * 
 * https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_listaccounts
 * 
 * <code>
 * curl --data '{"method":"personal_listAccounts","params":[],"id":1,"jsonrpc":"2.0"}' -H "Content-Type: application/json" -X POST localhost:8545
 * 
 * {
 * 
 *  "id": 1,
 *  "jsonrpc": "2.0",
 *  "result": [
 *    "0x7bf87721a96849d168de02fd6ea5986a3a147383",
 *    "0xca807a90fd64deed760fb98bf0869b475c469348"
 *  ]
 * }
 * </code>
 *
 */
public class ListAccounts {

	/**
	 * Lists all stored accounts.
	 * 
	 * @return
	 */
	public String execute() {
		try {
			return HttpClient.execute(
					new JsonCall().setId(String.valueOf(System.nanoTime())).setMethod("personal_listAccounts"));
		} catch (Exception e) {
			return e.getMessage();
		}

	}

}
