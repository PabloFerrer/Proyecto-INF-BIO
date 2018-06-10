package Model;
/*
 * Inspirado y modificado en base a codigo de
 * Imtaek Hong
 * http://imtaekh.github.io/portfolio/
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

public class CalendarDataManager extends JPanel{
    static final int CAL_WIDTH = 7;
    static final int CAL_HEIGHT = 6;
    private int[][] calDates = new int[6][7];
    private int calYear;
    private int calMonth;
    private int calDayOfMon;
    final int[] calLastDateOfMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    protected int calLastDate;
    private Calendar today = Calendar.getInstance();
    private Calendar cal;

    public CalendarDataManager() {
        this.setToday();
    }

    public void setToday() {
        this.setCalYear(this.today.get(1));
        this.setCalMonth(this.today.get(2));
        this.setCalDayOfMon(this.today.get(5));
        this.makeCalData(this.today);
        
    }

    private void makeCalData(Calendar cal) {
        int calStartingPos = (cal.get(7) + 7 - cal.get(5) % 7) % 7;
        this.calLastDate = this.getCalMonth() == 1 ? this.calLastDateOfMonth[this.getCalMonth()] + this.leapCheck(this.getCalYear()) : this.calLastDateOfMonth[this.getCalMonth()];
        int i = 0;
        while (i < 6) {
            int j = 0;
            while (j < 7) {
                this.getCalDates()[i][j] = 0;
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
                    this.getCalDates()[i][j] = num++;
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
        this.setCalMonth(this.getCalMonth() + mon);
        if (this.getCalMonth() > 11) {
            while (this.getCalMonth() > 11) {
                this.setCalYear(this.getCalYear() + 1);
                this.setCalMonth(this.getCalMonth() - 12);
            }
        } else if (this.getCalMonth() < 0) {
            while (this.getCalMonth() < 0) {
                this.setCalYear(this.getCalYear() - 1);
                this.setCalMonth(this.getCalMonth() + 12);
            }
        }
        this.setCal(new GregorianCalendar(this.getCalYear(), this.getCalMonth(), this.getCalDayOfMon()));
        this.makeCalData(this.getCal());
    }

	/**
	 * @return the calDates
	 */
	public int[][] getCalDates() {
		return calDates;
	}

	/**
	 * @param calDates the calDates to set
	 */
	public void setCalDates(int[][] calDates) {
		this.calDates = calDates;
	}

	/**
	 * @return the calDayOfMon
	 */
	public int getCalDayOfMon() {
		return calDayOfMon;
	}

	/**
	 * @param calDayOfMon the calDayOfMon to set
	 */
	public void setCalDayOfMon(int calDayOfMon) {
		this.calDayOfMon = calDayOfMon;
	}

	/**
	 * @return the calYear
	 */
	public int getCalYear() {
		return calYear;
	}

	/**
	 * @param calYear the calYear to set
	 */
	public void setCalYear(int calYear) {
		this.calYear = calYear;
	}

	/**
	 * @return the calMonth
	 */
	public int getCalMonth() {
		return calMonth;
	}

	/**
	 * @param calMonth the calMonth to set
	 */
	public void setCalMonth(int calMonth) {
		this.calMonth = calMonth;
	}

	/**
	 * @return the cal
	 */
	public Calendar getCal() {
		return cal;
	}

	/**
	 * @param cal the cal to set
	 */
	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	/**
	 * @return the today
	 */
	public Calendar getToday() {
		return today;
	}

	/**
	 * @param today the today to set
	 */
	public void setToday(Calendar today) {
		this.today = today;
	}
}