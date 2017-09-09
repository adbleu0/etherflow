package com.adbleu.util;

import org.junit.Assert;
import org.junit.Test;

public class HexUtilTest {

	@Test
	public void testToWei() {
		Assert.assertEquals("1000000000000000000", HexUtil.toWei("1"));
	}

	@Test
	public void testToWeiHex() {
		Assert.assertEquals("0xde0b6b3a7640000", HexUtil.toWeiHex("1"));
	}

}
