package com.curso.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

//Para capturar enventos en Selenium
public class OyenteSelenium implements WebDriverListener {
	
	private WebDriver webDriver;
	
	public OyenteSelenium(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}

	@Override
	public void beforeFindElement(WebDriver driver, By locator) {
	}

	@Override
	public void afterFindElement(WebDriver driver, By locator, WebElement result) {
	}

	@Override
	public void beforeGet(WebDriver driver, String url) {
	}
	
	@Override
	public void afterGet(WebDriver driver, String url) {
	}

	@Override
	public void beforeClick( WebElement element) {
	}
	
	@Override
	public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
	}

	@Override
	public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
		System.out.println("ZASCA");
		File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("src/test/resources/screenshots/onError.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}	
	}
	
}


