package pkgSlRenderer;

import pkgSlUtils.PingPongManager;
import pkgSlUtils.SlKeyStrokes;
import pkgSlUtils.SlWindowManager;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static pkgCSC133Driver.SlSpot.BOARDSIZE;

public class GoLRenderer {

    private static final float SPACE_BETWEEN_SQUARES = 0.01f;
    private static final float WIN_MARGIN = 0.02f;

    private static final float ADJUST_MARGIN_Y = 0.01f;

    public static final float NDC_WIDTH = 2.0f;
    public static final float NDC_HEIGHT = 2.0f;

    private static final float NDC_RIGHT_UP = 1.0f;

    public static final float NDC_LEFT_DOWN = -1.0f;

    public void render(int FRAME_DELAY) {
        long windowHandle = SlWindowManager.get().getWindowHandle();

        // goal
        // we need to hook up the pingpong arrays with the square renderer according to predefined rules
        // pp should be instantiated  somewhere inside render class
        PingPongManager pp = new PingPongManager(BOARDSIZE,BOARDSIZE);

        // we need an instance of key strokes
        //SlKeyStrokes ks = new SlKeyStrokes();

        // define how much screen space to give for squares
        float maxHorizontalSpace = SPACE_BETWEEN_SQUARES * (pp.getRows() - 1);
        float maxVerticalSpace = SPACE_BETWEEN_SQUARES * (pp.getCols() - 1);

        // define the max width of the squares
        // total NDC space is 2 from edge to edge
        float squareWidth = (NDC_WIDTH - maxHorizontalSpace - NDC_WIDTH * WIN_MARGIN) / pp.getRows();
        float squareHeight = (NDC_HEIGHT - maxVerticalSpace - NDC_HEIGHT * WIN_MARGIN) / pp.getCols();


        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // check output to match with what is rendered on the window
        //pp.showLiveArr();

        boolean KeepRunning = true;

        while (!glfwWindowShouldClose(windowHandle)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            if (SlKeyStrokes.isKeyPressed(GLFW_KEY_I) ) {
                KeepRunning = false;
                FRAME_DELAY += 500;
                System.out.println("+++ Frame delay is now: " + FRAME_DELAY + " ms!");
                KeepRunning = true;
                SlKeyStrokes.resetKeypressEvent(GLFW_KEY_D);
                SlKeyStrokes.resetKeypressEvent(GLFW_KEY_LEFT_SHIFT);
            }
            if(SlKeyStrokes.isKeyPressed(GLFW_KEY_D)) {
                KeepRunning = false;
                // only reduce if frame delay is larger than 500
                if (FRAME_DELAY >= 500) {
                    FRAME_DELAY -= 500;
                }
                else {
                    FRAME_DELAY = 0;
                }
                System.out.println("+++ Frame delay is now: " + FRAME_DELAY + " ms!");
                SlKeyStrokes.resetKeypressEvent(GLFW_KEY_I);
                SlKeyStrokes.resetKeypressEvent(GLFW_KEY_LEFT_SHIFT);
            }

            pp.liveOrDie();
            // put array on screen
            arrangeSquares(pp.getRows(), pp.getCols(),squareWidth,squareHeight,pp);


            glfwSwapBuffers(windowHandle);
            frameDelay(Math.max(FRAME_DELAY,10));
        }
    }

    // method needs to be passed the instance of pp so we can
    // edit the colors of the squares before the call to render them
    private void arrangeSquares(int rows, int cols, float squareWidth, float squareHeight, PingPongManager pp) {
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols; j++) {
                // define where to place squares
                // NCC -> -1 left/down | 1 up/right |
                float xAxi = NDC_LEFT_DOWN + WIN_MARGIN +  j * (squareWidth + SPACE_BETWEEN_SQUARES);
                float yAxi = NDC_RIGHT_UP - ADJUST_MARGIN_Y - (i + 1) * (squareHeight + SPACE_BETWEEN_SQUARES);

                // set color for alive or dead squares
                if (pp.get(i,j) == 1) {
                    // alive color
                    glColor3f(0,1,0);
                }
                else {
                    // dead color ( change to match screen background )
                    glColor4f(1,0,0,1);
                }
                //test square
                drawSquare(xAxi,yAxi,squareWidth,squareHeight);
            }
        }
    }
    // best to leave this to only control creating a square
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
    }
}
