<template>
    <div>
      <div class="container">
        <el-card class = "wrap">
          <img src="/static/avatar.png" class ="img">
          <el-radio-group v-model="radio" class ="radio" >
            <el-radio-button   label="账号密码登陆"></el-radio-button>
          </el-radio-group>
          <div>
            <el-input placeholder="请输入账号" v-model="loginFrom.username" class="input">
              <i slot="prefix" style="display: flex;align-items: center;margin-top:8px;margin-left:4px;">
                <img style="width:16px;height:16px" src="/static/user.png" alt/>
              </i>
            </el-input>
            <el-input placeholder="请输入密码" v-model="loginFrom.password" show-password class="input" autocomplete="new-password">
              <i slot="prefix" style="display: flex;align-items: center;margin-top:8px;margin-left:4px;">
                <img style="width:16px;height:16px" src="/static/password.png" alt/>
              </i>
            </el-input>

            <!--<div style = "margin-top: -10px;margin-bottom: 5px">-->
              <!--<el-checkbox v-model="checked">自动登陆</el-checkbox>-->
              <!--<el-link style = "margin-left: 210px" type="primary" :underline="false">忘记密码</el-link>-->
            <!--</div>-->
            <el-button class="button" type="primary" @click="Login" @keydown.enter="Login">登录</el-button>
          </div>
        </el-card>
        <!--<div class="footer">-->
          <!--<div class="subnav">-->
            <!--|  <a href="#">组长</a>:-->
            <!--<a href="http://tjuyjn.top/">尤克里里</a> |-->
            <!--<a href="#">组员</a> :-->
            <!--<a href="https://r1895.github.io/">任帅</a>-->
            <!--<a href="#">满可爱</a>-->
            <!--<a href="#">鲍富</a> |-->
          <!--</div>-->
          <!--<p> @2020 KaoQin. </p>-->
        <!--</div>-->
      </div>
    </div>
</template>
<script>
    export default {
      name: "Login",
      data () {
          return {
            loginFrom: {
              password: '',
              username: ''
            },
            radio: '账号密码登陆'
          }
      },
      methods: {
        Login (){
          let _this = this;
          if (this.loginFrom.username==='' || this.loginFrom.password===''){
            this.$message.warning('账号或密码不能为空')
          } else {
            this.$http.post(`/api/login/`,{
              username:this.loginFrom.username,
              password:this.loginFrom.password
            }).then(res =>{
              console.log(res);
              if (res.response && res.response.status!=200){
                let message = res.response.data.message
                _this.$message.error(message)
              } else {
                sessionStorage.setItem('token', res.data.token)
                _this.$message.success("登录成功，请手动刷新")
                let a = this.$route.params.id
                // window.location = a
                _this.$router.push(a)
              }
            }).catch(err=>{
              _this.$message.error('系统出错')
            })
          }
        }
      }
    }
</script>

<style scoped>
.container {
  max-width: 700px;
  margin: 0 auto;
}
.wrap{
  margin:20px auto;
  width:400px;
}
.desc{
  margin-top:25px;
  margin-left:50px;
  font-size: 10px;
}
.input{
  margin-bottom:20px;
}
.button{
  display: flex;
  margin:0 auto;
}
.img{
  width: 200px;
  height: 200px;
  margin:0 auto;
  display: flex;
  border-radius:100px;
}
.radio{
  width: 100px;
  margin:20px auto;
  display: flex;
}
.footer {
  height: 200px;
  overflow: hidden;
}

.subnav {
  text-align: center;
  margin-top: 75px;
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
