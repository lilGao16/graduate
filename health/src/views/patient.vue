<template>
  <div>
    <Header></Header>
    <el-row>
      <el-col :span="4"><Side></Side></el-col>
      <div>
        <el-col :span="12" :offset="3">
          <div class="grid-content"></div>
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="患者:">
              <el-input v-model="formInline.patient.name" placeholder="请输入患者姓名" :disabled= true></el-input>
            </el-form-item>
            <el-form-item label="年龄:">
              <el-input v-model="formInline.patient.age" placeholder="请输入患者年龄" :disabled="disabled"></el-input>
            </el-form-item>
            <el-form-item label="活动形式:" prop="desc">
              <el-input type="textarea" rows="5" v-model="formInline.patient.name" style="width: 650px" :disabled="disabled"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="unlock">修改</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit" :disabled="disabled">提交</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </div>
    </el-row>

  </div>
</template>

<script>
  import Qs from 'qs'
  import Header from "../components/header";
  import Side from "../components/side";
    export default {
      name: "patient",
      components: {Header, Side},
      data() {
        return {
          disabled: true,
          formInline: {
            patient: {
              name: localStorage.getItem("name"),
              age: localStorage.getItem("age")
            }
          }
        }
      },
      methods: {
        unlock() {
          const _this=this
          _this.disabled = false;
        },
        onSubmit() {
          const _this=this
          let upload = JSON.stringify(_this.formInline.patient)
          console.log(upload)
          _this.$axios.post("/update", upload, {headers: {'Content-Type': 'application/json'}}).then(res => {
            console.log(res.data)
            this.$alert('操作成功！', '成功', {
              confirmButtonText: '确定',
            })
            _this.$router.push("/toSearch")
          })
        }
      }
    }
</script>

<style scoped>
  .grid-content {
    border-radius: 4px;
    min-height: 100px;
  }
</style>
