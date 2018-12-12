<template>
  <div>
    <Row class="row">
      <i-col span="24">
        <div style="margin-top: 2%">
          <div class="subTitle">{{this.title}}</div>
          <Row style="margin-bottom: 2%">
            <div style="font-size: 1.2em; font-weight: bold; text-align:left; margin-top: .5em">{{this.summary}}
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
  import blogApi from '../methods/blogApi'

  export default {
    components: {ICol, Navigation},
    data() {
      return {
        //content的值是经过markdown解析后的文本，可使用`@change="changeData"`在控制台打印显示
        title: '',
        summary: '',
        name: '',
        reader: 0,
        date: '',
        content: ``,
        defaultData: "preview",

        // 适配问题
        offset: 1,
        avatar: 2
      }
    },
    methods: {
      success: function (status, text) {
        if (status === 200) {
          let blog = JSON.parse(text)

          let converter = new showdown.Converter()
          let html = converter.makeHtml(blog.content)
          this.content = html
          this.title = blog.title
          this.summary = blog.summary
          this.name = blog.writer
          this.reader = blog.reader
          this.date = blog.date
        }
        else
          alert('联网失败')
      },
      fail: function (error) {
        alert(error)
      }
    },
    mounted() {
      blogApi.getBlogById(this.$route.query.blogId, this.success, this.fail)

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
    margin-top: 40px
  }

  .subTitle {
    font-size: 2em;
    font-weight: bold;
    text-align: left;
  }
</style>
