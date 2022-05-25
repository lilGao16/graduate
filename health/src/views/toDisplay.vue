<template>
  <div>
    <Header></Header>
    <el-row>
      <el-col :span="4"><Side></Side></el-col>
      <div>
        <div class="col-md-6">
          <div class="col-md-6">
            <div id="main1" ref="b" style="height:300%;width:100%;"></div>
          </div>
          <div class="col-md-6">
            <div id="main2" ref="a" style="height:300%;width:100%;"></div>
          </div>
        </div>
        <div class="col-md-7">
          <div class="col-md-7">
            <div id="main3" ref="c" style="height:300%;width:100%;"></div>
          </div>
          <div class="col-md-7">
            <div id="main4" ref="d" style="height:300%;width:100%;"></div>
          </div>
        </div>
      </div>
    </el-row>

  </div>
</template>

<script>
  import * as echarts from 'echarts';
  import Header from "../components/header";
  import Side from "../components/side";
    export default {
        name: "toDisplay",
        components: {Header, Side},
        mounted() {
          const _this = this
          this.drawPie1()
          this.drawPie2()
          this.drawPie3()
          this.drawPie4()
          _this.$axios.get("/statistics").then(res => {
            console.log(res.data)
            console.log(res.data[0])
            console.log(res.data[1])
            localStorage.setItem('0', res.data[0])
            localStorage.setItem('1', res.data[1])
            localStorage.setItem('2', res.data[2])
            localStorage.setItem('3', res.data[3])
            localStorage.setItem('4', res.data[4])
            localStorage.setItem('5', res.data[5])
            localStorage.setItem('6', res.data[6])
            localStorage.setItem('7', res.data[7])
            localStorage.setItem('8', res.data[8])
            localStorage.setItem('9', res.data[9])
            localStorage.setItem('10', res.data[10])
            localStorage.setItem('11', res.data[11])
            localStorage.setItem('12', res.data[12])
          })
        },
        data() {
          return {
            male: 0,
            female: 0,
            below60: 0,
            sixtyTo70: 0,
            seventyTo80: 0,
            beyond80: 0,
            ability: 0,
            disability: 0,
            keep0: 0,
            keep30: 0,
            keep60: 0,
            keep90: 0,
            keep120: 0,
          }
        },
        methods: {
          drawPie1() {
            let chartDom = document.getElementById('main');
            let myChart = echarts.init(this.$refs.b);
            let option;
            const _this = this
            option = {
              title: {
                text: '老年人年龄比例',
                subtext: '',
                left: 'center'
              },
              tooltip: {
                trigger: 'item'
              },

              series: [
                {
                  name: '年龄',
                  type: 'pie',
                  radius: '35%',
                  data: [
                    { value: localStorage.getItem('2'), name: '60以下' },
                    { value: localStorage.getItem('3'), name: '60~70' },
                    { value: localStorage.getItem('4'), name: '70~80' },
                    { value: localStorage.getItem('5'), name: '80以上' },
                  ],
                  emphasis: {
                    itemStyle: {
                      shadowBlur: 10,
                      shadowOffsetX: 0,
                      shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                  }
                }
              ]
            };

            option && myChart.setOption(option);
          },
          drawPie2() {
            let chartDom = document.getElementById('main');
            let myChart = echarts.init(this.$refs.a);
            let option;

            option = {
              title: {
                text: '老年人锻炼时间',
                subtext: '',
                left: 'center'
              },
              xAxis: {
                data: ['0', '30', '60', '90', '120']
              },
              yAxis: {},
              series: [
                {
                  data: [localStorage.getItem('8'),
                    localStorage.getItem('9'),
                    localStorage.getItem('10'),
                    localStorage.getItem('11'),
                    localStorage.getItem('12')],
                  type: 'bar'
                }
              ]
            };

            option && myChart.setOption(option);
          },
          drawPie3() {
            let chartDom = document.getElementById('main');
            let myChart = echarts.init(this.$refs.c);
            let option;

            option = {
              title: {
                text: '老人性别比例',
                subtext: '',
                left: 'center'
              },
              tooltip: {
                trigger: 'item'
              },

              series: [
                {
                  name: '性别',
                  type: 'pie',
                  radius: '35%',
                  data: [
                    { value: localStorage.getItem('0'), name: '男' },
                    { value: localStorage.getItem('1'), name: '女' },
                  ],
                  emphasis: {
                    itemStyle: {
                      shadowBlur: 10,
                      shadowOffsetX: 0,
                      shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                  }
                }
              ]
            };

            option && myChart.setOption(option);
          },
          drawPie4() {
            let chartDom = document.getElementById('main');
            let myChart = echarts.init(this.$refs.d);
            let option;
            option = {
              title: {
                text: '老年人自理能力',
                subtext: '',
                left: 'center'
              },
              xAxis: {
                data: ['有', '无']
              },
              yAxis: {},
              series: [
                {
                  data: [localStorage.getItem('6'), localStorage.getItem('7')],
                  type: 'bar'
                }
              ]
            };
            option && myChart.setOption(option);
          }
        }
    }
</script>

<style scoped>
  .grid-content {
    border-radius: 4px;
    min-height: 100px;
  }
  .left-bar {
    margin-top: 70px;
    float: left;
    left: 500px;
    width: 50%;
    height: 100%;
    position: absolute;
    overflow: auto;
  }
  .right-bar {
    margin-top: 70px;
    float: start;
    right: 200px;
    width: 25%;
    height: 100%;
    position: absolute;
    overflow: auto;
  }
  .div1 {
    float: left;    /* 添加左浮动 */
    width: 200px;
    height: 200px;
    text-align: center;
    line-height: 200px;
    color: aliceblue;
    background-color: rgb(26, 135, 238);
  }
  .div2 {
    float: left;    /* 添加左浮动 */
    width: 200px;
    height: 200px;
    text-align: center;
    line-height: 200px;
    color: aliceblue;
    background-color: rgb(7, 194, 178);
  }
  .col-md-6 {
    margin-top: 30px;
    width:50%;
    float:left;
    overflow:hidden;
  }
  .col-md-7 {
    margin-top: 0px;
    width:50%;
    float:left;
    overflow:hidden;
  }
</style>
