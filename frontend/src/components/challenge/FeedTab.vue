<template>
  <v-card>
    <v-tabs
      v-model="tab"
      centered
    >
      <v-tab>
        소개
      </v-tab>
      <v-tab>
        인증목록
      </v-tab>
      <v-tab>
        1:1 배틀
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item>
        <IntroduceForm />
      </v-tab-item>
      <v-tab-item>
        <CertificationForm />
      </v-tab-item>
      <v-tab-item>
        <BattleForm />
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
import IntroduceForm from '@/components/challenge/IntroduceForm.vue'
import CertificationForm from '@/components/challenge/CertificationForm.vue'
import BattleForm from '@/components/challenge/BattleForm.vue'
export default {
  name: 'FeedTab',
  components: {
    IntroduceForm,
    CertificationForm,
    BattleForm,
  },
  data () {
    return {
      tab: null,
    }
  },
  // props: {
  //   challengeItems: {
  //     type: Object,
  //   }
  // }
}
</script>

<style>

</style>