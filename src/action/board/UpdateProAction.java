package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.*;
import command.*;
public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String pageNum=request.getParameter("pageNum");
		
		BoardDTO dto=new BoardDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setTitle(request.getParameter("title"));
		dto.setCategory(request.getParameter("category"));
		dto.setContent(request.getParameter("content"));
		dto.setThumbnail(request.getParameter("thumbnail"));
		
		BoardImpl dao=BoardImpl.getDao();
		int check=dao.updateArticle(dto);
		request.setAttribute("check", new Integer(check));
		request.setAttribute("pageNum", pageNum);
		
		return "/board/updatePro.jsp";
	}
	
}//class end
