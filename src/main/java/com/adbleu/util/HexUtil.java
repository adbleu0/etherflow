package com.adbleu.util;

import java.math.BigInteger;

public class HexUtil {

	private HexUtil() {
	}

	public static String intToHex(int decimalValue) {
		BigInteger value = new BigInteger(String.valueOf(decimalValue));
		if (value.signum() < 0) {
			throw new UnsupportedOperationException("Value cannot be negative");
		}
		return "0x" + value.toString(16);
	}

	public static String intFromHex(String hexValue) {
		BigInteger value = new BigInteger(hexValue.replace("0x",""), 16);
		if (value.signum() < 0) {
			throw new UnsupportedOperationException("Value cannot be negative");
		}
		return value.toString();
	}
	
	
	public static String toWei(String ether) {
		BigInteger value = new BigInteger(ether, 16);
		if (value.signum() < 0) {
			throw new UnsupportedOperationException("Value cannot be negative");
		}
		return value.multiply(new BigInteger("1000000000000000000")).toString();
	}
	
	public static String toWeiHex(String ether) {
		BigInteger value = new BigInteger(ether, 16);
		if (value.signum() < 0) {
			throw new UnsupportedOperationException("Value cannot be negative");
		}
		return "0x"+value.multiply(new BigInteger("1000000000000000000")).toString(16);
	}
	
}
