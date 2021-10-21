package it.unibo.pensilina14.bullet.ballet.model.weapon;
import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;


public class BulletImpl implements Bullet {

	private final String name;
	private double damage;
	private boolean fired;
	
	public BulletImpl(final EntityList.BulletType bulletType) {
		this.name = bulletType.description();
		this.damage = bulletType.damage();
		this.fired = false;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getDamage() {
		return this.damage;
	}

	@Override
	public Effect getEffect() {
		return null;
	}

    @Override
    public Items getItemId() {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public MutablePosition2D getPosition() {
		return this.getPosition();
	}

	@Override
	public Boolean isCollidingWith(final PhysicalObject other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension2D getDimension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Environment getGameEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDamage(final double factor) {
		this.damage *= factor;
	}
	
	@Override
	public void fire() {
		this.fired = true;
	}
	
	@Override
	public boolean isShot() {
		return this.fired;
	}

}