
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
enum Action
{
	run, 
	jump //jump_and_run
}
class Controller implements ActionListener, MouseListener, KeyListener 
{ 
    View view;
    Model model;
   
    boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;  
	boolean jump;
	int mouseDownX;
	int mouseDownY;
//key move
public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_S: model.save("map.json"); break;
			case KeyEvent.VK_L: model.load("map.json"); break;
			case KeyEvent.VK_SPACE: jump = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: jump = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void Update()
	{
		
		
		// Evaluate each possible action
		double score_run = model.evaluateAction(Action.run, 0);
		double score_jump = model.evaluateAction(Action.jump, 0);
	
			if(score_jump > score_run)
			model.do_action(Action.jump);
		else
			model.do_action(Action.run);


		
		
		if(keyRight)
		{	
		
			model.mario.x += 9;
		}
		if(keyLeft) 
		{
			model.mario.x -= 9;
		}
		if(jump)
		{
		
		    if(model.mario.num_frame < 5)
		    //	model.mario.mariojumped++;
			model.mario.fall = -40;
		}
		
		
	}




//mouse move
public void mousePressed(MouseEvent e)
	{
	//model.setDestination(e.getX(), e.getY());
	mouseDownX = e.getX();
	mouseDownY = e.getY();
	
	}

	public void mouseReleased(MouseEvent e)  
	{  
	 int x1 = mouseDownX;
	 int x2 = e.getX();
	 int y1 = mouseDownY;
	 int y2 = e.getY();
	 int left = Math.min(x1, x2);
	 int right = Math.max(x1, x2);
	 int top = Math.min(y1, y2);
	 int bottom = Math.max(y1, y2);
     model.addBrick(left + model.scrollPos, top, right - left, bottom - top);
    
	}
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e)
	{   
		int x;
		int y;
		x = e.getX();
		y = e.getY();
          
        
      model.addcoinblock(x + model.scrollPos,y, 89, 83);
		if(e.getY() < 100)
		{
			System.out.println("break here");
		}
		
	}



	

	public void actionPerformed(ActionEvent e)
	{
		view.removeButton();
	}
     void setView(View v)
	{
		view = v;
          
	}
Controller(Model m)
	{
		model = m;
	}




}