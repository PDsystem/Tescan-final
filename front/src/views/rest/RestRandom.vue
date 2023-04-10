<template>
  <div>
    <form method="post" action="">
      <div class="cn_headerline">
        <h5>오늘의 메뉴 : </h5>
        <input v-model="rest_name" class="inputcss fontStyle" name="rest_name">
      </div>
      <div class="body_text">
        <tr>
          <td class="col_name">번호</td>
          <td class="col_data">
            <input v-model="rest_no" class="inputcss" name="rest_no" maxlength='5'>
          </td>
          <td class="col_name">상호명</td>
          <td class="col_data">
            <input v-model="rest_name" class="inputcss"  name="rest_name" maxlength='20'>
          </td>
          <td class="col_name">구분</td>
          <td class="col_data">
            <select class="dropdown_list" @change="fnDisclosure()" v-model="division_no" >
              <option>선택</option>
              <option value="100">한식</option>
              <option value="200">일식</option>
              <option value="300">중식</option>
              <option value="400">패스트푸드</option>
              <option value="500">분식</option>
              <option value="600">기타</option>
            </select>
          </td>
          <td class="col_name">평균 가격</td>   
          <td>
            <input v-model="price" class="inputcss"  name="price">
          </td>
        </tr>
        <tr>
          <td class="col_name">주소</td>
          <td class="col_data">
            <input v-model="address1" class="inputcss"  name="address1">
          </td>
          <td class="col_name">상세주소</td>
          <td class="col_data">
            <input v-model="address2" class="inputcss"  name="address2">
          </td>
          <td class="col_name">우편번호</td>
          <td class="col_data">
              <input v-model="zip" class="inputcss" name="zip">
          </td>
        </tr>
        <tr>
          <td class="col_name">비고</td>&nbsp;
        </tr>
      </div>
      <div>
        <textarea id="note_area" cols="265" rows="10" v-model="note" maxlength='2000'>
        </textarea>
      </div>
    </form>
    <div id="map" style="width:500px;height:400px;"></div>
    <div class="cn_menu">              
      <button type="button" class="cn_btn common-buttons" v-on:click="fnList()">돌아가기</button>     
      <button type="button" class="cn_btn common-buttons" v-on:click="fnWrite">등록</button>                          
      <button type="button" class="cn_btn common-buttons" v-on:click="fnDelete">삭제</button>                                     
    </div>
    <br><br><br>
  </div> 
</template>
<script>
  export default {
    data() { //변수생성
      return {
        requestBody: this.$route.query,
        rest_no: this.$route.query.rest_no,  
        rest_name: '',
        address1: '',
        note: ''
      }
    },
    mounted() {
      this.fnGetView()
    },
    methods: {
      fnGetView() {
        console.log(this.requestBody);
        this.$axios.post(this.$serverUrl + '/restaurant/random', {
          params: this.requestBody
        }).then((res) => {
          this.rest_no=res.data.rest_no;
            this.rest_name=res.data.rest_name;
            this.division_no=res.data.division_no;
            this.price=res.data.price;
            this.address1=res.data.address1;
            this.address2=res.data.address2;
            this.zip=res.data.zip;
            this.note=res.data.note;
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      },
      fnDelete() {
        if (!confirm("삭제하시겠습니까?")) return
  
        this.$axios.delete(this.$serverUrl + '/restaurant/' + this.rest_no, {})
          .then(() => {
            alert('삭제되었습니다.')
            this.fnList();
          }).catch((err) => {
            console.log(err);
        })
      },
      fnList(){
        this.$router.push({
            path: './list'
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
.inputcss{
  border:none;
  border-right: 0px;
  border-top:0px;
  border-left: 0px;
  border-bottom:0px;
}
.fontStyle{
  font-size: 50px;
  color:#0078b3;
}
.cn_menu{
    float: right;
}
#note_area{
  resize: none;
}
</style>