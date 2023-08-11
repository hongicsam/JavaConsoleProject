package banking7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB implements Connect{
	
	public Connection con;
	public PreparedStatement psmt;
	public ResultSet rs;
	public Statement stmt;
	
	public ConnectDB() {}
	public ConnectDB(String user, String pass) {
		try {
			Class.forName(ORACLE_DRIVER);
			connect(user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	public ConnectDB(String driver, String user, String pass) {
		try {
			Class.forName(driver);
			connect(user, pass);
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void connect(String user, String pass) {
		try {
			con = DriverManager.getConnection(ORACLE_URL, user, pass);
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 오류");
			e.printStackTrace();
		}
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void close() {
		try {
			if(con!=null) {con.close();}
			if(psmt!=null) {psmt.close();}
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		} catch (Exception e) {
			System.out.println("자원 반납시 오류발생");
			e.printStackTrace();
		}
		
	}
}
