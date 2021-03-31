package album;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface Album {
	public void insertAlbum(HttpServletRequest request) throws Exception;
	public int getAlbumCount() throws Exception;
	public List getAlbumList(int start,int cnt) throws Exception;
	public AlbumDTO getAlbum(int al_no) throws Exception;
	public int deleteAlbum(int al_no) throws Exception;
	public String getAlbumJacket(int al_no) throws Exception;
	
}
