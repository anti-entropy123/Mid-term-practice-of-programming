<template lang="pug">
.body-container
  el-row.box(:gutter="10")
    el-col(:span="6")
      el-card.card-container.block-need-center(shadow="never")
        div(v-if="user.userId")
          img.avatar(src="/static/avatar.png")
        div(v-else)
          img.avatar(src="/static/avatar.png" style="cursor: pointer" @click="tologin()")
        div(v-if="user.userId")
          el-form(:model="user")
            el-form-item.light-bottom(label="姓名", label-width="50px" style="margin-bottom:0")
              span {{user.username}}
            el-form-item.light-bottom(label="职位", label-width="50px" style="margin-bottom:0")
              span {{user.title}}
            el-form-item.light-bottom(label="状态", label-width="50px" style="margin-bottom:0")
              span {{user.status}}
        div(v-else)
          .user-information-block 未登录
        el-button.text-button(v-if="user.userId&&!over" type="text" @click="overtime") 申请加班
      el-card.card-container.block-need-center(shadow="never")
        div.little-bottom 剩余假期
        div(v-if="user.userId")
          el-form(:model="leave")
            el-form-item.light-bottom(label="年假", label-width="70px" style="margin-bottom:0")
              span {{leave.annualLeave}}
            el-form-item.light-bottom(label="探亲假", label-width="70px" style="margin-bottom:0")
              span {{leave.homeLeave}}
            el-form-item.light-bottom(label="产假", label-width="70px" style="margin-bottom:0")
              span {{leave.maternityLeave}}
        div(v-else)
          .user-information-block 暂无数据
    el-col(:span="18")
      el-card.card-container(shadow="never")
        .card-title 考勤表
        el-table.table(:data="records.slice((currentPage-1)*pagesize,currentPage*pagesize)", border)
          el-table-column(align="center", prop="date", label="日期")
          el-table-column(align="center", prop="status", label="状态")
        el-pagination.manage-pagination(@current-change="handleCurrentChange",
          :current-page="currentPage",
          layout="prev, pager, next",
          :total="records.length",
          :page-size="pagesize")

</template>

<script>
    export default {
      name: "User",
      data () {
        return {
          pagesize:7,
          currentPage: 1,
          currentTotal: 0,
          user: {},
          leave:{
            annualLeave: 0,
            maternityLeave:0,
            homeLeave:0,
          },
          over:false,
          records:[]
        }
      },
      created () {
        this.userinfo()
        this.getRecords()
        this.getHoliday()
        this.getStatus()
      },
      methods: {
        handleCurrentChange(currentPage) {
          this.currentPage = currentPage
        },
        getRecords(){
          let _this = this
          if (this.user.userId) {
            this.$http.get(`api/record?id=${this.user.userId}`).then(res => {
              console.log(res)
              if (res.response && res.response.status!=200){
                let message = res.response.data.message
                _this.$message.error(message)
              } else {
                if (res.data.records.length != 0) {
                  this.records = res.data.records
                }
              }
            }).catch(err=>{
              _this.$message.error('系统出错')
            })
          }
        },
        userinfo(){
          if (sessionStorage.getItem('user')){
            this.user = JSON.parse(sessionStorage.getItem('user'))
          } else {
            this.user = {}
          }
        },
        getHoliday(){
          let _this = this
          if (this.user.userId){
            this.$http.get(`/api/user/Holidaybalance?id=${this.user.userId}`).then(res => {
              console.log(res)
              if (res.response && res.response.status!=200){
                let message = res.response.data.message
                _this.$message.error(message)
              } else {
                this.leave = res.data
              }
            }).catch(err=>{
              _this.$message.error('系统出错')
            })
          }
        },
        getStatus(){
          let _this = this
          if (this.user.userId) {
            this.$http.get(`/api/user/status?id=${this.user.userId}`).then(res => {
              console.log(res)
              if (res.response && res.response.status!=200){
                let message = res.response.data.message
                _this.$message.error(message)
              } else {
                this.user.status = res.data.status
                if (res.data.status == 'overtime') {
                  this.over = true
                }
              }
            }).catch(err=>{
              _this.$message.error('系统出错')
            })
          }
        },
        overtime(){
          let _this = this
          this.$confirm('你将申请加班, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            _this.$http.post(`/api/record/overtime`,{
              id:this.user.userId,
              datetime:Date.parse(new Date())
            }).then(res => {
              console.log(res)
              if (res.response && res.response.status!=200){
                let message = res.response.data.message
                _this.$message.error(message)
              } else {
                _this.$message.success('加班成功')
                _this.getStatus()
              }
            }).catch(err=>{
              _this.$message.error('系统出错')
            })
          }).catch(() => {
            console.log('取消加班')
          });
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
  .little-bottom{
    margin-bottom:5px;
  }
  .card-title {
    display: inline;
    font-size: 22px;
    letter-spacing: 1.5px;
    font-weight: 400;
  }
  .table {
    margin: 10px auto;
  }
  .box{
    margin: 10px auto;
  }
</style>
