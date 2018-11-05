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
class Coins extends Sprite
{
	Coins coins;
	//Model model;
	static Image coin;
	int hori_vel;
	int vert_velocity;
	Boolean iscoin()
	  {
		  return true;
	  }
	Coins(int xx, int yy,Model m)
	{
		super(m);
		model = m;
		x = xx;
		y =yy;
		Random r = new Random();
		 hori_vel = r.nextInt() % 16;
		vert_velocity = 10;	
	
	}
	Coins(Model m, Coins coin)
	{
		super(m);
		this.x=coin.x;
		this.y=coin.y;
		this.vert_velocity= coin.vert_velocity;
		this.hori_vel = coin.hori_vel;
	
	}
	Coins cloneMe(Model m)
    {
   	 return new Coins(m,this);
    }
	void update()
	{
		
		y += vert_velocity ;
		x += hori_vel;
		
		if (y > 300)
		{
			
		    x = 9000;
			y = 300;
		}
		//popcoin();	
			
		//System.out.println("am here");	
	}
	void popcoin()
	{
		
	
	}
	

	Json marshal()
	{
		Json ob = Json.newObject();
        ob.add("type","coins");
        ob.add("x", x);
		 ob.add("y", y);
		
        return ob;
	}
	Coins(Json ob,Model m)
	 {
		 super(m);
		 model = m;
	        x = (int)ob.getLong("x");
			y = (int)ob.getLong("y");
			
	     	 
	 }	 
	void draw(Graphics g)
	{
     // Brick b = Brick.get(i);
		if (coin == null)
		{
		try
		{
	   System.out.println("cwd=" + System.getProperty("user.dir"));
	  
	    coin = ImageIO.read(new File("bitcoin.png"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);	
		}
		}
    g.drawImage(coin, x- model.scrollPos, y,null);
    //System.out.println("am here");
	}
}
	
