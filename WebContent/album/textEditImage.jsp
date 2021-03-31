<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="java.net.InetAddress"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.io.*" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="org.imgscalr.Scalr" %>
<%@ page import="thumbnail.ThumbnailMaker" %>

<%

	request.setCharacterEncoding("utf-8");
    // 이미지 업로드할 경로
	String uploadPath = config.getServletContext().getRealPath("/upload");
	//String uploadPath ="C:\\java_work\\02_jsp_work\\krystal\\WebContent\\upload\\";
    int size = 10*1024*1024;  // 업로드 사이즈 제한 10M 이하
  
	String fileName = ""; // 파일명
	
	try{
        // 파일업로드 및 업로드 후 파일명 가져옴
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
		Enumeration files = multi.getFileNames();
		String file = (String)files.nextElement(); 
		fileName = multi.getFilesystemName(file); 
		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
    // 업로드된 경로와 파일명을 통해 이미지의 경로를 생성
    //uploadPath = uploadPath + fileName;
    //uploadPath = "http://"+ InetAddress.getLocalHost().getHostAddress() +":"+ request.getServerPort() +"/krystal/upload/"+fileName;
    uploadPath = request.getContextPath()+"/upload/"+fileName;
	
    // 생성된 경로를 JSON 형식으로 보내주기 위한 설정
	JSONObject jobj = new JSONObject();

		
	int index=fileName.lastIndexOf(".");
	
	String fileExt=fileName.substring(index+1);

	jobj.put("url", uploadPath);
	jobj.put("fileName", fileName);
	jobj.put("fileExt", fileExt);
	
	response.setContentType("application/json"); // 데이터 타입을 json으로 설정하기 위한 세팅
	out.print(jobj.toJSONString());
%>





