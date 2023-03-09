<template>
    <div class="board-detail">
      <div class="board-contents">
        
        <h6>담당자</h6>
        <input type="text" v-model="emp_no" class="w3-input w3-border" placeholder="담당자를 입력해주세요." style="width:600px;height:40px;font-size:15px">
        
        <div>
        <h6>거래처</h6>
        <input type="text" v-model="cor_reg_no" class="w3-input w3-border" placeholder="거래처를 입력해주세요." style="width:600px;height:40px;font-size:15px">
        <button type="button" class="w3-button w3-round w3-blue-gray" @click="fnWrite">검색</button>
        </div>
        
        <h6>방문목적</h6>
        <input type="text" v-model="visit_purpose" class="w3-input w3-border" placeholder="방문목적을 입력해주세요." v-if="idx === undefined" style="width:600px;height:40px;font-size:15px">
        
        <h6>방문일자</h6>
        <input type="date" v-model="visit_date" class="w3-input w3-border" placeholder="방문일자를 입력해주세요." style="width:600px;height:40px;font-size:15px">
      
      </div>
      <h6>방문내용</h6>
      <div class="board-contents">
        <textarea id="" cols="20" rows="4" v-model="visit_contents" class="w3-input w3-border" placeholder="방문목적을 입력해주세요." style="resize: none; width:600px;">
        </textarea>
      </div>
      
      <h6>비고</h6>
      <div class="board-contents">
        <textarea id="" cols="10" rows="2" v-model="note" class="w3-input w3-border" style="resize: none; width:600px;">
        </textarea>
      </div>

      <div class="common-buttons">
        <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">저장</button>&nbsp;
        <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
      </div>
    </div>
</template>
  
<script>
  export default {
    data() { //변수생성
      return {
        requestBody: this.$route.query,
        idx: this.$route.query.idx,
  
        emp_no: '',
        cor_reg_no: '',
        visit_purpose: '',
        visit_date: '',
        visit_contents: '',
        note: ''
      }
    },
    mounted() {
      this.fnGetView()
    },
    methods: {
      fnGetView() {
        if (this.idx !== undefined) {
          this.$axios.get(this.$serverUrl + '/board/' + this.idx, {
            params: this.requestBody
          }).then((res) => {
            this.emp_no = res.data.emp_no
            this.cor_reg_no = res.data.cor_reg_no
            this.visit_purpose = res.data.visit_purpose
            this.visit_date = res.data.visit_date
            this.visit_contents = res.data.visit_contents
            this.note = res.data.note
          }).catch((err) => {
            console.log(err)
          })
        }
      },
      fnList() {
        delete this.requestBody.idx
        this.$router.push({
          path: './list',
          query: this.requestBody
        })
      },
      fnView(idx) {
        this.requestBody.idx = idx
        this.$router.push({
          path: './detail',
          query: this.requestBody
        })
      },
      fnSave() {
        let apiUrl = this.$serverUrl + '/salse'
        this.form = {
          "emp_no": this.idx,
          "cor_reg_no": this.title,
          "visit_purpose": this.contents,
          "visit_date": this.author,
          "visit_contents": this.author,
          "note": this.author
        }
  
        if (this.idx === undefined) {
          //INSERT
          this.$axios.post(apiUrl, this.form)
          .then((res) => {
            alert('글이 저장되었습니다.')
            this.fnView(res.data.idx)
          }).catch((err) => {
            if (err.message.indexOf('Network Error') > -1) {
              alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
            }
          })
        } else {
          //UPDATE
          this.$axios.patch(apiUrl, this.form)
          .then((res) => {
            alert('글이 저장되었습니다.')
            this.fnView(res.data.idx)
          }).catch((err) => {
            if (err.message.indexOf('Network Error') > -1) {
              alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
            }
          })
        }
      }
    }
  }
</script>
<style scoped>
  
</style>