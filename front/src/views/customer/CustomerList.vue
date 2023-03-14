<template>
    <div class="customer-list">
            <h1>거래처 현황</h1>
        <div class="div-buttons">
            <input type="text" v-model="keyword" class="w3-input w3-border" placeholder="검색어를 입력해주세요.">
            <button type="button" class="w3-button" v-on:click="fnSearch">검색</button>
            <button type="button" class="w3-button" v-on:click="fnWrite">등록</button>                
            <!-- <button type="button" class="w3-button" v-on:click="fnView">수정</button>                 -->
            <!-- <button type="button" class="w3-button" v-on:click="fnDelete">삭제</button>         -->
            <!-- <button type="button" class="w3-button" v-on:click="fn">초기화</button>         -->
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
                <tr v-for="(row,corRegNo) in list" :key="corRegNo">
                    <td><input type="checkbox" value="{{row.cor_reg_no }}"></td>
                    <td v-on:dblclick="fnView">{{ row.cor_reg_no }}</td>
                    <td>{{ row.customer_name }}</td>
                    <td>{{ row.type_code }}</td>
                    <td>{{ row.email }}</td>
                    <td>{{ row.region1 }}</td>
                    <td>{{ row.region2 }}</td>
                    <td>{{ row.fax }}</td>
                    <td>{{ row.web }}</td>
                    <td>{{ row.address1 }}</td>
                    <td>{{ row.address2 }}</td>
                    <td>{{ row.high_customer }}</td>
                    <td>{{ row.tel }}</td>
                    <td>{{ row.note }}</td>
                </tr>
            </tbody>
        </table>
    </div>
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
            // alert();
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
                if (res.data.result_code === "OK") {
                this.list = res.data.data
                this.paging = res.data.pagination
                this.no = this.paging.total_list_cnt - ((this.paging.page - 1) * this.paging.page_size)
                }
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
    .div-buttons {
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
    .w3-button {
        padding: 2px 20px 2px 20px;
        font-size: 13px;
        font-weight: 900;
        color: white;
        background-color: #0078b3;
        border: 0px;
        border-radius: 4px;
        border: 1px solid rgb(185, 185, 185);
        margin-right: 5px;
    }
</style>