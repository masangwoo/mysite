package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAll(Long i, String kwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", i);
		map.put("kwd", kwd);
		List<BoardVo> list = sqlSession.selectList("board.findAll",map);
		return list;
	}
	
	/*public List<BoardVo> findAll() {
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
		
	public int getTotal(String keyword) {
		//return sqlSession.selectOne("board.getToTalCount", "%"+keyword+"%");
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
	}*/
		
	public boolean insert2(String title, String contents, long userNo ) {
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(userNo);
		return sqlSession.insert("board.insert2", vo) == 1;
	}
	
	public boolean insert1(BoardVo vo) {
		System.out.println(vo);
		return sqlSession.insert("board.insert1", vo) == 1;
	}
	
	
	public BoardVo view(long no) {
		return sqlSession.selectOne("board.view",no);
	}
	
	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}
	
	
	public boolean update1(long no, String title, String contents) {
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(contents);

		int count = sqlSession.update("board.update1",vo);
		return count==1;
	}
	
	public boolean update2(int orderNo, int groupNo) {
		BoardVo vo = new BoardVo();
		vo.setOrderNo(orderNo);
		vo.setGroupNo(groupNo);

		int count = sqlSession.update("board.update2",vo);
		return count==1;
	}
	
	public boolean delete(long no) {
		int count = sqlSession.delete("board.delete", no);
		return count==1;
	}

	/*public boolean hitUp(int no) {
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
	*/
	private Connection getConnection() throws SQLException{
		Connection conn =null;
		try {
			// 1. JDBC드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			// 2.연결하기
			String url = "jdbc:mysql://192.168.0.47:3307/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
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

	public int count(String kwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no=0;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select count(*) from  board where title  like '%"+kwd+"%' order by g_no desc, o_no;";
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
