<template>
  <div>
    <el-col :span="20" :offset="0">
      <div class="grid-content"></div>
      <el-col :span="12" :offset="5">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-row>基本信息</el-row>
          <div class="grid-content"></div>
          <el-form-item label="患者:">
            <el-input v-model="formInline.patient.name" placeholder="请输入患者姓名"></el-input>
          </el-form-item>
          <el-form-item label="性别:">
            <el-input v-model="formInline.patient.gender" placeholder="请输入患者性别" ></el-input>
          </el-form-item>
          <el-form-item label="住址:">
            <el-input v-model="formInline.patient.address" placeholder="请输入患者住址" ></el-input>
          </el-form-item>
          <el-form-item label="出生日期:">
            <el-input v-model="formInline.patient.birthday" placeholder="格式如1900-01-01" ></el-input>
          </el-form-item>
          <el-form-item label="电话号码:">
            <el-input v-model="formInline.patient.phoneNum" placeholder="请输入患者电话号码" ></el-input>
          </el-form-item>

          <!--el-form-item label="活动形式:" prop="desc">
            <el-input type="textarea" rows="5" v-model="formInline.patient.name" style="width: 650px" :disabled="disabled"></el-input>
          </el-form-item-->

        </el-form>
      </el-col>
      <el-col :span="12" :offset="5">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-row>健康指标</el-row>
          <div class="grid-content"></div>
          <el-form-item label="身高:">
            <el-input v-model="formInline.patient.height" placeholder="请输入患者身高" ></el-input>
          </el-form-item>
          <el-form-item label="体重:">
            <el-input v-model="formInline.patient.weight" placeholder="请输入患者体重" ></el-input>
          </el-form-item>
          <el-form-item label="体温:">
            <el-input v-model="formInline.patient.heat" placeholder="请输入患者体温" ></el-input>
          </el-form-item>
          <el-form-item label="脉搏:">
            <el-input v-model="formInline.patient.pulse" placeholder="请输入患者脉搏" ></el-input>
          </el-form-item>
          <el-form-item label="血压:">
            <el-input v-model="formInline.patient.pressure" placeholder="请输入患者血压" ></el-input>
          </el-form-item>
          <!--el-form-item label="活动形式:" prop="desc">
            <el-input type="textarea" rows="5" v-model="formInline.patient.name" style="width: 650px" :disabled="disabled"></el-input>
          </el-form-item-->
        </el-form>
      </el-col>
    </el-col>

    <el-button type="primary" @click="onSubmit" >提交</el-button>

  </div>
</template>

<script>
  import Qs from 'qs'
  export default {
    name: "patient",
    data() {
      return {
        disabled: true,
        formInline: {
          patient: {
            name: null,
            gender: null,
            height: null,
            weight: null,
            heat: null,
            pulse: null,
            pressure: null,
            address: null,
            birthday: null,
            phoneNum: null
          }
        }
      }
    },
    methods: {
      onSubmit() {
        const _this=this
        let upload = JSON.stringify(_this.formInline.patient)
        console.log(upload)
        _this.$axios.post("/insert", upload, {headers: {'Content-Type': 'application/json'}}).then(res => {
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
