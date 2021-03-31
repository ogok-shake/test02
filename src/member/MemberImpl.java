package member;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;

import member.*;

import java.util.*;
public class MemberImpl implements Member{
	
	//싱글톤 객체 사용,객체를 하나만 사용하겠다.(메모리 절약)
	private static MemberImpl member=new MemberImpl();
	
	private MemberImpl(){};
	
	//메서드,JSP에서 사용할 메서드
	public static MemberImpl getInstance(){
		return member;
	}//getInstance
	
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon() end

	@Override
	public void insertMember(MemberDTO dto) throws Exception {
	
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("insert into member(id, pw, name, birth, hp, email, regdate, condate, zipcode, addr, addr2) values(?,?,?,?,?,?,NOW(),NOW(),?,?,?)");
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
		
			pstmt.setString(4, dto.getBirth());
			
			pstmt.setString(5, dto.getHp());
			pstmt.setString(6, dto.getEmail());

			pstmt.setString(7, dto.getZipcode());
			pstmt.setString(8, dto.getAddr());
			pstmt.setString(9, dto.getAddr2());
			
			pstmt.executeUpdate();
			
		}catch(Exception ex1){
			System.out.println("insertMember() 예외:"+ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
	}

	@Override
	public int idCheck(String id) throws Exception {
	
		int x=-10;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=getCon();
			pstmt=con.prepareStatement("select id from member where id=?");
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				x=1;
			}else{
				x=-1;
			}//else end
			
		}catch(Exception ex1){
			System.out.println("idCheck() 예외 :"+ex1);
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
	public int loginMember(String id, String pw) throws Exception {
	
		int x=-1;
		String dbPw="";
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		
		try{
			
			con=getCon();
			pstmt=con.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dbPw=rs.getString("pw");
				if(pw.equals(dbPw)){
					x=1;
					pstmt2=con.prepareStatement("update member set condate=NOW() where id=?");
					pstmt2.setString(1, id);
					pstmt2.executeUpdate();
				}else{
					x=0;
				}//else end
			}else{//로그인 실패:없는 아이디
				x=-1;
			}//else end
			
		}catch(Exception ex1){
			System.out.println("loginMember() 예외 :"+ex1);
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
	public MemberDTO getMember(String id) throws Exception {
	
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDTO dto=null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto=new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				
				dto.setBirth(rs.getString("birth"));
				dto.setHp(rs.getString("hp"));
				dto.setEmail(rs.getString("email"));
				
				dto.setRegdate(rs.getTimestamp("regdate"));
				dto.setCondate(rs.getTimestamp("condate"));
				
				dto.setStatus(rs.getString("status"));
				dto.setGrade(rs.getString("grade"));
				
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddr(rs.getString("addr"));
				dto.setAddr2(rs.getString("addr2"));
				//***
		
			}//if
		}catch(Exception ex1){
			System.out.println("getMember 예외:"+ex1);
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
	public void updateMember(MemberDTO dto) throws Exception {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getCon();
			String sql="update member set pw=?, name=?, birth=?, hp=?, email=?, zipcode=?, addr=?, addr2=? where id=?";
			//생일이랑 휴대폰 번호 바뀌는 것은 나중에  본인 인증 구현 해야할듯...
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getBirth());
			pstmt.setString(4, dto.getHp());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getZipcode());
			pstmt.setString(7, dto.getAddr());
			pstmt.setString(8, dto.getAddr2());
			pstmt.setString(9, dto.getId());
			
			pstmt.executeUpdate();
			
		}catch(Exception ex1){
			System.out.println("updateMember 예외:"+ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
	}

	@Override
	public int deleteMember(String id, String pw) throws Exception {
		//회원탈퇴
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		ResultSet rs=null;
		
		String dbPw="";
		int x=-10;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select pw from member where id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dbPw=rs.getString("pw");
				if(pw.equals(dbPw)){
					pstmt2=con.prepareStatement("delete from board where id=?");
					pstmt2.setString(1, id);
					pstmt2.executeUpdate();
					pstmt3=con.prepareStatement("delete from member where id=?");
					pstmt3.setString(1, id);
					pstmt3.executeUpdate();
					x=1;
				}else{
					x=-1;
				}//else
			}//if
			
		}catch(Exception ex1){
			System.out.println("deleteMember() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(pstmt2!=null){pstmt2.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return x;
		
	}//deleteMember

}//class
