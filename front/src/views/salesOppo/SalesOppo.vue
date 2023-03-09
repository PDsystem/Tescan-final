<template>
    <div class="oppo">
        <div class="oppo_header">
            <p style="margin-left: 10px;">영업기회</p>
        </div>
        <div class="oppo_body">
            <!--검색부분-->
            <div class="searchBar">
                <div class="menu">
                    <!--담당자, 거래처검색-->
                    <div class="empSearch colText">담당자검색</div>
                    <div style="margin-right: 10px;"><input type="text"></div>
                    <button class="empSearch_btn search_btn" @click="isModalOpened = true">검색</button>
                    <div class="corSearch colText">거래처검색</div>
                    <div style="margin-right: 10px;"><input type="text"></div>
                    <button class="corSearch_btn search_btn" @click="isModalOpened = true">검색</button>

                    <!--검색관련 버튼-->
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
        </div>
        <br>
        <!--리스트-->
        <hr class="line-basic">
        <table>
            <thead>
                <tr>
                    <th>담당자</th>
                    <th>등록일자</th>
                    <th>장비명</th>
                    <th>거래처</th>
                    <th>프로젝트명</th>
                    <th>현황분류코드</th>
                    <th>예상금액</th>
                    <th>예상종료일자</th>
                    <th>가능성</th>
                    <th>완료일자</th>
                    <th>계약금액</th>
                    <th>계약금액(달러)</th>
                    <th>사유</th>
                    <th>비고</th>
                    <!-- <th>No.</th> -->
                </tr>   
            </thead>
            <tbody>
                <tr v-for="(row, empno) in list" :key="empno">
                    <td>{{ row.empno }}</td>
                    <td>{{ row.reg_date }}</td>
                    <td>{{ row.equip_no }}</td>
                    <td>{{ row.cor_reg_no }}</td>
                    <td>{{ row.project_name }}</td>
                    <td>{{ row.condition_code }}</td>
                    <td>{{ row.sales_forecast }}</td>
                    <td>{{ row.retire_date }}</td>
                    <td>{{ row.possibility }}</td>
                    <td>{{ row.end_date }}</td>
                    <td>{{ row.con_price }}</td>
                    <td>{{ row.con_price_dol }}</td>
                    <td>{{ row.reason }}</td>
                    <td>{{ row.note }}</td>
                    <!-- <td>{{ d.ROWNUM }}</td> -->
                </tr>
            </tbody>
        </table>
        <hr class="line-basic">
        <br>
        <!--crud 등록,수정칸-->
        <table>
            <tr>
                <td class="colText">사원명(사원번호)</td>
                <td class="colData"><input type="text"></td>
                <td class="colText">사업자등록번호</td>
                <td class="colData"><input type="text"></td>
                <td class="colText">등록일시</td>
                <td class="colData"></td>
            </tr>
            <tr>
                <td class="colText">프로젝트명</td>
                <td class="colData"><input type="text"></td>
                <td class="colText">현황분류코드</td>
                <td class="colData">
                    <select class="dropList">
                        <option>-</option>
                        <option>잠재</option>
                        <option>수주</option>
                        <option>진행</option>
                        <option>실패</option>
                    </select>
                </td>
                <td class="colText">가능성</td>
                <td class="colData">
                    <select class="dropList">
                        <option>%</option>
                        <option>10%</option>
                        <option>20%</option>
                        <option>30%</option>
                        <option>40%</option>
                        <option>50%</option>
                        <option>60%</option>
                        <option>70%</option>
                        <option>80%</option>
                        <option>90%</option>
                        <option>100%</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="colText">장비번호</td>
                <td class="colData"></td>
                <td class="colText">예상종료일자</td>
                <td class="colData"><input class="calendar" type="date"></td>
                <td class="colText">완료일자</td>
                <td class="colData"><input class="calendar" type="date"></td>
            </tr>
            <tr>
                <td class="colText">계약금액</td>
                <td class="colData"><input type="text"></td>
                <td class="colText">계약금액($)</td>
                <td class="colData"><input type="text"></td>
                <td class="colText">예상금액</td>
                <td class="colData"><input type="text"></td>
            </tr>
            <tr>
                <td class="colText">사유</td>
                <td class="colData" colspan="6"><input type="text"></td>
            </tr>
            <tr>
                <td class="colText">비고</td>
                <td class="colData" colspan="6"></td>
            </tr>
        </table>
    </div>
</template>

<script>
export default {
    data() {
        return {
            list: {}
        }
    },
    mounted() {    
        this.GetOppoList();
        // this.paginavigation();
    },
    methods: {     
        GetOppoList() {
            this.$axios.get(this.$serverUrl + "/oppo/list",
            this.requestBody
            ).then((res) => {    
                console.log(res)

                this.list = res.data
                console.log(this.list)
            }).catch((err) => {
            if (err.message.indexOf('Network Error') > -1) {
                alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
                }
            })
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
.oppo_header {
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