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

9. 自己搭建组件（navigation），以供其他使用



## 后端

1. 使用idea创建spring boot项目，默认配置maven，同时声明是一个web项目

   `打开项目之后在idea右下角有一个提示，选择enable auto-import，即根据maven配置自动下载jar包`

2. 修改pom.xml文件，使项目支持mysql和hibernate

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-thymeleaf</artifactId>
   </dependency>
   
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
   </dependency>
   <dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>fastjson</artifactId>
       <version>1.2.46</version>
   </dependency>
   ```

3. 修改application.properties文件，进行hibernate配置
   ```properties
   # server
   server.port=8081
   server.tomcat.uri-encoding=utf-8
   
   # Mysql
   spring.datasource.url =jdbc:mysql://localhost:3306/blog？useUnicode=true&characterEncoding=utf-8&useSSL=false
   spring.datasource.username = root
   spring.datasource.password = 123456
   spring.datasource.driverClassName = com.mysql.jdbc.Driver
   
   # JPA
   # Specify the DBMS
   spring.jpa.database = MYSQL
   # Show or not log for each sql query
   spring.jpa.show-sql = true
     
   # Hibernate ddl auto (create, create-drop, update)
   spring.jpa.hibernate.ddl-auto = update
   
   # Naming strategy
   spring.jpa.hibernate.naming_strategy = org.hibernate.cfg.ImprovedNamingStrategy
   # stripped before adding them to the entity manager)
   spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
   
   #视图层控制
   spring.mvc.view.prefix=classpath:/templates/
   spring.mvc.view.suffix=.html
   spring.mvc.static-path-pattern=/static/**
   ```

4. 创建JPA配置文件 `JpaConfiguration`

   ```java
   @Order(Ordered.HIGHEST_PRECEDENCE)
   @Configuration
   @EnableTransactionManagement(proxyTargetClass = true)
   @EnableJpaRepositories(basePackages = "kiki.blog.backend.repository")
   @EntityScan(basePackages = "kiki.blog.backend.entity")
   public class JpaConfiguration {
       @Bean
       PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
           return new PersistenceExceptionTranslationPostProcessor();
       }
   }
   ```

5. 创建实体类 `Blog`

   ```java
   @Entity //加入这个注解，Demo就会进行持久化了
   @Table(name="blog") //和数据库表进行映射
   public class Blog {
       @Id
       @GeneratedValue
       private int id;
       @Column(name="title")
       private String title;
       @Column(name="summary")
       private String summary;
       @Column(name="content")
       private String content;
       @Column(name="writer")
       private String writer;
       @Column(name="date")
       private String date;
       @Column(name="reader")
       private int reader;
   
       public int getId() {
           return id;
       }
   
       public void setId(int id) {
           this.id = id;
       }
   
       public String getTitle() {
           return title;
       }
   
       public void setTitle(String title) {
           this.title = title;
       }
   
       public String getSummary() {
           return summary;
       }
   
       public void setSummary(String summary) {
           this.summary = summary;
       }
   
       public String getContent() {
           return content;
       }
   
       public void setContent(String content) {
           this.content = content;
       }
   
       public String getWriter() {
           return writer;
       }
   
       public void setWriter(String writer) {
           this.writer = writer;
       }
   
       public String getDate() {
           return date;
       }
   
       public void setDate(String date) {
           this.date = date;
       }
   
       public int getReader() {
           return reader;
       }
   
       public void setReader(int reader) {
           this.reader = reader;
       }
   }
   ```

6. 创建数据库查询方法类 repository，类似dao

   ```java
   @Repository
   @EnableJpaRepositories(basePackages = "kiki.blog.backend.repository")
   @EntityScan(basePackages = "kiki.blog.backend.entity")
   public interface BlogRepository extends JpaRepository<Blog,Long> {
       @Query("select t from Blog t")
       List<Blog> find();
   }
   ```

7. 创建service类（由于比较简单，没有service和serviceImpl分开）

   ```java
   @Service
   public class BlogService {
       @Autowired
       private BlogRepository blogRepository;
   
       public List<Blog> getAllBlog(){
           List<Blog> blogList = null;
           try {
               blogList = blogRepository.find();
           } catch (Exception e) {
           }
           return blogList;
       }
   }
   ```

8. 创建controller类

   ```java
   @Controller
   @RequestMapping(value = "/")
   public class BlogController {
       @Autowired
       private BlogService blogService;
   
       @RequestMapping("getAllBlog")
       @ResponseBody
       private List<Blog> getAllUser() {
           List<Blog> blogs = blogService.getAllBlog();
   
           return blogs;
       }
   }
   ```

9. 启动后端即可

