package com.river.videriTest.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class FileWriterTest {
	
	@Test
	public void fileTest() throws Exception {
		File input = new File(Thread.currentThread().getContextClassLoader().getResource("./sample.txt").toURI());
		assertTrue(input.exists());
		String[] result = FileUtils.readFileToString(input).split("\n");
		assertEquals(3, result.length);
		assertEquals(3, result[0].split(",").length);
		assertEquals("Notre-Dame de Paris", result[1].split(",")[1].trim());
	}

}