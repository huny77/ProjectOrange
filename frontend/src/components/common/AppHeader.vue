<template>
  <div>
    <div class="d-flex justify-content-end mx-3">
      <!-- 로그인이 되었을 때 -->
      <template v-if="isUserLogin">
        <v-btn
          text
          @click="logoutUser"
        >
          로그아웃
        </v-btn>
      </template>
      <!-- 로그인이 되지 않았을 때 -->
      <template v-else>
        <v-btn 
          text
          @click="goLogin"
        >
          로그인
        </v-btn>
      </template>
    </div>
  </div>
</template>

<script>
import { deleteCookie } from '@/utils/cookies'

export default {
  name: 'AppHeader',
  methods: {
    goLogin() {
      this.$router.push({path:'/authentication/login'}).catch(()=> {});
    },
    goChall() {
      this.$router.push({path:'/challenge'}).catch(()=>{});
    },
    logoutUser() {
      this.$store.commit('clearUserId')
      this.$store.commit('clearToken')
      deleteCookie('til_auth')
      deleteCookie('til_user')
      this.$router.push({path:'/authentication/login'}).catch(()=>{});
    }
  },
  computed: {
    isUserLogin() {
      return this.$store.getters.isLogin;
    }
  }
}
</script>

<style>

</style>