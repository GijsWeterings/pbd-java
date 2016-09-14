package nl.gijsweterings;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gijs on 14-9-16.
 */
public class TileTest {

    @Test
    public void lock() throws Exception {
        Tile tile = new Tile(1);
        assertFalse(tile.isLocked());
        tile.lock();
        assertTrue(tile.isLocked());
    }

    @Test
    public void getValue() throws Exception {
        Tile tile = new Tile(1);
        assertEquals(1, tile.getValue().intValue());
    }

    @Test
    public void isSameValue() throws Exception {
        Tile tile = new Tile(1);
        Tile tile2 = new Tile(1);
        assertTrue(tile.isSameValue(tile2));
    }

}