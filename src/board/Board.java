package board;

import java.util.*;

public interface Board {
	public void insertArticle(BoardDTO dto) throws Exception;//�۾���-�α����� �����
	public int getArticleCount() throws Exception;//�۰���
	public List getList(int start,int cnt) throws Exception;//����Ʈ
	public BoardDTO getArticle(int num) throws Exception;//�۳��뺸��
	public BoardDTO getUpdateArticle(int num) throws Exception;//������Ʈ:�ۺҷ�����-�ۼ��� �����&�α����� �����
	public int updateArticle(BoardDTO dto) throws Exception;//������Ʈ
	public int deleteArticle(int num) throws Exception;//�ۻ���-�ۼ��� �����&�α����� �����
}
