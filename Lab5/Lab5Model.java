/* Lab5 Model */
/* 10403-15 Spring 2023 */
/* Student name: <> */


import javax.swing.*;

public class Lab5Model {
    // array of planets
    Planet[] planets;
    static final int totalPlanets = 4;

// we don't need main because model can't be run by itself
//    public static void main(String args[]) {
//        new Lab5Model();
//    }

    public class Planet {
        String name;
        String imagePath;
        ImageIcon image;
        double gravity;
        double surfaceArea;
        boolean hasMoon;
        String moonPath;
        ImageIcon moonImage; // only if hasMoon is true

        public Planet(String name, double grav, double surfArea, String imgPath) {
            System.out.println("at constructor Planet");
            // we set the image icons later
            this.name = name;
            this.gravity = grav;
            this.surfaceArea = surfArea;
            this.imagePath = imgPath;
            this.hasMoon = false; // until we get an moonPath
            this.image = null;
            this.moonImage = null;
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
 } // class Planet

    public Lab5Model() {
        System.out.println("At constructor Model Class");
        // setup planets
        // String name, double grav, double surfArea, boolean hasMoon
        Planet mercury = new Planet("Mercury", 0.38, 74797000.00, "images/mercury.jpg");
        Planet venus = new Planet("Venus", 0.91, 460234317.00, "images/venus.jpg");
        Planet earth = new Planet("Earth", 1.00, 510064472.99, "images/earth.jpg");
        Planet mars = new Planet("Mars", 0.38, 144371291.00, "images/mars.jpg");
        
        // set up planets with moon by adding images
        earth.setMoon("images/moon.jpg");
        mars.setMoon("images/phobos.jpg");
        
        planets = new Planet[totalPlanets];
        planets[0] = mercury;
        planets[1] = venus;
        planets[2] = earth;
        planets[3] = mars;
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
        }
        System.out.println("Did not find planets to return!");
        return null;
    }
    
}
