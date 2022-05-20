<template>
  <div>
    <el-col :span="12" :offset="3">
      <div class="grid-content"></div>
      <el-upload
        ref="upload"
        :auto-upload=false
        class="upload-demo"
        drag
        action="http://localhost:8081/upload"
        multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传xlsx文件</div>
      </el-upload>
      <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
    </el-col>
  </div>
</template>

<script>
    export default {
      name: "toUpload",
      methods: {
        submitUpload() {
          this.$refs.upload.submit();
          this.$alert('操作成功！', '成功', {
            confirmButtonText: '确定',
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
