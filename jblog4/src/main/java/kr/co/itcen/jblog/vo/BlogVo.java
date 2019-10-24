package kr.co.itcen.jblog.vo;

public class BlogVo {
	private String userId;
	private String title;
	private String logo;
	private String storedFile;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getStoredFile() {
		return storedFile;
	}
	public void setStoredFile(String storedFile) {
		this.storedFile = storedFile;
	}
	@Override
	public String toString() {
		return "BlogVo [userId=" + userId + ", title=" + title + ", logo=" + logo + ", storedFile=" + storedFile + "]";
	}
	public void defaultSetting(String userId) {
		this.userId = userId;
		title = userId+" 님의 블로그";
		logo = "logo.jpg";
		storedFile = "/assets/images/spring-logo.jpg";
		
	}
	
	
}
