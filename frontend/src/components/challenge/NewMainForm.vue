<template>
  <v-container fluid class="mx-auto">
    <v-row dense>
      <v-col
        v-for="(card, title) in cards" class="col-3 mb-10 pa-2 col-sm-6 col-md-3"
        :key="title"
        :cols="card.flex"
      >
        <v-hover v-slot="{ hover }">
          <v-card fill-height :class="{ 'on-hover': hover }" :elevation="hover ? 16 : 2">
            <template v-if="card.categoryId == '1'">
              <router-link :to="`/challenge/${card.challengeId}`" class="text-decoration-none">
              <v-img
                src="@/assets/images/health.png"
                class="white--text align-end px-3"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="300px"
              >
                <v-card-title>{{ card.challengeTitle }}</v-card-title>
                <!-- <v-card-subtitle class="text-end">{{ card.startDate | moment('YYYY-MM-DD')}}&nbsp; ~ &nbsp;{{ card.endDate | moment('YYYY-MM-DD')}}</v-card-subtitle> -->
              </v-img>
              </router-link>
            </template>
            
            <template v-else-if="card.categoryId =='2'">
              <router-link :to="`/challenge/${card.challengeId}`" class="text-decoration-none">
              <v-img
                src="@/assets/images/food.png"
                class="white--text align-end px-3"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="300px"
              >
                <v-card-title>{{ card.challengeTitle }}</v-card-title>
                <!-- <v-card-subtitle class="text-end">{{ card.startDate | moment('YYYY-MM-DD')}}&nbsp; ~ &nbsp;{{ card.endDate | moment('YYYY-MM-DD')}}</v-card-subtitle> -->
              </v-img>
              </router-link>
            </template>

            <template v-else-if="card.categoryId == '3'">
              <router-link :to="`/challenge/${card.challengeId}`" class="text-decoration-none">
              <v-img
                src="@/assets/images/nutrients.jpg"
                class="white--text align-end px-3"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="300px"
              >
                <v-card-title>{{ card.challengeTitle }}</v-card-title>
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
                {{ card.totalPoint }} 점
              </v-chip>
            </v-card-actions>
          </v-card>
        </v-hover>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { newMain } from '@/api/main'

export default {
  name: 'NewMainForm',
  data() {
    return {
      cards: [],
    }
  },
  methods: {
    async newFeed() {
      try {
        const { data } = await newMain()
        this.cards = data.slice(0, 8)
        // console.log(data)
      } catch(err) {
        console.log(err)
      }
    }
  },
  created() {
    this.newFeed()
  }
}
</script>