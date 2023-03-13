<template>
    <div class="visit-list">
      <div class="visit_header">
            <p style="margin-left: 10px;">영업방문</p>
        </div>
      <!--검색-->
            <div class="searchBar">
                <div class="menu">
                    <!--담당자, 거래처검색-->
                    <div class="empSearch colText">담당자검색</div>
                    <div style="margin-right: 10px;"><input type="text"></div>
                    <button class="empSearch_btn search_btn" @click="isModalOpened = true">검색</button>
                    <div class="corSearch colText">거래처검색</div>
                    <div style="margin-right: 10px;"><input type="text"></div>
                    <button class="corSearch_btn search_btn" @click="isModalOpened = true">검색</button>

                    <!--검색 버튼-->
                    <button class="search_btn btnSort menu_right" @click="search()">조회</button>
                    <button class="search_btn btnSort menu_right" @click="reset()">초기화</button>
                </div>
                <br>
                <div class="menu">
                    <!--날짜검색-->
                    <div class="startDate colText">시작일</div>
                    <div style="margin-right: 10px;"><input type="date"></div>
                    <p>~</p>
                    <div class="endDate colText">종료일</div>
                    <div style="margin-right: 10px;"><input type="date"></div>
                    <button class="dateSearch_btn search_btn" @click="dateSearch()">검색</button>
                    <!--crud 버튼-->
                    <button class="search_btn btnSort menu_right" @click="add()">추가</button>
                    <button class="search_btn btnSort menu_right" @click="save()">저장</button>
                    <button class="search_btn btnSort menu_right" @click="del()">삭제</button>
                </div>
            </div>
            <br>
            
      <table class="table">
        <thead class="thead">
                <tr class="tr">
                    <th class="th">담당자</th>&nbsp;
                    <th class="th">거래처</th>&nbsp;
                    <th class="th">방문목적</th>&nbsp;
                    <th class="th">방문내역</th>&nbsp;
                    <th class="th">방문날짜</th>&nbsp;
                    <th class="th">비고</th>
                </tr> 
            </thead>
        <tbody class="tbody">
        <tr v-for="(f,visit_no) in list" :key="visit_no">
          <td>{{ f.emp_no }}</td>&nbsp;
          <td>{{ f.cor_reg_no }}</td>&nbsp;
          <td><a v-on:click="fnView(`${row.visit_no}`)">{{ f.visit_purpose }}</a></td>&nbsp;
          <td>{{ f.visit_contents }}</td>&nbsp;
          <td>{{ f.visit_date }}</td>&nbsp;
          <td>{{ f.note }}</td>&nbsp;
        </tr>
        </tbody>
      </table>

      <div class="pagination w3-bar w3-padding-16 w3-small" v-if="paging.total_list_cnt > 0">
        <span class="pg">
        <a href="javascript:;" @click="fnPage(1)" class="first w3-button w3-bar-item w3-border">&lt;&lt;</a>
        <a href="javascript:;" v-if="paging.start_page > 10" @click="fnPage(`${paging.start_page-1}`)"
           class="prev w3-button w3-bar-item w3-border">&lt;</a>
        <template v-for=" (n,index) in paginavigation()">
            <template v-if="paging.page==n">
                <strong class="w3-button w3-bar-item w3-border w3-green" :key="index">{{ n }}</strong>
            </template>
            <template v-else>
                <a class="w3-button w3-bar-item w3-border" href="javascript:;" @click="fnPage(`${n}`)" :key="index">{{ n }}</a>
            </template>
        </template>
        <a href="javascript:;" v-if="paging.total_page_cnt > paging.end_page"
           @click="fnPage(`${paging.end_page+1}`)" class="next w3-button w3-bar-item w3-border">&gt;</a>
        <a href="javascript:;" @click="fnPage(`${paging.total_page_cnt}`)" class="last w3-button w3-bar-item w3-border">&gt;&gt;</a>
        </span>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() { //변수생성
      return {
        requestBody: {}, //리스트 페이지 데이터전송
        list: {}, //리스트 데이터
        no: '', //게시판 숫자처리
        paging: {
          block: 0,
          end_page: 0,
          next_block: 0,
          page: 0,
          page_size: 0,
          prev_block: 0,
          start_index: 0,
          start_page: 0,
          total_block_cnt: 0,
          total_list_cnt: 0,
          total_page_cnt: 0,
        }, //페이징 데이터
        page: this.$route.query.page ? this.$route.query.page : 1,
        size: this.$route.query.size ? this.$route.query.size : 10,
        keyword: this.$route.query.keyword,
        paginavigation: function () { //페이징 처리 for문 커스텀
          alert();
          let pageNumber = [] //;
          // let start_page = this.paging.start_page;
          // let end_page = this.paging.end_page;
           let start_page = 1;
           let end_page = 10;
          for (let i = start_page; i <= end_page; i++) pageNumber.push(i);
          return pageNumber;
        }
      }
    },
    mounted() {
      this.fnGetList();
      this.paginavigation();
    },
    methods: {
      fnGetList() {
        this.requestBody = { // 데이터 전송
          keyword: this.keyword,
          page: this.page,
          size: this.size
        }
  
        this.$axios.get(this.$serverUrl + "/sales/list", {
          params: this.requestBody,
          headers: {}
        }).then((res) => {      
          console.log(res);
          this.list = res.data  //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.
          this.total_page_cnt =res.data.length;
          console.log(this.total_page_cnt);
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      },
      fnView(idx) {
        this.requestBody.idx = idx
        this.$router.push({
          path: './detail',
          query: this.requestBody
        })
      },
      fnWrite() {
        this.$router.push({
          path: './write'
        })
      },
      fnPage(n) {
        if (this.page !== n) {
          this.page = n
          this.fnGetList()
        }
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
.visit_header {
    width: 100%;
    height: 50px;
    font-size: xx-large;
    font-weight: bold;
    display: flex;
    justify-content: flex-start;
    border-bottom: 6px solid #0078b3;
}
.searchBar {
    padding-top: 20px;
    justify-content: space-between;
    align-items: center;
    height: 100px;
    background-color: #f0f0f0;
}
.colText {
    padding: 2px 20px 2px 20px;
    margin: 0px 10px 0px 10px;
    align-items: center;
    justify-content: center;
    background-color: #f4edfa;
    border: 1px solid rgb(185, 185, 185);
    font-size: 13px;
    font-weight: 900;
    color: #000040;
}
.colData {
    width: 180px;
    /* background-color: salmon; */
    border: 1px solid rgb(185, 185, 185);
    padding: 0px 3px 0px 0px;
}
.dropList {
    width: 100%;
    height: 100%;
    font-size: 13px;
    border: 0px;
    padding: 0px 0px 0px 5px;
    color: #000040;
}
.calendar {
    width: 100%;
    height: 100%;
    border: 0px;
    padding: 0px 5px 0px 5px;
    font-size: 13px;
}
.search_btn {
    padding: 2px 20px 2px 20px;
    font-size: 13px;
    font-weight: 900;
    color: white;
    background-color: #0078b3;
    border: 0px;
    border-radius: 4px;
    border: 1px solid rgb(185, 185, 185);
}
.menu {
    display: flex;
}
.menu_right {
    display: flex;
}
.btnSort {
    margin-right: 5px;
}
</style>