<template>
  <div class="emp_container">
      
      <div class="emp_header">
          <p class="ml_10">게시판</p>
      </div>
      <div class="emp_body">
          <!-- 검색 및 CRUD 버튼 시작 -->
          <div class="menu">
              <div class="menu_left">
                      <div>
                        <select class="dropdown_list">
                            <option>선택</option>
                            <option>제목</option>
                            <option>아이디</option>
                        </select>
                      </div>
                  <div class="mr_10"><input type="text"></div>
                  <button class="emp_btn" @click="isModalOpened = true">검색</button>
              </div>
              <div class="menu_right">
                <input type="radio" v-model='checkedValues' value="전체"  @change="clickFunc">전체&nbsp;
                <input type="radio" v-model='checkedValues' value="공개"  @change="clickFunc">공개&nbsp;
                <input type="radio" v-model='checkedValues' value="비공개"  @change="clickFunc">비공개
                  <button class="emp_btn mr_5">검색</button>
                  <button class="emp_btn mr_5">초기화</button>
              </div>
          </div>
          <!-- 검색 및 CRUD 버튼 끝 -->
          <!-- 데이터 입력 테이블 시작 -->
          <table>
              <tr>               
                  <td class="col_name">글 번호</td>
                  <td class="col_data">
                    <input type="text">
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

                    <input type="radio" v-model='checkedValues' value="공개1"  @change="clickFunc">공개&nbsp;
                    <input type="radio" v-model='checkedValues' value="비공개1"  @change="clickFunc">비공개
                  {{removeValue}}
                  </div>
              </tr>
              <tr>
                  <td class="col_name">내용</td>
                  </tr>
              <tr>
              </tr>
          </table>
          <table>
            <div class="board-contents">
      <textarea id="" cols="300" rows="10" v-model="contents" class="w3-input w3-border" style="resize: none;">
      </textarea>
    </div>
    <div class="emp_header">
          <p class="ml_10">게시판리스트</p>
          <button class="emp_btn">작성</button>
           <button class="emp_btn">저장</button>
          <button class="emp_btn">삭제</button>
          {{removeValue1}}
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
        </table>
        </table>
          <!-- 데이터 입력 테이블 끝 -->
      </div>
  </div>
</template>

<script>5

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
        }
    },
    computed:{    
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


