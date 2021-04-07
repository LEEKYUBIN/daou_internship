package vo;

import java.sql.Date;

public class Item {

	private int no;
	private int price;
	private int review;
	private int reviewNum;
	private int categoryNo;
	private String name;
	private String img;
	private Date regdate;

	public Item() {
	}

	public Item(int categoryNo, String name, String img, int price, Date regdate, int reviewNum, int review) {
		this.categoryNo = categoryNo;
		this.name = name;
		this.img = img;
		this.price = price;
		this.regdate = regdate;
		this.reviewNum = reviewNum;
		this.review = review;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getReview() {
		return review;
	}

	public void setReview(int review) {
		this.review = review;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

}
