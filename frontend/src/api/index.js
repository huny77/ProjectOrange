import axios from 'axios';
import { setInterceptors } from './common/interceptors';

// 엑시오스 초기화 함수
function createInstance() {
  const instance = axios.create({
    baseURL: 'http://i5b102.p.ssafy.io:8181',
  })
  return setInterceptors(instance);
}
const instance = createInstance();

// 회원가입 API
function registerUser(userData) {
  return instance.post('/api/user/signup', userData)
}

// 로그인 API
function loginUser(userData) {
  return instance.post('/api/user/login', userData)
}


export { registerUser, loginUser };