import java.util.ArrayList;
import java.util.Iterator;
import java.util.Iterator;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Image;
import java.util.Random;
class Brick extends Sprite
{
	Model model;
//	ArrayList<Brick> brick;	
	
	Brick(int xx, int yy, int ww, int hh,Model m)
	 {
		super(m);
		model = m;
		x = xx;
	    y = yy;
	    w = ww;
	    h = hh;
	 }
	
	Brick(Brick brick, Model m)
	{
		super(m);
		this.x = brick.x;
		this.y = brick.y;
		this.w = brick.w;
		this.h = brick.h;
	
	}
	Brick cloneMe(Model m)
    {
   	 return new Brick(this,m);
    }  
	
	 Brick(Json ob,Model m)
	 {
		 super(m);
		 model = m;
	        x = (int)ob.getLong("x");
			y = (int)ob.getLong("y");
			w = (int)ob.getLong("w");
			h = (int)ob.getLong("h");
	     
		 
	 }	 
	 Json marshal()
	   {
	         Json ob = Json.newObject();
	         ob.add("type","Bricks");
	         ob.add("x", x);
			 ob.add("y", y);
			 ob.add("w", w);
			 ob.add("h", h);
	         return ob;
	    
	 }
	
	
	 void update()
	 {
		 
	 }
	 Boolean isbrick()
	  {
		  return true;
	  }
	
	 void draw(Graphics g)
		{
	     // Brick b = Brick.get(i);
             
		 g.setColor(new Color(153,102,0));
	      g.drawRect(x - model.scrollPos, y, w, h);
	    
	      g.fillRect(x - model.scrollPos, y, w, h);
	      //g.setColor(new Color(100, 125, 55));
		}  
	      
	      
	       
      
}