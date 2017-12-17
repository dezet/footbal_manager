import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import Players from '@/components/Players'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/players',
      name: 'Players',
      component: Players
    }
  ]
})
