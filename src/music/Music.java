package music;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface Music {
	
	public void insertMusic(MusicDTO musicDto) throws Exception;
	public int getMusicCount(String mu_album) throws Exception;
	public List getMusicList(String mu_album) throws Exception;
	public MusicDTO getMusic(int mu_no) throws Exception;
	public void updateMusic(MusicDTO dto) throws Exception;
	public int deleteMusic(int mu_no) throws Exception;
	public List getMusicVideo(String mu_album) throws Exception;

}
