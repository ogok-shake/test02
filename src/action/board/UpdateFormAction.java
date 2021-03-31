package action.board;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.*;
import command.*;
import thumbnail.GetImgTag;
public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		BoardImpl dao=BoardImpl.getDao();
		BoardDTO dto=dao.getUpdateArticle(num);//수정할 내용 얻는다.
		Map map=GetImgTag.getFileName(dto.getContent());
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("dto", dto);
		request.setAttribute("map", map);
		
		return "/board/updateForm.jsp";
	}//requestPro

}//UpdateFormAction
