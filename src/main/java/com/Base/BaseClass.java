package com.Base;

import com.microsoft.playwright.*;
import com.utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseClass {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext browserContext;
    public static Page page;

    public static final Logger logger = LoggerFactory.getLogger(BaseClass.class);

    public static void setUp() {
        logger.info(">>>>> Setting up Playwright");
        String browserType = ConfigReader.getProperty("browser");
        playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false);

        switch (browserType.toLowerCase()) {
            case "edge":
                browser = playwright.webkit().launch(options);
                break;
            case "chrome":
                browser = playwright.chromium().launch(options);
                break;
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;
            default:
                logger.error("Invalid browser specification in config file {}", browserType);
                throw new IllegalArgumentException("Invalid browser specification in config file " + browserType);
        }

        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(1280, 720));
        page = browserContext.newPage();
        page.navigate(ConfigReader.getProperty("url"));
    }

    public static void closeBrowser() {
        if (page != null) {
            page.close();
        }
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
