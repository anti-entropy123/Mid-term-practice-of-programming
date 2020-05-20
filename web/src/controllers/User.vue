<template lang="pug">
  .body-container
    el-row(:gutter="10")
    el-col(:span="6")
      el-card.card-container.block-need-center(shadow="never")
        div(v-if="user.id")
          img.avatar(src="/static/avatar.png")
        div(v-else)
          img.avatar(src="/static/avatar.png" style="cursor: pointer" @click="tologin()")
        div(v-if="user.id")
          el-form(:model="user")
            el-form-item.light-bottom(label="姓名", label-width="50px")
              span {{user.name}}
            el-form-item.light-bottom(label="职位", label-width="50px")
              span {{user.title}}
            el-form-item.light-bottom(label="状态", label-width="50px")
              span {{user.status}}
        div(v-else)
          .user-information-block 未登录
      el-card.card-container.block-need-center(shadow="never")
        span 剩余假期
        div(v-if="leave")
          el-form(:model="leave")
            el-form-item.light-bottom(label="年假", label-width="70px")
              span {{leave.annualLeave}}
            el-form-item.light-bottom(label="探亲假", label-width="70px")
              span {{leave.homeLeave}}
            <!--el-form-item.light-bottom(label="假", label-width="70px")-->
              <!--span {{leave.maternityLeave}}-->
        div(v-else)
          .user-information-block 暂无数据
</template>

<script>
    export default {
      name: "User",
      data () {
        return {
          user: {
            id:100,
            name:'鲍子龙',
            title:'经理',
            status:'外出'
          },
          leave:{
            annualLeave: 7,
            maternityLeave:7,
            homeLeave:7,
          }
        }
      }
    }
</script>

<style scoped>
  .body-container {
    max-width: 900px;
    margin: 0 auto;
  }
  .block-need-center {
    text-align: center;
  }
  .avatar {
    height: 100px;
    width: 100px;
    margin-bottom: 10px;
    border-radius: 50%;
  }
  .card-container {
    background-color: #fff;
    margin: 10px 0px;
    border-radius: 5px;
  }
  .user-information-block {
    text-align: center;
    margin: 5px 0;
    font-size: 16px;
    font-weight: 400;
  }
  .remain {
    padding: 10px 0 0 13px;
    line-height: 23px;
  }
  .light-bottom{
    margin-bottom:0;
  }
</style>
