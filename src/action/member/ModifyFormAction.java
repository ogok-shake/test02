package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import member.*;
public class ModifyFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		String id=request.getParameter("id");
		MemberImpl dao=MemberImpl.getInstance();

		MemberDTO dto=dao.getMember(id);	
		
		String hp=dto.getHp();
		String hp1=hp.substring(0,3);
		String hp2=hp.substring(3,7);
		String hp3=hp.substring(7,11);
		
		request.setAttribute("hp1", hp1);
		request.setAttribute("hp2", hp2);
		request.setAttribute("hp3", hp3);
		request.setAttribute("dto", dto);
		
		
		return "/member/modifyForm.jsp";
	}

}//class end
