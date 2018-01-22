import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import Panel from '@/components/admin/Panel'
import Players from '@/components/Players'
import Login from '@/components/authentication/Login'
import Signup from '@/components/authentication/Signup'
import AddPlayers from '@/components/AddPlayers'
import SeasonsPage from '@/components/admin/season/SeasonsPage'
import SeasonPage from '@/components/admin/season/SeasonPage'
import GenerateTimetable from '@/components/GenerateTimetable'

import auth from '../authentication'

Vue.use(Router)

const router = new Router({
  auth: auth,
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main,
      meta: {requiresAuth: false}
    },
    {
      path: '/panel',
      name: 'Panel',
      component: Panel,
      meta: {requiresAuth: false}
    },
    {
      path: '/panel/seasons',
      name: 'SeasonsPage',
      component: SeasonsPage,
      meta: {requiresAuth: false}
    },
    {
      path: '/panel/seasons/:id',
      name: 'SeasonPage',
      component: SeasonPage,
      meta: {requiresAuth: false}
    },
    {
      path: '/players',
      name: 'Players',
      component: Players,
      meta: {requiresAuth: true}
    },
    {
      path: '/timetable',
      name: 'Timetable',
      component: GenerateTimetable,
      meta: {requiresAuth: true}
    },
    {
      path: '/newplayer',
      name: 'new players',
      component: AddPlayers,
      meta: {requiresAuth: true}
    },
    {
      path: '/login',
      component: Login,
      meta: {requiresAuth: false}
    },
    {
      path: '/signup',
      component: Signup,
      meta: {requiresAuth: false}
    }
  ]
})
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    if (auth.checkAuth()) {
      next('/panel')
    } else {
      next('/')
    }
  } else {
    next()
  }
})

export default router
