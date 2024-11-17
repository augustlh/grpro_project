import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider;
import itumulator.simulator.Actor;
import itumulator.world.Location;
import itumulator.world.World;

import java.awt.Color;
import java.util.Random;
import java.util.Set;

public class Person implements Actor, DynamicDisplayInformationProvider {
    private Location previousLocation;
    private Dog dog;

    public Person() {
        super();
        previousLocation = null;
        dog = null;
    }

    @Override
    public DisplayInformation getInformation() {
        return new DisplayInformation(Color.black, "rabbit-large");
    }

    @Override
    public void act(World world) {

        if(world.isNight()) {
            world.delete(this);
            if(dog != null) world.delete(dog);
            return;
        }

        Set<Location> emptyNeighbours = world.getEmptySurroundingTiles();

        if(emptyNeighbours.isEmpty()) {
            return;
        }
        this.previousLocation = world.getLocation(this);
        world.move(this, (Location) emptyNeighbours.toArray()[new Random().nextInt(emptyNeighbours.size())]);

        if(dog != null) {
            dog.move(world, this.previousLocation);
        } else {
            acquireDog(world);
        }

    }

    private void acquireDog(World world) {
        if(dog != null) return;
        if(new Random().nextDouble() < 0.15){
            this.dog = new Dog(this);
            world.setTile(this.previousLocation, this.dog);
        }
    }

    public void addDog(World world) {
        if(dog != null) return;
        if(this.previousLocation == null) return;

        this.dog = new Dog(this);
        world.setTile(this.previousLocation, this.dog);
    }

    public Dog getDog() {
        return this.dog;
    }
}
