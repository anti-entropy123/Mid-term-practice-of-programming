import Vue from 'vue'
import Router from 'vue-router'
import home from '../controllers/Home.vue'

Vue.use(Router)
window.Bus = new Vue()

export default new Router({
  mode: 'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      component: home,
      meta: {}
    }
  ]
})
