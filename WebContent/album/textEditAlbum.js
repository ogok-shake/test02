
	function check(){
        if($('#al_jacket').val()==""||$('#al_jacket').val()==null){
            alert("앨범자켓을 등록해주세요.");
            $('#al_jacket').focus();
            return;
         }
         if($('#al_code').val()==""){
            alert("앨범코드를 입력해주세요.");
            $('#al_code').focus();
            return;
         }
         if($('#al_name').val()==""){
            alert("앨범명을 입력해주세요.");
            $('#al_name').focus();
            return;
         }
         if($('#al_artist').val()==""){
        	 alert("아티스트명을 입력해주세요.");
        	 $('#al_artist').focus();
        	 return;
         }
         if($('#al_release').val()==""||$('#al_release').val()==null){
        	 alert("발매일을 입력해주세요.");
        	 $('#al_release').focus();
        	 return;
         }
         for(var i=1;i<=Number($('#countTrack').val());i++){
        	 
             if($('#mu_name'+i).val()==""){
            	 alert("곡명을 입력해주세요.");
            	 $('#mu_name'+i).focus();
            	 return;
             }
             if($('#mu_artist'+i).val()==""){
            	 alert("아티스트명을 입력해주세요.");
            	 $('#mu_artist'+i).focus();
            	 return;
             }
             
         }//for end

         
         document.al_upload.submit();
         
	}//check()

	
	function appendMusic(){
		
		var i=$('#countTrack').val();
		var ii=Number(i)+1;
		var trackTable=$('#track1')
		.clone()
		.attr('id',"track"+ii)
        .attr('class',"track"+ii);
		
		$('#track').append(trackTable);		

		
		$("#track"+ii+" input[id=mu_name1]").attr('id','mu_name'+ii).attr('name','mu_name'+ii);
		$("#track"+ii+" input[id=mu_artist1]").attr('id','mu_artist'+ii).attr('name','mu_artist'+ii);
		$("#track"+ii+" input[id=mu_lyricist1]").attr('id','mu_lyricist'+ii).attr('name','mu_lyricist'+ii);
		$("#track"+ii+" input[id=mu_songwriter1]").attr('id','mu_songwriter'+ii).attr('name','mu_songwriter'+ii);
		$("#track"+ii+" input[id=mu_arranger1]").attr('id','mu_arranger'+ii).attr('name','mu_arranger'+ii);
		$("#track"+ii+" input[id=mu_genre1]").attr('id','mu_genre'+ii).attr('name','mu_genre'+ii);
		$("#track"+ii+" input[id=mu_video1]").attr('id','mu_video'+ii).attr('name','mu_video'+ii);
		$("#track"+ii+" textarea[id=mu_lyric1]").attr('id','mu_lyric'+ii).attr('name','mu_lyric'+ii);
		$("#track"+ii+" input[type=button]").attr('onclick','deleteTrack('+ii+')');

		
		$('#track'+ii+" input[type=text]").val('');
		$('#track'+ii+" textarea").val('');	
		
		$('#countTrack').val(String(ii));
		
	}//appendMusic
	
	function appendMusicUpdate(){
		
		var i=$('#countTrack').val();
		var ii=Number(i)+1;
		var trackTable=$('#track1')
		.clone()
		.attr('id',"track"+ii)
        .attr('class',"track"+ii);
		
		$('#track').append(trackTable);		

		
		$("#track"+ii+" input[id=mu_no1]").attr('id','mu_no'+ii).attr('name','mu_no'+ii);
		$("#track"+ii+" input[id=mu_name1]").attr('id','mu_name'+ii).attr('name','mu_name'+ii);
		$("#track"+ii+" input[id=mu_artist1]").attr('id','mu_artist'+ii).attr('name','mu_artist'+ii);
		$("#track"+ii+" input[id=mu_lyricist1]").attr('id','mu_lyricist'+ii).attr('name','mu_lyricist'+ii);
		$("#track"+ii+" input[id=mu_songwriter1]").attr('id','mu_songwriter'+ii).attr('name','mu_songwriter'+ii);
		$("#track"+ii+" input[id=mu_arranger1]").attr('id','mu_arranger'+ii).attr('name','mu_arranger'+ii);
		$("#track"+ii+" input[id=mu_genre1]").attr('id','mu_genre'+ii).attr('name','mu_genre'+ii);
		$("#track"+ii+" input[id=mu_video1]").attr('id','mu_video'+ii).attr('name','mu_video'+ii);
		$("#track"+ii+" textarea[id=mu_lyric1]").attr('id','mu_lyric'+ii).attr('name','mu_lyric'+ii);
		$("#track"+ii+" input[type=button]").attr('onclick','deleteTrack('+ii+')');

		
		$('#track'+ii+" input[type=text]").val('');
		$('#track'+ii+" textarea").val('');	
		
		$('#countTrack').val(String(ii));
		
	}//appendMusic
	
	function deleteTrack(ii){
		$('#track'+ii).remove();	
		
		
	}//deleteTrack
	
	


		$(document).ready(function(){
              $('#summernote').summernote({ // summernote를 사용하기 위한 선언
                  height: 300,
               callbacks: { // 콜백을 사용
                      // 이미지를 업로드할 경우 이벤트를 발생
                   onImageUpload: function(files, editor, welEditable) {
                      
                         sendFile(files[0], this);
                      
                   }
               }
            });
         });
      
         
          function sendFile(files, editor) {
              // 파일 전송을 위한 폼생성
             var data = new FormData();
              data.append("uploadFile", files);
              
              $.ajax({ // ajax를 통해 파일 업로드 처리
                  data : data,
                  type : "POST",
                  url : "textEditImage.jsp",
                  cache : false,
                  contentType : false,
                  processData : false,
                  success : function(data) { // 처리가 성공할 경우

                        if(data.fileExt!="jpg"&&data.fileExt!="png"){
                        	alert("이미지 확장자를 확인해주세요.\njpg, png 이미지만 업로드 할 수 있습니다.");
                        	return;
                        }else{
                            $(editor).summernote('editor.insertImage', data.url);
                        }
                  }
              });
          }   