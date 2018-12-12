import base from './baseQuery'
import METHOD from './HTTPmethod'
export default{
  getAllBlog,
  postBlog,
  getBlogById
}

async function getAllBlog(resolve, reject) {
  await base.query({}, resolve, reject, '/getAllBlog', METHOD.GET)
}

async function postBlog(b_title, b_summary, b_content, resolve, reject) {
  let blog = {}
  blog.title = b_title;
  blog.summary = b_summary
  blog.content = b_content

  await base.query(blog, resolve, reject, '/postBlog', METHOD.POST)
}

async function getBlogById(id, resolve, reject) {
  await base.query({id: id}, resolve, reject, '/getBlog', METHOD.GET)
}

