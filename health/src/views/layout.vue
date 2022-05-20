<template>
  <div>
    <el-row>
      <el-col :span="4"><Side></Side></el-col>
      <router-view></router-view>
    </el-row>
  </div>
</template>

<script>
    import Side from "../components/side";
    export default {
        name: "layout",
      components: {Side},

    }
</script>

<style scoped>

</style>

