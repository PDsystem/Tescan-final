<template>
  <header>
    <!-- Homebtn -->
    <div class="homeBtn"><router-link to="/"><img alt="Tescan logo" src="../assets/tescanK_menu.jpg"></router-link></div>
    <!-- navBar -->
    <div class="navbar-body">
      <div class="container cn_headerline">
        <div class="collapse navbar-collapse navbar-responsive-collapse">
          <ui id="nav">
            <li><a href="/">Home</a>
              <ul>
                <li><router-link to="/board/list">board</router-link></li>
                <li><router-link to="/attitudeBook/list">attitude book list</router-link></li>
              </ul>
            </li>
            <li><a href="/codeMaster/codeMaster">공통기능</a>
              <ul>
                <li><a href="/codeMaster/codeMaster">공통코드</a></li>
                <li><a href="/">공통코드상세</a></li>
              </ul>
            </li>
            <li><a href="/emp">내부관리</a>
              <ul>
                <li><a href="/emp">사원등록</a></li>
                <li><a href="/">장비관리</a></li>
              </ul>
            </li>
            <li><a href="/customer/list">거래처 관리</a>
              <ul>
                <li><a href="/customer/write">거래처 등록</a></li>
                <li><a href="/customer/list">거래처 현황</a></li>
              </ul>
            </li>
            <li><a href="/salesOppo/oppo">영업부관리</a>
              <ul>
                <li><a href="/salesOppo/oppo">영업기회</a></li>
                <li><a href="/sales/list">영업방문</a></li>
                <li><a href="">업무보고</a></li>
              </ul>
            </li>
            <li><a href="/">기술부관리</a>
              <ul>
                <li><a href="/">업무내역관리</a></li>
                <li><a href="/">거래처별 업무</a></li>
                <li><a href="/">실적보고</a></li>
                <li><a href="/">업무보고</a></li>
              </ul>
            </li>
            <li><a href="/askBoard/list">게시판</a>
              <ul>
                <li><a href="/askBoard/list">질문게시판</a></li>
              </ul>
            </li>
          </ui>
        </div>
      </div>
    </div> 
  </header>
  <br>
</template>

<script>
export default {

}
</script>

<style>
.homeBtn {
  padding: 20px;
}

.navbar-body{
  position: relative;
  padding-left: 0px;
}
.container {
  position: relative;
  height: 60px;
  padding-top: 30px;
  background: #f0f0f0;
}
#nav {
  list-style: none;
  height: 0px;
}

#nav li {
  float: left;
  position: relative;
  margin: 0px 40px;
}

#nav li a {
  display: block;
  padding: 0px 20px;
  margin: 0px;
  font-weight: 600;
  font-size: large;
  color: #0078b3;
  text-decoration: none;
}

#nav ul {
  margin: 0px 10px;
  padding: 0;
  list-style: none;
  position: absolute;
  left: 0;
  top: 55px;
  width: 200px;
  opacity: 0; /*네비바 비활성화*/
}

#nav li:hover ul{
  opacity: 1; /*네비바 활성화*/
}

#nav ul li {
  float: center;
  margin: 0px;
  padding: 0px;
}

#nav ul li a {
  font-weight: 500;
  color: #0078b3;
  background: #f0f0f0;
  border: 2px solid #0078b3;
}

#nav ul li a:hover {
  background: #0078b3;
  color: #f0f0f0;
}
</style>