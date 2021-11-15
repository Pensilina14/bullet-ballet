package it.unibo.pensilina14.bullet.ballet.model.weapon;

import it.unibo.pensilina14.bullet.ballet.common.Dimension2D;
import it.unibo.pensilina14.bullet.ballet.common.MutablePosition2D;
import it.unibo.pensilina14.bullet.ballet.model.effects.Effect;
import it.unibo.pensilina14.bullet.ballet.model.entities.AbstractStaticComponent;
import it.unibo.pensilina14.bullet.ballet.model.environment.Environment;

public class StaticPickUpItem extends AbstractStaticComponent implements Item{

    private final Items id;
    private final Effect effect;
    
    public StaticPickUpItem(final Dimension2D dimension, final MutablePosition2D position
            , final Environment gameEnvironment, final Items id,
            final Effect effect) {
        super(dimension, position, gameEnvironment);
        this.id = id;
        this.effect = effect;
    }

    @Override
    public Items getItemId() {
        return this.id;
    }

    @Override
    public Effect getEffect() {
        return this.effect;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((effect == null) ? 0 : effect.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StaticPickUpItem other = (StaticPickUpItem) obj;
        if (effect == null || id != other.id) {
            if (other.effect != null) {
                return false;
            }
        } else if (!effect.equals(other.effect)) {
            return false;
        }
        return true;
    }
    
}
