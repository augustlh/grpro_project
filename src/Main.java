import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int size = 3;
        int delay = 100;
        int display_size = 800;

        Program p = new Program(size, display_size, delay);
        simulate(p);
    }

    private static void simulate(Program p) {
        World world = p.getWorld();
        initalizeWorld(world);
        p.show();
        p.run();
    }


    private static void initalizeWorld(World world) {
        Person person = new Person();
        Location location = new Location(0, 0);
        world.setTile(location, person);
    }
}