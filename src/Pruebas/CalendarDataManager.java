package Pruebas;
/*
 * Decompiled with CFR 0_123.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

class CalendarDataManager extends JPanel{
    static final int CAL_WIDTH = 7;
    static final int CAL_HEIGHT = 6;
    int[][] calDates = new int[6][7];
    int calYear;
    int calMonth;
    int calDayOfMon;
    final int[] calLastDateOfMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int calLastDate;
    Calendar today = Calendar.getInstance();
    Calendar cal;

    public CalendarDataManager() {
        this.setToday();
    }

    public void setToday() {
        this.calYear = this.today.get(1);
        this.calMonth = this.today.get(2);
        this.calDayOfMon = this.today.get(5);
        this.makeCalData(this.today);
        
    }

    private void makeCalData(Calendar cal) {
        int calStartingPos = (cal.get(7) + 7 - cal.get(5) % 7) % 7;
        this.calLastDate = this.calMonth == 1 ? this.calLastDateOfMonth[this.calMonth] + this.leapCheck(this.calYear) : this.calLastDateOfMonth[this.calMonth];
        int i = 0;
        while (i < 6) {
            int j = 0;
            while (j < 7) {
                this.calDates[i][j] = 0;
                ++j;
            }
            ++i;
        }
        i = 0;
        int num = 1;
        int k = 0;
        while (i < 6) {
            k = i == 0 ? calStartingPos : 0;
            int j = k;
            while (j < 7) {
                if (num <= this.calLastDate) {
                    this.calDates[i][j] = num++;
                }
                ++j;
            }
            ++i;
        }
    }

    private int leapCheck(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return 1;
        }
        return 0;
    }

    public void moveMonth(int mon) {
        this.calMonth += mon;
        if (this.calMonth > 11) {
            while (this.calMonth > 11) {
                ++this.calYear;
                this.calMonth -= 12;
            }
        } else if (this.calMonth < 0) {
            while (this.calMonth < 0) {
                --this.calYear;
                this.calMonth += 12;
            }
        }
        this.cal = new GregorianCalendar(this.calYear, this.calMonth, this.calDayOfMon);
        this.makeCalData(this.cal);
    }
}