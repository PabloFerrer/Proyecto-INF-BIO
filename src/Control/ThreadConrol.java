package Control;

import java.util.Calendar;

import View.MemoCalendar;

/*
 * Inspirado y modificado en base a codigo de
 * Imtaek Hong
 * http://imtaekh.github.io/portfolio/
 */
    public class ThreadConrol extends Thread {
        private MemoCalendar this$0;


        @Override
        public void run() {
            boolean msgCntFlag = false;
            int num = 0;
            String curStr = new String();
            do {
                try {
                    do {
                        String amPm;
                        this.this$0.setToday(Calendar.getInstance());
                        amPm = this.this$0.getToday().get(9) == 0 ? "AM" : "PM";
                        String hour = this.this$0.getToday().get(10) == 0 ? "12" : (this.this$0.getToday().get(10) == 12 ? " 0" : String.valueOf(this.this$0.getToday().get(10) < 10 ? " " : "") + this.this$0.getToday().get(10));
                        String min = String.valueOf(this.this$0.getToday().get(12) < 10 ? "0" : "") + this.this$0.getToday().get(12);
                        String sec = String.valueOf(this.this$0.getToday().get(13) < 10 ? "0" : "") + this.this$0.getToday().get(13);
                        this.this$0.getInfoClock().setText(String.valueOf(amPm) + " " + hour + ":" + min + ":" + sec);
                        ThreadConrol.sleep(1000);
                        String infoStr = this.this$0.getBottomInfo().getText();
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
                        this.this$0.getBottomInfo().setText(" ");
                    } while (true);
                }
                catch (InterruptedException e) {
                    System.out.println("Thread:Error");
                    continue;
                }
            } while (true);
        }

       public ThreadConrol(MemoCalendar memoCalendar) {
    	   this.this$0=memoCalendar;
        }
    }