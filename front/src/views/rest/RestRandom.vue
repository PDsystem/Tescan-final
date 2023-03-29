<template>
    <div>
        <form method="post" action="">
            <input v-model="rest_name" class="inputcss" name="rest_name">
        </form>
    </div> 
    <div id="map" style="width:500px;height:400px;"></div>
    <div class="cn_menu">              
            <button type="button" class="cn_btn common-buttons" v-on:click="fnList()">돌아가기</button>                
        </div>
</template>
<script>
  export default {
    data() { //변수생성
      return {
        requestBody: this.$route.query,
        rest_no: this.$route.query.rest_no,  
        rest_name: ''
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
          this.rest_no = res.data.rest_no
          this.rest_name = res.data.rest_name
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
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
  font-size: 50px;
}
</style>