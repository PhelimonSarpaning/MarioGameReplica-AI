import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Image;



class View extends JPanel
{

      Model model;
	JButton b1;
	//Model sprite;
	static Image background;
	View(Controller c,Model m)
	
	{
		
		model = m;
		try
		{
	  
	    background = ImageIO.read(new File("mario10.jpg"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);	
		}	
	}	
		
		
	
	
	

void removeButton()
	{
		this.remove(b1);
		this.repaint();
	}
public void paintComponent(Graphics g)
	{
		
	//model = new Model();
			//clear the screen
			g.setColor(new Color(128, 255, 255));
			//g.drawImage(mario_images[0], this.getWidth(), this.getHeight(), null);
			
			g.drawImage(background, 0, 0, null);
			//g.fillRect(0, 0, this.getWidth(), this.getHeight());

			//
			//draw the ground;
			g.setColor(new Color(153,102,0));
				g.fillRect(0, 395, 10000, 395);
			
			g.setColor(new Color(0, 0, 0));
		for(int i = 0; i < model.sprite.size(); i++)
	     {
			Sprite b = model.sprite.get(i);
			b.draw(g);
			
	     }
	
	}

}
