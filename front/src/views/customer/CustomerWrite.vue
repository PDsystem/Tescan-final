<template>
  <div class="cn_headerline">
    <h5>거래처 등록</h5>
  </div>
    <div class="board-detail">     
      <div class="">
        <form method="post" action="">
          <div class="">
            <div class="insert">
              <table>
                <tr>
                  <td class="cn_col_name" id="col-long">사업자 등록번호</td>
                  <td class="col2">
                      <input v-model="cor_reg_no" class="inputcss" name="cor_reg_no">
                <!-- <input type="text" name="corRegNo" maxlength="14"> -->
                  </td>
                  <td class="cn_col_name">고객명</td>                                
                  <td class="col2">
                    <input v-model="customer_name" class="inputcss" name="customer_name">
                  </td>
                  <!-- <td class="cn_col_name">고객번호</td>
                    <td class="col2">
                      <input type="text" name="cusNo">
                    </td> -->
                </tr>
                <tr>                                
                  <td class="cn_col_name">주소</td>
                  <td class="col2">
                    <input v-model="address1" class="inputcss" name="address1">
                    <!-- </td> -->
                    <!-- <td class="col2"> -->
                    <input v-model="zip" class="inputcss" name="zip">
                    <button class="button primary-button" @click="postOpen">주소검색</button>    
                  </td>                               
                  <td class="cn_col_name">전화번호</td>                                
                  <td class="col2">                              
                    <input v-model="tel" class="inputcss" name="tel">
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
                  <td class="cn_col_name">이메일</td>                                
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
                  <td class="cn_col_name">지점2</td>                                
                  <td class="col2">
                    <input v-model="region2" class="inputcss" name="region2">
                  </td>                                   
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
      <div class="div-buttons">
        <button type="button" class="cn_btn" v-on:click="fnSave">등록</button>                  
        <!-- <button type="button" class="cn_btn" v-on:click="fn">초기화</button>         -->
      </div>
    </div>
</template>

<script>
export default {

  props:['removeValue'],
    data(){
        return{
            checkedValues:[],
            isAllChecked:true
        }
    },
    methods: {
        clickFunc(event) {
          for(let i=0; i<this.checkedValues.length;i++){
            if(this.checkedValues[i] !== event.target.value){
            console.log(this.checkedValues[i])
            this.checkedValues.splice(i,1);
            }
          }
        },
        allCheck(){
            if(!this.isAllChecked){
                for(let i=1; i<4; i++){
                    this.checkedValues.push(i)
                }
               this.isAllChecked = true;
            }else{
                 this.checkedValues=[]

                this.isAllChecked  = false;
            }
             this.$emit('checkClick',this.checkedValues)
        },
        fnSave() {
                  //let apiUrl = this.$serverUrl + "/customer"
                  this.requestBody = {
                      "cor_reg_no": this.cor_reg_no,
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
                      this.$axios.post(this.$serverUrl + "/customer", this.requestBody)
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
@import url('../../assets/common.css');
* {
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
    /* border: 1px solid rgb(185, 185, 185); */
}
.board-detail {
  height: 1100;
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
