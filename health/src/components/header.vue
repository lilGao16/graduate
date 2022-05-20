<template>
  <el-container>
    <el-header style="text-align: initial; font-size: 20px; color: #ffffff">
      <span>老年人健康管理系统</span>
    </el-header>
  </el-container>

</template>

<script>
  export default {
    name: 'header',
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped>
  .el-header {
    background-color: #2c3e50;
    color: #333;
    line-height: 60px;
  }
</style>
