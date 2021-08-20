import Vue from 'vue'
import Vuex from 'vuex'
import { 
  getAuthFromCookie,
  getUserFromCookie, 
  saveAuthToCookie, 
  saveUserToCookie  
} from '@/utils/cookies'
import { loginUser } from '@/api/auth'
import { listMain } from '@/api/main'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // token
    data1: getAuthFromCookie() || '',
    // userid
    data2: getUserFromCookie() || '',
  },
  getters: {
    isLogin(state) {
      return state.data2 !== '';
    }
  },
  mutations: {
    setToken(state, data1) {
      state.data1 = data1
    },
    setUserId(state, data2) {
      state.data2 = data2
    },
    clearUserId(state) {
      state.data2 = '';
    },
    clearToken(state) {
      state.data1 = '';
    },
  },
  actions: {
    async LOGIN({ commit }, userData) {
      const { data } = await loginUser(userData)
      // console.log(data)
      if (data.response == 'success') {
        commit('setToken', data.data1)
        commit('setUserId', data.data2)
        saveAuthToCookie(data.data1)
        saveUserToCookie(data.data2)
        alert(data.message)
        return data
      } else {
        alert(data.data1)
      }
    },
  }
})