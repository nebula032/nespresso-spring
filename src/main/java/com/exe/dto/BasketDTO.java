package com.exe.dto;

public class BasketDTO {
	
	private String email; //회원아이디
	
	private int num;
	private String type1; //커피, 머신, 액새서리
	private String type2; //오리지널, 버츄오
	private String type3; //캡슐, 팩
	
	private int pd_num;
	private String modelName1; //상품명
	private String imageUrl; //상품 이미지
	private int price;
	private int quantity;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getType3() {
		return type3;
	}
	public void setType3(String type3) {
		this.type3 = type3;
	}
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public String getModelName1() {
		return modelName1;
	}
	public void setModelName1(String modelName1) {
		this.modelName1 = modelName1;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
