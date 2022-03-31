package kr.spring.kit.vo;

import kr.spring.user.vo.UserVO;

public class KiteLikeVO {
		private int kitlike_num;
		private int kit_num;
		private int user_num;
		private KitVO kit;
		private UserVO user;
		
		
		public int getKitlike_num() {
			return kitlike_num;
		}
		public void setKitlike_num(int kitlike_num) {
			this.kitlike_num = kitlike_num;
		}
		public int getKit_num() {
			return kit_num;
		}
		public void setKit_num(int kit_num) {
			this.kit_num = kit_num;
		}
		public int getUser_num() {
			return user_num;
		}
		public void setUser_num(int user_num) {
			this.user_num = user_num;
		}
		public KitVO getKit() {
			return kit;
		}
		public void setKit(KitVO kit) {
			this.kit = kit;
		}
		public UserVO getUser() {
			return user;
		}
		public void setUser(UserVO user) {
			this.user = user;
		}
		
		
		
}
