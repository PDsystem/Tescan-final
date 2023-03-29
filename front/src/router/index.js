import { createRouter, createWebHistory } from 'vue-router'
import PageHome from '@/views/PageHome.vue'
import BoardList from '@/views/board/BoardList.vue'
import BoardDetail from '@/views/board/BoardDetail.vue'
import BoardWrite from '@/views/board/BoardWrite.vue'
import SalesOppo from '@/views/salesOppo/SalesOppo.vue'
import SalesList from '@/views/sales/SalesList.vue'
import EmpForm from '@/views/ts_emp/EmpForm.vue'
import CustomerList from '@/views/customer/CustomerList.vue'
import CustomerDetail from '@/views/customer/CustomerDetail.vue'
import CustomerWrite from '@/views/customer/CustomerWrite.vue'
import ClientList from '@/views/client/ClientList.vue'
import ClientDetail from '@/views/client/ClientDetail.vue'
import ClientWrite from '@/views/client/ClientWrite.vue'
import codeMaster from '@/views/codeMaster/codeMaster.vue'
import askBoard from '@/views/askBoard/askBoardList.vue'
import RestaurantList from '@/views/rest/RestaurantList.vue'
import RestRandom from '@/views/rest/RestRandom.vue'

const routes = [
  {
    path: '/',
    name: 'PageHome',
    component: PageHome
  },
  // {
  //   path: '/about',
  //   name: 'PageAbout',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/PageAbout.vue')
  // },
  {
    path: '/board/list',
    name: 'BoardList',
    component: BoardList
  },
  {
    path: '/board/detail',
    name: 'BoardDetail',
    component: BoardDetail
  },
  {
    path: '/board/write',
    name: 'BoardWrite',
    component: BoardWrite,
    meta: { hideeHeader: true, hideFooter: true},
    
  },
  {
    path: '/emp',
    name: 'EmpForm',
    component: EmpForm
  },
  //영업기회
  {
    path: '/SalesOppo/Oppo',
    name: 'SalesOppo',
    component: SalesOppo
  },
  //영업기회
  {
    path: '/Sales/list',
    name: 'SalesList',
    component: SalesList
  },
  {
    path: '/customer/detail',
    name: 'CustomerDetail',
    component: CustomerDetail
  },
  {
    path: '/customer/list',
    name: 'CustomerList',
    component: CustomerList
  },
  {
    path: '/customer/write',
    name: 'CustomerWrite',
    component: CustomerWrite
  },
  {
    path: '/client/list',
    name: 'ClientList',
    component: ClientList
  },
  {
    path: '/client/write',
    name: 'ClientWrite',
    component: ClientWrite
  },
  {
    path: '/client/detail',
    name: 'ClientDetail',
    component: ClientDetail
  },
  {
    path: '/restaurant/list',
    name: 'RestaurantList',
    component: RestaurantList
  },
  {
    path: '/restaurant/random',
    name: 'RestRandom',
    component: RestRandom
  },
  {
    path: '/codeMaster/codeMaster',
    name: 'codeMaster',
    component: codeMaster
  },
  {
      // 게시판
   path: '/askBoard/list',
   name: 'askBoard',
   component: askBoard
 },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router