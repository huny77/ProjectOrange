<template>
  <v-container>
    <v-card flat>
      <div class="text-h3 text-center mb-10">랭킹</div>
      <v-card-title>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="챌린지 제목을 입력해주세요"
          single-line
          hide-details
        ></v-text-field>
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="teams"
        :items-per-page="10"
        :search="search"
        class="elevation-1"
      ></v-data-table>
    </v-card>
  </v-container>
</template>

<script>
import { popularMain } from '@/api/main'

export default {
  name: 'RankingForm.vue',
  data() {
    return {
      search: '',
      headers: [
        {
          text: '챌린지 제목',
          align: 'start',
          sortable: true,
          value: 'challengeTitle',
        },
        { text: '점수', value: 'totalPoint' }
      ],
      teams: [],
    }
  },
  methods: {
    async ranking() {
      try {
        const { data } = await popularMain()
        this.teams = data
        // console.log(data)
      } catch(err) {
        console.log(err)
      }
    }
  },
  created() {
    this.ranking()
  },
}
</script>

<style>

</style>