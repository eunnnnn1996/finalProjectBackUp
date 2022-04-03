package kr.spring.onclass.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

public class OnclassVO {
	private int on_num;
	private int user_num;
	@NotEmpty
	private String on_name;
	@Range(min=0,max=999999)
	private int on_price;
	private int hit;
	@Range(min=1,max=5)
	private int category_num;
	@NotEmpty
	private String on_content;
	private MultipartFile upload;
	private byte[] uploadfile;
	private String filename;
	private Date reg_date;
	private Date modify_date;
	
	//평균 평점(후기게시판)
	private int avgqna;
	public int getAvgqna() {
		return avgqna;
	}

	public void setAvgqna(int avgqna) {
		this.avgqna = avgqna;
	}
	//평균 평점(후기게시판)
	
	
	private String deletePasswd;
	
	public String getDeletePasswd() {
		return deletePasswd;
	}

	public void setDeletPasswd(String deletePasswd) {
		this.deletePasswd = deletePasswd;
	}
	
	
	public int getCategory_num() {
		return category_num;
	}

	public void setCategory_num(int category_num) {
		this.category_num = category_num;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setUpload(MultipartFile upload)throws IOException{
		this.upload = upload;
		setUploadfile(upload.getBytes());
		setFilename(upload.getOriginalFilename());
	}


	public int getOn_num() {
		return on_num;
	}

	public void setOn_num(int on_num) {
		this.on_num = on_num;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public String getOn_name() {
		return on_name;
	}

	public void setOn_name(String on_name) {
		this.on_name = on_name;
	}

	public int getOn_price() {
		return on_price;
	}

	public void setOn_price(int on_price) {
		this.on_price = on_price;
	}

	public String getOn_content() {
		return on_content;
	}

	public void setOn_content(String on_content) {
		this.on_content = on_content;
	}

	public byte[] getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(byte[] uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public MultipartFile getUpload() {
		return upload;
	}

	@Override
	public String toString() {
		return "OnclassVO [on_num=" + on_num + ", user_num=" + user_num + ", on_name=" + on_name + ", on_price="
				+ on_price + ", hit=" + hit + ", category_num=" + category_num + ", on_content=" + on_content
				+ ", upload=" + upload + ", filename=" + filename + ", reg_date=" + reg_date + ", modify_date="
				+ modify_date + ", avgqna=" + avgqna + ", deletePasswd=" + deletePasswd + "]";
	}


	
	
}
