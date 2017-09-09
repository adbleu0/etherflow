package com.adbleu.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	public static String getFile(String fileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get(fileName)));
	}
}
