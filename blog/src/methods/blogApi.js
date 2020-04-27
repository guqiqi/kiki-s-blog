import base from './baseQuery'
import METHOD from './HTTPmethod'
export default{
  getAllBlog,
  postBlog,
  getBlogById,
  getBlogByYear,
  getBlogByTag
}

async function getAllBlog(resolve, reject) {
  await base.query({}, resolve, reject, '/getAllBlog', METHOD.GET)
}

async function getBlogByYear(year, resolve, reject) {
  await base.query({year: year}, resolve, reject, '/getBlogByYear', METHOD.GET)
}

async function getBlogByTag(tag, resolve, reject) {
  await base.query({tag: tag}, resolve, reject, '/getBlogByTag', METHOD.GET)
}

async function postBlog(b_title, b_summary, b_content, b_tags, resolve, reject) {
  let blog = {}
  blog.title = b_title;
  blog.summary = b_summary
  blog.content = b_content
  blog.tags = b_tags

  await base.query(blog, resolve, reject, '/postBlog', METHOD.POST)
}

async function getBlogById(id, resolve, reject) {
  await base.query({id: id}, resolve, reject, '/getBlog', METHOD.GET)
}

