package board;

import java.util.*;

public interface Board {
	public void insertArticle(BoardDTO dto) throws Exception;//글쓰기-로그인한 사람만
	public int getArticleCount() throws Exception;//글개수
	public List getList(int start,int cnt) throws Exception;//리스트
	public BoardDTO getArticle(int num) throws Exception;//글내용보기
	public BoardDTO getUpdateArticle(int num) throws Exception;//업데이트:글불러오기-작성한 사람만&로그인한 사람만
	public int updateArticle(BoardDTO dto) throws Exception;//업데이트
	public int deleteArticle(int num) throws Exception;//글삭제-작성한 사람만&로그인한 사람만
}
