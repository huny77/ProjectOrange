<template>
  <v-container class="text-center">
    <v-card
    flat
    class="mx-auto my-12"
    max-width="800"
  >

    <v-card-title
    class="text-h4 text--primary justify-center"
    ><b>About Challenge</b></v-card-title>
    <div class="mt-4 text-subtitle-1">
        <v-row>
          <v-col></v-col>
          <v-col>
            <v-icon>mdi-calendar-multiple-check </v-icon> &nbsp;
              {{ challengeItems.startDate | moment('YYYY-MM-DD')}}&nbsp;  ~  &nbsp;{{ challengeItems.endDate | moment('YYYY-MM-DD')}}</v-col>
          <v-col></v-col>
        </v-row>
      </div>


    <v-card-text>
      <v-row>
        <v-col></v-col>
        <v-col>
          <v-alert
            text
            dense
            shaped
            color="deep-orange"
            icon="mdi-fire"
            class="text-left"
          >
            현재 포인트 : {{ challengeItems.totalPoint }}
          </v-alert>

        </v-col>
        <v-col></v-col>
      </v-row>

    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title>팀소개</v-card-title>

    <v-card-text>
      {{ challengeItems.challengeDescribe }}
    </v-card-text>
  </v-card>

  </v-container>
  
</template>

<script>
import axios from 'axios';

export default {
  name: 'IntroduceForm',
  data() {
    return {
      challengeItems: [],
    }
  },
  methods: {
    async getChallenge() {
      try {
        const challengeId = this.$route.params.id
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/challenge/${challengeId}`)
        // console.log(data)
        this.challengeItems = data
      } catch (err) {
        console.log(err)
      }
    }
  },
  created() {
    this.getChallenge()
  }
}
</script>

<style>

</style>