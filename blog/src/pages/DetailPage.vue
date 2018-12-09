<template>
  <div>
    <mavon-editor v-html="value" :subfield="false" :defaultOpen="defaultData" :toolbarsFlag="false" :boxShadow="false"
                  @change="changeData" style="width: 90%; margin-left: 5%"/>
  </div>
</template>
<script>
  import showdown from 'showdown'

  export default {
    data() {
      return {//value的值是经过markdown解析后的文本，可使用`@change="changeData"`在控制台打印显示
        value: ``,
        defaultData: "preview"
      }
    },
    methods: {
      changeData(value, render) {
        console.log(render)
      }
    },
    mounted() {
      let converter = new showdown.Converter()
      let html = converter.makeHtml(`# Blog 建站小记——从建项开始

## 前端

1. 使用终端进入项目文件夹中所在文件夹 \`vue init webpack project-name\`

   注意：会有一系列选项需要填写，可以一路\`enter\`，也可以有选择的输入一些内容进行初始化，比如项目名字，是否建立单元测试等
`)
      this.value = html
    }
  }
</script>

<style scoped>

</style>
