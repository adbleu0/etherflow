package com.adbleu.jshell;

import com.adbleu.rpc.eth.GetBalance;
import com.adbleu.rpc.personal.ListAccounts;
import com.adbleu.rpc.personal.NewAccount;
import com.adbleu.rpc.personal.UnlockAccount;

public class Parity {

	public static void newAccount(String password) {
		System.out.println(new NewAccount().execute(password));
	}

	public static void listAccounts() {
		System.out.println(new ListAccounts().execute());
	}

	public static void unlockAccount(final String address, final String password, int durationInSecs) {
		System.out.println(new UnlockAccount().execute(address, password, durationInSecs));
	}
	
	public static void getBalance(final String address) {
		System.out.println(new GetBalance().execute(address));
	}
	
	public static void getAllBalances() {
		System.out.println(new GetBalance().execute());
	}

}
