<template lang="pug">
.body-container
  .title
   span 全体员工一览
  .employee-search
    .employee-input
      el-input(placeholder="请输入工号", v-model="searchInput",  @keyup.native.enter="getmember")
    el-button(type="primary", icon="el-icon-search", @click="getmember") 查找员工
  .employee-selector
    el-select(v-model="employee_type", placeholder="--未选择--")
      el-option(label="全部", value="0" @click.native='getmembers')
      el-option(label="已请假", value="1" @click.native='getleave')
      el-option(label="已外出", value="2" @click.native='getout')
      el-option(label="正在加班", value="3" @click.native='getover')
  el-pagination.manage-pagination(@current-change="handleCurrentChange",
      :current-page="currentPage",
      layout="prev, pager, next",
      :total="members.length",
      :page-size="pagesize")
  el-table.table(:data="members.slice((currentPage-1)*pagesize,currentPage*pagesize)", border)
    el-table-column(align="center", prop="name", label="姓名")
    el-table-column(align="center", prop="title", label="职位")
    el-table-column(align="center", prop="status", label="状态")
</template>

<script>
    export default {
      name: 'Home',
      data() {
        return {
          user:{},
          searchInput: '',
          employee_type: '0',
          pagesize: 8,
          currentPage: 1,
          currentTotal: 0,
          members: []
        }
      },
      created () {
        this.userinfo()
        if (this.user.userId) {
          this.getmembers();
        }
      },
      methods: {
        handleCurrentChange(currentPage) {
          this.currentPage = currentPage
        },
        getmember(){
          let _this = this
          if (this.searchInput == '' ){
            this.$message.warning('输入为空')
          } else if (!this.user.userId){
            this.$message.warning('请先登录')
          } else {
              this.$http.get(`/api/user/employees/${this.searchInput}`).then(res => {
                console.log(res)
                if (res.response && res.response.status!=200){
                  let message = res.response.data.message
                  _this.$message.error(message)
                } else {
                  this.members = []
                  this.members[0] = res.data
                  this.employee_type = ''
                }
              }).catch(err=>{
                _this.$message.error('系统出错')
              })
          }
        },
        getmembers(){
          this.searchInput=''
          let _this = this
          this.$http.get(`/api/user/employees`).then(res => {
            console.log(res)
            if (res.response && res.response.status!=200){
              let message = res.response.data.message
              _this.$message.error(message)
            } else {
              this.members = res.data.members
            }
          }).catch(err=>{
            _this.$message.error('系统出错')
          })
        },
        getleave(){
          this.searchInput=''
          let _this = this
          this.$http.get(`/api/manager/all/leaving-members`).then(res => {
            console.log(res)
            if (res.response && res.response.status!=200){
              let message = res.response.data.message
              _this.$message.error(message)
            } else {
              this.members = res.data.leaveMembers
            }
          }).catch(err=>{
            _this.$message.error('系统出错')
          })
        },
        getout(){
          this.searchInput=''
          let _this = this
          this.$http.get(`/api/manager/all/outing-members`).then(res => {
            console.log(res)
            if (res.response && res.response.status!=200){
              let message = res.response.data.message
              _this.$message.error(message)
            } else {
              this.members = res.data.outMembers
            }
          }).catch(err=>{
            _this.$message.error('系统出错')
          })
        },
        getover(){
          this.searchInput=''
          let _this = this
          this.$http.get(`/api/manager/all/overtime-members`).then(res => {
            console.log(res)
            if (res.response && res.response.status!=200){
              let message = res.response.data.message
              _this.$message.error(message)
            } else {
              this.members = res.data.overtimeMembers
            }
          }).catch(err=>{
            _this.$message.error('系统出错')
          })
        },
        userinfo(){
          if (sessionStorage.getItem('user')){
            this.user = JSON.parse(sessionStorage.getItem('user'))
          } else {
            this.user = {}
          }
        },
      }
    }
</script>

<style scoped>
  .body-container {
    max-width: 900px;
    margin: 0 auto;
  }
  .title {
    font-size: 20px;
    font-weight: 500;
    padding: 0 15px;
    margin: 20px 0;
    border-left: 8px solid #00a0e9;
    clear:both;
  }
  .employee-search {
    margin: 20px 15px;
  }
  .employee-input {
    display: inline-block;
    width: 500px;
    vertical-align: top;
    margin-right: 20px;
    width: 465px;
  }
  .employee-selector {
    display: inline-block;
    margin-left:15px;
    margin-bottom: 8px;
  }
  .table {
    margin: 10px auto;
    width: 870px;
  }
  .manage-pagination {
    margin-right:15px;
    float: right;
  }
</style>
