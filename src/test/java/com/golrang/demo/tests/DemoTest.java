package com.golrang.demo.tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.options.BrowserChannel;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class DemoTest {
	
	@Test
	public void test1() {
		LaunchOptions lo=new BrowserType.LaunchOptions();
		lo.setHeadless(false);
		
		lo.setChannel("chrome");
		Browser browser=Playwright.create().chromium().launch(lo);
		
		Page page=browser.newPage();
		page.navigate("https://www.google.com");
		
		System.out.println(page.title());
	}
}
