import itumulator.world.Location;
import itumulator.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    World w;
    @BeforeEach
    public void setUp() {
        this.w = new World(2);
    }

    @Test
    public void testNoValidMoves() {
        Person p = new Person();
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();

        Location location = new Location(0,0);
        Location location1 = new Location(0,1);
        Location location2 = new Location(1,0);
        Location location3 = new Location(1,1);

        w.setCurrentLocation(location);

        w.setTile(location, p);
        w.setTile(location1, p1);
        w.setTile(location2, p2);
        w.setTile(location3, p3);


        p.act(w);

        assertEquals(location, w.getLocation(p));
    }

    @Test
    public void personMovesDuringDay(){
        Person p = new Person();
        Location l = new Location(0,0);
        w.setDay();
        w.setCurrentLocation(l);
        w.setTile(l, p);

        p.act(w); // vi eksekverer ’act’ metoden

        if(p.getDog() != null) {
            w.delete(p.getDog());
        }

        Location n = w.getLocation(p);
        assertNull(w.getTile(l));
        assertNotNull(n);
        assertNotEquals(l, n);
    }

    @Test
    public void testDogProbability() {
        int NUM_PEOPLE = 100000;
        int NUM_DOGS = 0;
        for(int i = 0; i < NUM_PEOPLE; i++) {
            Person p = new Person();
            Location location = new Location(0,0);
            w.setTile(location, p);
            w.setCurrentLocation(location);
            p.act(w);

            if(p.getDog() != null) {
                NUM_DOGS++;
                w.delete(p.getDog());
            }
            w.delete(p);
        }

        double result = NUM_DOGS / (NUM_PEOPLE * 1.0);
        assertTrue(Math.abs(0.15 - result) <= 0.015);
    }

}
