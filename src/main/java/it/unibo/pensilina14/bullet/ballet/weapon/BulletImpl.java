package it.unibo.pensilina14.bullet.ballet.weapon;
import it.unibo.pensilina14.bullet.ballet.game.effects.Effect;


public class BulletImpl implements Bullet {

	private final String name;
	private final double damage;
	
	public BulletImpl(BulletType bulletType) {
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

}
