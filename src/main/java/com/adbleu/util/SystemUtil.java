package com.adbleu.util;

import java.io.IOException;
import java.net.ServerSocket;

public final class SystemUtil {

	private SystemUtil() {
	}

	public static boolean checkNodeRpcPortOpen() {
		try (ServerSocket ignored = new ServerSocket(8545)) {
			return false;
		} catch (IOException ignored) {
			return true;
		}
	}
}
