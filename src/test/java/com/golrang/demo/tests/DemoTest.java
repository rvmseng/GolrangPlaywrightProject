package com.golrang.demo.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class DemoTest {
	
	@Test
	public void test1() {
		throw new SkipException("not implemented");
	}

}