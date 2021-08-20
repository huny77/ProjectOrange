import store from "@/store/index";

export function setInterceptors(instance) {
  // 요청 인터셉터 추가
  instance.interceptors.request.use(
    async function (config) {
      config.headers.Authorization = store.state.data1;
      return await config;
    },
    function (error) {
      return Promise.reject(error);
    }
  );
    
    // 응답 인터셉터 추가
  instance.interceptors.response.use(
   async function (response) {
      return await response;
    },
    function (error) {
      return Promise.reject(error);
    }
  );
  return instance;
}