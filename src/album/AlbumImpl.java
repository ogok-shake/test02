package album;

import java.util.*;
import java.io.File;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.*;
import javax.naming.*;

import music.MusicDTO;
import music.MusicImpl;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class AlbumImpl implements Album{
	
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";
	
	private static AlbumImpl dao=new AlbumImpl();
	
	public static AlbumImpl getDao(){
		return dao;
	}
	
	private AlbumImpl(){}
	
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon

	@Override
	public void insertAlbum(HttpServletRequest request) throws Exception {
		
		try{
			con=getCon();
			String uploadPath = request.getServletContext().getRealPath("/upload");

			MultipartRequest multi=new MultipartRequest(request,uploadPath,10*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
			
			sql="insert into album(al_code,al_name,al_artist,al_jacket,al_genre,al_type,al_distributor,al_comp,al_release,al_detail) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, multi.getParameter("al_code"));
			pstmt.setString(2, multi.getParameter("al_name"));
			pstmt.setString(3, multi.getParameter("al_artist"));
			pstmt.setString(4, multi.getFilesystemName("al_jacket"));
			pstmt.setString(5, multi.getParameter("al_genre"));
			pstmt.setString(6, multi.getParameter("al_type"));
			pstmt.setString(7, multi.getParameter("al_distributor"));
			pstmt.setString(8, multi.getParameter("al_comp"));
			pstmt.setString(9, multi.getParameter("al_release"));
			pstmt.setString(10, multi.getParameter("al_detail"));
			
			pstmt.executeUpdate();
			
			//---------------------------------------------------
			int countTrack=Integer.parseInt(multi.getParameter("countTrack"));
//			Integer ct=new Integer(countTrack);
			MusicDTO musicDto=new MusicDTO();
			MusicImpl musicImpl=MusicImpl.getDao();
			
			
			
			for(int i=1;i<=countTrack;i++){
				String mu_name=multi.getParameter("mu_name"+i);
				if(mu_name!=null&&!mu_name.equals("")){	
					musicDto.setMu_album(multi.getParameter("al_code"));
					musicDto.setMu_name(multi.getParameter("mu_name"+i));
					musicDto.setMu_artist(multi.getParameter("mu_artist"+i));
					musicDto.setMu_lyricist(multi.getParameter("mu_lyricist"+i));
					musicDto.setMu_songwriter(multi.getParameter("mu_songwriter"+i));
					musicDto.setMu_arranger(multi.getParameter("mu_arranger"+i));
					musicDto.setMu_genre(multi.getParameter("mu_genre"+i));
					musicDto.setMu_video(multi.getParameter("mu_video"+i));
					musicDto.setMu_lyric(multi.getParameter("mu_lyric"+i));
					
					
					musicImpl.insertMusic(musicDto);
				}
			}
			
			//-----------------------------------------------------
			
			
			
		}catch(Exception ex1){
			System.out.println("insertAlbum() 예외:"+ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}
		
	}//

	@Override
	public int getAlbumCount() throws Exception {
		int x=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from album");
			rs=pstmt.executeQuery();
			if(rs.next()){
				x=rs.getInt(1);
			}//if
			
		}catch(Exception ex1){
			System.out.println("getAlbumCount() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
	
		return x;
	}
	//===============================
	public String getAlbumJacket(int al_no) throws Exception {
		String jacket="";
		try{
			con=getCon();
			pstmt=con.prepareStatement("select al_jacket from album where al_no=?");
			pstmt.setInt(1, al_no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				jacket=rs.getString(1);
			}//if
			
		}catch(Exception ex1){
			System.out.println("getAlbumJacket() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
	
		return jacket;
	}
	
	//=================================
	@Override
	public List getAlbumList(int start, int cnt) throws Exception {
		
		List<AlbumDTO> list=null;
		
		try{
			con=getCon();
			sql="select * from album order by al_no desc limit ?,?";//des로바꿔보기
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, cnt);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				list=new ArrayList<AlbumDTO>();//객체생성
				do{
					AlbumDTO dto=new AlbumDTO();//객체생성
					dto.setAl_no(rs.getInt("al_no"));
					dto.setAl_code(rs.getString("al_code"));
					dto.setAl_name(rs.getString("al_name"));
					
					dto.setAl_artist(rs.getString("al_artist"));
					dto.setAl_jacket(rs.getString("al_jacket"));
					dto.setAl_genre(rs.getString("al_genre"));
					
					dto.setAl_type(rs.getString("al_type"));
					dto.setAl_distributor(rs.getString("al_distributor"));
					dto.setAl_comp(rs.getString("al_comp"));
					
					dto.setAl_release(rs.getString("al_release"));
					dto.setAl_detail(rs.getString("al_detail"));				
					
					list.add(dto);
					
				}while(rs.next());
			}//if
			
		}catch(Exception ex1){
			System.out.println("getAlbumList 예외:"+ex1);
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
	public AlbumDTO getAlbum(int al_no) throws Exception {
		AlbumDTO dto=null;
		
		try{
		
			con=getCon();
			
			pstmt=con.prepareStatement("select * from album where al_no=?");
			pstmt.setInt(1, al_no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto=new AlbumDTO();
				
				dto.setAl_no(rs.getInt("al_no"));
				dto.setAl_code(rs.getString("al_code"));
				dto.setAl_name(rs.getString("al_name"));
				
				dto.setAl_artist(rs.getString("al_artist"));
				dto.setAl_jacket(rs.getString("al_jacket"));
				dto.setAl_genre(rs.getString("al_genre"));
				
				dto.setAl_type(rs.getString("al_type"));
				dto.setAl_distributor(rs.getString("al_distributor"));
				dto.setAl_comp(rs.getString("al_comp"));
				
				dto.setAl_release(rs.getString("al_release"));
				dto.setAl_detail(rs.getString("al_detail"));
				
			}//if
			
		}catch(Exception ex1){
			System.out.println("getAlbum 예외:"+ex1);
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
	public int deleteAlbum(int al_no) throws Exception {
		//앨범, 뮤직 삭제부분 로직 다시 짜기...
		int x=-10;
		int count=-10;
		String al_code="";
		try{
			con=getCon();
			//앨범 번호로 앨범 코드 불러오기
			pstmt=con.prepareStatement("select al_code from album where al_no=?");
			pstmt.setInt(1, al_no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				al_code=rs.getString(1);
			}//if

			pstmt=con.prepareStatement("select * from music where mu_album=?");
			pstmt.setString(1, al_code);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pstmt=con.prepareStatement("delete from music where mu_album=?");
				pstmt.setString(1, al_code);
				pstmt.executeUpdate();
			}
			
			//=============
			//수록곡 지운 뒤 앨범 지워야함 .무결성!!!
			//===============
			
			pstmt=con.prepareStatement("select * from album where al_no=?");
			pstmt.setInt(1, al_no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pstmt=con.prepareStatement("delete from album where al_no=?");
				pstmt.setInt(1, al_no);
				pstmt.executeUpdate();
				x=1;
			}else{
				x=0;
			}
			
			
		}catch(Exception ex1){
			System.out.println("deleteAlbum() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return x;
	}

}
