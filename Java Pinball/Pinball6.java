import java.awt.*;

/**
 * Write a description of class Pinball1 here.
 * 
 * @author (UP811334) 
 * @version (a version number or a date)
 */
public class Pinball6 extends Pinball
{
   private boolean flashing;
   private final Color validColour;
    
   public Pinball6(int xPos, int yPos, int xVel, int yVel, Color objectColor, int objectRadius, Machine theMachine)
   {
       super(xPos, yPos, xVel, yVel, objectColor, objectRadius, theMachine, 0, 1);
       flashing = false;
       validColour = colour;
    }
    
   /**
    * over rides change flash
    */

   public void changeflash()
   {
       flashing = !flashing;
       if (!flashing)
       {
           colour = validColour;
       }
   }
   
   /**
    * Returns the state of current flashing in boolean
    */

   public boolean isFlashing()
   {
       return flashing;
   }
   
   /**
    * It changes between white and original colour
    */

   public void changingFlash()
   {
       if (colour == validColour) 
       {
           colour = Color.WHITE;
       }else{
           colour = validColour;
       }
   }
}