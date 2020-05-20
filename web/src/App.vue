<style scoped>
  .header {
    top: 0;
    width:100%;
    background-color: #fff;
    position: fixed;
    z-index: 9;
    box-shadow: 0 2px 6px rgba(173, 172, 172, 0.363);
  }
  .header-box {
    margin: 0 auto;
    width: 900px;
  }
  .header-title {
    font-size: 20px;
    display: inline-block;
    vertical-align: top;
  }
  .logo-container {
    cursor: pointer;
  }
  .header-item {
    font-size: 16px;
    padding: 4px 20px 1px;
    display: inline-block;
    color: #000;
    cursor: pointer;
    text-align: center;
    line-height: 55px;
  }
  .active, .header-item:hover, .header-item:active {
    color: #00a1e9;
  }
  .main-container {
    background-color: #EFF3F5;
    margin: 0 auto;
    padding-top: 70px;
    min-height: calc(100vh - 70px);
  }
</style>

<template lang="pug">
div
  .header
    el-row.header-box(type="flex", justify="space-between")
      div.logo-container(@click="$router.push(`/`)")
        h2.header-title 考勤系统
      router-link(to="/", exact, tag="li", class="header-item") 系统首页
      router-link(to="/user", exact, tag="li", class="header-item") 个人信息
      router-link(to="/apply", exact, tag="li", class="header-item") 我的申请
      router-link(to="/work", exact, tag="li", class="header-item") 工作业务
      router-link(to="/login", exact, tag="button", class="header-item") 登陆
      el-button(type="text", @click="userLogin") 登录
  .main-container
    router-view(v-if="isRouterAlive")
</template>

<script>

export default {
  data () {
    return {
      isRouterAlive: true,
    }
  },
  watch: {
  },
  created () {

  },
  methods: {
    userLogin(){
      this.$router.push('/login')
    }
  }
}
</script>
