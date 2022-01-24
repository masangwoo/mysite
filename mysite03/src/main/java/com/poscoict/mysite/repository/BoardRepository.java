package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	public List<BoardVo> findAll(int i) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<BoardVo> result = new ArrayList<BoardVo>();

	try {

		conn = getConnection();

		// 3. SQL 준비
		String sql = "select b.no, b.title, a.name, b.hit, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, b.g_no, b.o_no, b.depth,a.no , b.contents from  user a, board b where a.no=b.user_no order by b.g_no desc, b.o_no limit ?, 5";
		pstmt = conn.prepareStatement(sql);

		// 4. 바인딩
		pstmt.setInt(1, i);
		// 5. SQL 실행
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int no = rs.getInt(1);
			String title = rs.getString(2);
			String name = rs.getString(3);
			int cnt = rs.getInt(4);
			String regDate = rs.getString(5);
			int groupNo = rs.getInt(6);
			int orderNo = rs.getInt(7);
			int depth = rs.getInt(8);
			int userNo = rs.getInt(9);
			

			BoardVo vo = new BoardVo();
			vo.setNo((long) no);
			vo.setTitle(title);
			vo.setUserName(name);
			vo.setHit(cnt);
			vo.setRegDate(regDate);
			vo.setGroupNo(groupNo);
			vo.setOrderNo(orderNo);
			vo.setDepth(depth);
			vo.setUserNo((long)userNo);
			
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
	
	public List<BoardVo> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVo> result = new ArrayList<BoardVo>();

		try {

			conn = getConnection();

			// 3. SQL 준비
			String sql = "select b.no, b.title, a.name, b.hit, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, b.g_no, b.o_no, b.depth,a.no , b.contents from  user a, board b where a.no=b.user_no order by b.g_no desc, b.o_no";
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
				int groupNo = rs.getInt(6);
				int orderNo = rs.getInt(7);
				int depth = rs.getInt(8);
				int userNo = rs.getInt(9);
				

				BoardVo vo = new BoardVo();
				vo.setNo((long) no);
				vo.setTitle(title);
				vo.setUserName(name);
				vo.setHit(cnt);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo((long)userNo);
				
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
	
	public int getTotal() {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) as total from board";
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("total");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		
	public boolean insert(String title, String contents, long userNo ) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = " insert into board values(null, ?, ?, 0, (select max(g_no)+1 from board b), 1, 1, now(), ?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setLong(3, userNo);

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
	
	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			update(vo.getOrderNo(), vo.getGroupNo());
			
			conn = getConnection();

			// 3. SQL 준비
			String sql = " insert into board values(null, ?, ?, 0, ?, ? +1, ? +1, now(), ?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getGroupNo());
			pstmt.setInt(4, vo.getOrderNo());
			pstmt.setInt(5, vo.getDepth());
			pstmt.setLong(6, vo.getUserNo());

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
	
	
	public BoardVo view(long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo result = null;
		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "select title, contents, hit, g_no, o_no, user_no, depth from board where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String title = rs.getString(1);
				String content = rs.getString(2);
				int hit = rs.getInt(3);
				int groupNo = rs.getInt(4);
				int orderno = rs.getInt(5);
				long userNo = rs.getLong(6);
				int depth = rs.getInt(7);

				result = new BoardVo();
				result.setTitle(title);
				result.setContents(content);
				result.setHit(hit);
				result.setGroupNo(groupNo);
				result.setOrderNo(orderno);
				result.setUserNo((long)userNo);
				result.setNo((long)no);
				result.setDepth(depth);
	
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
	
	public BoardVo findByNo(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo result = null;
		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "select no, title, contents, user_no from board where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				int bno = rs.getInt(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int uno = rs.getInt(4);

				result = new BoardVo();
				result.setNo((long)bno);
				result.setTitle(title);
				result.setContents(contents);
				result.setUserNo((long)uno);


				
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
	
	
	public boolean update(long no, String title, String contents) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			// 3. SQL 준비
			
			String sql = "update board set title=?, contents = ? where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setLong(3, no);
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
	
	public boolean update(int orderNo, int groupNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			// 3. SQL 준비
			
			String sql = "update board set o_no=o_no+1 where o_no>? and g_no = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, groupNo);
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
	
	public boolean delete(long no) {
		
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "delete from board where no= ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);
			
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

	public boolean hitUp(int no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "update board set hit=hit+1 where no = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setInt(1, no);
			
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

	public int count() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no=0;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select count(*) from board";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				no = rs.getInt(1);				
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
		return no;
	}


}
