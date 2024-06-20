/* Lab5 Model */
/* 10403-15 Spring 2023 */
/* Student name: Evan Eissler */
// Holds all information about the planets and the planet object for easy acces in View


import javax.swing.*;

public class Lab5ModelRevised {
    // array of planets
    Planet[] planets;
    static final int totalPlanets = 8;

// we don't need main because model can't be run by itself
//    public static void main(String args[]) {
//        new Lab5Model();
//    }

    public class Planet {
        String name;
        String imagePath, soundPath;
        // Added atmosphere ot hold string of atmosphere elements
        String atmosphere, discovered, effectiveTemp;
        ImageIcon image;
        double gravity, orbit, orbitVelocity, density;
        double surfaceArea;
        // Added rotation speed double, holds double of relative rotation speed ot earth
        double rotationSpeed;
        boolean hasMoon;
        String moonPath;
        ImageIcon moonImage; // only if hasMoon is true

        public Planet(String name, double grav, double surfArea, String atmos, double rotSpd, String disc, double orb, double orbV, double den, String temp, String imgPath, String sound) {
            System.out.println("at constructor Planet");
            // we set the image icons later
            this.name = name;
            this.gravity = grav;
            this.surfaceArea = surfArea;
            this.imagePath = imgPath;
            this.hasMoon = false; // until we get an moonPath
            this.image = null;
            this.moonImage = null;
            this.soundPath = sound;
            // Attaches the information to be accessible with the object
            this.atmosphere = atmos;
            this.rotationSpeed = rotSpd;
            this.discovered = disc;
            this.effectiveTemp = temp;
            this.orbit = orb;
            this.density = den;
            this.orbitVelocity = orbV;
        }

        public void setMoon(String moonPath) {
            hasMoon = true;
            this.moonPath = moonPath;
            if (moonImage == null) {
                try {
                    moonImage = new ImageIcon(moonPath);
                } catch (Exception e) {
                    System.out.println("problem with image file" + moonPath);
                }
            }
        }
        
        public String getName() {
            return this.name;
        }

        public ImageIcon getImage() {
            if (image == null) {
                try {
                    image = new ImageIcon(imagePath);
                } catch (Exception e) {
                    System.out.println("problem with image file" + imagePath);
                }
            }
            return image;
        }

        public ImageIcon getMoonImage() {
            if (hasMoon)
                return moonImage;
            else
                return null;
        }

        public double getSurfaceArea() {
            return surfaceArea;
        }
 
        public double getGravity() {
            return gravity;
        }

        // My added method, returns elements in atmosphere
        public String getAtmosphere() {
            return atmosphere;
        }

        // My added method, returns rotation speed relative to earth
        public double getRotationSpeed() {
            return rotationSpeed;
        }

        public String getDiscovered() {
            return discovered;
        }

        public double getOrbit() {
            return orbit;
        }

        public double getOrbitVelocity() {
            return orbitVelocity;
        }

        public double getDensity() {
            return density;
        }

        public String getTemp() {
            return effectiveTemp;
        }

        public String getSound() {
            return soundPath;
        }

 } // class Planet

    public Lab5ModelRevised() {
        System.out.println("At constructor Model Class");
        // setup planets
        // String name, double grav, double surfArea, boolean hasMoon
        Planet mercury = new Planet("Mercury", 0.38, 74797000.00, "No Atmosphere",58.646, "Known by Ancients", 57909227.00, 170503.00, 5.43, "-173/427", "Labs/Lab7/images/mercury.jpg", "Labs/Lab7/sounds/a.wav");
        Planet venus = new Planet("Venus", 0.91, 460234317.00,"Carbon Dioxide, Nitrogen", -243.018, "Known by Ancients", 108209475.00, 126074.00, 5.24, "462",  "Labs/Lab7/images/venus.jpg", "Labs/Lab7/sounds/appear.wav");
        Planet earth = new Planet("Earth", 1.00, 510064472.99, "Nitrogen, Oxygen", 0.99726968,  "Known by Ancients", 149598262.00, 107218.00, 5.51, "-88/58",  "Labs/Lab7/images/earth.jpg", "Labs/Lab7/sounds/avalanche.wav");
        Planet mars = new Planet("Mars", 0.38, 144371291.00, "Carbon Dioxide, Nitrogen, Argon",1.026,  "Known by Ancients", 227943824.00, 86677.00, 3.93, "-153/20",  "Labs/Lab7/images/mars.jpg", "Labs/Lab7/sounds/bubbles.wav");
        Planet jupiter = new Planet("Jupiter", 2.40, 61418738571.00,"Hydrogen, Helium", 0.41354, "Known by Ancients", 778340821.00, 47002.00, 1.33, "-148",   "Labs/Lab7/images/jupiter.jpg", "Labs/Lab7/sounds/fireworks.wav");
        Planet saturn = new Planet("Saturn", 1.07, 42612133285.00, "Hydrogen, Helium", 0.444, "Known by Ancients", 1426666422.00, 34701.00, 0.69, "-178",  "Labs/Lab7/images/saturn.jpg", "Labs/Lab7/sounds/growllion.wav");
        Planet uranus = new Planet("Uranus", 0.90, 8083079690.00, "Hydrogen, Helium, Methane", -0.718, "March 1, 1781", 2870658186.00, 24477.00, 1.27, "-216",  "Labs/Lab7/images/uranus.jpg", "Labs/Lab7/sounds/spaceSound.wav");
        Planet neptune = new Planet("Neptune", 1.10, 7618272763.00, "Hydrogen, Helium, Methane", 0.671, "September 23, 1846", 4498396441.00, 19566.00, 2.05, "-214",  "Labs/Lab7/images/neptune.jpg", "Labs/Lab7/sounds/telemetry.wav");
        
        // set up planets with moon by adding images
        earth.setMoon("Labs/Lab7/images/moon.jpg");
        mars.setMoon("Labs/Lab7/images/phobos.jpg");
        jupiter.setMoon("Labs/Lab7/images/jupiterMoons.jpg");
        saturn.setMoon("Labs/Lab7/images/saturnRings.jpg");
        uranus.setMoon("Labs/Lab7/images/uranusMoons.jpg");
        neptune.setMoon("Labs/Lab7/images/neptuneMoon.jpg");
        
        planets = new Planet[totalPlanets];
        planets[0] = mercury;
        planets[1] = venus;
        planets[2] = earth;
        planets[3] = mars;
        planets[4] = jupiter;
        planets[5] = saturn;
        planets[6] = uranus;
        planets[7] = neptune;

    }

    public ImageIcon getImageFile(String filename) {
        //helper class
        ImageIcon image = null;
        try {
            image = new ImageIcon(filename);
        } catch (Exception e) {
            System.out.println("problem with image file" + filename);
        }
        return image;
    }
    
    public Planet getPlanet(String pName) {
        // given a planet name, we return the planet instance
        switch (pName) {
        case "Mercury":
            return planets[0];
        case "Venus":
            return planets[1];
        case "Earth":
            return planets[2];
        case "Mars":
            return planets[3];
        case "Jupiter":
            return planets[4];
        case "Saturn":
            return planets[5];
        case "Uranus":
            return planets[6];
        case "Neptune":
            return planets[7];
        }
        System.out.println("Did not find planets to return!");
        return null;
    }
    
}
