import java.awt.*;
import java.util.*;

/**
 * Write a description of class Pinball here.
 * 
 * @author (UP811334) 
 * @version (a version number or a date)
 */
public class Pinball extends PinballObject
{
    // instance variables - replace the example below with your own
    protected int speedXTravel;
    protected int speedYTravel;
    private final int leftWallPosition;
    private final int rightWallPosition;
    private final int topWallPosition; 
    private final int bottomWallPosition;
    private final int gapWidth;
    private final int lengthToGap;
    private int currentScore;
    private boolean running;
    protected int edgeCollisionProperty;
    protected int ballCollisionProperty;
    private int maxSpd;
    private int minSpd;
    private Color[] colourVar;
    private String[] pinballType;

        /**
     * Constructor for objects of class PinBall
     * 
     * @param xPos                  the horizontal coordinate of the object
     * @param yPos                  the vertical coordinate of the object
     * @param xVel                  the horizontal speed of the object
     * @param yVel                  the vertical speed of the object  
     * @param objectRadius          the radius (in pixels) of the object
     * @param objectColor           the color of the object
     * @param theMachine            the machine this object is in
     * @param currentScore          the current score when bounching off 
     * @param edgeCollisionProperty the property when it bouches to edges
     * @param ballCollisionProperty the property when it bounches to other ball
     */
    public Pinball(int xPos, int yPos, int xVel, int yVel, Color objectColor, int objectRadius, Machine theMachine, int edgeCollisionProperty, int ballCollisionProperty)
    {
        super(xPos, yPos, objectColor, objectRadius, theMachine);
        speedXTravel = xVel;
        speedYTravel = yVel;
        leftWallPosition = machine.getLeftWall();
        bottomWallPosition = machine.getBottomWall();
        rightWallPosition = machine.getRightWall();
        topWallPosition = machine.getTopWall();
        gapWidth = machine.getGapWidth();
        lengthToGap = machine.getLengthToGap();
        currentScore = 0;
        running = true;
        maxSpd = 2;
        minSpd = 1;
        this.edgeCollisionProperty = edgeCollisionProperty;
        this.ballCollisionProperty = ballCollisionProperty;
            
        this.pinballType = new String[4];
        this.pinballType[0] = "flashing";     // this will flash when it touches the wall
        this.pinballType[1] = "speed";        //this will change the speed of the ball when colliding with another ball
        this.pinballType[2] = "colour";       //this will change when it hits the wall
        this.pinballType[3] = "direction";    //this will change the direction of the ball when colliding with another ball
        
        colourVar = new Color[3];
        colourVar[0] = Color.RED;
        colourVar[1] = Color.BLUE;
        colourVar[2] = Color.GREEN;
    }
    
    /**
     * This will move the ovject according to its position, speed and redraw
     */
     public void move()
    {
        //remove from universe at the current position
        machine.erase(this);
        if (running)
        {
            //compute new position
            currentXLocation += speedXTravel;
            currentYLocation += speedYTravel;
            
            checkWalls();
            checkOtherballs();
            checkBumpers();
            checkHoles();
            
            checkFlashing();
            // draw again at new position
            if (running)
            {
                machine.draw(this);
            }
        }
    }
    /**
     * This will check if the ball hits the wall 
     */
    public void checkWalls()
    {
        if (running)
        {
            //check if it has hit the leftwall
            if(currentXLocation <= (leftWallPosition + radius)) 
            {
                currentXLocation = leftWallPosition + radius;
                speedXTravel = -speedXTravel; 
                currentScore++;
                    if(edgeCollisionProperty == 2)
                {
                    colourChange();
                }
            }
                
            // check if it has hit the bottomwall
            if(currentYLocation >= (bottomWallPosition - radius))
            {
                if(currentXLocation <= (lengthToGap + radius))
                {
                    currentYLocation = bottomWallPosition - radius;
                    speedYTravel = -speedYTravel;
                    currentScore++;
                    if(edgeCollisionProperty == 2)
                {
                    colourChange();
                }
                    else
                {
                
                }
                    
                }    
                else if(currentXLocation >= (lengthToGap + (gapWidth*2) - radius))
                {
                    currentYLocation = bottomWallPosition - radius;
                    speedYTravel = -speedYTravel;
                    currentScore++;
                    if(edgeCollisionProperty == 2)
                    {
                        colourChange();
                    }
                }
                else if(currentYLocation + radius >= bottomWallPosition - 10)
                {
                    machine.running = false;
                }
            }
                
            // check if it has hit the rightwall
            if(currentXLocation >= (rightWallPosition - radius)) 
            {
                currentXLocation = rightWallPosition - radius;
                speedXTravel = -speedXTravel;
                currentScore++;
                if(edgeCollisionProperty == 2)
                {
                    colourChange();
                }
            }
                
            // check if it has hit the topwall
            if(currentYLocation <= (topWallPosition + radius)) 
            {
                currentYLocation = topWallPosition + radius;
                speedYTravel = -speedYTravel;
                currentScore++;
                if(edgeCollisionProperty == 2)
                {
                        colourChange();
                }
            }
        } 
    }
    
    /**
    * Dummy flashChange method
    */
    public void flashChange()
    {
        
    }
    
    public boolean isFlashing()
    {
    //Override method
    return true;
    }
   
    
    /**
    * Dummy flash changing method
    */
    public void  changingFlash()
    {
    
    }
    
    /**
     * This will check if the ball hits another ball 
     */
    public void checkOtherballs()
    {
       if (running)
       {
           for (Pinball pinballs: machine.getPinballs())
           {
               if (pinballs != this && pinballs.running)
               {
                   int xPinball = pinballs.getXPosition();
                   int yPinball = pinballs.getYPosition();
                   int pinballRadius = pinballs.getRadius();
                   
                   double differentX = Math.pow(currentXLocation - xPinball, 2);
                   double differentY = Math.pow(currentYLocation - yPinball, 2);
                   
                   double midDis = Math.sqrt(differentX + differentY);
                   
                   if (midDis <= radius + pinballRadius)
                   {
                       currentScore += 5;
                       speedXTravel = -speedXTravel;
                       speedYTravel = -speedYTravel;
                       
                       if( ballCollisionProperty == 3)
                       {
                            changeDirection();
                        }
                        else{
                            changeSpeed();
                        }
                       currentXLocation += speedXTravel;
                       currentYLocation += speedYTravel;
                   }
               }
           }
       }
    }
    
     /**
     * This will check if the direction change 
     */
    public void changeDirection()
    {
        double x = Math.random() - 0.1;
        double y = Math.random() - 0.1;
        double m = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        
        x /= m;
        y /= m;
        
        x *= 0.1;
        y *= 0.1;
        
        speedXTravel = (int) x;
        speedYTravel = (int) y;
    }
    
    /**
    * This will check if it chnages the speed
    */    
    public void changeSpeed()
    {
        Random ran = new Random();
        boolean speed = randomBoolean();
        int randX = ran.nextInt(maxSpd) + minSpd;
        int randY = ran.nextInt(maxSpd) + minSpd;
        if(speed)
        {
            speedXTravel *= (randX);
            speedYTravel *= (randY);
        }
        else
        {
            speedXTravel *= -(randX);
            speedYTravel *= -(randY);           
        }
        
       
    }
    
     /**
     * This will give out a random boolean
     */
    public boolean randomBoolean()
    {
        double d = Math.random();
    
        if(d >= 0.1)
        {
            return true;
        }else{
            return false;
        }
    }
  
    /**
     * This will check if the pinball has to flash 
     */
    public void checkFlashing()
    {
        if (ballCollisionProperty == 1 && isFlashing())
        {
            changingFlash();
        }
    }
     
    /**
    * Check if the ball hits the bumper
    */ 
    public void checkBumpers()
    {
        if (running)
        {
            for (Bumper bumpers: machine.getBumpers())
            {
                int xBump = bumpers.getXPosition();
                int yBump = bumpers.getYPosition();
                int radiusBump = bumpers.getRadius();
                
                double differentX = Math.pow(currentXLocation - xBump, 2);
                double differentY = Math.pow(currentYLocation - yBump, 2);
                
                double midDis = Math.sqrt(differentX + differentY);
                
                if (midDis <= radius + radiusBump)
                {
                    currentScore += 2;
                    speedXTravel = -speedXTravel;
                    speedYTravel = -speedYTravel;
                    
                    currentXLocation += speedXTravel;
                    currentYLocation += speedYTravel;
                }
            }
        }
    }
    
     /**
     * This will check if the hole works
     */
    public void checkHoles()
    {
        for (Hole holes: machine.getHoles())                                            //Call the arrayList
        {
            int xHole = holes.getXPosition();
            int yHole = holes.getYPosition();
            int radiusHole = holes.getRadius();
            
            double differentX = Math.pow(currentXLocation - xHole, 2);
            double differentY = Math.pow(currentYLocation - yHole, 2);
            
            double midDis = Math.sqrt(differentX + differentY);
            
            if (midDis <= radius + radiusHole)
            {
                if(radius <= radiusHole)
                {
                    running = false;
                    machine.erase(this);
                    machine.erase(holes);
                    machine.draw(holes);
                }
                currentScore = 0;
            }
        }
    }
  
    /**
     * return the current score 
     */
    public int getCurrentScore()
    {
        return currentScore;
    }
    
     /**
     * This 
     */
    public void colourChange()
    {
        int index = Arrays.asList(colourVar).indexOf(colour);
        index += 1;
        if(index > 2)
        {
            index = 0;
        }
        colour = colourVar[index];
    }
   
    /**
     * return if the game is running or not
     */
    public boolean ifrunning()
    {
        return running;
    }
}