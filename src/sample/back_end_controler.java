import java.util.Scanner;
import java.util.Arraylist;
import java.util.Arrays;
import java.util.ArrayList;


/*
DOCUMENTATION
This is the core code for Plants v Zombies
Non-GUI code!

startup() : In many classes, this is the first function that is called. It is the function called when changing screen. It displays a new screen when executed among other things.

p_variablename : parameter for variablename when passed in constructor. Uniformly implemented accross all classes.
*/


/*
Stuff to do:

if you have time, implement progress bar on basis of (#zombiesleft/total#ofzombies)

one user - one saved game seems sensible to me, should we keep multiple saved games per user

these implement Serializable interface - SignupLoginScreen, LevelsScreen, Level (and subclasses), PC (and subclasses)
issue - on 1 position on matrix, there can be 1 plant + 1-n zombies. Thus, >=2 PC's on 1 position on matrix is defo possible

Have to handle which variables are final

dude good idea - split this to a few different text files! (3 is fine I guess)
*/



/*
	MAJOR heading:
	///////////
	///////////
	/// xyz ///
	///////////
	///////////


	MINOR heading:
	/*************
	 *****  x ****
	 *************/
*/


/*
Directory structure:

INITIAL STUFF - TOP OF UML
			  - OTHERS
LEVELS
CHARACTERS    - PLANT
              - ZOMBIE
              - NPC
*/









////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////		INITIAL STUFF 			////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////



/*********************************************************************************
 ********************		START OF UML	**************************************
 ********************************************************************************/


public class PlantsvsZombies
{
	// display first image of PvZ
	LoginScreen loginScreen1 = new LoginScreen();
	loginScreen1.startup(); // starts up the loginscreen
	// no GUI required here
}



class LoginScreen implements Serializable
{
	private Hashmap<String, String> existingUsers; // contains <username, password>
	protected Player selectedPlayer;

	// no need to make constructor coz declaring constructor does not help

	public void startup()
	{
		// Display LoginScreen image with buttons, etc
		// wait for input of buttons

		// load existingUsers Hashmap from serial
		// if you are running application first time, the serial file would
		// be empty
		// but the file should exist
	}


	// IDEA: you write username, password
	// then you click on either login or signup


	public void SignupOrLoginPlayer()
	{
		// get input username, password from user
		// this is common for both signup and login
		String inputtedUsername = INPUT_USERNAME; // username
		String inputtedPassword = INPUT_PASSWORD; // password

		if( CLICK_SIGNUP_BUTTON )
		{
			boolean clashingUsernameFound;
			// check if this username exists in existigUsers ka field 0

			if(clashingUsernameFound)
			{
				throw new ClashingUsernameException("There already exists a user with username " + INPUT_USERNAME);
				// print this on screen as well
			}

			else
			{
				Player newlyCreatedPlayer = new Player(INPUT_USERNAME, INPUT_PASSWORD);

				// adding this new player to list of existing players
				this.existingUsers.add(newlyCreatedPlayer);

				SignupOrLoginPlayer(); // repeat
				// in either case!
			}

		}

		else if( CLICK_LOGIN_BUTTON )
		{
			// check if that username and password exist in database
			if( USERNAME_PASSWORD_PAIR_IS_AN_ENTRY_IN_HASHMAP)
			{
				LevelsScreen levelsScreen1 = new LevelsScreen(currentPlayer);
				levelsScreen1.startup();
			}
			else
			{
				// print - sorry, no match found
				// please enter credentials again
				SignupOrLoginPlayer(); // repeat function
			}
		}
	}
}



class LevelsScreen implements Serializable
{
	public ArrayList<Level> savedGames;
	Player currentPlayer;
	Level currentLevel;

	LevelsScreen(Player p_currentPlayer)
	{
		this.currentPlayer = p_currentPlayer;
		this.getSavedGames();
	}


	public void getSavedGames()
	{
		// does ls and find all serialized files
		// files are saved as level_<playerName>.txt
		// Write all these to 		Arraylist<Level> savedGames
		/*
		Path dir = Paths.get("/path/to/directory");

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.c")) {
			for (Path file : stream)
			{
				// parse file to each element in savedGames
			}
		}
		*/
	}


	public void startup()
	{
		// display all levels GUI
		// like 5 levels (boxes on screen)
		// no scrolling required

		if (USER_CLICK_SHOW_SAVED_GAMES)
		{
			showSavedGames();
			SELECT_GAME; // GUI
			GO_TO_THAT_GAME
		}

		GO_TO_SELECTED_GAME; // GUI
		// selected game would be like 		new Level_x();
	}



	public void goToLoginScreen()
	{
		// CALLED ON CLICKING BUTTON
		LoginScreen loginscreen1 = new LoginScreen(); // new object, different from previous one
		loginscreen1.startup();
	}
}



/*********************************************************************************
 ********************		OTHERS		******************************************
 ********************************************************************************/


interface Serializable
{
	// put in another file!
}


// exceptions below:

class ClashingLoginNameException extends Exception
{
	// to implement
}


class HaveNotUnlockedThisLevelYetException extends Exception
{
	// to implement
}



class Player // implements Serializable?
{
	protected String username;
	protected String password;
	protected int maxLevel; // max level this Player has reached till

	 Player(String p_username, String p_password)
	{
		this.username = p_username;
		this.password = p_password;
		this.maxLevel = 1;
	}

	public void incLevel() { this.maxLevel += 1; }

	public String serialize()
	{
		// serialize about player
	}

	private Player deserialize( Stringfilename )
	{
		// deserialize about player
	}
}










///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////		LEVEL, LEVEL_X BELOW		///////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////












abstract class Level implements Serializable
{
	// need to be able to get Plants, Zombies from this

	protected Arraylist<PC> matrixYard; // at these positions there can be plants, zombies
	protected int numberOfSunTokensAvailable = 0;

	protected int  thisLevelNumber;
	protected Arraylist<Plant> availablePlants;
	protected Hashmap<Zombie, int> zombieLeftByType;
	protected Arraylist<int>  legalRowNumbers; // row numbers are from 0-4
	// these are used in subclasses and not in Level class
	// do i need to write them here?
	// how to make it such that i dont need to redeclare them in subclasses


	public abstract void   initialiseYardWithRows();

	public abstract Zombie dropZombieFromSkyAtRandomPosition();
	// this is abstract because it is only there in level 5

	public String saveThisGameState()
	{
		// write to file
		// print - game state was saved!
		// GUI ^
	}

	public Level  deserialize(String filename)
	{
		// do stuff
	}

	public selectPlantFromChoices()
	{
		// GUI
	}

	// in each level the arraylist of available plants is hardcoded
	// ofcourse we could make a very elegant solution of computing available plants
	// by the current level number. however, that is not required for this assignment

	public Zombie getOneRandomZombieFromHashmap(Hashmap<Zombie, int> zombieLeftByType)
	{
		// get random zombie
		// deduct count in hashmap
	}

	public Level  saveThisGame()
	{
		// serialize
	}

	public void goToLoginScreen()
	{
		LoginScreen loginScreen1 = new LoginScreen();
		loginscreen1.startup();
	}

	public Sun dropSunAtRandomPosition()
	{
		// compute random position based on dimensions of this levels yard
		// compute random x
		// and random y
		// and drop it at that position
	}
}





class Level_1
{
	private Arraylist<int>  legalRowNumbers; // row numbers are from 0-4
	private Hashmap<Zombie, int> zombieLeftByType;
	protected int  thisLevelNumber;
	protected Arraylist<Plant> availablePlants;


	Level_1()
	{
		this.legalRowNumbers.add(2);
		this.thisLevelNumber = 1; // not sure where this would be used

		this.availablePlants.add(new Peashooter);

		// initialise hashmap
	}


	public Zombie  dropZombieFromSkyAtRandomPosition()	{ return; }

	public void initialiseYardWithRows()
	{
		// GUI stuff
		// and logical stuff
	}
}


class Level_2
{
	private Arraylist<int>  legalRowNumbers; // row numbers are from 0-4
	private Hashmap<Zombie, int> zombieLeftByType;
	protected int  thisLevelNumber;
	protected Arraylist<Plant> availablePlants;


	Level_2()
	{
		this.legalRowNumbers.add(1);
		this.legalRowNumbers.add(2);
		this.legalRowNumbers.add(3);
		this.thisLevelNumber = 2; // not sure where this would be used

		this.availablePlants.add(new Peashooter);
		this.availablePlants.add(new Sunflower);

		// initialise hashmap
	}


	public Zombie  dropZombieFromSkyAtRandomPosition()	{ return; }

	public void initialiseYardWithRows()
	{
		// GUI stuff
		// and logical stuff
	}
}

class Level_3
{

	private Arraylist<int>  legalRowNumbers; // row numbers are from 0-4
	private Hashmap<Zombie, int> zombieLeftByType;
	protected int  thisLevelNumber;
	protected Arraylist<Plant> availablePlants;


	Level_3()
	{
		this.legalRowNumbers.add(0);
		this.legalRowNumbers.add(1);
		this.legalRowNumbers.add(2);
		this.legalRowNumbers.add(3);
		this.legalRowNumbers.add(4);
		this.thisLevelNumber = 3; // not sure where this would be used

		this.availablePlants.add(new Peashooter);
		this.availablePlants.add(new Sunflower);
		this.availablePlants.add(new Cherry);

		// initialise hashmap
	}


	public Zombie  dropZombieFromSkyAtRandomPosition()	{ return; }

	public void initialiseYardWithRows()
	{
		// GUI stuff
		// and logical stuff
	}
}


class Level_4
{
	private Arraylist<int>  legalRowNumbers; // row numbers are from 0-4
	private Hashmap<Zombie, int> zombieLeftByType;
	protected int  thisLevelNumber;
	protected Arraylist<Plant> availablePlants;


	Level_4()
	{
		this.legalRowNumbers.add(0);
		this.legalRowNumbers.add(1);
		this.legalRowNumbers.add(2);
		this.legalRowNumbers.add(3);
		this.legalRowNumbers.add(4);
		this.thisLevelNumber = 4; // not sure where this would be used

		this.availablePlants.add(new Peashooter);
		this.availablePlants.add(new Sunflower);
		this.availablePlants.add(new Cherry);
		this.availablePlants.add(new Wallnut);

		// initialise hashmap
	}


	public Zombie  dropZombieFromSkyAtRandomPosition()	{ return; }

	public void initialiseYardWithRows()
	{
		// GUI stuff
		// and logical stuff
	}
}


class Level_5
{
	private Arraylist<int>  legalRowNumbers; // row numbers are from 0-4
	private Hashmap<Zombie, int> zombieLeftByType;
	protected int  thisLevelNumber;
	protected Arraylist<Plant> availablePlants;


	Level_5()
	{
		this.legalRowNumbers.add(0);
		this.legalRowNumbers.add(1);
		this.legalRowNumbers.add(2);
		this.legalRowNumbers.add(3);
		this.legalRowNumbers.add(4);
		this.thisLevelNumber = 4; // not sure where this would be used

		this.availablePlants.add(new Peashooter);
		this.availablePlants.add(new Sunflower);
		this.availablePlants.add(new Cherry);
		this.availablePlants.add(new Wallnut);

		// initialise hashmap
	}


	public Zombie  dropZombieFromSkyAtRandomPosition()	{ return; }

	public void initialiseYardWithRows()
	{
		// GUI stuff
		// and logical stuff
	}
}














////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////		CHARACTERS BELOW (PC, NPC)		////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////


abstract class Character
{
	// what comes here?
}



abstract class PC
{
	public double health;

	protected void takeDamage(Double reduceBy)
	{
		this.health -= reduceBy;
	}
}



/******************************************************************************
 **************************			NPC		***********************************
 ******************************************************************************/



abstract class NPC
{
	// what comes here?
}


class CrazyDave
{

	private void speakMessage(String myMessage)
	{
		// GUI
		// print this in proper form on screen
	}
}


class LawnMover
{
	private void executeLawnMover()
	{
		// run lawn mover accross lawn, killing all plants + zombies in that row
		// or only zombies? I think both
	}
}








/******************************************************************************
 **************************			ZOMBIE		*******************************
 ******************************************************************************/






abstract class Zombie
{
	protected double health;
	protected double  walkSpeed;
	protected double biteSize = 20;

	// takeDamage() is implemented in superclass

	protected void eatPlantOneBite(Plant currentPlant)
	{
		// to implement
	}
}




class Normal_Zombie
{
	public Normal_Zombie()
	{
		this.health = 100;
		this.walkSpeed = 7;
	}
}


class Conehead_Zombie
{
	public Conehead_Zombie()
	{
		this.health = 180;
		this.walkSpeed = 7;
	}
}


class Flag_Zombie
{
	public Flag_Zombie()
	{
		this.health = 140;
		this.walkSpeed = 13;
	}
}







/******************************************************************************
 **************************			PLANT		*******************************
 ******************************************************************************/



abstract class Plant
{
	protected abstract void doUniquePlantAction();
}


class Sun
{
	private int timeLeft;
	private int power;

	public Sun()
	{
		this.power = 25;
		this.timeLeft = 4; // seconds
	}

	public int getSunPower() { return this.power; }
}


class Pea
{
	protected double damageAmount;

	public Pea()
	{
		this.damageAmount = 25;
	}

	public Double giveDamage()
	{
		// return number?
	}
}


class Peashooter
{
	Peashooter()
	{
		this.health = 110;
	}

	protected void  doUniquePlantAction()
	{
		// do stuff
		// GUI
	}

	public Pea createPeaObject()
	{
		Pea pea1 = new Pea();
		return pea1;
	}
}


class Sunflower
{
	Sunflower()
	{
		this.health = 70;
	}

	protected void  doUniquePlantAction()
	{
		// GUI
		// do stuff
	}

	public Sun createSunObject()
	{
		Sun sun1 = new Sun();
		return sun1;
	}
}


class Cherry
{
	public int timeLeft;

	Cherry()
	{
		this.health = 90;
	}
	protected void  doUniquePlantAction()
	{
		// GUI
		// do stuff
	}

	public void  tickOneSecond(int timeLeft)
	{
		// is this a good programming idea?
	}

	public void amIReadyToBlast()
	{
		if(this.timeLeft<=0)
		{
			// blast
			// GUI
		}
	}
}


class Wallnut
{
	Wallnut()
	{
		this.health = 80;
	}

	protected void  doUniquePlantAction()
	{
		// GUI stuff
		// implement
	}

	private void takeOneBite(double reduceBy)
	{
		this.health -= reduceBy;
	}
}
