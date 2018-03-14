import java.awt.*;

/**
 * Write a description of class Pinball1 here.
 * 
 * @author (UP811334) 
 * @version (a version number or a date)
 */
public class Pinball4 extends Pinball
{

   
   public Pinball4(int xPos, int yPos, int xVel, int yVel, Color objectColor, int objectRadius, Machine theMachine)
   {
       super(xPos, yPos, xVel, yVel, objectColor, objectRadius, theMachine,2, 3);

   }
   
   public void changeDirection()
   {
       double xDirection = Math.random()-0.6;
       double yDirection = Math.random()-0.6;
       
       double magnitude = Math.sqrt(Math.pow(xDirection, 2) + Math.pow(yDirection, 2));    
         

       xDirection /= magnitude;
       yDirection /= magnitude;
         
       xDirection *= 5;
       yDirection *= 5;
       
       if(randomBoolean())
       {
           speedXTravel = (int)xDirection;
           speedYTravel = (int)yDirection;   
       }
       else
       {
           speedXTravel = -(int) xDirection;
           speedYTravel = -(int) yDirection;
       }
       
   }

   
    
}
