import METHOD from "./HTTPmethod"

export default {
  baseUrl: 'http://localhost:8081',
  query
}

/**
 *
 * @param {*} params
 * @param {*} resolve
 * @param {*} reject
 * @param {*} root
 * @param method
 */
async function query(params, resolve, reject, root, method) {
  try {
    let url = this.baseUrl + root
    let request = {}
    let headers = new Headers()
    headers.append("Content-Type", "application/json")
    request.headers = headers
    request.method = method

    if (method !== METHOD.GET) request.body = JSON.stringify(params)
    else {
      if (params !== {}) {
        url = url + '?'
        for (let key in params) {
          url = url + key + '=' + params[key] + '&'
        }
        url = url.substring(0, url.length - 1)
      }
    }
    let res = await fetch(url, request)
    let result = await res.text()

    resolve(res.status, result)
  } catch (e) {
    reject(e)
  }
}
