<template>
  <div>
    <Header></Header>
    <el-row>
      <el-col :span="4"><Side></Side></el-col>

    </el-row>
  </div>
</template>

<script>
    import Side from "../components/side";
    import Header from "../components/header";
    export default {
        name: "layout",
      components: {Side, Header},

    }
</script>

<style scoped>

</style>

