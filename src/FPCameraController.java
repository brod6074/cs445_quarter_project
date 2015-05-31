// --------------------------------------------------------
// File:        FPCameraController.java
// Authors:     Roberto Rodriguez, Sang Pham, Mike Claros
// Team:        SOF
// Class:       CS 445
//
// Assignment:  Check Point 1
// Date last modified: 5/4/2015
//
// Purpose: Creates a camera for 3D graphics viewing.
// Implemented from code in course slides.
// --------------------------------------------------------

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

// Class: FPCameraController
public class FPCameraController {
    private Vector3f position;
    private Vector3f lPosition;
    private float yaw;              // y-axis rotation of camera
    private float pitch;            // x-axis rotation of camera

    // Method: FPCameraController
    // Purpose: Constructor
    public FPCameraController(float x, float y, float z) {
        position = new Vector3f(x, y, z);
        lPosition = new Vector3f(x, y, z);
        lPosition.x = 0;
        lPosition.y = 15;
        lPosition.z = 0;
        yaw = 0;
        pitch = 0;
    }

    // Method: yaw
    // Purpose: Increments current yaw rotation by amount
    public void yaw(float amount) {
        yaw += amount;
    }

    // Method: pitch
    // Purpose: Decrement current pitch by amount
    public void pitch(float amount) {
        pitch -= amount;
    }

    // Method: walkForward
    // Purpose: Moves the camera forward relative to it's current rotation(yaw)
    public void walkForward(float distance) {
        float xOffset = distance * (float) Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float) Math.cos(Math.toRadians(yaw));
        position.x -= xOffset;
        position.z += zOffset;
    }

    // Method: walkBackwards
    // Purpose: Moves the camera backward relative to its current rotation (yaw)
    public void walkBackwards(float distance) {
        float xOffset = distance * (float) Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float) Math.cos(Math.toRadians(yaw));
        position.x += xOffset;
        position.z -= zOffset;
    }

    // Method: strafeLeft
    // Purpose: Strafes the camera left relative to its current rotation(yaw)
    public void strafeLeft(float distance) {
        float xOffset = distance * (float) Math.sin(Math.toRadians(yaw - 90));
        float zOffset = distance * (float) Math.cos(Math.toRadians(yaw - 90));
        position.x -= xOffset;
        position.z += zOffset;
    }

    // Method: strafeRight
    // Purpose: Strafes the camera right relative to its current rotation(yaw)
    public void strafeRight(float distance) {
        float xOffset = distance * (float) Math.sin(Math.toRadians(yaw + 90));
        float zOffset = distance * (float) Math.cos(Math.toRadians(yaw + 90));
        position.x -= xOffset;
        position.z += zOffset;
    }

    // Method: moveUp
    // Purpose: Moves the camera up relative to its current rotation (yaw)
    public void moveUp(float distance) {
        position.y -= distance;
    }

    // Method: moveDown
    // Purpose: Moves the camera down relative to its current rotation (yaw)
    public void moveDown(float distance) {
        position.y += distance;
    }

    // Method: lookThrough
    // Purpose: Translates and rotates the matrix so that it looks through the camera
    public void lookThrough() {
        glRotatef(pitch, 1, 0, 0);
        glRotatef(yaw, 0, 1, 0);
        glTranslatef(position.x, position.y, position.z);
    }

    // Method: gameLoop
    // Purpose: Repeatedly draws a 3D cube on screen until user exits program.
    public void gameLoop() {
        float mouseSensitivity = 0.2f;
        float movementSpeed = 0.35f;
        Mouse.setGrabbed(true);

        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            float dx = Mouse.getDX();
            float dy = Mouse.getDY();

            yaw(dx * mouseSensitivity);
            pitch(dy * mouseSensitivity);

            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                walkForward(movementSpeed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                walkBackwards(movementSpeed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                strafeLeft(movementSpeed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                strafeRight(movementSpeed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                moveUp(movementSpeed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                moveDown(movementSpeed);
            }

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            lookThrough();

            glPushMatrix();
            glTranslatef(0, 0, -8);
            glRotatef(1, 1, 1, 0);
            render();
            glPopMatrix();

            Display.update();
            Display.sync(120);
        }
        Display.destroy();
    }

    // Method: render
    // Purpose: Draws the cube to the display.
    private void render() {

        glBegin(GL_QUADS);

        // front
        glColor3f(1.0f, 0, 0);
        glVertex3f(-1, -1, 1);
        glVertex3f(-1, 1, 1);
        glVertex3f(1, 1, 1);
        glVertex3f(1, -1, 1);

        // back
        glColor3f(0, 1.0f, 0);
        glVertex3f(-1, -1, -1);
        glVertex3f(-1, 1, -1);
        glVertex3f(1, 1, -1);
        glVertex3f(1, -1, -1);

        // bottom
        glColor3f(0, 0, 1.0f);
        glVertex3f(-1, -1, -1);
        glVertex3f(-1, -1, 1);
        glVertex3f(-1, 1, 1);
        glVertex3f(-1, 1, -1);

        // top
        glColor3f(1.0f, 1.0f, 0);
        glVertex3f(1, -1, -1);
        glVertex3f(1, -1, 1);
        glVertex3f(1, 1, 1);
        glVertex3f(1, 1, -1);

        // left
        glColor3f(1.0f, 0, 1.0f);
        glVertex3f(-1, -1, -1);
        glVertex3f(1, -1, -1);
        glVertex3f(1, -1, 1);
        glVertex3f(-1, -1, 1);

        // right
        glColor3f(0, 1.0f, 1.0f);
        glVertex3f(-1, 1, -1);
        glVertex3f(1, 1, -1);
        glVertex3f(1, 1, 1);
        glVertex3f(-1, 1, 1);

        glEnd();
    }
}
