<template>
    <div class="form-signin w-100 m-auto">
   <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
   <div class="form-floating">
     <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com"
     @keyup.enter="submit()" v-model="email">
     <label for="floatingInput">Email address</label>
   </div>
   <div class="form-floating">
     <input type="password" class="form-control" id="floatingPassword" placeholder="Password"  @keyup.enter="submit()" v-model="password">
     <label for="floatingPassword">Password</label>
   </div>
   <div class="checkbox mb-3">
     <label>
       <input type="checkbox" value="remember-me"> Remember me
     </label>
   </div>
   <button class="w-100 btn btn-lg btn-primary" @click="submit()" >Sign in</button>
   <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
 </div>
 </template>
 <script>
 //import {reactive} from "vue";
 //import axios from "axios";
 export default {
   name: 'login',
   components:{},
   setup() {
     const state = {
     form: {
       email: "",
       password: ""
     }
   }
   // const submit = () => {
   //   axios.post(this.$serverUrl+"/api/account/login", state.form).then((res) => {
   //     console.log(res)
   //     //window.alert("로그인하였습니다.");
   //   }).catch(() => {
   //     //window.alert("로그인 정보가 존재하지 않습니다..");
   //   });
   // }
  
 
   return {state,userInfo:{
    
   }}
   },
   mounted() {
   //this.hi();
 },
 methods: {
   hi(){
       this.$axios.get(this.$serverUrl+"/account/login").then((res) => {
       console.log(res)
       //window.alert("로그인하였습니다.");
     }).catch(() => {
       //window.alert("로그인 정보가 존재하지 않습니다..");
     });
   },
 submit () {
   this.userInfo = { // 데이터 전송
     contentId:this.email,
     contentPw:this.password
     }
     this.$axios.post(this.$serverUrl+"/api/account/login",this.userInfo).then((res) => {
       console.log(res);
       if(res.data!="0"){
        window.alert("로그인하였습니다.");
        this.$router.push({path:"/board/list"});
       }else{
        window.alert("로그인 정보가 존재하지 않습니다..");
       }
      
     }).catch(() => {
       window.alert("로그인 정보가 존재하지 않습니다..");
     });
   }
 }

 }
 </script>