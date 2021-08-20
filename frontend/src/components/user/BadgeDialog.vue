<template>
  <v-container>
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
       
        width="300px"
        height="300px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            outlined
            small
            v-bind="attrs"
            v-on="on"
          >
            Badge
          </v-btn>
        </template>
        <v-spacer></v-spacer>
        <v-card style="padding-top:20px; padding-down:5px;" flat>
          <v-card-text>
            <p class="text-center text-h5 font-weight-bold">나의 배지</p>
          </v-card-text>
          <v-divider style="margin-top:0px; padding-top:0px;"></v-divider>

          <v-card flat v-for="(badge, i) in userbadges" :key="i">
            <div v-if="badge.badge == '1'">
              <v-img
                src="@/assets/images/badge/badge1.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">Here comes a new challenger!</p>
            </div>
            <div v-if="badge.badge == '2'">
              <v-img
                src="@/assets/images/badge/badge2.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">손에 손 잡고, 첫 챌린지 개최</p>
            </div>
            <div v-if="badge.badge == '3'">
              <v-img
                src="@/assets/images/badge/badge3.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">와우 친구들, 대머리 아저씨야</p>
            </div>
            <div v-if="badge.badge == '4'">
              <v-img
                src="@/assets/images/badge/badge4.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">이제 바질을 곁들인, '식단' 첫 참여</p>
            </div>
            <div v-if="badge.badge == '5'">
              <v-img
                src="@/assets/images/badge/badge5.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">약 먹을 시간, '영양제' 첫 참여</p>
            </div>
            <div v-if="badge.badge == '6'">
              <v-img
                src="@/assets/images/badge/badge6.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">삼위일체, 모든 챌린지 참여</p>
            </div>
            <div v-if="badge.badge == '7'">
              <v-img
                src="@/assets/images/badge/badge7.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">내 동료가 돼라!</p>
            </div>
            <div v-if="badge.badge == '8'">
              <v-img
                src="@/assets/images/badge/badge8.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">우린 이제 칭구칭긔</p>
            </div>
            <div v-if="badge.badge == '9'">
              <v-img
                src="@/assets/images/badge/badge9.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">나, 강림, 최초 게시글 작성</p>
            </div>
            <div v-if="badge.badge == '10'">
              <v-img
                src="@/assets/images/badge/badge10.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">무플방지위원회</p>
            </div>
            <div v-if="badge.badge == '11'">
              <v-img
                src="@/assets/images/badge/badge11.png"
                width="100" height="100"
                class="badge-position"
              ></v-img>
              <p class="text-center">난 필연적인 존재다</p>
            </div>
          </v-card>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="red darken-1"
              text
              @click="dialog = false"
            >
              close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
import { badgeList } from '@/api/challenge' 

export default {
  name: 'BadgeDialog',
  data () {
    return {
      dialog: false,
      userbadges: [],
      badges: [],
    }
  },
  methods: {
    async getUserBadge() {
      try {
        const userId = this.$store.state.data2
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/badgesusers/${userId}`)
        // console.log(data)
        this.userbadges = data
      } catch (err) {
        console.error(err)
      }
    },
    async getAllBadge() {
      try {
        const { data } = await badgeList()
        // console.log(data)
        this.badges = data
      } catch (err) {
        console.error(err)
      }
    },

  },
  created() {
    this.getUserBadge()
    this.getAllBadge()
  }
}
</script>
<style>
.badge-position {
  display: block; margin: 0px auto;
}
</style>