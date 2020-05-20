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
        el-option(label="请假申请", value="0")
        el-option(label="外出申请", value="1")
  el-table.table(:data="applications", border)
    el-table-column(align="center", prop="name", label="姓名", width="120")
    el-table-column(align="center", prop="startTime", label="起始时间", width="120")
    el-table-column(align="center", prop="endTime", label="终止时间", width="120")
    el-table-column(align="center", prop="type", label="类别",:formatter="typeformatter", width="120")
    el-table-column(align="center", prop="", label="状态",:formatter="statusformatter" width="120")
    el-table-column(align="center", label="操作")
      template(slot-scope="scope")
        el-button(type="text", @click="applyPage(scope.row)") 查看详情
        el-button(:disabled="scope.row.LeadersOpinion.length!=0" type="text", @click="updateApply(scope.row)") 修改申请

  //请假申请表
  el-dialog(title="请假申请", :visible.sync="EditDialogVisible1",width="600px")
    el-form(:model="apply")
      el-form-item(label="姓名", :label-width="formLabelWidth")
        el-input(v-model="apply_type",:disabled="true")
      el-form-item(label="起止日期", :label-width="formLabelWidth")
        el-date-picker(v-model="apply.dateArr1", type="datetimerange", range-separator="至", start-placeholder="开始日期", end-placeholder="结束日期", value-format="timestamp")
      el-form-item(label="请假类型", :label-width="formLabelWidth")
        el-select(v-model="apply.type", placeholder="--未选择--")
          el-option(label="年假", value="annualLeave")
          el-option(label="产假", value="maternityLeave'")
          el-option(label="探亲假", value="homeLeave")
          el-option(label="病假", value="sickLeave")
          el-option(label="事假", value="absenceLeave")
          el-option(label="其他", value="other")
      el-form-item(label="请假理由", :label-width="formLabelWidth")
        el-input(type="textarea", :rows="2", v-model="apply.reason", autocomplete="off")
    div(slot="footer", class="dialog-footer")
      el-button(@click="EditDialogVisible1 = false") 取 消
      el-button(type="primary", @click="submitApply()") 确认提交
  //外出申请表
  el-dialog(title="外出申请", :visible.sync="EditDialogVisible2",width="600px")
    el-form(:model="apply")
      el-form-item(label="姓名", :label-width="formLabelWidth")
        el-input(v-model="apply_type",:disabled="true")
      el-form-item(label="起止日期", :label-width="formLabelWidth")
        el-date-picker(v-model="apply.dateArr1", type="datetimerange", range-separator="至", start-placeholder="开始日期", end-placeholder="结束日期", value-format="timestamp")
      el-form-item(label="请假类型", :label-width="formLabelWidth")
        el-select(v-model="apply.type", placeholder="--未选择--")
          el-option(label="外出", value="out")
      el-form-item(label="请假理由", :label-width="formLabelWidth")
        el-input(type="textarea", :rows="2", v-model="apply.reason", autocomplete="off")
    div(slot="footer", class="dialog-footer")
      el-button(@click="EditDialogVisible2 = false") 取 消
      el-button(type="primary", @click="submitApply()") 确认提交
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
      el-form-item(label="请假理由", :label-width="formLabelWidth")
        el-input(type="textarea", :rows="2", v-model="applyDetail.reason", autocomplete="off" ,:disabled="true")
      el-form-item(label="状态", :label-width="formLabelWidth")
        span(v-if="applyDetail.LeadersOpinion.length!=0") 已处理
        span(v-else) 未处理
      el-form-item(v-if="applyDetail.LeadersOpinion.length!=0" label="领导反馈", :label-width="formLabelWidth")
        div(v-for="(item,index) in applyDetail.LeadersOpinion" key="index")
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
        el-date-picker(v-model="applyDetail.dateArr1", type="datetimerange", range-separator="至", :start-placeholder="applyDetail.startTime", :end-placeholder="applyDetail.endTime", value-format="timestamp")
      el-form-item(label="请假类型", :label-width="formLabelWidth")
        el-select(v-model="applyDetail.type", placeholder="--未选择--")
          el-option(label="年假", value="annualLeave")
          el-option(label="产假", value="maternityLeave'")
          el-option(label="探亲假", value="homeLeave")
          el-option(label="病假", value="sickLeave")
          el-option(label="事假", value="absenceLeave")
          el-option(label="其他", value="other")
      el-form-item(label="请假理由", :label-width="formLabelWidth")
        el-input(type="textarea", :rows="2", v-model="applyDetail.reason", autocomplete="off")
    div(slot="footer", class="dialog-footer")
      el-button(type="danger" @click="") 删除
      el-button(type="primary", @click="submitApply()") 确认提交
</template>

<script>
    export default {
      name: "Apply",
      data () {
         return{
            apply_type:'',
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
            applyDetail:{
              LeadersOpinion:[]
            },
            applications:[
              {
                applicationId:1,
                memberId:123,
                name:'鲍子龙',
                startTime:'2020-4-11 22:00',
                endTime:'2020-4-12 22:00',
                type:'annualLeave',
                reason:'I want to play a game',
                LeadersOpinion: [{
                  title:'总经理',
                  name:'尤嘉宁',
                  result:'agree',
                  opinion:'I agree with you!'
                } ]
              },
              {
                applicationId:1,
                memberId:123,
                name:'满显凡',
                startTime:'2020-4-11 22:00',
                endTime:'2020-4-12 22:00',
                type:'sickLeave',
                reason:'I want to play a game',
                LeadersOpinion: []
              },
              {
                applicationId:1,
                memberId:123,
                name:'任林杰',
                startTime:'2020-4-11 22:00',
                endTime:'2020-4-12 22:00',
                type:'out',
                reason:'I want to play a game',
                LeadersOpinion: []
              }
            ],
          }
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
          if (row.LeadersOpinion.length==0) return '未处理';
          else return '已处理'
        },
        addApply1(){
          this.EditDialogVisible1=true;
          this.apply.dataArr = [];
          this.apply.type = '';
          this.apply.reason = '';
        },
        addApply2(){
          this.EditDialogVisible2=true;
          this.apply.dataArr = [];
          this.apply.type = 'out';
          this.apply.reason = '';
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
    width: 900px;
    margin: 0 auto;
  }
</style>
