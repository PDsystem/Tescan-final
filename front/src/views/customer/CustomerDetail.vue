<template>
        <div class="div-buttons">
            <!-- <button type="button" class="w3-button" v-on:click="fnSave">등록</button>         -->
            <button type="button" class="w3-button" v-on:click="fnSave">수정</button>        
            <button type="button" class="w3-button" v-on:click="fnDelete">삭제</button>        
            <!-- <button type="button" class="w3-button" v-on:click="fn">초기화</button>         -->
        </div>
        <div class="customer-detail">
          <form method="post" action="">
            <div class="container">
                <div class="insert">
                    <table>
                        <tr>
                            <td class="col1" id="col-long">사업자 등록번호</td>
                            <td class="col2">
                                <input v-model="cor_reg_no" class="inputcss" name="cor_reg_no">
                                <!-- <input type="text" name="corRegNo" maxlength="14"> -->
                              </td>
                              <td class="col1">고객명</td>                                
                              <td class="col2">
                                <input v-model="customer_name" class="inputcss" name="customer_name">
                              </td>
                              <!-- <td class="col1">고객번호</td>
                                <td class="col2">
                                  <input type="text" name="cusNo">
                                </td> -->
                      </tr>
                      <tr>                                
                        <td class="col1">주소</td>
                        <td class="col2">
                          <input v-model="address1" class="inputcss" name="address1">
                          <!-- </td> -->
                          <!-- <td class="col2"> -->
                            <input v-model="zip" class="inputcss" name="zip">
                            <button class="button primary-button" @click="postOpen">주소검색</button>    
                          </td>                               
                          <td class="col1">전화번호</td>                                
                          <td class="col2">                              
                            <input v-model="tel" class="inputcss" name="tel">
                          </td>
                        </tr>
                        <tr>                            
                          <td class="col1">상세 주소</td>                                
                          <td class="col2">
                            <input v-model="address2" class="inputcss" name="address2">
                          </td>
                          <td class="col1">지점1</td>                                
                          <td class="col2">
                            <input v-model="region1" class="inputcss" name="region1">
                          </td>
                        </tr>
                        <tr>
                          <td class="col1">이메일</td>                                
                          <td class="col2">
                            <input v-model="email" class="inputcss" name="email">
                            <span class="a">@</span>
                            <!-- <input type="text" class="inputcss" name="mailslc"> select와 이름이 동일했다-->
                            <input v-model="semail" class="inputcss" name="semail">
                            <select name="semail">
                              <option value="self" selected>직접입력</option>
                              <option value="naver">naver.com</option>
                              <option value="gm">gmail.com</option>
                              <option value="da">daum.com</option>
                              <option value="kor">korea.com</option>
                            </select>
                            <input type="button" class='fnEmail' value="이메일 중복확인" onclick="">
                          </td>
                          <td class="col1">지점2</td>                                
                          <td class="col2">
                            <input v-model="region2" class="inputcss" name="region2">
                          </td>                                   
                        </tr>
                        <tr>
                          <td class="col1">홈페이지</td>                                
                          <td class="col2">
                            <input v-model="web" class="inputcss" name="web">
                          </td> 
                          <td class="col1">상위 거래처</td>                                
                          <td class="col2">
                            <input v-model="high_customer" class="inputcss" name="high_customer">
                          </td>                              
                        </tr>
                        <tr>
                          <td class="col1">비고</td>
                          <td class="col2">
                              <input v-model="note" class="inputcss" name="note">
                            </td>
                        </tr>
                    </table>
                </div> 
            </div>
        </form>
        </div> 
</template>
<script>
  export default {
    data() { //변수생성
      return {
        requestBody: this.$route.query,
        corRegNo: this.$route.query.corRegNo,  
        customer_name: '',
        email: '',
        region1: '',
        region2: '',
        fax: '',
        web: '',
        address1: '',
        address2: '',
        high_customer: '',
        note: '',
        tel: '',
        semail: '',
        content_id: '',
        content_pw: ''
      }
    },
    mounted() {
      this.fnGetView()
    },
    methods: {
      fnGetView() {
        this.$axios.post(this.$serverUrl + '/customer/' + this.cor_reg_no, {
          params: this.requestBody
        }).then((res) => {
          this.customer_name = res.data.customer_name
          this.typeCode = res.data.typeCode
          this.email = res.data.email
          this.region1 = res.data.region1
          this.region2 = res.data.region2
          this.fax = res.data.fax
          this.web = res.data.web
          this.address1 = res.data.address1
          this.address2 = res.data.address2
          this.high_customer = res.data.high_customer
          this.note = res.data.note
          this.tel = res.data.tel
          this.semail = res.data.semail
          this.content_id = res.data.content_id
          this.content_pw = res.data.content_pw
          // this.created_at = res.data.created_at
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      },
      fnList() {
        delete this.requestBody.cor_reg_no
        this.$router.push({
          path: './list',
          query: this.requestBody
        })
      },
      fnUpdate() {
        this.$router.push({
          path: './write',
          query: this.requestBody
        })
      },
      fnDelete() {
        if (!confirm("삭제하시겠습니까?")) return
  
        this.$axios.delete(this.$serverUrl + '/customer/' + this.cor_reg_no, {})
            .then(() => {
              alert('삭제되었습니다.')
              this.fnList();
            }).catch((err) => {
          console.log(err);
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
