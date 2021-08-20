import { instance } from './index'

// 회원가입 API
function registerUser(userData) {
  return instance.post('/api/user/signup', userData)
}
  
// 로그인 API
function loginUser(userData) {
  return instance.post('/api/user/login', userData)
}

export {
  registerUser,
  loginUser,
}