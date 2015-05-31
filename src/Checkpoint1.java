// --------------------------------------------------------
// File:        Checkpoint1.java
// Authors:     Roberto Rodriguez, Sang Pham, Mike Claros
// Team:        SOF
// Class:       CS 445
//
// Assignment:  Check Point 1
// Date last modified: 5/4/2015
//
// Purpose: This program creates a 3D cube to manipulate
// --------------------------------------------------------

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.util.glu.GLU.*;
import static org.lwjgl.opengl.GL11.*;

// Class: Checkpoint1
// Purpose: Client class for the camera
public class Checkpoint1 {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private FPCameraController fp;

    // Method: Checkpoint1
    // Purpose: Default constructor. Initializes camera.
    public Checkpoint1() {
        fp = new FPCameraController(0, 0, 0);
    }

    // Method: createWindow
    // Purpose: Sets up the Display and creates the window
    private void createWindow() throws Exception {
        Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
        Display.setFullscreen(false);
        Display.setTitle("Check Point 1");
        Display.create();
    }

    // Method: initGL
    // Purpose: Initialize the graphics components.
    private void initGL() {
        int fov = 70;
        float aspectRatio = (float)Display.getWidth() / (float)Display.getHeight();
        float near = 0.3f;
        float far = 1000;

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fov, aspectRatio, near, far);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
        glEnable(GL_DEPTH_TEST);
    }

    // Method: start
    // Purpose: Begins the main functionality of the program
    public void start() {
        try {
            createWindow();
            initGL();
            fp.gameLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method: main
    // Purpose: Starting point of program
    public static void main(String[] args) {
        Checkpoint1 cp = new Checkpoint1();
        cp.start();
    }
}
