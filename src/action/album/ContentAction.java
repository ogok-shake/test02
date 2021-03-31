package action.album;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import music.*;
import album.*;
import command.*;
public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int al_no=Integer.parseInt(request.getParameter("al_no"));
		String pageNum=request.getParameter("pageNum");
		
		AlbumImpl albumImpl=AlbumImpl.getDao();
		AlbumDTO albumDto=albumImpl.getAlbum(al_no);
		
		
		List musicList=null;
		MusicImpl musicImpl=MusicImpl.getDao();		
		int count=musicImpl.getMusicCount(albumDto.getAl_code());

		if(count>0){
			musicList=musicImpl.getMusicList(albumDto.getAl_code());
		}else{
			musicList=Collections.EMPTY_LIST;//����ִٴ� ��
		}
		
		//========================================
		List musicVideo=null;
	

		musicVideo=musicImpl.getMusicVideo(albumDto.getAl_code());
		if(musicVideo==null){
			musicVideo=Collections.EMPTY_LIST;//����ִٴ� ��
		}
		//========================================
		String strDate = albumDto.getAl_release(); 
	
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		// String Ÿ���� Date Ÿ������ ��ȯ 
		Date formatDate = dtFormat.parse(strDate); 
		// DateŸ���� ������ ���Ӱ� ������ �������� ��ȯ 
		String al_release = newDtFormat.format(formatDate);

		request.setAttribute("al_release", al_release);
		//=========================================
		request.setAttribute("albumDto", albumDto);
		request.setAttribute("musicList", musicList);
		request.setAttribute("musicVideo", musicVideo);
		
		request.setAttribute("al_no", new Integer(al_no));
		request.setAttribute("pageNum", pageNum);
		
		return "/album/content.jsp";
	}

}//class end
