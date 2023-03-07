<!-- 템플릿 시작 -->
<template>
    <div class="emp_container">
        <!-- 사원 검색 모달창 시작-->
        <div class="black_bg" v-if="isModalOpened == true">
            <div class="white_bg">
                <div class="emp_header">사원 검색</div>
                <div class="emp_modal_menu">
                    <select class="modal_dropdown_list">
                        <option>전체</option>
                        <option>이름</option>
                        <option>사번</option>
                        <option>부서</option>
                    </select>
                    <div>
                        <button class="emp_btn mr_5">검색</button>
                        <button class="emp_btn">초기화</button>
                    </div>
                </div>
                <table class="modal_table mt_5">
                    <thead>
                        <tr>
                            <th class="col_name">사번</th>
                            <th class="col_name">이름</th>
                            <th class="col_name">직급</th>
                            <th class="col_name">부서</th>
                            <th class="col_name">전화번호</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr id="emp_row" v-for="(row, emp_no) in list" :key="emp_no">
                            <td class="col_data"><a>{{ row.emp_no }}</a></td>
                            <td class="col_data">{{ row.name }}</td>
                            <td class="col_data">{{ row.position_code }}</td>
                            <td class="col_data">{{ row.dept_code }}</td>
                            <td class="col_data">{{ row.tel }}</td>
                        </tr>
                    </tbody>
                </table>

                <!-- 가짜 페이징 시작 -->
                <div class="mt_15">
                    <a class="modal_paging" href="javascript:;" v-for="i in 18" :key="i" @click="fnPage(i - 1)">{{ i }}</a>
                </div>
                <!-- 가짜 페이징 끝 -->

                <div class="modal_menu mv_20">
                    <button class="emp_btn">선택</button>
                    <button class="emp_btn" @click="isModalOpened = false">닫기</button>
                </div>
            </div>
        </div>
        <!-- 사원 검색 모달창 끝-->
        <div class="emp_header">
            <p class="ml_10">사원관리</p>
        </div>
        <div class="emp_body">
            <!-- 검색 및 CRUD 버튼 시작 -->
            <div class="menu">
                <div class="menu_left">
                    <div class="emp_search_keyword">
                        <p>사용자</p>
                    </div>
                    <div class="mr_10"><input type="text"></div>
                    <button class="emp_btn" @click="fnGetList(), isModalOpened = true">검색</button>
                </div>
                <div class="menu_right">
                    <button class="emp_btn mr_5">추가</button>
                    <button class="emp_btn mr_5">저장</button>
                    <button class="emp_btn mr_5">삭제</button>
                    <button class="emp_btn mr_10">초기화</button>
                </div>
            </div>
            <!-- 검색 및 CRUD 버튼 끝 -->
            <!-- 데이터 입력 테이블 시작 -->
            <table>
                <tr>
                    <td rowspan="9">
                        <div class="emp_photo mh_20">
                            <div>클릭하여 사진 추가</div>
                        </div>
                    </td>
                    <td class="col_name">사원번호</td>
                    <td class="col_data"></td>
                    <td class="col_name">사원ID</td>
                    <td class="col_data"></td>
                    <td class="col_name">사원PW</td>
                    <td class="col_data"></td>
                </tr>
                <tr>
                    <td class="col_name">성명</td>
                    <td class="col_data"></td>
                    <td class="col_name">영문성명</td>
                    <td class="col_data"></td>
                    <td class="col_name">주민등록번호</td>
                    <td class="col_data"></td>
                </tr>
                <tr>
                    <td class="col_name">주소</td>
                    <td colspan="3"></td>
                    <td class="col_name">상세주소</td>
                    <td class="col_data"></td>
                </tr>
                <tr>
                    <td class="col_name">이메일</td>
                    <td class="col_data"></td>
                    <td class="table_bt table_br ft_13">@</td>
                    <td class="table_bt" colspan="2"></td>
                    <td class="col_data">
                        <select class="dropdown_list">
                            <option value="">naver.com</option>
                            <option>hanmail.net</option>
                            <option>daum.net</option>
                            <option>nate.com</option>
                            <option>gmail.com</option>
                            <option>hotmail.com</option>
                            <option selected>직접입력</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="col_name">전화번호</td>
                    <td class="col_data"></td>
                    <td class="col_name">휴대폰번호</td>
                    <td class="col_data"></td>
                    <td class="col_name">성별</td>
                    <td class="col_data">
                        <select class="dropdown_list">
                            <option>남성</option>
                            <option>여성</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="col_name">입사구분코드</td>
                    <td class="col_data">
                        <select class="dropdown_list">
                            <option>재직</option>
                            <option>철수</option>
                            <option>휴직</option>
                        </select>
                    </td>
                    <td class="col_name">입사일자</td>
                    <td class="col_data"><input class="emp_cal" type="date"></td>
                    <td class="col_name">부서코드</td>
                    <td class="col_data">
                        <select class="dropdown_list">
                            <option>경영지원부</option>
                            <option>기술부</option>
                            <option>영업부</option>
                            <option>품질</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="col_name">직위구분코드</td>
                    <td class="col_data">
                        <select class="dropdown_list">
                            <option>과장</option>
                            <option>대리</option>
                            <option>대표이사</option>
                            <option>부장</option>
                            <option>사원</option>
                            <option>상무</option>
                            <option>전무</option>
                            <option>주임</option>
                            <option>차장</option>
                        </select>
                    </td>
                    <td class="col_name">퇴사일자</td>
                    <td class="col_data"><input class="emp_cal" type="date"></td>
                    <td class="col_name">연봉</td>
                    <td class="col_data"></td>
                </tr>
                <tr>
                    <td class="col_name">병역여부</td>
                    <td class="col_data">
                        <select class="dropdown_list">
                            <option>필</option>
                            <option>미필</option>
                        </select>
                    </td>
                    <td class="table_br" colspan="4"></td>
                </tr>
                <tr>
                    <td class="col_name ">비고</td>
                    <td class="table_bb table_bt table_br" colspan="6"></td>
                </tr>
            </table>
            <!-- 데이터 입력 테이블 끝 -->
        </div>
    </div>
</template>
<!-- 템플릿 끝 -->
<!-- 스크립트 시작 -->
<script>
export default {

    data() {
        return {
            isModalOpened: false,
            list: {},
            page: 0,   // 화면에 출력되는 페이지
            size: 12,   // 이미 back에서 사이즈 설정 완료
        }
    },
    mounted() {

    },
    methods: {
        // 사원 목록 가져오기
        fnGetList() {
            this.requestBody = {
                page: this.page,
                size: this.size
            },
                console.log("requestBody.page : " + this.requestBody.page),
                this.$axios.get(this.$serverUrl + "/emp/list", {
                    params: this.requestBody,
                    headers: {}
                }).then((res) => {
                    this.list = res.data
                    console.log("this.list.prev : " + this.list[0].prev)
                    console.log("this.list.next : " + this.list[0].next)
                }).catch((err) => {
                    if (err.message > -1) {
                        alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해 주세요.')
                    }
                })
        },
        fnPage(n) {
            if (this.page !== n) {
                this.page = n
                this.fnGetList()
            }
        },
        fnGetRow() {
            console.log("더블클릭 했어용");


        }
    }
}
</script>
<!-- 스크립트 끝 -->
<!-- CSS 시작 -->
<style scoped>
* {
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
    /* border: 1px solid rgb(185, 185, 185); */
}

.ft_13 {
    font-size: 13px;
}

.mh_10 {
    margin: 0px 10px 0px 10px;
}

.mh_20 {
    margin: 0px 20px 0px 20px;
}

.mv_20 {
    margin: 20px 0px 20px 0px;
}

.ml_10 {
    margin: 0px 0px 0px 10px;
}

.mr_10 {
    margin: 0px 10px 0px 0px;
}

.mr_5 {
    margin: 0px 5px 0px 0px;
}

.mt_5 {
    margin: 5px 0px 0px 0px;
}

.mt_15 {
    margin: 15px 0px 0px 0px;
}

table {
    /* width: 1160px;
    height: 500px; 이제 필요 없음*/

}

.emp_container {
    margin: 10px 0px 0px 0px;
    /** 헤더 제작 끝나면 마진 수정하기 */
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
    height: 600px;
    background-color: white;
    border-radius: 5px;
    padding: 20px;
}

.emp_modal_menu {
    display: flex;
    justify-content: space-between;
    height: 40px;
    background-color: #f0f0f0;
    padding: 0px 10px 0px 10px;
    align-items: center;
    border: 1px solid rgb(185, 185, 185);
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

.menu_right {
    display: flex;
}

.emp_photo {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 170px;
    height: 200px;
    border: 1px solid rgb(185, 185, 185);
    font-size: 15px;
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
    padding: 3px 3px 3px 0px;
    font-size: 13px;
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

.dropdown_list {
    width: 100%;
    height: 100%;
    font-size: 13px;
    border: 0px;
    padding: 0px 0px 0px 5px;
    color: #000040;
}

.modal_dropdown_list {
    width: 60px;
    height: 30px;
    font-size: 13px;
    color: #000040;
}

.modal_table {
    width: 100%;
    border-top: 3px solid rgb(156, 139, 108);
}

.modal_menu {
    display: flex;
    justify-content: space-evenly;
}

.modal_paging {
    text-decoration: none;
    color: #0078b3;
    font-weight: 600;
    margin: 0px 5px 0px 5px;
}
</style>
<!-- CSS 끝 -->

