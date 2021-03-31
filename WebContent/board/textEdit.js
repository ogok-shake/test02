


   function check(){
         
         if($('#title').val()==""){
            alert("제목을 입력해주세요.");
            $('#title').focus();
            return;
         }
         if($('#category').val()==""){
            alert("카테고리를 선택해주세요.");
            $('#category').focus();
            return;
         }
         if($('#summernote').val()==""){
            alert("내용을 입력해주세요.");
            $('#summernote').focus();
            return;
         }
 		if($('#thumbnail').val()==""||$('#thumbnail').val()==null){
			alert("대표사진을 선택해주세요.\n본문에 1개 이상의 사진을 등록 후, 대표사진을 클릭해주세요.");
			return;
		}
         
         document.upload.submit();
         
      }//function end
       
   function imgRemover(){
      for(i=1;i<=Number($('#img_num').val());i++){
      
         if($("img").is('[class*='+i+']')==false){
             if($("#thumbnail").val()==$("#"+i).attr("src")){
              	$("#thumbnail").val("");
              }
            $("#"+i).remove();
         }
 
      }//      
   }//   
   

      
      
          $(document).ready(function(){
              $('#summernote').summernote({ // summernote를 사용하기 위한 선언
                  height: 300,
               callbacks: { // 콜백을 사용
                      // 이미지를 업로드할 경우 이벤트를 발생
                   onImageUpload: function(files, editor, welEditable) {
                      
                         sendFile(files[0], this);
                      
                   },
					onChange: function(contents,$editable){
						console.log('onChange:',contents,$editable);
						imgRemover();
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
                        
                     var i=$('#img_num').val();
                       
                        if(data.fileExt!="jpg"&&data.fileExt!="png"){
                        	alert("이미지 확장자를 확인해주세요.\njpg, png 이미지만 업로드 할 수 있습니다.");
                        	return;
                        }else{
                            $(editor).summernote('editor.insertImage', data.url);
                            //$(".note-editable p img[src$='"+data.fileName+"']").attr('class',i);
                            $("img[src$='"+data.fileName+"']").attr('class',i);
                            //음... 클래스 명 지정해주는 거 다시 해야할듯...
                            var a=$('<img />')
                            .attr('id',i)
                            .attr('class','image')
                            .attr('src','/krystal/upload/THUMB_'+data.fileName)
                            .attr('width','100')
                            .attr('onClick',"$('img').attr('style','border:none');$(this).attr('style','border:5px solid #9c3639');document.upload.thumbnail.value='"+"/krystal/upload/THUMB_"+data.fileName+"';");
                            
                            $("#thumbnail").before(" ");
                            $("#thumbnail").before(a);
                            var ii=Number(i)+1;
                            $('#img_num').val(String(ii));
                        }
                  }
              });
          }   
       
             
   
