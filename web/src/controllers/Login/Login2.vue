<template>
    <div>
      <div class="container">
        <div class = "wrap">
            <div class = "header">
              <img src="/static/yjn.jpg"  style = "width: 200px;height: 200px;margin-left:100px;"class ="img">
              <div class="desc">🐌一个基于 Spring-Boot + Vue 前后端分离项目的简单实现</div>
            </div>
          <div>
            <el-radio-group style = "margin-top: 30px;margin-bottom: 10px;margin-left: 105px" v-model="radio" class ="radio" >
              <el-radio-button   label="账号密码登陆"></el-radio-button>
              <el-radio-button   label="快速登陆"></el-radio-button>
            </el-radio-group>
          </div>
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

            <div style = "margin-top: -10px;margin-bottom: 5px">
              <el-checkbox v-model="checked">自动登陆</el-checkbox>
              <el-link style = "margin-left: 210px" type="primary" :underline="false">忘记密码</el-link>
            </div>
            <el-button
              class="button"
              style = "width: 100%"
              type="primary"
              @click="Login"
              @keydown.enter="Login"
              round>登陆</el-button>
          </div>
      </div>
    </div>
</template>

<script>
    export default {
      name: "Login2",
      data () {
          return {
            inputPassword: '',
            inputUser: '',
            checked: false,
            isLogin: true,
            radio: '账号密码登陆'
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
.container {
  display: flex;
  align-items: center;
  justify-content: center;
}
.desc{
  margin-top:25px;
  margin-left:50px;
  font-size: 10px;
}
.input{
  margin-bottom:20px;
}

</style>
