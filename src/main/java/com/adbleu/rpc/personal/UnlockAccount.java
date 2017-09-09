package com.adbleu.rpc.personal;

import java.io.IOException;

import com.adbleu.jsonrpc.JsonCall;
import com.adbleu.util.HexUtil;
import com.adbleu.util.HttpClient;

/**
 * 
 * If permanent unlocking is disabled (the default) then the duration argument
 * will be ignored, and the account will be unlocked for a single signing. With
 * permanent locking enabled, the duration sets the number of seconds to hold
 * the account open for. It will default to 300 seconds. Passing 0 unlocks the
 * account indefinitely. There can only be one unlocked account at a time.
 * 
 * 
 * https://github.com/paritytech/parity/wiki/JSONRPC-personal-module#personal_unlockaccount
 * 
 * <code>
 * 
 * curl --data '{"method":"personal_unlockAccount","params":["0x8f0227d45853a50eefd48dd4fec25d5b3fd2295e","hunter2",null],"id":1,"jsonrpc":"2.0"}' -H "Content-Type: application/json" -X POST localhost:8545
 * 
 * {
 *   "id": 1,
 *   "jsonrpc": "2.0",
 *   "result": true
 * }
 * </code>
 * 
 */
public class UnlockAccount {

	/**
	 * 
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public String execute(final String address, final String password, int durationInSecs) {
		try {
			return HttpClient.execute(new JsonCall().setId(String.valueOf(System.nanoTime()))
					.setMethod("personal_unlockAccount").addStringParam(address).addStringParam(password)
					.addStringParam(HexUtil.intToHex(durationInSecs)));
		} catch (Exception e) {
			return e.getMessage();
		}

	}
}
