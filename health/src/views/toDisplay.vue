<template>
  <div>
    <!--div class="left-bar">
      <div>
        <div id="main1" ref="b" style="height:300%;width:100%;"></div>
        <div id="main2" ref="a" style="height:300%;width:100%;"></div>

      </div>
    </div>
    <div class="right-bar">
      <div>
        <div id="main3" ref="c" style="height:300%;width:100%;"></div>
        <div id="main4" ref="d" style="height:300%;width:100%;"></div>
      </div>
    </div-->
    <!--el-col :span="12" :offset="0">
      <div class="grid-content"></div>
      <div id="main1" ref="b" style="height:300%;width:100%;"></div>
      <div id="main2" ref="a" style="height:300%;width:100%;"></div>
      <div class="div1"></div>
      <div class="div2"></div>
    </el-col-->
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
</template>

<script>
  import * as echarts from 'echarts';
    export default {
        name: "toDisplay",
        mounted() {
          this.drawPie1()
          this.drawPie2()
          this.drawPie3()
          this.drawPie4()
        },
        methods: {
          drawPie1() {
            let chartDom = document.getElementById('main');
            let myChart = echarts.init(this.$refs.b);
            let option;

            option = {
              title: {
                text: '老年人年龄比例',
                subtext: '共504人',
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
                    { value: 1048, name: '50~60' },
                    { value: 735, name: '60~70' },
                    { value: 580, name: '70~80' },
                    { value: 484, name: '80以上' },
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
                subtext: '共504人',
                left: 'center'
              },
              xAxis: {
                data: ['0~30', '30~60', '60~90', '90~120']
              },
              yAxis: {},
              series: [
                {
                  data: [60, 200, 150, 80],
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
                subtext: '共504人',
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
                    { value: 354, name: '男' },
                    { value: 250, name: '女' },
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
                subtext: '共504人',
                left: 'center'
              },
              xAxis: {
                data: ['有', '无']
              },
              yAxis: {},
              series: [
                {
                  data: [476, 38],
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
