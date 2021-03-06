package it.aren.model.game;

import java.util.ArrayList;
import java.util.List;

import it.aren.common.BaseLevelEnum;
import it.aren.common.Constant;
import it.aren.common.BaseObjectEnum;
import it.aren.common.Point2D;
import it.aren.common.PointRange;
import it.aren.event.NullEvent;
import it.aren.file.SettingsLoader;
import it.aren.graphic.GraphicController;
import it.aren.graphic.component.BlockGraphicComponent;
import it.aren.graphic.component.GameMapGraphicComponent;
import it.aren.model.BaseEntity;

/**
 * Represent a map of game.
 *
 */
public class GameMap extends BaseEntity {
    private List<Block> blocks;
    private final BaseLevelEnum type;

    /**
     * Create a map.
     * @param type the {@link BaseLevelEnum} type of map
     */
    public GameMap(final BaseLevelEnum type) {
        super(new Point2D(), true, new GameMapGraphicComponent());
        this.blocks = new ArrayList<>();
        this.type = type;
        type.getPoints().forEach(p -> this.addBlocks(createLineBlock(p)));
    }

    /**
     * Create a range of aligned blocks.
     * @param range the {@link PointRange} with the point of start and the point of end
     * @return blocks the aligned blocks
     */
    private static List<Block> createLineBlock(final PointRange range) {
        final int ratio = SettingsLoader.loadSettings().scale();
        final int dimension = ratio * Constant.DEFAULT_HITBOX_DIMENSION;
        final List<Block> blocks = new ArrayList<>();
        for (int i = (int) range.getStart().getX(); i <= (int) range.getEnd().getX(); i++) {
            for (int j = (int) range.getStart().getY(); j <= (int) range.getEnd().getY(); j++) {
                blocks.add(createSimpleBlock(new Point2D(i * dimension, j * dimension)));
            }
        }
        return blocks;
    }

    /**
     * Create a new {@link Block}.
     * @param position the {@link Point2D} where the block is
     * @return the new {@link Block}
     */
    private static Block createSimpleBlock(final Point2D position) {
        return new Block(BaseObjectEnum.VOID, new NullEvent(), position,
                true, new BlockGraphicComponent());
    }

    /**
     * Add a {@link Block} in the map.
     * @param block the {@link Block} to add in the map
     */
    public final void addBlock(final Block block) {
        this.blocks.add(block);
    }

    /**
     * Add a {@link List} of {@link Block} in the map.
     * @param blocks the {@link List} of {@link Block} to add in the map
     */ 
    public void addBlocks(final List<Block> blocks) {
        this.blocks.addAll(blocks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateGraphic(final GraphicController g) {
        super.updateGraphic(g);
        this.blocks.forEach(b -> b.updateGraphic(g));
    }

    /**
     * Get GameMap's {@link List} of {@link Block}.
     * @return blocks the {@link List} of {@link Block}
     */
    public List<Block> getBlocks() {
        return this.blocks;
    }

    /**
     * Set GameMap's {@link List} of {@link Block}.
     * @param blocks the {@link List} of {@link Block} to set
     */
    public void setBlocks(final List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * Get the type of map.
     * @return {@link BaseLevelEnum} type of map
     */
    public final BaseLevelEnum getType() {
        return type;
    }

}
