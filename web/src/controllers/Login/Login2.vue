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
        <div class="footer">
          <div class="subnav">
            |  <a href="#">组长</a>:
            <a href="http://tjuyjn.top/">尤克里里</a> |
            <a href="#">组员</a> :
            <a href="https://r1895.github.io/">任帅</a>
            <a href="#">满可爱</a>
            <a href="#">鲍富</a> |
          </div>
          <p>Copyright @2020 KaoQin. | 码出世界出品</p>
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
  flex-flow: column;
  justify-content: center;
  background-color: rgba(109, 206, 231, 0.4);
}
.desc{
  margin-top:25px;
  margin-left:50px;
  font-size: 10px;
}
.input{
  margin-bottom:20px;
}
.footer {
  height: 200px;

  overflow: hidden;
}

.subnav {
  text-align: center;
  margin-top: 75px;
  color: #f0f1f1;
}

.subnav a {
  color: #f0f1f1;
  text-decoration: none;
  padding: 0 5px;
  font-size: 14px;
}

.subnav a:hover {
  color: #2288f6;
  text-decoration: underline;
}

.footer p {
  color: #888888;
  text-align: center;
  margin-top: 30px;
}
</style>
