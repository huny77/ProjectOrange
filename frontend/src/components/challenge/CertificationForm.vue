<template>
  <v-container>
    <v-row dense>
      <v-col
        v-for="(feedItem, index) in feedItems"
        :key="index"
      >
        <router-link :to="`/feed/${feedItem.articleId}`" class="text-decoration-none">
          <v-card
            class="mx-auto my-5"
            max-width="800"
          >
            <v-img
              src="@/assets/images/article.jpg"
              height="300px"
            ></v-img>

            <v-card-title>
              {{ feedItem.title }}
            </v-card-title>

            <v-card-subtitle>
              {{ feedItem.articleWritetime | moment('YYYY-MM-DD')}}&nbsp;
            </v-card-subtitle>

            <v-card-text>
              {{ feedItem.articleContent }}
            </v-card-text>
            
          </v-card>
        </router-link> 
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'CertificationForm',
  data() {
    return {
      feedItems: [],
    }
  },
  methods: {
    async getFeed() {
      try {
        const challengeId = this.$route.params.id
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/article/challenge/${challengeId}`)
        // console.log(data)
        this.feedItems = data
      } catch (err) {
        console.error(err)
      }
    }
  },
  created() {
    this.getFeed()
  }
}
</script>

<style>

</style>