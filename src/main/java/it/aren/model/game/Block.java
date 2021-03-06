package it.aren.model.game;

import it.aren.common.Constant;
import it.aren.common.BaseObjectEnum;
import it.aren.common.Point2D;
import it.aren.event.BaseEvent;
import it.aren.file.SettingsLoader;
import it.aren.graphic.GraphicComponent;

/**
 * Model for every block.
 * Extends {@link BaseEntity}
 */
public class Block extends GameObject {

    private BaseEvent event;
    /**
     * Create a Block.
     * @param type the {@link BaseObjectEnum} type of block
     * @param event the {@link BaseEvent} to set in the block
     * @param position the {@link Point2D} where the block is
     * @param drawable set if the block will be drawn
     * @param graphic for draw the block
     */
    public Block(final BaseObjectEnum type, final BaseEvent event, final Point2D position, final boolean drawable,
            final GraphicComponent graphic) {
        super(type, position, drawable, graphic);
        final int ratio = SettingsLoader.loadSettings().scale();
        this.event = event;
        this.getHitBox().setSize(ratio * Constant.DEFAULT_HITBOX_DIMENSION,
                ratio * Constant.DEFAULT_HITBOX_DIMENSION / 2);
    }

    /**
     * Get the block's {@link BaseEvent}.
     * @return the event
     */
    public BaseEvent getEvent() {
        return this.event;
    }

    /**
     * Set the block's {@link BaseEvent}.
     * @param event the event to set
     */
    public void setEvent(final BaseEvent event) {
        this.event = event;
    }
}
