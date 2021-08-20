<template>
  <v-app>
    <v-main>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center" dense>
          <v-col cols="12" sm="8" md="4" lg="4">
            <v-card elevation="0">
              <v-img src="@/assets/images/projectOrange.png" alt="Project Orange Log" contain height="200"></v-img>
              <v-card-text>
                <validation-observer ref="observer" v-slot="{ invalid }">
                  <v-form ref="form" @submit.prevent="checkForm">
                    <validation-provider v-slot="{ errors }" name="이메일" :rules="{ required: true, email: true }">
                      <v-text-field label="Enter your email" v-model="email" name="email" prepend-inner-icon="mdi-email" type="email" class="rounded-0" :error-messages="errors" clearable outlined></v-text-field>
                    </validation-provider>
                    <validation-provider v-slot="{ errors }" name="이름" :rules="{ required: true, kor: true }">
                      <v-text-field label="Enter your username" v-model="username" name="username" prepend-inner-icon="mdi-account" class="rounded-0" :error-messages="errors" clearable outlined></v-text-field>
                    </validation-provider>
                    <validation-provider v-slot="{ errors }" name="닉네임" :rules="{ required: true, korAlphaNum: true }">
                      <v-text-field label="Enter your nickname" v-model="nickname" name="nickname" prepend-inner-icon="mdi-alphabetical" class="rounded-0" :error-messages="errors" clearable outlined></v-text-field>
                    </validation-provider>
                    <validation-provider v-slot="{ errors }" name="비밀번호" :rules="{ required: true, min: 8 }">
                      <v-text-field label="Enter your password" v-model="password" name="password" prepend-inner-icon="mdi-lock" type="password" class="rounded-0" :error-messages="errors" clearable outlined></v-text-field>
                    </validation-provider>
                    <validation-provider v-slot="{ errors }" name="비밀번호 확인" :rules="{ confirmed: '비밀번호', required: true, min: 8 }">
                      <v-text-field label="Enter your password confirm" v-model="passwordConfirm" name="passwordConfirm" prepend-inner-icon="mdi-lock" type="password" class="rounded-0" :error-messages="errors" clearable outlined></v-text-field>
                    </validation-provider>
                    <v-btn class="rounded-0" type="submit" block x-large color="orange" :disabled="invalid" @click="signUp">
                      Signup
                    </v-btn>
                    <v-card-actions class="text--secondary">
                      <v-spacer></v-spacer>
                      <!-- <router-link :to="{ name: 'SignUp' }">Sign Up</router-link> -->
                      account? <router-link to="/authentication/login" class="pl-2 text-decoration-none" style="color: #000000">Login</router-link>
                    </v-card-actions>
                  </v-form>
                </validation-observer>
              </v-card-text>
              <v-card-actions class="ml-6 mr-6 text-center">
                <p>Project Orange는 다같이 건강 챌린지를 진행하는<router-link to="/" class="pl-2" style="color: #000000">공간</router-link>입니다. Project Orange로 건강한 하루를 시작하세요.</p>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { registerUser } from '@/api/auth'

export default {
  name: 'SignupForm',
  data() {
    return {
      email: "",
      username: "",
      nickname: "",
      password: "",
      passwordConfirm: "",
    }
  },
  methods: {
    async checkForm () {
      await this.$refs.observer.validate()
    },
    async signUp() {
      try {
        const userData = {
          email: this.email,
          username: this.username,
          nickname: this.nickname,
          password: this.password,
          passwordConfirm: this.passwordConfirm,
        }
        const { data } = await registerUser(userData)
        // console.log(data)
        if (data.response == 'success') {
          alert(data.message)
          this.$router.push('/authentication/login')
        } else {
          alert(data.message)
        }
      } catch (error) {
        console.error(error)
        alert("회원 가입에 실패하였습니다.")
      } finally {
        this.initForm();
      }
    },
    initForm() {
      this.email = ''
      this.username = ''
      this.nickname = ''
      this.password = ''
      this.passwordConfirm = ''
    },
  }
}
</script>

<style>

</style>