import java.awt.*;

/**
 * Class to demonstrate functionality of the Pinball machine
 * 
 * @author (UP811334) 
 * @version (a version number or a date)
 */
public class Demo
{
    private Machine machine;
    private int totalScore;

    /**
     * Constructor for objects of class Demo
     */
    public Demo()
    {
        machine = new Machine();
        totalScore = 0;
    }

    /**
     * Sample demo method - demonstrates what happens when an object rebounds off the left-hand wall
     */
    public void sampleDemo()
    {
        // sample demo
       machine.resetMachine();
       //hole obj9 score Pinball obj1 = new Pinball(100, 20, -5, 3, Color.RED, 15, machine);
       //demo holes Pinball obj1 = new Pinball(105, 200, -5, 3, Color.RED, 15, machine);
       //game over demo Pinball obj2 = new Pinball(220, 300, 1, 2, Color.BLUE, 25, machine);
       //bumper obj7/8 score Pinball obj4 = new Pinball(150, 450, 2, -2, Color.MAGENTA, 20, machine);
       Pinball6 obj1 = new Pinball6(105, 200, -5, 5, Color.CYAN, 15, machine);
       Pinball6 obj2 = new Pinball6(160, 300, -3, 3, Color.MAGENTA, 25, machine);
       Pinball6 obj3 = new Pinball6(450, 125, -3, 3, Color.YELLOW, 15, machine);
       Pinball4 obj4 = new Pinball4(250, 450, -3, 3, Color.RED, 20, machine);
       Pinball4 obj5 = new Pinball4(200, 200, 2, -2, Color.BLUE, 25, machine);
       Pinball4 obj6 = new Pinball4(300, 200, 2, -2, Color.GREEN, 30, machine);
       Bumper obj7 = new Bumper(100, 150, Color.GRAY, 20, machine);
       Bumper obj8 = new Bumper(370, 370, Color.GRAY, 10, machine);
       Hole obj9 = new Hole(80, 60, Color.BLACK, 20, machine);
       Hole obj10 = new Hole(400, 280, Color.BLACK, 20, machine);
       
       machine.addPinball(obj1);
       machine.addPinball(obj2);
       machine.addPinball(obj3);
       machine.addPinball(obj4);
       machine.addPinball(obj5);
       machine.addPinball(obj6);
       machine.addBumpers(obj7);
       machine.addBumpers(obj8);
       machine.addHole(obj9);
       machine.addHole(obj10);
        
       while (machine.notGameOver())
       {
           machine.pauseMachine();           // small delay
           obj1.move();
           obj2.move();   
           obj3.move();
           obj4.move();
           obj5.move();
           obj6.move();
           obj7.active();
           obj8.active();
           obj9.active();
           obj10.active();
        }
        
       for (Pinball p: machine.getPinballs())
       {
           totalScore += p.getCurrentScore();
       }
       machine.drawString("Game Over ", 250, 250); 
       machine.drawString("Total Score: " + totalScore, 245, 275);    
    }
}