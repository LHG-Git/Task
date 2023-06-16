package Homework2;

public class News {
	private int aid =0;
	private String title;
	private String img;
	private String date;
	private String content;
	private String text;
	
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
//	public String getDate() {
//		return date;
//	}
	public String getDate() {
        if (date != null && date.length() >= 19) {
            return date.substring(0, 19); // 초 이후의 부분은 잘라냄
        }
        return date;
    }
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
	    return "News [aid= " + aid + "title=" + title + ", img=" + img + ", content=" + content + ", text=" + text + "]";
	}
}
