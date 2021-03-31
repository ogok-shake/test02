package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardImpl;
import command.*;
public class DeleteAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		BoardImpl dao=BoardImpl.getDao();
		int check=dao.deleteArticle(num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/board/delete.jsp";
	}

}
