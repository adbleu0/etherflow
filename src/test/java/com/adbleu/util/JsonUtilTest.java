package com.adbleu.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class JsonUtilTest {

	@Test
	public void testParse() throws IOException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("result.json");
		Assert.assertEquals("0x0", JsonUtil.parse(is, "$.result"));
	}

	@Test
	public void testParseArray() throws IOException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("result1.json");

		assertThat(JsonUtil.parseArray(is, "$.result"), is(Arrays.asList("0x008ae303513c1ed9cb24bcf243b806c828e94b60",
				"0x009ed82813cacf2577410213802371e7abed7c7d", "0x00a329c0648769a73afac7f9381e08fb43dbea72")));
	}
}
