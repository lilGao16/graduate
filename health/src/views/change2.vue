<template>
  <div>
    <el-col :span="20" :offset="0">
      <div class="grid-content"></div>
      <div class="grid-content"></div>
      <div class="grid-content"></div>
      <el-col :span="20" :offset="1">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-row>健康档案</el-row>
          <div class="grid-content"></div>
          <el-form-item label="患者:">
            <el-input v-model="formInline.patient.name" placeholder="请输入" :disabled= true></el-input>
          </el-form-item>
          <el-form-item label="锻炼频率:">
            <el-input v-model="formInline.patient.frequency" placeholder="请输入" :disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="每次锻炼时间(min):">
            <el-input v-model="formInline.patient.keeptime1" placeholder="请输入" :disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="坚持锻炼时间(年):">
            <el-input v-model="formInline.patient.keeptime2" placeholder="请输入" :disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="生活自理能力:">
            <el-input v-model="formInline.patient.ability" placeholder="请输入" :disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="吸烟状况:">
            <el-input v-model="formInline.patient.smoke" placeholder="请输入" :disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="主要用药情况:">
            <el-input
              id="input1"
              type="textarea"
              placeholder="请输入用药史"
              v-model="formInline.patient.drug">
            </el-input>
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
</template>

<script>
  import Qs from 'qs'
  export default {
    name: "change2",
    mounted() {
      console.log(this.formInline.patient.frequency)
      if(this.formInline.patient.name  == "null") {
        this.formInline.patient.name = '';
      }
      if(this.formInline.patient.frequency == "null") {
        this.formInline.patient.frequency = '';
      }
      if(this.formInline.patient.keeptime1  == "null") {
        this.formInline.patient.keeptime1 = '';
      }
      if(this.formInline.patient.keeptime2  == "null") {
        this.formInline.patient.keeptime2 = '';
      }
      if(this.formInline.patient.ability  == "null") {
        this.formInline.patient.ability = '';
      }
      if(this.formInline.patient.smoke  == "null") {
        this.formInline.patient.smoke = '';
      }
      if(this.formInline.patient.drug  == "null") {
        this.formInline.patient.drug = '';
      }
    },
    data() {
      return {
        disabled: true,
        formInline: {
          patient: {
            name: localStorage.getItem("name"),
            frequency: localStorage.getItem("frequency"),
            keeptime1: localStorage.getItem("keeptime1"),
            keeptime2: localStorage.getItem("keeptime2"),
            ability: localStorage.getItem("ability"),
            smoke: localStorage.getItem("smoke"),
            drug: localStorage.getItem("drug")
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
        _this.$axios.post("/update2", upload, {headers: {'Content-Type': 'application/json'}}).then(res => {
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
