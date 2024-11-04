package pkgSlRenderer;

import pkgSlUtils.PingPongManager;
import pkgSlUtils.SlWindowManager;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GoLRenderer {

    public void render(int FRAME_DELAY) {
        long windowHandle = SlWindowManager.get().getWindowHandle();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);


        while (!glfwWindowShouldClose(windowHandle)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(windowHandle);
            frameDelay(FRAME_DELAY);
        }
    }

    private void drawSquare(float xAxi, float yAxi, float squareWidth, float squareHeight) {
        glBegin(GL_QUADS);
        glVertex2f(xAxi, yAxi);
        glVertex2f(xAxi + squareWidth, yAxi);
        glVertex2f(xAxi + squareWidth, yAxi + squareHeight);
        glVertex2f(xAxi, yAxi + squareHeight);
        glEnd();
    }


    private void frameDelay(int FRAME_DELAY) {
        if (FRAME_DELAY > 0) {
            try {
                Thread.sleep(FRAME_DELAY);
            } catch (InterruptedException e) {
                throw new RuntimeException("Error during ThreadSleep", e);
            }
        }
    }

    public void initOpenGL(SlWindowManager slWindowManager) {
        // Initialize OpenGL settings if necessary
    }
}
