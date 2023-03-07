<template>
    <div>
        <div class="header">
      <h4>공통코드 마스터관리</h4>
    </div>
    <div class="search">
        <div class="category">분류</div>
        <div class="select">
          <select name="searchCondition">
            <option value="TOTAL">전체</option>
            <option value="CLASS_CODE">분류코드</option>
            <option value="CLASS_NAME">분류명칭</option>
          </select>
        </div>
        <div class="input">
          <input type="text" name="searchKeyword" />
        </div>
        <div class="search-btn">
          <button type="submit">검색</button>
          <button>초기화</button>
        </div>
      </div>
      <div class="title">
      <h5>코드목록</h5>
    </div>
    <div class="contents">
      <table>
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
       
          <tr>
            <td>번호1</td>
            <td>분류코드1</td>
            <td>분류명칭1</td>
            <td>사용여부1</td>
            <td>주사용테이블1</td>
            <td>사용컬럼명칭1</td>
            <td>등록일자1</td>
            <td>등록자1</td>
          </tr>
        
      </table>
    </div>

    <div class="btn">
      <div class="btn-wrap">
        <button id="add">추가</button>
        <button id="save">저장</button>
        <button id="delete">삭제</button>
      </div>
    </div>
    <div class="update">
      
        <div class="update-top">
          <p>분류코드</p>
          <input id="classcode" name="classCode" type="text" />
          <p>분류명칭</p>
          <input id="classname" name="className" type="text" />
          <p>분류코드설명</p>
          <input id="classdesc" name="classDesc" type="text" />
          <p>사용여부</p>
          <select id="useyn" name="useYn">
            <option value="Y">Y</option>
            <option value="N">N</option>
          </select>
        </div>
        <div class="update-bottom">
          <p>주사용테이블</p>
          <input id="maintable" name="mainTable" type="text" />
          <p>사용컬럼명칭</p>
          <input id="maincolumn" name="mainColumn" type="text" />
          <p>수정일자</p>
          <input id="modifytime" type="text" />
          <p>수정자</p>
          <input id="modifyname" type="text" />
        </div>
 
    </div>
    </div>
</template>

<script>
export default{

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
  background-color: #f0f0f0;
  padding: 10px 0 0 20px;
}
.header h4 {
  font-size: 30px;
}
.search {
  height: 40px;
  background-color: #f0f0f0;
  border: 1px solid lightgray;
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
  background-color: #f0f0f0;
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