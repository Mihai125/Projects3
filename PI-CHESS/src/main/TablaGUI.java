package main;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import piese.Alianta;
import piese.Cal;
import piese.Nebun;
import piese.Piesa;
import piese.Pion;
import piese.Turn;
import tabla.Tabla;

public class TablaGUI extends JFrame {

    Connection cn;
    Tabla tabla;
    List<SpatiuGUI> boardTiles = new ArrayList<>();
    static Piesa piesaSelectata, piesaDestinatie;
    static List<Integer> mutariCorecte;
    static List<String> pieseLuate = new ArrayList<>();
    static int lastCursor;
    static Alianta jucator = Alianta.ALB;

    public TablaGUI(Tabla tabla) {
        connection();
        this.tabla = tabla;

        //seteaza ecran
        this.setSize(680, 660);
        this.setResizable(false);

        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new GridLayout(8, 8, 2, 2));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(getContentPane(),
                        "Vrei sa salvezi jocul?", "",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                    salveazaJoc(tabla);
                    System.exit(0);
                } else {
                    System.exit(0);
                }
            }

        });

        //   this.setUndecorated(true);
        //seteaza spatiile pe ecran 
        for (int i = 0; i < 64; i++) {
            SpatiuGUI spatiuGUI = new SpatiuGUI(this, i, tabla.getPiesa(i));
            this.boardTiles.add(spatiuGUI);
            this.add(spatiuGUI);
        }

        //coloreaza spatiile
        setDefaultBackground();

        //seteaza pozitii initiale
        pozitiiStandard();

        //interactiune cu tabla
        this.addMouseListener(new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {

                int mult = 0;
                int x = (e.getX() - 20) / 80;
                if (x >= 8) {
                    x--;
                }
                int y = (e.getY() - 38) / 80;
                if (y >= 8) {
                    y--;
                }
                int c = 0;
                while (c < y) {
                    mult += 8;
                    c++;
                }

                int cursor = x + mult;

                lastCursor = cursor;

                piesaSelectata = tabla.getPiesa(cursor);
                if (piesaSelectata != null) {
                    mutariCorecte = piesaSelectata.mutariPosibile();

                    for (int i : mutariCorecte) {
                        if (mutariCorecte.contains(i)) {
                            if (tabla.getPiesa(i) != null) {
                                getSpatiuGUI(i).setBackground(Color.RED);
                            } else {
                                getSpatiuGUI(i).setBackground(Color.GREEN);
                            }

                        }
                    }
                } else {
                    System.out.println("Spatiu gol");
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int mult = 0;
                int x = (e.getX() - 20) / 80;
                if (x >= 8) {
                    x--;
                }
                int y = (e.getY() - 38) / 80;
                if (y >= 8) {
                    y--;
                }
                int c = 0;
                while (c < y) {
                    mult += 8;
                    c++;
                }

                int cursor = x + mult;

                if (piesaSelectata != null) {
                    if (mutariCorecte.contains(cursor)) {

                        if (piesaSelectata.esteAlba() && jucator == Alianta.ALB || piesaSelectata.esteNeagra() && jucator == Alianta.NEGRU) {
                            piesaDestinatie = tabla.getPiesa(cursor);
                            if (piesaDestinatie != null) {
                                pieseLuate.add(piesaDestinatie.toString());
                            }

                            declaraCastigator();

                            tabla.mutare(piesaSelectata, cursor);
                            getSpatiuGUI(lastCursor).elibereazaSpatiu();
                            getSpatiuGUI(cursor).arataPiesa(tabla);

                            if (tabla.getPiesa(cursor) instanceof Pion && tabla.getPiesa(cursor).margineSus(cursor)) {
                                getSpatiuGUI(cursor).elibereazaSpatiu();
                                tabla.getSpatii().get(cursor).setPiesa(new Cal(cursor, jucator, tabla));
                                getSpatiuGUI(cursor).arataPiesa(tabla);
                            } else if (tabla.getPiesa(cursor) instanceof Pion && tabla.getPiesa(cursor).margineJos(cursor)) {
                                getSpatiuGUI(cursor).elibereazaSpatiu();
                                tabla.getSpatii().get(cursor).setPiesa(new Cal(cursor, jucator, tabla));
                                getSpatiuGUI(cursor).arataPiesa(tabla);
                            }

                            if (jucator == Alianta.ALB) {
                                jucator = Alianta.NEGRU;
                            } else if (jucator == Alianta.NEGRU) {
                                jucator = Alianta.ALB;
                            }
                        }

                    } else {
                        System.out.println("Mutare incorecta!");
                    }
                }

                mutariCorecte = null;
                setDefaultBackground();

            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

    }

    public SpatiuGUI getSpatiuGUI(int co) {
        return boardTiles.get(co);
    }

    public void setDefaultBackground() {
        Color white = Color.decode("#FFFACD");
        Color black = Color.decode("#593E1A");
        Color col = white;
        boolean randNou = false;
        for (int i = 0; i < 64; i++) {
            if (i % 8 == 0) {
                randNou = true;
            } else {
                randNou = false;
            }

            if (randNou == false) {
                if (col == white) {
                    col = black;
                } else {
                    col = white;
                }
            }
            this.getSpatiuGUI(i).setBackground(col);
        }
    }

    public void pozitiiStandard() {
        for (int i = 0; i < 64; i++) {
            this.getSpatiuGUI(i).arataPiesa(tabla);
        }
    }

    public void declaraCastigator() {
        if (pieseLuate.contains("BK")) {
            JOptionPane.showMessageDialog(this, "Alb este castigator!!");
            this.dispose();
        } else if (pieseLuate.contains("WK")) {
            JOptionPane.showMessageDialog(this, "Negru este castigator!!");
            this.dispose();
        }

    }

    public void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chessgame", "root", "");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Connection is close!");
        }
    }

    public void salveazaJoc(Tabla tabla) {
        connection();
        try {
            for (int i = 0; i < 64; i++) {

                if (this.tabla.getSpatii().get(i).esteOcupat()) {

                    String tip = tabla.getPiesa(i).toString();
                    int pozitie = i;

                    PreparedStatement ps = cn.prepareStatement("INSERT into chessboard (type, position) values (?,?)");
                    ps.setString(1, tip);
                    ps.setInt(2, pozitie);
                    ps.execute();

                }
            }
            cn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

//------------------------------------------------------------------------------------------------------------------------------------
    public class SpatiuGUI extends JPanel {

        TablaGUI tablaGUI;
        int spatiuId;

        public SpatiuGUI(TablaGUI tablaGUI, int spatiuId, Piesa piesa) {
            super(new GridBagLayout());
            this.tablaGUI = tablaGUI;
            this.spatiuId = spatiuId;

        }

        public void arataPiesa(Tabla tabla) {
            removeAll();
            if (tabla.getPiesa(spatiuId) != null) {
                try {
                    BufferedImage image = ImageIO.read(new File("imagini/" + tabla.getPiesa(spatiuId).toString() + ".gif"));
                    this.tablaGUI.getSpatiuGUI(spatiuId).add(new JLabel(new ImageIcon(image)));

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            repaint();
            validate();
        }

        public void elibereazaSpatiu() {
            this.removeAll();
            this.repaint();
            this.validate();
        }
    }
}
