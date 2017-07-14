package calendar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.ToolTipManager;
/**
 *
 * @author Dennis
 */
public class MyCalendar extends javax.swing.JFrame {

    private Login parent;
    private String login;
    private Point position;
    private List<JPanel> panels;
    private List<MyNote> notes;
    private ArrayList<String> steps;
    private ImageIcon textIcon;
    private ImageIcon walker;
    private Calendar c = Calendar.getInstance();
    private int DAYS_IN_MONTH;
    private int FIRST_PANEL;
    
    private int CURRENT_MONTH = c.get(Calendar.MONTH);
    private int CURRENT_YEAR = c.get(Calendar.YEAR);
    private int CURRENT_DAY = c.get(Calendar.DAY_OF_MONTH);
    
    private final int TODAY_MONTH = CURRENT_MONTH;
    private final int TODAY_YEAR = CURRENT_YEAR;
    private final int TODAY = CURRENT_DAY;
    private int INITIAL_DELAY = 0;
    
    private File file;
    private File settings;
    
    public MyCalendar(Login parent, String login) {
        
        MyCalendar myCal = this;
        panels = new ArrayList();
        this.parent = parent;
        initComponents();
        
        textIcon = new javax.swing.ImageIcon(getClass().getResource("/calendar/images/text.png"));
        walker = new javax.swing.ImageIcon(getClass().getResource("/calendar/images/walker.png"));
        this.setLocationRelativeTo(null);
        this.login = login;
        
        downloadSteps();
        setCurrentDate();
        
        file = new File("accounts/" + login);
        if(!file.exists()){
            file.mkdir();
        }
        /*
        settings = new File("accounts/" + login + "/settings.ini");
        if(!settings.exists())
            try {
                settings.createNewFile();
                BufferedWriter settingsFileWriter = new BufferedWriter(new FileWriter(settings));
                settingsFileWriter.write("InintialDelay=0");
                
                settingsFileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(MyCalendar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            BufferedReader settingsFileReader = new BufferedReader(new FileReader(settings));
            String setting = settingsFileReader.readLine();
            StringTokenizer st = new StringTokenizer(setting, "=");
            System.out.println(setting);
            st.nextToken();
            INITIAL_DELAY = Integer.getInteger(st.nextToken());
            settingsFileReader.close();
        } catch(IOException ex) {
            Logger.getLogger(MyCalendar.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        addMouseListener(new MouseAdapter() {                                   // Позиция мыши
            public void mousePressed(MouseEvent e) {                            
                position = e.getPoint();
                getComponentAt(position);
            }
        });                                                                     // -//-
        
        addMouseMotionListener(new MouseMotionAdapter() {                       // Перетаскивание по экрану
            @Override
            public void mouseDragged(MouseEvent e) {
 
                int thisX = myCal.getLocation().x;
                int thisY = myCal.getLocation().y;
 
                int xMoved = (thisX + e.getX()) - (thisX + position.x);
                int yMoved = (thisY + e.getY()) - (thisY + position.y);
 
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                myCal.setLocation(X, Y);
            }
        });                                                                     // -//-
        
        panels.add(jPanel1);
        panels.add(jPanel2);
        panels.add(jPanel3);
        panels.add(jPanel4);
        panels.add(jPanel5);
        panels.add(jPanel6);
        panels.add(jPanel7);
        panels.add(jPanel8);
        panels.add(jPanel9);
        panels.add(jPanel10);
        panels.add(jPanel11);
        panels.add(jPanel12);
        panels.add(jPanel13);
        panels.add(jPanel14);
        panels.add(jPanel15);
        panels.add(jPanel16);
        panels.add(jPanel17);
        panels.add(jPanel18);
        panels.add(jPanel19);
        panels.add(jPanel20);
        panels.add(jPanel21);
        panels.add(jPanel22);
        panels.add(jPanel23);
        panels.add(jPanel24);
        panels.add(jPanel25);
        panels.add(jPanel26);
        panels.add(jPanel27);
        panels.add(jPanel28);
        panels.add(jPanel29);
        panels.add(jPanel30);
        panels.add(jPanel31);
        panels.add(jPanel32);
        panels.add(jPanel33);
        panels.add(jPanel34);
        panels.add(jPanel35);
        panels.add(jPanel36);
        panels.add(jPanel37);
        panels.add(jPanel38);
        panels.add(jPanel39);
        panels.add(jPanel40);
        panels.add(jPanel41);
        panels.add(jPanel42);
        jPanel43.setVisible(false);
        
        ToolTipManager.sharedInstance().registerComponent(jButton1);
        ToolTipManager.sharedInstance().registerComponent(jButton2);
        ToolTipManager.sharedInstance().registerComponent(jButton3);
        ToolTipManager.sharedInstance().registerComponent(jButton4);
        ToolTipManager.sharedInstance().registerComponent(jButton12);
        ToolTipManager.sharedInstance().registerComponent(jButton13);           // Уменьшение задержки всплывающих подсказок
        ToolTipManager.sharedInstance().setInitialDelay(INITIAL_DELAY);                     // -//-

        Font font = new Font("Arial", 1, 30);
        Font font1 = new Font("Arial", 0, 14);
        
        Timer timer;
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g;
                
                int date = 0;
                //int[] categories;
                for (int i = FIRST_PANEL; i < FIRST_PANEL + DAYS_IN_MONTH; i++){
                    date++;
                    g = panels.get(i-1).getGraphics();
                    g.setFont(font);
                    g.drawString(Integer.toString(date), 5, 30);
                    /*categories = notes.get(i-1).getCategories();
                    if(categories != null)
                        for (int j = 0; j < categories.length; j++){
                            switch(categories[j]){
                                case 0: g.setColor(Color.green); break;
                                case 1: g.setColor(Color.yellow); break;
                                case 2: g.setColor(Color.red); break;
                                case 3: g.setColor(Color.blue); break;
                                case 4: g.setColor(Color.orange); break;
                            }
                            g.fillRect(categories[j]*19, 1, 19, 19);
                        }*/
                    try {
                        if(notes.get(i-1).hasNote())
                            g.drawImage(textIcon.getImage(), 58, 38, rootPane);
                    } catch (IOException ex) {
                        Logger.getLogger(MyCalendar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    g.setFont(font1);
                    if(notes.get(i-1).hasSteps()){
                        g.drawImage(walker.getImage(), 0, 60, rootPane);
                        g.drawString(notes.get(i-1).getSteps(), 22, 75);
                    }
                }
            }
        });
        timer.start();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Выйти");
        jButton1.setToolTipText("Сменить пользователя");
        jButton1.setPreferredSize(new java.awt.Dimension(65, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Текст");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 30));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 260, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 100, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 480, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel7MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 560, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, -1, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel8MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel9MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, -1, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel10MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel10MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 100, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel11MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel11MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, -1, -1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel12MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel12MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 400, -1, -1));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel13MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel13MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, -1, -1));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel14MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel14MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 560, -1, -1));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel15MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel15MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel16MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel16MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, -1));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel17MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel17MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 100, -1));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel18MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel18MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel19MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel19MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, -1, -1));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel20MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel20MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, -1, -1));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel21MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel21MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 560, -1, -1));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel22MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel22MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel22MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, -1, -1));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel23MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel23MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel23MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel24MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel24MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel24MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 100, -1));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel25MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel25MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel25MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, -1, -1));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel26MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel26MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, -1, -1));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel27.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel27MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel27MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, -1, -1));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel28.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel28MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel28MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel28MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 560, -1, -1));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel29.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel29MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel29MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel29MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, -1, -1));

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel32.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel32MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel32MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel32MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, -1, -1));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel34.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel34MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel34MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel34MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 480, -1, -1));

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel33.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel33MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel33MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel33MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 400, 100, -1));

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel35.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel35MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel35MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel35MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 560, -1, -1));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel31.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel31MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel31MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel31MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, -1, -1));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel30.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel30MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel30MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel30MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, -1, -1));

        jButton3.setText(">>");
        jButton3.setToolTipText("Следующий месяц");
        jButton3.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, -1, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setOpaque(true);
        jLabel3.setPreferredSize(new java.awt.Dimension(2, 0));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, 560));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Пн");
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 80));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Вт");
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 80));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ср");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 80));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Чт");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 80));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Пт");
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 80));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Сб");
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 80));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Вс");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 80));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendar/images/today.png"))); // NOI18N
        jButton4.setToolTipText("Выбрать месяц");
        jButton4.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, -1, -1));

        jButton12.setText("Х");
        jButton12.setToolTipText("Выйти из программы");
        jButton12.setPreferredSize(new java.awt.Dimension(65, 25));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(959, 0, -1, -1));

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel36.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel36MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel36MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel36MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, -1, -1));

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel38.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel38MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel38MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel38MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, -1, -1));

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel39.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel39MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel39MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel39MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, -1, -1));

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel40.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel40MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel40MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel40MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 400, 100, -1));

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel42.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel42MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel42MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel42MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 560, -1, -1));

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel41.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel41MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel41MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel41MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, -1, -1));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel37.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel37MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel37MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel37MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, -1, -1));

        jPanel44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel44.setPreferredSize(new java.awt.Dimension(1024, 700));
        jPanel44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel43.setBackground(new java.awt.Color(153, 153, 153));
        jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel43.setPreferredSize(new java.awt.Dimension(250, 230));
        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("2015");
        jLabel10.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanel43.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jButton10.setText("<<");
        jButton10.setPreferredSize(new java.awt.Dimension(55, 20));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel43.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jButton11.setText(">>");
        jButton11.setPreferredSize(new java.awt.Dimension(55, 20));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel43.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Январь");
        jLabel11.setOpaque(true);
        jLabel11.setPreferredSize(new java.awt.Dimension(55, 20));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });
        jPanel43.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Февраль");
        jLabel12.setOpaque(true);
        jLabel12.setPreferredSize(new java.awt.Dimension(55, 20));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel12MousePressed(evt);
            }
        });
        jPanel43.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Март");
        jLabel13.setOpaque(true);
        jLabel13.setPreferredSize(new java.awt.Dimension(55, 20));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel13MousePressed(evt);
            }
        });
        jPanel43.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Апрель");
        jLabel14.setOpaque(true);
        jLabel14.setPreferredSize(new java.awt.Dimension(55, 20));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel14MousePressed(evt);
            }
        });
        jPanel43.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Май");
        jLabel15.setOpaque(true);
        jLabel15.setPreferredSize(new java.awt.Dimension(55, 20));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel15MousePressed(evt);
            }
        });
        jPanel43.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Июнь");
        jLabel16.setOpaque(true);
        jLabel16.setPreferredSize(new java.awt.Dimension(55, 20));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel16MousePressed(evt);
            }
        });
        jPanel43.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Июль");
        jLabel17.setOpaque(true);
        jLabel17.setPreferredSize(new java.awt.Dimension(55, 20));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel17MousePressed(evt);
            }
        });
        jPanel43.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Август");
        jLabel18.setOpaque(true);
        jLabel18.setPreferredSize(new java.awt.Dimension(55, 20));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel18MousePressed(evt);
            }
        });
        jPanel43.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Сентябрь");
        jLabel19.setOpaque(true);
        jLabel19.setPreferredSize(new java.awt.Dimension(57, 20));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel19MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel19MousePressed(evt);
            }
        });
        jPanel43.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Октябрь");
        jLabel20.setOpaque(true);
        jLabel20.setPreferredSize(new java.awt.Dimension(57, 20));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel20MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel20MousePressed(evt);
            }
        });
        jPanel43.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, -1));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Ноябрь");
        jLabel21.setOpaque(true);
        jLabel21.setPreferredSize(new java.awt.Dimension(57, 20));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel21MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel21MousePressed(evt);
            }
        });
        jPanel43.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Декабрь");
        jLabel22.setOpaque(true);
        jLabel22.setPreferredSize(new java.awt.Dimension(57, 20));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel22MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel22MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel22MousePressed(evt);
            }
        });
        jPanel43.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, -1));

        jPanel44.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, -1, -1));

        jButton5.setText("Настройки");
        jPanel44.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 130, -1));

        jButton6.setText("Экспорт (в docx)");
        jPanel44.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, 130, -1));

        jButton2.setText("<<");
        jButton2.setToolTipText("Предыдущий месяц");
        jButton2.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel44.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendar/images/date.png"))); // NOI18N
        jButton13.setToolTipText("Перейти к текущему месяцу");
        jButton13.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel44.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        jButton14.setText("Импорт шагов");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel44.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, 130, -1));

        jButton7.setText("Сделать заметку!");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel44.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 130, -1));

        getContentPane().add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(jPanel43.isVisible())
            jPanel43.setVisible(false);
        else jPanel43.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        parent.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CURRENT_MONTH++;
        if(CURRENT_MONTH > 11){
            CURRENT_YEAR++;
            CURRENT_MONTH = 0;
        }
        setCurrentDate();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CURRENT_MONTH--;
        if(CURRENT_MONTH < 0){
            CURRENT_YEAR--;
            CURRENT_MONTH = 11;
        }
        setCurrentDate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int year = Integer.parseInt(jLabel10.getText());
        year--;
        jLabel10.setText(Integer.toString(year));
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int year = Integer.parseInt(jLabel10.getText());
        year++;
        jLabel10.setText(Integer.toString(year));
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 0;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel11MousePressed

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        jLabel11.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        jLabel11.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 1;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel12MousePressed

    private void jLabel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 2;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel13MousePressed

    private void jLabel14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 3;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel14MousePressed

    private void jLabel15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 4;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel15MousePressed

    private void jLabel16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 5;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel16MousePressed

    private void jLabel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 6;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel17MousePressed

    private void jLabel18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 7;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel18MousePressed

    private void jLabel19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 8;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel19MousePressed

    private void jLabel20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 9;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel20MousePressed

    private void jLabel21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 10;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel21MousePressed

    private void jLabel22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MousePressed
        int year = Integer.parseInt(jLabel10.getText());
        CURRENT_YEAR = year;
        CURRENT_MONTH = 11;
        setCurrentDate();
        jPanel43.setVisible(false);
    }//GEN-LAST:event_jLabel22MousePressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        CURRENT_MONTH = TODAY_MONTH;
        CURRENT_YEAR = TODAY_YEAR;
        setCurrentDate();
        jLabel10.setText(Integer.toString(TODAY_YEAR));
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        jLabel12.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        jLabel13.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        jLabel14.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        jLabel15.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        jLabel16.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        jLabel17.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        jLabel18.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseEntered
        jLabel19.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel19MouseEntered

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        jLabel20.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jLabel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseEntered
        jLabel21.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel21MouseEntered

    private void jLabel22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseEntered
        jLabel22.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jLabel22MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        jLabel12.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        jLabel13.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel13MouseExited

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        jLabel14.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        jLabel15.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel15MouseExited

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        jLabel16.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel16MouseExited

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        jLabel17.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel17MouseExited

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        jLabel18.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel18MouseExited

    private void jLabel19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseExited
        jLabel19.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel19MouseExited

    private void jLabel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseExited
        jLabel20.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel20MouseExited

    private void jLabel21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseExited
        jLabel21.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel21MouseExited

    private void jLabel22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseExited
        jLabel22.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel22MouseExited

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        if(notes.get(panels.indexOf(jPanel1)).getActivity())
            jPanel1.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited
        jPanel1.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel1MouseExited

    private void jPanel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseEntered
        if(notes.get(panels.indexOf(jPanel17)).getActivity())
            jPanel17.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel17MouseEntered

    private void jPanel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseExited
        jPanel17.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel17MouseExited

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        if(notes.get(panels.indexOf(jPanel2)).getActivity())
            jPanel2.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel2MouseEntered

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        if(notes.get(panels.indexOf(jPanel3)).getActivity())
            jPanel3.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseExited
        jPanel2.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel2MouseExited

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        jPanel3.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        if(notes.get(panels.indexOf(jPanel4)).getActivity())
            jPanel4.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        if(notes.get(panels.indexOf(jPanel5)).getActivity())
            jPanel5.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        if(notes.get(panels.indexOf(jPanel6)).getActivity())
            jPanel6.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
        if(notes.get(panels.indexOf(jPanel7)).getActivity())
            jPanel7.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseEntered
        if(notes.get(panels.indexOf(jPanel13)).getActivity())
            jPanel13.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel13MouseEntered

    private void jPanel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseEntered
        if(notes.get(panels.indexOf(jPanel14)).getActivity())
            jPanel14.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel14MouseEntered

    private void jPanel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseEntered
        if(notes.get(panels.indexOf(jPanel20)).getActivity())
            jPanel20.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel20MouseEntered

    private void jPanel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseEntered
        if(notes.get(panels.indexOf(jPanel21)).getActivity())
            jPanel21.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel21MouseEntered

    private void jPanel27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel27MouseEntered
        if(notes.get(panels.indexOf(jPanel27)).getActivity())
            jPanel27.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel27MouseEntered

    private void jPanel28MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel28MouseEntered
        if(notes.get(panels.indexOf(jPanel28)).getActivity())
            jPanel28.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel28MouseEntered

    private void jPanel34MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel34MouseEntered
        if(notes.get(panels.indexOf(jPanel34)).getActivity())
            jPanel34.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel34MouseEntered

    private void jPanel35MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel35MouseEntered
        if(notes.get(panels.indexOf(jPanel35)).getActivity())
            jPanel35.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel35MouseEntered

    private void jPanel41MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel41MouseEntered
        if(notes.get(panels.indexOf(jPanel41)).getActivity())
            jPanel41.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel41MouseEntered

    private void jPanel42MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel42MouseEntered
        if(notes.get(panels.indexOf(jPanel42)).getActivity())
            jPanel42.setBackground(new Color(255,153,153));
    }//GEN-LAST:event_jPanel42MouseEntered

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        if(notes.get(panels.indexOf(jPanel8)).getActivity())
            jPanel8.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        if(notes.get(panels.indexOf(jPanel9)).getActivity())
            jPanel9.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
        if(notes.get(panels.indexOf(jPanel10)).getActivity())
            jPanel10.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseEntered
        if(notes.get(panels.indexOf(jPanel11)).getActivity())
            jPanel11.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel11MouseEntered

    private void jPanel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseEntered
        if(notes.get(panels.indexOf(jPanel12)).getActivity())
            jPanel12.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel12MouseEntered

    private void jPanel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseEntered
        if(notes.get(panels.indexOf(jPanel15)).getActivity())
            jPanel15.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel15MouseEntered

    private void jPanel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseEntered
        if(notes.get(panels.indexOf(jPanel16)).getActivity())
            jPanel16.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel16MouseEntered

    private void jPanel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseEntered
        if(notes.get(panels.indexOf(jPanel18)).getActivity())
            jPanel18.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel18MouseEntered

    private void jPanel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseEntered
        if(notes.get(panels.indexOf(jPanel19)).getActivity())
            jPanel19.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel19MouseEntered

    private void jPanel22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseEntered
        if(notes.get(panels.indexOf(jPanel22)).getActivity())
            jPanel22.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel22MouseEntered

    private void jPanel23MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseEntered
        if(notes.get(panels.indexOf(jPanel23)).getActivity())
            jPanel23.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel23MouseEntered

    private void jPanel24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseEntered
        if(notes.get(panels.indexOf(jPanel24)).getActivity())
            jPanel24.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel24MouseEntered

    private void jPanel25MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel25MouseEntered
        if(notes.get(panels.indexOf(jPanel25)).getActivity())
            jPanel25.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel25MouseEntered

    private void jPanel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel26MouseEntered
        if(notes.get(panels.indexOf(jPanel26)).getActivity())
            jPanel26.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel26MouseEntered

    private void jPanel29MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel29MouseEntered
        if(notes.get(panels.indexOf(jPanel29)).getActivity())
            jPanel29.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel29MouseEntered

    private void jPanel30MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel30MouseEntered
        if(notes.get(panels.indexOf(jPanel30)).getActivity())
            jPanel30.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel30MouseEntered

    private void jPanel31MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel31MouseEntered
        if(notes.get(panels.indexOf(jPanel31)).getActivity())
            jPanel31.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel31MouseEntered

    private void jPanel32MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel32MouseEntered
        if(notes.get(panels.indexOf(jPanel32)).getActivity())
            jPanel32.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel32MouseEntered

    private void jPanel33MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel33MouseEntered
        if(notes.get(panels.indexOf(jPanel33)).getActivity())
            jPanel33.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel33MouseEntered

    private void jPanel36MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel36MouseEntered
        if(notes.get(panels.indexOf(jPanel36)).getActivity())
            jPanel36.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel36MouseEntered

    private void jPanel37MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel37MouseEntered
        if(notes.get(panels.indexOf(jPanel37)).getActivity())
            jPanel37.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel37MouseEntered

    private void jPanel38MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel38MouseEntered
        if(notes.get(panels.indexOf(jPanel38)).getActivity())
            jPanel38.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel38MouseEntered

    private void jPanel39MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel39MouseEntered
        if(notes.get(panels.indexOf(jPanel39)).getActivity())
            jPanel39.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel39MouseEntered

    private void jPanel40MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel40MouseEntered
        if(notes.get(panels.indexOf(jPanel40)).getActivity())
            jPanel40.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_jPanel40MouseEntered

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
        jPanel4.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel4MouseExited

    private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseExited
        jPanel5.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel5MouseExited

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        jPanel6.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel6MouseExited

    private void jPanel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseExited
        jPanel7.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel7MouseExited

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        jPanel8.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel8MouseExited

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
        jPanel9.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel9MouseExited

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
        jPanel10.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel10MouseExited

    private void jPanel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseExited
        jPanel11.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel11MouseExited

    private void jPanel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseExited
        jPanel12.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel12MouseExited

    private void jPanel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseExited
        jPanel13.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel13MouseExited

    private void jPanel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseExited
        jPanel14.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel14MouseExited

    private void jPanel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseExited
        jPanel15.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel15MouseExited

    private void jPanel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseExited
        jPanel16.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel16MouseExited

    private void jPanel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseExited
        jPanel18.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel18MouseExited

    private void jPanel19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseExited
        jPanel19.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel19MouseExited

    private void jPanel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseExited
        jPanel20.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel20MouseExited

    private void jPanel21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseExited
        jPanel21.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel21MouseExited

    private void jPanel22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseExited
        jPanel22.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel22MouseExited

    private void jPanel23MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseExited
        jPanel23.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel23MouseExited

    private void jPanel24MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseExited
        jPanel24.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel24MouseExited

    private void jPanel25MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel25MouseExited
        jPanel25.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel25MouseExited

    private void jPanel26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel26MouseExited
        jPanel26.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel26MouseExited

    private void jPanel27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel27MouseExited
        jPanel27.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel27MouseExited

    private void jPanel28MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel28MouseExited
        jPanel28.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel28MouseExited

    private void jPanel29MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel29MouseExited
        jPanel29.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel29MouseExited

    private void jPanel30MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel30MouseExited
        jPanel30.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel30MouseExited

    private void jPanel31MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel31MouseExited
        jPanel31.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel31MouseExited

    private void jPanel32MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel32MouseExited
        jPanel32.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel32MouseExited

    private void jPanel33MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel33MouseExited
        jPanel33.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel33MouseExited

    private void jPanel34MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel34MouseExited
        jPanel34.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel34MouseExited

    private void jPanel35MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel35MouseExited
        jPanel35.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel35MouseExited

    private void jPanel36MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel36MouseExited
        jPanel36.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel36MouseExited

    private void jPanel37MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel37MouseExited
        jPanel37.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel37MouseExited

    private void jPanel38MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel38MouseExited
        jPanel38.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel38MouseExited

    private void jPanel39MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel39MouseExited
        jPanel39.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel39MouseExited

    private void jPanel40MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel40MouseExited
        jPanel40.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel40MouseExited

    private void jPanel41MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel41MouseExited
        jPanel41.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel41MouseExited

    private void jPanel42MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel42MouseExited
        jPanel42.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel42MouseExited

    private void jPanel18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MousePressed
        if(notes.get(panels.indexOf(jPanel18)).getActivity())
            createNote(jPanel18);
    }//GEN-LAST:event_jPanel18MousePressed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        if(notes.get(panels.indexOf(jPanel1)).getActivity())
            createNote(jPanel1); 
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        if(notes.get(panels.indexOf(jPanel2)).getActivity())
            createNote(jPanel2); 
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        if(notes.get(panels.indexOf(jPanel3)).getActivity())
            createNote(jPanel3); 
    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        if(notes.get(panels.indexOf(jPanel4)).getActivity())
            createNote(jPanel4);
    }//GEN-LAST:event_jPanel4MousePressed

    private void jPanel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MousePressed
        if(notes.get(panels.indexOf(jPanel5)).getActivity())
            createNote(jPanel5);
    }//GEN-LAST:event_jPanel5MousePressed

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed
        if(notes.get(panels.indexOf(jPanel6)).getActivity())
            createNote(jPanel6); 
    }//GEN-LAST:event_jPanel6MousePressed

    private void jPanel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MousePressed
        if(notes.get(panels.indexOf(jPanel7)).getActivity())
            createNote(jPanel7); 
    }//GEN-LAST:event_jPanel7MousePressed

    private void jPanel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MousePressed
        if(notes.get(panels.indexOf(jPanel8)).getActivity())
            createNote(jPanel8);
    }//GEN-LAST:event_jPanel8MousePressed

    private void jPanel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MousePressed
        if(notes.get(panels.indexOf(jPanel9)).getActivity())
            createNote(jPanel9); 
    }//GEN-LAST:event_jPanel9MousePressed

    private void jPanel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MousePressed
        if(notes.get(panels.indexOf(jPanel10)).getActivity())
            createNote(jPanel10); 
    }//GEN-LAST:event_jPanel10MousePressed

    private void jPanel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MousePressed
        if(notes.get(panels.indexOf(jPanel11)).getActivity())
            createNote(jPanel11); 
    }//GEN-LAST:event_jPanel11MousePressed

    private void jPanel12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MousePressed
        if(notes.get(panels.indexOf(jPanel12)).getActivity())
            createNote(jPanel12); 
    }//GEN-LAST:event_jPanel12MousePressed

    private void jPanel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MousePressed
        if(notes.get(panels.indexOf(jPanel13)).getActivity())
            createNote(jPanel13);
    }//GEN-LAST:event_jPanel13MousePressed

    private void jPanel14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MousePressed
        if(notes.get(panels.indexOf(jPanel14)).getActivity())
            createNote(jPanel14); 
    }//GEN-LAST:event_jPanel14MousePressed

    private void jPanel15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MousePressed
        if(notes.get(panels.indexOf(jPanel15)).getActivity())
            createNote(jPanel15); 
    }//GEN-LAST:event_jPanel15MousePressed

    private void jPanel16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MousePressed
        if(notes.get(panels.indexOf(jPanel16)).getActivity())
            createNote(jPanel16);
    }//GEN-LAST:event_jPanel16MousePressed

    private void jPanel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MousePressed
        if(notes.get(panels.indexOf(jPanel17)).getActivity())
            createNote(jPanel17);
    }//GEN-LAST:event_jPanel17MousePressed

    private void jPanel19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MousePressed
        if(notes.get(panels.indexOf(jPanel19)).getActivity())
            createNote(jPanel19);
    }//GEN-LAST:event_jPanel19MousePressed

    private void jPanel20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MousePressed
        if(notes.get(panels.indexOf(jPanel20)).getActivity())
            createNote(jPanel20); 
    }//GEN-LAST:event_jPanel20MousePressed

    private void jPanel21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MousePressed
        if(notes.get(panels.indexOf(jPanel21)).getActivity())
            createNote(jPanel21);
    }//GEN-LAST:event_jPanel21MousePressed

    private void jPanel22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MousePressed
        if(notes.get(panels.indexOf(jPanel22)).getActivity())
            createNote(jPanel22); 
    }//GEN-LAST:event_jPanel22MousePressed

    private void jPanel23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MousePressed
        if(notes.get(panels.indexOf(jPanel23)).getActivity())
            createNote(jPanel23);
    }//GEN-LAST:event_jPanel23MousePressed

    private void jPanel24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MousePressed
        if(notes.get(panels.indexOf(jPanel24)).getActivity())
            createNote(jPanel24); 
    }//GEN-LAST:event_jPanel24MousePressed

    private void jPanel25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel25MousePressed
        if(notes.get(panels.indexOf(jPanel25)).getActivity())
            createNote(jPanel25); 
    }//GEN-LAST:event_jPanel25MousePressed

    private void jPanel26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel26MousePressed
        if(notes.get(panels.indexOf(jPanel26)).getActivity())
            createNote(jPanel26); 
    }//GEN-LAST:event_jPanel26MousePressed

    private void jPanel27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel27MousePressed
        if(notes.get(panels.indexOf(jPanel27)).getActivity())
            createNote(jPanel27); 
    }//GEN-LAST:event_jPanel27MousePressed

    private void jPanel28MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel28MousePressed
        if(notes.get(panels.indexOf(jPanel28)).getActivity())
            createNote(jPanel28); 
    }//GEN-LAST:event_jPanel28MousePressed

    private void jPanel29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel29MousePressed
        if(notes.get(panels.indexOf(jPanel29)).getActivity())
            createNote(jPanel29); 
    }//GEN-LAST:event_jPanel29MousePressed

    private void jPanel30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel30MousePressed
        if(notes.get(panels.indexOf(jPanel30)).getActivity())
            createNote(jPanel30); 
    }//GEN-LAST:event_jPanel30MousePressed

    private void jPanel31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel31MousePressed
        if(notes.get(panels.indexOf(jPanel31)).getActivity())
            createNote(jPanel31); 
    }//GEN-LAST:event_jPanel31MousePressed

    private void jPanel32MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel32MousePressed
        if(notes.get(panels.indexOf(jPanel32)).getActivity())
            createNote(jPanel32);
    }//GEN-LAST:event_jPanel32MousePressed

    private void jPanel33MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel33MousePressed
        if(notes.get(panels.indexOf(jPanel33)).getActivity())
            createNote(jPanel33); 
    }//GEN-LAST:event_jPanel33MousePressed

    private void jPanel34MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel34MousePressed
        if(notes.get(panels.indexOf(jPanel34)).getActivity())
            createNote(jPanel34);
    }//GEN-LAST:event_jPanel34MousePressed

    private void jPanel35MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel35MousePressed
        if(notes.get(panels.indexOf(jPanel35)).getActivity())
            createNote(jPanel35); 
    }//GEN-LAST:event_jPanel35MousePressed

    private void jPanel36MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel36MousePressed
        if(notes.get(panels.indexOf(jPanel36)).getActivity())
            createNote(jPanel36); 
    }//GEN-LAST:event_jPanel36MousePressed

    private void jPanel37MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel37MousePressed
        if(notes.get(panels.indexOf(jPanel37)).getActivity())
            createNote(jPanel37);
    }//GEN-LAST:event_jPanel37MousePressed

    private void jPanel38MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel38MousePressed
        if(notes.get(panels.indexOf(jPanel38)).getActivity())
            createNote(jPanel38); 
    }//GEN-LAST:event_jPanel38MousePressed

    private void jPanel39MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel39MousePressed
        if(notes.get(panels.indexOf(jPanel39)).getActivity())
            createNote(jPanel39); 
    }//GEN-LAST:event_jPanel39MousePressed

    private void jPanel40MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel40MousePressed
        if(notes.get(panels.indexOf(jPanel40)).getActivity())
            createNote(jPanel40);
    }//GEN-LAST:event_jPanel40MousePressed

    private void jPanel41MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel41MousePressed
        if(notes.get(panels.indexOf(jPanel41)).getActivity())
            createNote(jPanel41); 
    }//GEN-LAST:event_jPanel41MousePressed

    private void jPanel42MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel42MousePressed
        if(notes.get(panels.indexOf(jPanel42)).getActivity())
            createNote(jPanel42);
    }//GEN-LAST:event_jPanel42MousePressed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        ImportSteps imp = new ImportSteps(this, true);
        imp.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        createNote(panels.get(FIRST_PANEL + TODAY - 2));
    }//GEN-LAST:event_jButton7ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MyCalendar calendar = new MyCalendar(null, "");
                calendar.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                calendar.setVisible(true);
                calendar.setLocationRelativeTo(null);
             }
        });
    }
    
    private void setCurrentDate(){
        notes = new ArrayList();
        
        for(JPanel panel: panels){
                    panel.repaint();
        }
        
        boolean leap_year;
        if (CURRENT_YEAR%4 == 0) leap_year = true;
        else leap_year = false;
        
        switch(CURRENT_MONTH){
            case 0: jLabel1.setText("Январь, " + CURRENT_YEAR); DAYS_IN_MONTH = 31; break;
            case 2: jLabel1.setText("Март, " + CURRENT_YEAR); DAYS_IN_MONTH = 31; break;
            case 4: jLabel1.setText("Май, " + CURRENT_YEAR); DAYS_IN_MONTH = 31; break;
            case 6: jLabel1.setText("Июль, " + CURRENT_YEAR); DAYS_IN_MONTH = 31; break;
            case 7: jLabel1.setText("Август, " + CURRENT_YEAR); DAYS_IN_MONTH = 31; break;
            case 9: jLabel1.setText("Октябрь, " + CURRENT_YEAR); DAYS_IN_MONTH = 31; break;
            case 11: jLabel1.setText("Декабрь, " + CURRENT_YEAR); DAYS_IN_MONTH = 31; break;
            case 1:  {
                jLabel1.setText("Февраль, " + CURRENT_YEAR);
                if(leap_year)DAYS_IN_MONTH = 29;
                else DAYS_IN_MONTH = 28;
                break;
            }
            case 3: jLabel1.setText("Апрель, " + CURRENT_YEAR); DAYS_IN_MONTH = 30; break;
            case 5: jLabel1.setText("Июнь, " + CURRENT_YEAR); DAYS_IN_MONTH = 30; break;
            case 8: jLabel1.setText("Сентябрь, " + CURRENT_YEAR); DAYS_IN_MONTH = 30; break;
            case 10: jLabel1.setText("Ноябрь, " + CURRENT_YEAR); DAYS_IN_MONTH = 30; break;
        }
        
        c.set(Calendar.MONTH, CURRENT_MONTH);
        c.set(Calendar.YEAR, CURRENT_YEAR);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        while(dayOfMonth > 1){
            dayOfMonth--;
            if(dayOfWeek > 1)
                dayOfWeek--;
            else dayOfWeek = 7;
        }
        dayOfWeek--;
        if(dayOfWeek == 0) dayOfWeek = 7;
        FIRST_PANEL = dayOfWeek;
        
        MyNote note;
        int day = 0;
        for (int i = 1; i <= 42; i++){
            if ((i >= FIRST_PANEL) && (i < FIRST_PANEL + DAYS_IN_MONTH)){
                note = new MyNote(++day, CURRENT_MONTH, CURRENT_YEAR, true, login);
                String steps = checkSteps(CURRENT_YEAR, CURRENT_MONTH, day);
                if(!steps.equals("-1"))
                    note.setSteps(steps);
            }
            else note = new MyNote(i, CURRENT_MONTH, CURRENT_YEAR, false, login);
            notes.add(note);
            
        }
        
    }
    
    private void createNote(JPanel panel){
        if(notes.get(panels.indexOf(panel)).getActivity()){
            MyNoteForm note = new MyNoteForm(this, notes.get(panels.indexOf(panel)));
            note.setVisible(true);
            this.setVisible(false);
        }
    }
    
    public void importSteps(File file){
        String buffer;
        String date;
        String stepsNum;
        String year;
        String month;
        String day;
        String toCheck;
        ArrayList <String> stepsNotes = new ArrayList();
        ArrayList <String> newSteps = new ArrayList();
        File steps = new File("accounts\\" + login + "\\steps.txt");
        if(!steps.exists()){
            try {
                steps.createNewFile();
                BufferedReader in = new BufferedReader(new FileReader(file));
                BufferedWriter out = new BufferedWriter(new FileWriter(steps));
                buffer = in.readLine(); // первая строка
                boolean check = true;
                while(check){
                    buffer = in.readLine();
                    if(buffer == null) {
                        check = false;
                        break;
                    }
                    StringTokenizer st = new StringTokenizer(buffer, " ");
                    date = st.nextToken();
                    stepsNum = st.nextToken();
                    year = date.substring(0, 4);
                    month = date.substring(5, 7);
                    day = date.substring(8, 10);
                    stepsNotes.add(year + " " + month + " " + day + " " + stepsNum);
                }
                in.close();
                for (int i = stepsNotes.size() - 1; i > -1; i--){
                    out.write(stepsNotes.get(i));
                    out.newLine();
                }
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(MyCalendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                BufferedReader oldStepsFile = new BufferedReader(new FileReader(steps));
                boolean check = true;
                while(check){
                    buffer = oldStepsFile.readLine();
                    if(buffer == null){
                        check = false;
                        break;
                    }
                    stepsNotes.add(buffer);
                }
                oldStepsFile.close();
                
                BufferedReader newStepsFile = new BufferedReader(new FileReader(file));
                buffer = newStepsFile.readLine();                               // первая строка
                check = true;
                while(check){
                    buffer = newStepsFile.readLine();
                    if(buffer == null) {
                        check = false;
                        break;
                    }
                    StringTokenizer st = new StringTokenizer(buffer, " ");
                    date = st.nextToken();
                    stepsNum = st.nextToken();
                    year = date.substring(0, 4);
                    month = date.substring(5, 7);
                    day = date.substring(8, 10);
                    toCheck = year + " " + month + " " + day + " " + stepsNum;
                    newSteps.add(toCheck);
                }
                newStepsFile.close();
                
                BufferedWriter out = new BufferedWriter(new FileWriter(steps, true));
                for (int i = newSteps.size() - 1; i > -1; i--){
                    if(!stepsNotes.contains(newSteps.get(i))){
                        out.append(newSteps.get(i));
                        out.newLine();
                    }
                }
                out.close();
                
            } catch (IOException ex) {
                Logger.getLogger(MyCalendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        downloadSteps();
    }
    
    private String checkSteps(int year, int month, int day){
        String month1;
        String day1;
        
        if(month < 10) month1 = "0" + Integer.toString(month + 1);
        else month1 = Integer.toString(month);
        
        if(day < 10) day1 = "0" + Integer.toString(day);
        else day1 = Integer.toString(day);
        
        if(!steps.isEmpty())
        for (String step: steps){
            if (step.contains(year + " " + month1 + " " + day1)){
                return step.substring(11, step.length());
            }
        }
        return "-1";
    }
    
    private void downloadSteps(){
        File steps = new File("accounts\\" + login + "\\steps.txt");
        if(steps.exists()){
            this.steps = new ArrayList();
            String buffer;
            try {
                BufferedReader newStepsFile = new BufferedReader(new FileReader(steps));
                while(true){
                    buffer = newStepsFile.readLine();
                    if(buffer == null)
                        break;
                    this.steps.add(buffer);
                }
            } catch (IOException ex) {
                Logger.getLogger(MyCalendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else this.steps = new ArrayList();
        
    }
   

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables

}