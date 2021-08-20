import axios from 'axios';
import { setInterceptors } from './common/interceptors';

// 엑시오스 초기화 함수
function createInstance() {
  const instance = axios.create({
    baseURL: 'http://i5b102.p.ssafy.io:8181',
  })
  return setInterceptors(instance);
}
export const instance = createInstance();