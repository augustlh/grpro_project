import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DogTest {
    World world;
    @BeforeEach
    public void setUp() {
        world = new World(4) ;
    }

    @Test
    public void testDistanceToOwner() {
        Person person = new Person();
        Location location = new Location(0, 0);
        world.setTile(location, person);
        world.setCurrentLocation(location);

        for(int i = 0; i < 10000; i++) {
            world.setDay();
            person.act(world);

            if(i == 0) {
                person.addDog(world);
            }
            Location dogLocation = world.getLocation(person.getDog());
            Location playerLocation = world.getLocation(person);

            int dx = Math.abs(dogLocation.getX() - playerLocation.getX());
            int dy = Math.abs(dogLocation.getY() - playerLocation.getY());

            // Hvis forskellen i x-retningen og y-retningne er mindre eller lig med 1 , så
            // er hunden i et af otte felter ved siden af
            assertTrue(dx <= 1 && dy <= 1);
        }
    }
}
