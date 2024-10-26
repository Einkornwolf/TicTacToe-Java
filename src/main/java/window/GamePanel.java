package window;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class GamePanel extends JPanel implements MouseInputListener {

    //Board
    public int[][] gameState = new int[3][3];
    //Player Variable 1 = PC, 2 = Human
    private int currentPlayer = 2;
    //Boolean if AI is enabled
    private boolean aiEnabled = false;

    public GamePanel() {
        super();
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setSize(600, 600);
        this.setPreferredSize(new Dimension(600, 600));

        this.addMouseListener(this);

        //Add AI Button
        JCheckBox aiMode = new JCheckBox("AI");
        aiMode.setBackground(Color.BLACK);
        aiMode.setForeground(Color.WHITE);
        aiMode.setFocusable(false);

        this.add(aiMode);

        //Fill rows with zeros
        for (int[] row : gameState) {
            Arrays.fill(row, 0);
        }

        aiMode.addActionListener(e -> {
            this.aiEnabled = aiMode.isSelected();
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        //Draw Grid
        g.drawLine(0, 200, 600, 200);
        g.drawLine(0, 400, 600, 400);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(400, 0, 400, 600);

        //Draw current game-state:
        // 1 = X
        // 2 = O
        for(int i = 0; i < gameState.length; i++) {
            for(int j = 0; j < gameState[i].length; j++) {
                //Draw X
                if(gameState[i][j] == 1) {
                    g.drawLine((i * 200) + 10, (j * 200) + 10, ((i+1) * 200) - 10, ((j+1) * 200) - 10);
                    g.drawLine(((i+1) * 200) - 10, (j * 200) + 10, (i * 200) + 10, ((j+1) * 200) - 10);
                }
                //Draw O
                if(gameState[i][j] == 2) {
                    g.drawOval((i * 200) + 5, (j * 200) + 5, 190, 190);
                }

                //Else tile is empty
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(this.aiEnabled) {
            //If Humans Turn
            if(this.currentPlayer == 2) {
                int x = e.getX() / 200;
                int y = e.getY() / 200;

                //Check if Tile is already occupied
                if(this.gameState[x][y] != 0) {
                    JOptionPane.showMessageDialog(this, "Tile already occupied!");
                    return;
                }

                this.gameState[x][y] = currentPlayer;

                this.currentPlayer = 1;
            } else if(this.currentPlayer == 1) {

                int bestScore = -100;

                int x = 0;
                int y = 0;

                //MiniMax
                for(int i = 0; i < 3; i++) {
                    for(int j = 0; j < 3; j++) {
                        if(gameState[i][j] == 0) {
                            this.gameState[i][j] = currentPlayer;
                            int score = minimax(this.gameState, 0, false, 0);
                            this.gameState[i][j] = 0;
                            if(score > bestScore) {
                                bestScore = score;
                                x = i;
                                y = j;
                            }
                        }
                    }
                }


                this.gameState[x][y] = currentPlayer;
                this.currentPlayer = 2;
            }

        } else {
            //Check which tile is affected
            int x = e.getX() / 200;
            int y = e.getY() / 200;

            //Check if Tile is already occupied
            if(this.gameState[x][y] != 0) {
                JOptionPane.showMessageDialog(this, "Tile already occupied!");
                return;
            }

            this.gameState[x][y] = currentPlayer;

            currentPlayer = currentPlayer == 1 ? 2 : 1;
        }

        this.repaint();

        //Check for Win: 1 = PC, 2 = Human, 3 = Tie
        int win = this.checkWin();

        if(win == 1) {
            JOptionPane.showMessageDialog(this, "PC won!");
        }
        if(win == 2) {
            JOptionPane.showMessageDialog(this, "Human won!");
        }
        if(win == 3) {
            JOptionPane.showMessageDialog(this, "Game ended in a draw!");
        }
        if(win != 0) {
            this.resetGame();
            this.repaint();
        }

    }

    private int minimax(int[][] gameState, int depth, boolean isMaximizing, int currentScore) {

        int win = checkWin();

        int score = win == 3 ? 0 : win == 2 ? -1 : win == 1 ? 1 : -1;

        if(score != -1) return currentScore + score;

        if(isMaximizing) {
            int bestScore = -100;
            for(int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(gameState[i][j] == 0) {
                        gameState[i][j] = 1;
                        int tempScore = minimax(gameState, depth + 1, false, currentScore);
                        gameState[i][j] = 0;
                        if(tempScore > bestScore) {
                            bestScore = tempScore;
                        }
                    }
                }
            }
            return bestScore +currentScore;
        } else {
            int bestScore = 100;
            for(int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(gameState[i][j] == 0) {
                        gameState[i][j] = 2;
                        int tempScore = minimax(gameState, depth + 1, true, currentScore);
                        gameState[i][j] = 0;
                        if(tempScore < bestScore) {
                            bestScore = tempScore;
                        }
                    }
                }
            }
            return bestScore + currentScore;
        }
    }

    private void resetGame() {
        for(int i = 0; i < this.gameState.length; i++) {
            for(int j = 0; j < this.gameState[i].length; j++) {
                this.gameState[i][j] = 0;
            }
        }
    }

    public int checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (gameState[i][0] != 0 && gameState[i][0] == gameState[i][1] && gameState[i][1] == gameState[i][2]) {
                return gameState[i][0];
            }
            if (gameState[0][i] != 0 && gameState[0][i] == gameState[1][i] && gameState[1][i] == gameState[2][i]) {
                return gameState[0][i];
            }
        }

        // Check diagonals
        if (gameState[0][0] != 0 && gameState[0][0] == gameState[1][1] && gameState[1][1] == gameState[2][2]) {
            return gameState[0][0];
        }
        if (gameState[0][2] != 0 && gameState[0][2] == gameState[1][1] && gameState[1][1] == gameState[2][0]) {
            return gameState[0][2];
        }

        // Check for draw
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameState[i][j] == 0) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            return 3; // Indicate a draw
        }

        return 0; // No winner yet
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
