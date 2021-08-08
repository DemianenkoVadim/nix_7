package ua.com.alevel.gameoflife;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import static java.lang.Thread.sleep;

public class GameOfLife {

    private static final String NAME_OF_GAME = "Game of Life";
    private static final int START_LOCATION = 200;
    private static final int LIFE_SIZE = 50;
    private static final int POINT_RADIUS = 10;
    private static final int FIELD_SIZE = LIFE_SIZE * POINT_RADIUS;
    private static final int BTN_PANEL_HEIGHT = 58;
    private static final int TWO_NEIGHBORS = 2;
    private static final int THREE_NEIGHBORS = 3;
    private static final int INDEX_OF_ARRAY = 0;

    boolean[][] lifeGeneration = new boolean[LIFE_SIZE][LIFE_SIZE];
    boolean[][] nextGeneration = new boolean[LIFE_SIZE][LIFE_SIZE];
    volatile boolean goNextGeneration = false;
    int showDelay = 200;
    VisualPanel visualBoard;
    Random randomPositions = new Random();

    public void startsTheProgram() {
        JFrame frame = new JFrame(NAME_OF_GAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_SIZE, FIELD_SIZE + BTN_PANEL_HEIGHT);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);

        visualBoard = new VisualPanel();
        visualBoard.setBackground(Color.white);

        JButton fillButton = new JButton("Fill");
        fillButton.addActionListener(new FillButtonListener());

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processOfLife();
                visualBoard.repaint();
            }
        });

        final JButton goButton = new JButton("Play");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goNextGeneration = !goNextGeneration;
                goButton.setText(goNextGeneration ? "Stop" : "Play");
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(fillButton);
        btnPanel.add(stepButton);
        btnPanel.add(goButton);

        frame.getContentPane().add(BorderLayout.CENTER, visualBoard);
        frame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
        frame.setVisible(true);

        while (true) {
            if (goNextGeneration) {
                processOfLife();
                visualBoard.repaint();
                try {
                    sleep(showDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class FillButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            for (int pointVertical = INDEX_OF_ARRAY; pointVertical < LIFE_SIZE; pointVertical++) {
                for (int pointHorizontal = INDEX_OF_ARRAY; pointHorizontal < LIFE_SIZE; pointHorizontal++) {
                    lifeGeneration[pointVertical][pointHorizontal] = randomPositions.nextBoolean();
                }
            }
            visualBoard.repaint();
        }
    }

    int countTheNumberOfNeighbors(int x, int y) {
        int counter = 0;
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                int nX = x + dx;
                int nY = y + dy;
                nX = (nX < 0) ? LIFE_SIZE - 1 : nX;
                nY = (nY < 0) ? LIFE_SIZE - 1 : nY;
                nX = (nX > LIFE_SIZE - 1) ? 0 : nX;
                nY = (nY > LIFE_SIZE - 1) ? 0 : nY;
                counter += (lifeGeneration[nX][nY]) ? 1 : 0;
            }
        }
        if (lifeGeneration[x][y]) {
            counter--;
        }
        return counter;
    }

    void processOfLife() {
        for (int pointVertical = INDEX_OF_ARRAY; pointVertical < LIFE_SIZE; pointVertical++) {
            for (int pointHorizontal = INDEX_OF_ARRAY; pointHorizontal < LIFE_SIZE; pointHorizontal++) {
                int count = countTheNumberOfNeighbors(pointVertical, pointHorizontal);
                nextGeneration[pointVertical][pointHorizontal] = lifeGeneration[pointVertical][pointHorizontal];
                nextGeneration[pointVertical][pointHorizontal] = count == TWO_NEIGHBORS || nextGeneration[pointVertical][pointHorizontal];
                nextGeneration[pointVertical][pointHorizontal] = (count >= TWO_NEIGHBORS) && (count <= THREE_NEIGHBORS) && nextGeneration[pointVertical][pointHorizontal];
            }
        }
        for (int x = INDEX_OF_ARRAY; x < LIFE_SIZE; x++) {
            System.arraycopy(nextGeneration[x], 0, lifeGeneration[x], 0, LIFE_SIZE);
        }
    }

    public class VisualPanel extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = INDEX_OF_ARRAY; x < LIFE_SIZE; x++) {
                for (int y = INDEX_OF_ARRAY; y < LIFE_SIZE; y++) {
                    if (lifeGeneration[x][y]) {
                        g.fillOval(x * POINT_RADIUS, y * POINT_RADIUS, POINT_RADIUS, POINT_RADIUS);
                    }
                }
            }
        }
    }
}
