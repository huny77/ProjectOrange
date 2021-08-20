<template>
  <v-app>
    <v-main>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center" dense>
          <v-col cols="6">
            <v-img src="@/assets/images/challengeImg.jpg" alt="Project Orange Log" contain height="800"></v-img>
          </v-col>
          <v-col cols="6">
            <v-card elevation="0">
              <v-card-subtitle class="text-center">챌린지는 여러분들이 만들어나가는 겁니다.
              <br>성의있는 제목과 내용을 작성해주세요.</v-card-subtitle>
              <v-card-text>
                <validation-observer ref="observer" v-slot="{ invalid }">
                  <v-form ref="form" @submit.prevent="submitForm">
                    <validation-provider v-slot="{ errors }" name="챌린지 제목" :rules="{ required: true, ChallengeTitle: true }">
                      <v-text-field label="챌린지 제목" v-model="challengeTitle" prepend-inner-icon="mdi-walk" class="rounded-0" :error-messages="errors" clearable outlined></v-text-field>
                    </validation-provider>
                    <validation-provider v-slot="{ errors }" name="카테고리" :rules="{ required: true }">
                      <v-select label="챌린지 카테고리" v-model="selectCategory" :items="selectCategoryItem" prepend-inner-icon="mdi-numeric-3-box-outline" class="rounded-0" :error-messages="errors" outlined></v-select>
                    </validation-provider>
                    <validation-provider v-slot="{ errors }" name="기간" :rules="{ required: true }">
                      <v-select label="기간" v-model="selectPeriod" :items="selectPeriodItem" prepend-inner-icon="mdi-numeric-6-box-outline" class="rounded-0" :error-messages="errors" outlined></v-select>
                    </validation-provider>
                    <validation-provider v-slot="{ errors }" name="인원" :rules="{ required: true }">
                      <v-text-field label="2명 이상의 최대 인원" v-model="maxMembers" prepend-inner-icon="mdi-human-handsup" class="rounded-0" :error-messages="errors" clearable outlined></v-text-field>
                    </validation-provider>
                    <validation-provider v-slot="{ errors }" name="챌린지 내용" :rules="{ required: true}">
                      <v-textarea label="챌린지 내용" v-model="challengeDescribe" prepend-inner-icon="mdi-comment-text-outline" class="rounded-0" :error-messages="errors" clearable outlined></v-textarea>
                    </validation-provider>
                    <v-btn class="rounded-0" type="submit" block x-large color="orange" :disabled="invalid" @click="createForm">
                      챌린지 생성
                    </v-btn>
                    <v-card-actions class="text--secondary">
                      <v-spacer></v-spacer>
                    <a href="javascript:history.back()" class="pl-2 text-decoration-none" style="color: #000000">뒤로 가기</a>
                    </v-card-actions>
                  </v-form>
                </validation-observer>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { createChallenge } from '@/api/challenge'

export default {
  name: 'CreateChallengeForm',
  data() {
    return {
      challengeTitle: '',
      challengeDescribe: '',
      slider: null,
      selectCategory: null,
      selectPeriod: null,
      maxMembers: '',
      minMembers: 1,
      periodId: '',
      startDate: '',
      managerId: '',
      categoryId: '',
      periodDataItem: [],
      categoryDataItem: [],

      selectCategoryItem: [
        "운동", "음식", "영양제",
      ],
      selectPeriodItem: [
        "15일", "30일", "60일", "100일", "180일", "1년",
      ],
    }
  },
  methods: {
    async submitForm () {
      await this.$refs.observer.validate()
    },
    async createForm () {
      try {
        let date = new Date()
        if (this.selectCategory == '운동') {
          this.categoryId = 1
        } else if (this.selectCategory == '음식') {
          this.categoryId = 2
        } else if (this.selectCategory == '영양제') {
          this.categoryId = 3
        }
        
        if (this.selectPeriod == '15일') {
          this.periodId = 1
        } else if (this.selectPeriod == '30일') {
          this.periodId = 2
        } else if (this.selectPeriod == '60일') {
          this.periodId = 3
        } else if (this.selectPeriod == '100일') {
          this.periodId = 4
        } else if (this.selectPeriod == '180일') {
          this.periodId = 5
        } else if (this.selectPeriod == '1년') {
          this.periodId = 6
        }

        const challengeData = {
          challengeTitle: this.challengeTitle,
          challengeDescribe: this.challengeDescribe,
          maxMembers: this.maxMembers,
          minMembers: this.minMembers,
          periodId: this.periodId,
          startDate: date.toISOString(),
          managerId: this.$store.state.data2,
          categoryId: this.categoryId,
          // totalPoint: 100,
        }
        const { data } = await createChallenge(challengeData)
        // console.log(data)
        if (data.result == 'matched' || data.result == 'unmatched') {
          alert('챌린지가 정상적으로 생성되었습니다.')
          this.$router.push('/')
        } else {
          alert('챌린지가 생성되지 않았습니다.')
        }
      } catch(error) {
        console.error(error)
      }
    },
  }
}

</script>

<style>

</style>