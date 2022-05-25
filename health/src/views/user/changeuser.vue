<template>
  <div>
    <Header></Header>
    <el-row>
      <div>
        <el-col :span="20" :offset="0">
          <div class="grid-content"></div>
          <el-col :span="12" :offset="8">
            <el-form :inline="true" :model="user" class="demo-form-inline">
              <el-row>基本信息</el-row>
              <div class="grid-content"></div>
              <el-form-item label="用户名:">
                <el-input v-model="user.username" placeholder="请输入用户名"></el-input>
              </el-form-item>
              <el-form-item label="密码:">
                <el-input v-model="user.password" placeholder="请输入密码" ></el-input>
              </el-form-item>
              <el-form-item label="角色:">
                <el-input v-model="user.role" placeholder="请输入用户角色" ></el-input>
              </el-form-item>
            </el-form>
            <el-button type="primary" @click="onSubmit()" >提交</el-button>
          </el-col>
        </el-col>
      </div>
    </el-row>
  </div>
</template>

<script>
    import Header from "../../components/header";
    import Side from "../../components/side";

    export default {
        name: "changeuser",
      components: {Header, Side},
      data() {
        return {
          user: {
            username: localStorage.getItem("username"),
            password: localStorage.getItem("password"),
            role: localStorage.getItem("role")
          }
        }
      },
      methods: {
        onSubmit() {
          const _this=this
          let upload = JSON.stringify(_this.user)
          console.log(upload)
          _this.$axios.post("/updateuser", upload, {headers: {'Content-Type': 'application/json'}}).then(res => {
            console.log(res.data)
            this.$alert('操作成功！', '成功', {
              confirmButtonText: '确定',
            })
            _this.$router.push("/users")
          })
        }
      }
    }
</script>

<style scoped>
  .grid-content {
    border-radius: 4px;
    min-height: 30px;
  }
</style>
