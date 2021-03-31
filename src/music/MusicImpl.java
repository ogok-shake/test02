package music;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.*;
import javax.naming.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//multipartRequest가  music이랑 album에 두번 들어감...망..
//https://blog.naver.com/software705/220551397421

public class MusicImpl implements Music{
	
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";
	
	private static MusicImpl dao=new MusicImpl();
	
	public static MusicImpl getDao(){
		return dao;
	}
	
	private MusicImpl(){}
	
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon

	@Override
	public void insertMusic(MusicDTO musicDto) throws Exception {
		
		try{
			con=getCon();
			
			sql="insert into music(mu_album, mu_name, mu_artist, mu_lyricist, mu_songwriter, mu_arranger, mu_genre, mu_lyric, mu_video) "
					+ "values(?,?,?,?,?,?,?,?,?)";


			
			pstmt=con.prepareStatement(sql);

			pstmt.setString(1, musicDto.getMu_album());
			pstmt.setString(2, musicDto.getMu_name());
			pstmt.setString(3, musicDto.getMu_artist());
			pstmt.setString(4, musicDto.getMu_lyricist());
			pstmt.setString(5, musicDto.getMu_songwriter());
			pstmt.setString(6, musicDto.getMu_arranger());
			pstmt.setString(7, musicDto.getMu_genre());
			pstmt.setString(8, musicDto.getMu_lyric());
			pstmt.setString(9, musicDto.getMu_video());
			
			pstmt.executeUpdate();
				
		}catch(Exception ex1){
			System.out.println("insertMusic() 예외:"+ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}
		
	}

	@Override
	public int getMusicCount(String mu_album) throws Exception {
	
		int x=0;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from music where mu_album=?");
			pstmt.setString(1, mu_album);
			rs=pstmt.executeQuery();
			if(rs.next()){
				x=rs.getInt(1);
			}//if
			
		}catch(Exception ex1){
			System.out.println("getMusicCount() 예외:"+ex1);
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
	public List getMusicList(String mu_album) throws Exception {
		
		List<MusicDTO> list=null;
		
		try{
			con=getCon();
			sql="select * from music where mu_album=? order by mu_no asc";//desc로바꿔보기
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mu_album);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				list=new ArrayList<MusicDTO>();//객체생성
				do{
					MusicDTO dto=new MusicDTO();//객체생성
					dto.setMu_no(rs.getInt("mu_no"));
					dto.setMu_album(rs.getString("mu_album"));
					dto.setMu_name(rs.getString("mu_name"));
					
					dto.setMu_artist(rs.getString("mu_artist"));
					dto.setMu_lyricist(rs.getString("mu_lyricist"));
					dto.setMu_songwriter(rs.getString("mu_songwriter"));					
					dto.setMu_arranger(rs.getString("mu_arranger"));
					
					dto.setMu_genre(rs.getString("mu_genre"));
					dto.setMu_lyric(rs.getString("mu_lyric"));
					
					dto.setMu_video(rs.getString("mu_video"));			
					
					list.add(dto);
					
				}while(rs.next());
			}//if
			
		}catch(Exception ex1){
			System.out.println("getMusicList 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return list;
	}
	
	//===================================================================================
	@Override
	public List getMusicVideo(String mu_album) throws Exception {
		
		List<MusicDTO> list=null;
		
		try{
			con=getCon();
			sql="select * from music where mu_album=? order by mu_no asc";//desc로바꿔보기
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mu_album);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				list=new ArrayList<MusicDTO>();//객체생성
				do{
					MusicDTO musicDto=new MusicDTO();//객체생성
					musicDto.setMu_no(rs.getInt("mu_no"));
					musicDto.setMu_album(rs.getString("mu_album"));
					musicDto.setMu_name(rs.getString("mu_name"));
					
					musicDto.setMu_artist(rs.getString("mu_artist"));
					musicDto.setMu_lyricist(rs.getString("mu_lyricist"));
					musicDto.setMu_songwriter(rs.getString("mu_songwriter"));					
					musicDto.setMu_arranger(rs.getString("mu_arranger"));
					
					musicDto.setMu_genre(rs.getString("mu_genre"));
					musicDto.setMu_lyric(rs.getString("mu_lyric"));
					
					musicDto.setMu_video(rs.getString("mu_video"));			
					
					list.add(musicDto);
					
				}while(rs.next());
			}//if
			
		}catch(Exception ex1){
			System.out.println("getMusicVideo 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return list;
	}
	//===================================================================================

	@Override
	public MusicDTO getMusic(int mu_no) throws Exception {
		
		MusicDTO dto=null;
		
		try{
			con=getCon();
			
			pstmt=con.prepareStatement("select * from music where mu_no=?");
			pstmt.setInt(1, mu_no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto=new MusicDTO();
				dto.setMu_no(rs.getInt("mu_no"));
				dto.setMu_album(rs.getString("mu_album"));
				dto.setMu_name(rs.getString("mu_name"));
				
				dto.setMu_artist(rs.getString("mu_artist"));
				dto.setMu_lyricist(rs.getString("mu_lyricist"));
				dto.setMu_songwriter(rs.getString("mu_songwriter"));					
				dto.setMu_arranger(rs.getString("mu_arranger"));
				
				dto.setMu_genre(rs.getString("mu_genre"));
				dto.setMu_lyric(rs.getString("mu_lyric"));
				
				dto.setMu_video(rs.getString("mu_video"));
				
			}//if
			
		}catch(Exception ex1){
			System.out.println("getMusic 예외:"+ex1);
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
	public void updateMusic(MusicDTO dto) throws Exception {
		
		
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from music where mu_no=?");
			pstmt.setInt(1, dto.getMu_no());
			rs=pstmt.executeQuery();
			if(rs.next()){

				sql="update music set mu_name=?,mu_artist=?,mu_lyricist=?,"
						+ "mu_songwriter=?,mu_arranger=?,mu_genre=?,mu_lyric=?,mu_video=? where mu_no=?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getMu_name());
				pstmt.setString(2, dto.getMu_artist());
				pstmt.setString(3, dto.getMu_lyricist());
				pstmt.setString(4, dto.getMu_songwriter());
				pstmt.setString(5, dto.getMu_arranger());
				pstmt.setString(6, dto.getMu_genre());
				pstmt.setString(7, dto.getMu_lyric());
				pstmt.setString(8, dto.getMu_video());
				pstmt.setInt(9, dto.getMu_no());

				pstmt.executeUpdate();
	
			}else{
				insertMusic(dto);
			}
		
		}catch(Exception ex1){
			System.out.println("updateMusic 예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}				
			}catch(Exception ex2){}
		}//finally
	
	}

	@Override
	public int deleteMusic(int mu_no) throws Exception {
		int x=-100;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from music where mu_no=?");
			pstmt.setInt(1, mu_no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pstmt=con.prepareStatement("delete from music where mu_no=?");
				pstmt.setInt(1, mu_no);
				pstmt.executeUpdate();
				x=1;
			}else{
				x=0;
			}
		}catch(Exception ex1){
			System.out.println("deleteMusic() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally
		
		return x;
	}

}//class end






