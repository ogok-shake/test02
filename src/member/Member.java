package member;

public interface Member {
	public void insertMember(MemberDTO dto) throws Exception;
	public int idCheck(String id) throws Exception;
	public int loginMember(String id, String pw) throws Exception;
	public MemberDTO getMember(String id) throws Exception;
	public void updateMember(MemberDTO dto) throws Exception;
	public int deleteMember(String id, String pw) throws Exception;

}//Member
