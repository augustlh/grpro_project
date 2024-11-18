import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider;
import itumulator.simulator.Actor;
import itumulator.world.Location;
import itumulator.world.World;

import java.awt.*;
import java.util.Random;

public class Dog implements Actor, DynamicDisplayInformationProvider {
    Person owner;

    public Dog(Person owner) {
        if(owner == null) {
           throw new IllegalArgumentException("owner is null");
        }

        this.owner = owner;
    }
    @Override
    public DisplayInformation getInformation() {
        return new DisplayInformation(Color.blue, "wolf-small");
    }

    @Override
    public void act(World world) {
        Random random = new Random();
        if(random.nextDouble() < 0.10) {
            System.out.println("woof");
        }
    }

    public void move(World world, Location to) {
        if(world == null || to == null) {
            throw new IllegalArgumentException("world or location is null");
        }
        world.move(this, to);
    }
}
