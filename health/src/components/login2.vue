<template>
  <div style="text-align: center">
    <div class="top"></div>
    <div style="text-align: center">
      <el-form>
        <el-form-item>
          <label>用户名:</label>
          <el-input v-model="user.username" placeholder="请输入用户名" class="input1"></el-input>
        </el-form-item>
        <el-form-item>
          <label>密码:</label>
          <el-input v-model="user.password" placeholder="请输入密码" class="input2"></el-input>
        </el-form-item>
        <el-form-item style="text-align: center">
          <el-button type="primary" @click="onSubmit" >登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
    export default {
        name: "login2",
        data() {
          return {
            user: {
              username: '',
              password: '',
            }
          }
        },
        methods: {
          onSubmit() {
            const _this=this
            let upload = JSON.stringify(_this.user)
            _this.$axios.post("/login", upload, {headers: {'Content-Type': 'application/json'}}).then(res => {
              console.log(res.data)
              if(res.data === "No") {
                this.$alert('用户名或密码错误！', '登陆失败', {
                  confirmButtonText: '确定',
                })
              }
              else if(res.data === "notad") {
                localStorage.setItem("role", "notad")
                _this.$router.push('/')
              }
              else {
                localStorage.setItem("role", "admin")
                _this.$router.push('/users')
              }
            })
          }
        }
    }
</script>

<style scoped>
  .top{
    height: 200px;
  }
  .input1{
    width: 185px;
  }
  .input2{
    width: 200px;
  }
</style>
