import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;
 
public class GamePanel extends JPanel implements Runnable, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5000752910856927622L;

	private static final int FPS = 1000 / 36;
	
	public static void main(String[] args)
    {
    	JFrame parentWindow = new JFrame("The game");
    	
    	parentWindow.getContentPane().add(new GamePanel());
    	
    	parentWindow.setSize(320 , 240);
        parentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentWindow.setVisible(true);
        parentWindow.createBufferStrategy(2);
    }
	
	
	private Collection<Element> elements = new ArrayList<Element>();
    
	private Ball e1;
	private Rectangle e2;

	public GamePanel() {
		super(true);
		
		elements.add(e1 = new Ball(10, 20, 32, 32));
		elements.add(e2 = new Rectangle(20, 20, 32, 32));

		this.setFocusable(true);
        this.addKeyListener( this );

		new Thread(this).start();
	}
	
    public void run() {
    	// Remember the starting time
    	long tm = System.currentTimeMillis();

        while(true){
            update();
            repaint();

            try {
                tm += FPS;
                Thread.sleep(Math.max(0, tm - System.currentTimeMillis()));
            }
            catch(InterruptedException e)
            {
            	System.out.println(e);
            }
        }
    }
	
    public void update(){
    	e1.update();
    }
    
	public void paint(Graphics g){
		if ( e1.isColliding(e2))
		{
			g.setColor(Color.BLUE);
		}
		else
		{
				g.setColor(Color.WHITE);
		}			
		g.fillRect(0, 0, getWidth(), getHeight());
		
		Iterator<Element> iterator = elements.iterator();
		
		while(iterator.hasNext())
			iterator.next().draw(g);
		
		
	}

	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT)
		      e1.xspeed+=-1;
		    else if (keyCode == KeyEvent.VK_RIGHT)
		      e1.xspeed+=1;
		    else if (keyCode == KeyEvent.VK_UP)
		      e1.yspeed+=-1;
		    else if (keyCode == KeyEvent.VK_DOWN)
		      e1.yspeed+=1;
	}

	public void keyReleased(KeyEvent arg0) {
//		e1.xspeed = 0;	
//		e1.yspeed = 0;
	}

	public void keyTyped(KeyEvent arg0) {
			
		}
}