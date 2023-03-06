package com.golrang.demo.tests;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class DemoTest {
	
	@Test
	public void open_google_website_test() {
		LaunchOptions lo=new BrowserType.LaunchOptions();
		lo.setHeadless(false);
		
		lo.setChannel("chrome");
		Browser browser=Playwright.create().chromium().launch(lo);
		
		BrowserContext context = browser.newContext();
		Page page=context.newPage();
		
		page.navigate("https://www.google.com");
		System.out.println(page.title());
		
		browser.close();
	}
	
	@Test
	public void open_playwright_website_with_tracing_test() {
		LaunchOptions lo=new BrowserType.LaunchOptions();
		lo.setHeadless(false);
		
		lo.setChannel("chrome");
		Browser browser=Playwright.create().chromium().launch(lo);
		
		BrowserContext context = browser.newContext();
		
		// Start tracing before creating / navigating a page.
		context.tracing().start(new Tracing.StartOptions()
											.setScreenshots(true)
											.setSnapshots(true));
		
		Page page=context.newPage();
		page.navigate("https://playwright.dev");

		System.out.println(page.title());
		
		// Stop tracing and export it into a zip archive.
		context.tracing().stop(new Tracing.StopOptions()
											.setPath(Paths.get("trace.zip")));
		browser.close();
	}
	
	@Test
	public void test3() {
		LaunchOptions lo=new BrowserType.LaunchOptions();
		lo.setHeadless(false);
		lo.setChannel("msedge");
		
		Browser browser=Playwright.create().chromium().launch(lo);
		Page page1=browser.newPage();
		
		page1.navigate("https://www.google.com");
		System.out.println("title is > "+page1.title());
		
	}
}
