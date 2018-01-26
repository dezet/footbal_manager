import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import Panel from '@/components/admin/Panel'
import Players from '@/components/Players'
import Login from '@/components/authentication/Login'
import Signup from '@/components/authentication/Signup'
import SeasonsPage from '@/components/admin/season/SeasonsPage'
import SeasonRapport from '@/components/admin/season/SeasonRapport'
import SeasonPage from '@/components/admin/season/SeasonPage'
import LeaguePage from '@/components/admin/leagues/LeaguePage'
import TeamPage from '@/components/admin/teams/TeamPage'

import auth from '../authentication'

Vue.use(Router)

const router = new Router({
  auth: auth,
  mode: 'history',
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
      path: '/panel/seasons/:id/rapport',
      name: 'SeasonRapport',
      component: SeasonRapport,
      meta: {requiresAuth: false}
    },
    {
      path: '/panel/leagues',
      name: 'LeaguePage',
      component: LeaguePage,
      meta: {requiresAuth: false}
    },
    {
      path: '/panel/teams',
      name: 'TeamPage',
      component: TeamPage,
      meta: {requiresAuth: false}
    },
    {
      path: '/players',
      name: 'Players',
      component: Players,
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
      next()
    } else {
      next('/')
    }
  } else {
    next()
  }
})

export default router
