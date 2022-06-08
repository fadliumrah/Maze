package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Maze extends JFrame {

    // susunan awal untuk labirin
    int[][] maze = new int[][] {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 2, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0 },
            { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0 },
            { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0 },
            { 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0 },
            { 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0 },
            { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0 },
            { 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 3, 0 },
            { 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0 }
    };

    // inisialisasi tombol-tombol
    JButton exit;
    JButton ResetMap;
    JButton ResetResource;
    JButton ResetDestination;
    JButton ResetCourierPosition;
    JButton Working;

    boolean repaint;

    public Maze() {

        // frame
        setTitle("2001020049");
        setSize(960, 530);
        URL urlIcon = getClass().getResource("logo-umrah.png");
        ImageIcon image = new ImageIcon(urlIcon);
        setIconImage(image.getImage());

        // meletakkan frame di tengah-tengah layar
        setLocationRelativeTo(null);
        setLayout(null); //

        // pemberian nama pada button
        exit = new JButton("Exit");
        ResetMap = new JButton("Reset Map");
        ResetResource = new JButton("Reset Resource");
        ResetDestination = new JButton("Reset Destination");
        ResetCourierPosition = new JButton("Reset Courier Position");
        Working = new JButton("Working");

        // menambahkan button ke dalam frame
        add(exit);
        add(ResetMap);
        add(ResetResource);
        add(ResetDestination);
        add(ResetCourierPosition);
        add(Working);

        setVisible(true);

        // set posisi dari button
        Working.setBounds(760, 50, 100, 40);
        exit.setBounds(760, 115, 100, 40);
        ResetMap.setBounds(760, 180, 100, 40);
        ResetResource.setBounds(735, 245, 150, 40);
        ResetDestination.setBounds(735, 310, 150, 40);
        ResetCourierPosition.setBounds(710, 375, 200, 40);

        // aksi yang akan dijalankan ketika mengklik button Reset Map
        ResetMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // membuat array acak dan menyimpan di x
                int x[][] = GenerateArray();
                repaint = true;

                // untuk menampilkan array x di layar
                restore(x);

                // menggambar ulang array di frame
                repaint();
            }
        });

        // aksi yang akan dilakukan ketika mengklik button Exit
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // menutup aplikasi
                System.exit(0);
            }
        });

        // aksi yang akan dilakkan ketika mengklik button Reset Resource
        ResetResource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // code here

            }
        });

        // aksi yang akan dilakkan ketika mengklik button Reset Destination
        ResetDestination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // code here

            }
        });

        // aksi yang akan dilakkan ketika mengklik button Reset Courier Position
        ResetCourierPosition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // code here

            }
        });

        // aksi yang akan dilakkan ketika mengklik button Working
        Working.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // code here

            }
        });

    }

    // dapatkan ukuran labirin
    public int Size() {
        return maze.length;
    }

    public void restore(int[][] savedMazed) {
        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                maze[i][j] = savedMazed[i][j];
            }
        }

        // sumber daya atau paket
        maze[1][1] = 2;
        // destinasi
        maze[18][18] = 3;
    }

    // menghasilkan array acak dengan nilai 0 atau 1
    public int[][] GenerateArray() {
        // tempat menyimpan array yang sudah diacak
        int[][] arr;

        arr = new int[20][20];
        Random rnd = new Random();
        int min = 0;
        int high = 1;

        for (int i = 0; i < 20; i = i + 1) {
            for (int j = 0; j < 20; j = j + 2) {
                int n = rnd.nextInt((high - min) + 1);
                arr[i][j] = n;
            }
        }

        return arr;
    }

    // draw the maze on the JFrame
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(70, 70); // move the maze to begin at 70 from x and 70 from y

        // draw the maze to random
        if (repaint == true) {
            for (int row = 0; row < maze.length; row++) {
                for (int col = 0; col < maze[0].length; col++) {
                    Color color;
                    switch (maze[row][col]) {
                        case 1:
                            color = Color.darkGray; // block (black)
                            break;
                        case 3:
                            color = Color.RED; // goal (red)
                            break;
                        case 2:
                            color = Color.YELLOW; // initial state (yellow)
                            break;

                        default:
                            color = Color.WHITE; // white free space 0 (white)
                    }
                    g.setColor(color);
                    g.fillRect(20 * col, 20 * row, 20, 20); // fill rectangular with color
                    g.setColor(Color.BLUE); // the border rectangle color
                    g.drawRect(20 * col, 20 * row, 20, 20); // draw rectangular with color

                }
            }
        }

        // draw the maze to initial maze
        if (repaint == false) { // what to do if the repaint was set to false (draw the solution for the maze)
            for (int row = 0; row < maze.length; row++) {
                for (int col = 0; col < maze[0].length; col++) {
                    Color color;
                    switch (maze[row][col]) {
                        case 1:
                            color = Color.darkGray; // block (black)
                            break;
                        case 3:
                            color = Color.RED; // goal (red)
                            break;
                        case 2:
                            color = Color.YELLOW; // initial state (yellow)
                            break;
                        default:
                            color = Color.WHITE; // white free space 0 (white)
                    }
                    g.setColor(color);
                    g.fillRect(20 * col, 20 * row, 20, 20); // fill rectangular with color
                    g.setColor(Color.BLUE); // the border rectangle color
                    g.drawRect(20 * col, 20 * row, 20, 20); // draw rectangular with color

                }

            }

        }

    }

    // the main program
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Maze maze = new Maze(); // we create new class which will invoke the constructor
            }
        });

    }

}