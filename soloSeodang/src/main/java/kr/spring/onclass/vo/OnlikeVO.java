package kr.spring.onclass.vo;

public class OnlikeVO {
	private int onlike_num;
	private int user_num;
	private int on_num;
	private int olike; //찜 상태 , 0 안누름 1 누름
	public int getOnlike_num() {
		return onlike_num;
	}
	public void setOnlike_num(int onlike_num) {
		this.onlike_num = onlike_num;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public int getOn_num() {
		return on_num;
	}
	public void setOn_num(int on_num) {
		this.on_num = on_num;
	}
	public int getOlike() {
		return olike;
	}
	public void setOlike(int olike) {
		this.olike = olike;
	}
	
	
}
