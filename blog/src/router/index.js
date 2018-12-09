import Vue from 'vue'
import Router from 'vue-router'

import HomePage from '../pages/HomePage'
import DetailPage from '../pages/DetailPage'
import PostBlogPage from '../pages/PostBlogPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/detail',
      name: 'DetailPage',
      component: DetailPage
    },
    {
      path: '/post',
      name: 'PostBlogPage',
      component: PostBlogPage
    }
  ]
})
