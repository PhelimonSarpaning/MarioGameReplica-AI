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
abstract public class Sprite 
{
	Model model;
	int x;
	int y;
	int w;
	int h;
	int fall;
	
	abstract void update();
	
   Sprite(Model m)
   {
	   model = m;
   }
	

	 abstract Json marshal();
	abstract Sprite cloneMe(Model m);
	 
	 
	 Boolean is()
	  {
		  return false;
	  }  
	  
	  
	  Boolean isCoinblocks()
	  {
		  return false;
	  }
	  Boolean hitblick()
	  {
		  return false;
	  }
	  Boolean isbrick()
	  {
		  return false;
	  }
	  Boolean ismario()
	  {
		  return false;
	  }
	  Boolean iscoin()
	  {
		  return false;
	  }
	abstract void draw(Graphics g);
	
}


