<template lang="pug">
.body-container
  .title
    span 我的申请
  el-card.apply-box(@click.native="addApply1")
    span 请假申请
  el-card.apply-box(@click.native="addApply2")
    span 外出申请
  div(style="clear:both")
  .title
    span 已有申请
  .apply-selector
      el-select(v-model="apply_type", placeholder="--未选择--")
        el-option(label="全部申请", value="2" @click.native='getApplication')
        el-option(label="请假申请", value="0" @click.native='getApplication')
        el-option(label="外出申请", value="1" @click.native='getApplication')
  el-table.table(:data="applications", border)
    el-table-column(align="center", prop="name", label="姓名", width="120")
    el-table-column(align="center", prop="startTime", label="起始时间", width="120")
    el-table-column(align="center", prop="endTime", label="终止时间", width="120")
    el-table-column(align="center", prop="type", label="类别",:formatter="typeformatter", width="120")
    el-table-column(align="center", prop="", label="状态",:formatter="statusformatter" width="120")
    el-table-column(align="center", label="操作")
      template(slot-scope="scope")
        el-button(type="text", @click="applyPage(scope.row)") 查看详情
        el-button(:disabled="scope.row.authority!=0" type="text", @click="updateApply(scope.row)") 修改申请

  //请假申请表
  el-dialog(title="请假申请", :visible.sync="EditDialogVisible1",width="600px")
    el-form(:model="apply")
      el-form-item(label="姓名", :label-width="formLabelWidth")
        el-input(v-model="user.username",:disabled="true")
      el-form-item(label="起止日期", :label-width="formLabelWidth")
        el-date-picker(v-model="apply.dateArr", type="daterange", range-separator="至", start-placeholder="开始日期", end-placeholder="结束日期", value-format="timestamp")
      el-form-item(label="请假类型", :label-width="formLabelWidth")
        el-select(v-model="apply.type", placeholder="--未选择--")
          el-option(label="年假", value="annualLeave")
          el-option(label="产假", value="maternityLeave")
          el-option(label="探亲假", value="homeLeave")
          el-option(label="病假", value="sickLeave")
          el-option(label="事假", value="absenceLeave")
          el-option(label="其他", value="other")
      el-form-item(label="请假理由", :label-width="formLabelWidth")
        el-input(type="textarea", :rows="2", v-model="apply.reason", autocomplete="off")
    div(slot="footer", class="dialog-footer")
      el-button(@click="EditDialogVisible1 = false") 取 消
      el-button(type="primary", @click="submitLeave()") 确认提交
  //外出申请表
  el-dialog(title="外出申请", :visible.sync="EditDialogVisible2",width="600px")
    el-form(:model="apply")
      el-form-item(label="姓名", :label-width="formLabelWidth")
        el-input(v-model="user.username",:disabled="true")
      el-form-item(label="起止日期", :label-width="formLabelWidth")
        el-date-picker(v-model="apply.dateArr", type="daterange", range-separator="至", start-placeholder="开始日期", end-placeholder="结束日期", value-format="timestamp")
      el-form-item(label="请假类型", :label-width="formLabelWidth")
        el-select(v-model="apply.type", placeholder="--未选择--")
          el-option(label="外出", value="out")
      el-form-item(label="请假理由", :label-width="formLabelWidth")
        el-input(type="textarea", :rows="2", v-model="apply.reason", autocomplete="off")
    div(slot="footer", class="dialog-footer")
      el-button(@click="EditDialogVisible2 = false") 取 消
      el-button(type="primary", @click="submitOut()") 确认提交
  //详情页
  el-dialog(title="申请详情", :visible.sync="DetailDialogVisible",width="600px")
    el-form(:model="applyDetail")
      el-form-item(label="姓名", :label-width="formLabelWidth")
        el-input(v-model="applyDetail.name",:disabled="true")
      el-form-item(label="起止日期", :label-width="formLabelWidth")
        el-date-picker(:v-model="none", type="daterange", range-separator="至", :start-placeholder="applyDetail.startTime", :end-placeholder="applyDetail.endTime", value-format="timestamp",:disabled="true")
      el-form-item(label="请假类型", :label-width="formLabelWidth")
        el-select(v-model="applyDetail.type", placeholder="--未选择--", :disabled="true")
          el-option(label="外出", value="out")
          el-option(label="年假", value="annualLeave")
          el-option(label="产假", value="maternityLeave")
          el-option(label="探亲假", value="homeLeave")
          el-option(label="病假", value="sickLeave")
          el-option(label="事假", value="absenceLeave")
          el-option(label="其他", value="other")
      el-form-item(label="请假理由", :label-width="formLabelWidth")
        el-input(type="textarea", :rows="2", v-model="applyDetail.reason", autocomplete="off" ,:disabled="true")
      el-form-item(label="状态", :label-width="formLabelWidth")
        span(v-if="applyDetail.authority==3") 已处理
        span(v-else-if="applyDetail.authority==2||applyDetail.authority==1") 正在处理
        span(v-else) 未处理
      el-form-item(v-if="applyDetail.authority!=0" label="领导反馈", :label-width="formLabelWidth")
        div(v-for="(item,index) in applyDetail.leadersOpinion" key="index")
          span(v-if="item.result=='agree'") {{item.title+item.name}} ：同意
          span(v-else) {{item.title+item.name}} ：不同意
          div(style="clear:both")
          span 意见：{{item.opinion}}
    div(slot="footer", class="dialog-footer")
      el-button(@click="DetailDialogVisible = false") 取 消
  //修改申请
  el-dialog(title="修改申请", :visible.sync="EditDialogVisible3",width="600px")
    el-form(:model="applyDetail")
      el-form-item(label="姓名", :label-width="formLabelWidth")
        el-input(v-model="applyDetail.name",:disabled="true")
      el-form-item(label="起止日期", :label-width="formLabelWidth")
        el-date-picker(v-model="applyDetail.dateArr", type="daterange", range-separator="至", :start-placeholder="applyDetail.startTime", :end-placeholder="applyDetail.endTime", value-format="timestamp")
      el-form-item(label="请假类型", :label-width="formLabelWidth")
        el-select(v-if="applyDetail.type=='out'" v-model="applyDetail.type", placeholder="--未选择--")
          el-option(label="外出", value="out")
        el-select(v-else v-model="applyDetail.type", placeholder="--未选择--")
          el-option(label="年假", value="annualLeave")
          el-option(label="产假", value="maternityLeave")
          el-option(label="探亲假", value="homeLeave")
          el-option(label="病假", value="sickLeave")
          el-option(label="事假", value="absenceLeave")
          el-option(label="其他", value="other")
      el-form-item(label="请假理由", :label-width="formLabelWidth")
        el-input(type="textarea", :rows="2", v-model="applyDetail.reason", autocomplete="off")
    div(slot="footer", class="dialog-footer")
      el-button(@click="EditDialogVisible3 = false") 取消
      el-button(type="primary", @click="submitApply()") 确认提交
</template>

<script>
    export default {
      name: "Apply",
      data () {
         return{
            apply_type:'2',
            none:'',
            EditDialogVisible1: false,
            EditDialogVisible2: false,
            EditDialogVisible3: false,
            DetailDialogVisible: false,
            formLabelWidth: '90px',
            apply:{
             dateArr:[],
             type:'',
             reason:''
            },
            user:{
              name:''
            },
            applyDetail:{
              leadersOpinion:[]
            },
            applications:[],
          }
      },
      created(){
        this.userinfo()
        this.getApplication()
      },
      methods:{
        typeformatter(row, column) {
          switch(row.type){
            case 'annualLeave':
              return '年假';
              break;
            case 'maternityLeave':
              return '产假';
              break;
            case 'homeLeave':
              return '探亲假';
              break;
            case 'sickLeave':
              return '病假';
              break;
            case 'absenceLeave':
              return '事假';
              break;
            case 'out':
              return '外出';
              break;
            default:
              return '其它';
          }
        },
        statusformatter(row, column) {
          if (row.authority==3) return '已处理';
          else if (row.authority==1||row.authority==2) return '正在处理'
          else return '未处理'
        },
        addApply1(){
          if (this.user.userId) {
            this.EditDialogVisible1 = true;
            this.apply.dateArr = [];
            this.apply.type = '';
            this.apply.reason = '';
          } else {
            this.$message.warning('请先登录')
          }
        },
        addApply2(){
          if (this.user.userId) {
            this.EditDialogVisible2=true;
            this.apply.dateArr = [];
            this.apply.type = 'out';
            this.apply.reason = '';
          } else {
            this.$message.warning('请先登录')
          }
        },
        updateApply(e){
          this.EditDialogVisible3=true;
          this.applyDetail = e;
          this.applyDetail.dataArr = [];
        },
        applyPage(e){
          this.DetailDialogVisible=true;
          this.applyDetail = e;
          console.log(e);
        },
        submitLeave(){
          let _this = this
          console.log(this.apply);
          this.$http.post(`/api/application/leave`,{
            id: this.user.userId,
            startTime: this.apply.dateArr[0],
            endTime: this.apply.dateArr[1],
            type: this.apply.type,
            reason: this.apply.reason
          }).then(res => {
            console.log(res)
            if (res.response && res.response.status!=200){
              let message = res.response.data.message
              _this.$message.error(message)
            } else {
              _this.EditDialogVisible1 = false
              _this.$message.success('申请成功,请耐心等待审核')
              _this.apply_type='0'
              _this.getApplication()
            }
          }).catch(err=>{
            _this.$message.error('系统出错')
          })
        },
        submitOut(){
          let _this = this
          console.log(this.apply);
          this.$http.post(`/api/application/out`,{
            id: this.user.userId,
            startTime: this.apply.dateArr[0],
            endTime: this.apply.dateArr[1],
            reason: this.apply.reason
          }).then(res => {
            console.log(res)
            if (res.response && res.response.status!=200){
              let message = res.response.data.message
              _this.$message.error(message)
            } else {
              _this.EditDialogVisible2 = false
              _this.$message.success('申请成功,请耐心等待审核')
              _this.apply_type='1'
              _this.getApplication()
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
        getApplication(){
          let _this = this
          if (this.user.userId){
            this.$http.get(`/api/user/messages/application?type=${this.apply_type}&id=${this.user.userId}`).then(res => {
              console.log(res)
              if (res.response && res.response.status!=200){
                let message = res.response.data.message
                _this.$message.error(message)
              } else {
                this.applications = res.data.applications
              }
            }).catch(err=>{
              _this.$message.error('系统出错')
            })
          }
        },
        submitApply(){
          let _this = this
          if (this.user.userId) {
            if (this.applyDetail.type == 'out'){
              this.$http.post(`/api/application/mod/out`,{
                id: this.user.userId,
                outId: this.applyDetail.applicationId,
                startTime: this.applyDetail.dateArr[0],
                endTime: this.applyDetail.dateArr[1],
                reason: this.applyDetail.reason
              }).then(res => {
                console.log(res)
                if (res.response && res.response.status!=200){
                  let message = res.response.data.message
                  _this.$message.error(message)
                } else {
                  _this.EditDialogVisible3 = false;
                  _this.$message.success('修改成功,请耐心等待审核')
                }
              }).catch(err=>{
                _this.$message.error('系统出错')
              })
            } else {
              this.$http.post(`/api/application/mod/leave`,{
                id: this.user.userId,
                leaveIdId: this.applyDetail.applicationId,
                startTime: this.applyDetail.dateArr[0],
                endTime: this.applyDetail.dateArr[1],
                type: this.applyDetail.type,
                reason: this.applyDetail.reason
              }).then(res => {
                console.log(res)
                if (res.response && res.response.status!=200){
                  let message = res.response.data.message
                  _this.$message.error(message)
                } else {
                  _this.EditDialogVisible3 = false;
                  _this.$message.success('修改成功,请耐心等待审核')
                }
              }).catch(err=>{
                _this.$message.error('系统出错')
              })
            }
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
  .title {
    font-size: 20px;
    font-weight: 500;
    padding: 0 15px;
    margin: 20px 0;
    border-left: 8px solid #00a0e9;
    clear:both;
  }
  .apply-box{
    width:300px;
    text-align:center;
    margin: 0 10px;
    float:left;
    cursor:pointer;
  }
  .apply-box:hover{
    box-shadow: 0 2px 12px 0 rgba(0,0,0,0);
  }
  .apply-selector {
    display: inline-block;
    margin-left:15px;
    margin-bottom: 8px;
  }
  .table {
    margin: 10px auto;
    width: 870px;
  }
</style>
