package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import board.*;
import command.*;

public class TextEditProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		BoardDTO dto=new BoardDTO();
		
		//클라이언트가 보내준 데이터 받아서 dto에 저장
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("memId");
		dto.setId(id);
		dto.setTitle(request.getParameter("title"));
		dto.setCategory(request.getParameter("category"));

		dto.setRef(Integer.parseInt(request.getParameter("ref")));
		dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		dto.setContent(request.getParameter("content"));
		dto.setThumbnail(request.getParameter("thumbnail"));
		dto.setIp(request.getRemoteAddr());
		
		BoardImpl dao=BoardImpl.getDao();
		dao.insertArticle(dto);
		
		return "/board/textEditPro.jsp";
	}//requestPro

}//class end
