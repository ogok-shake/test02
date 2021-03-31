package board;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

//import board.*;



public class BoardImpl implements Board{
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";
	
	private static BoardImpl dao=new BoardImpl();
	
	private BoardImpl(){}
	
	public static BoardImpl getDao(){
		return dao;
	}
	
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon

	@Override
	public void insertArticle(BoardDTO dto) throws Exception {
		int num=dto.getNum();
		int ref=dto.getRef();
		int re_step=dto.getRe_step();
		int re_level=dto.getRe_level();
		
		int number=0;
		
		try{
			con=getCon();
			
			pstmt=con.prepareStatement("select max(num) from board");
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				number=rs.getInt(1)+1;
				
			}else{//처음 글일때
				number=1;	
			}//else
			
			if(num!=0){
				
				//답글끼워 넣기 위치 확보
				sql="update board set re_step=re_step+1 where ref=? and re_step>?";
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				re_step=re_step+1;
				re_level=re_level+1;//글깊이
			}else{
				ref=number;
				re_step=0;
				re_level=0;
			}//else
			
			sql="insert into board(id,title,category,content,thumbnail,regdate,readcount,ref,re_step,re_level,ip) values(?,?,?,?,?,NOW(),?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getCategory());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getThumbnail());
			pstmt.setInt(6, dto.getReadcount());
			pstmt.setInt(7, ref);
			pstmt.setInt(8, re_step);
			pstmt.setInt(9, re_level);
			pstmt.setString(10, dto.getIp());
			
			pstmt.executeUpdate();
			
			
		}catch(Exception ex1){
			System.out.println("insertArticle() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
	}

	@Override
	public int getArticleCount() throws Exception {
		
		int x=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from board");
			rs=pstmt.executeQuery();
			if(rs.next()){
				x=rs.getInt(1);
			}//if
			
		}catch(Exception ex1){
			System.out.println("getArticleCount() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
	
		return x;
	}

	@Override
	public List getList(int start, int cnt) throws Exception {

		List<BoardDTO> list=null;
		try{
			con=getCon();
			sql="select * from board where category='gallery' order by ref asc,re_step asc limit ?,?";//des로바꿔보기
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, cnt);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				list=new ArrayList<BoardDTO>();//객체생성
				do{
					BoardDTO dto=new BoardDTO();//객체생성
					dto.setNum(rs.getInt("num"));
					dto.setId(rs.getString("id"));
					dto.setTitle(rs.getString("Title"));
					dto.setCategory(rs.getString("category"));
					dto.setContent(rs.getString("content"));
					dto.setThumbnail(rs.getString("thumbnail"));
					dto.setRegdate(rs.getTimestamp("regdate"));

					dto.setReadcount(rs.getInt("readcount"));
					dto.setRef(rs.getInt("ref"));
					dto.setRe_step(rs.getInt("re_step"));
					dto.setRe_level(rs.getInt("re_level"));				
					dto.setIp(rs.getString("ip"));
					
					list.add(dto);
					
				}while(rs.next());
			}//if
			
		}catch(Exception ex1){
			System.out.println("getList 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		
		return list;		
		
	}

	@Override
	public BoardDTO getArticle(int num) throws Exception {
		BoardDTO dto=null;
		
		try{
			con=getCon();
			sql="update board set readcount=readcount+1 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt=con.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto=new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("Title"));
				dto.setCategory(rs.getString("category"));
				dto.setContent(rs.getString("content"));
				dto.setThumbnail(rs.getString("thumbnail"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));				
				dto.setIp(rs.getString("ip"));
				
			}//if
			
		}catch(Exception ex1){
			System.out.println("getArticle 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}
		return dto;
	}

	@Override
	public BoardDTO getUpdateArticle(int num) throws Exception {

		BoardDTO dto=null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				dto=new BoardDTO();

				dto.setNum(rs.getInt("num"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("Title"));
				dto.setCategory(rs.getString("category"));
				dto.setContent(rs.getString("content"));
				dto.setThumbnail(rs.getString("thumbnail"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));				
				dto.setIp(rs.getString("ip"));
				
			}//while
		}catch(Exception ex1){
			System.out.println("updateGetArticle 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return dto;		
	}

	@Override
	public int updateArticle(BoardDTO dto) throws Exception {
		//추후에 세션과 작성자 일치 시,글번호 일치시 수정되도록 구현해보기..! 
		int x=-10;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, dto.getNum());
			rs=pstmt.executeQuery();
			if(rs.next()){

				sql="update board set title=?, category=?, content=?, thumbnail=?, regdate=NOW() where num=?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getCategory());
				pstmt.setString(3, dto.getContent());
				pstmt.setString(4, dto.getThumbnail());
				pstmt.setInt(5, dto.getNum());
				pstmt.executeUpdate();
					
				x=1;
					
			}else{
				x=0;
			}
		
		}catch(Exception ex1){
			System.out.println("updateArticle 예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}				
			}catch(Exception ex2){}
		}//finally
		return x;
	}

	@Override
	public int deleteArticle(int num) throws Exception {
		//서버에 업로드한 파일 삭제하기 기능 추가!!!!!!!
		int x=-100;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pstmt=con.prepareStatement("delete from board where num=?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				x=1;
			}else{
				x=0;
			}
	
		}catch(Exception ex1){
			System.out.println("deleteArticle() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return x;
	}

}//
