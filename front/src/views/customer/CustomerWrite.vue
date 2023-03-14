<template>
  <div class="emp_container">     
    <div class="div-buttons">
        <input type="text" v-model="keyword" class="w3-input w3-border" placeholder="검색어를 입력해주세요."> 
        <button type="button" class="w3-button" v-on:click="fnSave">등록</button>                  
        <button type="button" class="w3-button" v-on:click="fn">초기화</button>        
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
                    console.log(this.requestBody);

                  
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

* {
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
  /* border: 1px solid rgb(185, 185, 185); */
}

.emp_container {
  margin: 10px 0px 0px 0px;  /** 헤더 제작 끝나면 마진 수정하기 */
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

.table_br {
  width : 100%;
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
  
}

.emp_cal {
  width: 100%;
  height: 100%;
  border: 0px;
  padding: 0px 5px 0px 5px;
  font-size: 13px;
}

.black_bg {
  width: 100%;
  height: 100%;
  background: rgba(11, 9, 9, 0.5);
  position: fixed;
  padding: 20px;
  z-index: 10000;
}

.white_bg {
  width: 700px;
  height: 500px;
  background-color: white;
  border-radius: 5px;
  padding: 20px;
}

.emp_modal_menu {
  display: flex;
  justify-content: space-between;
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
  padding: 0px 3px 0px 0px;
}

.table_bt {
  border-top: 1px solid rgb(185, 185, 185);
}

.table_bb {
  border-bottom: 1px solid rgb(185, 185, 185);
}

.table_br {
  border-right: 1px solid rgb(185, 185, 185);
}

table{
 width: 75%;
text-align : left;
}



.dropdown_list {
  width: 100%;
  height: 100%;
  font-size: 13px;
  border: 0px;
  padding: 0px 0px 0px 5px;
  color: #000040;
}
</style>


