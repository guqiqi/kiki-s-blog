<template>
  <div>
    <div v-show="show">
      <img src="../../static/image/home_bg_big.png" width="100%"/>
    </div>
    <div v-show="!show">
      <img src="../../static/image/home_bg_small.jpeg" width="100%"/>
    </div>
    <Card v-for="item in randomMovieList" style="width:80%; margin-left: 10%; margin-bottom: 10px; height: 200px">
      <Row>
        <i-col :xs="0" :sm="0" :md="4" :lg="4">
          <img height="100%" width="100%" v-bind:src="item.imgUrl">
        </i-col>
        <i-col :xs="24" :sm="24" :md="20" :lg="20">
          <div style="font-size: x-large; font-weight: bold; text-align: left; margin-left: 20px; height: 40px"
               @click="postDetail(item.key)">
            {{ item.name }}
          </div>
          <div style="text-align: left; margin-left: 20px; height: 105px">
            {{ item.name }}
          </div>
          <Row style="text-align: left; margin-left: 20px; height: 80%">
            <i-col :xs="18" :sm="21" :md="22" style="margin-top: 3px; font-weight: bold">
              2018年4月10日
            </i-col>
            <i-col :xs="6" :sm="3" :md="2">
              <Icon size="30" type="ios-eye-outline" style="margin-top: -3px"/>
              20
            </i-col>
          </Row>
        </i-col>
      </Row>
    </Card>
  </div>
</template>

<script>
  import global from '../../static/Global'
  import ICol from 'iview/src/components/grid/col'
  import router from '../router'

  export default {
    components: {ICol},
    name: 'HomePage',
    data () {
      return {
        show: true,
        movieList: [
          {
            name: 'The Shawshank Redemption',
            url: 'https://movie.douban.com/subject/1292052/',
            imgUrl: '../../static/logo.png',
            rate: 9.6
          },
          {
            name: 'Leon:The Professional',
            url: 'https://movie.douban.com/subject/1295644/',
            imgUrl: '../../static/logo.png',
            rate: 9.4
          },
          {
            name: 'Farewell to My Concubine',
            url: 'https://movie.douban.com/subject/1291546/',
            imgUrl: '../../static/logo.png',
            rate: 9.5
          },
          {
            name: 'Forrest Gump',
            url: 'https://movie.douban.com/subject/1292720/',
            imgUrl: '../../static/logo.png',
            rate: 9.4
          },
          {
            name: 'Life Is Beautiful',
            url: 'https://movie.douban.com/subject/1292063/',
            imgUrl: '../../static/logo.png',
            rate: 9.5
          },
          {
            name: 'Spirited Away',
            url: 'https://movie.douban.com/subject/1291561/',
            imgUrl: '../../static/logo.png',
            rate: 9.2
          },
          {
            name: 'Schindler\'s List',
            url: 'https://movie.douban.com/subject/1295124/',
            imgUrl: '../../static/logo.png',
            rate: 9.4
          },
          {
            name: 'The Legend of 1900',
            url: 'https://movie.douban.com/subject/1292001/',
            imgUrl: '../../static/logo.png',
            rate: 9.2
          },
          {
            name: 'WALL·E',
            url: 'https://movie.douban.com/subject/2131459/',
            imgUrl: '../../static/logo.png',
            rate: 9.3
          },
          {
            name: 'Inception',
            url: 'https://movie.douban.com/subject/3541415/',
            imgUrl: '../../static/logo.png',
            rate: 9.2
          }
        ],
        randomMovieList: []
      }
    },
    methods: {
      postDetail: (key) => {
        // alert('hello')
        router.push('/detail')
        // router.push('/detail')
      },
      getHeight(){
        console.log(document.body.clientHeight)
        return document.body.clientHeight
      },
      changeLimit () {
        function getArrayItems (arr, num) {
          const temp_array = []
          for (let index in arr) {
            temp_array.push(arr[index])
          }
          const return_array = []
          for (let i = 0; i < num; i++) {
            if (temp_array.length > 0) {
              const arrIndex = Math.floor(Math.random() * temp_array.length)
              return_array[i] = temp_array[arrIndex]
              temp_array.splice(arrIndex, 1)
            } else {
              break
            }
          }
          return return_array
        }

        this.randomMovieList = getArrayItems(this.movieList, 5)
      }
    },
    mounted () {
      this.changeLimit()
      let w = global.screenWidth
      if(w < 800){
        this.show = false;
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
