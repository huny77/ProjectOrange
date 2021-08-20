<template>
  <v-container>
    <v-card>
      <template v-if="challengeImg=='error'">
        <template v-if="challengeItems.categoryId=='1'">
          <v-img
            src="@/assets/images/health.png"
            height="400"
            class="black--text align-center"
          >
            <v-card-title class="justify-center text-h2 font-weight-bold">
              {{ challengeItems.challengeTitle }}
            </v-card-title>
            <div class="text-center">
              <v-chip
                class="ma-2 mt-8"
                outlined
                color="black"
              >
              참여 : {{challengeItems.currentMembers}} 명
              </v-chip>
            </div>
          </v-img>
        </template>
        <template v-if="challengeItems.categoryId=='2'">
          <v-img
            src="@/assets/images/food.png"
            height="400"
            class="black--text align-center"
          >
            <v-card-title class="justify-center text-h2 font-weight-bold">
              {{ challengeItems.challengeTitle }}
            </v-card-title>
            <div class="text-center">
              <v-chip
                class="ma-2 mt-8"
                outlined
                color="black"
              >
              참여 : {{challengeItems.currentMembers}} 명
              </v-chip>
            </div>
          </v-img>
        </template>
        <template v-if="challengeItems.categoryId=='3'">
          <v-img
            src="@/assets/images/nutrients.jpg"
            height="400"
            class="black--text align-center"
          >
            <v-card-title class="justify-center text-h2 font-weight-bold">
              {{ challengeItems.challengeTitle }}
            </v-card-title>
            <div class="text-center">
              <v-chip
                class="ma-2 mt-8"
                outlined
                color="black"
              >
              참여 : {{challengeItems.currentMembers}} 명
              </v-chip>
            </div>
          </v-img>
        </template>
      </template>
      <template v-else>
        <v-img
          :src="challengeImg"
          height="400"
          class="black--text align-center"
        >
          <v-card-title class="justify-center text-h2 font-weight-bold">
            {{ challengeItems.challengeTitle }}
          </v-card-title>
          <div class="text-center">
            <v-chip
              class="ma-2 mt-8"
              outlined
              color="black"
            >
            참여 : {{challengeItems.currentMembers}} 명
            </v-chip>
          </div>
        </v-img>
      </template>
      <v-card-actions>
        <v-spacer></v-spacer>
        <div class="btn-flex d-flex">
          <v-col>
            <v-btn 
              text 
              color="blue"
              @click="goRanking"
            >
              랭킹
            </v-btn>
          </v-col>
          
          <JoinDialog />
        </div>
        
      </v-card-actions>
    </v-card>
    <FeedTab />
  </v-container>
</template>

<script>
import axios from 'axios'
import FeedTab from '@/components/challenge/FeedTab.vue'
import JoinDialog from '@/components/challenge/JoinDialog.vue'
import { challengeDetail } from '@/api/challenge'
export default {
  name: 'FeedForm',
  components: {
    FeedTab,
    JoinDialog,
  },
  data() {
    return {
      challengeItems: [],
      challengeImg: ''
    }
  },
  methods: {
    goRanking() {
      this.$router.push({path:'/ranking'}).catch(()=> {});
    },
    async getChallengeImg() {
      try {
        const challengeId = this.$route.params.id
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/image/get/challenge/${challengeId}`)
        // console.log(data.result)
        if (data.result == 'http://i5b102.p.ssafy.io:8181/api/image/show/default_image') {
          // console.log('이미지 없음')
          this.challengeImg = 'error'
        } else {
          this.challengeImg = data.result
        }
      } catch (err) {
        console.error(err)
      }
    },
    async detailItem() {
      try {
        const challengeId = this.$route.params.id
        const { data } = await challengeDetail(challengeId)
        // console.log(data)
        this.challengeItems = data
      } catch (err) {
        console.error(err)
      }
    }
  },
  created() {
    this.getChallengeImg()
    this.detailItem()
  }
}
</script>

<style>
.btn-flex {
  justify-content: end;
}
</style>