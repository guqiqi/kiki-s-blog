<template>
  <div style="background-color: rgba(203,203,203,0.35); padding-bottom: 30px">
    <BackTop></BackTop>
    <div v-show="show">
      <img src="../../static/image/home_bg_big.jpg" width="100%"/>
    </div>
    <div v-show="!show">
      <img src="../../static/image/home_bg_small.jpg" width="100%"/>
    </div>
    <div style="background-color: rgba(170, 183, 185, 0.4); padding: 20px; width: 80%; margin-left: 10%; margin-bottom: 10px">
      <div
        style="margin-bottom: 20px; text-align: left; font-size: large">
        <i-col :xs="8" :sm="4" :md="1">
          时间
        </i-col>
        <i-col :xs="16" :sm="20" :md="23">
          <Tag v-for="item in years" style="margin-top: -4px; margin-left: 10px" @click.native="clickYearTag(item)">
            {{item}}年
          </Tag>
        </i-col>
      </div>
      <div
        style="margin-top: 20px; text-align: left; font-size: large">
        <i-col :xs="8" :sm="4" :md="1">
          标签
        </i-col>
        <Tag v-for="item in tags" style="margin-top: -4px; margin-left: 10px" @click.native="clickTag(item.tagName)">
          {{item.tagName}}
        </Tag>
      </div>
    </div>
    <Card v-for="item in blogList" style="width:80%; margin-left: 10%; margin-bottom: 10px; height: 200px"
          @click="postDetail(item.id)">
      <Row>
        <!--<i-col :xs="0" :sm="0" :md="4" :lg="4">-->
        <!--<img height="100%" width="100%" v-bind:src="item.imgUrl">-->
        <!--</i-col>-->
        <!--<i-col :xs="24" :sm="24" :md="20" :lg="20">-->
        <div style="font-size: x-large; font-weight: bold; text-align: left; margin-left: 20px; height: 40px"
             @click="postDetail(item.id)">
          {{ item.title }}
        </div>
        <div style="text-align: left; margin-left: 20px; height: 105px">
          {{ item.summary }}
        </div>
        <Row style="text-align: left; margin-left: 20px; height: 80%">
          <i-col :xs="16" :sm="20" :md="21" >
            <Icon size="30" type="ios-eye-outline" style="margin-top: -3px"/>
            {{item.reader}}
            <Icon v-show="item.tags.length != 0" type="ios-pricetag-outline" size="20" style="margin-top: -3px; margin-left: 20px"/>
            <Tag v-for="i in item.tags" style="margin-top: -4px; margin-left: 5px">
              {{i}}
            </Tag>
          </i-col>

          <i-col :xs="8" :sm="4" :md="3" style="margin-top: 3px; font-weight: bold">
            {{item.date}}
          </i-col>
        </Row>
        <!--</i-col>-->
      </Row>
    </Card>
    <Navigation/>
  </div>
</template>

<script>
  import global from '../../static/Global'
  import ICol from 'iview/src/components/grid/col'
  import router from '../router'
  import Navigation from '../components/Navigation'
  import blogApi from '../methods/blogApi'
  import tagApi from '../methods/tagApi'

  export default {
    components: {
      Navigation,
      ICol
    },
    name: 'HomePage',
    data() {
      return {
        show: true,
        blogList: [],
        tags: [],
        years: ["2019", "2020"]
      }
    },
    methods: {
      postDetail: (key) => {
        router.push({path: '/detail', query: {blogId: key}})
      },
      clickTag: function(tag) {
        blogApi.getBlogByTag(tag, this.success, this.fail)
      },
      clickYearTag: function(tag) {
        blogApi.getBlogByYear(tag, this.success, this.fail)
      },
      success: function (status, text) {
        if (status === 200) {
          let list = JSON.parse(text)

          for(let i = 0; i < list.length; i++){
            if(list[i].tagNames != null && list[i].tagNames != ""){
              list[i].tags = list[i].tagNames.split("&%#")
              list[i].tags.splice(0, 1)
            }
            else
              list[i].tags = []
          }

          this.blogList = list
        }
        else
          alert('出错啦')
      },
      fail: function (error) {
        alert(error)
      }
    },
    mounted() {
      blogApi.getAllBlog(this.success, this.fail)

      tagApi.getAllTags((status, text) => {
        if (status === 200) {
          let list = JSON.parse(text)
          this.tags = list
        }
        else
          alert('出错啦')
      }, (error) => {alert(error)})

      // 适配
      let w = global.screenWidth
      if (w < 800) {
        this.show = false
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
