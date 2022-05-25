<template>
  <div>
    <Header></Header>
    <el-row>
      <el-col :span="4"><Side></Side></el-col>
      <div>
        <el-col :span="20" :offset="0">
          <div class="grid-content"></div>
          <el-col :span="12" :offset="5">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
              <el-row>基本信息</el-row>
              <div class="grid-content"></div>
              <el-form-item label="患者:">
                <el-input v-model="formInline.patient.name" placeholder="请输入患者姓名" :disabled= true></el-input>
              </el-form-item>
              <el-form-item label="性别:">
                <el-input v-model="formInline.patient.gender" placeholder="请输入患者性别" :disabled="disabled"></el-input>
              </el-form-item>
              <el-form-item label="住址:">
                <el-input v-model="formInline.patient.address" placeholder="请输入患者住址" :disabled="disabled"></el-input>
              </el-form-item>
              <el-form-item label="出生日期:">
                <el-input v-model="formInline.patient.birthday" placeholder="请输入患者出生日期" :disabled="disabled"></el-input>
              </el-form-item>
              <el-form-item label="电话号码:">
                <el-input v-model="formInline.patient.phoneNum" placeholder="请输入患者电话号码" :disabled="disabled"></el-input>
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="12" :offset="5">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
              <el-row>健康指标</el-row>
              <div class="grid-content"></div>
              <el-form-item label="身高:">
                <el-input v-model="formInline.patient.height" placeholder="请输入" :disabled="disabled"></el-input>
              </el-form-item>
              <el-form-item label="体重:">
                <el-input v-model="formInline.patient.weight" placeholder="请输入" :disabled="disabled"></el-input>
              </el-form-item>
              <el-form-item label="体温:">
                <el-input v-model="formInline.patient.heat" placeholder="请输入" :disabled="disabled"></el-input>
              </el-form-item>
              <el-form-item label="脉搏:">
                <el-input v-model="formInline.patient.pulse" placeholder="请输入" :disabled="disabled"></el-input>
              </el-form-item>
              <el-form-item label="血压:">
                <el-input v-model="formInline.patient.pressure" placeholder="请输入" :disabled="disabled"></el-input>
              </el-form-item>
              <!--el-form-item label="活动形式:" prop="desc">
                <el-input type="textarea" rows="5" v-model="formInline.patient.name" style="width: 650px" :disabled="disabled"></el-input>
              </el-form-item-->
            </el-form>
          </el-col>
        </el-col>

        <el-button type="primary" @click="unlock">修改</el-button>
        <el-button type="primary" @click="onSubmit" :disabled="disabled">提交</el-button>
      </div>
    </el-row>


  </div>
</template>

<script>
  import Qs from 'qs'
  import Header from "../components/header";
  import Side from "../components/side";
  export default {
    name: "change",
    components: {Header, Side},
    mounted() {
      if(this.formInline.patient.height == "null") {
        this.formInline.patient.height = '';
      }
      if(this.formInline.patient.weight == "null") {
        this.formInline.patient.weight = '';
      }
      if(this.formInline.patient.pulse == "null") {
        this.formInline.patient.pulse = '';
      }
      if(this.formInline.patient.pressure == "null") {
        this.formInline.patient.pressure = '';
      }
      if(this.formInline.patient.heat == "null") {
        this.formInline.patient.heat = ''
      }
    },
    data() {
      return {
        disabled: true,
        formInline: {
          patient: {
            name: localStorage.getItem("name"),
            gender: localStorage.getItem("gender"),
            height: localStorage.getItem("height"),
            weight: localStorage.getItem("weight"),
            heat: localStorage.getItem("heat"),
            pulse: localStorage.getItem("pulse"),
            pressure: localStorage.getItem("pressure"),
            address: localStorage.getItem("address"),
            birthday: localStorage.getItem("birthday"),
            phoneNum: localStorage.getItem("phoneNum")
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
        _this.$axios.post("/update1", upload, {headers: {'Content-Type': 'application/json'}}).then(res => {
          console.log(res.data)
          this.$alert('操作成功！', '成功', {
            confirmButtonText: '确定',
          })
          _this.$router.push("/toSearch1")
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
