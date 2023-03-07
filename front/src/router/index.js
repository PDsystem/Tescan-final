import { createRouter, createWebHistory } from 'vue-router'
import PageHome from '@/views/PageHome.vue'
import BoardList from '@/views/board/BoardList.vue'
import BoardDetail from '@/views/board/BoardDetail.vue'
import BoardWrite from '@/views/board/BoardWrite.vue'
<<<<<<< HEAD
import EmpForm from '@/views/emp/EmpForm.vue'
=======
import SalesOppo from '@/views/salesOppo/SalesOppo.vue'
>>>>>>> f3d4932c4dcdc4d253c699c6e56baa90baa8cd26


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
    component: BoardWrite
  },
<<<<<<< HEAD
  {
    path: '/emp',
    name: 'EmpForm',
    component: EmpForm
}

=======
  //영업기회
  {
    path: '/SalesOppo/Oppo',
    name: 'SalesOppo',
    component: SalesOppo
  },
>>>>>>> f3d4932c4dcdc4d253c699c6e56baa90baa8cd26
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router