/*
 * 
 *
 * Note: DO NOT MODIFY!
 */

import java.util.Random;

/**
 * This class represents the Flutteroid creature. It looks a bit like a 
 * jellyfish and can fly. It is dangerous and can suck the life energy
 * out of something. Flutteroids produce the highly sought spice melange, 
 * so their dangerous nature is often ignored and instead they are farmed. 
 * 
 * @author Jeffrey LaMarche
 *
 * @version 1.0 2020-10-20 
 * Initial Version
 *
 * @version 1.1 2024-03-06
 * Updated for CS220 Project 3 to incorporate generator and finalize it. 
 */
public final class Flutteroid {
   /*
   The name for the Flutteroid. 
   */
   private final String nameID;
   
   /*
   The x and y location for the Flutteroid.
   */
   private Point2D location;

   /*
   The range that a Flutteroid can move in a time unit. A value of 10 would
   indicate that a Flutteroid can move anywhere between [-10, 10] x and/or y
   positions during a single time unit. 
   */
   private final int movementRange;
   
   /*
   How many times a Flutteroid has esacped.
   */
   private int escapes;
   
   /**
    * Random number generator object for the Flutteroid movement.
    */
   private static final Random rand = new Random();
   
   /**
    * Obtains a properly setup Flutteroid object with a unique name.
    * Note: Duplicate names are possible, just unlikely.
    * 
    * @return A Flutteroid object reference.
    */
   public static final Flutteroid generateFlutteroid(){
      Flutteroid generatedFlutteroid = null;
   
      // create a random name for the flutteroid
      String name = NAMES[rand.nextInt(0, NAMES.length)];
      int numID = rand.nextInt(NUM_ID_MIN, NUM_ID_MAX + 1);
      name += "-" + String.format("%04d", numID);
      
      // create a random starting location on the farm
      int startX = rand.nextInt(LOW_FARM_BOUNDARY, HIGH_FARM_BOUNDARY + 1);
      int startY = rand.nextInt(LOW_FARM_BOUNDARY, HIGH_FARM_BOUNDARY + 1);
      
      // create a random movement range
      int moveRange = rand.nextInt(MOVE_RANGE_MIN, MOVE_RANGE_MAX + 1);
   
      // instantiate a new Flutteroid object with the values created
      generatedFlutteroid = new Flutteroid(name, startX, startY, moveRange);
      
      return generatedFlutteroid;
   }
   
   /*
    * This constructor creates a Flutteroid object with the given name,
    * location values, and movement range. If the name is null, an invalid
    * name is assigned. 
    * 
    * @param nameID The String reference containing the name for the Flutteroid.
    * @param xLocation The x location integer value for the Flutteroid.
    * @param yLocation The y location integer value for the Flutteroid.
    * @param movementRange The movement range integer value for the Flutteroid.
    */
   private Flutteroid(String nameID, int xLocation, int yLocation, int movementRange) {
      if(nameID != null) {
         this.nameID = nameID;
      }
      else {
         this.nameID = "INVALID";
      }
      
      location = new Point2D(xLocation, yLocation);
      
      this.movementRange = Math.abs(movementRange);
      
      escapes = 0;
   }
   
   /**
    * Allows access to the Flutteroid name/ID value. 
    * 
    * @return A string containing the Flutteroid name string reference.
    */
   public final String getNameID() {
      return nameID;
   }

   /**
    * Allows access to the location of the Flutteroid in 2D space.
    * 
    * @return A Point object containing the X and Y coordinates 
    * for the Flutteroid. 
    */
   public final Point2D getLocation() {
      return location;
   }

   /**
    * Allows access to the movement range of the Flutteroid object.
    * 
    * @return An integer value for the Flutteroid movement range.
    */
   public final int getMovementRange() {
      return movementRange;
   }

   /**
    * Allows access to the number of times a Flutteroid has escaped.
    * 
    * @return An integer value for the number of escapes.
    */
   public final int getEscapes() {
      return escapes;
   }

   /**
    * Assigns the X coordinate location for a Flutteroid.
    * 
    * @param xLocation The new x location integer value for the Flutteroid.
    */
   public final void setXLocation(int xLocation) {
      location = new Point2D(xLocation, location.getY());
   }
   
   /**
    * Assigns the Y coordinate location for a Flutteroid.
    * 
    * @param yLocation The new y location integer value for the Flutteroid.
    */
   public final void setYLocation(int yLocation) {
      location = new Point2D(location.getX(), yLocation);
   }
   
   /**
    * Increases by 1 the times a Flutteroid has escaped.
    */
   public final void increaseEscapes() {
      escapes++;
   }
   
   /**
    * Moves the Flutteroid in a random manner based on its movement range.
    */
   public final void move() {
      // get the min and max values in the movement range
      int max = getMovementRange();
      int min = -getMovementRange();
      
      // compute a random number within this range
      int deltaX = rand.nextInt((max - min) + 1) + min;
      int deltaY = rand.nextInt((max - min) + 1) + min;
      
      // get the current location
      Point2D current = getLocation();
      
      // update the current location to update by the changes in X and Y
      setXLocation(current.getX() + deltaX);
      setYLocation(current.getY() + deltaY);
   }

   /**
    * Displays the Flutteroid data to standard output.
    */
   public final void display() {
      System.out.printf("-=: Flutteroid Info :=-\n");
      System.out.printf("--------------------------\n");
      System.out.printf("    Name ID:  %s\n", nameID);
      System.out.printf("    Location: %s\n", location);
      System.out.printf("    Movement: %d\n", movementRange);
      System.out.printf("    Escapes:  %d\n", escapes);
   }
   
   /**
    * Creates a string representation of a Flutteroid object.
    * 
    * @return A reference to the string representation of the Flutteroid object.
    */
   @Override
   public final String toString() {
      return "{" + nameID + " : " + location + "}";
   }
   
   /*** Constants for the generator. ***/
   
   /*
   The left and bottom boundary for the farm (inclusive).
   */
   private static final int LOW_FARM_BOUNDARY = -100;

   /*
   The right and top boundary for the farm (inclusive).
   */
   private static final int HIGH_FARM_BOUNDARY = 100;
    
   /*
   The minimum and maximum integer portion for the name (inclusive).
   */
   private static final int NUM_ID_MIN = 1;
   private static final int NUM_ID_MAX = 9999;
    
   /*
   The minimum and maximum range for flutteroid movement (inclusive).
   */
   private static final int MOVE_RANGE_MIN = 0;
   private static final int MOVE_RANGE_MAX = 20;
    
   /*
   The possible name prefixes for a flutteroid.
   */
   private static final String[] NAMES =
                {"ALPA", "ASPU", "ARES", "AZRA", "AIHU",
                 "BETA", "BRIA", "BOVU", "BREQ", "BZAR",
                 "CHIU", "CLUA", "CHEA", "CLEO", "CRYO",
                 "DELA", "DUST", "DARK", "DUSK", "DITA",
                 "EPSI", "EPHI", "ESSI", "EZRA", "EQUI", 
                 "FLIT", "FLUT", "FREA", "FURY", "FARA",
                 "GAMA", "GAIA", "GEOM", "GUAR", "GALA",
                 "HAST", "HALA", "HAPA", "HEPI", "HYRU",
                 "IOTA", "ILAM", "ISTO", "IZRO", "IZIO",
                 "JYKE", "JANI", "JOJU", "JERA", "JUJU",
                 "KAPA", "KRIL", "KOPH", "KAIU", "KEPA",
                 "LAMA", "LURN", "LEEM", "LARC", "LOYA",
                 "MUIA", "MULA", "MEKI", "MEKO", "MERA", 
                 "NUZI", "NAGA", "NEEP", "NERA", "NEEK",
                 "OGEA", "OPIA", "OLUM", "OZIA", "OBBU",
                 "PRIT", "PIRA", "PLUW", "PRON", "PIQA",
                 "QEAL", "QUIX", "QUAE", "QZOU", "QWIL",
                 "RHOU", "RENX", "RUMU", "RAMA", "RAZA",
                 "SIMA", "SMOA", "SHOU", "SUHU", "SEPA",
                 "THAU", "TRAL", "TINX", "THEA", "TAWN",
                 "URET", "USIA", "ULEA", "UZUL", "UBEL", 
                 "VESI", "VUSA", "VIPE", "VULU", "VOID",
                 "WHEI", "WOIL", "WOUZ", "WYZE", "WEHI",
                 "XAIN", "XIIM", "XSUA", "XUVA", "XIWE",
                 "YEBO", "YRAG", "YEEB", "YSBE", "YTOL", 
                 "ZETA", "ZLAP", "ZEUS", "ZIPS", "ZIZE"};
                 
}
