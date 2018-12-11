# Blog 建站小记——从建项开始

## 前端

1. 使用终端进入项目文件夹中所在文件夹 `vue init webpack project-name`

   注意：会有一系列选项需要填写，可以一路`enter`，也可以有选择的输入一些内容进行初始化，比如项目名字，是否建立单元测试等

2. 引入iView组件库，在`main.js`中添加如下语句
  ```js
   import iView from 'iview'
   import 'iview/dist/styles/iview.css'
   
   Vue.use(iView)
  ```

3. 建立一个 `global.vue`文件来存放全局变量和方法

   ```vue
   <script type="text/javascript">
     const screenWidth = document.documentElement.clientWidth
   
     //导出
     export default{
       screenWidth
     }
   </script>
   ```

   其他地方需要使用只需要import此文件，然后使用即可

4. 建立相应的页面，并在`router`文件夹下的`ndex.js`文件中注册

5. 搭建相应的界面（页面的组织放在page文件夹下，通用组件放在component文件夹下）

6. 引入markdown编辑器

   `npm install mavon-editor --save`

   在`main.js`中添加下列语句

   ```js
   import mavonEditor from 'mavon-editor'
   import 'mavon-editor/dist/css/index.css'
   
   Vue.use(mavonEditor)
   ```

7. 引入markdown解析器，将markdown语句解析成html语句，最终还是在markdown编辑器上面展示

   `npm install showdown`

   在需要的地方添加下列语句

   ```js
   import showdown from 'showdown'
   let converter = new showdown.Converter()
   let html = converter.makeHtml(text)
   ```

8. 使用v-bind绑定变量，实现响应式布局

9. 
