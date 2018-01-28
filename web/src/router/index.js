import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import Panel from '@/components/admin/Panel'
import Login from '@/components/authentication/Login'
import Signup from '@/components/authentication/Signup'
import SeasonsPage from '@/components/admin/season/SeasonsPage'
import SeasonRapport from '@/components/admin/season/SeasonRapport'
import SeasonPage from '@/components/admin/season/SeasonPage'
import LeaguePage from '@/components/admin/leagues/LeaguePage'
import TeamPage from '@/components/admin/teams/TeamPage'
import MatchPage from '@/components/admin/matches/MatchPage'
import MatchesPage from '@/components/admin/matches/MatchesPage'

import Leagues from '@/components/player/Leagues'
import Matches from '@/components/player/Matches'
import Seasons from '@/components/player/Seasons'
import Teams from '@/components/player/Teams'
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
      meta: {requiresAuth: false, requiresAdmin: false}
    },
    {
      path: '/panel',
      name: 'Panel',
      component: Panel,
      meta: {requiresAuth: true, requiresAdmin: true}
    },
    {
      path: '/panel/seasons',
      name: 'SeasonsPage',
      component: SeasonsPage,
      meta: {requiresAuth: true, requiresAdmin: true}
    },
    {
      path: '/panel/seasons/:id',
      name: 'SeasonPage',
      component: SeasonPage,
      meta: {requiresAuth: true, requiresAdmin: true}
    },
    {
      path: '/panel/seasons/:id/rapport',
      name: 'SeasonRapport',
      component: SeasonRapport,
      meta: {requiresAuth: true, requiresAdmin: true}
    },
    {
      path: '/panel/matches/:id',
      name: 'MatchPage',
      component: MatchPage,
      meta: {requiresAuth: true, requiresAdmin: true}
    },
    {
      path: '/panel/matches',
      name: 'MatchesPage',
      component: MatchesPage,
      meta: {requiresAuth: true, requiresAdmin: true}
    },
    {
      path: '/panel/leagues',
      name: 'LeaguePage',
      component: LeaguePage,
      meta: {requiresAuth: true, requiresAdmin: true}
    },
    {
      path: '/panel/teams',
      name: 'TeamPage',
      component: TeamPage,
      meta: {requiresAuth: true, requiresAdmin: true}
    },
    {
      path: '/leagues',
      component: Leagues,
      meta: {requiresAuth: true, requiresAdmin: false}
    },
    {
      path: '/matches',
      component: Matches,
      meta: {requiresAuth: true, requiresAdmin: false}
    },
    {
      path: '/seasons',
      component: Seasons,
      meta: {requiresAuth: true, requiresAdmin: false}
    },
    {
      path: '/teams',
      component: Teams,
      meta: {requiresAuth: true, requiresAdmin: false}
    },
    {
      path: '/login',
      component: Login,
      meta: {requiresAuth: false, requiresAdmin: false}
    },
    {
      path: '/signup',
      component: Signup,
      meta: {requiresAuth: false, requiresAdmin: false}
    }
  ]
})
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    if (auth.checkAuth()) {
      if (to.meta.requiresAdmin) {
        if (auth.isAdmin()) {
          next()
        } else {
          next('/')
        }
      } else {
        next()
      }
    } else {
      next('/')
    }
  } else {
    next()
  }
})

export default router
