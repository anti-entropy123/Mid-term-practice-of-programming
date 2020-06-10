<template lang="pug">
.body-container
  //经理业务
  div(v-if="user.title=='总经理'||user.title=='副总经理'||user.title=='项目经理'")
    .title
      span 待审批
    .apply-selector
      el-select(v-model="apply_type", placeholder="--未选择--")
        el-option(label="全部申请", value="2" @click.native='getApplication')
        el-option(label="请假申请", value="0" @click.native='getApplication')
        el-option(label="外出申请", value="1" @click.native='getApplication')
    el-pagination.manage-pagination(@current-change="handleCurrentChange",
      :current-page="currentPage",
      layout="prev, pager, next",
      :total="applications.length",
      :page-size="pagesize")
    el-table.table(:data="applications.slice((currentPage-1)*pagesize,currentPage*pagesize)", border)
      el-table-column(align="center", prop="name", label="姓名")
      el-table-column(align="center", prop="startTime", label="起始时间")
      el-table-column(align="center", prop="endTime", label="终止时间")
      el-table-column(align="center", prop="type", label="类别",:formatter="typeformatter")
      el-table-column(align="center", label="操作")
        template(slot-scope="scope")
          el-button(type="text", @click="approval(scope.row)") 审批
    .title
      span 已审批
    .apply-selector
      el-select(v-model="apply_type1", placeholder="--未选择--")
        el-option(label="全部申请", value="2" @click.native='getREApplication')
        el-option(label="请假申请", value="0" @click.native='getREApplication')
        el-option(label="外出申请", value="1" @click.native='getREApplication')
    el-pagination.manage-pagination(@current-change="handleCurrentChange1",
      :current-page="currentPage1",
      layout="prev, pager, next",
      :total="applications1.length",
      :page-size="pagesize")
    el-table.table(:data="applications1.slice((currentPage1-1)*pagesize,currentPage1*pagesize)", border)
      el-table-column(align="center", prop="name", label="姓名")
      el-table-column(align="center", prop="startTime", label="起始时间")
      el-table-column(align="center", prop="endTime", label="终止时间")
      el-table-column(align="center", prop="type", label="类别",:formatter="typeformatter")
      el-table-column(align="center", label="操作")
        template(slot-scope="scope")
          el-button(type="text", @click="detail(scope.row)") 查看


    //审批页
    el-dialog(title="审批申请", :visible.sync="applicationVisible",width="600px")
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
        el-form-item(v-if="applyDetail.authority!=0" label="领导反馈", :label-width="formLabelWidth")
          div(v-for="(item,index) in applyDetail.leadersOpinion" key="index")
            span(v-if="item.result=='agree'") {{item.title+item.name}} ：同意
            span(v-else) {{item.title+item.name}} ：不同意
            div(style="clear:both")
            span 意见：{{item.opinion}}
        el-form-item(label="添加审批", :label-width="formLabelWidth")
          el-select(v-model="result", placeholder="--未选择--")
            el-option(label="同意", value="agree")
            el-option(label="不同意", value="disagree")
        el-form-item(label="添加意见", :label-width="formLabelWidth")
          el-input(type="textarea", :rows="2", v-model="opinion", autocomplete="off")
      div(slot="footer", class="dialog-footer")
        el-button(@click="applicationVisible = false") 取 消
        el-button(type="primary", @click="submitApproval()") 确认审批


    //详情页
    el-dialog(title="审批详情", :visible.sync="applicationVisible1",width="600px")
      el-form(:model="applyDetail1")
        el-form-item(label="姓名", :label-width="formLabelWidth")
          el-input(v-model="applyDetail1.name",:disabled="true")
        el-form-item(label="起止日期", :label-width="formLabelWidth")
          el-date-picker(:v-model="none", type="daterange", range-separator="至", :start-placeholder="applyDetail.startTime", :end-placeholder="applyDetail.endTime", value-format="timestamp",:disabled="true")
        el-form-item(label="请假类型", :label-width="formLabelWidth")
          el-select(v-model="applyDetail1.type", placeholder="--未选择--", :disabled="true")
            el-option(label="外出", value="out")
            el-option(label="年假", value="annualLeave")
            el-option(label="产假", value="maternityLeave")
            el-option(label="探亲假", value="homeLeave")
            el-option(label="病假", value="sickLeave")
            el-option(label="事假", value="absenceLeave")
            el-option(label="其他", value="other")
        el-form-item(label="请假理由", :label-width="formLabelWidth")
          el-input(type="textarea", :rows="2", v-model="applyDetail1.reason", autocomplete="off" ,:disabled="true")
        el-form-item(v-if="applyDetail.authority!=0" label="领导反馈", :label-width="formLabelWidth")
          div(v-for="(item,index) in applyDetail1.leadersOpinion" key="index")
            span(v-if="item.result=='agree'") {{item.title+item.name}} ：同意
            span(v-else) {{item.title+item.name}} ：不同意
            div(style="clear:both")
            span 意见：{{item.opinion}}
      div(slot="footer", class="dialog-footer")
        el-button(@click="applicationVisible = false") 取 消

  //财务部员工业务
  div(v-if="user.title=='财务部员工'")
    .title
      span 查看员工
    .employee-search
      .employee-input
        el-input(placeholder="请输入工号", v-model="searchInput",  @keyup.native.enter="getmember")
      el-button(type="primary", icon="el-icon-search", @click="getmember") 查找员工
    el-table.table(:data="memberinfo", border)
      el-table-column(align="center", label="姓名")
        template(slot-scope="scope")
          span {{member.userName}}
      el-table-column(align="center", label="缺勤次数")
        template(slot-scope="scope")
          span {{member.unsigned}}
      el-table-column(align="center", label="加班次数")
        template(slot-scope="scope")
          span {{member.overTime}}
    el-table.table(:data="member.leaves", border)
      el-table-column(align="center", prop="leaveType", label="请假类别",:formatter="typeformatter")
      el-table-column(align="center", prop="startTime", label="起始时间")
      el-table-column(align="center", prop="endTime", label="终止时间")
    el-table.table(:data="member.outs", border)
      el-table-column(align="center", label="外出类别")
        template(slot-scope="scope")
          span 外出
      el-table-column(align="center", prop="startTime", label="起始时间")
      el-table-column(align="center", prop="endTime", label="终止时间")

  //行政部员工业务
  div(v-if="user.title=='行政部员工'")
    .title
      span 打卡信息上传
    el-upload.upload-demo.left(drag action="none" :http-request="uploadSectionFile" :show-file-list="false")
      i.el-icon-upload
      div.el-upload__text 将文件拖到此处，或点击上传
      div.el-upload__tip( slot="tip") 只能上传xlsx文件

  div(v-if="!user.title")
    div.left
</template>

<script>
    export default {
      name: "work",
      data () {
        return {
          user: {},
          none:'',
          formLabelWidth: '90px',
          searchInput: '',
          pagesize: 3,
          currentPage: 1,
          currentTotal: 0,
          currentPage1: 1,
          currentTotal1: 0,
          apply_type:'2',
          apply_type1:'2',
          applications:[],
          applications1:[],
          applyDetail:{
            leadersOpinion:[]
          },
          applyDetail1:{
            leadersOpinion:[]
          },
          applicationVisible:false,
          applicationVisible1:false,
          opinion:'',
          result:'',
          member: {},
          memberinfo:[]
        }
      },
      created(){
        this.userinfo()
        if (this.user.title=='总经理'||this.user.title=='副总经理'||this.user.title=='项目经理') {
          this.getApplication()
          this.getREApplication()
        }
      },
      methods:{
        typeformatter(row, column) {
          switch(row.leaveType){
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
        handleCurrentChange(currentPage) {
          this.currentPage = currentPage
        },
        handleCurrentChange1(currentPage) {
          this.currentPage1 = currentPage
        },
        getApplication(){
          let _this = this
          if (this.user.userId){
            this.$http.get(`/api/user/messages/process-application?type=${this.apply_type}&id=${this.user.userId}`).then(res => {
              console.log(res)
              if (res.response && res.response.status!=200){
                let message = res.response.data.message
                _this.$message.error(message)
              } else {
                this.applications = []
                let applications = res.data.applications
                for (let i = 0; i < applications.length; i++) {
                  if (this.user.title == '项目经理' && applications[i].authority == 0) {
                    this.applications.push(applications[i])
                  } else if (this.user.title == '副总经理' && applications[i].authority == 1) {
                    this.applications.push(applications[i])
                  } else if (this.user.title == '总经理' && applications[i].authority == 2) {
                    this.applications.push(applications[i])
                  }
                }
              }
            }).catch(err=>{
              _this.$message.error('系统出错')
            })
          }
        },
        getREApplication(){
          let _this = this
          if (this.user.userId){
            this.$http.get(`/api/user/messages/process-application?type=${this.apply_type1}&id=${this.user.userId}`).then(res => {
              console.log(res)
              if (res.response && res.response.status!=200){
                let message = res.response.data.message
                _this.$message.error(message)
              } else {
                this.applications1 = []
                let applications = res.data.applications
                for (let i = 0; i < applications.length; i++) {
                  if (this.user.title == '项目经理' && (applications[i].authority == 1 || applications[i].authority == 2 || applications[i].authority == 3)) {
                    this.applications1.push(applications[i])
                  } else if (this.user.title == '副总经理' && (applications[i].authority == 2 || applications[i].authority == 3)) {
                    this.applications1.push(applications[i])
                  } else if (this.user.title == '总经理' && applications[i].authority == 3) {
                    this.applications1.push(applications[i])
                  }
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
        approval(e){
          this.applicationVisible = true;
          this.applyDetail = e;
        },
        detail(e){
          this.applicationVisible1 = true;
          this.applyDetail1 = e;
        },
        submitApproval(){
          let _this = this
          this.$http.post(`/api/application/process-command/`,{
            id: this.user.userId,
            applicationId: this.applyDetail.applicationId,
            result: this.result,
            opinion: this.opinion
          }).then(res => {
            console.log(res)
            if (res.response && res.response.status!=200){
              let message = res.response.data.message
              _this.$message.error(message)
            } else {
              _this.EditDialogVisible1 = false
              _this.$message.success('审批成功')
              _this.getApplication()
              _this.getREApplication()
            }
          }).catch(err=>{
            _this.$message.error('系统出错')
          })
        },
        uploadSectionFile(params){
          let _this = this
          const file = params.file,
            fileType = file.name,
            isXlsx = fileType.indexOf("xlsx") != -1
          console.log(file)
          if (!isXlsx) {
            this.$message.error("只能上传文件格式xlsx!");
            return;
          }
          const form = new FormData();
          form.append("file", file);
          this.$http.post(`/api/administration-department/after-process/data/`,form,{
            headers: {"content-type": "multipart/form-data"}
          }).then(res => {
            console.log(res)
            if (res.response && res.response.status!=200){
              let message = res.response.data.message
              _this.$message.error(message)
            } else {
              this.$message.success('上传成功')
            }
          }).catch(err=>{
            this.$message.error('系统出错')
          })
        },
        getmember(){
          let _this = this
          if (this.searchInput == '' ){
            this.$message.warning('输入为空')
          } else {
            this.$http.get(`/api/administration-department/after-process/data?id=${this.searchInput}`).then(res => {
              console.log(res)
              if (res.response && res.response.status!=200){
                let message = res.response.data.message
                _this.$message.error(message)
              } else {
                this.member=res.data.data
                this.memberinfo = []
                this.memberinfo.push(res.data.data)
              }
            }).catch(err=>{
              this.$message.error('系统出错')
            })
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
  .manage-pagination {
    margin-right:15px;
    float: right;
  }
  .left{
    margin: 20px 15px;
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
</style>
