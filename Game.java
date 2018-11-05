
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
  Model model;
 Mario mario;
Controller controller;
View view;
	public Game()
	{
		//mario= new Mario();
		model = new Model();
		controller = new Controller(model);
		view = new View(controller,model);
		this.setTitle("mario!");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
         view.addMouseListener(controller);
        this.addKeyListener(controller);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
                
	}

	
public void run()
{
	
	
	while(true)
	{
		
		controller.Update();
		model.update();
		view.repaint(); // Indirectly calls View.paintComponent
		Toolkit.getDefaultToolkit().sync(); // Updates screen
		
		// Go to sleep for 50 miliseconds
		
		try
		{
			Thread.sleep(50);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
}

public static void main(String[] args)
	{
	
	
		Game g = new Game();
             g.run();
	}
}



