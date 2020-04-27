import base from './baseQuery'
import METHOD from './HTTPmethod'
export default{
  getAllTags
}

async function getAllTags(resolve, reject) {
  await base.query({}, resolve, reject, '/tag/getAllTag', METHOD.GET)
}


