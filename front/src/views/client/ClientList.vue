<template>
    <div class="customer-list">
        <!-- <caption><h1>거래처 현황</h1></caption> -->
        <div class="div-buttons">
            <input type="text" v-model="keyword" class="w3-input w3-border" placeholder="검색어를 입력해주세요.">
            <button type="button" class="w3-button" v-on:click="fnSave">검색</button>        
        </div>
    </div>
    <div class="customer-table">
        <table class="table-all">
            <thead>
                <tr>
                    <th>No</th>
                    <th>사업자등록번호</th>
                    <th>고객번호</th>
                    <th>고객명</th>
                    <th>부서</th>
                    <th>직급</th>
                    <th>우편번호</th>
                    <th>주소</th>
                    <th>상세주소</th>
                    <th>이메일</th>
                    <th>휴대폰번호</th>
                    <th>전화번호</th>
                    <th>담당자</th>
                    <th>비고</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(row,corRegNo) in list" :key="corRegNo">
                    <!-- <td>{{ row.row_num }}</td> -->
                    <td></td>
                    <td>{{ row.cor_reg_no }}</td>
                    <td>{{ row.cus_no }}</td>
                    <td>{{ row.name }}</td>
                    <td>{{ row.dept }}</td>
                    <td>{{ row.position }}</td>
                    <td>{{ row.zip }}</td>
                    <td>{{ row.address1 }}</td>
                    <td>{{ row.address2 }}</td>
                    <td>{{ row.email }}</td>
                    <td>{{ row.phone }}</td>
                    <td>{{ row.tel }}</td>
                    <td>{{ row.engineer }}</td>
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

            this.$axios.get(this.$serverUrl + "/client/list", {
            params: this.requestBody,
            headers: {}
            }).then((res) => {   
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
        }
        // ,fnView(corRegNo) {
        //     this.requestBody.corRegNo = corRegNo
        //     this.$router.push({
        //     path: './detail',
        //     query: this.requestBody
        //     })
        // },
        // fnWrite() {
        //     this.$router.push({
        //     path: './write'
        //     })
        // },
        // fnPage(n) {
        //     if (this.page !== n) {
        //     this.page = n
        //     this.fnGetList()
        //     }
        // }
    }
   }
</script>
<style>
</style>