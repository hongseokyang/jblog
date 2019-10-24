package kr.co.itcen.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String info;
	private String registerDate;
	private String blogId;
	private Long countPost;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	
	public Long getCountPost() {
		return countPost;
	}
	public void setCountPost(Long countPost) {
		this.countPost = countPost;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", info=" + info + ", registerDate=" + registerDate
				+ ", blogId=" + blogId + ", countPost=" + countPost + "]";
	}
	public void defaultSetting(String blogId) {
		this.name = "기타";
		this.info = "기타";
		this.blogId = blogId;
	}
	
	
}
