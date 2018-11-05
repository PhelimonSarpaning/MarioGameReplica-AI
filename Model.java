import java.util.ArrayList;




 class Model
 {
	
	ArrayList<Sprite> sprite;
	int scrollPos;
	Mario mario;
	
	
   Model()
	{
	   mario = new Mario(this);
	sprite = new ArrayList<Sprite>();
	sprite.add(mario);
	load("map.json");
	}
   Model(Model old_model)
   {
	   this.scrollPos= old_model.scrollPos;
	  sprite = new ArrayList<Sprite>();
	  for(int i = 0; i <old_model.sprite.size(); i++)
	  {
		  Sprite copyspr = old_model.sprite.get(i).cloneMe(this);
		  sprite.add(copyspr);
	   if(copyspr.ismario())
			 mario =(Mario)copyspr; 
	  }
	  //System.out.println("break here");
   }
   public void update()
	{
	   //mario.prev_frame();
      for (int i = 0; i < sprite.size(); i++)
      {
    	  Sprite s =sprite.get(i);
    	  s.update();
      }
	}
 
   void addBrick(int x, int y, int w, int h)
	{
		Brick b = new Brick(x, y, w, h,this);
		sprite.add(b);
	}
   void addcoinblock(int x, int y, int w, int h)
	{
	 Coinblocks c = new Coinblocks(x,y,w,h,this);
	 sprite.add(c);
	}
   
	 void unmarshall(Json ob)
 {
	   sprite.clear();
	  Json json_sprite = ob.get("sprites");
        for(int i = 0; i < json_sprite.size(); i++)
	    {
		   Json s = json_sprite.get(i);
		   String ty = s.getString("type");
		   if (ty.equals("Brick"))
		   {
			   Brick b = new Brick(s,this);
			   sprite.add(b);
		   }
		   if (ty.equals("Mario"))
		   {
			   Mario m = new Mario(s,this);
			   sprite.add(m); 
			mario = m; 
		   }
		   if (ty.equals("Coinblocks"))
		   {
			   Coinblocks c = new Coinblocks(s,this);
			   sprite.add(c); 
			   
		   }
		   if (ty.equals("coins"))
		   {
			   Coins co = new Coins(s,this);
			   sprite.add(co); 
			   
		   }
		   
	    }
	 
 }	 
 Json marshal()
   {
	   
         Json ob = Json.newObject();
		 
		 Json json_sprite = Json.newList();
		 ob.add("sprite", json_sprite);
		 for(int i = 0; i < sprite.size(); i++)
		 {
			Sprite s = sprite.get(i);
			Json j = s.marshal();
			json_sprite.add(j);
         }
         return ob;
    
 }
	void save(String filename)
	{
		Json ob = marshal();
		ob.save(filename);
		
	}
   void load(String filename)
   {
   Json ob = Json.load(filename);
   unmarshall(ob);
   }
   void do_action(Action a)
   {
	  if (a == Action.run )
	  {
		  mario.x += 9;
	  }
	  if (a == Action.jump)
	  {
	      mario.jumpCount++;
		 mario.fall = -40;  
		 waitSmall();
	  }
	  
	  
	/*
	  if (a == Action.jump_and_run)
	  {
		  mario.jumpCount++;
		  mario.fall = -40;
		  mario.x += 9;
	  }
	  */
   }
   public void waitSmall() {
	   mario.x-=20;
		  //return true;
		  
	  }
   
   double evaluateAction(Action action, int depth)
   {
   	// Evaluate the state
   	if(depth >= 20)
   	{
   		//System.out.println(mario.x);
   	//	System.out.println(mario.coins);
   		//System.out.println(mario.jumpCount);
   		return mario.x + 50000 * mario.coins - 2 * mario.jumpCount;
   		
   	}
   	// Simulate the action
   	Model copy = new Model(this); // uses the copy constructor
   	copy.do_action(action); // like what Controller.update did before
   	copy.update(); // advance simulated time

   	// Recurse
   	if(depth % 6 != 0)
   	   return copy.evaluateAction(action, depth + 1);
   	else
   	{
   	   double best = copy.evaluateAction(Action.run, depth + 1);
   	   best = Math.max(best, copy.evaluateAction(Action.jump, depth + 1));
   	  // best = Math.max(best, copy.evaluateAction(Action.jump_and_run, depth + 1));
   	   return best;
   	}
   }
   

	
}
