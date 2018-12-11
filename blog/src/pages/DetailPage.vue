<template>
  <div>
    <Row class="row">
      <i-col span="24">
        <div style="margin-top: 2%">
          <div class="subTitle">{{this.title}}</div>
          <Row style="margin-bottom: 2%">
            <div style="font-size: 1.2em; font-weight: bold; text-align:left; margin-top: .5em">{{this.abstract}}
            </div>
          </Row>
          <Row style="margin-top: 2em; margin-bottom: 2em">
            <i-col v-bind:span="avatar">
              <Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg" size="large"/>
            </i-col>
            <i-col v-bind:span="24-avatar-offset" v-bind:offset="offset">
              <div v-bind:style="{marginLeft: offset?0:.7+'em'}"
                   style="font-size: 1.1em; text-align: left; font-weight: bold; color: black; margin-top: .1em">
                {{this.name}}
              </div>
              <div v-bind:style="{marginLeft: offset?0:.7+'em'}"
                   style="font-size: 1em; text-align: left; margin-top: .3em">{{this.date}} &nbsp&nbsp&nbsp 阅读
                {{this.reader}}
              </div>
            </i-col>
          </Row>
        </div>
      </i-col>
    </Row>
    <mavon-editor v-html="content" :subfield="false" :defaultOpen="defaultData" :toolbarsFlag="false" :boxShadow="false"
                  style="width: 90%; margin-left: 5%; margin-bottom: 2em"/>
    <Navigation/>
  </div>
</template>
<script>
  import showdown from 'showdown'
  import global from '../../static/Global'
  import ICol from "iview/src/components/grid/col"
  import Navigation from '../components/Navigation'

  export default {
    components: {ICol, Navigation},
    data() {
      return {
        //content的值是经过markdown解析后的文本，可使用`@change="changeData"`在控制台打印显示
        title: 'Blog 建站小记——从建项开始',
        abstract: '建立自己博客的过程记录',
        name: 'kiki',
        reader: 20,
        date: '2018.12.10 19:23',
        content: ``,
        defaultData: "preview",

        // 适配问题
        offset: 1,
        avatar: 2
      }
    },
    methods: {},
    mounted() {
      let converter = new showdown.Converter()
      let html = converter.makeHtml(`# Blog 建站小记——从建项开始

## 前端

1. 使用终端进入项目文件夹中所在文件夹 \`vue init webpack project-name\`

   注意：会有一系列选项需要填写，可以一路\`enter\`，也可以有选择的输入一些内容进行初始化，比如项目名字，是否建立单元测试等
`)
      this.content = html

      // 适配问题
      this.offset = global.screenWidth > 700 ? 0 : 1
      this.avatar = global.screenWidth > 700 ? 1 : 2
    }
  }
</script>

<style scoped>
  .row {
    margin-left: 5%;
    width: 90%;
    margin-top: 20px
  }

  .subTitle {
    font-size: 2em;
    font-weight: bold;
    text-align: left;
  }
</style>
