<template>
<div class="body-container">
  <div class="login_card">
    <div class="title">考勤系统登陆</div>
    <img src="/static/avatar.png" class ="img">
    <el-input
      class="input"
      placeholder="请输入账号"
      v-model="inputUser"
      clearable>
    </el-input>
    <el-input
      placeholder="请输入密码"
      v-model="inputPassword"
      show-password
      class="input">
    </el-input>
    <el-button
      class="button"
      type="primary"
      @click="Login"
      @keydown.enter="Login"
      round>登陆</el-button>
  </div>
</div>
</template>

<script>
    export default {
      name: "Login",
      data() {
        return {
          inputUser: '',
          inputPassword: '',
          isLogin: true
        }
      },
      methods: {
        Login (){
          if(this.isLogin){
            alert('user:'+this.inputUser+"\n" +
              "Password"+this.inputPassword)
            this.$router.replace('/')
          }
          else{
            alter('密码错误')
          }
        }
      }
    }
</script>

<style scoped>
.body-container {
  display: flex;
  align-items: center;
  justify-content: center;
}
.login_card {
  height: 600px;
  width: 500px;
  background: #9ee7dc;
}
.title{
  font-size: 20px;
  font-weight: 500;
  padding: 0 15px;
  margin: 20px 0;
  border-left: 8px solid #00a0e9;
  clear:both;
}
.input{
  padding-top: 10px;
  padding-left: 25%;
  width: 50%;
}
.img {
    margin-left:150px;
    padding-bottom: 10px;
    height: 200px;
    width:  200px;
  }
.button{
  margin-top: 15px;
  margin-left: 25%;
  width: 50%;
}
</style>
