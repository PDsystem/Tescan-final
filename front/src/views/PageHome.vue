<template> 
    <div class="home">
         <!-- <img alt="Vue logo" src="../assets/crm.png" style="
    max-height: 450px;">  -->
         <HelloWorld msg="Welcome to Your Vue.js App"/> 
    </div> 
    </template>
    
    <script> 
    // @ is an alias to /src 
    import HelloWorld from '@/views/page/login.vue' 
    export default { 
        name: 'PageHome',
         components: { 
            HelloWorld 
        }
         }
     </script>