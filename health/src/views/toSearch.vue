<template>
  <div>
    <el-col :span="12" :offset="3">
      <div class="grid-content"></div>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="患者：">
          <el-input v-model="formInline.user" placeholder="请输入患者姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </div>
</template>
<script>

  import Side from "../components/side";
  export default {
    components: {Side},
    data() {
      return {
        formInline: {
          user: ''
        }
      }
    },
    methods: {
      onSubmit() {
        console.log("/search/" + this.formInline.user);
        const _this = this
        //localStorage.setItem("name", "name")
        //localStorage.setItem("age", "age")
        //_this.$router.push("/patient")
        _this.$axios.get("/search/" + this.formInline.user).then(res => {
          console.log(res.data)
          _this.$router.push("/patient")
          localStorage.setItem("name", res.data.name)
          localStorage.setItem("age", res.data.age)
        })
      }
    }
  }
</script>
<style>
  .grid-content {
    border-radius: 4px;
    min-height: 100px;
  }
</style>
