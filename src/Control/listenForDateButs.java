package Control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import com.mindfusion.common.DateTime;

import View.MemoCalendar;

/*
 * Inspirado y modificado en base a codigo de
 * Imtaek Hong
 * http://imtaekh.github.io/portfolio/
 */
public class listenForDateButs 
implements ActionListener {
    final MemoCalendar this$0;


    @Override
    public void actionPerformed(ActionEvent e) {
    	if(this.this$0.getCalendar().save()) {
    		this.this$0.showCal(this.this$0.getCalendar().getMe());
    	}
        int k = 0;
        int l = 0;
        int i = 0;
        while (i < 6) {
            int j = 0;
            while (j < 7) {
                if (e.getSource() == this.this$0.getDateButs()[i][j]) {
                	this.this$0.getDateButs()[i][j].setBackground(Color.GREEN);
                    k = i;
                    l = j;
                } else {
                	this.this$0.getDateButs()[i][j].setBackground(Color.white);
                }
                ++j;
            }
            ++i;
        }
        if (k != 0 || l != 0) {
            this.this$0.setCalDayOfMon(this.this$0.getCalDates()[k][l]);
        }
        this.this$0.setCal(new GregorianCalendar(this.this$0.getCalYear(), this.this$0.getCalMonth(), this.this$0.getCalDayOfMon()));
        String dDayString = new String();
        int dDay = (int)((this.this$0.getCal().getTimeInMillis() - this.this$0.getToday().getTimeInMillis()) / 1000 / 60 / 60 / 24);
        if (dDay == 0 && this.this$0.getCal().get(1) == this.this$0.getToday().get(1) && this.this$0.getCal().get(2) == this.this$0.getToday().get(2) && this.this$0.getCal().get(5) == this.this$0.getToday().get(5)) {
            dDayString = "Today";
        } else if (dDay >= 0) {
            dDayString = "D-" + (dDay + 1);
        } else if (dDay < 0) {
            dDayString = "D+" + dDay * -1;
        }
        this.this$0.getCalendar().getCalendar().setDate(new DateTime(this.this$0.getCalYear(),this.this$0.getCalMonth()+1,this.this$0.getCalDayOfMon()));
       this.this$0.getCalendar().open();
    }
    	
    public listenForDateButs(MemoCalendar memoCalendar) {
    	this.this$0=memoCalendar;
    }
}