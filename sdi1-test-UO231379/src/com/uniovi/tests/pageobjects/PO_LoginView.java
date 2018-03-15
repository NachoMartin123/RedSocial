package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView{


	static public void fillForm(WebDriver driver, String usernamep, String passwordp) {
		WebElement username = driver.findElement(By.id("email"));
		username.click();
		username.clear();
		username.sendKeys(usernamep);
		WebElement password = driver.findElement(By.id("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
	
		// Pulsar el boton de Alta.
		By boton = By.id("btnLogin");
		driver.findElement(boton).click();
	}
}
