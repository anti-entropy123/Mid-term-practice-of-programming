import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import ElementUI from 'element-ui'
import './theme/index.css'
import App from './App.vue'
import axios from 'axios'
import qs from 'qs'

Vue.use(VueRouter)

Vue.prototype.$http = axios
axios.defaults.withCredentials = true
axios.defaults.baseURL = 'http://188.131.227.20:8080' // 关键步骤–填写后台请求统一的地址
Vue.config.productionTip = false

Vue.use(ElementUI, { size: 'small', zIndex: 3000 })

let vm = new Vue()
axios.interceptors.request.use((config) => {
  const USER_TOKEN = sessionStorage.getItem('token')
  //登录不需要传token,看一下自己登录的接口包含哪个特殊字段
  if(USER_TOKEN && !config.url.includes('login')){
    config.headers.Authorization = USER_TOKEN
  }
  return config
}, (error) => {
  return Promise.reject(error)
})

/* eslint-disable no-new */
vm = new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  render: h => h(App)
})
