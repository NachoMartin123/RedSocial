package org.uniovi.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_RegisterPublicationView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class sdi1 {

	// En Windows (Debe ser la versión 46.0 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox = "C:\\Users\\Blacky\\Desktop\\PEQUE\\CURSO 2017-2018\\SDI\\PRACTICAS\\SESION_5\\Firefox46.0.win\\Firefox46.win\\FirefoxPortable.exe";
	// //En MACOSX (Debe ser la versión 46.0 y desactivar las actualizaciones
	// automáticas):
	// static String PathFirefox =
	// "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox);
	static String URL = "http://localhost:8090/";

	public static WebDriver getDriver(String PathFirefox) { // Firefox (Versión 46.0) sin geckodriver para Selenium 2.x.
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	// PR1.1[RegVal]. Prueba del formulario de registro. Registro con datos
	// correctos
	@Test
	public void RegVal() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "id", "btnRegistro");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "gabriela@gmail.com", "Gabriela", "123456", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "id", "zonaPrivada");
	}

	// PR1.2[RegInVal]. Prueba del formulario de registro. Registro con datos
	// incorrectos.
	// Repetición de contraseña invalida
	@Test
	public void RegInVal() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "id", "btnRegistro");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "gabriela@gmail.com", "Gabriela", "123456", "111111");
		PO_View.getP();
		// COmprobamos el error de contraseña no coincide.
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}

	// PR2.1[InVal] Prueba del formulario de login.Login con datos correctos
	@Test
	public void InVal() {
		PO_HomeView.clickOption(driver, "login", "id", "btnIdentificate");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "id", "zonaPrivada");
	}

	// PR2.2[InInVal] Prueba del formulario de login.Login con datos incorrectos
	// Usuario no existe
	@Test
	public void InInVal() {
		PO_HomeView.clickOption(driver, "login", "id", "btnIdentificate");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "daniel@gmail.com", "123456");
		// Comprobamos que seguimos en la vista de login
		PO_View.checkElement(driver, "id", "vistaLogin");
	}

	// PR3.1[LisUsrVal] Acceso al listado de usuarios desde un usuario en sesion
	@Test
	public void LisUsrVal() {
		PO_HomeView.clickOption(driver, "login", "id", "btnIdentificate");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "id", "zonaPrivada");
		//Pinchamos en la opción de menu de ver usuarios: 
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//*[@id=\"listaUsuarios\"]"); 
		elementos.get(0).click();
		//Contamos el numero de filas con usuarios
		List<WebElement> elemento = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout()); 
		assertTrue(elemento.size() == 5);
		//Ahora nos desconectamos
		PO_RegisterPublicationView.clickOption(driver, "logout", "id", "btnIdentificate");
		
	}
	
	// PR3.1[LisUsrInVal] Acceso al listado de usuarios desde un usuario no autenticado
		@Test
		public void LisUsrInVal() {
		}
	
	// PR4.1[BusUsrVal] Busqueda valida en listado de usuarios desde usuario en sesion
	@Test
	public void BusUsrVal() {
		PO_HomeView.clickOption(driver, "login", "id", "btnIdentificate");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "id", "zonaPrivada");
		//Pinchamos en la opción de menu de ver usuarios: 
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//*[@id=\"listaUsuarios\"]"); 
		elementos.get(0).click();
		//Acceso al campo de busqueda
		elementos= PO_View.checkElement(driver,"free","//*[@id=\"campoBusqueda\"]");
		elementos.get(0).click();
		// Insertamos la cadena "mar"
		elementos.get(0).sendKeys("mar");
		// Pinchamo en buscar
		elementos= PO_View.checkElement(driver,"free","//*[@id=\"btnBuscar\"]");
		elementos.get(0).click();
		//Contamos el numero de filas con usuarios
		List<WebElement> elemento = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elemento.size() == 2);
		//Nos desconectamos
		PO_RegisterPublicationView.clickOption(driver, "logout", "id", "btnIdentificate");
		
	}
	// PR4.2[LisUsrInVal] // NO CREO QUE ESTE BIEN ASI
		@Test
		public void BusUsrInVal() {
		
		driver.navigate().to("http://localhost:8090/user/list");
		PO_HomeView.clickOption(driver, "login", "id", "btnIdentificate");
		
		}

	// PR5.1[InvVal] Enviar una invitación de amistad a un usuario de forma valida.
	@Test
	public void InvVal() {

	}

	// PR5.2[InvInVal] Enviar una invitación de amistad a un usuario al que ya le habíamos
	//invitado la invitación previamente. No debería dejarnos enviar la invitación,
	//se podría ocultar el botón de enviar invitación o notificar que ya había sido enviada previamente.
	@Test
	public void InvInVal() {

	}
	
	// PR6.1 [LisInvVal] Listar las invitaciones recibidas por un usuario, realizar la comprobación
	//con una lista que al menos tenga una invitación recibida.
	@Test
	public void LisInvVal() {

	}
	
	// PR7.1 [AcepInvVal] Aceptar una invitación recibida.
	@Test
	public void AcepInvVal() {

	}
	
	// PR8.1 [ListAmiVal] Listar los amigos de un usuario, realizar la comprobación con 
	//una lista que al menos tenga un amigo.
	@Test
	public void ListAmiVal() {

	}
	
	// PR9.1 [PubVal] Crear una publicación con datos válidos.
	@Test
	public void PubVal() {
		PO_HomeView.clickOption(driver, "login", "id", "btnIdentificate");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "id", "zonaPrivada");
		//Pinchamos en la opción de menu de ver usuarios: 
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//*[@id=\"publicaciones-menu\"]/a"); 
		elementos.get(0).click();
		SeleniumUtils.esperarSegundos(driver, 1);
		elementos = PO_View.checkElement(driver, "free", "//*[@id=\"crearPublication\"]");
		elementos.get(0).click();
		PO_RegisterPublicationView.fillFormAddPublication(driver, "prueba1", "Esto es una prueba");
		PO_View.checkElement(driver, "id", "listaPublicaciones");
		
		
	}
	
	// PR10.1 [LisPubVal] Acceso al listado de publicaciones desde un usuario en sesión.
	@Test
	public void LisPubVal() {
		PO_HomeView.clickOption(driver, "login", "id", "btnIdentificate");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "id", "zonaPrivada");
		//Pinchamos en la opción de menu de publicaciones: 
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//*[@id=\"publicaciones-menu\"]/a"); 
		elementos.get(0).click();
		//Pinchamos en mis ublicaciones
		elementos = PO_View.checkElement(driver, "free", "//*[@id=\"misPublicaciones\"]");
		elementos.get(0).click();
		//Comprobamos que aparece la publicacion Publicacion1
		elementos = PO_View.checkElement(driver, "text", "Publicacion1");

	}
	
	// PR11.1 [LisPubAmiVal] Listar las publicaciones de un usuario amigo
	@Test
	public void LisPubAmiVal() {

	}
	
	// PR11.2 [LisPubAmiInVal] Utilizando un acceso vía URL tratar de listar 
	//las publicaciones de un usuario que no sea amigo del usuario identificado en sesión.
	@Test
	public void LisPubAmiInVal() {

	}
	
	// PR12.1 [PubFot1Val] Crear una publicación con datos válidos y una foto adjunta.
	@Test
	public void PubFot1Val() {

	}
	
	// PR12.1 [PubFot2Val] Crear una publicación con datos válidos y sin una foto adjunta.
	@Test
	public void PubFot2Val() {

	}
	
	// PR13.1 [AdInVal] Inicio de sesión como administrador con datos válidos.
	@Test
	public void AdInVal() {

	}
	
	// PR13.2 [AdInInVal] Inicio de sesión como administrador con datos inválidos (usar los datos 
	//de un usuario que no tenga perfil administrador).
	@Test
	public void AdInInVal() {

	}
	
	// PR14.1 [AdLisUsrVal] Desde un usuario identificado en sesión como administrador listar a 
	//todos los usuarios de la aplicación.
	@Test
	public void AdLisUsrVal() {

	}
	
	// PR15.1 [AdBorUsrVal] Desde un usuario identificado en sesión como administrador eliminar un 
	//usuario existente en la aplicación.
	@Test
	public void AdBorUsrVal() {

	}

	// PR15.2 [AdBorUsrInVal] Intento de acceso vía URL al borrado de un usuario existente en la aplicación. 
	//Debe utilizarse un usuario identificado en sesión pero que no tenga perfil de administrador.
	@Test
	public void AdBorUsrInVal() {

	}
}
