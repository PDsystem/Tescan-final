<template>
  <div class="cn_menu">
      <button type="button" class="cn_btn" v-on:click="fnSave">수정</button>        
      <button type="button" class="cn_btn" v-on:click="fnDelete">삭제</button>               
  </div>
  <div class="board-detail">
    <form method="patch" action="">
      <div class="">
        <div class="insert">
          <table>
            <tr>
              <td class="cn_col_name" id="col-long">사업자 등록번호</td>
              <td class="col2">
                  <input v-model="corRegNo" class="inputcss" name="cor_reg_no">
                <!-- <input type="text" name="corRegNo" maxlength="14"> -->
              </td>
              <td class="cn_col_name">고객명</td>                                
              <td class="col2">
                <input v-model="customer_name" class="inputcss" name="customer_name">
              </td>
              <!-- <td class="col1">고객번호</td>
                <td class="col2">
                  <input type="text" name="cusNo">
                </td> -->
            </tr>
            <tr>                                
              <td class="cn_col_name">주소</td>
              <td class="col2">
                <input v-model="address1" class="inputcss" name="address1">   
              </td>       
              <td class="col2">
                <input v-model="zip" class="inputcss" name="zip">
              </td>    
              <td class="col2">
                <button class="button cn_btn " @click="postOpen">주소검색</button>    
              </td>                             
            </tr>
            <tr>                            
              <td class="cn_col_name">상세 주소</td>                                
              <td class="col2">
                <input v-model="address2" class="inputcss" name="address2">
              </td>
              <td class="cn_col_name">지점1</td>                                
              <td class="col2">
                <input v-model="region1" class="inputcss" name="region1">
              </td>
            </tr> 
            <tr>
        <!--  <td class="cn_col_name">전화번호</td>                                
              <td class="col2">                              
                <input v-model="tel" class="inputcss" name="tel">
              </td> -->
              <td class="cn_col_name">이메일</td>
              <td class="col2"><input v-model="email" class="inputcss"></td>
              <td class="col2 ft_13">@</td>
              <td class="col2" colspan="2"><input v-model="semail" class="inputcss"></td>
              <td class="col2">
                  <select @change="fnSetEmail($event)" class="dropdown_list">
                      <option value="@naver.com">@naver.com</option>
                      <option value="@hanmail.net">@hanmail.net</option>
                      <option value="@daum.net">@daum.net</option>
                      <option value="@nate.com">@nate.com</option>
                      <option value="@gmail.com">@gmail.com</option>
                      <option value="@hotmail.com">@hotmail.com</option>
                      <option value="" selected>직접입력</option>
                  </select>
                  <input type="button" class='cn_btn' value="이메일 중복확인" onclick="">
              </td>
              <!-- <td class="cn_col_name">지점2</td>                                
              <td class="col2">
                <input v-model="region2" class="inputcss" name="region2">
              </td>                                    -->
            </tr>
            <tr>
              <td class="cn_col_name">홈페이지</td>                                
              <td class="col2">
                <input v-model="web" class="inputcss" name="web">
              </td> 
              <td class="cn_col_name">상위 거래처</td>                                
              <td class="col2">
                <input v-model="high_customer" class="inputcss" name="high_customer">
              </td>                              
            </tr>
            <tr>
              <td class="cn_col_name">비고</td>
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
        console.log(this.requestBody);
        this.$axios.post(this.$serverUrl + '/customer/' + this.requestBody.corRegNo, {
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
      fnSave() {
        this.requestBody = {
          "cor_reg_no": this.corRegNo,
          "customer_name": this.customer_name,
          "typeCode": this.typeCode,
          "email": this.email,
          "region1": this.region1,
          "region2": this.region2,
          "fax":this.fax,
          "web":this.web,
          "address1": this.address1,
          "address2": this.address2,
          "high_customer": this.high_customer,
          "note": this.note,
          "tel": this.tel,
          "note": this.note,
          "semail": this.semail,
          "content_id": this.content_id,
          "content_pw": this.content_pw
        }                  
        //INSERT
        this.$axios.patch(this.$serverUrl + "/customer", this.requestBody)
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
  
        this.$axios.delete(this.$serverUrl + '/customer/' + this.corRegNo, {})
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
.board-detail {
  height: 1000;
}
.col2{
  /* width: 200px; */
    /* background-color: salmon; */
    border: 1px solid rgb(185, 185, 185);
    padding: 3px 3px 3px 0px;
    font-size: 13px;
}
.inputcss{
  border:none;
  border-right: 0px;
  border-top:0px;
  border-left: 0px;
  border-bottom:0px;
}
</style>
