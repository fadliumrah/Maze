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
            { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 1, 4, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
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
            { 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
            { 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 3 }
    };

    // inisialisasi tombol-tombol
    JButton exit;
    JButton ResetMap;
    JButton ResetResource;
    JButton ResetDestination;
    JButton ResetCourierPosition;
    JButton Working;

    // inisialisasi variabel repaint
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

        // menampilkan frame ke layar
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

                // memanggil fungsi array acak dan menyimpan di array maze
                int x[][] = GenerateArray();

                // untuk menyimpan array ke array utama/maze
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

                // memanggil fungsi GenerateResource dan menyimpan di array x
                int x[][] = GenerateResouce();

                // untuk menyimpan array ke array utama/maze
                restore(x);

                // menggambar ulang array di freme
                repaint();
            }
        });

        // aksi yang akan dilakkan ketika mengklik button Reset Destination
        ResetDestination.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // memanggil fungsi GenerateDestination dan menyimpannya di array x
                int x[][] = GenerateDestination();

                // menyimpan ulang array x di array utama/maze
                restore(x);

                // menggambar ulang array di frame
                repaint();
            }
        });

        // aksi yang akan dilakkan ketika mengklik button Reset Courier Position
        ResetCourierPosition.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // memanggil fungsi GenerateCourierPosition dan menyimpannya di array x
                int x[][] = GenerateCourierPosition();

                // menyimpan ulang array x di array utama/maze
                restore(x);

                // menggambar ulang array di frame
                repaint();
            }
        });

        // aksi yang akan dilakkan ketika mengklik button Working
        Working.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // memanggil fungsi GenerateCourierPosition dan menyimpannya di array x
                int x[][] = Working();

                // menyimpan ulang array x di array utama/maze
                restore(x);

                // menggambar ulang array di frame
                repaint();

            }
        });
    }

    // dapatkan ukuran labirin
    public int Size() {
        return maze.length;
    }

    // menyimpan ulang array ke dalam array maze/array utama
    public void restore(int[][] savedMazed) {
        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                maze[i][j] = savedMazed[i][j];
            }
        }
    }

    public int[][] GenerateArray() {
        // tempat menyimpan array yang sudah diacak
        int[][] arr;

        arr = new int[Size()][Size()];
        Random rnd = new Random();

        for (int i = 0; i < Size(); i = i + 1) {
            for (int j = 0; j < Size(); j = j + 1) {
                if (i % 2 == 0 || j % 2 == 1) {
                    int n = rnd.nextInt(2) + 0;
                    arr[i][j] = n;
                }
            }
        }

        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                if (maze[i][j] == 2) {
                    arr[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                if (maze[i][j] == 3) {
                    arr[i][j] = 3;
                }
            }
        }

        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                if (maze[i][j] == 4) {
                    arr[i][j] = 4;
                }
            }
        }

        return arr;
    }

    public int[][] GenerateResouce() {
        // tempat menyimpan array untuk sementara
        int[][] arr;
        arr = new int[Size()][Size()];

        Random row = new Random();
        Random col = new Random();

        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                if (maze[i][j] == 0) {
                    arr[i][j] = 0;
                } else if (maze[i][j] == 1) {
                    arr[i][j] = 1;
                } else if (maze[i][j] == 3) {
                    arr[i][j] = 3;
                } else if (maze[i][j] == 4) {
                    arr[i][j] = 4;
                }
            }
        }

        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {

                if (maze[i][j] == 2) {
                    int m = row.nextInt(Size()) + 0;
                    int n = col.nextInt(Size()) + 0;

                    if (maze[m][n] == 0) {
                        arr[m][n] = 2;
                    } else {
                        m = row.nextInt(Size()) + 0;
                        n = col.nextInt(Size()) + 0;

                        if (maze[m][n] == 0) {
                            arr[m][n] = 2;
                        } else {
                            m = row.nextInt(Size()) + 0;
                            n = col.nextInt(Size()) + 0;

                            if (maze[m][n] == 0) {
                                arr[m][n] = 2;
                            } else {
                                m = row.nextInt(Size()) + 0;
                                n = col.nextInt(Size()) + 0;

                                if (maze[m][n] == 0) {
                                    arr[m][n] = 2;
                                } else {
                                    m = row.nextInt(Size()) + 0;
                                    n = col.nextInt(Size()) + 0;

                                    if (maze[m][n] == 0) {
                                        arr[m][n] = 2;
                                    } else {
                                        m = row.nextInt(Size()) + 0;
                                        n = col.nextInt(Size()) + 0;

                                        if (maze[m][n] == 0) {
                                            arr[m][n] = 2;
                                        } else {
                                            // top ampe ini aje deh, capek kepanjangan ngecek nya
                                            // soalnya ketika hasil random untuk array dengan nilai 2, namun
                                            // menimpa array 1,3 atau 4 maka harus random lagi sampai ketemu array
                                            // dengan nilai 0 alias jalanan
                                            arr[i][j] = maze[i][j];
                                        }
                                    }
                                }

                            }

                        }

                    }

                }
            }
        }

        return arr;
    }

    public int[][] GenerateDestination() {
        int[][] arr;
        arr = new int[Size()][Size()];

        Random row = new Random();
        Random col = new Random();

        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {

                if (maze[i][j] == 0) {
                    arr[i][j] = 0;
                } else if (maze[i][j] == 1) {
                    arr[i][j] = 1;
                } else if (maze[i][j] == 2) {
                    arr[i][j] = 2;
                } else if (maze[i][j] == 4) {
                    arr[i][j] = 4;
                }
            }
        }

        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {

                if (maze[i][j] == 3) {
                    int m = row.nextInt(Size()) + 0;
                    int n = col.nextInt(Size()) + 0;

                    if (maze[m][n] == 0) {
                        arr[m][n] = 3;
                    } else {
                        m = row.nextInt(Size()) + 0;
                        n = col.nextInt(Size()) + 0;

                        if (maze[m][n] == 0) {
                            arr[m][n] = 3;
                        } else {
                            m = row.nextInt(Size()) + 0;
                            n = col.nextInt(Size()) + 0;

                            if (maze[m][n] == 0) {
                                arr[m][n] = 3;
                            } else {
                                m = row.nextInt(Size()) + 0;
                                n = col.nextInt(Size()) + 0;

                                if (maze[m][n] == 0) {
                                    arr[m][n] = 3;
                                } else {
                                    m = row.nextInt(Size()) + 0;
                                    n = col.nextInt(Size()) + 0;

                                    if (maze[m][n] == 0) {
                                        arr[m][n] = 3;
                                    } else {
                                        m = row.nextInt(Size()) + 0;
                                        n = col.nextInt(Size()) + 0;

                                        if (maze[m][n] == 0) {
                                            arr[m][n] = 3;
                                        } else {
                                            // top ampe ini aje deh, capek kepanjangan ngecek nya
                                            // soalnya ketika hasil random untuk array dengan nilai 2, namun
                                            // menimpa array 1,3 atau 4 maka harus random lagi sampai ketemu array
                                            // dengan nilai 0 alias jalanan
                                            arr[i][j] = maze[i][j];
                                        }
                                    }
                                }

                            }

                        }

                    }

                }
            }
        }

        return arr;
    }

    public int[][] GenerateCourierPosition() {
        int[][] arr;
        arr = new int[Size()][Size()];
        Random row = new Random();
        Random col = new Random();
        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {

                if (maze[i][j] == 0) {
                    arr[i][j] = 0;
                } else if (maze[i][j] == 1) {
                    arr[i][j] = 1;
                } else if (maze[i][j] == 2) {
                    arr[i][j] = 2;
                } else if (maze[i][j] == 3) {
                    arr[i][j] = 3;
                }
            }
        }

        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {

                if (maze[i][j] == 4) {
                    int m = row.nextInt(Size()) + 0;
                    int n = col.nextInt(Size()) + 0;

                    if (maze[m][n] == 0) {
                        arr[m][n] = 4;
                    } else {
                        m = row.nextInt(Size()) + 0;
                        n = col.nextInt(Size()) + 0;

                        if (maze[m][n] == 0) {
                            arr[m][n] = 4;
                        } else {
                            m = row.nextInt(Size()) + 0;
                            n = col.nextInt(Size()) + 0;

                            if (maze[m][n] == 0) {
                                arr[m][n] = 4;
                            } else {
                                m = row.nextInt(Size()) + 0;
                                n = col.nextInt(Size()) + 0;

                                if (maze[m][n] == 0) {
                                    arr[m][n] = 4;
                                } else {
                                    m = row.nextInt(Size()) + 0;
                                    n = col.nextInt(Size()) + 0;

                                    if (maze[m][n] == 0) {
                                        arr[m][n] = 4;
                                    } else {
                                        m = row.nextInt(Size()) + 0;
                                        n = col.nextInt(Size()) + 0;

                                        if (maze[m][n] == 0) {
                                            arr[m][n] = 4;
                                        } else {
                                            // top ampe ini aje deh, capek kepanjangan ngecek nya
                                            // soalnya ketika hasil random untuk array dengan nilai 2, namun
                                            // menimpa array 1,3 atau 4 maka harus random lagi sampai ketemu array
                                            // dengan nilai 0 alias jalanan
                                            arr[i][j] = maze[i][j];
                                        }
                                    }
                                }

                            }

                        }

                    }

                }
            }
        }

        return arr;
    }

    public int[][] Working() {
        int[][] arr;
        arr = new int[Size()][Size()];

        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {

                if (maze[i][j] == 0) {
                    arr[i][j] = 0;
                } else if (maze[i][j] == 1) {
                    arr[i][j] = 1;
                } else if (maze[i][j] == 2) {
                    arr[i][j] = 2;
                } else if (maze[i][j] == 3) {
                    arr[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                if (maze[i][j] == 4) {
                    // courier
                    int x = GetIndexBarisCourier();
                    int y = GetIndexColomCourier();
                    // resource
                    int p = GetIndexBarisResource();
                    int q = GetIndexColomResource();

                    // ii == courier && iv == resource
                    if (x < p && y < q) {
                        if ((maze[i - 1][j] == 0 || maze[i + 1][j] == 1 || maze[i][j + 1] == 1 || maze[i][j - 1] == 1)
                                && maze[i - 1][j] != 1) {
                            arr[i - 1][j] = 4;
                        }

                    }
                    // iii == courier && i == resource
                    else if (x > p && y < q) {

                    }
                    // ii == courier && i == resource
                    else if (x == p && y < q) {

                    }
                    // i == courier && iii == resource
                    else if (x < p && y > q) {

                    }
                    // iv == courier && ii == resource
                    else if (x > p && y > q) {

                    }

                    // i == courier && ii == resource
                    else if (x == p && y > q) {

                    }
                    // ii = courier && iii == resource
                    else if (x < p && y == q) {

                    }
                    // iii = courier && ii == resource
                    else if (x > p && y == q) {

                    }
                }
            }
        }

        return arr;

    }

    public int GetIndexBarisCourier() {
        int a = 0;
        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                if (maze[i][j] == 4) {
                    a = i;
                }
            }
        }
        return a;
    }

    public int GetIndexColomCourier() {
        int b = 0;
        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                if (maze[i][j] == 4) {
                    b = j;
                }
            }
        }
        return b;
    }

    public int GetIndexBarisResource() {
        int a = 0;
        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                if (maze[i][j] == 2) {
                    a = i;
                }
            }
        }
        return a;
    }

    public int GetIndexColomResource() {
        int b = 0;
        for (int i = 0; i < Size(); i++) {
            for (int j = 0; j < Size(); j++) {
                if (maze[i][j] == 2) {
                    b = j;
                }
            }
        }
        return b;
    }

    // draw the maze on the JFrame
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(70, 70); // move the maze to begin at 70 from x and 70 from y

        for (int row = 0; row < Size(); row++) {
            for (int col = 0; col < Size(); col++) {
                Color color;
                switch (maze[row][col]) {
                    case 1:
                        color = Color.darkGray; // block (black)
                        break;
                    case 3:
                        color = Color.RED; // goal (red)
                        break;
                    case 4:
                        color = Color.WHITE; // goal (red)
                        break;
                    case 2:
                        color = Color.darkGray; // initial state (yellow)

                        break;
                    default:
                        color = Color.WHITE; // white free space 0 (white)
                }

                g.setColor(color);
                g.fillRect(20 * col, 20 * row, 20, 20); // fill rectangular with color
                g.setColor(Color.BLUE); // the border rectangle color
                g.drawRect(20 * col, 20 * row, 20, 20); // draw rectangular with color

                if (maze[row][col] == 2) {

                    g.setColor(Color.red);
                    g.fillRect((int) ((20 * col) + (2.5)), (int) ((20 * row) + 2), (int) (13), (int) (4));
                    g.setColor(Color.white);
                    g.fillRect((int) ((20 * col) + (2.5)), (int) ((20 * row) + 6), (int) (13), (int) (4));
                    g.setColor(Color.GREEN);
                    g.fillRect((int) ((20 * col) + (2.5)), (int) ((20 * row) + 2), 2, 19);
                }

                if (maze[row][col] == 4) {
                    g.setColor(Color.BLACK);
                    g.drawRoundRect((int) ((20 * col) + (2.5)), (int) ((20 * row) + 6.5), (int) (16), 10, 5, 5);
                    g.setColor(Color.BLACK);
                    g.drawRoundRect((int) ((20 * col) + (2.5)), (int) ((20 * row) + 6.5), (int) (16), 10, 5, 5);
                    g.setColor(Color.darkGray);
                    g.drawRoundRect((int) ((20 * col) + (2.5)), (int) ((20 * row) + 6.5), (int) (5), 10, 5, 5);
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