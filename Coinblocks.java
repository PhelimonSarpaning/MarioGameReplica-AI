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
class Coinblocks extends Sprite
{
	//Model model;
	static Image[] blocks;
	int numcol=0;
	
	Boolean isCoinblocks()
	  {
		  return true;
	  }
	
	
	Coinblocks(int xx, int yy, int ww, int hh, Model m)
	 {
		 
		super(m);
		x = xx;
	    y = yy;
	    w = ww;  
	    h = hh;
	    //type = "CoinBlock";

		
		
	    
	 }
	Coinblocks(Model m,Coinblocks cb)
	{
		super(m);
		this.h = cb.h;
		this.w = cb.w;
		this.x = cb.x;
		this.y = cb.y;
		this.numcol= cb.numcol;
		/*
		blocks = new Image[2];
		try
		{
	   System.out.println("cwd=" + System.getProperty("user.dir"));
	    blocks[0] = ImageIO.read(new File("block1.png"));
		blocks[1] = ImageIO.read(new File("block2.png"));
		
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);	
		}	
		
	  */  
		
	}
	Coinblocks cloneMe(Model m)
    {
   	 return new Coinblocks(m,this);
    }
	Json marshal()
	{
		Json ob = Json.newObject();
        ob.add("type","coinBlocks");
        ob.add("x", x);
		 ob.add("y", y);
		 ob.add("w", w);
		 ob.add("h", h);
        return ob;
	}
	Coinblocks(Json ob,Model m)
	 {
	
		super(m);
		model = m;
	        x = (int)ob.getLong("x");
			y = (int)ob.getLong("y");
			w = (int)ob.getLong("w");
			h = (int)ob.getLong("h");
	     //type = ob.getString("type");
			blocks = new Image[2];
			try
			{
		   System.out.println("cwd=" + System.getProperty("user.dir"));
		    blocks[0] = ImageIO.read(new File("block1.png"));
			blocks[1] = ImageIO.read(new File("block2.png"));
			
			} 
			catch(Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);	
			}	 
	 }	 
	 void update()
	 {
		 
	 }
	 void draw(Graphics g)
		{
	     // Brick b = Brick.get(i);
		if(blocks == null)
		{
		 blocks = new Image[2];
		
		 try
			{
		   System.out.println("cwd=" + System.getProperty("user.dir"));
		    blocks[0] = ImageIO.read(new File("block1.png"));
			blocks[1] = ImageIO.read(new File("block2.png"));
			
			} 
			catch(Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);	
			}	
		} 
		 
		 if (numcol <= 4)
		{		

			 //System.out.print(x);
			// System.out.print(model.scrollPos);
			// System.out.print(y);
			 //System.out.print(w);
			 //System.out.print(h);
			
			 g.drawImage(blocks[0], x - model.scrollPos, y,w, h ,null);
		}
		else
		{
			
			 g.drawImage(blocks[1], x - model.scrollPos, y,w, h ,null);	
		}
		}
		 
		
}
