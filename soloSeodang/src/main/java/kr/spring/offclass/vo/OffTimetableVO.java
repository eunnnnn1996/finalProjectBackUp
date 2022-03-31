package kr.spring.offclass.vo;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class OffTimetableVO {
	private int time_num;
	private int off_num;
	private Date time_date;
	private String time_start;
	private String time_end;
	
	public OffTimetableVO() {}
	public OffTimetableVO(int time_num,int off_num,Date time_date,String time_start,String time_end) {
		this.time_num=time_num;
		this.off_num= off_num;
		this.time_date=time_date;
		this.time_start=time_start;
		this.time_end=time_end;
	}
	@Override
	public boolean equals(Object o) {
		if (o == this)
            return true;
        if (!(o instanceof OffTimetableVO))
            return false;
        OffTimetableVO other = (OffTimetableVO) o;
        boolean time_numEquals = (Integer.valueOf(this.time_num) == null && Integer.valueOf(other.time_num) == null)
          || (Integer.valueOf(this.time_num) != null && Integer.valueOf(this.time_num).equals(other.time_num));
        boolean off_numEquals = (Integer.valueOf(this.off_num) == null && Integer.valueOf(other.off_num) == null)
          || (Integer.valueOf(this.off_num) != null && Integer.valueOf(this.off_num).equals(other.off_num));
        return time_numEquals && (this.time_date.equals(other.time_date))
        		&& (this.time_start.equals(other.time_start)) && (this.time_end.equals(other.time_end));
	}
    // hashcode 재정의
    @Override
    public int hashCode() {
        return Objects.hash(time_num, off_num,time_date,time_start,time_end);
    }
	
	public int getTime_num() {
		return time_num;
	}
	public void setTime_num(int time_num) {
		this.time_num = time_num;
	}
	public int getOff_num() {
		return off_num;
	}
	public void setOff_num(int off_num) {
		this.off_num = off_num;
	}
	public Date getTime_date() {
		return time_date;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public void setTime_date(Date time_date) {
		this.time_date = time_date;
	}
	@Override
	public String toString() {
		return "OffTimetableVO [time_num=" + time_num + ", off_num=" + off_num + ", time_date=" + time_date
				+ ", time_start=" + time_start + ", time_end=" + time_end + "]";
	}
	
}
