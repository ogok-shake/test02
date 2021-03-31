package action.album;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import album.*;
import command.*;
public class TextEditProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");

		AlbumImpl albumImpl=AlbumImpl.getDao();
		albumImpl.insertAlbum(request);
	
		return "/album/textEditPro.jsp";
	}
	

	
}//class end
