package Pruebas;
/*
 * Decompiled with CFR 0_123.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jsoup.Jsoup;

import com.mindfusion.common.DateTime;

import Model.Conexion;
import Model.Medico;


public class MemoCalendar extends CalendarDataManager {
    ImageIcon icon;
    JPanel central;
    JPanel calOpPanel;
    JButton todayBut;
    JLabel todayLab;
    JButton lYearBut;
    JButton lMonBut;
    JLabel curMMYYYYLab;
    JButton nMonBut;
    JButton nYearBut;
    ListenForCalOpButtons lForCalOpButtons;
    JPanel calPanel;
    JButton[] weekDaysName;
    JButton[][] dateButs;
    listenForDateButs lForDateButs;
    JPanel infoPanel;
    JLabel infoClock;
    JPanel memoPanel;
    JLabel selectedDate;
    JTextArea memoArea;
    JPanel memoAreaSP;
    JPanel frameBottomPanel;
    JLabel bottomInfo;
    String[] WEEK_DAY_NAME;
    String title = "Memo Calendar ver 1.0";
    String SaveButMsg1 = " saved in MemoData folder.";
    String SaveButMsg2 = "Write something first.";
    String SaveButMsg3 = "<html><font color=red>ERROR : failed to write a file</html>";
    String DelButMsg1 = "Memo deleted.";
    String DelButMsg2 = "There is nothing to delete.";
    String DelButMsg3 = "<html><font color=red>ERROR : failed to delete a file</html>";
    String ClrButMsg1 = "Text Area cleared.";
   private MainWindow  calendar;

   

    public MemoCalendar(Medico m) {
    	central=new JPanel();
    	central.setLayout(new BorderLayout());
        //this.icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("icon.png")));
        this.lForCalOpButtons = new ListenForCalOpButtons(this, null);
        this.dateButs = new JButton[6][7];
        this.lForDateButs = new listenForDateButs(this, null);
        this.bottomInfo = new JLabel("Welcome to Memo Calendar!");
        this.WEEK_DAY_NAME = new String[]{"SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT"};
        this.title = "Memo Calendar ver 1.0";
        this.SaveButMsg1 = " saved in MemoData folder.";
        this.SaveButMsg2 = "Write something first.";
        this.SaveButMsg3 = "<html><font color=red>ERROR : failed to write a file</html>";
        this.DelButMsg1 = "Memo deleted.";
        this.DelButMsg2 = "There is nothing to delete.";
        this.DelButMsg3 = "<html><font color=red>ERROR : failed to delete a file</html>";
        this.ClrButMsg1 = "Text Area cleared.";
        this.setOpaque(false);
       // this.this.setIconImage(this.icon.getImage());
//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            SwingUtilities.updateComponentTreeUI(this.this);
//        }
//        catch (Exception e) {
//            this.bottomInfo.setText("ERROR : LookAndFeel setting failed");
//        }
        this.calOpPanel = new JPanel();
        calOpPanel.setOpaque(false);
        this.todayBut = new JButton("Today");
        this.todayBut.setToolTipText("Today");
        this.todayBut.addActionListener(this.lForCalOpButtons);
        this.todayLab = new JLabel(String.valueOf(this.today.get(2) + 1) + "/" + this.today.get(5) + "/" + this.today.get(1));
        this.lYearBut = new JButton("<<");
        this.lYearBut.setToolTipText("Previous Year");
        this.lYearBut.addActionListener(this.lForCalOpButtons);
        this.lMonBut = new JButton("<");
        this.lMonBut.setToolTipText("Previous Month");
        this.lMonBut.addActionListener(this.lForCalOpButtons);
        this.curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>" + (this.calMonth + 1 < 10 ? "&nbsp;" : "") + (this.calMonth + 1) + " / " + this.calYear + "</th></tr></table></html>");
        this.nMonBut = new JButton(">");
        this.nMonBut.setToolTipText("Next Month");
        this.nMonBut.addActionListener(this.lForCalOpButtons);
        this.nYearBut = new JButton(">>");
        this.nYearBut.setToolTipText("Next Year");
        this.nYearBut.addActionListener(this.lForCalOpButtons);
        this.calOpPanel.setLayout(new GridBagLayout());
        GridBagConstraints calOpGC = new GridBagConstraints();
        calOpGC.gridx = 1;
        calOpGC.gridy = 1;
        calOpGC.gridwidth = 2;
        calOpGC.gridheight = 1;
        calOpGC.weightx = 1.0;
        calOpGC.weighty = 1.0;
        calOpGC.insets = new Insets(5, 5, 0, 0);
        calOpGC.anchor = 17;
        calOpGC.fill = 0;
        this.calOpPanel.add((Component)this.todayBut, calOpGC);
        calOpGC.gridwidth = 3;
        calOpGC.gridx = 2;
        calOpGC.gridy = 1;
        this.calOpPanel.add((Component)this.todayLab, calOpGC);
        calOpGC.anchor = 10;
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 1;
        calOpGC.gridy = 2;
        this.calOpPanel.add((Component)this.lYearBut, calOpGC);
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 2;
        calOpGC.gridy = 2;
        this.calOpPanel.add((Component)this.lMonBut, calOpGC);
        calOpGC.gridwidth = 2;
        calOpGC.gridx = 3;
        calOpGC.gridy = 2;
        this.calOpPanel.add((Component)this.curMMYYYYLab, calOpGC);
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 5;
        calOpGC.gridy = 2;
        this.calOpPanel.add((Component)this.nMonBut, calOpGC);
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 6;
        calOpGC.gridy = 2;
        this.calOpPanel.add((Component)this.nYearBut, calOpGC);
        this.calPanel = new JPanel();
        this.weekDaysName = new JButton[7];
        int i = 0;
        while (i < 7) {
            this.weekDaysName[i] = new JButton(this.WEEK_DAY_NAME[i].charAt(0)+"");
            this.weekDaysName[i].setBorderPainted(false);
            this.weekDaysName[i].setContentAreaFilled(false);
            this.weekDaysName[i].setForeground(Color.WHITE);
            if (i == 0) {
                this.weekDaysName[i].setBackground(new Color(200, 50, 50));
            } else if (i == 6) {
                this.weekDaysName[i].setBackground(new Color(50, 100, 200));
            } else {
                this.weekDaysName[i].setBackground(new Color(150, 150, 150));
            }
            this.weekDaysName[i].setOpaque(true);
            this.weekDaysName[i].setFocusPainted(false);
            this.calPanel.add(this.weekDaysName[i]);
            ++i;
        }
        i = 0;
        while (i < 6) {
            int j = 0;
            while (j < 7) {
                this.dateButs[i][j] = new JButton();
                this.dateButs[i][j].setBorderPainted(false);
                this.dateButs[i][j].setContentAreaFilled(false);
                this.dateButs[i][j].setBackground(Color.WHITE);
                this.dateButs[i][j].setOpaque(true);
                this.dateButs[i][j].addActionListener(this.lForDateButs);
                this.calPanel.add(this.dateButs[i][j]);
                ++j;
            }
            ++i;
        }
        this.calPanel.setLayout(new GridLayout(0, 7, 2, 2));
        this.calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        this.showCal(m);
        this.infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        this.infoPanel.setLayout(new BorderLayout());
        this.infoClock = new JLabel("", 4);
        this.infoClock.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.infoPanel.add((Component)this.infoClock, "North");
        this.selectedDate = new JLabel("<Html><font size=3>" + (this.today.get(2) + 1) + "/" + this.today.get(5) + "/" + this.today.get(1) + "&nbsp;(Today)</html>", 2);
        this.selectedDate.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        this.memoPanel = new JPanel();
        memoPanel.setOpaque(false);
        this.memoPanel.setBorder(BorderFactory.createTitledBorder("Memo"));
        this.memoArea = new JTextArea();
        this.memoArea.setLineWrap(true);
        this.memoArea.setWrapStyleWord(true);
        calendar=new MainWindow(m);
        this.memoAreaSP = new JPanel();
        memoAreaSP.setOpaque(false);
        memoAreaSP.setLayout(new BorderLayout());
        memoAreaSP.add(calendar);
        this.calendar.open();
      
        
        this.memoPanel.setLayout(new BorderLayout());
		
        this.memoPanel.add((Component)this.memoAreaSP, "Center");
        JPanel frameSubPanelWest = new JPanel();
        Dimension calOpPanelSize = this.calOpPanel.getPreferredSize();
        calOpPanelSize.height = 90;
        this.calOpPanel.setPreferredSize(calOpPanelSize);
        frameSubPanelWest.setLayout(new BorderLayout());
        frameSubPanelWest.add((Component)this.calOpPanel, "North");
        frameSubPanelWest.add((Component)this.calPanel, "Center");
        frameSubPanelWest.add(new JLabel("                                                                                                        "),BorderLayout.SOUTH);
        
        JPanel frameSubPanelEast = new JPanel();
        Dimension infoPanelSize = this.infoPanel.getPreferredSize();
        infoPanelSize.height = 65;
        this.infoPanel.setPreferredSize(infoPanelSize);
        frameSubPanelEast.setLayout(new BorderLayout());
        frameSubPanelEast.add((Component)this.infoPanel, "North");
        frameSubPanelEast.add((Component)this.memoPanel, "Center");
        Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
        frameSubPanelWestSize.width = 410;
        frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);
       this.setLayout(new BorderLayout());
        frameSubPanelWest.setOpaque(false);
        frameSubPanelEast.setOpaque(false);
        central.add((Component)frameSubPanelWest, "West");
        central.add((Component)frameSubPanelEast, "Center");
        central.setOpaque(false);
        this.add(central,BorderLayout.CENTER);
        String espacio="                        ";
        JButton invi=new JButton();
        invi.setOpaque(false);
        invi.setContentAreaFilled(false);
        invi.setBorderPainted(false);
        JButton invi2=new JButton();
        invi2.setOpaque(false);
        invi2.setContentAreaFilled(false);
        invi2.setBorderPainted(false);
        this.add(invi,BorderLayout.NORTH);
        this.add(invi2,BorderLayout.SOUTH);
        this.add(new JLabel(espacio),BorderLayout.WEST);
        this.add(new JLabel(espacio),BorderLayout.EAST);
        this.setVisible(true);
        this.focusToday();
        ThreadConrol threadCnl = new ThreadConrol(this, null);
        threadCnl.start();
    }

    private void focusToday() {
        if (this.today.get(7) == 1) {
            this.dateButs[this.today.get(4)][this.today.get(7) - 1].requestFocusInWindow();
        } else {
            this.dateButs[this.today.get(4) - 1][this.today.get(7) - 1].requestFocusInWindow();
        }
    }

  private int i=0;
    private void showCal(Medico med) {
        int i = 0;
        while (i < 6) {
            int j = 0;
            while (j < 7) {
                String fontColor = "black";
                if (j == 0) {
                    fontColor = "red";
                } else if (j == 6) {
                    fontColor = "blue";
                }
                if (Conexion.dayHaveSomething(Integer.parseInt(this.calYear + (this.calMonth + 1 < 10 ? "0" : "") + (this.calMonth + 1) + (this.calDates[i][j] < 10 ? "0" : "") + this.calDates[i][j]),med.getDni() )) {
                    this.dateButs[i][j].setText("<html><b><font size=\"6\" color=" + fontColor + ">" + this.calDates[i][j] + "</font></html>");
                } else {
                    this.dateButs[i][j].setText("<html><font size=\"5\" color=" + fontColor + ">" + this.calDates[i][j] + "</font></html>");
                }
                JLabel todayMark = new JLabel("<html><font color=green>*</html>");
                this.dateButs[i][j].removeAll();
                if (this.calMonth == this.today.get(2) && this.calYear == this.today.get(1) && this.calDates[i][j] == this.today.get(5)) {
                	if(this.i==0) {
                	this.dateButs[i][j].setBackground(Color.GREEN);
                	this.i++;
                	}
                    this.dateButs[i][j].add(todayMark);
                    this.dateButs[i][j].setToolTipText("Today");
                }
                if (this.calDates[i][j] == 0) {
                    this.dateButs[i][j].setVisible(false);
                } else {
                    this.dateButs[i][j].setVisible(true);
                }
                ++j;
            }
            ++i;
        }
    }

private class ListenForCalOpButtons implements ActionListener {
       MemoCalendar this$0;

       
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this.this$0.todayBut) {
                this.this$0.setToday();
                this.this$0.lForDateButs.actionPerformed(e);
                this.this$0.focusToday();
            } else if (e.getSource() == this.this$0.lYearBut) {
                this.this$0.moveMonth(-12);
            } else if (e.getSource() == this.this$0.lMonBut) {
                this.this$0.moveMonth(-1);
            } else if (e.getSource() == this.this$0.nMonBut) {
                this.this$0.moveMonth(1);
            } else if (e.getSource() == this.this$0.nYearBut) {
                this.this$0.moveMonth(12);
            }
            
            this.this$0.curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>" + (this.this$0.calMonth + 1 < 10 ? "&nbsp;" : "") + (this.this$0.calMonth + 1) + " / " + this.this$0.calYear + "</th></tr></table></html>");
          
            this.this$0.showCal(this.this$0.calendar.getMe());
            int i = 5;
            boolean fin=true;
            while (i >= 0 && fin) {
                int j = 6;
                while (j >= 0 && fin) {
                		if(Integer.parseInt(Jsoup.parse(this.this$0.dateButs[i][j].getText()).text())!=0 && Integer.parseInt(Jsoup.parse(this.this$0.dateButs[i][j].getText()).text())<=this$0.calendar.calendar.getDate().getDay()) {
                			fin=false;
                			this.this$0.dateButs[i][j].doClick();
                		}
                	j--;
                }
                i--;
            }
        }

        /* synthetic */ ListenForCalOpButtons(MemoCalendar memoCalendar, ListenForCalOpButtons listenForCalOpButtons) {
        	this.this$0=memoCalendar;
        }
    }

    private class ThreadConrol extends Thread {
        final /* synthetic */ MemoCalendar this$0;


        @Override
        public void run() {
            boolean msgCntFlag = false;
            int num = 0;
            String curStr = new String();
            do {
                try {
                    do {
                        String amPm;
                        this.this$0.today = Calendar.getInstance();
                        amPm = this.this$0.today.get(9) == 0 ? "AM" : "PM";
                        String hour = this.this$0.today.get(10) == 0 ? "12" : (this.this$0.today.get(10) == 12 ? " 0" : String.valueOf(this.this$0.today.get(10) < 10 ? " " : "") + this.this$0.today.get(10));
                        String min = String.valueOf(this.this$0.today.get(12) < 10 ? "0" : "") + this.this$0.today.get(12);
                        String sec = String.valueOf(this.this$0.today.get(13) < 10 ? "0" : "") + this.this$0.today.get(13);
                        this.this$0.infoClock.setText(String.valueOf(amPm) + " " + hour + ":" + min + ":" + sec);
                        ThreadConrol.sleep(1000);
                        String infoStr = this.this$0.bottomInfo.getText();
                        if (!(infoStr == " " || msgCntFlag && curStr == infoStr)) {
                            num = 5;
                            msgCntFlag = true;
                            curStr = infoStr;
                            continue;
                        }
                        if (infoStr == " " || !msgCntFlag) continue;
                        if (num > 0) {
                            --num;
                            continue;
                        }
                        msgCntFlag = false;
                        this.this$0.bottomInfo.setText(" ");
                    } while (true);
                }
                catch (InterruptedException e) {
                    System.out.println("Thread:Error");
                    continue;
                }
            } while (true);
        }

       public ThreadConrol(MemoCalendar memoCalendar, ThreadConrol threadConrol) {
    	   this.this$0=memoCalendar;
        }
    }

    private class listenForDateButs 
    implements ActionListener {
        final MemoCalendar this$0;


        @Override
        public void actionPerformed(ActionEvent e) {
        	if(this.this$0.calendar.save()) {
        		this.this$0.showCal(this.this$0.calendar.getMe());
        	}
            int k = 0;
            int l = 0;
            int i = 0;
            while (i < 6) {
                int j = 0;
                while (j < 7) {
                    if (e.getSource() == this.this$0.dateButs[i][j]) {
                    	this.this$0.dateButs[i][j].setBackground(Color.GREEN);
                        k = i;
                        l = j;
                    } else {
                    	this.this$0.dateButs[i][j].setBackground(Color.white);
                    }
                    ++j;
                }
                ++i;
            }
            if (k != 0 || l != 0) {
                this.this$0.calDayOfMon = this.this$0.calDates[k][l];
            }
            this.this$0.cal = new GregorianCalendar(this.this$0.calYear, this.this$0.calMonth, this.this$0.calDayOfMon);
            String dDayString = new String();
            int dDay = (int)((this.this$0.cal.getTimeInMillis() - this.this$0.today.getTimeInMillis()) / 1000 / 60 / 60 / 24);
            if (dDay == 0 && this.this$0.cal.get(1) == this.this$0.today.get(1) && this.this$0.cal.get(2) == this.this$0.today.get(2) && this.this$0.cal.get(5) == this.this$0.today.get(5)) {
                dDayString = "Today";
            } else if (dDay >= 0) {
                dDayString = "D-" + (dDay + 1);
            } else if (dDay < 0) {
                dDayString = "D+" + dDay * -1;
            }
            this.this$0.calendar.calendar.setDate(new DateTime(this.this$0.calYear,this.this$0.calMonth+1,this.this$0.calDayOfMon));
            this.this$0.selectedDate.setText("<Html><font size=3>" + (this.this$0.calMonth + 1) + "/" + this.this$0.calDayOfMon + "/" + this.this$0.calYear + "&nbsp;(" + dDayString + ")</html>");
            this.this$0.calendar.open();
        }
        	
        public listenForDateButs(MemoCalendar memoCalendar, listenForDateButs listenForDateButs2) {
        	this.this$0=memoCalendar;
        }
    }

	public MainWindow getCalendar() {
		return calendar;
	}

	
}