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
class Mario extends Sprite 
{
	int coins;
	int num_frame;
  //   Mario mario;
    int jumpCount;
     Sprite s = null;   
	static Image[] mario_images;
	//Model model;
	int prev_x;
	int prev_y;
	Boolean hit_ground = true;
	  
	Boolean ismario()
	  {
		  return true;
	  }
	
	Mario(Model m)
	{
		super(m);
		model = m;
		jumpCount = 0;
		num_frame = 0;
		 w = 60;
		 h = 95;
		 coins = 0;
	
	if(mario_images == null)
	{

	mario_images = new Image[5];
	try
	{
   
    mario_images[0] = ImageIO.read(new File("mario1.png"));
	mario_images[1] = ImageIO.read(new File("mario2.png"));
	mario_images[2] = ImageIO.read(new File("mario3.png"));
	mario_images[3] = ImageIO.read(new File("mario4.png"));
	mario_images[4] = ImageIO.read(new File("mario5.png"));	
	} 
	catch(Exception e) {
		e.printStackTrace(System.err);
		//System.out.println("nyinyinyi");
		System.exit(1);	
	}	
	}
	}
	
	Mario(Mario mario,Model m)
	{
		super(m);
	this.x = mario.x;
	this.y = mario.y;
	this.num_frame = mario.num_frame;
	this.fall= mario.fall;
	this.h= mario.h;
	this.w= mario.w;
	this.prev_x = mario.prev_x;
	this.prev_y = mario.prev_y;
	this.hit_ground= mario.hit_ground;
	this.jumpCount= mario.jumpCount;
	
	if(mario.coins > 0)
		//System.out.println("i foudn a coin");
	this.coins = mario.coins;
	
	}
     Mario cloneMe(Model m)
     {
    	 return new Mario(this,m);
     }
	
	
	boolean collision(int _x,int _y, int _w, int _h)
	{
		if (x + w <= _x)
			return false;
		if (x >= _x + _w)
			return false;
		if (y + h <= _y)
			return false;
		if (y >= _y + _h)
	      		return false;
		return true;
	}
	void prev_frame()
	{
		prev_x = x;
		prev_y = y;	
		
	}
	// for the brick collision
   void get_out(int _x, int _y, int _w, int _h)
	{  
	 
	  if(x + w >= _x  && prev_x + w < _x )
		{ 
		//  System.out.println("Hello");
	     x =_x - w - 1 ; 
		}
	  if(x <= _x + _w  && prev_x >_x + _w  )
	  {
		  x = _x + _w + 1;
	  }
	  if(y + h >= _y && prev_y + h < _y)
	  {
		 y = _y - h -1;
		num_frame = 0;
		  fall = 0;
	  }
	  if(y <= _y + _h && prev_y > _y + _h)
	  {
		  y = _y + _h +1; 
		  fall = 0;  
		  hit_ground = true;
	  }
           
	}
	// for the coin block collision
   void get_outblock(int _x, int _y, int _w, int _h)
	{  
	  
			
	  if(x + w >= _x  && prev_x + w < _x )
		{ 
		//  System.out.println("Hello");
	     x =_x - w - 1 ; 
		}
	  if(x <= _x + _w  && prev_x >_x + _w  )
	  {
		  x = _x + _w + 1;
	  }
	  if(y + h >= _y && prev_y + h < _y)
	  {
		 y = _y - h -1;
		num_frame = 0;
		  fall = 0;
	  }
	  if(y <= _y + _h && prev_y > _y + _h)
	  {
		  y = _y + _h +1; 
		  fall = 0; 
	//	  addcoin(_x, _y);
		 
     }
	}
   
  void update()
	
	{
	//  popcoin();
	  model.scrollPos = x - 180;
		fall += 3;
		y += fall ;
	//	System.out.println(mariojumped);
		if (y > 300)
		{
			hit_ground = true;
			y = 300;
			num_frame = 0; 
		}
		
     for (int i = 0; i < model.sprite.size(); i++)	
     //Iterator<Sprite> it = model.sprite.iterator();	
			{
    	        
				Sprite b = model.sprite.get(i);
				if(b.isbrick())
				{
			     if(collision(b.x, b.y, b.w, b.h))
					{
						//System.out.println("Hello");
			        	get_out(b.x, b.y, b.w, b.h);
					}
			    }
				if (b.isCoinblocks())
				{
					
					Coinblocks cb = (Coinblocks)b;
				 if(collision(b.x, b.y, b.w, b.h))
					{
					 
					 if(y <= b.y + b.h && prev_y > b.y + b.h)
					  {
						// System.out.println("am in the coinblock function");
							 y = b.y + b.h +1; 
							 fall = 0; 
						  
						  if(cb.numcol <= 4 && hit_ground)
						  {
						  addcoin(b.x, b.y);
						  hit_ground = false;
						  cb.numcol++;
						  coins++;
						  System.out.println(coins);
						  }  	  
						  
				     }
					}//System.out.println("Hello");
					// get_outblock(b.x, b.y, b.w, b.h);
				//	   it.add(addcoin(b.x,b.y));
					 

					// crecoin =true;
					 
					}
				}
					fall ++;
					num_frame ++;
					 prev_frame();	
			}
 
	
  
	void addcoin(int x, int y)
	{
		
		Coins c = new Coins(x,y,model);	
		model.sprite.add(c);
		Coins cn = (Coins)c;
		
	}
	Json marshal()
	   {
	         Json ob = Json.newObject();
	         ob.add("type","Mario");
	         ob.add("x", x);
			 ob.add("y", y);
			 ob.add("w", w);
			 ob.add("h", h);
			 ob.add("fall",fall);
			 
	         return ob;
	    
	 }
	 Mario(Json ob,Model m)
	 {
		 
		   super(m);
		   model = m;
	        x = (int)ob.getLong("x");
			y = (int)ob.getLong("y");
			w = (int)ob.getLong("w");
			h = (int)ob.getLong("h");
		 
	 }	 
	void draw(Graphics g)
	{
		
		g.drawImage(mario_images[Math.abs(x/15) % 5], x - model.scrollPos, y, null);
	}
	
	
}