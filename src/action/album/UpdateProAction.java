package action.album;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import music.MusicDTO;
import music.MusicImpl;
import album.AlbumDTO;
import album.AlbumImpl;
import command.*;
public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String pageNum=request.getParameter("pageNum");
		int al_no=Integer.parseInt(request.getParameter("al_no"));
		
		

		AlbumImpl albumDao=AlbumImpl.getDao();
		
		int check=albumDao.deleteAlbum(al_no);
		if(check==1){
			albumDao.insertAlbum(request);
		}
		request.setAttribute("check", check);
		request.setAttribute("pageNum", pageNum);
		
		return "/album/updatePro.jsp";
	}

}
