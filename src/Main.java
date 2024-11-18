import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int size = 15;
        int delay = 500;
        int display_size = 800;

        Program p = new Program(size, display_size, delay);
        simulate(p);
    }

    private static void simulate(Program p) {
        World world = p.getWorld();
        initializeWorld(world);
        p.show();
        p.run();
    }

    private static void initializeWorld(World world) {
        if (world == null) {
            throw new NullPointerException("World is null");
        }

        int size = world.getSize();
        Random random = new Random();

        for(int i = 0; i < random.nextInt(size*size); i++) {
            Location place = new Location(random.nextInt(size), random.nextInt(size));

            while(!world.isTileEmpty(place)) {
                place = new Location(random.nextInt(size), random.nextInt(size));
            }

            world.setTile(place, new Person());
        }
    }
}