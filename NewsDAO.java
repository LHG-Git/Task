package Homework2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<News> getAll() throws Exception {
		Connection conn = open();
		List<News> newsList = new ArrayList<>();
		
		String sql = "select aid, title, img, PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss.SSSSSS') as cdate, content, text from news";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				News n = new News();
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setImg(rs.getString("img"));
//				n.setDate(rs.getString("cdate"));
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
//				n.setDate(LocalDateTime.parse(rs.getString("cdate"), formatter));
				LocalDateTime dateTime = rs.getTimestamp("cdate").toLocalDateTime();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            n.setDate(dateTime.format(formatter));
				n.setContent(rs.getString("content"));
				n.setText(rs.getString("text"));
				newsList.add(n);
				System.out.println("getAll: "+n.getAid());
			}
			return newsList;
		}
	}
	
	public void addNews(News n) throws Exception {
		Connection conn = open();
		
		String sql = "insert into news(title, img, date, content, text) values(?,?, CURRENT_TIMESTAMP(),?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		try(conn; pstmt) {
//			pstmt.setInt(1, n.getAid());
			pstmt.setString(1,  n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getContent());
			pstmt.setString(4, n.getText());
			pstmt.executeUpdate();
			
			 ResultSet generatedKeys = pstmt.getGeneratedKeys();
		        if (generatedKeys.next()) {
		            int generatedAid = generatedKeys.getInt(1);
		            n.setAid(generatedAid);
		            System.out.println("NewsDAO addNews: " + generatedAid);
		        }
		}
		System.out.println("NewsDAO addNews: "+n.getAid());
	}
	
	public News getNews(int aid) throws SQLException {
		Connection conn = open();
		
		News n = new News();
		String sql = "select aid, title, img, PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss.SSSSSS') as cdate, content from news where aid = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		System.out.println("NewsDAO getNews: " + aid);
		try(conn; pstmt; rs) {
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setImg(rs.getString("img"));
			n.setDate(rs.getString("cdate"));
//			LocalDateTime dateTime = rs.getTimestamp("cdate").toLocalDateTime();
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			n.setContent(rs.getString("content"));
			pstmt.executeQuery();
			return n;
		}
	}
	
	public void delNews(int aid) throws SQLException {
		Connection conn = open();
		
		String sql = "delete from news where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setInt(1,  aid);
			if (pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}
	
	public void updateNews(News n, int aid) throws Exception {
		Connection conn = open();
		
		String sql = "UPDATE news SET title=?, img=?, content=?, text=? where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
//		n.setAid(aid);
		try(conn; pstmt) {
			pstmt.setString(1,  n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getContent());
			pstmt.setString(4, n.getText());
	        pstmt.setInt(5, aid);
	        pstmt.executeUpdate();
		}
		
		System.out.println("NewsDAO updateNews "+aid);
	}
	
	
	public int getAidByTitle(String title) throws Exception {
	    Connection conn = open();
	    System.out.println("getAidByTitle 제목:"+title);
	    String sql = "SELECT aid FROM news WHERE title = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, title);
	    ResultSet rs = pstmt.executeQuery();
	    System.out.println("getAidByTitle 제목:"+title);
	    int aid = 4; // 기본적으로 -1을 반환하도록 초기화
	    
	    if (rs.next()) {
	        aid = rs.getInt("aid");
	    }
	    
	    rs.close();
	    pstmt.close();
	    conn.close();
	    
	    return aid;
	}
}
