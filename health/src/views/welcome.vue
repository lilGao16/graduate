<template>
  <div>
    <Header></Header>
    <el-row>
      <el-col :span="4"><Side></Side></el-col>
      <div>

      </div>
    </el-row>
  </div>
</template>

<script>
  import Side from "../components/side";
  import Header from "../components/header";
    export default {
      name: "welcome",
      components: {Header, Side}
    }
</script>

<style scoped>

</style>
