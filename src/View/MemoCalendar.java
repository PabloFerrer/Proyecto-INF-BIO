package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import Control.ListenForCalOpButtons;
import Control.ThreadConrol;
import Control.listenForDateButs;
import Model.CalendarDataManager;
import Model.Conexion;
import Model.Medico;

public class MemoCalendar extends CalendarDataManager {
   private JPanel central;
   private JPanel calOpPanel;
   private JButton todayBut;
   private JLabel todayLab;
   private JButton lYearBut;
   private  JButton lMonBut;
   private  JLabel curMMYYYYLab;
   private  JButton nMonBut;
   private JButton nYearBut;
    private  JPanel calPanel;
    private  JButton[] weekDaysName;
    private  JButton[][] dateButs;
    private  listenForDateButs lForDateButs;
    private  JPanel infoPanel;
    private  JLabel infoClock;
    private  JPanel memoPanel;
    private  JLabel bottomInfo;
    private  String[] WEEK_DAY_NAME;
   private MainWindow  calendar;

   

    public MemoCalendar(Medico m) {
    	central=new JPanel();
    	central.setLayout(new BorderLayout());
        //this.icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("icon.png")));
    	ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons(this);
        this.setDateButs(new JButton[6][7]);
        this.setlForDateButs(new listenForDateButs(this));
        this.setBottomInfo(new JLabel("Welcome to Memo Calendar!"));
        this.WEEK_DAY_NAME = new String[]{"D", "L", "M", "M", "J", "V", "S"};
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
        this.setTodayBut(new JButton("Today"));
        this.getTodayBut().setToolTipText("Today");
        this.getTodayBut().addActionListener(lForCalOpButtons);
        this.todayLab = new JLabel("    "+String.valueOf(this.getToday().get(2) + 1) + "/" + this.getToday().get(5) + "/" + this.getToday().get(1));
        this.setlYearBut(new JButton("<<"));
        this.getlYearBut().setToolTipText("Previous Year");
        this.getlYearBut().addActionListener(lForCalOpButtons);
        this.setlMonBut(new JButton("<"));
        this.getlMonBut().setToolTipText("Previous Month");
        this.getlMonBut().addActionListener(lForCalOpButtons);
        this.setCurMMYYYYLab(new JLabel("<html><table width=100><tr><th><font size=4>" + (this.getCalMonth() + 1 < 10 ? "&nbsp;" : "") + (this.getCalMonth() + 1) + " / " + this.getCalYear() + "</th></tr></table></html>"));
        this.setnMonBut(new JButton(">"));
        this.getnMonBut().setToolTipText("Next Month");
        this.getnMonBut().addActionListener(lForCalOpButtons);
        this.setnYearBut(new JButton(">>"));
        this.getnYearBut().setToolTipText("Next Year");
        this.getnYearBut().addActionListener(lForCalOpButtons);
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
        this.calOpPanel.add((Component)this.getTodayBut(), calOpGC);
        calOpGC.gridwidth = 3;
        calOpGC.gridx = 2;
        calOpGC.gridy = 1;
        this.calOpPanel.add((Component)this.todayLab, calOpGC);
        calOpGC.anchor = 10;
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 1;
        calOpGC.gridy = 2;
        this.calOpPanel.add((Component)this.getlYearBut(), calOpGC);
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 2;
        calOpGC.gridy = 2;
        this.calOpPanel.add((Component)this.getlMonBut(), calOpGC);
        calOpGC.gridwidth = 2;
        calOpGC.gridx = 3;
        calOpGC.gridy = 2;
        this.calOpPanel.add((Component)this.getCurMMYYYYLab(), calOpGC);
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 5;
        calOpGC.gridy = 2;
        this.calOpPanel.add((Component)this.getnMonBut(), calOpGC);
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 6;
        calOpGC.gridy = 2;
        this.calOpPanel.add((Component)this.getnYearBut(), calOpGC);
        this.calPanel = new JPanel();
        this.weekDaysName = new JButton[7];
        int i = 0;
        while (i < 7) {
            this.weekDaysName[i] = new JButton(this.WEEK_DAY_NAME[i].charAt(0)+"");
            this.weekDaysName[i].setHorizontalAlignment(SwingConstants.LEFT);
            this.weekDaysName[i].setText("<html><font size=\"6\">" + this.WEEK_DAY_NAME[i].charAt(0)+"" + "</font></html>");
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
                this.getDateButs()[i][j] = new JButton();
                this.getDateButs()[i][j].setBorderPainted(false);
                this.getDateButs()[i][j].setContentAreaFilled(false);
                this.getDateButs()[i][j].setBackground(Color.WHITE);
                this.getDateButs()[i][j].setOpaque(true);
                this.getDateButs()[i][j].addActionListener(this.getlForDateButs());
                this.calPanel.add(this.getDateButs()[i][j]);
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
        this.setInfoClock(new JLabel("", 4));
        this.getInfoClock().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.infoPanel.add((Component)this.getInfoClock(), "North");
        this.memoPanel = new JPanel();
        memoPanel.setOpaque(false);
        this.memoPanel.setBorder(BorderFactory.createTitledBorder("Memo"));
        calendar=new MainWindow(m);
        this.calendar.open();
      
        
        this.memoPanel.setLayout(new BorderLayout());
		
        this.memoPanel.add((Component)calendar, "Center");
        JPanel frameSubPanelWest = new JPanel();
        Dimension calOpPanelSize = this.calOpPanel.getPreferredSize();
        calOpPanelSize.height = 90;
        this.calOpPanel.setPreferredSize(calOpPanelSize);
        frameSubPanelWest.setLayout(new BorderLayout());
        frameSubPanelWest.add((Component)this.calOpPanel, "North");
        frameSubPanelWest.add((Component)this.calPanel, "Center");
        
        JPanel frameSubPanelEast = new JPanel();
        Dimension infoPanelSize = this.infoPanel.getPreferredSize();
        infoPanelSize.height = 65;
        this.infoPanel.setPreferredSize(infoPanelSize);
        frameSubPanelEast.setLayout(new BorderLayout());
        frameSubPanelEast.add((Component)this.infoPanel, "North");
        frameSubPanelEast.add((Component)this.memoPanel, "Center");
        Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
        frameSubPanelWestSize.width = 390;
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
        ThreadConrol threadCnl = new ThreadConrol(this);
        threadCnl.start();
    }

    public void focusToday() {
        if (this.getToday().get(7) == 1) {
            this.getDateButs()[this.getToday().get(4)][this.getToday().get(7) - 1].requestFocusInWindow();
        } else {
            this.getDateButs()[this.getToday().get(4) - 1][this.getToday().get(7) - 1].requestFocusInWindow();
        }
    }

  private int i=0;
    public void showCal(Medico med) {
        int i = 0;
        Vector<Integer> days=Conexion.dayofMonthWithSomething(this.getCalYear(),(this.getCalMonth() + 1),med.getDni() );
        while (i < 6) {
            int j = 0;
            while (j < 7) {
                String fontColor = "black";
                if (j == 0) {
                    fontColor = "red";
                } else if (j == 6) {
                    fontColor = "blue";
                }

                if ((days.size()>0)?
                		days.get(0)==Integer.parseInt(this.getCalYear() + (this.getCalMonth() + 1 < 10 ? "0" : "") + (this.getCalMonth() + 1) + (this.getCalDates()[i][j] < 10 ? "0" : "") + this.getCalDates()[i][j])
                		:false) {
                	days.remove(0);
                    this.getDateButs()[i][j].setText("<html><b><font size=\"6\" color=" + fontColor + ">" + this.getCalDates()[i][j] + "</font></html>");
                } else {
                    this.getDateButs()[i][j].setText("<html><font size=\"5\" color=" + fontColor + ">" + this.getCalDates()[i][j] + "</font></html>");
                }
                JLabel todayMark = new JLabel("<html><font color=green>*</html>");
                this.getDateButs()[i][j].removeAll();
                if (this.getCalMonth() == this.getToday().get(2) && this.getCalYear() == this.getToday().get(1) && this.getCalDates()[i][j] == this.getToday().get(5)) {
                	if(this.i==0) {
                	this.getDateButs()[i][j].setBackground(Color.GREEN);
                	this.i++;
                	}
                    this.getDateButs()[i][j].add(todayMark);
                    this.getDateButs()[i][j].setToolTipText("Today");
                }
                if (this.getCalDates()[i][j] == 0) {
                    this.getDateButs()[i][j].setVisible(false);
                } else {
                    this.getDateButs()[i][j].setVisible(true);
                }
                ++j;
            }
            ++i;
        }
    }
   



	public MainWindow getCalendar() {
		return calendar;
	}

	/**
	 * @return the dateButs
	 */
	public JButton[][] getDateButs() {
		return dateButs;
	}

	/**
	 * @param dateButs the dateButs to set
	 */
	public void setDateButs(JButton[][] dateButs) {
		this.dateButs = dateButs;
	}

	/**
	 * @return the todayBut
	 */
	public JButton getTodayBut() {
		return todayBut;
	}

	/**
	 * @param todayBut the todayBut to set
	 */
	public void setTodayBut(JButton todayBut) {
		this.todayBut = todayBut;
	}

	/**
	 * @return the lForDateButs
	 */
	public listenForDateButs getlForDateButs() {
		return lForDateButs;
	}

	/**
	 * @param lForDateButs the lForDateButs to set
	 */
	public void setlForDateButs(listenForDateButs lForDateButs) {
		this.lForDateButs = lForDateButs;
	}

	/**
	 * @return the lYearBut
	 */
	public JButton getlYearBut() {
		return lYearBut;
	}

	/**
	 * @param lYearBut the lYearBut to set
	 */
	public void setlYearBut(JButton lYearBut) {
		this.lYearBut = lYearBut;
	}

	/**
	 * @return the lMonBut
	 */
	public JButton getlMonBut() {
		return lMonBut;
	}

	/**
	 * @param lMonBut the lMonBut to set
	 */
	public void setlMonBut(JButton lMonBut) {
		this.lMonBut = lMonBut;
	}

	/**
	 * @return the nMonBut
	 */
	public JButton getnMonBut() {
		return nMonBut;
	}

	/**
	 * @param nMonBut the nMonBut to set
	 */
	public void setnMonBut(JButton nMonBut) {
		this.nMonBut = nMonBut;
	}

	/**
	 * @return the nYearBut
	 */
	public JButton getnYearBut() {
		return nYearBut;
	}

	/**
	 * @param nYearBut the nYearBut to set
	 */
	public void setnYearBut(JButton nYearBut) {
		this.nYearBut = nYearBut;
	}

	/**
	 * @return the curMMYYYYLab
	 */
	public JLabel getCurMMYYYYLab() {
		return curMMYYYYLab;
	}

	/**
	 * @param curMMYYYYLab the curMMYYYYLab to set
	 */
	public void setCurMMYYYYLab(JLabel curMMYYYYLab) {
		this.curMMYYYYLab = curMMYYYYLab;
	}

	/**
	 * @return the infoClock
	 */
	public JLabel getInfoClock() {
		return infoClock;
	}

	/**
	 * @param infoClock the infoClock to set
	 */
	public void setInfoClock(JLabel infoClock) {
		this.infoClock = infoClock;
	}

	/**
	 * @return the bottomInfo
	 */
	public JLabel getBottomInfo() {
		return bottomInfo;
	}

	/**
	 * @param bottomInfo the bottomInfo to set
	 */
	public void setBottomInfo(JLabel bottomInfo) {
		this.bottomInfo = bottomInfo;
	}

	
}