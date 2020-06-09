import Vue from 'vue'
import Router from 'vue-router'
import home from '../controllers/Home.vue'
import user from '../controllers/User.vue'
import work from '../controllers/Work.vue'
import apply from '../controllers/Apply.vue'
import login from '../controllers/Login/Login2.vue'
Vue.use(Router)
window.Bus = new Vue()

export default new Router({
  mode: 'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      component: home,
      meta: { title: '首页'}
    },
    {
      path: '/user',
      component: user,
      meta: { title: '个人信息' }
    },
    {
      path: '/work',
      component: work,
      meta: { title: '工作业务' }
    },
    {
      path: '/apply',
      component: apply,
      meta: { title: '我的申请' }
    },
    {
      path: '/login',
      component: login,
      meta: { title: '登陆' }
    }
  ]
})
