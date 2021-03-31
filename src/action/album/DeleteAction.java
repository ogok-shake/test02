package action.album;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import album.AlbumImpl;
import board.BoardImpl;
import command.*;
public class DeleteAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		int al_no=Integer.parseInt(request.getParameter("al_no"));
		String pageNum=request.getParameter("pageNum");
		
		AlbumImpl albumImpl=AlbumImpl.getDao();
		int check=albumImpl.deleteAlbum(al_no);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/album/delete.jsp";
	}

}

