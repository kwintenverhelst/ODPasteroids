package asteroids.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.Asteroids;
import asteroids.Util;
import asteroids.IFacade.ParseOutcome;
import asteroids.model.programs.Program;

public class FacadeTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	Facade facade;

	/**
	 * @post variable position with x coordinate set to 20 and y coordinate set
	 *       to 20
	 */
	@Before
	public void setUp() {
		facade = new Facade();
	}

	@Test
	public final void parseOutCome_TrueCase() {
		String text = "";
		try {
			URL yahoo = Asteroids.class.getClassLoader().getResource(
					"asteroids/resources/program.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yahoo.openStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				text = text + "\n" + inputLine;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ParseOutcome<Program> parseOutcome = facade.parseProgram(text);
		assertTrue(parseOutcome.isSuccessful());
	}

	@Test
	public final void parseOutCome_FalseCase() {
		String text = "bla abb" + "\n ba";
		ParseOutcome<Program> parseOutcome = facade.parseProgram(text);
		assertFalse(parseOutcome.isSuccessful());
	}
	
}
