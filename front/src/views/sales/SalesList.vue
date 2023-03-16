<template>
    <div class="visit-list">
      <div class="visit_header">
            <p style="margin-left: 10px;">영업방문</p>
        </div>
      <!--검색-->
            <div class="searchBar">
                <div class="menu2">
                    <!--담당자, 거래처검색-->
                    <div class="empSearch colText">담당자검색</div>
                    <div style="margin-right: 10px;"><input type="text" name="empno" v-model="searchKeyword"></div>
                    <button class="empSearch_btn search_btn" @click="fnSalesList">검색</button>
                    <div class="corSearch colText">거래처검색</div>
                    <div style="margin-right: 10px;"><input type="text"></div>
                    <button class="corSearch_btn search_btn" @click="isModalOpened = true">검색</button>

                    <!-- 검색 버튼
                    <button class="search_btn" @click="search()">조회</button> -->
                </div>
                <br>
                <div class="menu2">
                    <!--날짜검색-->
                    <div class="startDate colText">시작일</div>
                    <div style="margin-right: 10px;"><input type="date"></div>
                    <p>~</p>
                    <div class="endDate colText">종료일</div>
                    <div style="margin-right: 10px;"><input type="date"></div>
                    <button class="dateSearch_btn search_btn" @click="dateSearch()">검색</button>
                    <!--crud 버튼-->
                    <!-- <button class="search_btn" id="add" @click="fnAdd()">추가</button> -->
                    <button class="search_btn" id="save" @click="fnSave()">저장</button>
                    <button class="search_btn" id="delete" @click="fnDelete()">삭제</button>
                </div>
            </div>
            <br>
            <table>
            <tr>
                <td class="colText">No</td>
                <td class="colData"><input type="text" v-model="visit_no" style="width:100%"></td>
                <td class="colText">사원명(사원번호)</td>
                <td class="colData"><input type="text" v-model="empno" style="width:100%"></td>
                <td class="colText">사업자등록번호</td>
                <td class="colData"><input type="text" v-model="cor_reg_no" style="width:100%"></td>
            </tr>
            <tr>
              <td class="colText">방문일자</td>
              <td class="colData" colspan="6"><input type="date" v-model="visit_date" style="width:100%"></td>
            </tr>
            <tr>
                <td class="colText">방문목적</td>
                <td class="colData" colspan="6"><input type="text" v-model="visit_purpose" style="width:100%"></td>
            </tr>
            <tr>
                <td class="colText">방문내용</td>
                <td class="colData" colspan="6"><input type="text" v-model="visit_contents" style="width:100%"></td>
            </tr>
            <tr>
                <td class="colText">비고</td>
                <td class="colData" colspan="6"><input type="text" v-model="note" style="width:100%"></td>
            </tr>
        </table>
        <br>

      <div class="contents">
      <table>
        <thead>
                <tr>
                    <th class="th">No</th>
                    <th class="th">담당자</th>
                    <th class="th">거래처</th>
                    <th class="th">방문목적</th>
                    <th class="th">방문내역</th>
                    <th class="th">방문일자</th>
                    <th class="th">비고</th>
                </tr> 
            </thead>
        <tbody>
        <tr v-for="(f,visit_no) in list" :key="visit_no" @click="fn_bind(f)">
          <td>{{ f.visit_no }}</td>
          <td>{{ f.empno }}</td>
          <td>{{ f.cor_reg_no }}</td>
          <td>{{ f.visit_purpose }}</td>
          <td>{{ f.visit_contents }}</td>
          <td>{{ f.visit_date }}</td>
          <td>{{ f.note }}</td>
        </tr>
        </tbody>
      </table>
      </div>      
      <br>

      <div class="pagination w3-bar w3-padding-16 w3-small" v-if="paging.total_list_cnt > 0">
        <span class="pg">
        <a href="javascript:;" @click="fnPage(1)" class="first w3-button w3-bar-item w3-border">&lt;&lt;</a>
        <a href="javascript:;" v-if="paging.start_page > 10" @click="fnPage(`${paging.start_page-1}`)"
           class="prev w3-button w3-bar-item w3-border">&lt;</a>
        <template v-for=" (n,index) in paginavigation()">
            <template v-if="paging.page==n">
                <strong class="w3-button w3-bar-item w3-border w3-green" :key="index">{{ n }}</strong>
            </template>
            <template v-else>
                <a class="w3-button w3-bar-item w3-border" href="javascript:;" @click="fnPage(`${n}`)" :key="index">{{ n }}</a>
            </template>
        </template>
        <a href="javascript:;" v-if="paging.total_page_cnt > paging.end_page"
           @click="fnPage(`${paging.end_page+1}`)" class="next w3-button w3-bar-item w3-border">&gt;</a>
        <a href="javascript:;" @click="fnPage(`${paging.total_page_cnt}`)" class="last w3-button w3-bar-item w3-border">&gt;&gt;</a>
        </span>
      </div>
    </div>
  </template>
  
  <script>
    export default {

      data() { //변수생성
        return {
          visit_no:'',
          empno:'',
          cor_reg_no:'',
          visit_purpose:'',
          visit_contents:'',
          visit_date:'',
          note:'',
          detailBody:{idx:0},
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
      mounted() {
        this.fnSalesList();
      // this.paginavigation();
      },
      methods: {
        
        fnSalesList(currentPage) {
          
          this.requestBody = { // 데이터 전송
            searchKeyword: this.searchKeyword,
            page: currentPage-1,
            size: 10
          }
          this.$axios.post(this.$serverUrl + "/sales/list",
            this.requestBody
          
          ).then((res) => {    
          console.log(res)
        
            this.list = res.data.content  //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.
            this.paging.total_page_cnt =res.data.total_pages;
            this.paging.total_list_cnt =res.data.total_elements;
            this.paging.page=res.data.pageable.page_number+1;
          }).catch((err) => {
            if (err.message.indexOf('Network Error') > -1) {
              alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
            }
          })
        },
        fnView(idx) {
          this.detailBody.idx = idx
          this.$router.push({
            path: './detail',
            query: this.detailBody
          })
        },
        fnWrite() {
          this.$router.push({
            path: './write'
          })
        },

         fnPage(n) {
          
           if (this.page !== n) {
             this.page = n
             this.fnSalesList(n)
           }
         },
         //저장버튼 클릭시
        fnSave() {
            this.requestBody = {
                "visit_no": this.visit_no,
                "empno": this.empno,
                "visit_date": "",
                "cor_reg_no": this.cor_reg_no,
                "visit_purpose": this.visit_purpose,
                "visit_contents": this.visit_contents,
                "note": this.note
            }
            console.log(222222222222222222222222222222222222222222);
            console.log(this.requestBody);

                //INSERT
                this.$axios.post(this.$serverUrl + "/sales", this.requestBody)
                .then((res) => {
                alert('글이 저장되었습니다.')
                console.log(res);
                //this.fnView(res.data.idx)
                }).catch((err) => {
                if (err.message.indexOf('Network Error') > -1) {
                    alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
                }
            })
        },

        fnDelete() {
        if (!confirm("삭제하시겠습니까?")) return
      this.idx=this.visit_no;
      alert(this.idx);
        this.$axios.delete(this.$serverUrl + '/salesDelete/' + this.idx)
            .then(() => {
              alert('삭제되었습니다.')
             
            }).catch((err) => {
          console.log(err);
        })
      },

      fn_bind(f){

      this.visit_no=f.visit_no
      this.empno=f.empno
      this.cor_reg_no=f.cor_reg_no
      this.visit_purpose=f.visit_purpose
      this.visit_contents=f.visit_contents
      this.visit_date=f.visit_date
      this.note=f.note
       // alert(object);
       // console.log(bind);
      }
      }
  }
  </script>

  <style scoped>
* {
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
    /* border: 1px solid rgb(185, 185, 185); */
}
.visit_header {
    width: 100%;
    height: 50px;
    font-size: xx-large;
    font-weight: bold;
    display: flex;
    justify-content: flex-start;
    border-bottom: 6px solid #0078b3;
}
.searchBar {
    padding-top: 20px;
    justify-content: space-between;
    align-items: center;
    height: 100px;
    background-color: #f0f0f0;
}
.colText {
    padding: 2px 20px 2px 20px;
    margin: 0px 10px 0px 10px;
    align-items: center;
    justify-content: center;
    background-color: #f4edfa;
    border: 1px solid rgb(185, 185, 185);
    font-size: 13px;
    font-weight: 900;
    color: #000040;
}
.colData {
    width: 180px;
    /* background-color: salmon; */
    border: 1px solid rgb(185, 185, 185);
    padding: 0px 3px 0px 0px;
}
.dropList {
    width: 100%;
    height: 100%;
    font-size: 13px;
    border: 0px;
    padding: 0px 0px 0px 5px;
    color: #000040;
}
.calendar {
    width: 100%;
    height: 100%;
    border: 0px;
    padding: 0px 5px 0px 5px;
    font-size: 13px;
}
.search_btn {
    padding: 2px 20px 2px 20px;
    font-size: 13px;
    font-weight: 900;
    color: white;
    background-color: #0078b3;
    border: 0px;
    border-radius: 4px;
    border: 1px solid rgb(185, 185, 185);
}
.menu2 {
    display: flex;
}
.menu_right {
    display: flex;
}
.btnSort {
    margin-right: 5px;
}

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
  height: 40vh;
  overflow: auto;
}
.contents table {
  width: 80vw;
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
  width: 5%;
  border-left: 1px solid lightgray;
}
.contents th:nth-child(2) {
  width: 8%;
}
.contents th:nth-child(3) {
  width: 8%;
}
.contents th:nth-child(4) {
  width: 10%;
}
.contents th:nth-child(5) {
  width: 20%;
}
.contents th:nth-child(6) {
  width: 10%;
}
.contents th:nth-child(7) {
  width: 10%;
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