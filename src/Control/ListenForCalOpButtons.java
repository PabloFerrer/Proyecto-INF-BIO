package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jsoup.Jsoup;

import View.MemoCalendar;

/*
     * Inspirado y modificado en base a codigo de
     * Imtaek Hong
     * http://imtaekh.github.io/portfolio/
     */
public class ListenForCalOpButtons implements ActionListener {
	private MemoCalendar this$0;

       
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this.this$0.getTodayBut()) {
                this.this$0.setToday();
                this.this$0.getlForDateButs().actionPerformed(e);
                this.this$0.focusToday();
            } else if (e.getSource() == this.this$0.getlYearBut()) {
                this.this$0.moveMonth(-12);
            } else if (e.getSource() == this.this$0.getlMonBut()) {
                this.this$0.moveMonth(-1);
            } else if (e.getSource() == this.this$0.getnMonBut()) {
                this.this$0.moveMonth(1);
            } else if (e.getSource() == this.this$0.getnYearBut()) {
                this.this$0.moveMonth(12);
            }
            
            this.this$0.getCurMMYYYYLab().setText("<html><table width=100><tr><th><font size=4>" + (this.this$0.getCalMonth() + 1 < 10 ? "&nbsp;" : "") + (this.this$0.getCalMonth() + 1) + " / " + this.this$0.getCalYear() + "</th></tr></table></html>");
          
            this.this$0.showCal(this.this$0.getCalendar().getMe());
            int i = 5;
            boolean fin=true;
            while (i >= 0 && fin) {
                int j = 6;
                while (j >= 0 && fin) {
                		if(Integer.parseInt(Jsoup.parse(this.this$0.getDateButs()[i][j].getText()).text())!=0 && Integer.parseInt(Jsoup.parse(this.this$0.getDateButs()[i][j].getText()).text())<=this$0.getCalendar().getCalendar().getDate().getDay()) {
                			fin=false;
                			this.this$0.getDateButs()[i][j].doClick();
                		}
                	j--;
                }
                i--;
            }
        }

       public ListenForCalOpButtons(MemoCalendar memoCalendar) {
        	this.this$0=memoCalendar;
        }
    }