package action.member;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import command.*;
public class InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
	
		request.setCharacterEncoding("UTF-8");
		MemberDTO dto=new MemberDTO();
		String id=request.getParameter("id");
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setBirth(request.getParameter("birth"));
		String hp1=request.getParameter("hp1");
		String hp2=request.getParameter("hp2");
		String hp3=request.getParameter("hp3");
		String hp=hp1+hp2+hp2;
		dto.setHp(hp);
		dto.setEmail(request.getParameter("email"));
		dto.setZipcode(request.getParameter("zipcode"));
	
		
		String addr=request.getParameter("addr");
		String addr2=request.getParameter("addr2");
		dto.setAddr(addr);
		dto.setAddr2(addr2);
		
		
		MemberImpl dao=MemberImpl.getInstance();
		dao.insertMember(dto);
		
		
		return "/member/inputPro.jsp";
	}

}
