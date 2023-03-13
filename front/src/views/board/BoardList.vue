<template>
  <div class="board-list">
    제목: <input type = "text"  name="title" v-model="searchKeyword">
    <button class="btn btn-warning" @click="fnGetList()"><i class="bi bi-search"></i>조회</button>
    <div class="common-buttons">
      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnWrite">등록</button>
    </div>
    <table class="w3-table-all">
      <thead>
      <tr>
        <th>No</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일시</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(row, idx) in list" :key="idx">
        <td >{{ row.idx }}</td>
        <td><a v-on:click="fnView(`${row.idx}`)" >{{ row.title }}</a></td>
        <td>{{ row.author }}</td>
        <td>{{ row.created_at }}</td>
      </tr>
      </tbody>
    </table>
    <div class="paging pagination w3-bar w3-padding-16 w3-small" v-if="paging.total_list_cnt > 0">
      <span class="pg">
      <a href="javascript:;" @click="fnGetList(1)" class="paging first w3-button w3-bar-item w3-border">&lt;&lt;</a>
      <a href="javascript:;" v-if="paging.start_page > 10" @click="fnGetList(`${paging.start_page-1}`)"
         class="paging prev w3-button w3-bar-item w3-border">&lt;</a>
      <template v-for=" (n,index) in paginavigation()">
          <template v-if="paging.page==n">
              <strong class="paging w3-button w3-bar-item w3-border w3-green" :key="index">{{ n }}</strong>
          </template>
          <template v-else>
              <a class="paging w3-button w3-bar-item w3-border" href="javascript:;" @click="fnGetList(`${n}`)" :key="index">{{ n }}</a>
          </template>
      </template>
      <a href="javascript:;" v-if="paging.total_page_cnt > paging.end_page"
         @click="fnGetList(`${paging.end_page+1}`)" class="paging next w3-button w3-bar-item w3-border">&gt;</a>
      <a href="javascript:;" @click="fnGetList(`${paging.total_page_cnt}`)" class="paging last w3-button w3-bar-item w3-border">&gt;&gt;</a>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  
  data() { //변수생성
    return {
      detailBody:{idx:0},
       list: {}, //리스트 데이터
      paging: {
        end_page: 0,
        page: 0,
        start_page: 0,
        total_list_cnt: 0,
        total_page_cnt: 0,
      }, 
      paginavigation: function () { 
  //페이징 처리 for문 커스텀
        let nowPage=this.paging.page;
        let start_page =(parseInt((nowPage-1)/10))*10+1;
         let end_page = start_page+10-1;
         
         end_page=Math.min(this.paging.total_page_cnt,end_page);
         this.paging.start_page=start_page;
         this.paging.end_page=end_page;
        let pageNumber = [] //;
        
  
        for (let i = start_page; i <= end_page; i++) pageNumber.push(i);
        return pageNumber;
      }
    }
  },
  mounted() {
    this.fnGetList();
   // this.paginavigation();
  },
  methods: {
     
    fnGetList(currentPage) {
      
      this.requestBody = { // 데이터 전송
        searchKeyword: this.searchKeyword,
        page: currentPage-1,
        size: 10
      }
      this.$axios.post(this.$serverUrl + "/board/list",
        this.requestBody
       
      ).then((res) => {    
       console.log(res)
    
        this.list = res.data.content  //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.
         this.paging.total_page_cnt =res.data.total_pages;
         this.paging.total_list_cnt =res.data.total_elements;
         this.paging.page=res.data.pageable.page_number+1;
      }).catch((err) => {
        if (err.message.indexOf('Network Error') > -1) {
          alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
        }
      })
    },
    fnView(idx) {
      this.detailBody.idx = idx
      this.$router.push({
        path: './detail',
        query: this.detailBody
      })
    },
    fnWrite() {
      this.$router.push({
        path: './write'
      })
    }
    // fnPage(n) {
      
    //   if (this.page !== n) {
    //     this.page = n
    //     this.fnGetList(n)
    //   }
    // }
  }
}
</script>

<style scoped>
.paging {
    text-decoration: none;
    color: #0078b3;
    font-weight: 600;
    margin: 0px 5px 0px 5px;
}
</style>