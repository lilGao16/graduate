<template>
  <div>
    <Header></Header>
    <el-row>
      <el-col :span="4"><Side></Side></el-col>
      <div>
        <el-col :span="12" :offset="3">
          <div class="grid-content"></div>
          <el-col :span="6" :offset="0">
            <el-input v-model="input" placeholder="请输入内容"></el-input>
          </el-col>
          <el-col :span="4" :offset="0">
            <el-button type="primary" @click="searchByName()">搜索</el-button>
          </el-col>
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
                  @click="check(scope.$index)"
                  type="text"
                  size="small">
                  查看
                </el-button>
                <el-button
                  @click.native.prevent="deleteRow(scope.$index, patients)"
                  type="text"
                  size="small">
                  移除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="grid-content"></div>
          <el-button type="primary" @click="insert()">新增档案</el-button>
        </el-col>
      </div>
    </el-row>

  </div>
</template>

<script>
  import Header from "../components/header";
  import Side from "../components/side";

  export default {
    components: {Header, Side},
    methods: {
      deleteRow(index, rows) {
        console.log(index)
        const _this = this
        _this.$axios.get("/delete/"+ this.patients[index].name).then(res => {
          console.log(res.data)
          rows.splice(index, 1);
        })
      },
      check(index) {
        const _this = this
        console.log("/search/" + this.patients[index].name);
        /*_this.$axios.get("/search/" + this.patients[index].name).then(res => {
          console.log(res.data)
          //_this.$router.push("/patient")
        })*/
        this.$confirm('请选择档案类型', '提示', {
          confirmButtonText: '基本信息',
          cancelButtonText: '健康档案',
          type: 'warning'
        }).then(() => {
          localStorage.setItem("name", this.patients[index].name)
          localStorage.setItem("gender", this.patients[index].gender)
          localStorage.setItem("address", this.patients[index].address)
          localStorage.setItem("birthday", this.patients[index].birthday)
          localStorage.setItem("phoneNum", this.patients[index].phoneNum)
          localStorage.setItem("height", this.patients[index].height)
          localStorage.setItem("weight", this.patients[index].weight)
          localStorage.setItem("heat", this.patients[index].heat)
          localStorage.setItem("pulse", this.patients[index].pulse)
          localStorage.setItem("pressure", this.patients[index].pressure)
          _this.$router.push("/change")
        }).catch(() => {
          _this.$axios.get("/getrecord/" + this.patients[index].name).then(res => {
            console.log(res.data)
            localStorage.setItem("name", res.data.name)
            localStorage.setItem("frequency", res.data.frequency)
            localStorage.setItem("keeptime1", res.data.keeptime1)
            localStorage.setItem("keeptime2", res.data.keeptime2)
            localStorage.setItem("ability", res.data.ability)
            localStorage.setItem("smoke", res.data.smoke)
            localStorage.setItem("drug", res.data.drug)
            _this.$router.push("/change2")
          })
        });

        //_this.$router.push("/change")
      },
      getAll() {
        const _this = this
        _this.$axios.get("/getall").then(res => {
          console.log(res.data)
          _this.patients = res.data
        })
      },
      insert() {
        const _this = this
        _this.$router.push("/add");
      },
      searchByName() {
        const _this = this
        _this.$axios.get("/searchlike/" + this.input).then(res => {
          console.log(res.data)
          _this.patients = res.data
        })
      }
    },
    data() {
      return {
        patients : [],
        records: [],
        input: null,
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
