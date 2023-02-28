package com.golrang.demo.tests;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.options.BrowserChannel;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class DemoTest {
	
	@Test
	public void test1() {
		LaunchOptions lo=new BrowserType.LaunchOptions();
		lo.setHeadless(false);
		
		lo.setChannel("chrome");
		Browser browser=Playwright.create().chromium().launch(lo);
		
		BrowserContext context = browser.newContext();
		Page page=context.newPage();
		
		page.navigate("https://www.google.com");
		System.out.println(page.title());
	}
	
	@Test
	public void test2_with_tracing() {
		LaunchOptions lo=new BrowserType.LaunchOptions();
		lo.setHeadless(false);
		
		lo.setChannel("chrome");
		Browser browser=Playwright.create().chromium().launch(lo);
		
		BrowserContext context = browser.newContext();
		
		// Start tracing before creating / navigating a page.
		context.tracing().start(new Tracing.StartOptions()
											.setScreenshots(true)
											.setSnapshots(true)
											.setSources(true));
		
		Page page=context.newPage();
		page.navigate("https://playwright.dev");

		System.out.println(page.title());
		
		// Stop tracing and export it into a zip archive.
		context.tracing().stop(new Tracing.StopOptions()
											.setPath(Paths.get("trace.zip")));
	}
}
