package it.unibo.pensilina14.bullet.ballet.model.weapon;
import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.characters.EntityList;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.PhysicalObject;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;


public class BulletImpl implements Bullet {

	private final String name;
	private final double damage;
	
	public BulletImpl(final EntityList.BulletType bulletType) {
		this.name = bulletType.description();
		this.damage = bulletType.damage();
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
    public ITEM_ID getItemId() {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public MutablePosition2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isCollidingWith(PhysicalObject other) {
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

}
