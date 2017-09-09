package com.adbleu.jshell;

import com.adbleu.rpc.personal.ListAccounts;
import com.adbleu.rpc.personal.NewAccount;

public class Parity {

	public static void newAccount(String password) {
		System.out.println(new NewAccount().execute(password));
	}

	public static void listAccounts() {
		System.out.println(new ListAccounts().execute());
	}

}
