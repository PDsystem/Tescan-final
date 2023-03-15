<template>
  <div class="emp_container">
    <div class="emp_header">
      <p class="ml_10">게시판</p>
    </div>
    <div class="emp_body">
      <div class="menu">
        <div class="menu_left">
          <div>
            <select class="dropdown_list">
              <option>선택</option>
              <option>제목</option>
              <option>아이디</option>
            </select>
          </div>
          <input type="text" name="searchKeyword" v-model="searchKeyword"/>
          <button class="emp_btn"  @click="codeSearch()">검색</button>
        </div>
        <div class="menu_right">
          <input type="radio" v-model='checkedValues1' value="전체" @change="clickFunc">전체&nbsp;
          <input type="radio" v-model='checkedValues1' value="공개" @change="clickFunc">공개&nbsp;
          <input type="radio" v-model='checkedValues1' value="비공개" @change="clickFunc">비공개
          <button class="emp_btn mr_5">검색</button>
          <button class="emp_btn mr_5">초기화</button>
        </div>
      </div>
      <!-- 검색 및 CRUD 버튼 끝 -->
      <!-- 데이터 입력 테이블 시작 -->
      <div class="body_text">
        <tr>
          <td class="col_name">글 번호</td>
          <td class="col_data">
            <input v-model="contentNo" class="inputcss" name="contentNo">
          </td>
          <td class="col_name">ID</td>
          <td class="col_data">
            <input type="text">
          </td>
          <td class="col_name">PASSWORD</td>
          <td class="col_data">
            <input type="text">
          </td>
        </tr>
        <tr>
          <td class="col_name">제목</td>
          <td class="col_data">
            <input type="text">
          </td>
          <td class="col_name">등록날짜</td>
          <td class="col_data">
            <input class="emp_cal" type="date">
          </td>
          <td class="col_name">공개여부</td>
          <div>
            <input type="checkbox" v-model='checkedValues' value="공개1" @change="clickFunc">공개&nbsp;
            <input type="checkbox" v-model='checkedValues' value="비공개1" @change="clickFunc">비공개
          </div>
        </tr>
        <tr>
          <td class="col_name">내용</td>
        </tr>
        <tr>
        </tr>
      </div>
      <div class="board-contents">
        <textarea id="" cols="265" rows="10" v-model="contents" class="w3-input w3-border" style="resize: none;">
          </textarea>
      </div>
      <div class="emp_header" float="right">
        <p class="ml_10">게시판리스트</p>
        <div class="button_right">
          <button class="emp_btn mr_5">작성</button>
          <button class="emp_btn mr_5" id="save" @click="fnSave()">저장</button>
          <button class="emp_btn mr_5" v-on:click="fnDelete">삭제</button>
        </div>
      </div>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
            <th>공개 여부</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(row, content_no) in list" :key="content_no">
            <td>{{ row.content_no }}</td>
            <td>{{ row.content_title }}</td>
            <td>{{ row.content_id }}</td>
            <td>{{ row.content_date }}</td>
            <td>{{ row.disclosure }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="pagination w3-bar w3-padding-16 w3-small" v-if="paging.total_list_cnt > 0">
    <span class="pg">
    <a href="javascript:;" @click="codeSearch(1)" class="first w3-button w3-bar-item w3-border">&lt;&lt;</a>
    <a href="javascript:;" v-if="paging.start_page > 10" @click="codeSearch(`${paging.start_page-1}`)"
       class="prev w3-button w3-bar-item w3-border">&lt;</a>
    <template v-for=" (n,index) in paginavigation()">
        <template v-if="paging.page==n">
            <strong class="w3-button w3-bar-item w3-border w3-green" :key="index">{{ n }}</strong>
        </template>
        <template v-else>
            <a class="w3-button w3-bar-item w3-border" href="javascript:;" @click="codeSearch(`${n}`)" :key="index">{{ n }}</a>
        </template>
    </template>
    <a href="javascript:;" v-if="paging.total_page_cnt > paging.end_page"
       @click="codeSearch(`${paging.end_page+1}`)" class="next w3-button w3-bar-item w3-border">&gt;</a>
    <a href="javascript:;" @click="codeSearch(`${paging.total_page_cnt}`)" class="last w3-button w3-bar-item w3-border">&gt;&gt;</a>
    </span>
  </div>
  </div>
</template>
  
<script>

export default {

  data() {
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
      },
      //페이징 데이터
      page: this.$route.query.page ? this.$route.query.page : 1,
      size: this.$route.query.size ? this.$route.query.size : 10,
      keyword: this.$route.query.keyword,
      paginavigation: function () { //페이징 처리 for문 커스텀
        let nowPage=this.paging.page;
      let start_page =(parseInt((nowPage-1)/10))*10+1;
       let end_page = start_page+10-1;
       
       end_page=Math.min(this.paging.total_page_cnt,end_page);
       this.paging.start_page=start_page;
       this.paging.end_page=end_page;
      let pageNumber = [] //;
    
      for (let i = start_page; i <= end_page; i++) pageNumber.push(i);
      return pageNumber;
      },
      checkedValues: [],
      checkedValues1: [],
      isAllChecked: true
    }
  },
  mounted() {
    this.codeSearch();
    this.paginavigation();
    this.fnGetList();
  },
  methods: {
    codeSearch(currentPage){
      console.log(this.requestBody.searchType);
      this.requestBody = { //데이터 전송
        searchType:this.requestBody.searchType,

        searchKeyword: this.searchKeyword,
        page: currentPage-1,
        size:10
      }
      this.$axios.post(this.$serverUrl+"/askBoard/askBoardList",this.requestBody).then((res) => {
        console.log(res);
        this.list = res.data.content //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.
        this.paging.total_page_cnt =res.data.total_pages;
        this.paging.total_list_cnt =res.data.total_elements;
        this.paging.page=res.data.pageable.page_number+1;
    
   }).catch(() => {
     window.alert("네트워크 통신 이상");
   });
  },
  setSelect(){
    this.requestBody.searchType=event.target.value;
    },
    fnSave() {
    //let apiUrl = this.$serverUrl + "/codeMaster"
    this.requestBody = {
        "content_No": this.contentNo,
        "content_Title": this.contentTitle,
        "contents": this.contents,
        "content_Id": this.contentId,
        "content_Pw": this.contentPw,
        "content_Date": "",
        "disclosure": this.disclosure,
      }
      console.log(this.requestBody);
    },

    clickFunc(event) { // 하나의 버튼만 클릭
      for (let i = 0; i < this.checkedValues.length; i++) {
        if (this.checkedValues[i] !== event.target.value) {
          console.log(this.checkedValues[i])
          this.checkedValues.splice(i, 1);
        }
      }
    },
    allCheck() {
      if (!this.isAllChecked) {
        for (let i = 1; i < 4; i++) {
          this.checkedValues.push(i)
        }
        this.isAllChecked = true;
      } else {
        this.checkedValues = []

        this.isAllChecked = false;
      }
      this.$emit('checkClick', this.checkedValues)
    },

    fnList() {
        delete this.requestBody.contentNo
        this.$router.push({
          path: './list',
          query: this.requestBody
        })
      },

    fnDelete() {
        if (!confirm("삭제하시겠습니까?")) return
  
        this.$axios.delete(this.$serverUrl + '/askBoard/' + this.contentNo, {})
            .then(() => {
              alert('삭제되었습니다.')
              this.fnList();
            }).catch((err) => {
          console.log(err);
        })
      },

    fnGetList() {
      this.$axios.get(this.$serverUrl + "/askBoard/List",
      
      ).then((res) => {
        console.log(res);
        this.list = res.data
        console.log(this.list);

      }).catch((err) => {
        if (err.message.indexOf('Network Error') > -1) {
          alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
        }
      })
    }

  },
  computed: {
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

.button_right {
  text-align: center;
  margin: 0px -200px 0px 800px;
}

.emp_container {
  margin: 10px 0px 0px 0px;
  /** 헤더 제작 끝나면 마진 수정하기 */
}

.body_text {
  text-align: center;

}

div {
  box-sizing: border-box;
}

.emp_header {
  width: 160%;
  height: 50px;
  font-size: xx-large;
  font-weight: bold;
  display: flex;
  justify-content: flex-start;
  border-bottom: 6px solid #0078b3;
}


.table_br {
  width: 100%;
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

table {
  width: 75%;
  text-align: left;
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