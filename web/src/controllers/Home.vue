<template lang="pug">
.body-container
  .title
   span 全体员工一览
  .employee-search
    .employee-input
      el-input(placeholder="请输入学号", v-model="searchInput", @keyup.native.enter="find()")
    el-button(type="primary", icon="el-icon-search", @click="find()") 查找员工
  .employee-selector
    el-select(v-model="employee_type", placeholder="全部")
      el-option(label="全部", value="0")
      el-option(label="已请假", value="1")
      el-option(label="已外出", value="2")
      el-option(label="正在加班", value="3")
</template>

<script>
    export default {
      name: 'Home',
      data () {
        return {
          searchInput: '',
          employee_type:'',
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
</style>
