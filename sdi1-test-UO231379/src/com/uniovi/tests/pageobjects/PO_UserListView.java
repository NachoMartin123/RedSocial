package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_UserListView extends PO_NavView{
	
	static public void clickButton(WebDriver driver, String idBoton) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", idBoton, getTimeout());
		elementos.get(0).click(); // Esperamos a que aparezca el menú de opciones.
		
	}

	static public String getButtonText(WebDriver driver, String idBoton) {
		WebElement boton = driver.findElement(By.id(idBoton));
		return boton.getText();
	}
}
