package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.MysiteVo;

@Repository
public class MysiteRepository {
	public List<MysiteVo> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MysiteVo> result = new ArrayList<MysiteVo>();

		try {

			conn = getConnection();

			// 3. SQL 준비
			String sql = "select no, name, date_format(reg_date,'%Y/%m/%d %H:%i:%s') as reg_date, message from guestbook  order by no desc";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String reg_date = rs.getString(3);
				String message = rs.getString(4);

				MysiteVo vo = new MysiteVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setReg_date(reg_date);
				vo.setMessage(message);

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

	public boolean insert(MysiteVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "insert into guestbook values(null, ?,?,?, now())";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
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

	public boolean delete(MysiteVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "delete from guestbook where no= ? and password = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
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
