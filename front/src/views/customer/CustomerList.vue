<template>
    <div class="customer-list">
        <!-- <caption><h1>거래처 현황</h1></caption> -->
        <div class="common-buttons">
            <input type="text" v-model="keyword" class="w3-input w3-border" placeholder="검색어를 입력해주세요.">
            <button type="button" class="w3-button" v-on:click="fnSave">검색</button>        
            <button type="button" class="w3-button" v-on:click="fnSave">등록</button>        
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
    </div>
    <div class="customer-table">
        <table class="table-all">
            <thead>
                <tr>
                    <th>No</th>
                    <th>사업자등록번호</th>
                    <th>고객명</th>
                    <th>타입 코드</th>
                    <th>이메일</th>
                    <th>REGION1</th>
                    <th>REGION2</th>
                    <th>팩스</th>
                    <th>웹사이트</th>
                    <th>주소</th>
                    <th>상세주소</th>
                    <th>상위 거래처</th>
                    <th>전화번호</th>
                    <!-- <th>이메일 홈페이지</th> -->
                    <!-- <th>담당자ID</th> -->
                    <!-- <th>담당자PW</th> -->
                    <th>비고</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(row,cor_reg_no) in list" :key="cor_reg_no">
                    <td>{{ row.idx }}</td>
                    <!-- <td>1</td> -->
                    <td>{{ row.cor_reg_no }}</td>
                    <td>{{ row.customer_name }}</td>
                    <td>{{ row.typeCode }}</td>
                    <td>{{ row.email }}</td>
                    <td>{{ row.region1 }}</td>
                    <td>{{ row.region2 }}</td>
                    <td>{{ row.fax }}</td>
                    <td>{{ row.web }}</td>
                    <td>{{ row.address1 }}</td>
                    <td>{{ row.address2 }}</td>
                    <td>{{ row.high_customer }}</td>
                    <td>{{ row.tel }}</td>
                    <!-- <td>{{ row.semail }}</td> -->
                    <!-- <td>{{ row.content_id }}</td> -->
                    <!-- <td>{{ row.content_pw }}</td> -->
                    <td>{{ row.note }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div>
        <!-- 페이지 처리-->
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

            this.$axios.get(this.$serverUrl + "/customer/list", {
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
        fnView(cor_reg_no) {
            this.requestBody.cor_reg_no = cor_reg_no
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
<style>
    .common-buttons {
        padding: 8px;
        text-align: right;
    }
    /* 리스트 화면 */
    .customer-list {
        width: 1000px;
        margin: auto;
        /* background-color: pink; */
    }
    .customer-table {
        width: 1000px;
        margin: auto;
        overflow: auto;
        white-space: nowrap;
        /* background-color: pink; */
    }
    /*테이블*/
    .table-all{
        width: 1000px;
        border: 1px solid #444444;
        border-collapse: collapse;
    }
    th{
        border: 1px solid #444444;
        background:#888888;
    }
    td {
        border: 1px solid #444444;
    }
    /* 상세보기 */
    .customer-detail {
        /* width: 1000px; */
        /* margin: auto; */
        text-align: left;
        /* background-color: #888888; */
    }
    /* 
    저장 화면 
    .customer-contents {
        padding: 12px 8px;
        border-bottom: 1px solid #eee;
    } */
    .col1{
        width: 90px;
    }
    #col-long{
        width: 120px;
    }
    .inputcss{
        width: 200px;
    }
</style>