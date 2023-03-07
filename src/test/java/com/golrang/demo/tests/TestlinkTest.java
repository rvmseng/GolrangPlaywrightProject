package com.golrang.demo.tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class TestlinkTest {

	@Test
	public void verify_new_user_registration_test() {
		Playwright playwright = Playwright.create();
		LaunchOptions lo = new BrowserType.LaunchOptions();

		lo.setHeadless(false);
		// lo.setChannel("msedge");
		lo.setSlowMo(500);

		Browser browser = playwright.chromium().launch(lo);

		BrowserContext context = browser.newContext();
		Page page = context.newPage();

		page.navigate("http://91.92.207.95:4651/testlink/firstLogin.php?viewer=new");
		page.getByPlaceholder("Login Name").click();
		page.getByPlaceholder("Login Name").fill("rvmseng");
		page.getByPlaceholder("Password", new Page.GetByPlaceholderOptions().setExact(true)).click();
		page.getByPlaceholder("Repeat password").click();
		page.getByPlaceholder("Repeat password").fill("Aa123456");
		page.getByPlaceholder("First name").click();
		page.getByPlaceholder("First name").fill("Reza");
		page.getByPlaceholder("Last name").click();
		page.getByPlaceholder("Last name").fill("Valizadeh");
		page.getByPlaceholder("E-mail address").click();
		page.getByPlaceholder("E-mail address").fill("Rvm.seng@gmail.com");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign up")).click();
		page.getByPlaceholder("Password", new Page.GetByPlaceholderOptions().setExact(true)).click();
		page.getByPlaceholder("Password", new Page.GetByPlaceholderOptions().setExact(true)).fill("Aa123456");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign up")).click();

		page.close();
		context.close();

		browser.close();
		playwright.close();
	}
}
