package it.unibo.pensilina14.bullet.ballet.input;

import java.io.IOException;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

public class Space implements Command {
	
	private final static int PLATFORM_SIZE = 60;
	private final GameView mapScene;

	public Space(final GameView mapScene) {
		this.mapScene = mapScene;
	}
	
	@Override
	public void execute(final GameState env) {
		if (env.getGameEnvironment().getPlayer().get().hasWeapon()) {
			final Weapon weapon = env.getGameEnvironment().getPlayer().get().getWeapon();
    		if(env.getGameEnvironment().getPlayer().get().getWeapon().hasAmmo()) {
    			env.getGameEnvironment().addBullet(new BulletFactoryImpl().createClassicBullet(env.getGameEnvironment()
    					, new SpeedVector2DImpl(new MutablePosition2Dimpl(weapon.getPosition().get().getX() * PLATFORM_SIZE
    							, weapon.getPosition().get().getY() * PLATFORM_SIZE), 1.0)));
    			weapon.decreaseAmmo();
    			try {
					this.mapScene.generateBullet();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		}
		}
		
	}

}
