<template>
  <v-container>
    <v-tabs
      v-model="tabs"
      centered
      class="my-5"
    >
      <v-tab>
        진행 중인 챌린지
      </v-tab>
      <v-tab>
        작성한 피드
      </v-tab>
      
    </v-tabs>
    <v-tabs-items v-model="tabs">
      <v-tab-item>
        <!-- 반복되는 카드 -->
        <v-col
          v-for="(myData, title) in myDatas" cols="12"
          :key="title"  
        >
          <v-card fill-height>
            <template v-if="myData.categoryId == '1'">
              <router-link :to="`/challenge/${myData.challengeId}`" class="text-decoration-none">
              <v-img
                src="@/assets/images/health.png"
                class="white--text align-end px-3"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="300px"
              >
                <v-card-title>{{ myData.challengeTitle }}</v-card-title>
                <v-card-text>팀 점수: {{ myData.totalPoint }}</v-card-text>
              </v-img>
              </router-link>
            </template>

            <template v-if="myData.categoryId == '2'">
              <router-link :to="`/challenge/${myData.challengeId}`" class="text-decoration-none">
              <v-img
                src="@/assets/images/food.png"
                class="white--text align-end px-3"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="300px"
              >
                <v-card-title>{{ myData.challengeTitle }}</v-card-title>
                <v-card-text>팀 점수: {{ myData.totalPoint }}</v-card-text>
              </v-img>
              </router-link>
            </template>

            <template v-if="myData.categoryId == '3'">
              <router-link :to="`/challenge/${myData.challengeId}`" class="text-decoration-none">
              <v-img
                src="@/assets/images/nutrients.jpg"
                class="white--text align-end px-3"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="300px"
              >
                <v-card-title>{{ myData.challengeTitle }}</v-card-title>
                <v-card-text>팀 점수: {{ myData.totalPoint }}</v-card-text>
              </v-img>
              </router-link>
            </template>

            <v-card-actions>
              <v-spacer></v-spacer>
            
              <v-btn text color="orange">
                챌린지 상세보기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
        <!-- 반복되는 카드 끝 -->
      </v-tab-item>
      
      <v-tab-item>
        <!-- 반복되는 피드 -->
        <v-col
          v-for="(feedData, index) in feedDatas"
          :key="index"
        >
          <router-link :to="`/feed/${feedData.articleId}`" class="text-decoration-none">
            <v-card
              class="mx-auto my-5"
              max-width="800"
            >
              <v-img
                src="@/assets/images/article.jpg"
                height="300px"
              ></v-img>

              <v-card-title>
                {{ feedData.title }}
              </v-card-title>

              <v-card-subtitle>
                {{ feedData.articleWritetime }}
              </v-card-subtitle>

              <v-card-text>
                {{ feedData.articleContent }}
              </v-card-text>
              
            </v-card>
          </router-link>
        </v-col>
        <!-- 반복되는 피드 끝 -->
      </v-tab-item>
    </v-tabs-items>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ProfileTab',
  data() {
    return { 
      tabs: null,
      myDatas: [],
      feedDatas: [],
    };
  },
  methods: {
    async userData() {
      try {
        const userId = this.$store.state.data2
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/challenge/search/user/${userId}`)
        this.myDatas = data
        // console.log(data)
      } catch(err) {
        console.log(err)
      }  
    },
    async userFeed() {
      try {
        const userId = this.$store.state.data2
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/article/list/${userId}`)
        this.feedDatas = data
        // console.log(data)
      } catch(err) {
        console.log(err)
      }  
    },
  },
  created() {
    this.userData()
    this.userFeed()
  }
}
</script>

<style>

</style>