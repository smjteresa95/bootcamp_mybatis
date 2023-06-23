<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

 <style>
    div {
      border: 1px solid black;
    }
 </style>

</head>
<body>
<div class = "container" action="/score/detail" >

    <div class="row first-row" >
      <div class="row">
        <div class="col-1">
          글번호
        </div>
        <div class="col-1">
          ${blogId}
        </div>
        <div class="col-2">
          제목
        </div>
        <div class="col-4">
          ${blogTitle}
        </div>
        <div class="col-1">
          작성자
        </div>
        <div class="col-1">
          ${writer}
        </div>
        <div class="col-1">
          조회수
        </div>
        <div class="col-1">
          ${blogCount}
        </div>
      </div>
    </div>

    <div class="row second-row">
      <div class="col-1">
        작성일
      </div>
      <div class="col-5">
        ${publishedAt}
      </div>
      <div class="col-1">
        수정일
      </div>
      <div class="col-2">
        ${updatedAt}
      </div>
    </div>

    <div class="row third-row">
      <div class="col-1">
        본문
      </div>
      <div class="col-11">
        ${blogContent}
      </div>
    </div>

        <a class="btn btn-primary" href="/blog/list" method="GET">목록</a>

        <div class="row fifth-row">
             <form action="/blog/delete" method="POST">
                <input type="hidden" name="blogId" value="${blogId}">
                <input type="submit" class="btn btn-secondary" value="글삭제">
            </form>
            <form action="/blog/updateform" method="POST">
                <input type="hidden" name="blogId" value="${blogId}">
                <input type="submit" class="btn btn-secondary" value="글수정">
            </form>

        </div>
        <br>

        <!--댓글입력 받아오는 구간-->

        <div class="row">
          댓글
          <div id="replies">

          </div>
        </div>


        <!--댓글 입력 구간-->

        <div class="row">
          <!-- 비동기 form의 경우 목적지로 이동하지 않고 페이지 내에서 처리가 되므로 action을 가지지 않는다.
          제출버튼도 제출 기능을 막고 fetch요청만 넣는다.-->
 
            <div class="col-1">
              <!--name을 기준으로 데이터가 넘어가지는 않을 것이다. 유지보수측면에서 써주겠다.
              id 있는 것이 값을 가지고 오기 편하므로 id 도 써준다.-->
              <input type="text" class="form-control" id="replyWriter" name="replyWriter">
            </div>
            <div class="col-6">
              <input type="text" class="form-control" id="replyContent" name="replyContent">
            </div>
            <div class="col-1">
              <button class="btn btn-primary" id="replySubmit">댓글쓰기</button>
            </div>

        </div>

</div>

<script>
      //글 구성에 필요한 글 번호를 자바스크립트 변수에 저장
      let blogId = "${blog.blogId}";


function getAllReplies(id){
  //jsp 확장자 파일을 js에서 처리를 해줄 때 백틱 리터럴 타입으로 변수를 끼워넣어 처리를 해줄 때 역슬래시 붙여줘야한다. 
  //붙이지 않은 경우 우선순위가 높은(확장자가 jsp이기 때문에) jsp 관점에서 먼저 처리하게 된다.

  let url = `http://localhost:8080/reply/${blogId}/all`;
  console.log(url);

  let str = " "; //받아온 json을 표현 할 html 코드를 저장 할 문자열 str 선언

  fetch(url, {method: 'get'})
    .then((response) => response.json())
    .then((replies) => {
      console.log(replies);

      //방법 1
      // for(reply of replies){
      //   console.log(reply);
      //   str += `<h3>댓글쓴이: \${reply.replyWriter}, 댓글 내용: \${reply.replyContent}</h3>`;
      // }

      //방법 2: .map()을 이용한 간결한 반복문 처리

      //삭제버튼은 다른 태그로 나누어 빼줘야한다. 
      replies.map((reply, i) => { // 첫 파라미터 : 반복대상자료, 두번째 파라미터 : 순번
                    str += 
                    `<h3>\${i}번째 댓글 || 글쓴이: \${reply.replyWriter}, 댓글내용: \${reply.replyContent}
                        
                        <span class="deleteReplyBtn" data-replyId="\${reply.replyId}">
                            [삭제]
                          </span>

                    </h3>`;
                });


      //저장 된 #replies의 innerHTML에 str을 대입해 실제 화면에 출력되게 해주세요.
      const $replies = document.getElementById('replies');
      $replies.innerHTML = str;
    }

    );
};

//blogId를 받아 전체 데이터를 JS 내부로 가져오는 함수선언
//  function getAllReplies(blogId){
//   let url = `http://localhost:8080/reply/${blogId}/all`;
//   fetch(url, {method:'get'}) //get방식으로 위 주소에 요청 넣기
//   .then((res) => res.json()) //응답받은 요소중 json만 뽑기
//   .then(data => { //뽑아온 json으로 처리작업하기
//     console.log(data);
//   });

//함수호출 - 페이지가 켜지자 마자 댓글이 화면이 뿌려짐.
getAllReplies(blogId);


//reply달기

//해당 함수 실행시 비동기 폼에 작성된 글쓴이, 내용으로 댓글입력
//이 코드는 비효율적이다. 나중에 고쳐야 함. 
//댓글을 새로 만들게 아니라 만들어져 있는건 그대로 놔두고 샐로 달린 댓글만 추가로 붙이기
function insertReply(){
  //Post방식 요청
  let url = `http://localhost:8080/reply`;

  const bannedWords = ["나쁜 말"];

  //요소가 다 채워졌는지 확인
  //.trim() 띄어쓰기 만으로 값을 넣으려고 했을 때 막을 수 있다.
  //.includes() 을 써서 금칙어 포함 시킬 수 있다. 
  if(document.getElementById("replyWriter").value.trim() === ""){
    alert("글쓴이를 써주세요");
    return;
  }
  if(document.getElementById("replyContent").value.trim() === ""){
    alert("댓글 본문 입력해주세요");
    return;
  }


  fetch(url, {
    method:'post',
    headers: {
      //json 데이터 요청과 함께 전달, @RequestBody를 입력받는 로직에 추가
      //서버에 어떤 정보가 간다는 걸 통보하는 역할
      "Content-Type": "application/json", 
    },
    body: JSON.stringify({ 
      //실질적으로 요청과 보낼 json정보를 기술한다. 
      replyWriter: document.getElementById("replyWriter").value,
      replyContent: document.getElementById("replyContent").value,
      blogId : "${blogId}",
    }),
    //insert 로직이기 때문에 response에 실제 화면에서 사용할 데이터 전송X
  }).then(() => {
    alert("댓글 작성이 완료되었습니다.");
    document.getElementById("replyWriter").value = " ";
    document.getElementById("replyContent").value =" "; 
    getAllReplies(blogId)
  })
}


//제출버튼에 이벤트 연결하기
$replySubmit = document.getElementById("replySubmit");
//버튼 클릭 시 insertReply 내부 로직 실행
$replySubmit.addEventListener("click", insertReply);


//댓글삭제
//이벤트 객체를 활용해야 이벤트 위임을 구현하기 수월하므로 먼저 html 객체부터 가져온다.
//삭제버튼이 몇개가 나올지 모른다. 하나의 요소에 다이렉트로 이벤트를 걸 수 없을 때 이벤트 위임.
//삭제 버튼을 포함하고 있는 태그 중 가장 가까운 태그 in HTML(id = "replies")에 이벤트위임한다. 
const $replies = document.querySelector('#replies');

$replies.addEventListener("click", function(e){
// $replies.onClick = (e) => {
  //클릭한 요소가 #replies의 자손태그(띄어쓰기/ FYI 자신은 > 로 표현한다.)인 .deleteReplyBtn인지 검사하기
  //이벤트객체.target.matches는 클릭한 요소가 어떤 태그인지 검사해준다.

  // if(e.target.matches('#replies .deleteReplyBtn')){
  //   alert("댓글 삭제버튼을 잘 찾아오셨어요.");
  // } else
  //   alert("댓글 삭제버튼이 아니다.");

  //삭제버튼이 아닌 곳을 클릭하면 
    if(!e.target.matches('#replies .deleteReplyBtn')){
      return;
    }
    //내가 클릭 한 요소에서만 반응하는 지 콘솔찍어확인
    //클릭이벤트 객체 e의 target 속성의 dataset 속성내부에 댓글 번호가 있으므로 확인.
    console.log(e.target.dataset['replyid']);

    
    //삭제하고자 하는 댓글의 id
    const replyId = e.target.dataset['replyid'];

    //예, 아니오로 답할 수 있는 경고창을 띄운다.
    if(confirm("정말로 삭제하시겠어요?")){ 
      //예를 선택하면 true, 아니오를 선택하면 false입니다. 

      let url = `http://localhost:8080/reply/\${replyId}`;
      //실제 delete 로직을 실행하고, 실행하자마자 댓글 다시 불러오기
      fetch(url, {method : 'delete'}).then(()=>{getAllReplies(blogId)});
      console.log("replyId번 요소 삭제");
    } 
    else {
      //삭제 취소
      return;
    }
});


</script>


</body>
</html>