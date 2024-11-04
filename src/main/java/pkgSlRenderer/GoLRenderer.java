package pkgSlRenderer;

import pkgSlUtils.PingPongManager;
import pkgSlUtils.SlWindowManager;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GoLRenderer {

    public void render(int FRAME_DELAY, int ROWS, int COLS) {
        long windowHandle = SlWindowManager.get().getWindowHandle();

        // define how much screen space to give for squares
        // try a small value -> magic number | change to defined var later
        float maxHorizontalSpace = 0.2f * (ROWS - 1);
        float maxVerticalSpace = 0.2f * (COLS - 1);

        // define the max width of the squares
        // total NDC space is 2 from edge to edge
        // 0.01-0.03 best for testing in NDC
        float squareWidth = (2 - maxHorizontalSpace -2 * 0.01f) / COLS;
        float squareHeight = (2 - maxVerticalSpace -2 * 0.01f) / ROWS;


        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);


        while (!glfwWindowShouldClose(windowHandle)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            for (int i = 0; i < ROWS; i++){
                for (int j = 0; j < COLS; j++) {
                    // define where to place squares
                    // NCC -> -1 left/down | 1 up/right |
                    // 0.01-0.03 best for testing in NDC
                    float xAxi = -1 + 0.01f +  j * (squareWidth + 0.02f);
                    float yAxi = 1 - 0.01f - (i + 1) * (squareHeight + 0.02f);
                    //test square
                    glColor3f(1,0,0);
                    drawSquare(xAxi,yAxi,squareWidth,squareHeight);
                }
            }

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
