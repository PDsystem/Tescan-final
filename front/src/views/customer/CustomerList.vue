<template>
    <div class="cn_headerline">
        <h5>거래처 현황</h5>
    </div>
    <div class="board-list">        
        <div class="cn_menu">
            <br><br><br>
            <select name="searchCondition" @change="setSelect()">
                <option value=""></option>
                <option value="CORREGNO">사업자 등록번호</option>
                <option value="CUSTOMERNAME">고객 명</option>
            </select>
            <input type="text" v-model="keyword" class="w3-input w3-border" placeholder="검색어를 입력해주세요.">
            <button type="button" class="cn_btn common-buttons" @click="fnGetList()">검색</button>
            <button type="button" class="cn_btn common-buttons" v-on:click="fnWrite">등록</button>                
        </div>
        <table class="table-all">
            <thead>
                <tr class="cn_col_name">
                    <th><input type="checkbox" value="{{row.cor_reg_no }}"></th>
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
                    <th>비고</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(row,idx) in list" :key="idx">
                    <td><input type="checkbox" value="{{row.cor_reg_no }}"></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{idx+1}}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.cor_reg_no }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.customer_name }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.type_code }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.email }}@{{ row.semail }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.region1 }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.region2 }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.fax }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.web }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.address1 }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.address2 }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.high_customer }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.tel }}</a></td>
                    <td><a @dblclick="fnView(`${row.cor_reg_no}`)">{{ row.note }}</a></td>
                </tr>
            </tbody>
        </table>
        <div class="pagination w3-bar w3-padding-16 w3-small" v-if="paging.total_list_cnt > 0">
            <span class="pg">
            <a href="javascript:;" @click="fnGetList(1)" class="first w3-bar-item w3-border">&lt;&lt;</a>
            <a href="javascript:;" v-if="paging.start_page > 10" @click="fnGetList(`${paging.start_page-1}`)"
                class="prev w3-bar-item w3-border">&lt;</a>
            <template v-for=" (n,index) in paginavigation()">
                <template v-if="paging.page==n">
                    <strong class=" w3-bar-item w3-border w3-green" :key="index">{{ n }}</strong>
                </template>
            <template v-else>
                <a class="w w3-bar-item w3-border" href="javascript:;" @click="fnGetList(`${n}`)" :key="index">{{ n }}</a>
            </template>
            </template>
                <a href="javascript:;" v-if="paging.total_page_cnt > paging.end_page"
                @click="fnGetList(`${paging.end_page+1}`)" class="next w3-bar-item w3-border">&gt;</a>
                <a href="javascript:;" @click="fnGetList(`${paging.total_page_cnt}`)" class="last w3-bar-item w3-border">&gt;&gt;</a>
                </span>
        </div>
        <div class="cn_menu">
            <select name="searchCondition" @change="setSelect()">
                <option value=""></option>
                <option value="CORREGNO">사업자 등록번호</option>
                <option value="CUSTOMERNAME">고객 명</option>
            </select>
            <input type="text" v-model="keyword" class="w3-input w3-border" placeholder="검색어를 입력해주세요.">
            <button type="button" class="cn_btn common-buttons" @click="fnGetList()">검색</button>
            <button type="button" class="cn_btn common-buttons" v-on:click="fnWrite">등록</button>                
        </div>   
        <br><br><br>
    </div>

</template>
<script>
  export default {
    data() { //변수생성
        return {
            requestBody: {}, //리스트 페이지 데이터전송
            list: {}, //리스트 데이터
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
                let pageNumber = [] //;
                let start_page = this.paging.start_page;
                let end_page = this.paging.end_page;
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
        fnGetList(n) {
            if (this.page !== n) {
                this.page = n
             }
            this.requestBody = { // 데이터 전송
                searchType:this.requestBody.searchType,
                keyword: this.keyword,
                page: this.page,
                size: this.size
            }
            this.$axios.get(this.$serverUrl + "/customer/list",{
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
        setSelect(){
            this.requestBody.searchType=event.target.value;
        },
        fnView(corRegNo) {            
            this.requestBody.corRegNo = corRegNo           
            this.$router.push({
            path: './detail',
            query: this.requestBody
            })
        },
        fnWrite() {
            this.$router.push({
            path: './write'
            })
        }
    }
   }
</script>

<style scoped>
@import url('../../assets/common.css');
* {
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
}
.cn_menu{
    float: right;
}
.board-list { width: 1500px;} 
/*테이블*/
.table-all{
    width: 1500px;
    border: 1px solid #444444;
    border-collapse: collapse;
}
th{
    width: 120px;
    padding: 5px 0px 5px 0px;
    background-color: #f0f0f0;
    font-size: 13px;
    border: 1px solid rgb(185, 185, 185);
    border-radius: 3px;
    color: #000040;
}
td {
    width: 180px;
    border: 1px solid rgb(185, 185, 185);
    padding: 3px 3px 3px 0px;
    font-size: 13px;
}
</style>
