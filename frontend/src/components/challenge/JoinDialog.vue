<template>
  <v-col cols="auto">
    <v-dialog
      v-model="dialog"
      max-width="290"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="orange"
          text
          v-bind="attrs"
          v-on="on"
          width="50"
        >
          가입하기
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="text-h5">
          위 챌린지에 가입하시겠습니까?
        </v-card-title>
        <v-card-text>
          가입한다면 AGREE 버튼을 누르고, 가입하지 않는다면 DISAGREE 버튼을 눌러주세요.
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="red darken-1"
            text
            @click="dialog = false"
          >
            Disagree
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="joinChallenge"
          >
            Agree
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-col>
</template>

<script>
import axios from 'axios'
export default {
  name: 'JoinDialog',
  data() {
    return {
      dialog: false,
      challengeId: '',
      userId: '',
    }
  },
  methods: {
    async joinChallenge() {
      try {
        const userData = {
          challengeId: this.$route.params.id,
          userId: this.$store.state.data2,
        }
        const { data } = await axios.post('http://i5b102.p.ssafy.io:8181/api/challenge/register/new-user', userData)
        // console.log(data)
        if (data.result == 'Success') {
          alert('챌린지 가입이 완료되었습니다.')
          this.dialog= false
        } else if (data.result == 'Max Member Reached') {
          alert('이미 마감된 챌린지입니다.')
          this.dialog= false
        } else if (data.result == 'Already In Challenge') {
          alert('이미 가입한 챌린지입니다.')
          this.dialog= false
        } else {
          alert('챌린지에 가입할 수 없습니다.')
          this.dialog= false
        }
      } catch(error) {
        console.log(error)
      }
    }
  }
} 
</script>

<style>

</style>