<template>
  <v-container>
    <v-card outlined>
      <v-row d-flex>
        <v-col outlined>
          <template v-if="feedImg=='error'">
            <v-img
              src="@/assets/images/article.jpg"
              height="500"
              max-width="600"
            >
            </v-img>
          </template>
          <template v-else>
            <v-img
              :src="feedImg"
              height="500"
              max-width="600"
            >
            </v-img>
          </template>
        </v-col>
        <v-col>
          <v-card-title>{{ feedItems.title }}</v-card-title>
          <v-card-subtitle class="mt-1">{{ feedItems.articleContent }}</v-card-subtitle>
          <v-divider></v-divider>
          <v-card-text
            v-for="(CommentItem, index) in CommentItems"
            :key="index"
            height="400"
            max-height="400"
          >
            🍊 : {{ CommentItem.commentContent }} 
            <!-- <p class="text-end data-color">{{ CommentItem.commentWritetime| moment('YYYY-MM-DD')}}&nbsp;</p> -->
          </v-card-text>
          <div class="input-group mb-3">
            <input v-model.trim="commentContent" type="text" class="form-control border-warning" placeholder="댓글">
            <div class="text-end"><button class="btn btn-outline-warning" @click="newComment()">작성</button></div>
          </div>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: 'FeedDetailForm',
  data() {
    return {
      feedItems: [],
      CommentItems: [],
      commentContent: '',
      user: '',
      article: '',
      feedImg: '',
    }
  },
  methods: {
    async getFeed() {
      try {
        const articleId = this.$route.params.id
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/article/${articleId}`)
        // console.log(data)
        this.feedItems = data
      } catch (err) {
        console.log(err)
      }
    },
    async getFeedImg() {
      try {
        const articleId = this.$route.params.id
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/image/get/article/${articleId}`)
        // console.log(data)
        if (data == '') {
          this.feedImg = 'error'
        } else {
          this.feedImg = data[0]
        }
      } catch (err) {
        console.log(err)
      }
    },
    async getComment() {
      try {
        const articleId = this.$route.params.id
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/comment/article/${articleId}`)
        // console.log(data)
        this.CommentItems = data
      } catch (err) {
        console.log(err)
      }
    },
    async newComment() {
      try {
        const commentData = {
          commentContent: this.commentContent,
          user: this.$store.state.data2,
          article: this.$route.params.id,
        }
        const { data } = await axios.post('http://i5b102.p.ssafy.io:8181/api/comment/create', commentData)
        // console.log(data)
        location.reload()
      } catch(error) {
        console.log(error)
      }
    },
  },
  created() {
    this.getFeed()
    this.getComment()
    this.getFeedImg()
  }
}
</script>

<style>
.data-color {
  color: gray;
}
</style>