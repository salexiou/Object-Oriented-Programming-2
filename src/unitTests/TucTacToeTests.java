package unitTests;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeThat;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import control.*;
import model.*;
import view.*;




public class TucTacToeTests {

		GameController gc;
		
	/*
		@BeforeEach
		void init() {
			gc = new GameController();
		}
		
		
		
		@Test
		@DisplayName("Players must have been inserted correctly")
		void testInitialPlayers() {
			assertEquals(gc.getModel().getPlayersCatalogue().getNumOfPlayers(), 4 ,"Initial Players must be 4");
		}
		
		
		
		@Test
		@DisplayName("Catalogue size must be updated respectively")
		void testCatalogueSize() {
			this.gc.getModel().getPlayersCatalogue().addPlayer("Stamatis");
			this.gc.getModel().getPlayersCatalogue().addPlayer("Eleni");
			assertAll("Sizes after list additions",
					()->assertEquals(gc.getModel().getPlayersCatalogue().getNumOfPlayers(), 2,"Initial Players must be 2")
					);

		}
		
		
		@Test
		@DisplayName("Full List should not accept more elements")
		void testAddRespectsLimits() {
			this.gc.getModel().getPlayersCatalogue().addPlayer("S");
			this.gc.getModel().getPlayersCatalogue().addPlayer("St");
			this.gc.getModel().getPlayersCatalogue().addPlayer("Sta");
			this.gc.getModel().getPlayersCatalogue().addPlayer("Stam");
			this.gc.getModel().getPlayersCatalogue().addPlayer("Stama");
			this.gc.getModel().getPlayersCatalogue().addPlayer("Stamat");
			this.gc.getModel().getPlayersCatalogue().addPlayer("Stamati");
			this.gc.getModel().getPlayersCatalogue().addPlayer("Stamatis");
			this.gc.getModel().getPlayersCatalogue().addPlayer("Eleni");
			this.gc.getModel().getPlayersCatalogue().addPlayer("E");
			this.gc.getModel().getPlayersCatalogue().addPlayer("El");
			this.gc.getModel().getPlayersCatalogue().addPlayer("Ele");
			this.gc.getModel().getPlayersCatalogue().addPlayer("Elen");
			this.gc.getModel().getPlayersCatalogue().addPlayer("fdh");
			this.gc.getModel().getPlayersCatalogue().addPlayer("fhkdghj");
			this.gc.getModel().getPlayersCatalogue().addPlayer("fhmksffha");
			this.gc.getModel().getPlayersCatalogue().addPlayer("mcvnbs");
			this.gc.getModel().getPlayersCatalogue().addPlayer("yerwyqwer");
			this.gc.getModel().getPlayersCatalogue().addPlayer("uolkghf");
			this.gc.getModel().getPlayersCatalogue().addPlayer("qwerej");
			this.gc.getModel().getPlayersCatalogue().addPlayer("2343wyrehdf");
			this.gc.getModel().getPlayersCatalogue().addPlayer("dgjfghkjj");
			this.gc.getModel().getPlayersCatalogue().addPlayer("sjdhfdghdfgd");
			this.gc.getModel().getPlayersCatalogue().addPlayer("mdgfas");
			this.gc.getModel().getPlayersCatalogue().addPlayer("djytukit");
			this.gc.getModel().getPlayersCatalogue().addPlayer("jcjdewyw");
			this.gc.getModel().getPlayersCatalogue().addPlayer("fgkkoyoiuyt");
			this.gc.getModel().getPlayersCatalogue().addPlayer("adjfdfkglgulkj");
			this.gc.getModel().getPlayersCatalogue().addPlayer("fgityuygd");
			this.gc.getModel().getPlayersCatalogue().addPlayer("dsgfktyu");


			Throwable exception = assertThrows(ArrayIndexOutOfBoundsException.class, ()->this.gc.getModel().getPlayersCatalogue().addPlayer("Fotis"));
			assertEquals("PlayersCatalogue is full", exception.getMessage());
			assertEquals(this.gc.getModel().getPlayersCatalogue().getPlayers(), 30 ,"Catalogue's size should be 30");
		}
		
		

		
		@Test
		@DisplayName("returned element should be equal to added element in a position")
		void testGetReturnsAddedElement() {
			this.gc.getModel().getPlayersCatalogue().addPlayer("dsgfktyu");
			this.gc.getModel().getPlayersCatalogue().addPlayer("jcjdewyw");
			
			assertEquals(this.gc.getModel().getPlayersCatalogue().getPlayers()[0],"dsgfktyu");
			assertEquals(this.gc.getModel().getPlayersCatalogue().getPlayers()[1],"jcjdewyw");
			assertNull(this.gc.getModel().getPlayersCatalogue().getPlayers()[15]);
		}
		
	
		*/
		
	}
