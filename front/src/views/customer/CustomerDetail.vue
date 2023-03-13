<template>
        <div class="div-buttons">
            <input type="text" v-model="keyword" class="w3-input w3-border" placeholder="검색어를 입력해주세요.">
            <!-- <button type="button" class="w3-button" v-on:click="fnSave">검색</button>         -->
            <!-- <button type="button" class="w3-button" v-on:click="fnSave">등록</button>         -->
            <button type="button" class="w3-button" v-on:click="fnSave">수정</button>        
            <button type="button" class="w3-button" v-on:click="fnDelete">삭제</button>        
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
                                    <input type="text" class="inputcss" name="corRegNo">
                                    <!-- <input type="text" name="corRegNo" maxlength="14"> -->
                                </td>
                                <td class="col1">고객명</td>                                
                                <td class="col2">
                                    <input type="text" class="inputcss" name="customerName">
                                </td>
                                <!-- <td class="col1">고객번호</td>
                                <td class="col2">
                                    <input type="text" name="cusNo">
                                </td> -->
                            </tr>
                            <tr>                                
                                <td class="col1">주소</td>
                                <td class="col2">
                                    <input type="text" class="inputcss" name="address1">
                                <!-- </td> -->
                                <!-- <td class="col2"> -->
                                    <input type="text" name="zip">
                                    <button class="button primary-button" @click="postOpen">주소검색</button>    
                                </td>                               
                                <td class="col1">전화번호</td>                                
                                <td class="col2">
                                    <input type="text" class="inputcss" name="pwdCheck">
                                </td>
                            </tr>
                            <tr>                            
                                <td class="col1">상세 주소</td>                                
                                <td class="col2">
                                    <input type="text" class="inputcss" name="address2">
                                </td>
                                <td class="col1">모바일</td>                                
                                <td class="col2">
                                    <input type="text" class="inputcss" name="pwd">
                                </td>
                            </tr>
                            <tr>
                                <td class="col1">이메일</td>                                
                                <td class="col2">
                                    <input type="email" class="inputcss" name="email">
                                    <span class="a">@</span>
                                    <input type="text" class="inputcss" name="mailslc">
                                    <select name="mailslc">
                                    <option value="self" selected>직접입력</option>
                                    <option value="naver">naver.com</option>
                                    <option value="gm">gmail.com</option>
                                    <option value="da">daum.com</option>
                                    <option value="kor">korea.com</option>
                                    </select>
                                    <input type="button" class='fnEmail' value="이메일 중복확인" onclick="">
                                </td>                                   
                            </tr>
                            <tr>
                                <td class="col1">직급</td>                                
                                <td class="col2">
                                    <input type="text" class="inputcss" name="pwdCheck">
                                </td>                               
                                <td class="col1">부서</td>                                
                                <td class="col2">
                                    <input type="text" name="pwd">
                                </td>
                            </tr>
                            <tr>
                                <td class="col1">담당자</td>                                
                                <td class="col2">
                                    <input type="text" name="pwdCheck">
                                </td>
                                <td class="col1">비고</td>
                                <td class="col2">
                                    <input type="text" name="pwdCheck">
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
        idx: this.$route.query.idx,  
        title: '',
        author: '',
        contents: '',
        created_at: ''
      }
    },
    mounted() {
      this.fnGetView()
    },
    methods: {
      fnGetView() {
        this.$axios.get(this.$serverUrl + '/board/' + this.idx, {
          params: this.requestBody
        }).then((res) => {
          this.title = res.data.title
          this.author = res.data.author
          this.contents = res.data.contents
          this.created_at = res.data.created_at
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      },
      fnList() {
        delete this.requestBody.idx
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
  
        this.$axios.delete(this.$serverUrl + '/board/' + this.idx, {})
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
<style>

</style>