package action.album;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import album.AlbumImpl;
import music.MusicDTO;
import music.MusicImpl;
import command.*;
public class MusicContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
			
		int al_no=Integer.parseInt(request.getParameter("al_no"));	
		int mu_no=Integer.parseInt(request.getParameter("mu_no"));	
		String al_name=request.getParameter("al_name");
		String pageNum=request.getParameter("pageNum");
		
		MusicImpl musicImpl=MusicImpl.getDao();	
		MusicDTO musicDto=musicImpl.getMusic(mu_no);
		
		//가사======================================
		String mu_lyric=musicDto.getMu_lyric();
		mu_lyric=mu_lyric.replace("\n", "<br>");
		//==========================================
		
		//앨범 자켓====================================
		AlbumImpl albumImpl=AlbumImpl.getDao();
		String al_jacket=albumImpl.getAlbumJacket(al_no);
		//==========================================
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("al_name", al_name);
		request.setAttribute("al_jacket", al_jacket);
		request.setAttribute("mu_lyric", mu_lyric);
		request.setAttribute("al_no", new Integer(al_no));
		request.setAttribute("mu_no", new Integer(mu_no));
		request.setAttribute("musicDto", musicDto);
		
		return "/album/musicContent.jsp";
	}
	
}
