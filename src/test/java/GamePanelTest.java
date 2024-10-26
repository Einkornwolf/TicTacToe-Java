import org.junit.jupiter.api.Test;
import window.GamePanel;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class GamePanelTest {

    @Test
    void checkWinReturnsPCWinForRow() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.gameState = new int[][] {
                {1, 1, 1},
                {0, 2, 0},
                {2, 0, 2}
        };
        assertEquals(1, gamePanel.checkWin());
    }

    @Test
    void checkWinReturnsHumanWinForColumn() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.gameState = new int[][] {
                {2, 1, 0},
                {2, 1, 0},
                {2, 0, 1}
        };
        assertEquals(2, gamePanel.checkWin());
    }

    @Test
    void checkWinReturnsPCWinForDiagonal() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.gameState = new int[][] {
                {1, 2, 0},
                {2, 1, 0},
                {0, 0, 1}
        };
        assertEquals(1, gamePanel.checkWin());
    }

    @Test
    void checkWinReturnsHumanWinForAntiDiagonal() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.gameState = new int[][] {
                {2, 1, 2},
                {1, 2, 0},
                {2, 0, 1}
        };
        assertEquals(2, gamePanel.checkWin());
    }

    @Test
    void
    checkWinReturnsDraw() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.gameState = new int[][] {
                {1, 2, 1},
                {2, 1, 2},
                {2, 1, 2}
        };
        assertEquals(3, gamePanel.checkWin());
    }

    @Test
    void checkWinReturnsNoWinnerYet() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.gameState = new int[][] {
                {1, 2, 0},
                {2, 1, 0},
                {0, 0, 0}
        };
        assertEquals(0, gamePanel.checkWin());
    }
}