<template>
  <v-container>
    <v-card flat>
      <v-row>
        <v-col align-self="center">
          <template v-if="profileImg=='error'">
            <v-img
              src="@/assets/images/basic.png"
              class="profile_image"
            ></v-img>
          </template>
          <template v-else>
            <v-img
              :src="profileImg"
              class="profile_image"
            ></v-img>
          </template>  
        </v-col>
        <v-col class="my-auto">
          <v-row justify="space-between">
            <v-col cols="auto" class="align-self-center h4 my-5">
              {{ userDatas.nickname }}
            </v-col>

          </v-row>
          <v-col>
            <v-row justify="space-between">
              <v-col cols="auto">
                <v-tooltip right>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon
                      color="primary"
                      dark
                      v-bind="attrs"
                      v-on="on"
                    >
                      mdi-trophy-outline
                    </v-icon>
                  </template>
                  <span>현재 갖고 있는 뱃지</span>
                </v-tooltip>
              </v-col>
              <v-col cols="auto">
                <BadgeDialog />
              </v-col>
            </v-row>
          </v-col>
          <v-divider class="ma-4"></v-divider>
          <v-col>
          <v-container class="d-flex flex-row justify-space-between text-center">
            <v-flex>
              <div>
                <div class="caption grey--text">현재 가입중인 챌린지</div>
              </div>
            </v-flex>
            <v-flex>
              <div class="subtitle-2">{{ challengeDates.length }}</div>
            </v-flex>
          </v-container>
          </v-col>
          <v-divider class="ma-4"></v-divider>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import BadgeDialog from '@/components/user/BadgeDialog.vue'
import FollowerDialog from '@/components/user/FollowerDialog.vue'
import FollowingDialog from '@/components/user/FollowingDialog.vue'
import axios from 'axios'

export default {
  name: 'ProfileForm',
  components: {
    BadgeDialog,
    FollowerDialog,
    FollowingDialog,
  },
  data() {
    return {
      userDatas: [],
      challengeDates: [],
      profileImg: '',
      userId: '',
    }
  },
  methods: {
    async myProfile() {
      try {
        const userId = this.$store.state.data2
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/user/userinfo/${userId}`)
        // console.log(data)
        this.userDatas = data.data1
      } catch (err) {
        console.log(err)
      }
    },
    async challengeDate() {
      try {
        const userId = this.$store.state.data2
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/challenge/search/user/${userId}`)
        this.challengeDates = data
        // console.log(data)
      } catch(err) {
        console.log(err)
      }  
    },
    async getProfileImg() {
      try {
        const userId = this.$store.state.data2
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/image/get/profile/${userId}`)
        console.log(data.result)
        if (data.result == null) {
          // console.log('이미지 없음')
          this.profileImg = 'error'
        } else {
          this.profileImg = data.result
        }
      } catch (err) {
        console.error(err)
      }
    },
    goEdit() {
      const userId = this.$store.state.data2
      this.$router.push({path:`/user/${userId}`}).catch(()=> {});
    },
  },
  created() {
    this.myProfile()
    this.challengeDate()
    this.getProfileImg()
  }
}
</script>

<style>
.profile_image {
  width: 200px;
  height: 200px; 
  border-radius: 70%;
  overflow: hidden;
  display: block;
  margin: auto;
  border: black;
}
</style>