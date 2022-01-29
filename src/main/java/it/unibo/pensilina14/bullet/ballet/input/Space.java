package it.unibo.pensilina14.bullet.ballet.input;

import java.io.IOException;

import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2Dimpl;
import it.unibo.pensilina14.bullet.ballet.common.SpeedVector2DImpl;
import it.unibo.pensilina14.bullet.ballet.graphics.scenes.GameView;
import it.unibo.pensilina14.bullet.ballet.model.environment.GameState;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Bullet;
import it.unibo.pensilina14.bullet.ballet.model.weapon.BulletFactoryImpl;
import it.unibo.pensilina14.bullet.ballet.model.weapon.Weapon;

public class Space implements Command {
	
	private final GameView mapScene;

	public Space(final GameView mapScene) {
		this.mapScene = mapScene;
	}
	
	@Override
	public void execute(final GameState env) {
		if (env.getGameEnvironment().getEntityManager().getPlayer().get().hasWeapon()) {
			final Weapon weapon = env.getGameEnvironment().getEntityManager().getPlayer().get().getWeapon();
    		if (env.getGameEnvironment().getEntityManager().getPlayer().get().getWeapon().hasAmmo()) {
    			final Bullet bullet = new BulletFactoryImpl().createClassicBullet(env.getGameEnvironment(),
    					new SpeedVector2DImpl(new MutablePosition2Dimpl(weapon.getPosition().get().getX(), 
    							weapon.getPosition().get().getY()), 1.0));
    			env.getGameEnvironment().getEntityManager().addBullet(bullet);
    			weapon.decreaseAmmo();
    			try {
					this.mapScene.generateBullet(bullet.getPosition().get());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
		}
	}
}
