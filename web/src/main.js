import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import ElementUI from 'element-ui'

import App from './App.vue'

import axios from 'axios'
import qs from 'qs'

Vue.use(VueRouter)

Vue.prototype.$http = axios
axios.defaults.withCredentials = true
axios.defaults.baseURL = '' // 关键步骤–填写后台请求统一的地址
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
// axios.defaults.headers.post['x-csrf-token'] = localStorage.getItem('token')
Vue.config.productionTip = false

Vue.use(ElementUI, { size: 'small', zIndex: 3000 })

let vm = new Vue()
axios.interceptors.request.use((config) => {
  if (config.method === 'post') {
    config.data = qs.stringify(config.data)
  }
  return config
}, (error) => {
  return Promise.reject(error)
})
axios.interceptors.response.use(
  res => {
    const headers = res.headers
    if (headers['content-type'] === 'application/octet-stream') {
      return res
    } else if (typeof res.data === 'string') {
      vm.$message.warning('请求异常！请稍后再试')
      res.processed = true
    } else if (res.status !== 200 && res.status < 300) {
      vm.$message.error('请求失败！请检查您的网络连接')
      res.processed = true
    } else {
      let data = res.data
      if (data.errno === 10051) {
        vm.$message.warning(data.errmsg)
        res.processed = true
      }
    }
    return res
  }, err => {
    vm.$message.warning('访问受限')
})

/* eslint-disable no-new */
vm = new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  render: h => h(App)
})
