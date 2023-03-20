package projectile;

import main.GamePanel;

public class PROJ_Fireball extends Projectile{

	public PROJ_Fireball(GamePanel gPanel) {
		super(gPanel);
		
		name = "Fireball";
		speed = 3;
		maxLife = 70;			// time it fly
		life = maxLife;
		attack = 1;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		idle0 = setupImage("/projectile/fireball_up_1");
		idle1 = setupImage("/projectile/fireball_up_2");
		run0 = setupImage("/projectile/fireball_down_1");
		run1 = setupImage("/projectile/fireball_down_2");
		run2 = setupImage("/projectile/fireball_left_1");
		run3 = setupImage("/projectile/fireball_left_2");
		run4 = setupImage("/projectile/fireball_right_1");
		run5 = setupImage("/projectile/fireball_right_2");
		
	}
	
	

}
