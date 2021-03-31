package action.album;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import music.MusicImpl;
import album.AlbumDTO;
import album.AlbumImpl;
import command.*;

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int al_no=Integer.parseInt(request.getParameter("al_no"));
		String pageNum=request.getParameter("pageNum");
		
		AlbumImpl albumImpl=AlbumImpl.getDao();
		AlbumDTO albumDto=albumImpl.getAlbum(al_no);
		
		MusicImpl musicImpl=MusicImpl.getDao();
		int count=musicImpl.getMusicCount(albumDto.getAl_code());
		
		List musicList=null;

		if(count>0){
			musicList=musicImpl.getMusicList(albumDto.getAl_code());
		}else{
			musicList=Collections.EMPTY_LIST;//비어있다는 뜻
		}
		
		String strDate = albumDto.getAl_release(); 
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		// String 타입을 Date 타입으로 변환 
		Date formatDate = dtFormat.parse(strDate); 
		// Date타입의 변수를 새롭게 지정한 포맷으로 변환 
		String al_release = newDtFormat.format(formatDate);

		request.setAttribute("al_release", al_release);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("al_no", new Integer(al_no));
		request.setAttribute("albumDto", albumDto);
		request.setAttribute("musicList", musicList);
		
		return "/album/updateForm.jsp";
	}

}
