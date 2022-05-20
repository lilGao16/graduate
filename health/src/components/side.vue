<template>
  <el-row class="tac" >
    <el-col :span="24">
      <h5>服务导航</h5>
      <el-menu
        default-active="2"
        class="left-menu"
        background-color="#ffffff"
        :router= true
        @open="handleOpen"
        @close="handleClose">

        <el-menu-item index='/toUpload'>
          <i class="el-icon-menu"></i>
          <span slot="title">数据导入</span>
        </el-menu-item>
        <el-menu-item index='/toSearch1'>
          <i class="el-icon-document"></i>
          <span slot="title">数据管理</span>
        </el-menu-item>
        <el-menu-item index='/disease'>
          <i class="el-icon-setting"></i>
          <span slot="title">疾病预测</span>
        </el-menu-item>
        <el-menu-item index="/toDisplay">
          <i class="el-icon-setting"></i>
          <span slot="title">数据展示</span>
        </el-menu-item>

      </el-menu>
    </el-col>

  </el-row>
</template>

<script>
  export default {
    name:'side',
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped>
  .left-menu{
    height: 83.5vh;
  }
</style>
