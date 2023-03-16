<template>
        <div class="div-buttons">
            <!-- <button type="button" class="w3-button" v-on:click="fnSave">등록</button>         -->
            <button type="button" class="w3-button" v-on:click="fnSave">수정</button>        
            <button type="button" class="w3-button" v-on:click="fnDelete">삭제</button>        
            <!-- <button type="button" class="w3-button" v-on:click="fn">초기화</button>         -->
        </div>
        <div class="customer-detail">
          <form method="patch" action="">
            <div class="container">
                <div class="insert">
                    <table>
                        <tr>
                            <td class="col1" id="col-long">사업자 등록번호</td>
                            <td class="col2">
                                <input v-model="corRegNo" class="inputcss" name="cor_reg_no">
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
                  //let apiUrl = this.$serverUrl + "/customer"
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
                      alert(this.requestBody)
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

* {
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
    /* border: 1px solid rgb(185, 185, 185); */
}

.ft_13 {
    font-size: 13px;
}

.mh_10 {
    margin: 0px 10px 0px 10px;
}

.mh_20 {
    margin: 0px 20px 0px 20px;
}

.mv_20 {
    margin: 20px 0px 20px 0px;
}

.ml_10 {
    margin: 0px 0px 0px 10px;
}

.mr_10 {
    margin: 0px 10px 0px 0px;
}

.mr_5 {
    margin: 0px 5px 0px 0px;
}

.mt_5 {
    margin: 5px 0px 0px 0px;
}

.mt_15 {
    margin: 15px 0px 0px 0px;
}

table {
    /* width: 1160px;
    height: 500px; 이제 필요 없음*/

}

.emp_container {
    margin: 10px 0px 0px 0px;
    /** 헤더 제작 끝나면 마진 수정하기 */
}

div {
    box-sizing: border-box;
}

.emp_header {
    width: 100%;
    height: 50px;
    font-size: xx-large;
    font-weight: bold;
    display: flex;
    justify-content: flex-start;
    border-bottom: 6px solid #0078b3;
}

.emp_search_keyword {
    display: flex;
    padding: 0px 20px 0px 20px;
    margin: 0px 10px 0px 10px;
    align-items: center;
    justify-content: center;
    background-color: #f4edfa;
    border: 1px solid rgb(185, 185, 185);
    font-size: 13px;
    font-weight: 900;
    color: #000040;
}

.emp_btn {
    padding: 2px 20px 2px 20px;
    font-size: 13px;
    font-weight: 900;
    color: white;
    background-color: #0078b3;
    border: 0px;
    border-radius: 4px;
    border: 1px solid rgb(185, 185, 185);
    cursor: pointer;
}

.emp_cal {
    width: 100%;
    height: 100%;
    border: 0px;
    padding: 0px 5px 0px 5px;
    font-size: 13px;
}

.emp_data {
    width: 100%;
    height: 100%;
    border: none;
    padding-left: 5px;
    font-size: 13px;
}

.black_bg {
    width: 100%;
    height: 100%;
    background: rgba(11, 9, 9, 0.5);
    /* position: fixed; */
    position: absolute;
    padding: 20px;
    z-index: 10000;
}

.white_bg {
    width: 700px;
    height: 600px;
    background-color: white;
    border-radius: 5px;
    padding: 20px;
}

.emp_modal_menu {
    display: flex;
    justify-content: space-between;
    height: 40px;
    background-color: #f0f0f0;
    padding: 0px 10px 0px 10px;
    align-items: center;
    border: 1px solid rgb(185, 185, 185);
}

.emp_body {
    width: 1160px;
}

.menu {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 40px;
    background-color: #f0f0f0;
    margin-bottom: 20px;
}

.menu_left {
    display: flex;
}

.menu_right {
    display: flex;
}

.emp_photo {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 170px;
    height: 200px;
    border: 1px solid rgb(185, 185, 185);
    font-size: 15px;
}

.col_name {
    width: 120px;
    padding: 5px 0px 5px 0px;
    background-color: #f0f0f0;
    font-size: 13px;
    border: 1px solid rgb(185, 185, 185);
    border-radius: 3px;
    color: #000040;
}

.col_data {
    width: 180px;
    /* background-color: salmon; */
    border: 1px solid rgb(185, 185, 185);
    padding: 3px 3px 3px 0px;
    font-size: 13px;
}

.table_ba {
    border: 1px solid rgb(185, 185, 185);
}

.dropdown_list {
    width: 100%;
    height: 100%;
    font-size: 13px;
    border: 0px;
    padding: 0px 0px 0px 5px;
    color: #000040;
}

.modal_dropdown_list {
    width: 60px;
    height: 30px;
    font-size: 13px;
    color: #000040;
}

.modal_table {
    width: 100%;
    border-top: 3px solid rgb(156, 139, 108);
}

.modal_menu {
    display: flex;
    justify-content: space-evenly;
}

.modal_paging {
    text-decoration: none;
    color: #0078b3;
    font-weight: 600;
    margin: 0px 5px 0px 5px;
}
</style>
<!-- CSS 끝 -->
