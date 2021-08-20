<template>
  <v-container fluid class="mx-auto">
    <v-row dense>
      <template v-if="searchedItems=='error'">
        <v-card
          class="mx-auto"
          max-width="800"
        >
          <v-card-text>
            <p class="text-h4 text--primary mb-10">
              "{{ this.$route.params.title }}"에 대한 검색결과가 없습니다.
            </p>

            <div class="text--primary">
              • 단어의 철자가 정확한지 확인해 보세요.
            </div>
            <div class="text--primary">
              • 한글을 영어로 혹은 영어를 한글로 입력했는지 확인해 보세요.
            </div>
            <div class="text--primary">
              • 해당 프로젝트 명이 맞는지 확인해 보세요.
            </div>
          </v-card-text>
          <v-card-actions>
            <v-btn
              text
              color="deep-orange accent-4"
              @click="goHome()"
            >
              Project Orange로 이동
            </v-btn>
          </v-card-actions>
        </v-card>
      </template>
      <template v-else>
        <v-col
          v-for="(searchedItem, title) in searchedItems" class="col-3 mb-10 pa-2 col-sm-6 col-md-3"
          :key="title"
          :cols="searchedItem.flex"
        >
          <v-hover v-slot="{ hover }">
            <v-card :class="{ 'on-hover': hover }" :elevation="hover ? 16 : 2">
              <template v-if="searchedItem.categoryId == '1'">
                <router-link :to="`/challenge/${searchedItem.challengeId}`" class="text-decoration-none">
                <v-img
                  src="@/assets/images/health.png"
                  class="white--text align-end px-3"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  height="300px"
                >
                  <v-card-title>{{ searchedItem.challengeTitle }}</v-card-title>
                  <!-- <v-card-subtitle class="text-end">{{ card.startDate | moment('YYYY-MM-DD')}}&nbsp; ~ &nbsp;{{ card.endDate | moment('YYYY-MM-DD')}}</v-card-subtitle> -->
                </v-img>
                </router-link>
              </template>
              
              <template v-else-if="searchedItem.categoryId =='2'">
                <router-link :to="`/challenge/${searchedItem.challengeId}`" class="text-decoration-none">
                <v-img
                  src="@/assets/images/food.png"
                  class="white--text align-end px-3"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  height="300px"
                >
                  <v-card-title>{{ searchedItem.challengeTitle }}</v-card-title>
                  <!-- <v-card-subtitle class="text-end">{{ card.startDate | moment('YYYY-MM-DD')}}&nbsp; ~ &nbsp;{{ card.endDate | moment('YYYY-MM-DD')}}</v-card-subtitle> -->
                </v-img>
                </router-link>
              </template>

              <template v-else-if="searchedItem.categoryId == '3'">
                <router-link :to="`/challenge/${searchedItem.challengeId}`" class="text-decoration-none">
                <v-img
                  src="@/assets/images/nutrients.jpg"
                  class="white--text align-end px-3"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  height="300px"
                >
                  <v-card-title>{{ searchedItem.challengeTitle }}</v-card-title>
                  <!-- <v-card-subtitle class="text-end">{{ card.startDate | moment('YYYY-MM-DD')}}&nbsp; ~ &nbsp;{{ card.endDate | moment('YYYY-MM-DD')}}</v-card-subtitle> -->
                </v-img>
                </router-link>
              </template>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-chip
                  class="ma-2"
                  color="orange accent-4"
                  outlined
                >
                  {{ searchedItem.totalPoint }} 점
                </v-chip>
              </v-card-actions>
            </v-card>
          </v-hover>
        </v-col>
      </template>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Search',
  data() {
    return {
      title: '',
      searchedItems: [],
    }
  },
  methods: {
    async searchTitle() {
      try {
        const title = this.$route.params.title
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/challenge/search/title/${title}`)
        // console.log(data)
        if (data == '') {
          this.searchedItems = 'error'
        } else {
          this.searchedItems = data
        }
      } catch (err) {
        console.error(err)
      }
    },
    goHome() {
      this.$router.push({path:'/'}).catch(()=> {});
      location.reload()
    }, 
  },
  created() {
    this.searchTitle()
  }

}
</script>

<style>

</style>