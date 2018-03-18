package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PO_RegisterPublicationView extends PO_NavView{


	static public void fillFormAddPublication(WebDriver driver, String titulop, String textop) {
		WebElement titulo = driver.findElement(By.id("titulo"));
		titulo.click();
		titulo.clear();
		titulo.sendKeys(titulop);
		WebElement texto = driver.findElement(By.id("texto"));
		texto.click();
		texto.clear();
		texto.sendKeys(textop);
		
		// Pulsar el boton de Alta.
		By boton = By.id("btnEnviarPublicacion");
		driver.findElement(boton).click();
	}

}
