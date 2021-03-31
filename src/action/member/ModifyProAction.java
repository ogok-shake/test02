package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import member.*;
public class ModifyProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		MemberDTO dto=new MemberDTO();
		
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setBirth(request.getParameter("birth"));
		dto.setEmail(request.getParameter("email"));
		String hp1=request.getParameter("hp1");
		String hp2=request.getParameter("hp2");
		String hp3=request.getParameter("hp3");
		dto.setHp(hp1+hp2+hp3);
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddr(request.getParameter("addr"));
		dto.setAddr2(request.getParameter("addr2"));
		MemberImpl dao=MemberImpl.getInstance();
		dao.updateMember(dto);
		
		return "/member/modifyPro.jsp";
	}

}
