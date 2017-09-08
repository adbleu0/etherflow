package com.adbleu.jsonrpc.personal;

import java.io.IOException;

import com.adbleu.jsonrpc.JsonCall;
import com.adbleu.util.HttpClient;

/**
 * 
 * Create a new account on a node and return the address
 * 
 * https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_newaccount
 * 
 * <code>
 * curl --data
 * '{"method":"personal_newAccount","params":["hunter2"],"id":1,"jsonrpc":"2.0"}'
 * -H "Content-Type: application/json" -X POST localhost:8545
 * 
 * {
 *   "id": 1,
 *   "jsonrpc": "2.0",
 *   "result": "0x8f0227d45853a50eefd48dd4fec25d5b3fd2295e"
 * }
 * </code>
 * 
 */
public class NewAccount {

	/**
	 * 
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public String execute(final String password) throws IOException {
		return HttpClient.execute(new JsonCall().setId(String.valueOf(System.nanoTime()))
				.setMethod("personal_newAccount").addStringParam(password).addIntegerParam(100)).getResult();

	}
}
