<template>
  <div>
    <Row class="row" style="margin-top: 40px">
      <i-col span="5">
        <img v-bind:style="{marginTop:mobile?1+'em':0}" width="90%" src="../../static/image/idea.png"/>
      </i-col>
      <i-col span="19">
        <div v-show="!mobile" style="text-align: left; font-size: 2.5em; font-weight: bolder; margin-top: .5em">
          record your idea
        </div>
        <div style="margin-top: 2%">
          <Row>
            <i-col span="7">
              <div class="subTitle">标题</div>
            </i-col>
            <i-col span="17">
              <Input class="subTitleInput" v-model="title" placeholder="请输入标题..." size="large"/>
            </i-col>
          </Row>
          <Row style="margin-bottom: .5em">
            <i-col span="7">
              <div class="subTitle">简介</div>
            </i-col>
            <i-col span="17">
              <Input class="subTitleInput" v-model="abstract" type="textarea" :autosize="{minRows: 4,maxRows: 4}"
                     placeholder="请输入简介（不得超过50词）" maxLength="50" size="large"/>
            </i-col>
          </Row>
        </div>
      </i-col>
    </Row>
    <mavon-editor :ishljs="true" v-model="content" :defaultOpen="defaultData"
                  @save="save"
                  :toolbars="{
                    bold: true, // 粗体
                    italic: true, // 斜体
                    header: true, // 标题
                    underline: true, // 下划线
                    strikethrough: true, // 中划线
                    mark: true, // 标记
                    superscript: false, // 上角标
                    subscript: false, // 下角标
                    quote: true, // 引用
                    ol: true, // 有序列表
                    ul: true, // 无序列表
                    link: true, // 链接
                    imagelink: true, // 图片链接
                    code: true, // code
                    table: true, // 表格
                    fullscreen: false, // 全屏编辑
                    readmodel: true, // 沉浸式阅读
                    htmlcode: true, // 展示html源码
                    help: true, // 帮助
                    /* 1.3.5 */
                    undo: true, // 上一步
                    redo: true, // 下一步
                    trash: true, // 清空
                    save: true, // 保存（触发events中的save事件）
                    /* 1.4.2 */
                    navigation: true, // 导航目录
                    /* 2.1.8 */
                    alignleft: true, // 左对齐
                    aligncenter: true, // 居中
                    alignright: true, // 右对齐
                    /* 2.2.1 */
                    subfield: true, // 单双栏模式
                    preview: true, // 预览
                  }"
                  class="row" v-bind:style="{height: md_height+'em'}" style="margin-bottom: 1em"/>

    <Row class="row" style="margin-bottom: 2em">
      <i-col span="12">
        <Button style="margin-right: 2.5%; width: 97.5%" to="/" type="error" long @click="back">返回</Button>
      </i-col>
      <i-col span="12">
        <Button style="margin-left: 2.5%; width: 97.5%" type="success" long @click="submit">提交</Button>
      </i-col>
    </Row>
    <Modal></Modal>
    <Navigation/>
  </div>
</template>

<script>
  import global from '../../static/Global'
  import ICol from "iview/src/components/grid/col"
  import Navigation from '../components/Navigation'
  import router from '../router'
  import blogApi from '../methods/blogApi'

  export default {
    components: {
      ICol,
      global,
      Navigation
    },
    data() {
      return {
        title: '',
        abstract: '',
        content: '',
        defaultData: 'edit',
        // 适配
        mobile: false,
        md_height: 35,
      }
    },
    methods: {
      save: function () {
        console.log(this.value)
      },
      back: () => {
        router.push('/')
        // this.$Modal.confirm({
        //   title: '确认退出',
        //   content: '您所编辑的内容尚未保存，请问确认退出吗？',
        //   okText: '确认',
        //   cancelText: '取消',
        //   onOk: () => {
        //     router.push('/')
        //   },
        // });
      },
      submit: function () {
        blogApi.postBlog(this.title,this.abstract,this.content, this.success, this.fail)
      },
      success: function (status, text) {
        console.log(status)
        if (status === 200) {
          alert('保存成功')
          router.push('/')
        }
        else
          alert('保存成功')
      },
      fail: function (error) {
        alert(error)
      }
    },
    mounted() {
      // 适配问题
      this.mobile = global.screenWidth <= 1000
      if (global.screenWidth === 768 && global.screenHeight === 1024)
        this.md_height = 48
      if (global.screenWidth === 375 && global.screenHeight === 812)
        this.md_height = 38
      if (global.screenWidth === 1024 && global.screenHeight === 1366)
        this.md_height = 65
    }
  }
</script>

<style scoped>
  .row {
    margin-left: 5%;
    width: 90%;
  }

  .subTitle {
    font-size: 2em;
    font-weight: bold;
    text-align: left;
  }

  .subTitleInput {
    margin-top: .1em;
    font-weight: bold;
    width: 100%;
  }
</style>
