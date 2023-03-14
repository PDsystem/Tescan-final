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
                            <td class="col_data"><a @dblclick="fnView(`${row.emp_no}`)">{{ row.emp_no }}</a></td>
                            <td class="col_data"><a @dblclick="fnView(`${row.emp_no}`)">{{ row.name }}</a></td>
                            <td class="col_data"><a @dblclick="fnView(`${row.emp_no}`)">{{ row.position_code }}</a></td>
                            <td class="col_data"><a @dblclick="fnView(`${row.emp_no}`)">{{ row.dept_code }}</a></td>
                            <td class="col_data"><a @dblclick="fnView(`${row.emp_no}`)">{{ row.tel }}</a></td>
                        </tr>
                    </tbody>
                </table>

                <!-- 가짜 페이징 시작 -->
                <div class="mt_15">
                    <a class="modal_paging" href="javascript:;" v-for="i in 20" :key="i" @click="fnPage(i - 1)">{{ i }}</a>
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
                    <button @click="newForm = true" class="emp_btn mr_5">추가</button>
                    <button @click="fnSave()" class="emp_btn mr_5">저장</button>
                    <button class="emp_btn mr_5">삭제</button>
                    <button @click="fnRefresh()" class="emp_btn mr_10">초기화</button>
                </div>
            </div>
            <!-- 검색 및 CRUD 버튼 끝 -->
            <!-- 데이터 입력 테이블 시작 -->
            <table v-if="newForm == true">
                <tr>
                    <td rowspan="9">
                        <div class="emp_photo mh_20">
                            <div>클릭하여 사진 추가</div>
                        </div>
                    </td>
                    <td class="col_name">사원번호</td>
                    <td class="col_data"><input readonly v-model="emp_no" class="emp_data"></td>
                    <td class="col_name">사원ID</td>
                    <td class="col_data"><input v-model="id" class="emp_data"></td>
                    <td class="col_name">사원PW</td>
                    <td class="col_data"><input v-model="password" class="emp_data"></td>
                </tr>
                <tr>
                    <td class="col_name">성명</td>
                    <td class="col_data"><input v-model="name" class="emp_data"></td>
                    <td class="col_name">영문성명</td>
                    <td class="col_data"><input v-model="eng_name" class="emp_data"></td>
                    <td class="col_name">주민등록번호</td>
                    <td class="col_data"><input v-model="reg_no" class="emp_data"></td>
                </tr>
                <tr>
                    <td class="col_name">주소</td>
                    <td class="table_ba" colspan="3"><input v-model="address_1" class="emp_data"></td>
                    <td class="col_name">상세주소</td>
                    <td class="col_data"><input v-model="address_2" class="emp_data"></td>
                </tr>
                <tr>
                    <td class="col_name">이메일</td>
                    <td class="col_data"><input v-model="email" class="emp_data"></td>
                    <td class="table_ba ft_13">@</td>
                    <td class="table_ba" colspan="2"><input v-model="semail" class="emp_data"></td>
                    <td class="col_data">
                        <select @change="fnSetEmail($event)" class="dropdown_list">
                            <option value="naver.com">naver.com</option>
                            <option value="hanmail.net">hanmail.net</option>
                            <option value="daum.net">daum.net</option>
                            <option value="nate.com">nate.com</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="hotmail.com">hotmail.com</option>
                            <option value="" selected>직접입력</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="col_name">전화번호</td>
                    <td class="col_data"><input v-model="tel" class="emp_data"></td>
                    <td class="col_name">휴대폰번호</td>
                    <td class="col_data"><input v-model="phone" class="emp_data"></td>
                    <td class="col_name">성별</td>
                    <td class="col_data">
                        <select v-model="sex" class="dropdown_list">
                            <option label="남성" value="M">남성</option>
                            <option label="여성" value="F">여성</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="col_name">입사구분코드</td>
                    <td class="col_data">
                        <select v-model="join_code" class="dropdown_list">
                            <option value="1010">재직</option>
                            <option value="1020">철수</option>
                            <option value="1030">휴직</option>
                        </select>
                    </td>
                    <td class="col_name">입사일자</td>
                    <td class="col_data"><input v-model="join_date" class="emp_cal" type="date"></td>
                    <td class="col_name">부서코드</td>
                    <td class="col_data">
                        <select v-model="dept_code" class="dropdown_list">
                            <option value="1210">경영지원부</option>
                            <option value="1230">기술부</option>
                            <option value="1220">영업부</option>
                            <option value="1240">품질</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="col_name">직위구분코드</td>
                    <td class="col_data">
                        <select v-model="position_code" class="dropdown_list">
                            <option value="1130">과장</option>
                            <option value="1120">대리</option>
                            <option value="1160">대표이사</option>
                            <option value="1150">부장</option>
                            <option value="1110">사원</option>
                            <option value="1170">상무</option>
                            <option value="1180">전무</option>
                            <option value="1190">주임</option>
                            <option value="1140">차장</option>
                        </select>
                    </td>
                    <td class="col_name">퇴사일자</td>
                    <td class="col_data"><input v-model="retire_date" class="emp_cal" type="date"></td>
                    <td class="col_name">연봉</td>
                    <td class="col_data"><input v-model="salary" class="emp_data"></td>
                </tr>
                <tr>
                    <td class="col_name">병역여부</td>
                    <td class="col_data">
                        <select v-model="mil_yn" class="dropdown_list">
                            <option value="Y">필</option>
                            <option value="N">미필</option>
                        </select>
                    </td>
                    <td class="table_ba" colspan="4"></td>
                </tr>
                <tr>
                    <td class="col_name">비고</td>
                    <td class="table_ba" colspan="6"><input v-model="note" class="emp_data"></td>
                </tr>
            </table>
            <!-- 데이터 입력 테이블 끝 -->
            <!-- 데이터 조회 테이블 시작 -->
            <table v-else>
                <tr>
                    <td rowspan="9">
                        <div class="emp_photo mh_20">
                            <div>클릭하여 사진 추가</div>
                        </div>
                    </td>
                    <td class="col_name">사원번호</td>
                    <td class="col_data"><input readonly v-bind:value="emp_no" class="emp_data"></td>
                    <td class="col_name">사원ID</td>
                    <td class="col_data"><input readonly v-bind:value="id" class="emp_data"></td>
                    <td class="col_name">사원PW</td>
                    <td class="col_data"><input readonly v-bind:value="password" class="emp_data"></td>
                </tr>
                <tr>
                    <td class="col_name">성명</td>
                    <td class="col_data"><input v-bind:value="name" class="emp_data"></td>
                    <td class="col_name">영문성명</td>
                    <td class="col_data"><input v-bind:value="eng_name" class="emp_data"></td>
                    <td class="col_name">주민등록번호</td>
                    <td class="col_data"><input readonly v-bind:value="reg_no" class="emp_data"></td>
                </tr>
                <tr>
                    <td class="col_name">주소</td>
                    <td class="table_ba" colspan="3"><input v-bind:value="address_1" class="emp_data"></td>
                    <td class="col_name">상세주소</td>
                    <td class="col_data"><input v-bind:value="address_2" class="emp_data"></td>
                </tr>
                <tr>
                    <td class="col_name">이메일</td>
                    <td class="col_data"><input v-bind:value="email" class="emp_data"></td>
                    <td class="table_ba ft_13">@</td>
                    <td class="table_ba" colspan="2"><input v-bind:value="semail" class="emp_data"></td>
                    <td class="col_data">
                        <select @change="fnSetEmail($event)" class="dropdown_list">
                            <option value="naver.com">naver.com</option>
                            <option value="hanmail.net">hanmail.net</option>
                            <option value="daum.net">daum.net</option>
                            <option value="nate.com">nate.com</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="hotmail.com">hotmail.com</option>
                            <option value="" selected>직접입력</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="col_name">전화번호</td>
                    <td class="col_data"><input v-bind:value="tel" class="emp_data"></td>
                    <td class="col_name">휴대폰번호</td>
                    <td class="col_data"><input v-bind:value="phone" class="emp_data"></td>
                    <td class="col_name">성별</td>
                    <td class="col_data">
                        <select v-model="sex" class="dropdown_list">
                            <option label="남성" value="M">남성</option>
                            <option label="여성" value="F">여성</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="col_name">입사구분코드</td>
                    <td class="col_data">
                        <select v-model="join_code" class="dropdown_list">
                            <option value="1010">재직</option>
                            <option value="1020">철수</option>
                            <option value="1030">휴직</option>
                        </select>
                    </td>
                    <td class="col_name">입사일자</td>
                    <td class="col_data"><input v-model="join_date" class="emp_cal" type="date"></td>
                    <td class="col_name">부서코드</td>
                    <td class="col_data">
                        <select v-model="dept_code" class="dropdown_list">
                            <option value="1210">경영지원부</option>
                            <option value="1230">기술부</option>
                            <option value="1220">영업부</option>
                            <option value="1240">품질</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="col_name">직위구분코드</td>
                    <td class="col_data">
                        <select v-model="position_code" class="dropdown_list">
                            <option value="1130">과장</option>
                            <option value="1120">대리</option>
                            <option value="1160">대표이사</option>
                            <option value="1150">부장</option>
                            <option value="1110">사원</option>
                            <option value="1170">상무</option>
                            <option value="1180">전무</option>
                            <option value="1190">주임</option>
                            <option value="1140">차장</option>
                        </select>
                    </td>
                    <td class="col_name">퇴사일자</td>
                    <td class="col_data"><input v-model="retire_date" class="emp_cal" type="date"></td>
                    <td class="col_name">연봉</td>
                    <td class="col_data"><input v-bind:value="salary" class="emp_data"></td>
                </tr>
                <tr>
                    <td class="col_name">병역여부</td>
                    <td class="col_data">
                        <select v-model="mil_yn" class="dropdown_list">
                            <option value="Y">필</option>
                            <option value="N">미필</option>
                        </select>
                    </td>
                    <td class="table_ba" colspan="4"></td>
                </tr>
                <tr>
                    <td class="col_name">비고</td>
                    <td class="table_ba" colspan="6"><input v-bind:value="note" class="emp_data"></td>
                </tr>
            </table>
            <!-- 데이터 조회 테이블 끝 -->
        </div>
        <div style="margin-bottom: 300px;">
            그냥 지우면 된다
        </div>
    </div>
</template>
<!-- 템플릿 끝 -->
<!-- 스크립트 시작 -->
<script>
import router from '@/router'

export default {

    data() {
        return {
            isModalOpened: false,
            newForm: false,

            list: {},
            page: 0,   // 화면에 출력되는 페이지
            // size: 12,   // 이미 back에서 사이즈 설정 완료
            emp_no: this.$route.query.emp_no,  // 이렇게 하면 찾아지는구나

            emp_no: '',
            id: '',
            name: '',
            eng_name: '',
            tel: '',
            dept_code: '',
            phone: '',
            position_code: '',
            sex: '',
            reg_no: '',
            email: '',
            semail: '',
            zip: '',
            address_1: '',
            address_2: '',
            mil_yn: '',
            join_code: '',
            salary: '',
            join_date: '',
            retire_date: '',
            image: '',
            password: '',
            note: '',

        }
    },
    mounted() {

    },
    methods: {
        // 사원 목록 조회하기
        fnGetList() {
            this.requestBody = {
                page: this.page,
                size: this.size
            },
                //console.log("requestBody.page : " + this.requestBody.page),
                this.$axios.get(this.$serverUrl + "/emp/list", {
                    params: this.requestBody,
                    headers: {}
                }).then((res) => {
                    this.list = res.data
                    //console.log("this.list.prev : " + this.list[0].prev)
                    //console.log("this.list.next : " + this.list[0].next)
                }).catch((err) => {
                    if (err.message > -1) {
                        alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해 주세요.')
                    }
                })
        },
        // 사원 한 건 조회하기
        fnView(emp_no) {
            console.log("더블클릭 했어용")
            // console.log(emp_no) // 베리굿
            this.$axios.get(this.$serverUrl + '/emp/' + emp_no)
                .then((res) => {
                    // console.log(res.data) // res.data 안에 데이터 잘 들어왔다

                    this.emp_no = res.data.emp_no
                    this.id = res.data.id
                    this.name = res.data.name
                    this.eng_name = res.data.eng_name
                    this.tel = res.data.tel
                    this.phone = res.data.phone
                    this.reg_no = res.data.reg_no
                    this.email = res.data.email
                    this.semail = res.data.semail
                    this.zip = res.data.zip
                    this.address_1 = res.data.address_1
                    this.address_2 = res.data.address_2
                    this.salary = res.data.salary
                    this.image = res.data.image
                    this.password = res.data.password
                    this.note = res.data.note
                    this.join_date = res.data.join_date
                    this.retire_date = res.data.retire_date
                    this.join_code = res.data.join_code
                    this.sex = res.data.sex
                    this.dept_code = res.data.dept_code
                    this.position_code = res.data.position_code
                    this.mil_yn = res.data.mil_yn

                    console.log("emp_no :" + this.emp_no)
                    console.log("id :" + this.id)
                    console.log("name :" + this.name)
                    console.log("eng_name :" + this.eng_name)
                    console.log("tel :" + this.tel)
                    console.log("dept_code :" + this.dept_code)
                    console.log("phone :" + this.phone)
                    console.log("position_code :" + this.position_code)
                    console.log("sex :" + this.sex)
                    console.log("reg_no :" + this.reg_no)
                    console.log("email :" + this.email)
                    console.log("semail :" + this.semail)
                    console.log("zip :" + this.zip)
                    console.log("address_1 :" + this.address_1)
                    console.log("address_2 :" + this.address_2)
                    console.log("mil_yn :" + this.mil_yn)
                    console.log("join_code :" + this.join_code)
                    console.log("salary :" + this.salary)
                    console.log("join_date :" + this.join_date)
                    console.log("retire_date :" + this.retire_date)

                    this.isModalOpened = false;

                }).catch((err) => {
                    if (err.message > -1) {
                        alert('네트워크가 원활하지 않습니다.\n 잠시 후 다시 시도해 주세요.')
                    }
                })
        },
        fnSetEmail(event) {
            //console.log(event.target.value)
            this.semail = event.target.value
        },
        fnSave() {
            this.form = {
                "emp_no": this.emp_no,
                "id": this.id,
                "name": this.name,
                "eng_name": this.eng_name,
                "tel": this.tel,
                "dept_code": this.dept_code,
                "phone": this.phone,
                "position_code": this.position_code,
                "sex": this.sex,
                "reg_no": this.reg_no,
                "email": this.email,
                "semail": this.semail,
                "zip": this.zip,
                "address_1": this.address_1,
                "address_2": this.address_2,
                "mil_yn": this.mil_yn,
                "join_code": this.join_code,
                "salary": this.salary,
                "join_date": this.join_date,
                "retire_date": this.retire_date,
                "image": this.image,
                "password": this.password,
                "note": this.note
            }
            console.log("emp_no :" + this.emp_no)
            console.log("id :" + this.id)
            console.log("eng_name :" + this.eng_name)
            console.log("tel :" + this.tel)
            console.log("dept_code :" + this.dept_code)
            console.log("phone :" + this.phone)
            console.log("position_code :" + this.position_code)
            console.log("sex :" + this.sex)
            console.log("reg_no :" + this.reg_no)
            console.log("email :" + this.email)
            console.log("semail :" + this.semail)
            console.log("zip :" + this.zip)
            console.log("address_1 :" + this.address_1)
            console.log("address_2 :" + this.address_2)
            console.log("mil_yn :" + this.mil_yn)
            console.log("join_code :" + this.join_code)
            console.log("salary :" + this.salary)
            console.log("join_date :" + this.join_date)
            console.log("retire_date :" + this.retire_date)
            // if (this.emp_no === undefined) {
                // INSERT
                this.$axios.post(this.$serverUrl + '/emp', this.form)
                    .then((res) => {
                        // this.fnView(res.data.emp_no)
                        console.log('post 했다!!!!!!!!!!!!!!!!')
                    })
            // }
        },
        // 초기화 버튼 - 새로고침
        fnRefresh() {
            router.go(0)
        },
        // 페이징
        fnPage(n) {
            if (this.page !== n) {
                this.page = n
                this.fnGetList()
            }
        },
    }
}
</script>
<!-- 스크립트 끝 -->
<!-- CSS 시작 -->
<style scoped>
@import url('../../assets/common.css');

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
    cursor: pointer;
}

.emp_cal {
    width: 100%;
    height: 100%;
    border: 0px;
    padding: 0px 5px 0px 5px;
    font-size: 13px;
}

.emp_data {
    width: 100%;
    height: 100%;
    border: none;
    padding-left: 5px;
    font-size: 13px;
}

.black_bg {
    width: 100%;
    height: 100%;
    background: rgba(11, 9, 9, 0.5);
    /* position: fixed; */
    position: absolute;
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

.table_ba {
    border: 1px solid rgb(185, 185, 185);
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

