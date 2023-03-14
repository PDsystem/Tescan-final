<template>
  <div>
      <div class="header">
    <h4>공통코드 마스터관리</h4>
  </div>
  <div class="search">
      <div class="category">분류</div>
      <div class="select">
        <select name="searchCondition" @change="setSelect()">
          <option value="TOTAL">전체</option>
          <option value="CLASSCODE">분류코드</option>
          <option value="CLASSNAME">분류명칭</option>
        </select>
      </div>
      <div class="input">
        <input type="text" name="searchKeyword"  v-model="searchKeyword"/>
      </div>
      <div class="search-btn">
        <button @click="codeSearch()">검색</button>
        <button>초기화</button>
      </div>
    </div>
    <div class="title">
    <h5>코드목록</h5>
  </div>
  <div class="contents">
    <table>
      <thead>
      <tr>
        <th>번호</th>
        <th>분류코드</th>
        <th>분류명칭</th>
        <th>사용여부</th>
        <th>주사용테이블</th>
        <th>사용컬럼명칭</th>
        <th>등록일자</th>
        <th>등록자</th>
      </tr>
      </thead>
      <tbody>
        <tr v-for="(row, idx) in list" :key="idx">
          <td>{{idx+1}}</td>
          <td>{{ row.class_code }}</td>
          <td>{{ row.class_name }}</td>
          <td>{{ row.use_yn }}</td>
          <td>{{ row.main_table }}</td>
          <td>{{ row.main_column }}</td>
          <td>{{ row.regist_time }}</td>
          <td>{{ row.regist_id }}</td>
        
        </tr>
      </tbody>
    </table>
  </div>
  <div class="pagination w3-bar w3-padding-16 w3-small" v-if="paging.total_list_cnt > 0">
    <span class="pg">
    <a href="javascript:;" @click="codeSearch(1)" class="first w3-button w3-bar-item w3-border">&lt;&lt;</a>
    <a href="javascript:;" v-if="paging.start_page > 10" @click="codeSearch(`${paging.start_page-1}`)"
       class="prev w3-button w3-bar-item w3-border">&lt;</a>
    <template v-for=" (n,index) in paginavigation()">
        <template v-if="paging.page==n">
            <strong class="w3-button w3-bar-item w3-border w3-green" :key="index">{{ n }}</strong>
        </template>
        <template v-else>
            <a class="w3-button w3-bar-item w3-border" href="javascript:;" @click="codeSearch(`${n}`)" :key="index">{{ n }}</a>
        </template>
    </template>
    <a href="javascript:;" v-if="paging.total_page_cnt > paging.end_page"
       @click="codeSearch(`${paging.end_page+1}`)" class="next w3-button w3-bar-item w3-border">&gt;</a>
    <a href="javascript:;" @click="codeSearch(`${paging.total_page_cnt}`)" class="last w3-button w3-bar-item w3-border">&gt;&gt;</a>
    </span>
  </div>
  <div class="btn">
    <div class="btn-wrap">
      <button id="add">추가</button>
      <button id="save" @click="fnSave()">저장</button>
      <button id="delete">삭제</button>
    </div>
  </div>
  <div class="update">
    
      <div class="update-top">
        <p>분류코드</p>
        <input id="classcode" name="classCode" type="text" v-model="classCode" />
        <p>분류명칭</p>
        <input id="classname" name="className" type="text" v-model="className" />
        <p>분류코드설명</p>
        <input id="classdesc" name="classDesc" type="text" v-model="classDesc" />
        <p>사용여부</p>
        <select id="useyn" name="useYn"  v-model="useYn">
          <option value="Y">Y</option>
          <option value="N">N</option>
        </select>
      </div>
      <div class="update-bottom">
        <p>주사용테이블</p>
        <input id="maintable" name="mainTable" type="text" v-model="mainTable" />
        <p>사용컬럼명칭</p>
        <input id="maincolumn" name="mainColumn" type="text"   v-model="mainColumn"/>
        <p>수정일자</p>
        <input id="modifytime" type="text" v-model="modifytime" readonly/>
        <p>수정자</p>
        <input id="modifyname" type="text"  v-model="modifyname" readonly/>
      </div>

  </div>
  </div>
</template>

<script>
export default{
data(){
  return{
    requestBody:{},
    list: {}, //리스트 데이터
    paging: {
      end_page: 0,
      page: 0,
      start_page: 0,
      total_list_cnt: 0,
      total_page_cnt: 0,
    }, 
    paginavigation: function () { 
//페이징 처리 for문 커스텀
      let nowPage=this.paging.page;
      let start_page =(parseInt((nowPage-1)/10))*10+1;
       let end_page = start_page+10-1;
       
       end_page=Math.min(this.paging.total_page_cnt,end_page);
       this.paging.start_page=start_page;
       this.paging.end_page=end_page;
      let pageNumber = [] //;
      

      for (let i = start_page; i <= end_page; i++) pageNumber.push(i);
      return pageNumber;
    }
  }
},
mounted(){
  this.codeSearch();
},
methods: {
  
  codeSearch(currentPage){
    console.log(this.requestBody.searchType);
    this.requestBody = { // 데이터 전송
      searchType:this.requestBody.searchType,

      searchKeyword: this.searchKeyword,
      page: currentPage-1,
      size: 10
    }
    this.$axios.post(this.$serverUrl+"/codeMaster/codeMasterList",this.requestBody).then((res) => {
     console.log(res);
     this.list = res.data.content  //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.
       this.paging.total_page_cnt =res.data.total_pages;
       this.paging.total_list_cnt =res.data.total_elements;
       this.paging.page=res.data.pageable.page_number+1;
    
   }).catch(() => {
     window.alert("네트워크 통신 이상");
   });
  },
  setSelect(){
    this.requestBody.searchType=event.target.value;
  },
  fnSave() {
    //let apiUrl = this.$serverUrl + "/codeMaster"
    this.requestBody = {
        "class_code": this.classCode,
        "class_name": this.className,
        "use_yn": this.useYn,
        "main_table": this.mainTable,
        "main_column": this.mainColumn,
        "class_desc": this.classDesc,
        "regist_time":"",
        "regist_id":"",
        "modify_time": this.modifytime,
        "modify_id": this.modifyId
      }
      console.log(this.requestBody);

     
        //INSERT
        this.$axios.post(this.$serverUrl + "/codeMaster", this.requestBody)
        .then((res) => {
          alert('글이 저장되었습니다.')
          console.log(res);
          //this.fnView(res.data.idx)
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      
  }
}
}
</script>
<style scoped>
html,
body,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
code,
img,
dl,
dt,
dd,
ol,
ul,
li,
fieldset,
legend,
caption {
margin: 0;
padding: 0;
border: 0;
}
div,
span,
article,
section,
header,
footer,
p,
ul,
li,
fieldset,
legend,
label,
a,
nav {
box-sizing: border-box;
}
html {
height: 100%;
}
body {
min-height: 100%;
}
html,
body,
div,
span,
applet,
object,
iframe,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
pre,
a,
abbr,
acronym,
address,
big,
cite,
code,
del,
dfn,
em,
img,
ins,
kbd,
q,
s,
samp,
small,
strike,
strong,
sub,
sup,
tt,
var,
b,
u,
i,
center,
dl,
dt,
dd,
ol,
ul,
li,
fieldset,
form,
label,
legend,
table,
caption,
tbody,
tfoot,
thead,
tr,
th,
td,
article,
aside,
canvas,
details,
embed,
figure,
figcaption,
footer,
header,
hgroup,
menu,
nav,
output,
ruby,
section,
summary,
time,
mark,
audio,
video {
margin: 0;
padding: 0;
border: 0;
}
section {
display: block;
}
ol,
ul,
li {
list-style: none;
}
table {
border-collapse: collapse;
border-spacing: 0;
}
select,
input {
height: 20px;
}
button {
height: 30px;
width: 70px;
background-color: #0077db;
border: 0;
color: white;
}
.header {
height: 60px;
background-color: #edf4fa;
padding: 10px 0 0 20px;
}
.header h4 {
font-size: 30px;
}
.search {
height: 40px;
background-color: #edf4fa;

display: flex;
padding-left: 10px;

}
.search .input {
margin-right: 10px;
}
.category {
height: 25px;
width: 120px;
text-align: center;
border: 1px solid rgba(0, 0, 0, 0.3);
margin-top: 6px;
}
.select {
padding-top: 6px;
}
.select select {
height: 26px;
width: 120px;
}
.input {
padding-top: 6px;
}
.input input {
width: 200px;
}
.search-btn {
padding-top: 5px;
}
.title {
height: 40px;
padding: 15px 0 0 10px;
}
.title h5 {
font-size: 15px;
}
.contents {
height: 70vh;
overflow: auto;
}
.contents table {
width: 99vw;
border: 1px solid rgba(0, 0, 0, 0.5);
margin: auto;
}
.contents tr:nth-child(1) {
height: 30px;
}
.contents th {
height: 30px;
background: linear-gradient(#efefeb, #d9d9d1);
border-right: 1px solid lightgray;
}
.contents th:nth-child(1) {
width: 7%;
border-left: 1px solid lightgray;
}
.contents th:nth-child(2) {
width: 12%;
}
.contents th:nth-child(3) {
width: 12%;
}
.contents th:nth-child(4) {
width: 12%;
}
.contents th:nth-child(5) {
width: 18%;
}
.contents th:nth-child(6) {
width: 18%;
}
.contents th:nth-child(7) {
width: 12%;
}
.contents th:nth-child(8) {
width: 9%;
}
.contents td {
text-align: center;
border: 1px solid lightgray;
padding: 3px 0;
}
.contents td:nth-child(3),
.contents td:nth-child(5),
.contents td:nth-child(6) {
text-align: start;
padding-left: 10px;
}
.btn {
height: 60px;
}
.btn-wrap {
padding: 20px 10px 0 0;
float: right;
}
.update {
height: 80px;
width: 99vw;
background-color:#edf4fa;
text-align: center;
margin: auto;
}
.update p {
height: 26px;
width: 120px;
border: 1px solid rgba(0, 0, 0, 0.5);
}
.update input {
width: 120px;
margin-right: 10px;
}
.update-top,
.update-bottom {
height: 50%;
border: 1px solid lightgray;
display: flex;
justify-content: center;
padding-top: 13px;
}
.update-top {
border-bottom: 0;
padding: 0.1px 0.2px 0.2px 0.3px;
}
.update-bottom {
padding: 0 0 13px 0;
border-top: 0;
}
.update-top select {
height: 26px;
width: 128px;
}
.update-bottom input:last-child {
margin-right: 0;
}
</style>