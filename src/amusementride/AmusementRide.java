package amusementride;

import java.util.*;

/*
 * AmusementRide is a generalization of ride objects
 * found at amusement parks.
 * 
 * Note: The non-public fields for this class are 
 * defined at the bottom of the class.
 *
 * @creator DeAndre Sellers
 * @created 02018.10.02
 * @deasells
 */

public abstract class AmusementRide extends Object {

    public final static String DFLT_NAME = "Ride With No Name";
    public final static double DFLT_PRICE = 0.0;
    public final static int DFLT_CAPACITY = 0;

    /**
     * Construct an AmusementRide object.
     *
     * @param n the name of the AmusementRide
     * @param p the price of the AmusementRide
     * @param c the capacity of the AmusementRide
     */
    public AmusementRide(String n, double p, int c) {
        name = (n == null) ? DFLT_NAME : n;
        price = (p < 0) ? DFLT_PRICE : p;
        capacity = (c < 0) ? DFLT_CAPACITY : c;
    }

    /**
     * Print a message indicating this ride has started.
     *
     * @return true
     */
    public abstract boolean start();

    /**
     * Print a message indicating this ride has stopped.
     *
     * @return true
     */
    public abstract boolean stop();

    /**
     * Print a message indicating this ride has been loaded.
     *
     * @return this ride's capacity
     */
    public abstract int load();

    /**
     * Print a message indicating this ride as been repaired.
     */
    public void repair() {
        System.out.println("mechanics are on strike");
    }

    /**
     * Set the capacity of this AmusementRide.
     *
     * @param maxload maximum number of riders allowed
     * @return this AmusementRide
     */
    public AmusementRide setCapacity(int maxload) {
        capacity = (maxload < 0) ? DFLT_CAPACITY : maxload;
        return this;
    }

    /**
     * Set the price of this AmusementRide.
     *
     * @param price is USD
     */
    public AmusementRide setPrice(double p) {
        price = (p < 0) ? DFLT_PRICE : p;
        return this;
    }

    /**
     * construct a String representation of this ride...
     *
     * @return a String representation of this AmusementRide
     */
    public String toString() {
        return "\"" + name + "\" has capacity of " + capacity
                + " and costs $" + price;
    }

    /**
     * main() method used has a driver program to test the methods defined in
     * this class...
     */
    public static void main(String[] argv) {

        AmusementRide[] rides = {
            new FerrisWheel("The Billy Preston", 50, 6.28, 100),
            new RollerCoaster("for(;;) Young", 32, 3.14, 25, 99, 1.618),
            new BumperCars("Crazy Bumper", 17, 6.28, 13, 8, 3),};

        for (int i = 0; i < rides.length; i++) {
            System.out.println(rides[i]);
            rides[i].load();
            if (rides[i].start()) {
                rides[i].stop();
            } else {
                rides[i].repair();
            }
        }
    }

    /*
    * The windSpeed is the same for all AmusementRide objects; 
    * therefore, class variable used instead of an instance variable.
     */
    private static int windSpeed;

    /*
    * The instance data for an AmusementRide.
     */
    protected int capacity;
    protected String name;
    private double price;
    private Date lastRepair = new Date();
}

class FerrisWheel extends AmusementRide {

    /**
     * initialize this object using client supplied data...
     */
    public FerrisWheel(String name, int capacity, double price, int height) {
        super(name, price, capacity);
        if (height <= 0) {
            throw new IllegalArgumentException("invalid height");
        }
        this.height = height;
    }

    /**
     * Return a string representation of this object including the state of the
     * AmusementRide part of the object.
     */
    public String toString() {
        return "Ferris wheel " + super.toString() + "\n"
                + "...height: " + height + "; # of spins: " + nSpins;
    }

    /**
     * Spin this ferris wheel once.
     */
    public void spin() {
        nSpins++;
    }

    public boolean start() {
        return true;
    }

    public boolean stop() {
        return true;
    }

    public int load() {
        return capacity;
    }

    /*
    * default state values...
     */
    private final static int DFLT_MAX_SPINS = 100;
    private final static int DFLT_HEIGHT = -1;

    /*
    * instance variables...
     */
    private int nSpins = 0;
    private int height = DFLT_HEIGHT;    //in feet

}

class RollerCoaster extends AmusementRide {

    /**
     */
    public RollerCoaster(String name, int capacity, double price,
            int len, int speed, double factor) {
        super(name, price, capacity);
        init(len, speed, factor);
    }

    private void init(int len, int speed, double factor) {
        if (len < 0) {
            throw new IllegalArgumentException("invalid length");
        }
        trackLength = len;
        if (!setMaxSpeed(speed)) {
            throw new IllegalArgumentException("invalid speed");
        }
        if (factor < 0) {
            throw new IllegalArgumentException("invalid factor");
        }
        whiplashFactor = factor;
    }

    /**
     * maxSpeed is not changed if parameter speed is negative
     *
     * @param speed new speed
     * @return true if speed changed; false otherwise
     */
    public boolean setMaxSpeed(int speed) {
        if (speed < 0) {
            return false;
        }
        maxSpeed = speed;
        return true;
    }

    /**
     * Return a string representation of this object including the state of the
     * AmusementRide part of the object.
     */
    public String toString() {
        final String SEP = "; ";
        return "Roller coaster " + super.toString() + "\n"
                + "...maxSpeed: " + maxSpeed
                + "; trackLength: " + trackLength
                + "; whiplashFactor: " + whiplashFactor;
    }

    public boolean start() {
        return true;
    }

    public boolean stop() {
        return true;
    }

    public int load() {
        return capacity;
    }

    /*
    * default state values...
     */
    private final static int DFLT_TRACK_LENGTH = -1;
    private final static int DFLT_MAX_SPEED = 0;
    private final static double DFLT_WHIPLASH_FACTOR = 0.0;

    /*
    * instance variables...
     */
    private int trackLength = DFLT_TRACK_LENGTH;             //in feet
    private int maxSpeed = DFLT_MAX_SPEED;                   //in knots
    private double whiplashFactor = DFLT_WHIPLASH_FACTOR;    //gforces

}

class BumperCars extends AmusementRide {

    public BumperCars(String name, int capacity, double price,
            int weight, int speed, int time) {
        super(name, price, capacity);
        if (capacity <= 4) {
            throw new IllegalArgumentException("invalid capacity");
        }
        this.capacity = capacity;

        init(weight, speed, time);
    }

    private void init(int weight, int speed, int time) {
        if (weight < 0) {
            throw new IllegalArgumentException("invalid weight");
        }
        rideWeight = weight;
        if (!setMaxSpeed(speed)) {
            throw new IllegalArgumentException("invalid speed");
        }
        if (time < 0) {
            throw new IllegalArgumentException("invalid time");
        }
        minTime = time;
    }

    /**
     * maxSpeed is not changed if parameter speed is negative
     *
     * @param speed new speed
     * @return true if speed changed; false otherwise
     */
    public boolean setMaxSpeed(int speed) {
        if (speed < 0) {
            return false;
        }
        maxSpeed = speed;
        return true;
    }

    public String toString() {
        return "Bumper Cars " + super.toString() + "\n"
                + "...weight " + rideWeight
                + "; speed: " + maxSpeed
                + "; time: " + minTime;
    }

    public boolean start() {
        System.out.println("..." + name + " has started");
        return true;
    }

    public boolean stop() {
        System.out.println("..." + name + " has stopped");
        return true;
    }

    public int load() {
        System.out.println("..." + name + " has been loaded");
        return capacity;
    }

    /*
    * default state values...
     */
    private final static int DFLT_WEIGHT = 5;
    private final static int DFLT_MAX_SPEED = 0;
    private final static int DFLT_MIN_TIME = 2;

    /*
    * instance variables...
     */
    private int rideWeight = DFLT_WEIGHT;                        //in kilograms
    private int maxSpeed = DFLT_MAX_SPEED;                       //in knots
    private int minTime = DFLT_MIN_TIME;                         //in minutes

}
