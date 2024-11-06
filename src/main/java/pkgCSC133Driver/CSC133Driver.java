package pkgCSC133Driver;
import pkgSlRenderer.GoLRenderer;
import pkgSlUtils.SlWindowManager;

import static pkgCSC133Driver.SlSpot.*;

public class CSC133Driver {
    public static void main(String[] my_args) {

        // window size, frame delay, board size all controlled in spot file
        // when running : r to reset
        //                i to increase frame delay by 500
        //                d to decrease frame delay by 500
        //                p to pause / p to unpause
        //                q to quit

        GoLRenderer my_re = new GoLRenderer();
        SlWindowManager.get().initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, "CSUS CSC133");
        my_re.initOpenGL(SlWindowManager.get());
        my_re.render();

    }
}
