<template>
  <div>
    <div v-show="show">
      <img src="../../static/image/home_bg_big.png" width="100%"/>
    </div>
    <div v-show="!show">
      <img src="../../static/image/home_bg_small.jpeg" width="100%"/>
    </div>
    <Card v-for="item in blogList" style="width:80%; margin-left: 10%; margin-bottom: 10px; height: 200px">
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
          <i-col :xs="18" :sm="21" :md="22" style="margin-top: 3px; font-weight: bold">
            {{item.date}}
          </i-col>
          <i-col :xs="6" :sm="3" :md="2">
            <Icon size="30" type="ios-eye-outline" style="margin-top: -3px"/>
            {{item.reader}}
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
      }
    },
    methods: {
      postDetail: (key) => {
        router.push({path:'/detail', query: {blogId: key}})
      },
      success: function (status, text) {
        if (status === 200) {
          let list = JSON.parse(text)
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
