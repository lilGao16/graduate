<template>
  <div>
    <el-col :span="12" :offset="3">
      <div class="grid-content"></div>
      <el-table
        :data="patients"
        style="width: 100%"
        max-height="250">
        <el-table-column
          fixed
          prop="birthday"
          label="出生日期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="120">
        </el-table-column>
        <el-table-column
          prop="gender"
          label="性别"
          width="60">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址"
          width="120">
        </el-table-column>
        <el-table-column
          prop="phoneNum"
          label="电话号码"
          width="120">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click="predict(scope.$index)"
              type="text"
              size="small">
              预测
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
  </div>
</template>

<script>
  export default {
    methods: {
      deleteRow(index, rows) {
        rows.splice(index, 1);
        console.log(this.patients[index-1].name)
        const _this = this
        _this.$axios.get("/delete/"+ this.patients[index-1].name).then(res => {
          console.log(res.data)
        })
      },
      predict(index) {
        const _this = this
        console.log("/search/" + this.patients[index].name);

        let upload = JSON.stringify(_this.patients[index])
        console.log(upload)
        _this.$axios.post("/predict", upload, {headers: {'Content-Type': 'application/json'}}).then(res => {
          console.log(res.data)
          //_this.$router.push("/patient")
          if(res.data === 1) {
            this.$alert('老人有较高患病风险，请尽快体检！', '危险', {
              confirmButtonText: '确定',
            })
          }
          else {
            this.$alert('老人暂无较高患病风险，请用心照顾。', '健康', {
              confirmButtonText: '确定',
            })
          }
        })

      },
      getAll() {
        const _this = this
        _this.$axios.get("/getall").then(res => {
          console.log(res.data)
          _this.patients = res.data
        })
      },
      insert(){
        const _this = this
        _this.$router.push("/add");
      }
    },
    data() {
      return {
        patients : [],
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
