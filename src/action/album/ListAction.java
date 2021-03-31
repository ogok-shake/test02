package action.album;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import album.*;
import command.*;
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}
		
		int pageSize=6;
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		int count=0;
		int number=0;
		int pageBlock=10;
		
		List albumList=null;
		AlbumImpl dao=AlbumImpl.getDao();
		count=dao.getAlbumCount();
		
		if(count>0){
			albumList=dao.getAlbumList(startRow, pageSize);
		}else{
			albumList=Collections.EMPTY_LIST;//비어있다는 뜻
		}
		
		number=count-(currentPage-1)*pageSize;
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		int startPage=(int)(currentPage/pageBlock)*10+1;
		int endPage=startPage+pageBlock-1;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("pageBlock", new Integer(pageBlock));
		request.setAttribute("pageCount", new Integer(pageCount));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("albumList", albumList);
		
		return "/album/list.jsp";
	}
	
}//class end
















