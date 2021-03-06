package it.aren.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.aren.common.BaseObjectEnum;
import it.aren.common.Point2D;
import it.aren.graphic.component.GameObjectGraphicComponent;
import it.aren.model.GameState;
import it.aren.model.game.Block;
import it.aren.model.game.GameObject;

class EventsTest {

    @Test void testGiveToPlayerEvent() {
        final GameState gameState = new GameState(eventListener -> { });
        final GameObject go = new GameObject(BaseObjectEnum.BOX, new Point2D(), false, new GameObjectGraphicComponent());
        final GameObject go2 = new GameObject(BaseObjectEnum.BOX, new Point2D(), false, new GameObjectGraphicComponent());
        final BaseEvent ev = new InteractWithPlayerEvent(go, "Prova");
        final BaseEvent evRequirement = new InteractWithPlayerEvent(go2, "Prova requisiti", go, "Requisiti non rispettati");
        final Block blk = new Block(BaseObjectEnum.VOID, evRequirement, new Point2D(), false, null);
        final Block blkReq = new Block(BaseObjectEnum.VOID, ev, new Point2D(), false, null);
        final List<BaseEvent> listEvent = new ArrayList<>();



        gameState.getWorld().getCurrentMap().addBlock(blk);
        gameState.getWorld().getCurrentMap().addBlock(blkReq);

        listEvent.add(ev);
        listEvent.add(evRequirement);
        listEvent.forEach(e -> System.out.println(e));
        listEvent.forEach(e -> e.launch(gameState));
        final List<GameObject> listGo = new ArrayList<>();
        listGo.add(go);
        listGo.add(go2);
        System.out.println(gameState.getWorld().getPlayer().getBackPack());
        System.out.println(go);
        assertEquals(gameState.getWorld().getPlayer().getBackPack(), listGo);
    }
}
