<template>
  <div>
    <Header></Header>
    <el-row>
      <div class="grid-content"></div>
      <div>
        <el-col :span="8" :offset="8">
          <el-table
            :data="users"
            style="width: 100%"
            max-height="250">
            <el-table-column
              fixed
              prop="username"
              label="用户名"
              width="150">
            </el-table-column>
            <el-table-column
              prop="password"
              label="密码"
              width="120">
            </el-table-column>
            <el-table-column
              prop="role"
              label="角色"
              width="60">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="120">
              <template slot-scope="scope">
                <el-button
                  @click="check(scope.$index)"
                  type="text"
                  size="small">
                  查看
                </el-button>
                <el-button
                  @click.native.prevent="deleteRow(scope.$index, users)"
                  type="text"
                  size="small">
                  移除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="grid-content"></div>
          <el-button type="primary" @click="adduser()">新增用户</el-button>
        </el-col>
      </div>
    </el-row>
  </div>
</template>

<script>
  import Header from "../../components/header";
  import Side from "../../components/side";
  export default {
    name: "users",
    components: {Header, Side},
    data() {
      return {
        users: []
      }
    },
    methods: {
      getAll() {
        const _this = this
        _this.$axios.get("/getusers").then(res => {
          console.log(res.data)
          _this.users = res.data
        })
      },
      adduser() {
        this.$router.push('/adduser')
      },
      check(index) {
        const _this = this
        console.log("/search/" + this.users[index].username);
        localStorage.setItem("username", this.users[index].username)
        localStorage.setItem("password", this.users[index].password)
        localStorage.setItem("role", this.users[index].role)
        _this.$router.push("/changeuser")
      },
      deleteRow(index, rows) {
        console.log(index)
        const _this = this
        _this.$axios.get("/deleteuser/"+ this.users[index].username).then(res => {
          console.log(res.data)
          rows.splice(index, 1);
        })
      }
    },
    created() {
      this.getAll()
    }

  }
</script>

<style scoped>
  .grid-content {
    border-radius: 4px;
    min-height: 100px;
  }
</style>
