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
import com.microsoft.playwright.options.AriaRole;

public class DemoTest {

	@Test
	public void open_google_website_test() {
		LaunchOptions lo = new BrowserType.LaunchOptions();
		lo.setHeadless(false);

		lo.setChannel("chrome");
		Playwright pl=Playwright.create();
		
		Browser browser = pl.chromium().launch(lo);

		BrowserContext context = browser.newContext();
		Page page = context.newPage();

		page.navigate("https://www.google.com");
		System.out.println(page.title());

		page.close();
		context.close();
		browser.close();
		pl.close();
	}

	public void open_playwright_website_with_tracing_test() {
		LaunchOptions lo = new BrowserType.LaunchOptions();
		lo.setHeadless(false);

		lo.setChannel("chrome");
		Browser browser = Playwright.create().chromium().launch(lo);

		BrowserContext context = browser.newContext();

		// Start tracing before creating / navigating a page.
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

		Page page = context.newPage();
		page.navigate("https://playwright.dev");

		System.out.println(page.title());

		// Stop tracing and export it into a zip archive.
		context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
		browser.close();
	}

	public void test3() {
		LaunchOptions lo = new BrowserType.LaunchOptions();
		lo.setHeadless(false);
		lo.setChannel("msedge");

		Browser browser = Playwright.create().chromium().launch(lo);
		Page page1 = browser.newPage();

		page1.navigate("https://www.google.com");
		System.out.println("title is > " + page1.title());

	}

	@Test
	public void test4(){

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
				new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
		BrowserContext context = browser.newContext();
		
		//start
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
		
		Page page = context.newPage();
		page.navigate("http://91.92.207.95:4651/testlink/firstLogin.php?viewer=new");
		page.getByPlaceholder("Login Name").fill("rvmseng2");
		page.getByPlaceholder("Password", new Page.GetByPlaceholderOptions().setExact(true)).click();
		page.getByPlaceholder("Password", new Page.GetByPlaceholderOptions().setExact(true)).fill("Aa123456");
		page.getByPlaceholder("Repeat password").fill("Aa123456");
		page.getByPlaceholder("First name").fill("Reza");
		page.getByPlaceholder("Last name").fill("Valizadeh");
		page.getByPlaceholder("E-mail address").fill("None@gmail.com");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign up")).click();
		
		//stop
		context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
		
		context.close();
		browser.close();
		playwright.close();
	}
}
