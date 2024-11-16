import itumulator.world.Location;
import itumulator.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(location, w.getLocation(p),"The last person should not have moved as there are no valid moves.");
    }



}
