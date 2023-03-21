<template>
    <div class="cn_headerline">
      <h5>거래처 등록</h5>
    </div>
    <div class="board-detail">     
        <div class="insert_div">
            <form method="post" action="">
            <div class="">
                <table>
                    <tr>
                        <td class="cn_col_name" id="col-long">고객번호</td>
                        <td class="col2">
                            <input  v-model="cus_no" name="cus_no" maxlength="14">
                        </td>
                        <td class="cn_col_name" id="col-long">사업자 등록번호</td>
                        <td class="col2">
                            <input v-model="cor_reg_no" class="inputcss" name="cor_reg_no">
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
                    <tr>
                        <td class="cn_col_name">등록 일자</td>                                
                        <td class="col2">
                            <input v-model="reg_date" class="inputcss" name="reg_date">
                        </td>                              
                        <td class="cn_col_name">등록ID</td>                                
                        <td class="col2">
                            <input v-model="reg_id" class="inputcss" name="reg_id">
                        </td>                              
                        <td class="cn_col_name">수정 일자 </td>
                        <td class="col2">
                            <input v-model="mod_date" class="inputcss" name="mod_date">
                            </td>
                        <td class="cn_col_name">수정 ID</td>
                        <td class="col2">
                            <input v-model="mod_id" class="inputcss" name="mod_id">
                        </td>
                    </tr>
                    <tr>
                        <td class="cn_col_name">비고</td>
                    </tr>
                </table>
                <textarea v-model="note" class="input_note" name="note"></textarea> 
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
                    //let apiUrl = this.$serverUrl + "/client"
                    this.requestBody = {
                        "cor_reg_no": this.cor_reg_no,
                        "cus_no": this.cus_no,
                        "name": this.name,
                        "dept": this.dept,
                        "position": this.position,
                        "zip": this.zip,
                        "address1": this.address1,
                        "address2": this.address2,
                        "email": this.email,
                        "semail": this.semail,
                        "tel": this.tel,
                        "phone":this.phone,
                        "engineer":this.engineer,
                        "reg_date": this.reg_date,
                        "reg_id": this.reg_id,
                        "mod_date": this.mod_date,
                        "mod_id": this.mod_id,
                        "note": this.note  
                      }                  
                        //INSERT
                        this.$axios.post(this.$serverUrl + "/client", this.requestBody)
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
      border: 1px solid rgb(185, 185, 185);
      padding: 3px 3px 3px 0px;
      font-size: 13px;
  }
  </style>
  