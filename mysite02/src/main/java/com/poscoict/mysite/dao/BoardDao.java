package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Session;

import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.MysiteVo;
import com.poscoict.mysite.vo.UserVo;

public class BoardDao {
	public List<BoardVo> findAll() {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<BoardVo> result = new ArrayList<BoardVo>();

	try {

		conn = getConnection();

		// 3. SQL 준비
		String sql = "select b.no, b.title, a.name, b.hitcnt, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, b.contents, b.g_no, b.o_no, b.depth from  user a, board b where a.no=b.user_no order by b.g_no desc, b.o_no";
		pstmt = conn.prepareStatement(sql);

		// 4. 바인딩

		// 5. SQL 실행
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int no = rs.getInt(1);
			String title = rs.getString(2);
			String name = rs.getString(3);
			int cnt = rs.getInt(4);
			String regDate = rs.getString(5);

			BoardVo vo = new BoardVo();
			vo.setNo((long) no);
			vo.setTitle(title);
			vo.setUserName(name);
			vo.setHit(cnt);
			vo.setRegDate(regDate);
			
			result.add(vo);
		}

	} catch (SQLException e) {
		System.out.print("error : " + e.getMessage());
	} finally {
		// 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return result;
	}
		
	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "insert into user values(null, ?,?,?,?, now())";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			// 5. SQL 실행

			result = pstmt.executeUpdate() == 1;

		} catch (SQLException e) {
			System.out.print("error : " + e.getMessage());
		} finally {
			// 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public UserVo findByEmailAndPassword(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo result = null;
		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "select no, name from user where email=? and password=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
				
			}
	
			// 5. SQL 실행
		} catch (SQLException e) {
			System.out.print("error : " + e.getMessage());
		} finally {
			// 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public UserVo findByNo(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo result = null;
		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "select name, gender, email from user where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				String name = rs.getString(1);
				String gender = rs.getString(2);
				String email = rs.getString(3);


				
				result = new UserVo();
				result.setName(name);
				result.setGender(gender);
				result.setEmail(email);


				
			}
	
			// 5. SQL 실행
		} catch (SQLException e) {
			System.out.print("error : " + e.getMessage());
		} finally {
			// 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean update(long no, String name, String password, String gender) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Session session = null;

		try {
			conn = getConnection();
			
			// 3. SQL 준비
			if(password.isBlank()){
				String sql = "update user set name=?, gender = ? where no=?";
				pstmt = conn.prepareStatement(sql);

				// 4. 바인딩
				pstmt.setString(1, name);
				pstmt.setString(2, gender);
				pstmt.setLong(3, no);
				// 5. SQL 실행

				result = pstmt.executeUpdate() == 1;

			}else {
			String sql = "update user set name=?, gender = ?, password= ? where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, name);
			pstmt.setString(2, gender);
			pstmt.setString(3, password);
			pstmt.setLong(4, no);
			// 5. SQL 실행

			result = pstmt.executeUpdate() == 1;
			}
		} catch (SQLException e) {
			System.out.print("error : " + e.getMessage());
		} finally {
			// 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn =null;
		try {
			// 1. JDBC드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2.연결하기
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		}catch (ClassNotFoundException e) {
			System.out.print("드라이버 로딩 실패 : " + e.getMessage());
		}
		return conn;
		}



}
