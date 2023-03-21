<template>
  <div class="cn_menu">
      <button type="button" class="cn_btn" v-on:click="fnSave">수정</button>        
      <button type="button" class="cn_btn" v-on:click="fnDelete">삭제</button>               
  </div>
  <div class="board-detail">     
  <!-- <div class="insert_div"> -->
    <form method="post" action="">
      <div class="">
        <table>
          <tr>
            <td class="cn_col_name" id="col-long">고객번호</td>
            <td class="col2">
              <input  v-model="cus_no" name="cus_no" readonly>
            </td>
            <td class="cn_col_name" id="col-long">사업자 등록번호</td>
            <td class="col2">
              <input v-model="cor_reg_no" class="inputcss" name="cor_reg_no" readonly>
            </td>
            <td class="cn_col_name">고객명</td>                                
            <td class="col2">
              <input v-model="name" class="inputcss" name="name">
            </td>
            <td class="cn_col_name">담당자</td>                                
            <td class="col2">
              <input v-model="engineer" class="inputcss" name="engineer">
            </td>           
          </tr>
          <tr>
            <td class="cn_col_name">부서</td>                                
            <td class="col2">
              <input v-model="dept" class="inputcss" name="dept">
            </td>
            <td class="cn_col_name">직책</td>                                
            <td class="col2">
              <input v-model="position" class="inputcss" name="position">
            </td>
            <td class="cn_col_name">휴대폰 번호</td>                                
            <td class="col2">
              <input v-model="phone" class="inputcss" name="phone">
            </td> 
            <td class="cn_col_name">전화번호</td>                                
            <td class="col2">                              
              <input v-model="tel" class="inputcss" name="tel">
            </td>                                                      
          </tr>
          <tr>                                
            <td class="cn_col_name">주소</td>
            <td class="col2">
                <input v-model="address1" class="inputcss" name="address1" placeholder="주소">
                <button class="button primary-button" @click="postOpen">주소검색</button>    
            </td>                               
            <td class="cn_col_name">상세 주소</td>                                
            <td class="col2">
                    <input v-model="address2" class="inputcss" name="address2" placeholder="상세주소">
                    <input v-model="zip" class="inputcss" name="zip" placeholder="우편번호">
            </td>
            <td class="cn_col_name">이메일</td>                                
            <td class="col2">
                <input v-model="email" class="inputcss" name="email">
            </td>
            <td class="col2">
                <span class="a">@</span>
            </td>
            <td class="col2">
                <input v-model="semail" class="inputcss" name="semail">
                <select name="semail">
                <option value="self" selected>직접입력</option>
                <option value="naver">naver.com</option>
                <option value="gm">gmail.com</option>
                <option value="da">daum.com</option>
                <option value="kor">korea.com</option>
                </select>
                <!-- <input type="button" class='fnEmail' value="이메일 중복확인" onclick=""> -->
            </td>
          </tr>
          <!-- <tr>
            <td class="cn_col_name">등록 일자</td>                                
            <td class="col2">
                <input v-model="regDate" class="inputcss" name="regDate">
            </td>                              
            <td class="cn_col_name">등록ID</td>                                
            <td class="col2">
                <input v-model="regId" class="inputcss" name="regId">
            </td>                              
            <td class="cn_col_name">수정 일자 </td>
            <td class="col2">
                <input v-model="modDate" class="inputcss" name="modDate">
                </td>
            <td class="cn_col_name">수정 ID</td>
            <td class="col2">
                <input v-model="modId" class="inputcss" name="modId">
            </td>
          </tr> -->
          <tr>
            <td class="cn_col_name">비고</td>
          </tr>
        </table>
        <textarea v-model="note" class="input_note" name="note"></textarea> 
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
        cus_no: '',
        name: '',
        dept: '',
        position: '',
        zip: '',
        address1: '',
        address2: '',
        email: '',
        semail: '',
        tel: '',
        phone: '',
        engineer: '',
        // regDate: '',
        // regId: '',
        // modDate: '',
        // modId: '',
        note: ''
      }
    },
    mounted() {
      this.fnGetView()
    },
    methods: {
      fnGetView() {
        console.log(this.requestBody);
        this.$axios.post(this.$serverUrl + '/client/' + this.requestBody.cus_no, {
          params: this.requestBody
        }).then((res) => {
          this.cus_no = res.data.cus_no
          this.cor_reg_no = res.data.cor_reg_no
          this.name = res.data.name
          this.dept = res.data.dept
          this.position = res.data.position
          this.zip = res.data.zip
          this.address1 = res.data.address1
          this.address2 = res.data.address2
          this.email = res.data.email
          this.semail = res.data.semail
          this.tel = res.data.tel
          this.phone = res.data.phone
          this.engineer = res.data.engineer
          // this.reg_date = res.data.regDate
          // this.reg_id = res.data.regId
          // this.mod_date = res.data.modDate
          // this.mod_id = res.data.modId
          this.note = res.data.note
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      },
      fnList() {
        delete this.requestBody.cus_no
        this.$router.push({
          path: './list',
          query: this.requestBody
        })
      },
      fnSave() {
        this.requestBody = {
          "cor_reg_no": this.cor_reg_no,
          "cus_no":this.cus_no,
          "name":this.name,
          "dept":this.dept,
          "position":this.position,
          "zip":this.zip,
          "address1":this.address1,
          "address2":this.address2,
          "email":this.email,
          "semail":this.semail,
          "tel":this.tel,
          "phone":this.phone,
          "engineer":this.engineer,
          // "reg_date":this.regDate,
          // "reg_id":this.regId,
          // "mod_date":this.modDate,
          // "mod_id":this.modId,
          "note":this.note
        }                  
        //INSERT
        this.$axios.patch(this.$serverUrl + "/client", this.requestBody)
        .then((res) => {
          alert('글이 저장되었습니다.')
          console.log(res);
          // this.fnView(res.data.idx)
          this.fnList()
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })        
      },
      fnDelete() {
        if (!confirm("삭제하시겠습니까?")) return
  
        this.$axios.delete(this.$serverUrl + '/client/' + this.cus_no, {})
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
@import url('../../assets/common.css');
* {
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
}
.board-detail {
  height: 1000;
}
.col2{
    border: 1px solid rgb(185, 185, 185);
    padding: 3px 3px 3px 0px;
    font-size: 13px;
}
</style>
