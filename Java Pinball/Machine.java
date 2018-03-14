import java.awt.*;
import java.util.ArrayList;

/**
 * A pinball machine, with a sample demo
 * 
 * @author (UP811334) 
 * @version (a version number or a date)
 */
public class Machine
{
    private Canvas machine;
    private int topEdge = 0;
    private int leftEdge = 0;
    private int bottomEdge;
    private int rightEdge;
    private int lengthToGap;        // the distance between the edge of the machine and the start of the gap
    private int gapWidth = 50;
    protected ArrayList<Pinball> pinballs;
    protected ArrayList<Bumper> bumpers;
    protected ArrayList<Hole> holes;
    protected boolean running;

    /**
     * Create a machine with default name and size
    */
    public Machine()
    {
        machine = new Canvas("Pinball Demo", 600, 500);
        rightEdge = 600;
        bottomEdge = 500;
        lengthToGap = (rightEdge / 2) - gapWidth;
        pinballs= new ArrayList<Pinball>();
        bumpers= new ArrayList<Bumper>();
        holes= new ArrayList<Hole>();
        running = true;
        drawMachine();
    }
    
    /**
     *  Create a machine with given name and size
     *  @param name The name to give the machine
     *  @param rightEdge The maximum x coordinate
     *  @param bottomEdge The maximum y coordinate
     */
     public Machine(String name, int rightEdge, int bottomEdge)
    {
        machine = new Canvas(name, rightEdge, bottomEdge);
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        this.pinballs= new ArrayList<Pinball>();
        this.bumpers= new ArrayList<Bumper>();
        this.holes= new ArrayList<Hole>();
        lengthToGap = (rightEdge / 2) - gapWidth;
        drawMachine();
    }

    /**
     * Erase a PinballObject from the view of the pinball machine
     * 
     * @param pinballObj The object to be erased
     */
    public void erase(PinballObject pinballObj)
    {
        machine.eraseCircle(pinballObj.getXPosition() - pinballObj.getRadius(), pinballObj.getYPosition()- pinballObj.getRadius(), pinballObj.getDiameter());
    }
    
    /**
    * Draw an PinballObject at its current position onto the view of the pinball machine
    * 
    * @param pinballObj The object to be drawn
    */
    public void draw(PinballObject pinball)
    {
        machine.setForegroundColor(pinball.getColor());
        machine.fillCircle(pinball.getXPosition() - pinball.getRadius(), pinball.getYPosition() - pinball.getRadius(), pinball.getDiameter());
    }
    
    public void draw(Pinball pinball)
    {
        machine.setForegroundColor(pinball.getColor());
        machine.fillCircle(pinball.getXPosition() - pinball.getRadius(), pinball.getYPosition() - pinball.getRadius(), pinball.getDiameter());
        
        machine.setForegroundColor(Color.BLACK);
        machine.drawString(Integer.toString(pinball.getCurrentScore()),pinball.getXPosition()-5, pinball.getYPosition()+5);
    }
    
    public void draw(Bumper bumper)
    {
        machine.setForegroundColor(bumper.getColor());
        machine.fillCircle(bumper.getXPosition() - bumper.getRadius(), bumper.getYPosition() - bumper.getRadius(), bumper.getDiameter());
    }
    
    public void draw(Hole hole)
    {
        machine.setForegroundColor(hole.getColor());
        machine.fillCircle(hole.getXPosition() - hole.getRadius(), hole.getYPosition() - hole.getRadius(), hole.getDiameter());
    }
    
    /**
    * Draw the edge of the pinball machine 
    */
    public void drawMachine()
    {
        machine.setForegroundColor(Color.DARK_GRAY);
        
        machine.fillRectangle(0, 0, rightEdge, 10);  // top edge
        machine.fillRectangle(0, 0, 10, bottomEdge); // left edge
        machine.fillRectangle(rightEdge - 10, 0, 10, bottomEdge); // right edge
        
        machine.fillRectangle(0, bottomEdge - 10, lengthToGap, 10); // left-hand side of bottom edge
        machine.fillRectangle(rightEdge - lengthToGap, bottomEdge - 10, rightEdge, 10);     // right-hand side of bottom edge
    }
  
     /**
     * Return the edge of the left-hand wall
     */
    public int getLeftWall()
    {
        return leftEdge + 10;
    }
    
    /**
     * Return the edge of the bottom-hand wall
     */
    public int getBottomWall()
    {
        return bottomEdge - 10;
    }
    
    /**
     * Return the edge of the right-hand wall
     */
    public int getRightWall()
    {
        return rightEdge - 10;
    }
    
    /**
     * Return the edge of the top-hand wall
     */
    public int getTopWall()
    {
        return topEdge + 10;
    }
    
    /**
     * Return the gap of bottom wall
     */
     public int getLengthToGap()
    {
        return lengthToGap;
    }
    
    /**
     * Return the gap width of the wall
     */
    public int getGapWidth()
    {
        return gapWidth;
    }
    
    /**
     * Return a condtion for game
     */
    public boolean notGameOver()
    {
        return running;
    }
    
    /**
     * Return the ArrayList for Pinball
     */
    public ArrayList<Pinball> getPinballs()
    {
        return pinballs;
    }
    
    public void addPinball(Pinball ball)
    {
        this.pinballs.add(ball);
    }
    
    /**
     * Return the ArrayList for Hole
     */
    public ArrayList<Hole> getHoles()
    {
        return holes;
    }
    
    public void addHole(Hole hole)
    {
        this.holes.add(hole);
    }
    
    /**
     * Return the ArrayList for Bumper
     */
    public ArrayList<Bumper> getBumpers()
    {
        return bumpers;
    }
    
    public void addBumpers(Bumper bump)
    {
        this.bumpers.add(bump);
    }
    
    /**
     * Return drawString
     */
    public void drawString(String text, int xPos, int yPos)
    {
        machine.drawString(text, xPos, yPos);
    }
    
    /**
     * Introduces a small delay in ball movement, for smooth running
     */
    
    public void pauseMachine()
    {
        machine.wait(50);
    }
    
    /**
     * Resets the machine back to initial view, with no pinballs
     */
    public void resetMachine()
    {
        machine.erase();
        drawMachine();
    }
}
