// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Vuex from 'vuex'
import VeeValidate from 'vee-validate'
import Datatable from 'vue2-datatable-component'

Vue.config.productionTip = false

const vueConfig = require('vue-config')
const configs = {
  API: 'http://localhost:8124/'
}

Vue.use(vueConfig, configs)
Vue.use(Vuex)
Vue.use(VeeValidate)
Vue.use(Datatable)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
